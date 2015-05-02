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
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Utility.AlgrothimImpl;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * @author Arun
 *
 */

public class eCardShareTest extends AlgrothimImpl {
	
	  String EXP_SUCCESS_STATUS = "200";
	  String EXP_FAIL_STATUS = "400";
	  static String userName;
	  static String Password;
	  static ClientConfig config;
	  static Client client;
	  String resp_userId_detail=null;
	  String resp_oauthToken_detail=null;
	  String resp_udid=null;
	  String resp_serverKey=null;
	  String emailToken="AwGDyPnLGnqUvIqB6/yOq6q9SSkFMpjJOjnmepvkUWRxuTlp2kXB7n7L6S0f3Qitulfbau8SEg0KZfgptYnvkTuXpBNHldsZR30+x0zr1gdIfXRFKIjNZJyD9sibtvXUj2ZZ9d51ZuzXmqc/umhC1zu6gaCK3z853e7rl1twPcdm9Q==";
    
	 @BeforeClass
	 public static void beforeClass() throws UnknownHostException {
		          
	      config = new DefaultClientConfig();
	      client = Client.create(config);    
	 }
	
@Test(dataProvider="inputData")
	 public void testSucess_registrationAPI(String userName, String password, String fName, 
			 String Lname, String mobilenumber, String deviceType, String deviceToken, String oldUsername, 
			 String forceRegistration, String claimPhoneNumber ) throws Exception
	 {
		 
		
		 System.out.println(" \n\n ================================>> testSucess_registrationAPI <<===================================== ");
		 
		  ClientResponse response= eCardShare.registrationAPI(client,userName,password,fName,
				  Lname,mobilenumber,deviceType,deviceToken,oldUsername,forceRegistration,claimPhoneNumber);
		  String resp = response.getEntity(String.class);

			 HashMap<String, Serializable> surveyMap = null;
			 ArrayList<HashMap<String, Serializable>> addSurveyMap = null;
			 ArrayList<HashMap<String, Serializable>> dataMap = null;
			 HashMap<String, Serializable> dataOauthMap = null;
			 ArrayList<HashMap<String, Serializable>> datamapper = null;
			 
			 ObjectMapper mapper = new ObjectMapper();
			 try {
				 surveyMap = new ObjectMapper().readValue(resp, HashMap.class);
				 addSurveyMap = (ArrayList<HashMap<String, Serializable>>) surveyMap.get("message");
				 dataMap = (ArrayList<HashMap<String, Serializable>>) surveyMap.get("data");
				 dataOauthMap= (HashMap<String, Serializable>) dataMap.get(0);//.get("userDetails");
				 datamapper = (ArrayList<HashMap<String, Serializable>>) dataOauthMap.get("userDetails");
				 System.out.println(dataOauthMap);
			 }
			 catch (JsonParseException e) {	e.printStackTrace(); } 
			 catch (JsonMappingException e) { e.printStackTrace(); } 
			 catch (IOException e) { e.printStackTrace(); }

			 String API_response_code = (String) surveyMap.get("responseCode").toString();
			 String resp_response_msg = (String) surveyMap.get("responseMessage").toString();
			 String resp_msg_detail = (String) addSurveyMap.get(0).get("detail").toString();
			 resp_userId_detail = (String) datamapper.get(0).get("userId").toString();
			 resp_oauthToken_detail = (String) datamapper.get(0).get("oauthToken").toString();
			 assertTrue(API_response_code.equalsIgnoreCase(EXP_SUCCESS_STATUS) &&
					 (resp_response_msg.equalsIgnoreCase("Success")) &&
					 (resp_msg_detail.equalsIgnoreCase("User registration is successful")));
			
			
	 }
	
	@Test(dataProvider="inputData",dependsOnMethods={"testSucess_registrationAPI"}, alwaysRun=true)
	 public void testSuccess_Resend_Email_API(String mobileNumber,String deviceToken) throws Exception
	 {
		 System.out.println(" \n\n ================================>> testSuccess_Resend_Email_API <<===================================== ");
		 
		  ClientResponse response= eCardShare.Resend_Email_API(client,resp_userId_detail,resp_oauthToken_detail);
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
			 
			 assertTrue(API_response_code.equalsIgnoreCase(EXP_SUCCESS_STATUS));
			 assertTrue(resp_response_msg.contains("Success"));
			 assertTrue(resp_msg_detail.contains("The email verification link is sent to your email address"));
			 
		
	 }
	
	 
	 @Test(dataProvider="inputData",dependsOnMethods={"testSuccess_Resend_Email_API"},alwaysRun=true)
	 public void testSuccess_Resend_Mobile_Verification_API(String mobileNumber, String deviceToken) throws Exception
	 {
		 System.out.println(" \n\n ================================>> testSuccess_Resend_Mobile_Verification_API <<===================================== ");
		 // API not found error
		  ClientResponse response= eCardShare.Resend_Mobile_Verification_API(client);
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
			 
			 assertTrue((API_response_code.equalsIgnoreCase(EXP_SUCCESS_STATUS)) && 
			  	(resp_response_msg.contains("Success")) && 
			  (resp_msg_detail.contains("Mobile number verification PIN is sent to your registered mobile numbers")));
			 
			 //need to some other validation
		
	 } 
	 
	 @Test(dataProvider="inputData",dependsOnMethods={"testSuccess_Resend_Mobile_Verification_API"},alwaysRun=true)
	 public void testSuccess_PinVerificationAPI(String mobileNumber,String deviceToken,String pin) throws Exception
	 {
		 System.out.println(" \n\n ================================>> testSuccess_PinVerificationAPI <<===================================== ");
	//need to pass pin number manually, until separate Db get into a picture	 
		  ClientResponse response= eCardShare.PinVerificationAPI(client,pin,resp_oauthToken_detail);
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
			 System.out.println("Response CODE:"+API_response_code );
			
			 String resp_response_msg = (String) surveyMap.get("responseMessage").toString();
			 System.out.println("Response Message:"+resp_response_msg );
			 
			 String resp_msg_detail = (String) addSurveyMap.get(0).get("detail").toString();
			 System.out.println("Response DataMap Detail :"+resp_msg_detail.toString());
			 
			 resp_udid = (String) dataMap.get(0).get("udid").toString();
			 System.out.println("Response DataMap UDID Detail :"+resp_udid.toString());
			 
			 assertTrue(  (API_response_code.trim().equalsIgnoreCase(EXP_SUCCESS_STATUS)) && 
			  	(resp_response_msg.contains("Success")) && 
			  (resp_msg_detail.contains("PIN verification is successful")));
		
	 }
	
	 @Test(dataProvider="inputData",dependsOnMethods={"testSuccess_PinVerificationAPI"},alwaysRun=true)
	 public void testSuccess_Symmetric_Key_Request_API(String mobileNumber, String deviceToken) throws Exception
	 {
		 System.out.println(" \n\n ================================>> testSuccess_Symmetric_Key_Request_API <<===================================== ");
		 
		  ClientResponse response= eCardShare.Symmetric_Key_Request_API(client,resp_udid);
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
			 System.out.println("Response CODE:"+API_response_code );
			
			 String resp_response_msg = (String) surveyMap.get("responseMessage").toString();
			 System.out.println("Response Message:"+resp_response_msg );
			 
			 String resp_msg_detail = (String) addSurveyMap.get(0).get("detail").toString();
			 System.out.println("Response DataMap Detail :"+resp_msg_detail.toString());
			 
			 
			 resp_serverKey = (String) dataMap.get(0).get("serverKey").toString();
			 System.out.println("Response DataMap UDID Detail :"+resp_serverKey.toString());
			 
			 assertTrue((API_response_code.equalsIgnoreCase(EXP_SUCCESS_STATUS)) && 
			  	(resp_response_msg.contains("Success")) && 
			  (resp_msg_detail.contains("Server key for your profile")));
		
	 }
	 
	 @SuppressWarnings("unchecked")
	@Test(dataProvider="inputData",dependsOnMethods={"testSuccess_Symmetric_Key_Request_API"}, alwaysRun=true)
	 public void testSuccess_Sharing_Cads_API(String deviceToken) throws Exception
	 {
		 System.out.println(" \n\n ================================>> testSuccess_Sharing_Cads_API <<===================================== ");
		 /*
		  **Algorithm for Encrypted msg
		  */
		  ClientResponse response= eCardShare.Sharing_Cads_API(client);
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
			  (resp_msg_detail.contains("The card details have been shared successfully.")));
		
	 }
	 
	 @Test(dataProvider="inputData",dependsOnMethods={"testSuccess_Sharing_Cads_API"}, alwaysRun=true)
	 public void testSuccess_View_Card_Detail_API(String deviceToken) throws Exception
	 {
		 System.out.println(" \n\n ================================>> testSuccess_View_Card_Detail_API <<===================================== ");
		 
		  ClientResponse response= eCardShare.View_Card_Detail_API(client,deviceToken,resp_oauthToken_detail);
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
	 
	 @Test(dataProvider="inputData",dependsOnMethods={"testSuccess_View_Card_Detail_API"}, alwaysRun=true)
	 public void testSuccess_Get_My_Notification_List_API(String deviceToken) throws Exception
	 {
		 System.out.println(" \n\n ================================>> testSuccess_Get_My_Notification_List_API <<===================================== ");
		 
		  ClientResponse response= eCardShare.Get_My_Notification_List_API(client);
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
		 
		  ClientResponse response= eCardShare.Email_Verification_Api(client,"users/verify_email?emailToken=");
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
