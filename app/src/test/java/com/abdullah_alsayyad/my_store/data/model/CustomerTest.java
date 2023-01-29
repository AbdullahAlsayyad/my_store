package com.abdullah_alsayyad.my_store.data.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CustomerTest {

    @Test
    public void equalsTest() {
        assertTrue(new Customer(1, "", "", "").equals(new Customer(1, "s", "c", "a")));
    }
}