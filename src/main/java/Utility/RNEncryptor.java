package Utility;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.Format.Field;

import org.apache.commons.io.IOUtils;
import org.cryptonode.jncryptor.AES256JNCryptor;
import org.cryptonode.jncryptor.AES256JNCryptorInputStream;
import org.cryptonode.jncryptor.CryptorException;
import org.cryptonode.jncryptor.JNCryptor;
import org.webbitserver.helpers.Base64;

public class RNEncryptor {

	
	public String encryption(String EncValue,String EncKey) throws CryptorException
	{
		
		try { 
			java.lang.reflect.Field field = Class.forName("javax.crypto.JceSecurity").
			getDeclaredField("isRestricted");
			field.setAccessible(true);
			field.set(null, java.lang.Boolean.FALSE); 
			} catch (Exception ex) {
			ex.printStackTrace();
			}
				
	    byte[] plaintext = EncValue.getBytes();
	    String password = EncKey;
	    AES256JNCryptor cryptor = new AES256JNCryptor();
	    byte[] ciphertext = cryptor.encryptData(plaintext, password.toCharArray());
	    String encoded = Base64.encode(ciphertext);
	    System.out.println(encoded);
	    
	    return encoded;

	       
	}
	
	public String decryption(String encoded) throws CryptorException
	{
		
		try { 
			java.lang.reflect.Field field = Class.forName("javax.crypto.JceSecurity").
			getDeclaredField("isRestricted");
			field.setAccessible(true);
			field.set(null, java.lang.Boolean.FALSE); 
			} catch (Exception ex) {
			ex.printStackTrace();
			}
		  
	    

	    // Decode data on other side, by processing encoded data
	    String decoded = Base64.decode(encoded).toString();
	    System.out.println("Decoded value is " + new String(decoded));
	    return decoded;
	}
}
