package com.iexemplar.Iexemplar_API;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.TimeZone;

import javax.swing.text.Utilities;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.sun.jersey.api.client.ClientResponse;

public class mix {
	
	
	 static StringBuilder joinAms = new StringBuilder("");
	
		   public static String[] signature(String[] args, String API_SECRET)
		        {
			   StringBuilder joiner = new StringBuilder("");
			  
		              Arrays.sort(args);
		            for(int i = 0; i < args.length; i++)
		            {
		            	
		            	joiner.append(args[i]);
		            	joinAms.append(args[i]).append('&');
		            
		            }
		          //  joiner.append(args);
		           
		            String joined = joiner.toString();
		            String md5Hex = DigestUtils.md5Hex (joined+API_SECRET);
					joinAms.append("sig=").append(md5Hex);
					String joinAms1 = joinAms.toString();
					String[] queryParam = {md5Hex,joinAms1};
		               
		               // GetUTCdatetimeAsString();
						return 	queryParam;
						
		        }
	public static String encodeUrl(String url){
			        
			        //System.out.println(URLEncoder.encode(url, "UTF-8"));
					url = url.replace(" ", "+");
					url = url.replace("[", "%5B");
					url = url.replace("]", "%5D");
					url = url.replace("\"", "%22");
					url = url.replace("==","%3D%3D");
					return url;
					
			    }

}

		
	


//api_key=02abb5c69b84e501d52a7a361918100fevent=User Registrationfrom_date=2015-5-10to_date=2015-5-11where= "1055018"==properties["UserId"]
//sig = fc790c2078638269a86017841922bd9d