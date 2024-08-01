package com.stone.crm.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @description AES加密/解密
 */
@Slf4j
public class AesUtils {

    /**
     * AES 加密
     *
     * @param str 待加密内容
     * @param key 加密key
     * @return 加密内容
     */
    public static String encrypt(String str, String key) {
        try {
            byte[] raw = key.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encrypted = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeBase64String(encrypted);
        } catch (Exception e) {
            log.error("AES 加密异常", e);
            throw new RuntimeException(e);
        }
    }


    /**
     * AES 解密
     *
     * @param str 待解密内容
     * @param key 解密key
     * @return 解密内容
     */
    public static String decrypt(String str, String key) {
        try {
            byte[] raw = key.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] encrypted = cipher.doFinal(Base64.decodeBase64(str));
            return new String(encrypted);
        } catch (Exception e) {
            log.error("AES 解密异常", e);
            throw new RuntimeException(e);
        }
    }


    /**
     * AES 解密
     *
     * @param str 待解密内容
     * @return 解密内容
     */
    public static String decrypt(String str) {
        try {
            byte[] raw = "hbt4j8ql7n7yjtfb".getBytes(StandardCharsets.UTF_8);
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] encrypted = cipher.doFinal(Base64.decodeBase64(str));
            return new String(encrypted);
        } catch (Exception e) {
            log.error("AES 解密异常", e);
            throw new RuntimeException(e);
        }
    }


//    5EcSyDPxsF0FlYWDePUMKnGwQOx66CEMnSrjEut5QgCjQLcE4gDbnfCs57mZ0ryidWPaD7FIXf3Y0sFx2Nc5Mxb63TS5BVnSSoh8WsVewVdle3nUqBFqrSArMnCTXwpfM6iWRzVGDrvZxSJphZihSClfUXVuqipc/0+lVsxelVZzAxfWFkMXlMkeEZmsYMdWdUx/4pQVSJ+CqdjtF59m/asdq+FrNghNl4dLNwdTeZ8v1/eP1X5oDvFBXBlni6/DbRhYBePfCObOIDA30QLCNPJc7ZcDL380FaXbjVjWXtHQUqeyluVZTLLlw309FVGw

    public static void main (String[] args) {
        String string = "5EcSyDPxsF0FlYWDePUMKnGwQOx66CEMnSrjEut5QgCjQLcE4gDbnfCs57mZ0ryidWPaD7FIXf3Y0sFx2Nc5Mxb63TS5BVnSSoh8WsVewVdle3nUqBFqrSArMnCTXwpfM6iWRzVGDrvZxSJphZihSClfUXVuqipc/0+lVsxelVZzAxfWFkMXlMkeEZmsYMdWdUx/4pQVSJ+CqdjtF59m/asdq+FrNghNl4dLNwdTeZ8v1/eP1X5oDvFBXBlni6/DbRhYBePfCObOIDA30QLCNPJc7ZcDL380FaXbjVjWXtHQUqeyluVZTLLlw309FVGw";
        String data = decrypt(string);
        System.out.println(data);

        String str = "{\"profession\":1,\"gender\":1,\"city\":\"哈密市\",\"cityCode\":\"650500\",\"ip\":\"39.171.240.225\",\"providentFund\":3,\"house\":1,\"loadAmount\":2,\"vehicle\":1,\"phone\":\"19848948949\",\"name\":\"测试009\",\"socialSecurity\":1,\"age\":25,\"sesameSeed\":1}";
        String data1 = encrypt(str, "hbt4j8ql7n7yjtfb");
        System.out.println(data1);

        String str1 = "{\"phone\":\"19848948\"}";
        String data2 = encrypt(str1, "hbt4j8ql7n7yjtfb");
        System.out.println(data2);
    }
}