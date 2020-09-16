package com.gozap.basetest.java;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Create by liuxue on 2020/9/9 0009.
 * description:注解
 * 内置注解
 * 元注解
 * 自定义注解
 */
class AnnotationTest {

    public static void main(String[] args) {
        // 新建Person
        Person person = new Person();
        // 获取Person的Class实例
        Class<Person> c = Person.class;
        try {
            // 获取 somebody() 方法的Method实例
            Method mSomebody = c.getMethod("somebody", new Class[]{String.class, int.class});

            // 执行该方法
            mSomebody.invoke(person, new Object[]{"lily", 18});
            iteratorAnnotations(mSomebody);


            // 获取 somebody() 方法的Method实例
            Method mEmpty = c.getMethod("empty", new Class[]{});
            // 执行该方法
            mEmpty.invoke(person, new Object[]{});
            iteratorAnnotations(mEmpty);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void iteratorAnnotations(Method method) {

        // 判断 somebody() 方法是否包含MyAnnotation注解
        if (method.isAnnotationPresent(MyAnnotation.class)) {
            // 获取该方法的MyAnnotation注解实例
            MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
            // 获取 myAnnotation的值，并打印出来
            String[] values = myAnnotation.schools();
            for (String str : values)
                System.out.printf(str + ", ");
            System.out.println();
        }

        // 获取方法上的所有注解，并打印出来
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }

    static class Person {
        @MyAnnotation(id = 1005)
        public void empty() {
            System.out.println("test1");
        }

        @MyAnnotation(name = "lilei", age = 15, id = 1006)
        public void somebody(String name, int age) {
            System.out.println("\nsomebody: "+name+", "+age);
        }
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MyAnnotation {
        //注解的参数:参数类型 + 参数名 ()
        String name() default "";

        int age() default 0;

        int id();

        String[] schools() default {"清华大学", "北京大学"};
    }
}


