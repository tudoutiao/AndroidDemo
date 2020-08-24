package com.android.test.model;


class Test {
    public static void main(String[] args) {
        //产品A
        Factory factoryA = new FactoryA();
        Product productA = factoryA.create();
        productA.show();
        //产品B
        Factory factoryB = new FactoryB();
        Product productB = factoryB.create();
        productB.show();
    }
}

/**
 * Create by liuxue on 2020/8/21 0021.
 * description:
 */
abstract class Product {
    abstract void show();
}

class ProductA extends Product {

    @Override
    void show() {
        System.out.println("ProductA");
    }
}

class ProductB extends Product {
    @Override
    void show() {
        System.out.println("ProductB");
    }
}

//抽象工厂类
abstract class Factory {
    public abstract Product create();
}

//具体工厂类A
class FactoryA extends Factory {
    @Override
    public Product create() {
        return new ProductA();//创建ProductA
    }
}

//具体工厂类B
class FactoryB extends Factory {
    @Override
    public Product create() {
        return new ProductB();//创建ProductB
    }
}





