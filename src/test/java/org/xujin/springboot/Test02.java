package org.xujin.springboot;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * @author suixing
 * @date 2021-07-26-4:00 下午
 */
public class Test02 {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader mcl = new MyClassLoader(ClassLoader.getSystemClassLoader().getParent());
        Class<?> c1 = Class.forName("org.xujin.springboot.Personal", true, mcl);
        Object obj = c1.newInstance();
        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());
        InputStream resourceAsStream = c1.getResourceAsStream("Test01.class");

    }
}
