package iExemplar_API;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.MediaType;


import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import Utility.AlgrothimImpl;
import Utility.PropertyUtil;

import com.fasterxml.jackson.core.JsonGenerationException;
//import com.owler.api.util.SignatureUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author Arun
 *
 */

public class eCardShare {
	
	static String mobileNumber = null;
	static String deviceToken = null;
	public static String oAuthToken = null;
	public static String userID=null;
	public static String udid=null;
	static String EncryptedMsg = "AwHiNSU2EdxbkoI6jp+g9g7ISrVbFKp8zLYPJbX+P5aelOpjSptmy5REGTa1u9CjXaWQ2fGaBcnIUFr93VXnrrjJU5H57BdMQJPUtVe+KUs2fyac20CMB5Hf1ZxO3uCHJmOSJ6QZKy8TrP0qduU30kREvLM1vE7IjkbMqr/inQfRv8vNjSWriQZQG1XJI6e5redBSQYmq5brdwX69C/EBi2aL5vYBu7CPzSpOtoRi/1O0tNEx9k9f8VljHQvY9bcGri+fbptwzfAFTn0GBuIZ0ExDxh5rn+fkCID1/fRJOa16DSVFB7tCAFkaUcsWxQ6uIAEMDRkh/K2kmYPIfJfZ10El0GQVO/9rannpxM1qnr8ttlx1n01wiYFgpK19gRzkEpI7Ca2Igh5jVwB0H8IHY49Kfi1O+jRe61xaRoJsB13lgFf18B1abzUXPiQxFilMPT2TXrOyr6gbIBAhiaV5fqtEFBmOxb4Df4Q3zbrcVKA55+X4AOxQAuDScQoC5FxA789eUpOgv7qPvozcb4ORH56pr40wmV68Wgjy+FPdKZReYq+dsc8JF4sHIlyWG/yLKBkjtQwDRSubM2X3yVg5Oi+GylsB2H0BnEQrvpYaSK/CjgYWpw7kB8qUavJZplvZ0833775+CIujbxaJPnvyprhFYRhj1QpJCF3TCUfoC8qaWPo+X8q+O6RXU6Fpn1KhWMQFJtrcCOMIzzI+9lnOxALJxUqYyE/ltTGaoCoTo2lipVyBV6QN9LWOdw35jF/56OIJhAOEDNNvwmOeznLpf7Ds0+um4671zjIB4mLGroTTsFI7rOEhypVkSiDNZFf+X/3PpJ/e1AxIfl2y/icfB2v1LGnDVD80c4/8Yta27dWdb9RXhSyCUXEdXLXNd+6usYHXX4Gy/wZTNx1m+rp7Pn5U23HN/wqPx0rXM9gVh4wpq/ow9ZkcsqsBk5S3IZWw/Gm93mc3SQYbcToYGuu2ydC";
	
	public static ClientResponse registrationAPI(Client client,String username, String password, String fName,
			String Lname, String mobNumber, String deviceType, String devToken, String oldUname, String forceRegistration,
			String claimPhoneNumber) throws Exception{
	    	
			mobileNumber=mobNumber;
			deviceToken=devToken;
		 	String Md5password = AlgrothimImpl.Md5SingleValueEncryption(password);
		 	String Md5RegisterToken = AlgrothimImpl.Md5MultiValueEncryption(username, Md5password);
		 	
		 	WebResource service = client.resource(PropertyUtil.getProperty("register_user"));
		 	System.out.println("Service is "+service);
            Map<String,Object> requestMap = new HashMap<String,Object>();
	        
	        requestMap.put("userName", username);
	        requestMap.put("password", Md5password);
	        requestMap.put("firstName", fName);
	        requestMap.put("lastName", Lname);
	        requestMap.put("mobileNumber", mobileNumber);
	        requestMap.put("deviceType", deviceType);
	        requestMap.put("deviceToken", deviceToken);
	        requestMap.put("oldUserName", oldUname);
	       // requestMap.put("forceRegistration", forceRegistration);
	        requestMap.put("claimPhoneNumber", claimPhoneNumber);
	        
	        ObjectWriter jacksonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
	        String json = jacksonWriter.writeValueAsString(requestMap);
	        
	        System.out.println("JSON Object with CompanyName :\n"+ json); 
	            
	        ClientResponse response = null; 
	        try{
	        response = service.header("apikey", "testapikey").header("apisecretkey", "testapisecretkey").header("registrationtoken", Md5RegisterToken).header("Access-Control-Allow-Origin", "*").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
	       // System.out.println("RESPONSE Object :"+response.getEntity(String.class));
	        }
	        catch(Exception ex){
	        	ex.printStackTrace();
	        }
			return response;
	        }

	public static ClientResponse PinVerificationAPI(Client client,String pin,String oauthKey) throws JsonGenerationException, JsonMappingException, IOException {
		
		oAuthToken=oauthKey;
		WebResource service = client.resource(PropertyUtil.getProperty("verify_pin"));
	     
        Map<String,Object> requestMap = new HashMap<String,Object>();
        
        requestMap.put("mobileNumber", mobileNumber);
        requestMap.put("deviceToken", deviceToken);
        requestMap.put("pin",pin);
        ObjectWriter jacksonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = jacksonWriter.writeValueAsString(requestMap);
        
        System.out.println("JSON Object with CompanyName :\n"+ json); 
            
        ClientResponse response = null; 
        try{
        response = service.header("apikey", "testapikey").header("apisecretkey", "testapisecretkey").header("oauthkey", oauthKey).header("Access-Control-Allow-Origin", "*").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
			}
        catch(Exception ex) {
        	ex.printStackTrace();
        }		
		return response;
	}
	public static ClientResponse Resend_Email_API(Client client,String UserID) throws JsonGenerationException, JsonMappingException, IOException {
			
				userID=UserID;
				WebResource service = client.resource(PropertyUtil.getProperty("resend_verification_email"));
			     
		        Map<String,Object> requestMap = new HashMap<String,Object>();
		        
		        requestMap.put("userId",userID);
		        requestMap.put("deviceToken", deviceToken);
		        requestMap.put("mobileNumber", mobileNumber);
		        ObjectWriter jacksonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		        String json = jacksonWriter.writeValueAsString(requestMap);
		        
		        System.out.println("JSON Object with CompanyName :\n"+ json); 
		            
		        ClientResponse response = null; 
		        try{
		        response = service.header("apikey", "testapikey").header("apisecretkey", "testapisecretkey").header("oauthkey", oAuthToken).header("Access-Control-Allow-Origin", "*").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
					}
		        catch(Exception ex) {
		        	ex.printStackTrace();
		        }		
		        return response;
	}
	
	public static ClientResponse Resend_Mobile_Verification_API(Client client) throws JsonGenerationException, JsonMappingException, IOException {
		
				WebResource service = client.resource(PropertyUtil.getProperty("Resend_Mobile_Verification"));
			     
		        Map<String,Object> requestMap = new HashMap<String,Object>();
		        
		        requestMap.put("mobileNumber", mobileNumber);
		        requestMap.put("userId", userID);
		        requestMap.put("deviceToken", deviceToken);
		        ObjectWriter jacksonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		        String json = jacksonWriter.writeValueAsString(requestMap);
		        
		        System.out.println("JSON Object with CompanyName :\n"+ json); 
		            
		        ClientResponse response = null; 
		        try{
		        response = service.header("apikey", "testapikey").header("apisecretkey", "testapisecretkey").header("oauthkey", oAuthToken).header("Access-Control-Allow-Origin", "*").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
					}
		        catch(Exception ex) {
		        	ex.printStackTrace();
		        }		
		        return response;
	}
	
	public static ClientResponse Symmetric_Key_Request_API(Client client,String uDid) throws JsonGenerationException, JsonMappingException, IOException {
			
				udid = uDid;
				WebResource service = client.resource(PropertyUtil.getProperty("key"));
			     
		        Map<String,Object> requestMap = new HashMap<String,Object>();
		        
		        requestMap.put("mobileNumber", mobileNumber);
		        requestMap.put("udid", udid);
		        requestMap.put("deviceToken", deviceToken);
		        requestMap.put("sessionKey", "d6c972cfc157c4641fd4405211891a67");
		        ObjectWriter jacksonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		        String json = jacksonWriter.writeValueAsString(requestMap);
		        
		        System.out.println("JSON Object with CompanyName :\n"+ json); 
		            
		        ClientResponse response = null; 
		        try{
		        response = service.header("apikey", "testapikey").header("apisecretkey", "testapisecretkey").header("oauthkey", oAuthToken).header("Access-Control-Allow-Origin", "*").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
					}
		        catch(Exception ex) {
		        	ex.printStackTrace();
		        }		
		        return response;
	}
	
	public static ClientResponse Sharing_Cads_API(Client client) throws JsonGenerationException, JsonMappingException, IOException {
		
				WebResource service = client.resource(PropertyUtil.getProperty("cards"));
				
				Map<String,Object> requestMap = new HashMap<String,Object>();
		        requestMap.put("userId", userID);
		        requestMap.put("message", EncryptedMsg);
		        requestMap.put("deviceToken",deviceToken);
		       
		        ObjectWriter jacksonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		        String json = jacksonWriter.writeValueAsString(requestMap);
		        
		        System.out.println("JSON Object with CompanyName :\n"+ json); 
		        ClientResponse response = null; 
		        try{
		        response = service.header("apikey", "testapikey").header("apisecretkey", "testapisecretkey").header("oauthkey", oAuthToken).header("Access-Control-Allow-Origin", "*").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,json);
					}
		        catch(Exception ex) {
		        	ex.printStackTrace();
		        }		
		        return response;
	}
	
	public static ClientResponse Get_My_Notification_List_API(Client client) throws JsonGenerationException, JsonMappingException, IOException {
				
				String url = "notifications?deviceToken="+deviceToken+"&offset=1&limit=12";
				WebResource service = client.resource(url);
			    ClientResponse response = null; 
		        try{
		        response = service.header("apikey", "testapikey").header("apisecretkey", "testapisecretkey").header("oauthkey", oAuthToken).header("Access-Control-Allow-Origin", "*").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
					}
		        catch(Exception ex) {
		        	ex.printStackTrace();
		        }		
		        return response;
	}
	
	public static ClientResponse Email_Verification_Api(Client client,
			String uri) throws JsonGenerationException, JsonMappingException, IOException {
		
				WebResource service = client.resource("http://www.iexemplar.com/website/ecs/v3/"+uri+oAuthToken);
			    ClientResponse response = null; 
		        try{
		        response = service.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
					}
		        catch(Exception ex) {
		        	ex.printStackTrace();
		        }		
		        return response;
	}
	
	public static ClientResponse View_Card_Detail_API(Client client,
			String deviceToken, String oauthKey) throws JsonGenerationException, JsonMappingException, IOException {
				
				String url= "cards?cardId=14&deviceToken="+deviceToken;
				WebResource service = client.resource(url);
			    ClientResponse response = null; 
		        try{
		        response = service.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
					}
		        catch(Exception ex) {
		        	ex.printStackTrace();
		        }		
		        return response;
	}
	
}
    
