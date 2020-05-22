package com.client.application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.json.simple.JSONObject;

import com.client.controller.SocketClient;
import com.commons.model.AccessServer;

import indicator.CarIndicator;
import indicator.SensorIndicator;
import indicator.StationIndicator;


public class TestCLI {
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
	
	public static void main(String [] args) throws SQLException, IOException {
		TestCLI t = new TestCLI();
		t.testCLI();
		
		PagePrincipale page = new PagePrincipale();
		page.setVisible(true);
	
		
	}
	
	public void testCLI() throws IOException{
	Scanner sc = new Scanner(System.in);
	while(true) { // Menu display
		System.out.println("########################### Menu Namai-city-client #########################");
		System.out.println("1: Afficher Bornes");
		System.out.println("2: lever Bornes");
		System.out.println("3: baisser bornes");
		System.out.println("4: Supprimer");
		System.out.println("########################### Menu Namai-city-client #########################");
		
		client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
		JSONObject obj=new JSONObject();  //JSONObject creation
		String rep = sc.nextLine();
		

		switch (rep) {
		
		case "1":
			try {
				client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			obj.put("demandType",String.valueOf("getInitialInfos")); 
			System.out.println(obj);	
			JSONObject reponseBornes = client.sendMessage(obj);
			
			System.out.println("voici les bornes récupéré" + reponseBornes);
			client.stopConnection(); 
				break;


		case "2":
				
				System.out.println("l'état des bornes va etre modifié");
				client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
				obj=new JSONObject();  //JSONObject creation
				obj.put("demandType",String.valueOf("RiseBornes")); 
				System.out.println(obj);	
				JSONObject reponseSimulation = client.sendMessage(obj);
				System.out.println("reponse levée des bornes :" + reponseSimulation);
				client.stopConnection(); 
				break;

		case "3":
			// requete insertion dans table utilisateur
			System.out.println("l'état des bornes va etre modifié");
			SocketClient client = new SocketClient();
			client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
			obj=new JSONObject();  //JSONObject creation
			obj.put("demandType",String.valueOf("LowerBornes")); 
			System.out.println(obj);	
			reponseSimulation = client.sendMessage(obj);
			System.out.println(reponseSimulation);
			client.stopConnection();   
			break; 

		case "4": 
			
			this.client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
			obj=new JSONObject();  //JSONObject creation
			
			System.out.println("quel est le noouveau max vehicules?"); 
			String id_update = sc.nextLine();
			Integer id_user_update = Integer.parseInt(id_update);
			obj.put("demandType",String.valueOf("ChangeLimit")); 
			obj.put("maxCars",Integer.valueOf(id_user_update)); 
			System.out.println(obj);	
			JSONObject reponseMaxVehicules = this.client.sendMessage(obj);
			System.out.println(reponseMaxVehicules);
			this.client.stopConnection(); 
			break; 
			
		
		case "5": 
			
			System.out.println("je rentre deja dans la recherche vehicules");
			this.client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
			obj=new JSONObject();  //JSONObject creation
			
			System.out.println("quel est la date de depart ?"); 
			String dateDebut = sc.nextLine();
			
			System.out.println("quel est la date de fin ?"); 
			String dateFin = sc.nextLine();
			
			System.out.println("quel est le type de mouvements?"); 
			String type = sc.nextLine();
			
			System.out.println("quel est la zone de la ville?"); 
			String zone = sc.nextLine();
			
	
			obj.put("demandType",String.valueOf("filterVehicule"));
			if(type.equals("Les deux")) {
				type = "town";
			}
			if(zone.equals("toute la ville")) {
				zone = "All";
			}
			obj.put("type", String.valueOf(type));
			obj.put("zone", String.valueOf(zone));
			obj.put("dateDebut", String.valueOf(dateDebut));
			obj.put("dateFin", String.valueOf(dateFin));
			
			System.out.println(obj);	
			JSONObject reponseSearch = this.client.sendMessage(obj);
			this.client.stopConnection(); 
			break; 

		}
	}

	}


}
	

