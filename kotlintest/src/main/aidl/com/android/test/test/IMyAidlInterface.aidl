// IMyAidlInterface.aidl
package com.android.test.test;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
  void setName(String name);
  String getName();
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);
}
