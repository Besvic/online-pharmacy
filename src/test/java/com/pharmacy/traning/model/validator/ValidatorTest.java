package com.pharmacy.traning.model.validator;

import com.pharmacy.traning.model.validator.impl.ValidatorImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorTest {

    @Test
    public void isOnlyLetter() {
        assertEquals(ValidatorImpl.getInstance().isOnlyLetter("sdfg"), true);
    }
}