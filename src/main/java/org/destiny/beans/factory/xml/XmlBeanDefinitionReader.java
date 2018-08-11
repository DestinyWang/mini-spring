package org.destiny.beans.factory.xml;

import org.destiny.beans.BeanDefinition;
import org.destiny.beans.factory.BeanCreationException;
import org.destiny.beans.factory.BeanDefinitionStoreException;
import org.destiny.beans.factory.support.BeanDefinitionRegistry;
import org.destiny.beans.factory.support.GenericBeanDefinition;
import org.destiny.core.io.Resource;
import org.destiny.utils.ClassUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @author destiny
 * ------------------------------------------------------------------
 * <p>
 * ------------------------------------------------------------------
 * @version JDK 1.8.0_101
 * @since 2018/8/11 10:02
 */
public class XmlBeanDefinitionReader {

    public static final String ID_ATTRIBUTE = "id";
    public static final String CLASS_ATTRIBUTE = "class";

    BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void loadBeanDefinitions(Resource resource) {
        try {
            registry(resource.getInputStream());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadBeanDefinitions(String configPath) {
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
            registry(inputStream);
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

    private void registry(InputStream inputStream) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new BeanCreationException("cannot read xml");
        }

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
            beanDefinitionRegistry.registryBeanDefinition(id, beanDefinition);
        }
    }
}
