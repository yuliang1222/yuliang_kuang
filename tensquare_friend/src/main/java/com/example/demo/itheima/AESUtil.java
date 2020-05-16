/**
 * ClassName:AESUtil
 * Author:机械革命
 * Date:2019/9/2120:07
 * Description:TODO
 */
package com.example.demo.itheima;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: yuliang
 * @Date: 2019/9/21 20:07
 */
@Slf4j
public class AESUtil {
	private static final String KEY = "abcdefgabcdefg12";
	private static final String KEY_ALGORITHM = "AES"; //计算法则
	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//默认的加密算法
	private static SecretKeySpec secretKey = getSecretKey();
	private static SecretKeySpec secretKey1 = getSecretKey1();

	/**
	 * AES 加密操作
	 *
	 * @param content 待加密内容
	 * @return 返回Base64转码后的加密数据
	 */
	public static String encrypt(String content) {
		try {
			SecretKeySpec key = new SecretKeySpec(KEY.getBytes("utf-8"), "AES");
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);//创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			// 初始化为加密模式的密码器
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(byteContent); //加密
			return Base64.encodeBase64String(result); //通过Base64转码返回
		} catch (Exception ex) {
			Logger.getLogger(AESUtil.class.getSimpleName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	/**
	 * AES 解密操作
	 *
	 * @param content
	 * @return
	 */
	public static String decrypt(String content) {
		try {
			SecretKeySpec key = new SecretKeySpec(KEY.getBytes("utf-8"), "AES");
			// 实例化
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			// 使用密钥初始化,设置解密模式
			cipher.init(Cipher.DECRYPT_MODE, key);
			// 执行操作
			byte[] result = cipher.doFinal(Base64.decodeBase64(content));
			return new String(result, "utf-8");
		} catch (Exception ex) {
			Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	/**
	 * 生成加密秘钥
	 *
	 * @return
	 */
	private static SecretKeySpec getSecretKey() {
		//返回生成指定算法密钥生成器的 KeyGenerator 对象
		KeyGenerator kg = null;
		try {
			kg = KeyGenerator.getInstance(KEY_ALGORITHM);
			//AES 要求密钥长度为 128
			kg.init(128, new SecureRandom(KEY.getBytes()));
			//生成一个密钥
			byte[] secretKey = kg.generateKey().getEncoded();
			SecretKeySpec keySpec = new SecretKeySpec(secretKey, "AES");
			return keySpec;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	private static SecretKeySpec getSecretKey1() {
		//返回生成指定算法密钥生成器的 KeyGenerator 对象
		KeyGenerator kg = null;
		try {
			kg = KeyGenerator.getInstance(KEY_ALGORITHM);
			//AES 要求密钥长度为 128
			kg.init(128, new SecureRandom(KEY.getBytes()));
			//生成一个密钥
			byte[] secretKey = kg.generateKey().getEncoded();
			SecretKeySpec keySpec = new SecretKeySpec(secretKey, "AES");
			return keySpec;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String content = "1";
		Map<Object, Object> objectObjectMap = new HashMap<>();
		objectObjectMap.put("key", content);
		String string = JSON.toJSONString(objectObjectMap);
//		String string = "{'key':'1'}";
		System.out.println("加密前：" + string);
		String encrypt = encrypt(string);
		System.out.println(encrypt.length()+":加密后：" + encrypt);
//		encrypt.replaceAll("#","/");
		String decrypt = decrypt("9qSQtjFCJnQb10Ev3/Z63Q==");
		System.out.println("解密后：" + decrypt);
	}
}
