package com.iexemplar.Iexemplar_API;

import static org.testng.Assert.assertTrue;

import iExemplar_API.eCardShareMobileAPI;
import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Utility.AlgrothimImpl;
import Utility.RNEncryptor;
//import Utility.Browser;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * @author Arun
 *
 */

public class eCardShareTest extends AlgrothimImpl {
	public static WebDriver webDriver;
	public static RNEncryptor RNEncryptor;
	String EXP_SUCCESS_STATUS = "200";
	String EXP_FAIL_STATUS = "400";
	static String userName;
	static String Password;
	static String mobileNumber;
	static ClientConfig config;
	static Client client;
	String resp_userId_detail=null;
	String resp_oauthToken_detail=null;
	String resp_udid=null;
	String resp_serverKey=null;
	HashMap<String, Serializable> dataOauthMap;
	
	 @BeforeClass
	 public static void beforeClass() throws UnknownHostException {
		 
		 RNEncryptor = PageFactory.initElements(webDriver, RNEncryptor.class);
		 config = new DefaultClientConfig();
	     client = Client.create(config);    
	 }
	
@SuppressWarnings({ "unchecked", "unused" })
@Test()
	 public void testSuccess_registrationAPI() throws Exception
	 {
				String username = AlgrothimImpl.generateRandomString(3, AlgrothimImpl.Mode.ALPHA);
				username = "restMobileAPI".concat(username).concat("@yopmail.com");
				String password = AlgrothimImpl.generateRandomString(10, AlgrothimImpl.Mode.ALPHA);
				String fName = AlgrothimImpl.generateRandomString(10, AlgrothimImpl.Mode.ALPHA);
				String Lname = AlgrothimImpl.generateRandomString(10, AlgrothimImpl.Mode.ALPHA);
				String mobNumber = AlgrothimImpl.generateRandomString(10, AlgrothimImpl.Mode.NUMERIC);
				mobileNumber = mobNumber;
		 System.out.println(" \n\n ================================>> testSucess_registrationAPI <<===================================== ");
		 
		 ClientResponse response= eCardShareMobileAPI.registrationAPI(client,username,password,fName,Lname,mobNumber);
		 String resp = response.getEntity(String.class);
		 String resp_message = null;
		 HashMap<String, Serializable> surveyMap = null;
		 HashMap<String, Serializable> addSurveyMap = null;
		 ArrayList<HashMap<String, Serializable>> dataMap = null;
		 HashMap<String, Serializable> dataOauthMap = null;
		 ArrayList<HashMap<String, Serializable>> datamapper = null;
			 
		 ObjectMapper mapper = new ObjectMapper();
		 try {
				 surveyMap = new ObjectMapper().readValue(resp, HashMap.class);
		   		 resp_message =(String) surveyMap.get("message");
				 dataMap = (ArrayList<HashMap<String, Serializable>>) surveyMap.get("data");
				 dataOauthMap= (HashMap<String, Serializable>) dataMap.get(0);
				 datamapper = (ArrayList<HashMap<String, Serializable>>) dataOauthMap.get("userDetails");
				 System.out.println(dataOauthMap);
			 }
			 catch (JsonParseException e) {	e.printStackTrace(); } 
			 catch (JsonMappingException e) { e.printStackTrace(); } 
			 catch (IOException e) { e.printStackTrace(); }
			 
			 	String resp_response_msg = (String) surveyMap.get("status").toString().trim();
			 	resp_userId_detail = (String) datamapper.get(0).get("userId").toString().trim();
			 	resp_oauthToken_detail = (String) datamapper.get(0).get("oauthToken").toString().trim();
			 	assertTrue(response.toString().contains(EXP_SUCCESS_STATUS) &&
					 (resp_response_msg.equalsIgnoreCase("Success")) &&
					 resp_message.equalsIgnoreCase(ApiResponseMessage.Registration_success));
			
			
	 }

@SuppressWarnings({ "unchecked", "unused", "null" })
@Test(dependsOnMethods={"testSuccess_registrationAPI"}, alwaysRun=true)
public void testSuccess_ResendEmail_Verification_Api() throws Exception
{
	 System.out.println(" \n\n ================================>> testSuccess_Email_Verification_Api <<===================================== ");
	 ClientResponse response= eCardShareMobileAPI.Resend_Email_API(client,resp_userId_detail,resp_oauthToken_detail);
	 String resp = response.getEntity(String.class);
	  	  
	  	HashMap<String, Serializable> surveyMap = null;
		 ArrayList<HashMap<String, Serializable>> addSurveyMap = null;
		 ArrayList<HashMap<String, Serializable>> dataMap = null;
		 HashMap<String, Serializable> dataOauthMap = null;
		 ArrayList<HashMap<String, Serializable>> datamapper = null;
		 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 surveyMap = new ObjectMapper().readValue(resp, HashMap.class);
		
		 
		 String resp_response_status = (String) surveyMap.get("status").toString().trim();
		 String resp_response_msg = (String) surveyMap.get("message").toString().trim();
		 addSurveyMap = (ArrayList<HashMap<String, Serializable>>) surveyMap.get("data");
		 System.out.println(resp_response_msg);
		 System.out.println(ApiResponseMessage.EmailLink_SendSuccess);
		
		  assertTrue((response.toString().trim().contains(EXP_SUCCESS_STATUS))); //&& 
		  assertTrue((resp_response_status.trim().equalsIgnoreCase("Success"))); //&& 
		  assertTrue((resp_response_msg.trim().equalsIgnoreCase(ApiResponseMessage.EmailLink_SendSuccess)));//) );
	
}

@SuppressWarnings({ "unchecked", "unused", "null" })
@Test(dependsOnMethods={"testSuccess_ResendEmail_Verification_Api"}, alwaysRun=true)
public void testSuccess_Email_Verification_Api() throws Exception
{
	 System.out.println(" \n\n ================================>> testSuccess_Email_Verification_Api <<===================================== ");
	 ClientResponse response= eCardShareMobileAPI.Email_Verification_Api(client,"users/verify_email?token=");
	 String resp = response.getEntity(String.class);
	  	  
	  	 HashMap<String, Serializable> surveyMap = null;
	  	 ArrayList<HashMap<String, Serializable>> addSurveyMap = null;
		 ArrayList<HashMap<String, Serializable>> dataMap = null;
		 HashMap<String, Serializable> dataOauthMap = null;
		 HashMap<String, Serializable> MobilePin = null;
		 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 surveyMap = new ObjectMapper().readValue(resp, HashMap.class);
		
		 
		 String resp_response_msg = (String) surveyMap.get("status").toString();
		 addSurveyMap = (ArrayList<HashMap<String, Serializable>>) surveyMap.get("data");
		 dataOauthMap= (HashMap<String, Serializable>) addSurveyMap.get(0);
		 dataMap= (ArrayList<HashMap<String, Serializable>>) dataOauthMap.get("mobilePinSent");
		// MobilePin= (HashMap<String, Serializable>) dataMap.get(0);
		 
		 assertTrue((response.toString().contains(EXP_SUCCESS_STATUS)) && 
		  	(resp_response_msg.equalsIgnoreCase("Success")) /*&& 
		  (MobilePin.toString().trim().equalsIgnoreCase("["+mobileNumber+"]"))*/);
	
}


@SuppressWarnings("unchecked")
@Test(dependsOnMethods={"testSuccess_Email_Verification_Api"},alwaysRun=true)
public void testSuccess_Resend_Mobile_Verification_API() throws Exception
{
	 System.out.println(" \n\n ================================>> testSuccess_Resend_Mobile_Verification_API <<===================================== ");
	 ClientResponse response= eCardShareMobileAPI.Resend_Mobile_Verification_API(client,resp_userId_detail);
	 String resp = response.getEntity(String.class);

	 HashMap<String, Serializable> surveyMap = null;
		
	 ObjectMapper mapper = new ObjectMapper();
	 try {
	 	 surveyMap = new ObjectMapper().readValue(resp, HashMap.class);
			 
		 }
		 catch (JsonParseException e) {	e.printStackTrace(); } 
		 catch (JsonMappingException e) { e.printStackTrace(); } 
		 catch (IOException e) { e.printStackTrace(); }

		 String API_response_status = (String) surveyMap.get("status").toString();
		 System.out.println("Response CODE:"+API_response_status );
		 
		 String resp_response_msg = (String) surveyMap.get("message").toString();
		 System.out.println("Response Message:"+resp_response_msg );
		 
		assertTrue((response.toString().equalsIgnoreCase(EXP_SUCCESS_STATUS)) && 
			(API_response_status.contains("Success")) && 
		  (resp_response_msg.contains("Mobile number verification PIN is sent to your registered mobile numbers")));
		 
		 //need to some other validation
	
} 




@SuppressWarnings({ "unchecked", "unused" })
@Test(dependsOnMethods={"testSuccess_Resend_Mobile_Verification_API"},alwaysRun=true)
public void testSuccess_PinVerificationAPI() throws Exception
{
	 System.out.println(" \n\n ================================>> testSuccess_PinVerificationAPI <<===================================== ");
//need to pass pin number manually, until separate Db get into a picture	 
	  ClientResponse response= eCardShareMobileAPI.PinVerificationAPI(client,resp_oauthToken_detail);
	  String resp = response.getEntity(String.class);

		 HashMap<String, Serializable> surveyMap = null;
		 ArrayList<HashMap<String, Serializable>> addSurveyMap = null;
		 ArrayList<HashMap<String, Serializable>> dataMap = null;
		
		 
		 ObjectMapper mapper = new ObjectMapper();
		 try {
			 surveyMap = new ObjectMapper().readValue(resp, HashMap.class);
			
			
		 }
		 	catch (JsonParseException e) {	e.printStackTrace(); } 
		 	catch (JsonMappingException e) { e.printStackTrace(); } 
		 	catch (IOException e) { e.printStackTrace(); }

		 String resp_response_status = surveyMap.get("status").toString().trim();
		 System.out.println("Response Message:"+resp_response_status );

		 String	resp_response_msg = surveyMap.get("message").toString().trim();
		 System.out.println("Response Message:"+resp_response_msg );
			 
				 
		 assertTrue((response.toString().contains(EXP_SUCCESS_STATUS)));// && 
		 assertTrue((resp_response_status.equalsIgnoreCase("Success")));
		 assertTrue((resp_response_msg.equalsIgnoreCase("Mobile PIN verified")));
		
	
}

@SuppressWarnings({ "unchecked", "unused" })
@Test(dependsOnMethods={"testSuccess_PinVerificationAPI"},alwaysRun=true)
public void testSuccess_ResendUID() throws Exception
{
	 System.out.println(" \n\n ================================>> testSuccess_PinVerificationAPI <<===================================== ");
	 
	  ClientResponse response= eCardShareMobileAPI.Resend_UDID_API(client);
	  String resp = response.getEntity(String.class);

		 HashMap<String, Serializable> surveyMap = null;
		 ArrayList<HashMap<String, Serializable>> addSurveyMap = null;
		 ArrayList<HashMap<String, Serializable>> dataMap = null;
		
		 
		 ObjectMapper mapper = new ObjectMapper();
		 try {
			 surveyMap = new ObjectMapper().readValue(resp, HashMap.class);
			
			
		 }
		 	catch (JsonParseException e) {	e.printStackTrace(); } 
		 	catch (JsonMappingException e) { e.printStackTrace(); } 
		 	catch (IOException e) { e.printStackTrace(); }

		 String resp_response_status = surveyMap.get("status").toString();
		 System.out.println("Response Message:"+resp_response_status );
		
		 resp_udid = surveyMap.get("message").toString();
		 System.out.println("Response Message:"+resp_udid );
			 
				 
		 assertTrue((response.toString().contains(EXP_SUCCESS_STATUS)));// && 
		 assertTrue((resp_response_status.equalsIgnoreCase("Success")));
		 assertTrue(!(resp_udid.equalsIgnoreCase("")));
	
}

@SuppressWarnings({ "unchecked", "unused" })
@Test(dependsOnMethods={"testSuccess_PinVerificationAPI"},alwaysRun=true)
public void testSuccess_Symmetric_Key_Request_API() throws Exception
{
	 System.out.println(" \n\n ================================>> testSuccess_Symmetric_Key_Request_API <<===================================== ");
	 
	  ClientResponse response= eCardShareMobileAPI.Symmetric_Key_Request_API(client,resp_udid);
	  String resp = response.getEntity(String.class);

		 HashMap<String, Serializable> surveyMap = null;
		 ArrayList<HashMap<String, Serializable>> addSurveyMap = null;
		 ArrayList<HashMap<String, Serializable>> dataMap = null;
		 ObjectMapper mapper = new ObjectMapper();
		 try {
			 surveyMap = new ObjectMapper().readValue(resp, HashMap.class);
			 
			
		 }
		 catch (JsonParseException e) {	e.printStackTrace(); } 
		 catch (JsonMappingException e) { e.printStackTrace(); } 
		 catch (IOException e) { e.printStackTrace(); }
		 
		 
		 String API_response_status = (String) surveyMap.get("status").toString();
		 System.out.println("Response CODE:"+API_response_status );
		
		 String resp_response_msg = (String) surveyMap.get("message").toString();
		 System.out.println("Response Message:"+resp_response_msg );
		 
		 addSurveyMap = (ArrayList<HashMap<String, Serializable>>) surveyMap.get("data");
		 dataOauthMap= (HashMap<String, Serializable>) addSurveyMap.get(0);
		 dataMap= (ArrayList<HashMap<String, Serializable>>) dataOauthMap.get("serverKey");
		
		 assertTrue((response.toString().contains(EXP_SUCCESS_STATUS)) && 
		  	(API_response_status.contains("Success")) /*&& 
		  (resp_msg_detail.contains("Server key for your profile"))*/);
	
}

	 
	 
	 @SuppressWarnings("unchecked")
	@Test(dependsOnMethods={"testSuccess_Symmetric_Key_Request_API"}, alwaysRun=true)
	 public void testSuccess_Sharing_Cads_API() throws Exception
	 {
		 System.out.println(" \n\n ================================>> testSuccess_Sharing_Cads_API <<===================================== ");
		 
		 String encValue= "[{\"ecardDetails\":[{\"cardData\":\"Testing\",\"receiverList\":[{\"phoneNumber\" : \"9003589912\"}],\"cardType\":\"quick\",\"cardOwnerUserId\":\"ed077\"}],\"digitalSignature\":\"aswqkdjfneaswqkdjfneaswqkdjfneds\",\"sessionKey\":\"123123123\"}]}";
		String Message= RNEncryptor.encryption(encValue, "1234567890");
		  ClientResponse response= eCardShareMobileAPI.Sharing_Cads_API(client,Message);
		  String resp = response.getEntity(String.class);

			 HashMap<String, Serializable> surveyMap = null;
			 ArrayList<HashMap<String, Serializable>> addSurveyMap = null;
			 ObjectMapper mapper = new ObjectMapper();
			 try {
				 surveyMap = new ObjectMapper().readValue(resp, HashMap.class);
				
			 }
			 catch (JsonParseException e) {	e.printStackTrace(); } 
			 catch (JsonMappingException e) { e.printStackTrace(); } 
			 catch (IOException e) { e.printStackTrace(); }

			 String API_response_status = (String) surveyMap.get("status").toString();
			 System.out.println("Response CODE:"+API_response_status );
			
			 String resp_response_msg = (String) surveyMap.get("message").toString();
			 System.out.println("Response Message:"+resp_response_msg );
			 
			/* String resp_msg_detail = (String) addSurveyMap.get(0).get("detail").toString();
			 System.out.println("Response DataMap Detail :"+resp_msg_detail.toString());*/
			 
			/* assertTrue(  (API_response_code == EXP_SUCCESS_STATUS) && 
			  	(resp_response_msg.contains("Success")) && 
			  (resp_msg_detail.contains("The card details have been shared successfully.")));*/
		
	 }
	 
	 @Test(dependsOnMethods={"testSuccess_Sharing_Cads_API"}, alwaysRun=true)
	 public void testSuccess_View_Card_Detail_API() throws Exception
	 {
		 System.out.println(" \n\n ================================>> testSuccess_View_Card_Detail_API <<===================================== ");
		 
		  ClientResponse response= eCardShareMobileAPI.View_Card_Detail_API(client,resp_oauthToken_detail);
		  String resp = response.getEntity(String.class);
		  HashMap<String, Serializable> surveyMap = null;
		  System.out.println("testSuccess_View_Card_Detail_API"+resp);
			 ArrayList<HashMap<String, Serializable>> addSurveyMap = null;
			 HashMap<String, Serializable> dataMap = null;
			 
			 ObjectMapper mapper = new ObjectMapper();
			 try {
				 surveyMap = new ObjectMapper().readValue(resp, HashMap.class);
				
			 }
			 catch (JsonParseException e) {	e.printStackTrace(); } 
			 catch (JsonMappingException e) { e.printStackTrace(); } 
			 catch (IOException e) { e.printStackTrace(); }

			 String API_response_status = (String) surveyMap.get("status").toString();
			 System.out.println("Response CODE:"+API_response_status );
			
			 String resp_response_msg = (String) surveyMap.get("message").toString();
			 System.out.println("Response Message:"+resp_response_msg );
			 
			 String resp_msg_detail = (String) addSurveyMap.get(0).get("detail").toString();
			 System.out.println("Response DataMap Detail :"+resp_msg_detail.toString());
			 
			 String resp_data_cardType = (String) dataMap.get("cardType").toString();
			 System.out.println("Response DataMap Detail :"+resp_data_cardType.toString());
			 
			 String resp_data_cardData = (String) dataMap.get("cardData").toString();
			 System.out.println("Response DataMap Detail :"+resp_data_cardData.toString());
			 
			/* assertTrue(  (API_response_code == EXP_SUCCESS_STATUS) && 
			  	(resp_response_msg.contains("Success")) && 
			  (resp_data_cardType.contains("quick")) &&
			  (resp_data_cardData.contains("this is the sample card details for sharing")));*/
		
	 }
	 
	 @Test(dependsOnMethods={"testSuccess_View_Card_Detail_API"}, alwaysRun=true)
	 public void testSuccess_Get_My_Notification_List_API() throws Exception
	 {
		 System.out.println(" \n\n ================================>> testSuccess_Get_My_Notification_List_API <<===================================== ");
		 
		  ClientResponse response= eCardShareMobileAPI.Get_My_Notification_List_API(client);
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
			 
			/* assertTrue(  (API_response_code == EXP_SUCCESS_STATUS) && 
			  	(resp_response_msg.contains("Success")) && 
			  (resp_msg_detail.contains("Card details")));*/
		
	 }
	 
	 
}
