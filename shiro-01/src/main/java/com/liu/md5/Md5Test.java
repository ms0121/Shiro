package com.liu.md5;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5Test {
    public static void main(String[] args) {

        // md5加密使用
        Md5Hash md5Hash = new Md5Hash("123");
        System.out.println("md5Hash.toHex() = " + md5Hash.toHex());

        // md5 + salt
        Md5Hash md5Hash1 = new Md5Hash("123", "lms");
        System.out.println("md5Hash1.toHex() = " + md5Hash1.toHex());

        // md5 + salt + hash
        Md5Hash md5Hash2 = new Md5Hash("123", "lms", 1024);
        System.out.println("md5Hash2.toHex() = " + md5Hash2.toHex());

    }
}
