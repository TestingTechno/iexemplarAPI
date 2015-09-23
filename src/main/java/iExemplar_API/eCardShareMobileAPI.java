package iExemplar_API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;


import Utility.AlgrothimImpl;
import Utility.PropertyUtil;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.iexemplar.Iexemplar_API.mix;

/*import com.iexemplar.Iexemplar_API..mix;*/
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author Arun
 *
 */

public class eCardShareMobileAPI {
	
	static String mobileNumber = null;
	static String deviceToken = null;
	public static String oAuthToken = null;
	public static String userID=null;
	public static String udid=null;
	static String deviceType="android";
	static String oldUname="";
	static String claimPhoneNumber="no";
	static String deviceUniqueId;
	
	public static ClientResponse registrationAPI(Client client,String username,String password,String fName,String Lname,String mobNumber) throws Exception{
	    	
			
			mobileNumber=mobNumber;
			deviceToken=mobNumber;
			deviceUniqueId=mobNumber;
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
	        requestMap.put("deviceUniqueId", deviceUniqueId);
	        requestMap.put("claimPhoneNumber", claimPhoneNumber);
	        
	        ObjectWriter jacksonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
	        String json = jacksonWriter.writeValueAsString(requestMap);
	        
	        System.out.println("JSON Object with CompanyName :\n"+ json); 
	            
	        ClientResponse response = null; 
	        try{
	        response = service.header("apikey", "testapikey").header("apisecretkey", "testapisecretkey").header("registrationtoken", Md5RegisterToken).header("Access-Control-Allow-Origin", "*").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
	       
	        }
	        catch(Exception ex){
	        	ex.printStackTrace();
	        }
			return response;
	        }

	public static ClientResponse PinVerificationAPI(Client client,String oauthKey) throws JsonGenerationException, JsonMappingException, IOException {
		
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String Verificationpin;   
        System.out.println("Please Enter the Pin :: ");
        Verificationpin= br.readLine();  
        
		
		oAuthToken=oauthKey;
		WebResource service = client.resource(PropertyUtil.getProperty("verify_pin"));
	     
        Map<String,Object> requestMap = new HashMap<String,Object>();
        
        requestMap.put("mobileNumber", mobileNumber);
        requestMap.put("deviceToken", deviceToken);
        requestMap.put("pin",Verificationpin);
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
	public static ClientResponse Resend_Email_API(Client client,String UserID,String oauthkey) throws JsonGenerationException, JsonMappingException, IOException {
			
				userID=UserID;
				oAuthToken=oauthkey;
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
	
	public static ClientResponse Resend_UDID_API(Client client) throws JsonGenerationException, JsonMappingException, IOException {
		
		
		
		WebResource service = client.resource(PropertyUtil.getProperty("Resend_udid"));
	     
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

	
	
	public static ClientResponse Resend_Mobile_Verification_API(Client client,String userid) throws JsonGenerationException, JsonMappingException, IOException {
		
				WebResource service = client.resource(PropertyUtil.getProperty("Resend_Mobile_Verification"));
			     
		        Map<String,Object> requestMap = new HashMap<String,Object>();
		        userID=userid;
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
				WebResource service = client.resource(PropertyUtil.getProperty("server_key"));
			     
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
	
	public static ClientResponse Sharing_Cads_API(Client client,String msg) throws JsonGenerationException, JsonMappingException, IOException {
		
				WebResource service = client.resource(PropertyUtil.getProperty("cards"));
				
				Map<String,Object> requestMap = new HashMap<String,Object>();
		        requestMap.put("userId", userID);
		        requestMap.put("message", msg);
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
				
				String url = "http://www.iexemplar.com/website/ecs/v3/notifications?deviceToken="+deviceToken+"&offset=1&limit=12";
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
			String uri/*,String token*/) throws JsonGenerationException, JsonMappingException, IOException {
		

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String newOauthTOken;   
        System.out.println("Please Enter the Pin :: ");
        newOauthTOken= br.readLine();  
        
		
		oAuthToken=newOauthTOken;
		
				WebResource service = client.resource("http://208.43.91.140/rest/beta/esharecard/v0.2/"+uri+oAuthToken);
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
			String oauthKey) throws JsonGenerationException, JsonMappingException, IOException {
				
				String url= "http://www.iexemplar.com/website/ecs/v3/cards?cardId=14&deviceToken="+deviceToken;
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
	
	public static ClientResponse gettingMixPanelEvent(Client client) throws Exception, IOException {
		
		 Date date = new Date();
	      long timeMilli = date.getTime();
	      timeMilli=timeMilli+600000;
	      System.out.println("Time in milliseconds using Date class: " + timeMilli);
	      
		String API_KEY = "api_key=02abb5c69b84e501d52a7a361918100f";
		String API_SECRET = "9fc0827b25b20c364334c570a3d5a13f";
		String FROM_DATE = "from_date=2015-06-02";
		String TO_DATE = "to_date=2015-06-03";
		String Event = "event=[\"User Registration\"]";
		String where = "where=\"sds@sdf.com\""+" == "+"properties[\"Email\"]";
		String expire="expire="+timeMilli;
		
		
		
    	System.out.println("===================================================================");
    	WebResource service = client.resource("http://data.mixpanel.com/api/2.0/export/?");
    	
    	System.out.println("Service is "+service);
    	Map<String,Object> requestMap = new HashMap<String,Object>();
        String[] args = {API_KEY,FROM_DATE,TO_DATE,Event,where,expire};
        String[] sigwithqueryParam= mix.signature(args,API_SECRET);
    
       /* 
	 	//JSONObject requestMap = new JSONObject();
        requestMap.put("api_key","02abb5c69b84e501d52a7a361918100f");
        requestMap.put("sig",sigwithqueryParam[0]);
        requestMap.put("event", "[\"User Registration\"]");
        requestMap.put("expire", "1433222734");
        requestMap.put("from_date","2015-05-18" );
        requestMap.put("to_date", "2015-05-19");
        requestMap.put("where", "\"1060212\""+" == "+"properties[\"UserId\"]");
        
        
        ObjectWriter jacksonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = jacksonWriter.writeValueAsString(requestMap);
*/
        String query = mix.encodeUrl(sigwithqueryParam[1]);
        
        System.out.println("JSON Object with CompanyName :\n"+ service+query); 
            
        ClientResponse response = null; 
        try{
        	response = service.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,query);
        	System.out.println("RESPONSE"+response);
			}
        catch(Exception ex) {
        	ex.printStackTrace();
        }
     
		return response;
    }
    
	/*public void getEncodeurl(){
		
	

		String API_KEY = "1234";
		String API_SECRET = "5678";

		query_params = {
		    'api_key': API_KEY,
		    'from_date': '2014-04-01',
		    'to_date': '2014-04-30',
		    'event': 'High Fantasy',
		    'expire': str(int(time.time()) + 600),
		    'format': 'json',
		    }

		s = ''.join('='.join(i) for i in sorted(query_params.items()))
		s += API_SECRET
		query_params['sig'] = hashlib.md5(s).hexdigest()

		ENDPOINT = 'https://mixpanel.com/api/2.0/segmentation/'

		response = requests.get(ENDPOINT, params=query_params)

		data = response.json()

		print data
		
	}
	*/
	
}
    
