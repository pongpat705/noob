package com.app.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.AppBean;
import com.app.ResponseBean;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AppRestController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private static final String key = "aesEncryptionKey";
	private static final String initVector = "encryptionIntVec";

	private <T> T transformStringToObject(String str, Class<T> clazz)
			throws InstantiationException, IllegalAccessException {
		log.info("######################## transformStringToObject ########################");
		log.info("source {}", str);
		log.info("to clazz {}", clazz.getName());
		T object = clazz.newInstance();

		try {
			ObjectMapper om = new ObjectMapper();

			object = (T) om.readValue(str, clazz);

		} catch (JsonParseException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (JsonMappingException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}

		return object;
	}
	
	private <T> String objectToJson(T object){
		String result = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			result = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}

	private String decrypt(String src) {
		log.info("######################## decrypt ########################");
		log.info("source {}", src);
		String result = new String();
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(Base64.decodeBase64(src));

			return new String(original);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = src;
		log.info("result {}", result);

		return result;
	}

	public String encrypt(String value) {
		log.info("######################## encrypt ########################");
		log.info("source {}", value);
		
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());
			return Base64.encodeBase64String(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@PostMapping("/service1")
	public ResponseBean<AppBean> service1(@RequestBody String str)
			throws InterruptedException, InstantiationException, IllegalAccessException {
		
		String encrypted = decrypt(str);
		AppBean appBean = transformStringToObject(encrypted, AppBean.class);

		ResponseBean<AppBean> result = new ResponseBean<>();
		if (!"1350100204282".equals(appBean.getNatId())) {
			result.setStatus("0000");
		} else {
			result.setStatus("9999");
		}

//		Thread.sleep(2000);

		result.setData(appBean);

		log.info(result.toString());

		return result;
	}
	
	@PostMapping("/service2")
	public String service2()
			throws InterruptedException, InstantiationException, IllegalAccessException {
		AppBean appBean = new AppBean();
		appBean.setMobileNo("0895264086");
		appBean.setNatId("1350100204282");
		
		String xString = objectToJson(appBean);

		String encryptString = encrypt(xString);

		return encryptString;
	}

}
