package org.destiny.test.v1;

import org.destiny.beans.context.ApplicationContext;
import org.destiny.beans.context.support.ClassPathXmlApplicationContext;
import org.destiny.beans.context.support.FileSystemXmlApplicationContext;
import org.destiny.service.v1.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author destiny
 * ------------------------------------------------------------------
 * <p>
 * ------------------------------------------------------------------
 * @version JDK 1.8.0_101
 * @since 2018/8/11 10:36
 */
public class ApplicationContextTest {

    @Test
    public void testGetBean() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");
        Assert.assertNotNull(petStoreService);
    }

    @Test
    public void testGetBeanFromFileSystem() {
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("/Users/destiny/IdeaProjects/minispring/src/test/resources/petstore-v1.xml");
        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");
        Assert.assertNotNull(petStoreService);
    }
}
