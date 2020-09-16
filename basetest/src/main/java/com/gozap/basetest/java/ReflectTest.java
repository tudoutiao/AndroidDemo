package com.gozap.basetest.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Create by liuxue on 2020/9/9 0009.
 * description: 反射
 */
class ReflectTest {
    public static void main(String[] args) {

        try {
            Class<?> c = Class.forName(args[0]);
            Constructor<?>[] constructors = c.getConstructors();
            Constructor<?> declaredConstructor = c.getDeclaredConstructor();
            Method[] methods = c.getMethods();
            Method[] declaredMethods = c.getDeclaredMethods();
            Field[] fields = c.getFields();
            Field[] declaredFields = c.getDeclaredFields();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
