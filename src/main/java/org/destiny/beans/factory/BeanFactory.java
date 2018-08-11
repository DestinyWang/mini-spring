package org.destiny.beans.factory;

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
 * @since 2018/8/6 22:42
 */
public interface BeanFactory {



    /**
     * 获取 bean 实例
     * @param beanId
     * @return
     */
    Object getBean(String beanId);
}
