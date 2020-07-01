import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import com.client.controller.SocketClient;
import com.commons.model.AccessServer;

import NetworkCard.NetworkInsertSelect;

public class Valid_Insert {

	public static Connection c; 
	private static String URL = "jdbc:postgresql://172.31.249.44:5432/NamaiDB";
	private static String login = "toto" ;
	private static String password = "toto";


	public static Connection createConnection() throws SQLException {
		try {

			return  DriverManager.getConnection (URL, login, password);
		} catch (SQLException e) {
			throw new SQLException("Can't create connection", e);
		}

	}

	
	public static void main(String [] args) throws IOException, SQLException, JSONException, ParseException {
		
		InputStreamReader ipsr = new InputStreamReader(Valid_Insert.class.getClassLoader().getResourceAsStream("CarteVille.json"));
		BufferedReader br = new BufferedReader(ipsr);		
		
		String outjsonString = "";
		String s = "";

		while ((outjsonString = br.readLine()) != null) {
			s = s + outjsonString;
		}
		 c = createConnection();
		
		JSONObject obj = new JSONObject(s);
		obj.put("demandType",String.valueOf("demande insertion")); 
		System.out.println(obj);
		NetworkInsertSelect networkInsertSelect=new NetworkInsertSelect();
		JSONObject reponse= new JSONObject();
			reponse=networkInsertSelect.insertNetwork(obj,c);
		System.out.println(reponse);
	}

}
