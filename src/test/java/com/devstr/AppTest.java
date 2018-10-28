package com.devstr;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AppTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void emptyTest() {

    }

    @Test
    public void emailNotification() {
        assertTrue(trueMethod());
    }

    private boolean trueMethod() {
        return true;
    }

}
