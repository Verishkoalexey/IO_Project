package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class AppSettings {

	private Properties props;
	private static AppSettings instance;

	private AppSettings() {
		props = new Properties();
		try {
			props.load(new FileInputStream("resources/config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static AppSettings getInstance() {
		if (instance == null) {
			instance = new AppSettings();
		}
		return instance;
	}

	public Properties getProps() {
		return props;
	}
	
	public String getValue(String key){
		if(!props.containsKey(key)){
			System.out.println("NO SUCH KEY IN PROPS: " + key);
		}
		return props.getProperty(key);
	}
	
	public static void main(String[] args) {
		String val = getInstance().getValue("db_test_name");
		System.out.println(val);
	}
	
}
