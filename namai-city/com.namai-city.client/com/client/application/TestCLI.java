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
import indicator.PersonStationIndicator;
import indicator.SensorIndicator;
import indicator.StationIndicator;
import indicator.WarningIndicator;

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
	//	TestJson t = new TestJson();
		PagePrincipale page = new PagePrincipale();
		page.setVisible(true);
	
		
	}
	
	public void testCLI(){
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
			client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
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

		case "3": 
			// requete pour mettre à  jour la table utilisateur 
			System.out.println("########################### UPDATE #########################");
			System.out.println("quel est l'id à modifier?"); 

			String id_update = sc.nextLine();
			Integer id_user_update = Integer.parseInt(id_update);
			System.out.print("le nom ? ");
			String nomUpdate = sc.nextLine(); 
			System.out.print("le prenom ? ");
			String prenomUpdate = sc.nextLine();
			obj.put("demandType",String.valueOf("UPDATE"));
			obj.put("nom",String.valueOf(nomUpdate));
			obj.put("prenom",String.valueOf(prenomUpdate));
			obj.put("Id",id_user_update);
			System.out.println(obj);
			JSONObject reponseUdpade = client.sendMessage(obj);
			String repServerUpdate = (String) reponseUdpade.get("reponse"); 
			if(repServerUpdate.contentEquals("mise à jour reussie")) {
				String prenomUpdate2 = (String) reponseUdpade.get("prenom");  
				String nomupdate2 = (String) reponseUdpade.get("nom");
				long idCaste = (long) reponseUdpade.get("Id");
				int idUpdate = (int) idCaste;
				System.out.println(repServerUpdate +"\n voici les donnees mises a jour: \n" + prenomUpdate2 + "\n " + nomupdate2  + "\n" + idUpdate);
			}
			else {
				System.out.println(repServerUpdate);
			}
			client.stopConnection();

			break; 


		}

	}
}

}
	

