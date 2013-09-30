/**
 * 
 */
package com.w.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RSASignature{
	
	/**

	* 解密

	* @param content 密文
	* 
	* @param key 商户私钥

	* @return 解密后的字符串

	*/
	public static String decrypt(String content, String key) throws Exception {
        PrivateKey prikey = getPrivateKey(key);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prikey);

        InputStream ins = new ByteArrayInputStream(Base64.decode(content));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        //rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
        byte[] buf = new byte[128];
        int bufl;

        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }

            writer.write(cipher.doFinal(block));
        }

        return new String(writer.toByteArray(), "utf-8");
    }


	
	/**

	* 得到私钥

	* @param key 密钥字符串（经过base64编码）

	* @throws Exception

	*/

	public static PrivateKey getPrivateKey(String key) throws Exception {

		byte[] keyBytes;
		
		keyBytes = Base64.decode(key);
		
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		
		return privateKey;

	}
	
	public static final String  SIGN_ALGORITHMS = "SHA1WithRSA";
	/**
	* RSA签名
	* @param content 待签名数据
	* @param privateKey 商户私钥
	* @param encode 字符集编码
	* @return 签名值
	*/
	public static String sign(String content, String privateKey,String encode)
	{
		String charset = "utf-8";
		if(encode != null && !"".equals(encode)){
		    charset=encode;
		}
        try 
        {
        	PKCS8EncodedKeySpec priPKCS8 	= new PKCS8EncodedKeySpec( Base64.decode(privateKey) ); 
        	KeyFactory keyf 				= KeyFactory.getInstance("RSA");
        	PrivateKey priKey 				= keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature
                .getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update( content.getBytes(charset) );

            byte[] signed = signature.sign();
            
            return Base64.encode(signed);
        }
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
        
        return null;
    }
	
	/**
	* RSA验签名检查
	* @param content 待签名数据
	* @param sign 签名值
	* @param publicKey 支付宝公钥
	* @param encode 字符集编码
	* @return 布尔值
	*/
	public static boolean doCheck(String content, String sign,
			String publicKey, String encode) throws Exception{
		String charset = "utf-8";
		if (encode != null && !"".equals(encode)) {
			charset = encode;
		}
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		byte[] encodedKey = Base64.decode(publicKey);
		PublicKey pubKey = keyFactory
				.generatePublic(new X509EncodedKeySpec(encodedKey));

		java.security.Signature signature = java.security.Signature
				.getInstance(SIGN_ALGORITHMS);

		signature.initVerify(pubKey);
		signature.update(content.getBytes(charset));

		boolean bverify = signature.verify(Base64.decode(sign));
		return bverify;
	}
	
	 /**  
     * 加密<br>  
     * 用公钥加密  
     *    
     * @param data  
     * @param key  
     * @return  
     * @throws Exception  
     */   
    public static byte[] encrypt(byte[] data, String key)     
            throws Exception {     
        // 对公钥解密     
        byte[] keyBytes = Base64.decode(key);     
  
        // 取得公钥     
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);     
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");     
        Key publicKey = keyFactory.generatePublic(x509KeySpec);     
  
        // 对数据加密     
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());     
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);     
  
        return cipher.doFinal(data);     
    }

    /**
     * 加密<br>  
     * 用公钥加密  
     * @param content
     * @param key
     * @return
     */
    public static String encrypt(String content, String key) {
    	try {
			byte[] data = content.getBytes("utf-8");
			byte[] enData = encrypt(data, key);
			String enContent = Base64.encode(enData);
			return enContent;
		} catch (Exception e) {
			System.err.println("encrypt error");
		}
    	return null;
    }
    
    public static void main(String args[]) {
    	
    	String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOJIN7RpB/X3MzetQS6OWXg8mG6saJ1Z7FhA6YcvGUkJbLzPN1pJjIosYebsniwN6giSeVWxT5pqBhazXH3Y6lUBMxwrpmUTx96JGr5zmroCiAjupunX3YNjpuWE88JF8ZT4mAxzmf9hPdYgGRuir5XDXofq2F+FltThikuAiRBBAgMBAAECgYAY6VhwnSuKMafK1mEZrgQ9j3Y8oPB3ekHhyWusPUwsVURKWQm//97gC1jP4nbbZMDCJtYC0B1yY3++Ggrxs+LAOfgqQVEtpu3EWvLwviPdyJNA0eGVpcKF4a+FxlcD5SQwR8vYNOYz7UAgHC+5j+fipbtLQHCOObYsuRh917KoPQJBAPfT5rICjHAMTQFycFRqU/lPKayOMaqfXs8x/Jhpq2SKeUvn8aCqIA/6LXKKxA95D2O4voWWN8tbaxScwv871v8CQQDpvm8CXvEnUbd4SI0ZUB8y8n99DX0NVrV6wO5ASJT6BihFZXye1d7XEE3ej+Bw2dPFzSLs1zCfto37b8nbBFi/AkEA1c82X+t8+ApUwWPW761QR235uUpwJREb/cvVbjT0Tmh9X3R7bS55PO+NGB4KKoM6OMGhdaT0+/gh+QWJ4iPhRwJAI3hDbi+sFpRe7ADOcI3o/hkwYXCfjlufFG6ceOLKwJoYmFdLLuszcp1Fw8U/gMB9U2HKr/gDp+jS6NnQ3sxtDwJBAOR0y+U9IZE6LqmBY1WSdyAf5Oa81/d7Kt+ZRuUU6R1U2+oTY0lgmQwwiu9Mh7icdiIqq0VcYQkDU4wChOXu0D0=";
    	String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDiSDe0aQf19zM3rUEujll4PJhurGidWexYQOmHLxlJCWy8zzdaSYyKLGHm7J4sDeoIknlVsU+aagYWs1x92OpVATMcK6ZlE8feiRq+c5q6AogI7qbp192DY6blhPPCRfGU+JgMc5n/YT3WIBkboq+Vw16H6thfhZbU4YpLgIkQQQIDAQAB";
    	
    	String content = "name=廖鹏";
    	
    	String enContent = encrypt(content, publicKey);
    	System.out.println(enContent);
    	
    	try {
			System.out.println(decrypt(enContent, privateKey));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
