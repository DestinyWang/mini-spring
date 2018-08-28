package org.destiny.beans.factory;

/**
 * @author 王康
 * destinywk@163.com
 * ------------------------------------------------------------------
 * <p></p>
 * ------------------------------------------------------------------
 *
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
