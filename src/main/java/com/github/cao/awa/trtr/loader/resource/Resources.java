package com.github.cao.awa.trtr.loader.resource;

import java.io.*;

/**
 * Utils for resources in jar.
 */
public class Resources {
    /**
     * Read a resource using InputStream.
     *
     * @param resource Target resource
     * @return Resources stream
     */
    public static InputStream getResource(String resource) {
        return Resources.class.getClassLoader().getResourceAsStream(resource);
    }
}

