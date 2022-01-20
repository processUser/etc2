package org.iptime.mpage;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

public class SecretKey {
    private static UUID uuid;

    public static String makeUuid(){
        uuid = UUID.randomUUID();
        return Base64.getUrlEncoder().encodeToString(uuid.toString().getBytes(StandardCharsets.UTF_8));
    }

    public static String decod(String key){
        return new String(Base64.getUrlDecoder().decode(key));
    }
}

/*
    UUID uuid = UUID.randomUUID();
    uuid.toString().getBytes("UTF-8");
    final String str = Base64.getUrlEncoder().encodeToString(uuid.toString().getBytes("UTF-8")); // 암호화
    System.out.println(uuid);
    System.out.println(str);
    System.out.println(new String(Base64.getUrlDecoder().decode(str)));// 복호화
*/