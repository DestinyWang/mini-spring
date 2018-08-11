package org.destiny.beans.factory.support;

import org.destiny.beans.BeanDefinition;

/**
 * @author destiny
 * ------------------------------------------------------------------
 * <p>
 * ------------------------------------------------------------------
 * @version JDK 1.8.0_101
 * @since 2018/8/11 10:06
 */
public interface BeanDefinitionRegistry {

    /**
     * 根据 id 获取 BeanDefinition
     * @param beanId
     * @return
     */
    BeanDefinition getBeanDefinition(String beanId);

    /**
     * 注册 BeanDefinition
     * @param beanId
     * @param beanDefinition
     */
    void registryBeanDefinition(String beanId, BeanDefinition beanDefinition);

}
