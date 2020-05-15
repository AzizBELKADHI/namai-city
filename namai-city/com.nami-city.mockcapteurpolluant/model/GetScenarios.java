package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetScenarios {
	final Properties prop;
	InputStream input = null;
	private static String scenarios;
	
	private GetScenarios() {
		prop = new Properties();
		try {

			input = new FileInputStream("./ressources/MockPollution.properties");
			// loads the file properties 
			prop.load(input);
			scenarios = prop.getProperty("scenarios");
		} catch (final IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String getScenarios() {
		return scenarios;
	}

	public void setScenarios(String scenarios) {
		this.scenarios = scenarios;
	}
}
