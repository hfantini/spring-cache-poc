package com.henriquefantini.cacheboot.util;

import java.util.Random;

public class RandomUtil {

    private static final String charList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static int getRandomNumberBetween(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String getRandomString(int maxLength) {
        StringBuilder sBuilder = new StringBuilder();
        Random random = new Random();

        while(sBuilder.length() < maxLength) {
            int index = RandomUtil.getRandomNumberBetween(0, charList.length());
            sBuilder.append(charList.charAt(index));
        }

        return sBuilder.toString();
    }

    public static boolean getRandomBoolean() {
        int value = getRandomNumberBetween(0, 2);

        if(value == 1) {
            return true;
        }

        return false;
    }
}
