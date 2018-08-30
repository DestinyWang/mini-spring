package org.destiny.beans.context.support;

import org.destiny.core.io.ClassPathResource;
import org.destiny.core.io.Resource;

/**
 * @author destiny
 * ------------------------------------------------------------------
 * <p>
 * ------------------------------------------------------------------
 * @version JDK 1.8.0_101
 * @since 2018/8/11 10:51
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {


    public ClassPathXmlApplicationContext(String configPath) {
        super(configPath);
    }

    /**
     * 根据路径获取资源的抽象方法
     *
     * @param path
     * @return
     */
    @Override
    protected Resource getResourceByPath(String path) {
        return new ClassPathResource(path);
    }

}
