package org.destiny.other;

import org.destiny.classloader.ClassB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author destiny
 * ------------------------------------------------------------------
 * <p>
 * ------------------------------------------------------------------
 * @version JDK 1.8.0_101
 * @since 2018/8/10 15:44
 */
public class ClassA {

    private static final Logger logger = LoggerFactory.getLogger(ClassA.class);

    public static void main(String[] args) {
        ClassLoader classLoader = ClassA.class.getClassLoader();
        logger.info("ClassA.class.getClassLoader() -> {}", classLoader);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    Class<?> bClass = classLoader.loadClass("org.destiny.classloader.ClassB");
                    ClassLoader classLoader = ClassA.class.getClassLoader();
                    Class<?> bClass = classLoader.loadClass("org.destiny.classloader.ClassB");
                    try {
                        ClassB b = (ClassB) bClass.newInstance();
                        logger.info("b -> {}", b);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
