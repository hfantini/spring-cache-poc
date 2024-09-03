package com.henriquefantini.cacheboot.interceptor;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class CustomKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {

        try
        {
            String id = params[0].toString();
            String auth = params[1].toString();
            byte[] bytes = (id + auth).getBytes();

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] hash = messageDigest.digest(bytes);

            BigInteger no = new BigInteger(1, hash);

            String retValue = no.toString(16);
            while (retValue.length() < 32) {
                retValue = "0" + retValue;
            }

            return retValue;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
