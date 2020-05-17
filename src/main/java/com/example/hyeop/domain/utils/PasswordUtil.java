package com.example.hyeop.domain.utils;

public class PasswordUtil {

    public static boolean isAvailPassword(String expected, String actual){
        return expected.equals(actual);
    }

}
