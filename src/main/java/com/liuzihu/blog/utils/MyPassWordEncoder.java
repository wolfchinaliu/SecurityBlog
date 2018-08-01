package com.liuzihu.blog.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Jerry
 * @version 1.0
 * @Description
 * @Date Create by 2018-06-01 8:33
 * @Modified by
 */
public class MyPassWordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        byte[] passwordBytes = new byte[0];
        try {
            passwordBytes = rawPassword.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String password = MD5(passwordBytes);
        return password;
    }

    private String MD5(byte[] passwordBytes) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md5.update(passwordBytes);
        byte[] digest = md5.digest();
        return bytesToHex(digest);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            byte[] bytes = rawPassword.toString().getBytes("utf-8");
            if (MD5(bytes).equals(encodedPassword)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("is matches");
        }
    }
    public  String bytesToHex(byte[] bytes)
    {

        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes)
        {
            String tmp = Integer.toHexString(b & 0xFF);
            if (tmp.length() == 1)
            {
                tmp = "0" + tmp;
            }
            sb.append(tmp);
        }

        return sb.toString().toUpperCase();
    }
}
