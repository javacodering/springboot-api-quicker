package com.zwd.boot.utils;


import cn.hutool.core.date.DateUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Aes加密工具
 * @author 随风逐梦
 */
public class AesUtils {
	
	public static void main(String[] args) {
		String uuid = UuidUtils.creatUUID();

		String content = "token_"+ DateUtil.format(new Date(),"yyyyMMdd")+"_"+uuid;
		System.out.println("内容："+content);
		byte[] encrypt = AesUtils.encrypt(content, PASSWORD_SECRET_KEY, 16);//加密
		String parseByte2HexStr = AesUtils.parseByte2HexStr(encrypt);//转换为16进制字符串
		System.out.println("加密后："+parseByte2HexStr);
		byte[] parseHexStr2Byte = AesUtils.parseHexStr2Byte(parseByte2HexStr);
		String decrypt = AesUtils.decrypt(parseHexStr2Byte, PASSWORD_SECRET_KEY, 16);
		System.out.println("解密后："+decrypt);
		
		System.out.println("UUID："+uuid);
	}

	/**
	 * 秘钥
	 */
	public static final String PASSWORD_SECRET_KEY = "EasyRailEveryday";
	
	/**
	 * 初始向量
	 */
	public static final String INITIAL_VECTOR = "EasyRailEasyRail";

    /**
     * 补码方式
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
	
    /**
     * 加密
     * @param content 需要加密的内容
     * @param password  加密密码
     * @param keySize 密钥长度16,24,32(密码长度为24和32时需要将local_policy.jar/US_export_policy.jar两个jar包放到JRE目录%jre%/lib/security下)
     * @return
     */
    public static byte[] encrypt(String content, String password, int keySize){
    	try {                              
    		//定义加密算法AES、算法模式ECB、补码方式PKCS5Padding
    		//Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    		//定义加密算法AES 算法模式CBC、补码方式PKCS5Padding
    		Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
    		
        	//密钥长度不够用0补齐。
    		//ZeroPadding(password.getBytes(Base64Util.DEFAULT_CHARSET), keySize)   
    		SecretKeySpec key = new SecretKeySpec(ZeroPadding(password.getBytes(Base64EncodeUtil.DEFAULT_CHARSET), keySize), "AES");
    		
            //CBC模式模式下初始向量 不足16位用0补齐
            IvParameterSpec iv = new IvParameterSpec(ZeroPadding(INITIAL_VECTOR.getBytes(Base64EncodeUtil.DEFAULT_CHARSET),16));
            byte[] byteContent = content.getBytes();  
            //初始化加密
            //ECB
            //cipher.init(Cipher.ENCRYPT_MODE, key);
            //CBC 
			cipher.init(Cipher.ENCRYPT_MODE, key,iv);
            byte[] result = cipher.doFinal(byteContent);
            return result; 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
        	e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    /**解密
     * @param content  待解密内容
     * @param password 解密密钥
     * @param keySize 密钥长度16,24,32(密码长度为24和32时需要将local_policy.jar/US_export_policy.jar两个jar包放到JRE目录%jre%/lib/security下)
     * @return
     */
    public static String decrypt(byte[] content, String password, int keySize) {
        try { 
        	//密钥长度不够用0补齐。
    		SecretKeySpec key = new SecretKeySpec(ZeroPadding(password.getBytes(), keySize), "AES");
    		//定义加密算法AES、算法模式ECB、补码方式PKCS5Padding
            //Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    		//定义加密算法AES 算法模式CBC、补码方式PKCS5Padding
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //CBC模式模式下初始向量 不足16位用0补齐
            IvParameterSpec iv = new IvParameterSpec(ZeroPadding(INITIAL_VECTOR.getBytes(Base64EncodeUtil.DEFAULT_CHARSET),16));
            // 初始化解密
            //ECB
            //cipher.init(Cipher.DECRYPT_MODE, key);
            //CBC
            cipher.init(Cipher.DECRYPT_MODE, key,iv);
            byte[] result = cipher.doFinal(content);
            return new String(result,Base64EncodeUtil.DEFAULT_CHARSET);
        } catch (NoSuchAlgorithmException e) {
        	e.printStackTrace();
        } catch (NoSuchPaddingException e) {
        	e.printStackTrace();
        } catch (InvalidKeyException e) {
        	e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
        	e.printStackTrace();
        } catch (BadPaddingException e) {
        	e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e){
        	e.printStackTrace();
        } catch (Exception e){
        	e.printStackTrace();
        }
        return null;
    }
    
    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                    hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    
    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {//CD9DDD1A768A868718A9F7A7C589E998AC70D4979CC1F6D49C239321C3B578CD84F8EA600152707818C002472EFBE38F0A20E5FF5E4F88A8B067E90C42BCAAC0
        if (hexStr.length() < 1){
        	return null;
        }
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    
    /**
     * 字符达不到指定长度补0
     * @param in 字符数组
     * @param blockSize 长度
     * @return
     */
    public static byte[] ZeroPadding(byte[] in,Integer blockSize){
    	Integer copyLen = in.length;
    	if (copyLen > blockSize) {
			copyLen = blockSize;
		}
    	byte[] out = new byte[blockSize];
    	System.arraycopy(in, 0, out, 0, copyLen);
    	return out;
    }

}
