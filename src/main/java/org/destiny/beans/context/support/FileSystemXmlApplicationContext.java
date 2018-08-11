package org.destiny.beans.context.support;

import org.destiny.beans.context.ApplicationContext;
import org.destiny.beans.factory.support.DefaultBeanFactory;
import org.destiny.beans.factory.xml.XmlBeanDefinitionReader;
import org.destiny.core.io.FileSystemResource;
import org.destiny.core.io.Resource;

/**
 * @author destiny
 * ------------------------------------------------------------------
 * <p>
 * ------------------------------------------------------------------
 * @version JDK 1.8.0_101
 * @since 2018/8/11 11:18
 */
public class FileSystemXmlApplicationContext implements ApplicationContext {

    private DefaultBeanFactory beanFactory;

    public FileSystemXmlApplicationContext(String realPath) {
        beanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        Resource resource = new FileSystemResource(realPath);
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
}
