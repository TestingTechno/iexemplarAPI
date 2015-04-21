package com.iexemplar.Iexemplar_API;

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
	
	static String EncryptedMsg = "AwHiNSU2EdxbkoI6jp+g9g7ISrVbFKp8zLYPJbX+P5aelOpjSptmy5REGTa1u9CjXaWQ2fGaBcnIUFr93VXnrrjJU5H57BdMQJPUtVe+KUs2fyac20CMB5Hf1ZxO3uCHJmOSJ6QZKy8TrP0qduU30kREvLM1vE7IjkbMqr/inQfRv8vNjSWriQZQG1XJI6e5redBSQYmq5brdwX69C/EBi2aL5vYBu7CPzSpOtoRi/1O0tNEx9k9f8VljHQvY9bcGri+fbptwzfAFTn0GBuIZ0ExDxh5rn+fkCID1/fRJOa16DSVFB7tCAFkaUcsWxQ6uIAEMDRkh/K2kmYPIfJfZ10El0GQVO/9rannpxM1qnr8ttlx1n01wiYFgpK19gRzkEpI7Ca2Igh5jVwB0H8IHY49Kfi1O+jRe61xaRoJsB13lgFf18B1abzUXPiQxFilMPT2TXrOyr6gbIBAhiaV5fqtEFBmOxb4Df4Q3zbrcVKA55+X4AOxQAuDScQoC5FxA789eUpOgv7qPvozcb4ORH56pr40wmV68Wgjy+FPdKZReYq+dsc8JF4sHIlyWG/yLKBkjtQwDRSubM2X3yVg5Oi+GylsB2H0BnEQrvpYaSK/CjgYWpw7kB8qUavJZplvZ0833775+CIujbxaJPnvyprhFYRhj1QpJCF3TCUfoC8qaWPo+X8q+O6RXU6Fpn1KhWMQFJtrcCOMIzzI+9lnOxALJxUqYyE/ltTGaoCoTo2lipVyBV6QN9LWOdw35jF/56OIJhAOEDNNvwmOeznLpf7Ds0+um4671zjIB4mLGroTTsFI7rOEhypVkSiDNZFf+X/3PpJ/e1AxIfl2y/icfB2v1LGnDVD80c4/8Yta27dWdb9RXhSyCUXEdXLXNd+6usYHXX4Gy/wZTNx1m+rp7Pn5U23HN/wqPx0rXM9gVh4wpq/ow9ZkcsqsBk5S3IZWw/Gm93mc3SQYbcToYGuu2ydC";
	 public static ClientResponse registrationAPI(Client client,String uri, String RegisOAuthToken) throws Exception{
	    	
		 System.out.println("Iexamplar API");
		 WebResource service = client.resource("http://www.iexemplar.com/website/ecs/v3/"+uri);
     
	        Map<String,Object> requestMap = new HashMap<String,Object>();
	        
	        requestMap.put("userName", "arun@iexemplar.com");
	        requestMap.put("password", "984fhg10a8a5eb516c6bb47652968e7");
	        requestMap.put("firstName", "arun");
	        requestMap.put("lastName", "bharath");
	        requestMap.put("mobileNumber", "9003589912");
	        requestMap.put("deviceType", "android");
	        requestMap.put("deviceToken", "9876543211");
	        requestMap.put("oldUserName", "");
	        requestMap.put("forceRegistration", "no");
	        requestMap.put("claimPhoneNumber", "no");
	        
	        ObjectWriter jacksonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
	        String json = jacksonWriter.writeValueAsString(requestMap);
	        
	        System.out.println("JSON Object with CompanyName :\n"+ json); 
	            
	        ClientResponse response = null; 
	        try{
	        response = service.header("apikey", "testapikey").header("apisecretkey", "testapisecretkey").header("registrationtoken", RegisOAuthToken).header("Access-Control-Allow-Origin", "*").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
	       // System.out.println("RESPONSE Object :"+response.getEntity(String.class));
	        }
	        catch(Exception ex){
	        	ex.printStackTrace();
	        }
			return response;
	        }

	public static ClientResponse PinVerificationAPI(Client client,
			String uri, String oauthKey) throws JsonGenerationException, JsonMappingException, IOException {
		
		WebResource service = client.resource("http://www.iexemplar.com/website/ecs/v3/"+uri);
	     
        Map<String,Object> requestMap = new HashMap<String,Object>();
        
        requestMap.put("mobileNumber", "9876543210");
        requestMap.put("deviceToken", "1234567890");
        requestMap.put("pin", "fived");
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
	public static ClientResponse Resend_Email_API(Client client,
			String uri, String oauthKey) throws JsonGenerationException, JsonMappingException, IOException {
		
				WebResource service = client.resource("http://www.iexemplar.com/website/ecs/v3/"+uri);
			     
		        Map<String,Object> requestMap = new HashMap<String,Object>();
		        
		        requestMap.put("userId", "0188e8b8b014829e2fa0f430f0a95961");
		        requestMap.put("deviceToken", "1234567890");
		        requestMap.put("mobileNumber", "919524804883");
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
	
	public static ClientResponse Resend_Mobile_Verification_API(Client client,
			String uri, String oauthKey) throws JsonGenerationException, JsonMappingException, IOException {
		
				WebResource service = client.resource("http://www.iexemplar.com/website/ecs/v3/"+uri);
			     
		        Map<String,Object> requestMap = new HashMap<String,Object>();
		        
		        requestMap.put("mobileNumber", "919524804883");
		        requestMap.put("userId", "0188e8b8b014829e2fa0f430f0a95961");
		        requestMap.put("deviceToken", "1234567890");
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
	
	public static ClientResponse Symmetric_Key_Request_API(Client client,
			String uri, String oauthKey) throws JsonGenerationException, JsonMappingException, IOException {
		
				WebResource service = client.resource("http://www.iexemplar.com/website/ecs/v3/"+uri);
			     
		        Map<String,Object> requestMap = new HashMap<String,Object>();
		        
		        requestMap.put("mobileNumber", "919524804883");
		        requestMap.put("udid", "6c972cfc157c4641fd4405211891a67");
		        requestMap.put("deviceToken", "1234567890");
		        requestMap.put("sessionKey", "d6c972cfc157c4641fd4405211891a67");
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
	
	public static ClientResponse Sharing_Cads_API(Client client,
			String uri, String oauthKey) throws JsonGenerationException, JsonMappingException, IOException {
		
				WebResource service = client.resource("http://www.iexemplar.com/website/ecs/v3/"+uri);
				
				Map<String,Object> requestMap = new HashMap<String,Object>();
		        requestMap.put("userId", "0188e8b8b014829e2fa0f430f0a95961");
		        requestMap.put("message", EncryptedMsg);
		        requestMap.put("deviceToken", "1234567890");
		       
		        ObjectWriter jacksonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		        String json = jacksonWriter.writeValueAsString(requestMap);
		        
		        System.out.println("JSON Object with CompanyName :\n"+ json); 
		        ClientResponse response = null; 
		        try{
		        response = service.header("apikey", "testapikey").header("apisecretkey", "testapisecretkey").header("oauthkey", oauthKey).header("Access-Control-Allow-Origin", "*").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,json);
					}
		        catch(Exception ex) {
		        	ex.printStackTrace();
		        }		
		        return response;
	}
	
	public static ClientResponse Get_My_Notification_List_API(Client client,
			String uri, String oauthKey) throws JsonGenerationException, JsonMappingException, IOException {
		
				WebResource service = client.resource("http://www.iexemplar.com/website/ecs/v3/"+uri);
			    ClientResponse response = null; 
		        try{
		        response = service.header("apikey", "testapikey").header("apisecretkey", "testapisecretkey").header("oauthkey", oauthKey).header("Access-Control-Allow-Origin", "*").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
					}
		        catch(Exception ex) {
		        	ex.printStackTrace();
		        }		
		        return response;
	}
	
	public static ClientResponse Email_Verification_Api(Client client,
			String uri, String oauthKey) throws JsonGenerationException, JsonMappingException, IOException {
		
				WebResource service = client.resource("http://www.iexemplar.com/website/ecs/v3/"+uri+oauthKey);
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
			String uri, String oauthKey) throws JsonGenerationException, JsonMappingException, IOException {
		
				WebResource service = client.resource("http://www.iexemplar.com/website/ecs/v3/"+uri);
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
    
