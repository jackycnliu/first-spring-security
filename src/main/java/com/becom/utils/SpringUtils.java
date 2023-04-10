package com.becom.utils;


import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.charset.StandardCharsets;

public class SpringUtils {
    public static void main(String[] args) {
        String str = Base64.encodeBase64String("zhangsan:zhangsan".getBytes(StandardCharsets.UTF_8));
       String bcryptPasswod =   new BCryptPasswordEncoder().encode("zhangsan");
        System.out.println(bcryptPasswod);
    }

}
