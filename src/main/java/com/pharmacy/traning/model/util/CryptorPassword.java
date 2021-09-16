package com.pharmacy.traning.model.util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptorPassword {

    private static final Logger logger = LogManager.getLogger();
    private static CryptorPassword instance = null;

    private CryptorPassword(){};

    public static CryptorPassword getInstance(){
        if (instance == null)
            instance = new CryptorPassword();
        return instance;
    }
    public String encryptor(String password){
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.warn("Was catch NoSuchAlgorithmException: ", e);
            return new String();
        }
        byte[] bytes = md5.digest(password.getBytes());
      StringBuilder builder = new StringBuilder();
        for (var b: bytes) {
            builder.append(b);
        }
        System.out.println(builder);
        return builder.toString();
    }

}