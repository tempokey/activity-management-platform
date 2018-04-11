package com.edudigital.cloudy.amp.user.service.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.edudigital.cloudy.amp.util.base.util.security.Base64Utils;
import com.edudigital.cloudy.amp.util.base.util.security.RSAUtils;

/**
 * 
 * @author Tempo
 * @date 2017年9月6日 下午2:46:09
 *
 */
@Component
public class EncryptUtils {

	@Value("${amp.user.security.public-key}")
	private String PUBLIC_KEY;

	@Value("${amp.user.security.private-key}")
	private String PRIVATE_KEY;

	public String getPUBLIC_KEY() {
		return PUBLIC_KEY;
	}

	public void setPUBLIC_KEY(String pUBLIC_KEY) {
		PUBLIC_KEY = pUBLIC_KEY;
	}

	public String getPRIVATE_KEY() {
		return PRIVATE_KEY;
	}

	public void setPRIVATE_KEY(String pRIVATE_KEY) {
		PRIVATE_KEY = pRIVATE_KEY;
	}

	public String signSHA1(String plaintext) {
		return SignUtils.signSHA1(plaintext);
	}

	public String encrypt432(String plaintext) {
		String result = "";
		if (StringUtils.isBlank(plaintext)) {
			return result;
		}
		try {
			result = encrypt(plaintext).substring(0, 32);
		} catch (Exception e) {

		}
		return result;
	}

	public String encrypt(String plaintext) throws Exception {
		String result = "";
		if (StringUtils.isBlank(plaintext)) {
			return result;
		}

		InputStream inPublic = new ByteArrayInputStream(PUBLIC_KEY.getBytes());
		PublicKey publicKey = RSAUtils.loadPublicKey(inPublic);
		byte[] encryptByte = null;
		// 添加无限长度
		while (plaintext.length() > 28) {
			String temp = plaintext;
			plaintext = temp.substring(28);
			String b1 = temp.substring(0, 28);
			encryptByte = RSAUtils.encryptData(b1.getBytes(), publicKey);
			result = result + Base64Utils.encode(encryptByte);
		}
		encryptByte = RSAUtils.encryptData(plaintext.getBytes(), publicKey);
		return result + Base64Utils.encode(encryptByte);
	}

	public String decrypt(String ciphertext) throws Exception {
		String result = "";
		InputStream inPrivate = new ByteArrayInputStream(PRIVATE_KEY.getBytes());
		PrivateKey privateKey = RSAUtils.loadPrivateKey(inPrivate);
		byte[] decryptByte = null;
		// 添加无限长度
		while (ciphertext.length() > 172) {
			String temp = ciphertext;
			String b2 = temp.substring(0, 172);
			ciphertext = temp.substring(172);
			decryptByte = RSAUtils.decryptData(Base64Utils.decode(b2), privateKey);
			result = result + new String(decryptByte);
		}
		// 因为RSA加密后的内容经Base64再加密转换了一下，所以先Base64解密回来再给RSA解密
		decryptByte = RSAUtils.decryptData(Base64Utils.decode(ciphertext), privateKey);
		return result = result + new String(decryptByte);

	}
}
