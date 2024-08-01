package com.stone.quartz.util;




import java.io.IOException;
        import java.security.*;
        import java.security.spec.PKCS8EncodedKeySpec;
        import java.util.*;

/**
 */
public class RSAUtil {


    public static String getSign(Map<String, String> params, String key) {
        try {


            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key));

            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyf.generatePrivate(priPKCS8);
            Signature sign = Signature.getInstance("SHA1withRSA");
            sign.initSign(privateKey);
            String Paramstr = createLinkString(params);
            byte[] data = Paramstr.getBytes();
            //更新用于签名的数据
            sign.update(data);
            byte[] signature = sign.sign();
            return Base64.getEncoder().encodeToString(signature);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String createLinkString(Map<String, String> params) {

        ArrayList<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;

    }
}