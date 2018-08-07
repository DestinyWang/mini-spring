package org.destiny.utils;

/**
 * @author 王康
 * hzwangkang1@corp.netease.com
 * ------------------------------------------------------------------
 * <p></p>
 * ------------------------------------------------------------------
 * Corpright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @version JDK 1.8.0_101
 * @since 2018/8/6 23:06
 */
public class ClassUtil {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        if (classLoader == null) {
            classLoader = ClassUtil.class.getClassLoader();
            if (classLoader == null) {
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
