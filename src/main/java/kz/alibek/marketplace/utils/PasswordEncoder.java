package kz.alibek.marketplace.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {
    public static String hash(String password){
        return BCrypt.hashpw(password,BCrypt.gensalt(12));
    }
}
