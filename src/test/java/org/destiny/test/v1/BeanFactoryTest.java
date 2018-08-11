package org.destiny.test.v1;

import org.destiny.beans.BeanDefinition;
import org.destiny.beans.factory.BeanCreationException;
import org.destiny.beans.factory.support.DefaultBeanFactory;
import org.destiny.beans.factory.xml.XmlBeanDefinitionReader;
import org.destiny.service.v1.PetStoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author 王康
 * destinywk@163.com
 * ------------------------------------------------------------------
 * <p></p>
 * ------------------------------------------------------------------
 *
 * @version JDK 1.8.0_101
 * @since 2018/8/6 22:33
 */
public class BeanFactoryTest {

    private DefaultBeanFactory beanFactory;

    @Before
    public void setUp() {
        beanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("petstore-v1.xml");
    }

    @Test
    public void testGetBean() {

        BeanDefinition definition = beanFactory.getBeanDefinition("petStore");
        assertEquals("org.destiny.service.v1.PetStoreService", definition.getBeanClassName());

        PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStore");
        assertNotNull(petStoreService);
    }

    @Test
    public void testInvalidBean() {
        try {
            beanFactory.getBean("invalidBean");
        } catch (BeanCreationException ex) {
            return;
        }
        Assert.fail("expect BeanCreationException");
    }
}
