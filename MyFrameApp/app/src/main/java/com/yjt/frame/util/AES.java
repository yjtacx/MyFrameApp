package com.yjt.frame.util;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Base64;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @ClassName:
 * @Description: AES加密解密工具类
 * @author yujiangtao
 * @date 2016-2-1
 *
 */
@SuppressLint("TrulyRandom")
public class AES{

    private final static int JELLY_BEAN_4_2 = 17;
    private String PKCS5PADDINGCBC="AES/CBC/PKCS5Padding";
    private static final String PKCS5PADDINGECB="AES/ECB/PKCS5Padding";
    /**
     * 加密
     *
     * @param key
     *            密钥
     * @param src
     *            加密文本
     * @return
     * @throws Exception
     */
    public static String encrypt(String key, String src)  {
        if(StringUtils.isEmpty(src))return "";
        if(StringUtils.isEmpty(key))return src;
        try {
            byte[] rawKey = getRawKey(key.getBytes("utf-8"));
            byte[] result = encrypt(rawKey, src.getBytes("utf-8"));
//            return toHex(result);
            return Base64.encodeToString(result,Base64.DEFAULT);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 解密
     *
     * @param key
     *            密钥
     * @param encrypted
     *            待揭秘文本
     * @return
     * @throws Exception
     */
    public static String decrypt(String key, String encrypted){
        if(StringUtils.isEmpty(encrypted))return "";
        if(TextUtils.isEmpty(key))return encrypted;
        try {
            byte[] rawKey = getRawKey(key.getBytes("utf-8"));
//        byte[] enc = toByte(encrypted);
            byte[] enc = Base64.decode(encrypted, Base64.DEFAULT);
            byte[] result = decrypt(rawKey, enc);
            return new String(result, "utf-8");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取256位的加密密钥
     *
     * @param seed
     * @return
     * @throws Exception
     */
    @SuppressLint("TrulyRandom")
    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = null;
        // 在4.2以上版本中，SecureRandom获取方式发生了改变
        if (android.os.Build.VERSION.SDK_INT >= JELLY_BEAN_4_2) {
            sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        } else {
            sr = SecureRandom.getInstance("SHA1PRNG");
        }
        sr.setSeed(seed);
        // 256 bits or 128 bits,192bits
        kgen.init(256, sr);
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }

    /**
     * 真正的加密过程
     *
     * @param key
     * @param src
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] key, byte[] src) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance(PKCS5PADDINGECB);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(src);
        return encrypted;
    }

    /**
     * 真正的解密过程
     *
     * @param key
     * @param encrypted
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] key, byte[] encrypted)
            throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance(PKCS5PADDINGECB);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }
    public static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2),
                    16).byteValue();
        return result;
    }



}