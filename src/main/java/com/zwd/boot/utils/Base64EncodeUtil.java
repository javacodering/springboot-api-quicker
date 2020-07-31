package com.zwd.boot.utils;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;

public class Base64EncodeUtil {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * 解密
     * @param str
     * @return
     */
    public static String decodeStr(String str){
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return "";
        }
        return new String(Base64.decodeBase64(new String(str).getBytes(DEFAULT_CHARSET)),DEFAULT_CHARSET).trim();
    }

    /**
     * 加密
     *
     * @param str
     * @return
     */
    public static String encodeStr(String str){
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return "";
        }
        return new String(Base64.encodeBase64Chunked(str.getBytes(DEFAULT_CHARSET)),DEFAULT_CHARSET).trim();
    }

}