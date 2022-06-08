package com.object.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryp {
    private static BCryptPasswordEncoder bc = new BCryptPasswordEncoder();

    /**
     * 加密密码并获取加密过后的密码
     *
     * @param password
     * @return
     */
    public static String getPaawordEncryp(String password) {
        return bc.encode(password);
    }

    /**
     * 加密密码与明文密码的判断
     *
     * @param Encrpepassword
     * @param password
     * @return
     */
    public static boolean IfPassowrd(String password, String Encrpepassword) {
        return bc.matches(password, Encrpepassword);
    }
}
