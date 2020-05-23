package com.example.a2048.model;

public class Utils {

    public static int randInt(int max) {
        return (int) (Math.random() * max);
    }

    public static int randAmong(final int i1, final int i2) {
        int i = randInt(2);
        return (i == 0) ? i1 : i2;
    }
}
