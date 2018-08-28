package org.destiny.beans.context.support;

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
public class FileSystemXmlApplicationContext extends AbstractApplicationContext {

    public FileSystemXmlApplicationContext(String path) {
        super(path);
    }

    /**
     * 根据路径获取资源的抽象方法
     *
     * @param path
     * @return
     */
    @Override
    protected Resource getResourceByPath(String path) {
        return new FileSystemResource(path);
    }
}
