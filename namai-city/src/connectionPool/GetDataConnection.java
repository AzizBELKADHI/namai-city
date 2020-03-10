package connectionPool;

import java.io.*;
import java.util.Properties;

public class GetDataConnection {
	
	private String DriverName;
	private String DatabaseUrl;
	private String login;
	private String password;
	final Properties prop;
	InputStream input = null;
	
	public GetDataConnection() {
		prop = new Properties();
		try {

			input = new FileInputStream("./Ressources/Data.properties");

			// charge le fichier properties 
			prop.load(input);

			// r�cupere les valeurs des variables dans le fichier properties 
			DatabaseUrl = prop.getProperty("url");
			login = prop.getProperty("username");
			password = prop.getProperty("password");
			DriverName = prop.getProperty("DriverName");

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
	
	
	public String getDriverName() {
		return DriverName;
	}
	public void setDriverName(String driverName) {
		DriverName = driverName;
	}
	public String getDatabaseUrl() {
		return DatabaseUrl;
	}
	public void setDatabaseUrl(String databaseUrl) {
		DatabaseUrl = databaseUrl;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
