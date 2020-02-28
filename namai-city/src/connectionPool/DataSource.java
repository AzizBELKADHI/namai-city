package connectionPool;

import java.sql.*;

public class DataSource {
	private static JDBCConnectionPool connectionPool;
	public DataSource() throws ClassNotFoundException, SQLException {
		connectionPool = new JDBCConnectionPool();
	}
	
	public static Connection getConnection() {
		return connectionPool.getConnection();
	}
	
	public static void addConnection(Connection c) {
		connectionPool.addConnection(c);
	}
	
	public static void closeConnections() throws SQLException {
		connectionPool.closeConnections();
	}
	

}
