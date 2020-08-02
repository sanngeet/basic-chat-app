package com.udacity.jwdnd.c1.review.service;

import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.logging.Logger;

@Service
public class HashService {

    public String getHashedValue(@NonNull String data, @NonNull String salt) {
        byte[] hashedValue = null;

        KeySpec spec = new PBEKeySpec(data.toCharArray(), salt.getBytes(), 5000, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hashedValue = factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.out.println(e.getMessage().toString());
//            logger.error(e.getMessage());
        }

        return Base64.getEncoder().encodeToString(hashedValue);
    }
}
