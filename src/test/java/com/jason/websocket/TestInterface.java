package com.jason.websocket;

/**
 * @author : kohyusik
 * @version : 1.0
 * @date : 2018-09-20
 * @description :
 */
public interface TestInterface {
    
    default void test() {
    
        System.out.println("test");
    }
    
    public void test2();
}
