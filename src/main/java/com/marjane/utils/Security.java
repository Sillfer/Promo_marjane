package com.marjane.utils;

import org.mindrot.jbcrypt.BCrypt;

public class Security {


    /**
     * hash password using bcrypt algorithm
     * @param password String
     */
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    /**
     * check if password is correct
     * @param password String
     * @param hashedPassword String
     */
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
