/**   
* @Title: CrossLanguageEncryptTest.java 
* @Package com.tyyd.ywpt.biz.encrypt 
* @Description:  
* @author wangyu   
* @date 2014-6-30 上午9:54:37 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.biz.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

/**
 * @author wangyu
 *
 */
public class CrossLanguageEncryptTest {

	
	@Test
    public void testCrossLanguageEncrypt() throws Exception{
        System.out.println(encrypt());
        System.out.println(desEncrypt());
    }
    
    public static String encrypt() throws Exception {
        try {
            String data = "123456789oiifafafncononmvaop=-fa";
            String key = "!@#$%^&*()098765";
            String iv = "!@#$%^&*()098765";
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
             
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
            return new sun.misc.BASE64Encoder().encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String desEncrypt() throws Exception {
        try
        {
            String data = "mzamJr6gbgm9ML/muLVCi375uZdfbQ7X+8vHNEUhIFI=";
            String key = "!@#$%^&*()098765";
            String iv = "!@#$%^&*()098765";
             
            byte[] encrypted1 = new sun.misc.BASE64Decoder().decodeBuffer(data);
             
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
             
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        
}
    
    
}
