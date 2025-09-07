package com.hren.spring;

import java.io.File;
import java.net.URL;

public class HrenApplicationContext {

    private Class configClass;

    public HrenApplicationContext(Class configClass) {
        this.configClass = configClass;

        if(configClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan componentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);

            String path = componentScanAnnotation.value();
            path = path.replace('.', '/');

            ClassLoader classLoader = HrenApplicationContext.class.getClassLoader();

            URL resource = classLoader.getResource(path);
            File file = new File(resource.getFile());
            System.out.println(file);
            if (file.isDirectory()) {
                File[] files = file.listFiles();

                for (File f : files) {
                    String fileName = f.getAbsolutePath();
                    String className = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
                    System.out.println(fileName);

                    if (fileName.endsWith(".class")) {
                         Class<?> clazz = classLoader.loadClass()
                    }
                }
            }
        }
    }

    // 获取bean
    public Object getBean(String beanName) {
        return null;
    }
}
