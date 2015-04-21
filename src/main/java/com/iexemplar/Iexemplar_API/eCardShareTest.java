package com.iexemplar.Iexemplar_API;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jongo.marshall.Marshaller;
import org.json.JSONObject;
import org.json.XML;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.iexemplar.Iexemplar_API.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * @author Arun
 *
 */

public class eCardShareTest {
	
	  String EXP_SUCCESS_STATUS = "200";
	  String EXP_FAIL_STATUS = "400";
	  String emailToken="AwGDyPnLGnqUvIqB6/yOq6q9SSkFMpjJOjnmepvkUWRxuTlp2kXB7n7L6S0f3Qitulfbau8SEg0KZfgptYnvkTuXpBNHldsZR30+x0zr1gdIfXRFKIjNZJyD9sibtvXUj2ZZ9d51ZuzXmqc/umhC1zu6gaCK3z853e7rl1twPcdm9Q==";
	
	static ClientConfig config;
    static Client client;
      
	 @BeforeClass
	  public static void beforeClass() throws UnknownHostException {
	      config = new DefaultClientConfig();
	      client = Client.create(config);
	      
	 }
	
	 
	 @Test()
	 public void testSucess_registrationAPI() throws Exception
	 {
		 System.out.println(" \n\n ================================>> testSucess_registrationAPI <<===================================== ");
		 
		  ClientResponse response= eCardShare.registrationAPI(client,"register_user","9a2ebcf03a16e26bd8d2240ee3e141ca");
		  String resp = response.getEntity(String.class);

			 HashMap<String, Serializable> surveyMap = null;
			 ArrayList<HashMap<String, Serializable>> addSurveyMap = null;
//			 HashMap<String, Serializable> dataMap = null;
			 
			 ObjectMapper mapper = new ObjectMapper();
			 try {
				 surveyMap = new ObjectMapper().readValue(resp, HashMap.class);
				 addSurveyMap = (ArrayList<HashMap<String, Serializable>>) surveyMap.get("message");
//				 dataMap = (HashMap<String, Serializable>) surveyMap.get("message");
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
		 assertTrue(  (API_response_code == EXP_SUCCESS_STATUS) && 
  			  	(resp_response_msg.contains("Success")) && 
  			  (resp_msg_detail.contains("User login successful")));
					 
	 }
	 
	 @Test(dependsOnMethods={"testSucess_registrationAPI"},alwaysRun=true)
	 public void testSuccess_PinVerificationAPI() throws Exception
	 {
		 System.out.println(" \n\n ================================>> testSuccess_PinVerificationAPI <<===================================== ");
		 
		  ClientResponse response= eCardShare.PinVerificationAPI(client,"users/verify_pin","f12fec2f637dbbeb49c2646c2b04e65e");
		  String resp = response.getEntity(String.class);

			 HashMap<String, Serializable> surveyMap = null;
			 ArrayList<HashMap<String, Serializable>> addSurveyMap = null;
	 
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
			 
			 assertTrue(  (API_response_code == EXP_SUCCESS_STATUS) && 
			  	(resp_response_msg.contains("Success")) && 
			  (resp_msg_detail.contains("PIN verification is successful")));
		
	 }
	 
	 @Test(dependsOnMethods={"testSuccess_PinVerificationAPI"}, alwaysRun=true)
	 public void testSuccess_Resend_Email_API() throws Exception
	 {
		 System.out.println(" \n\n ================================>> testSuccess_Resend_Email_API <<===================================== ");
		 
		  ClientResponse response= eCardShare.Resend_Email_API(client,"users/resend_verification_email","f12fec2f637dbbeb49c2646c2b04e65e");
		  String resp = response.getEntity(String.class);

			 HashMap<String, Serializable> surveyMap = null;
			 ArrayList<HashMap<String, Serializable>> addSurveyMap = null;
//			 HashMap<String, Serializable> dataMap = null;
			 
			 ObjectMapper mapper = new ObjectMapper();
			 try {
				 surveyMap = new ObjectMapper().readValue(resp, HashMap.class);
				 addSurveyMap = (ArrayList<HashMap<String, Serializable>>) surveyMap.get("message");
//				 dataMap = (HashMap<String, Serializable>) surveyMap.get("message");
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
			 
			 assertTrue(  (API_response_code == EXP_SUCCESS_STATUS) && 
			  	(resp_response_msg.contains("Success")) && 
			  (resp_msg_detail.contains("The email verification link is sent to your email address")));
		
	 }
	 
	 @Test(dependsOnMethods={"testSuccess_Resend_Email_API"},alwaysRun=true)
	 public void testSuccess_Resend_Mobile_Verification_API() throws Exception
	 {
		 System.out.println(" \n\n ================================>> testSuccess_Resend_Mobile_Verification_API <<===================================== ");
		 
		  ClientResponse response= eCardShare.Resend_Mobile_Verification_API(client,"users/resend_pin","f12fec2f637dbbeb49c2646c2b04e65e");
		  String resp = response.getEntity(String.class);

			 HashMap<String, Serializable> surveyMap = null;
			 ArrayList<HashMap<String, Serializable>> addSurveyMap = null;
//			 HashMap<String, Serializable> dataMap = null;
			 
			 ObjectMapper mapper = new ObjectMapper();
			 try {
				 surveyMap = new ObjectMapper().readValue(resp, HashMap.class);
				 addSurveyMap = (ArrayList<HashMap<String, Serializable>>) surveyMap.get("message");
//				 dataMap = (HashMap<String, Serializable>) surveyMap.get("message");
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
			 
			 assertTrue(  (API_response_code == EXP_SUCCESS_STATUS) && 
			  	(resp_response_msg.contains("Success")) && 
			  (resp_msg_detail.contains("Mobile number verification PIN is sent to your registered mobile numbers")));
		
	 }
	 
	 @Test(dependsOnMethods={"testSuccess_Resend_Mobile_Verification_API"},alwaysRun=true)
	 public void testSuccess_Symmetric_Key_Request_API() throws Exception
	 {
		 System.out.println(" \n\n ================================>> testSuccess_Symmetric_Key_Request_API <<===================================== ");
		 
		  ClientResponse response= eCardShare.Symmetric_Key_Request_API(client,"users/key","f12fec2f637dbbeb49c2646c2b04e65e");
		  String resp = response.getEntity(String.class);

			 HashMap<String, Serializable> surveyMap = null;
			 ArrayList<HashMap<String, Serializable>> addSurveyMap = null;
//			 HashMap<String, Serializable> dataMap = null;
			 
			 ObjectMapper mapper = new ObjectMapper();
			 try {
				 surveyMap = new ObjectMapper().readValue(resp, HashMap.class);
				 addSurveyMap = (ArrayList<HashMap<String, Serializable>>) surveyMap.get("message");
//				 dataMap = (HashMap<String, Serializable>) surveyMap.get("message");
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
			 
			 assertTrue(  (API_response_code == EXP_SUCCESS_STATUS) && 
			  	(resp_response_msg.contains("Success")) && 
			  (resp_msg_detail.contains("Server key for your profile")));
		
	 }
	 
	 @Test(dependsOnMethods={"testSuccess_Symmetric_Key_Request_API"}, alwaysRun=true)
	 public void testSuccess_Sharing_Cads_API() throws Exception
	 {
		 System.out.println(" \n\n ================================>> testSuccess_Sharing_Cads_API <<===================================== ");
		 
		  ClientResponse response= eCardShare.Sharing_Cads_API(client,"cards","f12fec2f637dbbeb49c2646c2b04e65e");
		  String resp = response.getEntity(String.class);

			 HashMap<String, Serializable> surveyMap = null;
			 ArrayList<HashMap<String, Serializable>> addSurveyMap = null;
//			 HashMap<String, Serializable> dataMap = null;
			 
			 ObjectMapper mapper = new ObjectMapper();
			 try {
				 surveyMap = new ObjectMapper().readValue(resp, HashMap.class);
				 addSurveyMap = (ArrayList<HashMap<String, Serializable>>) surveyMap.get("message");
//				 dataMap = (HashMap<String, Serializable>) surveyMap.get("message");
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
			 
			 assertTrue(  (API_response_code == EXP_SUCCESS_STATUS) && 
			  	(resp_response_msg.contains("Success")) && 
			  (resp_msg_detail.contains("The card details have been shared successfully.")));
		
	 }
	 
	 @Test(dependsOnMethods={"testSuccess_Sharing_Cads_API"}, alwaysRun=true)
	 public void testSuccess_View_Card_Detail_API() throws Exception
	 {
		 System.out.println(" \n\n ================================>> testSuccess_View_Card_Detail_API <<===================================== ");
		 
		  ClientResponse response= eCardShare.View_Card_Detail_API(client,"cards?cardId=14&deviceToken=1234567890","f12fec2f637dbbeb49c2646c2b04e65e");
		  String resp = response.getEntity(String.class);
		  HashMap<String, Serializable> surveyMap = null;
			 ArrayList<HashMap<String, Serializable>> addSurveyMap = null;
			 HashMap<String, Serializable> dataMap = null;
			 
			 ObjectMapper mapper = new ObjectMapper();
			 try {
				 surveyMap = new ObjectMapper().readValue(resp, HashMap.class);
				 addSurveyMap = (ArrayList<HashMap<String, Serializable>>) surveyMap.get("message");
				 dataMap = (HashMap<String, Serializable>) surveyMap.get("data");
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
			 
			 String resp_data_cardType = (String) dataMap.get("cardType").toString();
			 System.out.println("Response DataMap Detail :"+resp_data_cardType.toString());
			 
			 String resp_data_cardData = (String) dataMap.get("cardData").toString();
			 System.out.println("Response DataMap Detail :"+resp_data_cardData.toString());
			 
			 assertTrue(  (API_response_code == EXP_SUCCESS_STATUS) && 
			  	(resp_response_msg.contains("Success")) && 
			  (resp_data_cardType.contains("quick")) &&
			  (resp_data_cardData.contains("this is the sample card details for sharing")));
		
	 }
	 
	 @Test(dependsOnMethods={"testSuccess_View_Card_Detail_API"}, alwaysRun=true)
	 public void testSuccess_Get_My_Notification_List_API() throws Exception
	 {
		 System.out.println(" \n\n ================================>> testSuccess_Get_My_Notification_List_API <<===================================== ");
		 
		  ClientResponse response= eCardShare.Get_My_Notification_List_API(client,"notifications?deviceToken=0123456789&offset=1&limit=12","f12fec2f637dbbeb49c2646c2b04e65e");
		  String resp = response.getEntity(String.class);

			 HashMap<String, Serializable> surveyMap = null;
			 ArrayList<HashMap<String, Serializable>> addSurveyMap = null;
//			 HashMap<String, Serializable> dataMap = null;
			 
			 ObjectMapper mapper = new ObjectMapper();
			 try {
				 surveyMap = new ObjectMapper().readValue(resp, HashMap.class);
				 addSurveyMap = (ArrayList<HashMap<String, Serializable>>) surveyMap.get("message");
//				 dataMap = (HashMap<String, Serializable>) surveyMap.get("message");
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
			 
			 assertTrue(  (API_response_code == EXP_SUCCESS_STATUS) && 
			  	(resp_response_msg.contains("Success")) && 
			  (resp_msg_detail.contains("Card details")));
		
	 }
	 
	 @Test(dependsOnMethods={"testSuccess_Get_My_Notification_List_API"}, alwaysRun=true)
	 public void testSuccess_Email_Verification_Api() throws Exception
	 {
		 System.out.println(" \n\n ================================>> testSuccess_Email_Verification_Api <<===================================== ");
		 
		  ClientResponse response= eCardShare.Email_Verification_Api(client,"users/verify_email?emailToken=",emailToken);
		  String resp = response.getEntity(String.class);
		  
		  String soapmessageString = "<xml>"+resp+"</xml>";
		  JSONObject soapDatainJsonObject = XML.toJSONObject(soapmessageString);
		  System.out.println(soapDatainJsonObject);
				  
		  	HashMap<String, Serializable> surveyMap = null;
			 ArrayList<HashMap<String, Serializable>> addSurveyMap = null;
//			 HashMap<String, Serializable> dataMap = null;
			 
			 ObjectMapper mapper = new ObjectMapper();
			 try {
				 surveyMap = new ObjectMapper().readValue(resp, HashMap.class);
				 addSurveyMap = (ArrayList<HashMap<String, Serializable>>) surveyMap.get("message");
//				 dataMap = (HashMap<String, Serializable>) surveyMap.get("message");
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
			 
			 assertTrue(  (API_response_code == EXP_SUCCESS_STATUS) && 
			  	(resp_response_msg.contains("Success")) && 
			  (resp_msg_detail.contains("Card details")));
		
	 }
}
