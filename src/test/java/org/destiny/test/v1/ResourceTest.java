package org.destiny.test.v1;

import org.destiny.core.io.ClassPathResource;
import org.destiny.core.io.FileSystemResource;
import org.destiny.core.io.Resource;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author destiny
 * ------------------------------------------------------------------
 * <p>
 * ------------------------------------------------------------------
 * @version JDK 1.8.0_101
 * @since 2018/8/11 11:21
 */
public class ResourceTest {

    @Test
    public void testClassPathResource() throws IOException {
        Resource resource = new ClassPathResource("petstore-v1.xml");
        try (InputStream inputStream = resource.getInputStream()) {
            Assert.assertNotNull(inputStream);
        }
    }

    @Test
    public void testFileSystemResource() throws IOException {
        Resource resource = new FileSystemResource("/Users/destiny/IdeaProjects/minispring/src/test/resources/petstore-v1.xml");
        try (InputStream inputStream = resource.getInputStream()) {
            Assert.assertNotNull(inputStream);
        }
    }
}
