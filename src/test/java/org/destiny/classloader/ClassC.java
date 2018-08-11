package org.destiny.classloader;

/**
 * @author destiny
 * ------------------------------------------------------------------
 * <p>
 * ------------------------------------------------------------------
 * @version JDK 1.8.0_101
 * @since 2018/8/10 15:43
 */
public class ClassC {

    static {
        System.out.println("ClassC.static initializer");
    }
}
