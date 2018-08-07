package org.destiny.beans.factory.support;

import org.destiny.beans.BeanDefinition;

/**
 * @author 王康
 * hzwangkang1@corp.netease.com
 * ------------------------------------------------------------------
 * <p></p>
 * ------------------------------------------------------------------
 * Corpright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2018/8/6 22:56
 */
public class GenericBeanDefinition implements BeanDefinition {

    private String id;
    private String beanClassName;

    public GenericBeanDefinition(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }

    /**
     * 获取 bean 名称
     *
     * @return
     */
    @Override
    public String getBeanClassName() {
        return beanClassName;
    }
}
