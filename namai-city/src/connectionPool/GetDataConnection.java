package connectionPool;

public class GetDataConnection {
	private String DriverName;
	private String DatabaseUrl;
	private String login;
	private String password;
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
