package framework.data.readers;

import java.io.InputStream;

public final class ResourceReader {
    private ResourceReader(){}

    public static InputStream open(String resourcePath){
        if (resourcePath == null || resourcePath.isBlank()){
            throw new IllegalArgumentException("Resource path is not null or blank");
        }

        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream is = cl.getResourceAsStream(resourcePath);

        if (is == null){
            throw new IllegalArgumentException("Missing resource on classpath: " + resourcePath);
        }

        return is;
    }

}
