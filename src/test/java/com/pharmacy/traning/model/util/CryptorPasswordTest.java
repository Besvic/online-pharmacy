package com.pharmacy.traning.model.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class CryptorPasswordTest {

    private static final Logger logger = LogManager.getLogger();
    @Test
    public void encryptor() {
        String password = "12345678";
        String expected = "37-4390-46-125-866410-12100-57109113607-83";
        logger.info(CryptorPassword.getInstance().encryptor(password));

    }
}
