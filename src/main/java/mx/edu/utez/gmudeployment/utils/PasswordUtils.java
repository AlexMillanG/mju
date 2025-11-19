package mx.edu.utez.gmudeployment.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.time.LocalDateTime;

public class PasswordUtils {
    public static String encodePassword(String raw){
        return new BCryptPasswordEncoder().encode(raw);
    }


    public static boolean matchPassword(String raw, String encoded){
        return new BCryptPasswordEncoder().matches(raw, encoded);
    }

    public static String generateEncodedPassword(String username, String fullName){
        String raw = fullName.charAt(0) + username + LocalDateTime.now().getYear();
        System.out.println(raw);
        return encodePassword(raw);
    }
}
