package Utility;

import java.io.IOException;
import java.lang.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.testng.annotations.DataProvider;



public class AlgrothimImpl {
	
	public static enum Mode {
	    ALPHA, ALPHANUMERIC, NUMERIC 
	}
	static String Email = "EcardMobileAPI";
	
	
	public static String Md5SingleValueEncryption(String TextToEncrypt) {
		
		if (!TextToEncrypt.equals(null))
		{
			String md5Hex = DigestUtils.md5Hex (TextToEncrypt);
			return md5Hex;
		}
		else { return null;}
	}
	
		public static String Md5MultiValueEncryption(String TextToEncrypt, String TextToEncrypt1) {
			
			if (!(TextToEncrypt.equals(null)) && (!TextToEncrypt.equals(null)))
			{
				String md5Hex = TextToEncrypt.concat(TextToEncrypt1);
				md5Hex = DigestUtils.md5Hex (md5Hex);
				return md5Hex;
			}
			else{
			return null;
			}
		}
		
		
		@DataProvider(name = "inputData")
		public Object[][] getInputData(Method testMethod) {
			URL url = null;
			url = ClassLoader.getSystemResource(PropertyUtil
						.getProperty("excelFilePath_qe"));
				System.out.println("---reading excelFilePath_qe for qe.owler.com");
			
			Object[][] inPut;
			
				inPut = DataProviderUtil.getTableArray(
						url.getFile().replaceFirst("/", ""), this.getClass()
								.getSimpleName(), testMethod.getName());
		
			return inPut;
		}

		public static String generateRandomString(int length, Mode mode) throws Exception {

			StringBuffer buffer = new StringBuffer();
			String characters = "";

			switch(mode){
			
			case ALPHA:
				characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
				break;
			
			case ALPHANUMERIC:
				characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
				break;
		
			case NUMERIC:
				characters = "1234567890";
			    break;
			}
			
			int charactersLength = characters.length();

			for (int i = 0; i < length; i++) {
				double index = Math.random() * charactersLength;
				buffer.append(characters.charAt((int) index));
			}
			return buffer.toString();
		}
		}



