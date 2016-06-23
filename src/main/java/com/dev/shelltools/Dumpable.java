package com.dev.shelltools;

public class Dumpable {
    public static void error(String msg, Exception e) {
        System.out.println(msg);
        e.printStackTrace();
    }
}
