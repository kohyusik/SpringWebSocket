package com.jason.websocket;

import org.junit.Test;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class TestMain {

    @Test
    public void binaryTest() {
    
        //        int mask = 0b11100101111011101111111111010000;
        int mask =    0b11100101;
        int encoded = 0b11010100;
        int decoded = 0b00110001;
    
        decoded = mask ^ encoded;
    
        System.out.println(mask);
        System.out.println(encoded);
        System.out.println((char)decoded);
        System.out.println(0b10000001 - 128);
    
        System.out.println(229 ^ 212);
        
    }
    
    @Test
    public void test() {
    
    }
    
}
