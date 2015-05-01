package Utility;

import java.io.IOException;
import java.util.Properties;


public class PropertyUtil {
	
		public static String getProperty(String name) {
			String proppertyName = null;
			Properties properties = new Properties();
			try {
				properties.load(PropertyUtil.class.getClassLoader()
						.getResourceAsStream("conf/automation.properties"));
				proppertyName = properties.getProperty(name);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return proppertyName;
		}
	}



