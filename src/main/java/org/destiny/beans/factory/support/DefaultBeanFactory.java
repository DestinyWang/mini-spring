package org.destiny.beans.factory.support;

import org.apache.commons.lang3.StringUtils;
import org.destiny.beans.BeanDefinition;
import org.destiny.beans.factory.BeanCreationException;
import org.destiny.beans.factory.BeanFactory;
import org.destiny.utils.ClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry {

    private static final Logger logger = LoggerFactory.getLogger(DefaultBeanFactory.class);

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public DefaultBeanFactory() {
        logger.info("DefaultBeanFactory.DefaultBeanFactory()");
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
     * 注册 BeanDefinition
     *
     * @param beanId
     * @param beanDefinition
     */
    @Override
    public void registryBeanDefinition(String beanId, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanId, beanDefinition);
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
