/*package com.client.application;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.client.controller.SocketClient;
import com.client.view.PanneauPrincipalBornes;
import com.commons.model.AccessServer;

import carsHistory.carsHistory;
import indicator.CarIndicator;
import indicator.PersonStationIndicator;
import indicator.SensorIndicator;
import indicator.StationIndicator;
import indicator.WarningIndicator;



public class TestBornes  {
	private SocketClient client = new SocketClient();
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
	
	public static void main(String [] args) throws UnsupportedEncodingException {
		TestBornes t = new TestBornes();
		
		try {
			
			JFrame fenetre = new JFrame();
			fenetre.setLocationRelativeTo(null);
			fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			fenetre.setTitle("bornes");
			fenetre.setSize(1300, 720);
					
			JSONObject rep = t.go();
			PanneauPrincipalBornes test = new PanneauPrincipalBornes(rep); 
			final ArrayList<JSONObject> allBornes = (ArrayList<JSONObject>) rep.get("bornes");
			Object data[][]= 	new Object[allBornes.size()][3];
			for(int i = 0; i<allBornes.size(); i++) {
			//		System.out.println(allBornes.get(i));
					data[i][0] = allBornes.get(i).get("Id_borne");
					data[i][1] = allBornes.get(i).get("position");
					if(allBornes.get(i).get("state").equals("0")) {
						data[i][2] = "baissé";
					}
					else {
						data[i][2] = "relevé";
					}
			
			}
			  
			    //Les titres des colonnes
			    String  title[] = {"borne", "position", "état"};
			    JTable tableau = new JTable(data, title);	
			    fenetre.getContentPane().add(new JScrollPane(tableau), BorderLayout.NORTH);
				JLabel label = new JLabel("nombre de vehicules");
		//		fenetre.getContentPane().add(label,BorderLayout.CENTER);
		//	    fenetre.setVisible(true);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}
	

	public JSONObject go() throws SQLException, IOException,UnsupportedEncodingException {

		client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
		JSONObject obj=new JSONObject();  //JSONObject creation
		obj.put("demandType",String.valueOf("getInitialInfos")); 
		System.out.println(obj);	
		JSONObject reponseBornes = client.sendMessage(obj);
		System.out.println(reponseBornes);
		client.stopConnection(); 

		return reponseBornes; 
		
	}
}
*/