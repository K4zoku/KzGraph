package io.github.k4zoku.kzgraph;

import java.io.InputStream;
import java.net.URL;

public class ResourcesGetter {

    public static URL getResource(String name) {
        return ResourcesGetter.class.getClassLoader().getResource(name);
    }

    public static InputStream getResourceAsStream(String name) {
        return ResourcesGetter.class.getClassLoader().getResourceAsStream(name);
    }

}
