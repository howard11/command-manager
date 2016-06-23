package com.dev;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

    public UtilsTest() {
    }


    @Test
    public void testSplitCommand() {
        String[] res = new String[]{"a", "bbb", "123 456 "};
        String[] Q = Utils.splitCommand("a    bbb             \"123 456 \"");
        String[] q = Utils.splitCommand("a bbb             '123 456 '");

        assertArrayEquals(res, Q);
        assertArrayEquals(res, q);
    }
}
