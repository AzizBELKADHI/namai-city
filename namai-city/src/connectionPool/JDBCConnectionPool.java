package connectionPool;

import java.sql.*;
import java.util.*;

public class JDBCConnectionPool {
	ArrayList<Connection> connections;
	ArrayList<Connection> usedConnections;
	GetDataConnection Data;
	private int sizeMax = 3;
	private int sizeMin =1;
	private String DRIVER_NAME;
	private String URL;
	private String login ;
	private String password;
	
	
	public JDBCConnectionPool() throws SQLException, ClassNotFoundException {
		connections = new ArrayList<Connection>();
		usedConnections = new ArrayList<Connection>();
		Data = new GetDataConnection();
		DRIVER_NAME = Data.getDriverName();
		URL = Data.getDatabaseUrl();
		login= Data.getLogin();
		password = "";
		Connection con = null;
		for(int i=0; i<sizeMax; i++) {
			try {
				Class.forName (DRIVER_NAME);
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}		

		}

	}


// methode permettant de créer une connexion.
	public Connection createConnection() throws SQLException {
		try {
		return  DriverManager.getConnection (URL, login, password);
		} catch (SQLException e) {
			throw new SQLException("Can't create connection", e);
		}

	}

// amelioration de cette methode pour que l'utilisateur obtienne une connexion ssi les connexions 
	//utilisées ne depassent pas la taille maximale du pool et si le pool de connexion n'est pas vide.
	
	public Connection getConnection() throws SQLException {
		if(connections.isEmpty()) {
			if(usedConnections.size()<sizeMax) {
				try {
					connections.add(createConnection());
				} catch (SQLException e) {
						throw new SQLException("Can't create connection", e);
					
				}
			} else {
				throw new RuntimeException("Maximum pool size reached, no available connections!");
			}
		}
		Connection toGet = connections.remove(connections.size() -1);
		usedConnections.add(toGet);
		return toGet;
	}

// methode permettant de liberer les connexions de la liste des connexions utilisées et de les ajouter dans les connexions disponibles.	
	public synchronized boolean releaseConnection(Connection c) {
		connections.add(c);
		return usedConnections.remove(c);
		
	}

	
// methode permettant de fermer les connections si la liste des connexions utilisées n'est pas vide.
	public void closeConnections() throws SQLException {
		while(!usedConnections.isEmpty()) {
			releaseConnection(usedConnections.get(0));
		}
			for (Connection c : connections) {
				try{
					c.close();
				} catch (SQLException e) {
					throw new SQLException("Can't close connection", e); 
				}
			}
			
			
			connections.clear();
		}


	public int getSize() {
		return connections.size() + usedConnections.size();
	}
		
		
	}



