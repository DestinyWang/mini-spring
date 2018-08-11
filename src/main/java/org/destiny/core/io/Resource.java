package org.destiny.core.io;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author destiny
 * ------------------------------------------------------------------
 * <p>
 * ------------------------------------------------------------------
 * @version JDK 1.8.0_101
 * @since 2018/8/11 11:25
 */
public interface Resource {

    /**
     * 获取该资源的输入流
     *
     * @return
     * @throws FileNotFoundException
     */
    InputStream getInputStream() throws FileNotFoundException;

    /**
     * 获取描述信息, 包括文件路径, 资源名称等
     * @return
     */
    String getDescription();

}
