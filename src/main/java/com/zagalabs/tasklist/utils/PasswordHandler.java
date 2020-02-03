package com.zagalabs.tasklist.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHandler {
    private PasswordHandler(){}

    public static boolean isPasswordTheSame(String password, String hashPassword) {
        return BCrypt.checkpw(password, hashPassword);
    }
}
