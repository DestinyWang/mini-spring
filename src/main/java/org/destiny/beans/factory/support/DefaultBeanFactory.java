package org.destiny.beans.factory.support;

import org.destiny.beans.BeanDefinition;
import org.destiny.beans.factory.BeanFactory;

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
    public DefaultBeanFactory(String configPath) {

    }


    /**
     * 获取 bean 的定义信息
     *
     * @param beanId
     * @return
     */
    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return null;
    }

    /**
     * 获取 bean 实例
     *
     * @param beanId
     * @return
     */
    @Override
    public Object getBean(String beanId) {
        return null;
    }
}
