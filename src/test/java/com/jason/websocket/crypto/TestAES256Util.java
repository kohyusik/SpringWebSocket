package com.jason.websocket.crypto;

import org.junit.Test;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static junit.framework.TestCase.assertEquals;

/**
 * @author : kohyusik
 * @version : 1.0
 * @date : 2018-09-27
 * @description :
 */


public class TestAES256Util {
    
    @Test
    public void keyGenerate() {
    
        KeyGenerator generator = null;
        try {
            generator = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
    
            generator.init(128, random);
            Key secureKey = generator.generateKey();
    
            assertEquals(secureKey.getAlgorithm(), "AES");
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
