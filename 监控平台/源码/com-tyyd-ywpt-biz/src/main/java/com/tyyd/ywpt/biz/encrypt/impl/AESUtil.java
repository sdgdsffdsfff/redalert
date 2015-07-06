/**   
 * @Title: AESUtil.java 
 * @Package com.tyyd.ywpt.biz.encrypt.impl 
 * @Description:  
 * @author wangyu   
 * @date 2014-6-27 下午4:00:51 
 * @CopyRight 天翼阅读
 * @version V1.0   
 */
package com.tyyd.ywpt.biz.encrypt.impl;

import it.sauronsoftware.base64.Base64;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

/**
 * @author wangyu
 * 
 */
public class AESUtil {

	private String encryptKey;

	private static final int encrypt_length = 128;

	private static final String charset = "utf-8";
	
	 /**
	  * 加解密算法/工作模式/填充方式
	  * AES/ECB/PKCS5Padding
	  */
	private static final String cipher_algorithm = "AES/CBC/NoPadding";
	
	/**
	  * 密钥算法
	  */
	public static final String aes_key_algorithm = "AES";

	private Key secretKey;

	/**
	 * @return the secretKey
	 */
	public Key getSecretKey() {
		if (secretKey != null) {
			return secretKey;
		}
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(aes_key_algorithm);
			kgen.init(encrypt_length, new SecureRandom(encryptKey.getBytes()));
			this.secretKey = kgen.generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return secretKey;
	}

	/**
	 * @return the encryptKey
	 */
	public String getEncryptKey() {
		return encryptKey;
	}

	/**
	 * @param encryptKey
	 *            the encryptKey to set
	 */
	public void setEncryptKey(String encryptKey) {
		this.encryptKey = encryptKey;
	}

	/**
	 * AES/ECB/PKCS5Padding
	 * 
	 * @param byteS
	 * @return
	 */
	private byte[] encryptByte(byte[] byteS) {
		byte[] byteFina = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance(cipher_algorithm);
			cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing SqlMap class. Cause: " + e);
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 解密以 byte[] 密文输入 , 以 byte[] 明文输出
	 * 
	 * @param byteD
	 * @return
	 */
	private byte[] decryptByte(byte[] byteD) {
		Cipher cipher;
		byte[] byteFina = null;
		try {
			cipher = Cipher.getInstance(cipher_algorithm);
			cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing SqlMap class. Cause: " + e);
		} finally {
			cipher = null;
		}
		return byteFina;
	}


	@SuppressWarnings("static-access")
	public String decryptStr(String strMi) {
		Base64 base64en = new Base64();
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = "";
		try {
			byteMi = base64en.decode(strMi.getBytes(charset));
			byteMing = this.decryptByte(byteMi);
			strMing = new String(byteMing, charset);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing SqlMap class. Cause: " + e);
		} finally {
			byteMing = null;
			byteMi = null;
		}
		return strMing;
	}

	@SuppressWarnings("static-access")
	public String encryptStr(String strMing) {
		byte[] byteMi = null;
		byte[] byteMing = null;
		byte[] baseRes = null;
		String strMi = "";
		Base64 base64en = new Base64();
		try {
			byteMing = strMing.getBytes(charset);
			byteMi = encryptByte(byteMing);
			baseRes = base64en.encode(byteMi);
			strMi = new String(baseRes, charset);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing SqlMap class. Cause: " + e);
		} finally {
			base64en = null;
			byteMing = null;
			byteMi = null;
		}
		return strMi;
	}

	public static void main(String[] args) {
		AESUtil obj = new AESUtil();
		String content = "123456789oiifafafncononmvaop=-fa";
		String encryptKey = "!@#$%^&*()098765";
		obj.setEncryptKey(encryptKey);

		
		
		String result = obj.encryptStr(content);
		System.out.println(result);
		
		
		String org = obj.decryptStr(result);
		System.out.println(org);
		
	}
}
