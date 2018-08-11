package org.destiny.core.io;

import org.apache.commons.lang3.StringUtils;
import org.destiny.utils.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author destiny
 * ------------------------------------------------------------------
 * <p>
 * ------------------------------------------------------------------
 * @version JDK 1.8.0_101
 * @since 2018/8/11 11:31
 */
public class FileSystemResource implements Resource {

    private final String path;
    private final File file;

    public FileSystemResource(String path) {
        Assert.notNull(path, "path must not null");
        this.path = path;
        file = new File(path);
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(file);
    }

    @Override
    public String getDescription() {
        return "file [" + file.getAbsolutePath() + "]";
    }
}
