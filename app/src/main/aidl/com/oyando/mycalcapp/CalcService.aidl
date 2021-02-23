// CalcService.aidl
package com.oyando.mycalcapp;

// Declare any non-default types here with import statements

interface CalcService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
           int add(int a,int b);
           double divide(int a, int b);
           int subtract(int a, int b);
           int multiply (int a, int b);

}