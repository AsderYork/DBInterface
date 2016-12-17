package com.dbInterface.System;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Asder on 17.12.2016.
 */
public class Hasher {
    public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        return hash(hash(password, "MD5") + salt, "MD5");
    }

    private static String hash(String value, String algorithmName) throws NoSuchAlgorithmException {
        //Метод поддержки. Принимает строку, возвращает её хэш

        MessageDigest digest;

        digest = MessageDigest.getInstance(algorithmName);

        digest.reset();
        digest.update(value.getBytes());
        byte[] returnedByteCode = null;
        returnedByteCode = digest.digest();


        BigInteger hashInBigInteger = new BigInteger(1, returnedByteCode);

        return hashInBigInteger.toString(16);
    }
}
