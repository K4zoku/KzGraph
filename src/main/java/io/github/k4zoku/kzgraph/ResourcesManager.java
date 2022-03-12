package io.github.k4zoku.kzgraph;

import java.io.InputStream;
import java.net.URL;

public class ResourcesManager {

    public static URL getResource(String name) {
        return ResourcesManager.class.getClassLoader().getResource(name);
    }

    public static InputStream getResourceAsStream(String name) {
        return ResourcesManager.class.getClassLoader().getResourceAsStream(name);
    }

}
