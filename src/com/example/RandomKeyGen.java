package com.example;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class RandomKeyGen {

    private static final String ALGORITHM = "AES";

    private static KeyGenerator keyGenerator;

    public static Key keygen(){
        try {
            keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            keyGenerator.init(128, new SecureRandom());
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
