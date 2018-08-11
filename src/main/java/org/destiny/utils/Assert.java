package org.destiny.utils;

/**
 * @author destiny
 * ------------------------------------------------------------------
 * <p>
 * ------------------------------------------------------------------
 * @version JDK 1.8.0_101
 * @since 2018/8/11 11:35
 */
public class Assert {

    public static void notNull(Object o, String message) {
        if (o == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
