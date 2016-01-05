package com.vrv.cems.service.block.security.impl;



import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;




import com.vrv.cems.service.block.security.IDissymmetricSecurityService;
import com.vrv.cems.service.block.security.ISymmetricSecurityService;
/**
 * RSA非对称加解密算法
 * @author guodong
 *
 */
public class RsaSecurityService implements IDissymmetricSecurityService {
	public final static String KEY_ALGORITHM = "RSA";
	//RSA最大加密明文大小
	private static final int MAX_ENCRYPT_BLOCK = 214;
	//RSA最大解密密文大小
	private static final int MAX_DECRYPT_BLOCK = 256;
	
	public byte[] decryptByPrivateKey(byte[] data, byte[] key) throws Exception {
		//取得私钥
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
		//生成私钥
		PrivateKey privateKey = factory.generatePrivate(pkcs8EncodedKeySpec);
		//对数据解密
		Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
	}

	public byte[] decryptByPublicKey(byte[] data, byte[] key) throws Exception {
		//取得公钥
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(key);
		KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
		//生成公钥
		PublicKey publicKey = factory.generatePublic(x509EncodedKeySpec);
		//对数据解密
		Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
	}

	public byte[] encryptByPrivateKey(byte[] data, byte[] key) throws Exception {
		//取得私钥
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
		//生成私钥
		PrivateKey privateKey = factory.generatePrivate(pkcs8EncodedKeySpec);
		//对数据加密
		Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
	}

	public byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception {
		//取得公钥
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(key);
		KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
		//生成公钥
		PublicKey publicKey = factory.generatePublic(x509EncodedKeySpec);
		//对数据加密
		Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
	}

	
	
}
