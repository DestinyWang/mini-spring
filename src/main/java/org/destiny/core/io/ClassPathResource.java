package org.destiny.core.io;

import org.destiny.utils.ClassUtil;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author destiny
 * ------------------------------------------------------------------
 * <p>
 * ------------------------------------------------------------------
 * @version JDK 1.8.0_101
 * @since 2018/8/11 11:27
 */
public class ClassPathResource implements Resource {

    private final String path;
    private final ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtil.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        InputStream inputStream = classLoader.getResourceAsStream(path);
        if (inputStream == null) {
            throw new FileNotFoundException(path + " cannot be opened");
        }
        return inputStream;
    }

    @Override
    public String getDescription() {
        return path;
    }
}
