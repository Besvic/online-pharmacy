package com.pharmacy.traning.model.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CryptorPasswordTest {

    private static final CryptorPassword crypto = CryptorPassword.getInstance();

    @Test
    public void encryptor() {
        String actual = crypto.encryptor("12345678");
        String expected = "37-4390-46-125-866410-12100-57109113607-83";
        Assertions.assertEquals(expected, actual);

    }
}
