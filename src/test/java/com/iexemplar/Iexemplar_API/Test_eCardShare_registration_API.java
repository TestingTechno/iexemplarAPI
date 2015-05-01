package com.iexemplar.Iexemplar_API;

import static org.testng.Assert.assertTrue;
import iExemplar_API.eCardShare;

import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import Utility.DataProviderUtil;
import Utility.AlgrothimImpl;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class Test_eCardShare_registration_API  extends AlgrothimImpl{
	
	  String EXP_FAIL_STATUS = "400";
	  static ClientConfig config;
	  static Client client;
	
	 @BeforeClass
	 public static void beforeClass() throws UnknownHostException {
	      config = new DefaultClientConfig();
	      client = Client.create(config);
	 
	 }
	
	 
	
	@Test(dataProvider="inputData")
	 public void testError_registrationAPI(String userName, String password, String fName, 
			 String Lname, String mobilenumber, String deviceType, String deviceToken, String oldUsername, 
			 String forceRegistration, String claimPhoneNumber) throws Exception
	 {
		 
		
		 System.out.println(" \n\n ================================>> testSucess_registrationAPI <<===================================== ");
		 
		  ClientResponse response= eCardShare.registrationAPI(client,userName,password,fName,Lname,mobilenumber,deviceType,deviceToken,oldUsername,forceRegistration,claimPhoneNumber);
		  String resp = response.getEntity(String.class);

			 HashMap<String, Serializable> surveyMap = null;
			 ArrayList<HashMap<String, Serializable>> addSurveyMap = null;
			 ArrayList<HashMap<String, Serializable>> dataMap = null;
			
			 
			 ObjectMapper mapper = new ObjectMapper();
			 try {
				 surveyMap = new ObjectMapper().readValue(resp, HashMap.class);
				 addSurveyMap = (ArrayList<HashMap<String, Serializable>>) surveyMap.get("message");
				 dataMap = (ArrayList<HashMap<String, Serializable>>) surveyMap.get("data");
				
			 }
			 catch (JsonParseException e) {	e.printStackTrace(); } 
			 catch (JsonMappingException e) { e.printStackTrace(); } 
			 catch (IOException e) { e.printStackTrace(); }

			 String API_response_code = (String) surveyMap.get("responseCode").toString();
			 String resp_response_msg = (String) surveyMap.get("responseMessage").toString();
			 String resp_msg_detail = (String) addSurveyMap.get(0).get("detail").toString();
			 assertTrue(API_response_code.equalsIgnoreCase(EXP_FAIL_STATUS) &&
					 (resp_response_msg.equalsIgnoreCase("Error")) &&
					 (resp_msg_detail.equalsIgnoreCase("Invalid input details")));
			
			
	 }

}
