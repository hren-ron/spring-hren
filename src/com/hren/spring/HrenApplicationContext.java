package com.hren.spring;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

public class HrenApplicationContext {

    private Class configClass;

    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();

    public HrenApplicationContext(Class configClass) {
        this.configClass = configClass;

        // 扫描beanDefinition --> beanDefinitionMap
        if (configClass.isAnnotationPresent(ComponentScan.class)) {
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
                    //System.out.println(fileName);
                    className = className.replace("\\", ".");
                    System.out.println( className);
                    try {
                        Class<?> clazz = classLoader.loadClass(className);
                        if (fileName.endsWith(".class")) {

                            if (clazz.isAnnotationPresent(Component.class)) {

                                Component componentAnnotation = clazz.getAnnotation(Component.class);
                                String beanName = componentAnnotation.value();

                                // 如果没有指定beanName，则默认为类名首字母小写
                                if (beanName.equals("")) {
                                    // 变成首字母小写
                                    beanName = Introspector.decapitalize(clazz.getSimpleName());
                                }

                                // BeanDefinition对象创建
                                BeanDefinition beanDefinition = new BeanDefinition();
                                if(clazz.isAnnotationPresent(Scope.class)) {
                                    Scope scopeAnnotation = clazz.getAnnotation(Scope.class);
                                    beanDefinition.setScope(scopeAnnotation.value());
                                } else {
                                    beanDefinition.setScope("singleton");
                                }

                                beanDefinition.setType(clazz);

                                beanDefinitionMap.put(beanName, beanDefinition);
                            }

                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }


                }
            }
        }

        // 创建单例bean --> singletonObjects
        for (String beanName : beanDefinitionMap.keySet()) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if ("singleton".equals(beanDefinition.getScope())) {
                Object bean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, bean);
            }
        }
    }

    private Object createBean(String beanName, BeanDefinition beanDefinition) {

        Class clazz = beanDefinition.getType();
        try {
            Object instance = clazz.getConstructor().newInstance();

            // 实现依赖注入
            for (Field field : clazz.getDeclaredFields()) {
                if(field.isAnnotationPresent(Autowired.class)) {
                    // 才能赋值
                    field.setAccessible(true);
                    // 注入属性值
                    field.set(instance, getBean(field.getName()));
                }
            }

            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 获取bean
    public Object getBean(String beanName) {

        // 根据名字找到对应的类，判断类是否单例

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

        if (beanDefinition == null) {
            throw new NullPointerException();
        } else {
            String scope = beanDefinition.getScope();
            if ("singleton".equals(scope)) {
                Object singletonBean = singletonObjects.get(beanName);
                //依赖注入时，如果单例对象不存在，则创建
                if (singletonBean == null) {
                    Object object = createBean(beanName, beanDefinition);
                    singletonObjects.put(beanName, object);
                }
                return singletonObjects.get(beanName);
            } else {
                return createBean(beanName, beanDefinition);
            }
        }
    }
}