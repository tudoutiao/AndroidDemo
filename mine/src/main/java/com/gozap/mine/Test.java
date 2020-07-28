package com.gozap.mine;

import java.lang.reflect.Constructor;

/**
 * Create by liuxue on 2020/7/24 0024.
 * description:
 */

class Food {
}

class Fruit extends Food {
}

class Apple extends Fruit {
}

class Banana extends Fruit {
}

class Plate  {
    private String item;

    public Plate(String t) {
        item = t;
    }

    public Plate() {

    }


    public void set(String t) {
        item = t;
    }

    public String get() {
        return item;
    }


}

class Test {
    public static void main(String[] args) throws Exception {
        Plate plate = Plate.class.newInstance();

        Plate plate2 = plate.getClass().newInstance();

        Class plateClass = Class.forName("com.gozap.mine.Plate");
        Plate plate3 = (Plate) plateClass.newInstance();

        Constructor<?>[] constructors = Plate.class.getConstructors();
        Constructor<?>[] declaredConstructors = Plate.class.getDeclaredConstructors();
        System.out.println("--" + constructors);
        System.out.println("--" + declaredConstructors);

        Plate.class.getConstructor(new Class[]{}).newInstance("a");
        Plate.class.getDeclaredConstructor(new Class[]{}).newInstance("a");

        System.out.println(plate);
        System.out.println(plate2);
        System.out.println(plate3);

    }


}


