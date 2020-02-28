package connectionPool;

import java.sql.*;
import java.util.*;

public class JDBCConnectionPool {
	ArrayList<Connection> connections;
	GetDataConnection Data;
	
	public JDBCConnectionPool() throws SQLException, ClassNotFoundException {
		connections = new ArrayList<Connection>();
		Data = new GetDataConnection(); 
		String DRIVER_NAME = Data.getDriverName();
		String URL = Data.getDatabaseUrl();
		String login = Data.getLogin();
		String password = Data.getPassword();
		Connection con = null;
		for(int i=0; i<10; i++) {
			Class.forName (DRIVER_NAME);
			con = DriverManager.getConnection (URL, login, password);
			connections.add(con);

		}
	}
		
		

	public Connection getConnection() {
		Connection toGet = connections.get(0);
		connections.remove(0);
		return toGet;
	}
	
	
	public void addConnection(Connection c) {
		connections.add(c);
	}
	
	public void closeConnections() throws SQLException {
		for(int i=0;i<connections.size()-1;i++) {
			connections.get(i).close();
		}
	}
	
	
}
