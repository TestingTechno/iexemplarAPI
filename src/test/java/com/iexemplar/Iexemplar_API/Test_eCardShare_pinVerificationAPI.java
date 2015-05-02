package com.iexemplar.Iexemplar_API;

import static org.testng.Assert.assertTrue;
import iExemplar_API.eCardShare;

import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Utility.AlgrothimImpl;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class Test_eCardShare_pinVerificationAPI extends AlgrothimImpl {
	
	
	 String EXP_FAIL_STATUS = "400";
	  static ClientConfig config;
	  static Client client;
	  eCardShareTest eCardShareTest;
	
	 @BeforeClass
	 public static void beforeClass() throws UnknownHostException {
	      config = new DefaultClientConfig();
	      client = Client.create(config);
	  
	 }
	
	 @Test(dataProvider="inputData",dependsOnMethods={"testSucess_registrationAPI"},alwaysRun=true)
	 public void testFailure_PinVerificationAPI(String pin, String oAuthKey) throws Exception
	 {
		System.out.println(" \n\n ================================>> testFailure_PinVerificationAPI <<===================================== ");
		 ClientResponse response= eCardShare.PinVerificationAPI(client,pin,oAuthKey);
		 String resp = response.getEntity(String.class);

			 HashMap<String, Serializable> surveyMap = null;
			 ArrayList<HashMap<String, Serializable>> addSurveyMap = null;
			 ArrayList<HashMap<String, Serializable>> dataMap = null;
			
			 
			 ObjectMapper mapper = new ObjectMapper();
			 try {
				 surveyMap = new ObjectMapper().readValue(resp, HashMap.class);
				 addSurveyMap = (ArrayList<HashMap<String, Serializable>>) surveyMap.get("message");
				 }
			 	catch (JsonParseException e) {	e.printStackTrace(); } 
			 	catch (JsonMappingException e) { e.printStackTrace(); } 
			 	catch (IOException e) { e.printStackTrace(); }

			 String API_response_code = (String) surveyMap.get("responseCode").toString();
			 System.out.println("Response CODE:"+API_response_code );
			
			 String resp_response_msg = (String) surveyMap.get("responseMessage").toString();
			 System.out.println("Response Message:"+resp_response_msg );
			 
			 String resp_msg_detail = (String) addSurveyMap.get(0).get("detail").toString();
			 System.out.println("Response DataMap Detail :"+resp_msg_detail.toString());
			 
				 
			 assertTrue((API_response_code.equalsIgnoreCase(EXP_FAIL_STATUS)) && 
			  	(resp_response_msg.contains("Failure")) && 
			  (resp_msg_detail.contains("Invalid Pin Verification")));
		
	 }

}
