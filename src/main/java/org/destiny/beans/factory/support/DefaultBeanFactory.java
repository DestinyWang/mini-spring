package org.destiny.beans.factory.support;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.destiny.beans.BeanDefinition;
import org.destiny.beans.factory.BeanCreationException;
import org.destiny.beans.factory.BeanDefinitionStoreException;
import org.destiny.beans.factory.BeanFactory;
import org.destiny.utils.ClassUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 王康
 * hzwangkang1@corp.netease.com
 * ------------------------------------------------------------------
 * <p></p>
 * ------------------------------------------------------------------
 * Corpright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2018/8/6 22:42
 */
public class DefaultBeanFactory implements BeanFactory {

    public static final String ID_ATTRIBUTE = "id";
    public static final String CLASS_ATTRIBUTE = "class";

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public DefaultBeanFactory(String configPath) {
        loadBeanDefinition(configPath);
    }

    /**
     * 通过 dom4j 解析传入的配置文件
     * 使用 ClassLoader.getResourceAsStream() 的方式读取 classPath 下的配置文件路径
     * 默认配置文件放在 resources 下
     *
     * @param configPath 配置文件路径
     * @see java.lang.ClassLoader#getResourceAsStream(String)
     */
    private void loadBeanDefinition(String configPath) {
        InputStream inputStream = null;
        try {
            /*
             * Class.getResourceAsStream() 会指定要加载的资源路径与当前类所在包的路径一致。
             * 例如有一个 MyTest 类在包 org.destiny 下, 那么 MyTest.class.getResourceAsStream("path")
             * 会在 org.destiny 包下查找相应的资源。
             * 而如果这个 path 是以 '/' 开头的，那么就会从 classpath 的根路径下开始查找。
             * -----------------------------------------------------------------------------------------
             * ClassLoader.getResourceAsStream() 无论要查找的资源前面是否带'/' 都会从classpath的根路径下查找。
             * -----------------------------------------------------------------------------------------
             * MyTest.class.getClassLoader().getResourceAsStream("/name")
             * 和 ClassLoader.getClassLoader().getResourceAsStream("name") 的效果是一样的。
             */
            ClassLoader defaultClassLoader = ClassUtil.getDefaultClassLoader();
            inputStream = defaultClassLoader.getResourceAsStream(configPath);
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);

            // <beans>
            Element root = document.getRootElement();
            Iterator<Element> iterator = root.elementIterator();
            while (iterator.hasNext()) {
                // <bean>
                Element element = iterator.next();
                // id=""
                String id = element.attributeValue(ID_ATTRIBUTE);
                // class=""
                String beanClassName = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(id, beanClassName);
                beanDefinitionMap.put(id, beanDefinition);
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("error parsing xml with path: " + configPath);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 获取 bean 的定义信息
     *
     * @param beanId
     * @return
     */
    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        if (!StringUtils.isNotEmpty(beanId)) {
            throw new NullPointerException("beanId can't be null");
        }
        return beanDefinitionMap.get(beanId);
    }

    /**
     * 获取 bean 实例
     *
     * @param beanId
     * @return
     */
    @Override
    public Object getBean(String beanId) {
        if (!StringUtils.isNotEmpty(beanId)) {
            throw new NullPointerException("beanId can't be null");
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanId);
        if (beanDefinition == null) {
            throw new BeanCreationException("Bean Definition doesn't exist");
        }
        // 获取类全路径名
        ClassLoader defaultClassLoader = ClassUtil.getDefaultClassLoader();
        String beanClassName = beanDefinition.getBeanClassName();
        // 反射创建
        try {
            Class<?> clazz = defaultClassLoader.loadClass(beanClassName);
            return clazz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("error creating bean with name: " + beanClassName);
        }
    }
}
