package com.imooc.user.util;

import java.util.Random;

public class KeyUtil {

    public static synchronized String genUniqueKey(){
        Random random = new Random();
        //长度一致
        Integer number = random.nextInt(900000) +100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
