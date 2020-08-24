package com.android.test.model;

import android.app.ActivityManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Create by liuxue on 2020/8/22 0022.
 * description:
 */
class ProxyTest {
    public static void main(String[] args) {
        People people=new SomeBody();//创建委托者
        //
//        BuyProxy proxyBody = new BuyProxy(people);
//        proxyBody.buy();
        //
        BuyDynamicProxy buyDynamicProxy = new BuyDynamicProxy(people);//创建代理
        ClassLoader classLoader=people.getClass().getClassLoader();
        People body = (People) Proxy.newProxyInstance(classLoader, new Class[]{People.class}, buyDynamicProxy);//通过 Proxy 创建海外代购实例 ，实际上通过反射来实现的。
        body.buy();//调用代购的buy
    }
}

//*********静态代理
interface People {
    void buy();
}

class SomeBody implements People {
    @Override
    public void buy() {
        System.out.println("我想买包");
    }
}

class BuyProxy implements People {
    People people;

    public BuyProxy(People p) {
        this.people = p;
    }


    @Override
    public void buy() {
        System.out.println("我是代购");
        people.buy();
    }
}

//*************动态代理
class BuyDynamicProxy implements InvocationHandler {

    private Object object;//被代理的对象

    public BuyDynamicProxy(Object o) {
        this.object = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是代购");
        Object result = method.invoke(object, args);//调用被代理对象的方法
        return result;
    }
}