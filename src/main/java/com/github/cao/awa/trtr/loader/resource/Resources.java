package com.github.cao.awa.trtr.loader.resource;

import java.io.*;

public class Resources {
    public static InputStream getResource(String resource, Class<?> getC) {
        return getC.getClassLoader().getResourceAsStream(resource);
    }

    public static File getResourceByFile(String resource, Class<?> getC) {
        return new File(String.valueOf(getC.getResource(resource)));
    }
}

