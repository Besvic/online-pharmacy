package com.pharmacy.traning.validator;

import com.pharmacy.traning.validator.impl.ValidatorProductImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorTest {

    @Test
    public void isOnlyLetter() {
        assertEquals(ValidatorProductImpl.getInstance().isOnlyLetter("sdfg"), true);
    }
}