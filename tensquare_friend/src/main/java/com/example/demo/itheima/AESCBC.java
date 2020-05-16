/**
 * ClassName:AESCBC
 * Author:机械革命
 * Date:2019/9/220:06
 * Description:TODO
 */
package com.example.demo.itheima;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yuliang
 * @Date: 2019/9/22 0:06
 */
public class AESCBC {
	private static final String content="1";
	private static final String key="abcdefgabcdefg12";
	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";//默认的加密算法
	private static final String iv="abcdefgabcdefg12";
	private static final String KEY_ALGORITHM = "AES"; //计算法则

	public static String AES_CBC_Encrypt(byte[]content, byte[] keyBytes, byte[] iv){
		try{
//			KeyGenerator kg = null;
//			kg = KeyGenerator.getInstance(KEY_ALGORITHM);
//			//AES 要求密钥长度为 128
//			kg.init(128, new SecureRandom(keyBytes));
//			//生成一个密钥
//			byte[] secretKey = kg.generateKey().getEncoded();
			SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
			Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE,key, new IvParameterSpec(iv));
			byte[]result=cipher.doFinal(content);
			return new String(Base64.encodeBase64String(result)); //通过Base64转码返回
		}catch (Exception e) {
			System.out.println("exception:"+e.toString());
		}
		return null;
	}
	public static String AES_CBC_Decrypt(byte[]content, byte[] keyBytes, byte[] iv) {

		try {
//			KeyGenerator kg = null;
//			kg = KeyGenerator.getInstance(KEY_ALGORITHM);
//			//AES 要求密钥长度为 128
//			kg.init(128, new SecureRandom(keyBytes));
//			//生成一个密钥
//			byte[] secretKey = kg.generateKey().getEncoded();
			SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
			byte[] result = cipher.doFinal(content);
			return new String(result, "utf-8"); //通过Base64转码返回
		} catch (Exception e) {
			// TODO Auto-generated catchblock
			System.out.println("exception:" + e.toString());
		}
		return null;
	}
	public static void main(String[] args) throws UnsupportedEncodingException {
		Map<Object, Object> objectObjectMap = new HashMap<>();
		objectObjectMap.put("key", content);
		String content = JSON.toJSONString(objectObjectMap);
		System.out.println("明文" + content);
		String encrypt=AES_CBC_Encrypt(content.getBytes("utf-8"), key.getBytes("utf-8"), iv.getBytes("utf-8"));
		System.out.println(encrypt.length()+":加密后：" + encrypt);
		String decrypted=AES_CBC_Decrypt(Base64.decodeBase64(encrypt), key.getBytes("utf-8"), iv.getBytes("utf-8"));
		System.out.println(":解密后：" + decrypted);
	}
}
