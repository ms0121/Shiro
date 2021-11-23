package com.liu.utils;

import java.util.Random;

public class SaltUtils {
    public static String getSalt(int n){
        String str = "ABCDEFJHIJKLMNOPQRSTUVWSXYZabcdefjhijklmnopqrstuvwsxyz0123456789(*&^$#@!).,";
        int len = str.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = str.charAt(new Random().nextInt(len));
            builder.append(c);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String salt = getSalt(8);
        System.out.println("salt = " + salt);
    }
}
