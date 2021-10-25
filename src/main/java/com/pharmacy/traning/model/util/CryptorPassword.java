package com.pharmacy.traning.model.util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Besarab Victor
 * The type Cryptor password.
 */
public final class CryptorPassword {

    private static final Logger logger = LogManager.getLogger();
    private static CryptorPassword instance = null;

    private CryptorPassword(){};

    /**
     * Get instance cryptor password.
     *
     * @return the cryptor password
     */
    public static CryptorPassword getInstance(){
        if (instance == null)
            instance = new CryptorPassword();
        return instance;
    }

    /**
     * Encryptor string password by user.
     *
     * @param password the password
     * @return the string
     */
    public String encryptor(String password){
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.warn("Was catch NoSuchAlgorithmException: ", e);
            return "";
        }
        byte[] bytes = md5.digest(password.getBytes());
        StringBuilder builder = new StringBuilder();
        for (var b: bytes) {
            builder.append(b);
        }
        return builder.toString();
    }

}
