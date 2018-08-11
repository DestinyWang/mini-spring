package org.destiny.beans.context.support;

import org.destiny.beans.context.ApplicationContext;
import org.destiny.beans.factory.support.DefaultBeanFactory;
import org.destiny.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author destiny
 * ------------------------------------------------------------------
 * <p>
 * ------------------------------------------------------------------
 * @version JDK 1.8.0_101
 * @since 2018/8/11 10:51
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory = null;

    public ClassPathXmlApplicationContext(String configPath) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(configPath);
    }

    /**
     * 获取 bean 实例
     *
     * @param beanId
     * @return
     */
    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }
}
