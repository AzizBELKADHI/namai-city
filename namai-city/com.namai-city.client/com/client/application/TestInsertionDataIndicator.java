package com.client.application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.json.simple.JSONObject;

import com.client.controller.SocketClient;
import com.commons.model.AccessServer;

public class TestInsertionDataIndicator {
	private SocketClient client = new SocketClient();
	public static Connection c; 
	private static String URL = "jdbc:postgresql://172.31.249.44:5432/NamaiDB";
	private static String login = "toto" ;
	private static String password = "toto";

	public static void main (String[] args) {
		TestInsertionDataIndicator t = new TestInsertionDataIndicator();
		try {
			t.go();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void go() throws SQLException, IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		while (true) { // Menu display
			System.out.println("########################### Menu Namai-city-client #########################");
			System.out.println("1: insérer des données dans la table Frequentation_Voiture");
			System.out.println("2: insérer des données dans la table station"); 
			System.out.println("3: insérer des données dans la table capteur_polluant");  
			System.out.println("4: insérer des données dans la table historique_capteurpol");  
			System.out.println("5 : insérer des données dans la table bornes"); 
			System.out.println("6 : insérer des données dans la table capteur_vehicule");
			System.out.println("########################### Menu Namai-city-client #########################");

			client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
			JSONObject obj = new JSONObject(); // JSONObject creation
			String rep = sc.nextLine();

			switch (rep) {
			case "1":
				System.out.println("########################### INSERT CAR #########################");
				obj.put("demandType", String.valueOf("MOCK_CAR_INSERT"));

				JSONObject reponseInsertCar = client.sendMessage2(obj);
				String repServerCar = (String) reponseInsertCar.get("reponse");
				System.out.println(repServerCar);

				client.stopConnection();
				break;

			case "2":
				System.out.println("########################### INSERT STATION #########################");
				obj.put("demandType", String.valueOf("MOCK_STATION_INSERT"));

				JSONObject reponseInsertStation = client.sendMessage2(obj);
				String repServerStation = (String) reponseInsertStation.get("reponse");
				System.out.println(repServerStation);

				client.stopConnection();
				break;

			case "3":
				System.out.println("########################### INSERT SENSOR POLLUANT #########################");
				obj.put("demandType", String.valueOf("MOCK_SENSOR_POLLUANT_INSERT"));
				JSONObject reponseInsertSensorPolluant = client.sendMessage2(obj);
				String repServerPolluant = (String) reponseInsertSensorPolluant.get("reponse");
				System.out.println(repServerPolluant);

				client.stopConnection();
				break;

			case "4":
				System.out.println("########################### INSERT WARNING #########################");
				obj.put("demandType", String.valueOf("MOCK_HISTORICAL_SENSOR_POLLUANT_INSERT"));
				JSONObject reponseInsertHistoricalSensorPolluant = client.sendMessage2(obj);
				String repServerHistoricalPolluant = (String) reponseInsertHistoricalSensorPolluant.get("reponse");
				System.out.println(repServerHistoricalPolluant);

				client.stopConnection();
				break;


				// MOCK INSERT DATA OF BORNE
			case "5":
				System.out.println("########################### INSERT BORNE #########################");
				obj.put("demandType", String.valueOf("MOCK_BORNE_INSERT"));

				JSONObject reponseInsertBorne = client.sendMessage2(obj);
				String repServerBorne = (String) reponseInsertBorne.get("reponse");
				System.out.println(repServerBorne);

				client.stopConnection();
				break;

				// MOCK INSERT SENSOR CAR 

			case "6":
				System.out.println("########################### INSERT SENSOR CAR #########################");
				obj.put("demandType", String.valueOf("MOCK_SENSOR_CAR_INSERT"));

				JSONObject reponseInsertSensorCar = client.sendMessage2(obj);
				String repServerSensorCar = (String) reponseInsertSensorCar.get("reponse");
				System.out.println(repServerSensorCar);

				client.stopConnection();
				break;

			default:
				System.out.println("Unrocognized command");
				break;

			}

		}
	}
}
