package org.apereo.cas.web.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ljs
 * @date 2022-07-01
 * @description
 */
public class EncryptUtil {

    private static final String ALG_STRING_MD = "MD5";
    private static final String ALG_STRING = "AES";
    private static final String MD5 = "MD5";
    private static final String SHA = "SHA";


    private static byte[] genKeyFromPassword(String str) {
        return md5sum(str.toString().getBytes());
    }

    private static byte[] md5sum(byte[] buffer) {
        try {
            MessageDigest md5 = MessageDigest.getInstance(ALG_STRING_MD);
            md5.update(buffer);
            return md5.digest();
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static String getMd5String(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            // 一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // e10adc3949ba59abbe56e057f20f883e
        System.out.println(getMd5String("123456"));
    }
}
