package com.zwd.boot.utils;

import java.util.UUID;
/**
 * UUID 工具类
 * @author 随风逐梦
 */
public class UuidUtils {

    public static String creatUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }	
    
     public static void main(String[] args) {
		System.out.println(UuidUtils.creatUUID());
	}
}
