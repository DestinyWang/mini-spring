package org.destiny.beans.context.support;

import org.destiny.beans.context.ApplicationContext;
import org.destiny.beans.factory.support.DefaultBeanFactory;
import org.destiny.beans.factory.xml.XmlBeanDefinitionReader;
import org.destiny.core.io.Resource;

/**
 * design by 2018/8/11 15:47
 *
 * @author destiny
 * @version JDK 1.8.0_101
 * @since JDK 1.8.0_101
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefaultBeanFactory beanFactory = null;

    public AbstractApplicationContext(String path) {
        beanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        Resource resource = this.getResourceByPath(path);
        reader.loadBeanDefinitions(resource);
    }

    /**
     * 获取 bean 实例
     *
     * @param beanId
     * @return
     */
    @Override
    public Object getBean(String beanId) {
        return beanFactory.getBean(beanId);
    }

    /**
     * 根据路径获取资源的抽象方法
     * @param path
     * @return
     */
    protected abstract Resource getResourceByPath(String path);
}
