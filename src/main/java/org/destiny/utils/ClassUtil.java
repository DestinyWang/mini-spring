package org.destiny.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 王康
 * destinywk@163.com
 * ------------------------------------------------------------------
 * <p></p>
 * ------------------------------------------------------------------
 *
 * @version JDK 1.8.0_101
 * @since 2018/8/6 23:06
 */
public class ClassUtil {

    private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 获取一个 ClassLoader
     *  1. 通过 Thread.currentThread().getContextClassLoader() 尝试获取
     *  由于 spring 对外提供 jar 包, 可能被其他组件(如 Tomcat)的 ClassLoader 加载
     *  接下来 Spring 还需要去装载其他的 xml 文件,
     *  此时 xml 文件的 path 和当前 Spring 的 ClassLoader  是否处于一个环境
     *  所以 Tomcat 可以给当前运行 Spring 的线程设置 ContextClassLoader
     *  @see java.lang.Thread#setContextClassLoader(ClassLoader)
     *
     *  2. 通过 ClassUtil.class.getClassLoader() 尝试获取
     *  如果没有设置 ContextClassLoader, 则获取本类的 ClassLoader
     *  该 ClassLoader 保证 classpath 就是当前项目路径
     *
     *  3. 通过 ClassLoader.getSystemClassLoader() 尝试获取
     *  如果上一步返回空, 只有一种情况是加载该类的是 BootstrapClassLoader
     * @return
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        if (classLoader == null) {
            // No thread context class loader -> use class loader of this class
            classLoader = ClassUtil.class.getClassLoader();
            if (classLoader == null) {
                // getClassLoader() returning null indicates the bootstrap ClassLoader
                try {
                    classLoader = ClassLoader.getSystemClassLoader();
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
            }
        }
        return classLoader;
    }

}
