package com.rainsen.display.util;

import com.rainsen.display.common.Constant;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MD5Util {

    public static String getMD5String(String stringValue) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return Base64.getEncoder().encodeToString(md5.digest((stringValue + Constant.USER_SALT).getBytes()));
    }

    public static void main(String[] args) {
        String md5;
        try {
            md5 = getMD5String("12345");
            System.out.println(md5);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
