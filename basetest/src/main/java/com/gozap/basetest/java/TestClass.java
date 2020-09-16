package com.gozap.basetest.java;

/**
 * Create by liuxue on 2020/9/4 0004.
 * description:
 */
class TestClass {
    private String name = "qwerfqwer";
    private static String name2 = "asdfasdfas";

    public static void main(String[] args) {
        Base base = new Base() {
            @Override
            public void printData() {

            }
        };

    }

    Object method(){
        int localVariable = 0;
        class Inner{
            void println(){
                System.out.println("localVariable " + localVariable);
            }
        }
        Object in = new Inner();
        return in;
    }


    private int getLength() {
        return name.length();
    }

    private static int getLength2() {
        return name2.length();
    }

    interface Base {
        void printData();
    }


    class A implements Base {
        @Override
        public void printData() {
            System.out.print(name + "--" + getLength() + "--" + name2 + "--" + getLength2());
        }
    }

    static class B implements Base {
        @Override
        public void printData() {
            System.out.print(name2 + "--" + getLength2());
        }
    }
}
