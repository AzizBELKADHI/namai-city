package com.client.application;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.json.simple.JSONObject;

import com.client.controller.SocketClient;
import com.commons.model.AccessServer;

import indicator.CarIndicator;
import indicator.SensorIndicator;
import indicator.SensorPolluantIndicator;
import indicator.StationIndicator;

public class TestIndicator {
	private SocketClient client = new SocketClient();
	public static Connection c; 
	private static String URL = "jdbc:postgresql://172.31.249.44:5432/NamaiDB";
	private static String login = "toto" ;
	private static String password = "toto";

	public static void main (String[] args) {
		TestIndicator t = new TestIndicator();
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
			System.out.println("1: récupération de l'indicateur du nombre de voitures et la date dans la ville ");
			System.out.println("2: récupération de l'indicateur du nombre de stations");
			System.out.println("3: récupération de l'indicateur du du nombre d'alertes");
			System.out.println("4: récupération de l'id des capteurs ayant déclenché une alerte");
			System.out.println("5: récupération du seuil de chaque polluant");
			System.out.println("6: récupération des données de chaque alerte"); 
			System.out.println("7 : récupération du nombre de bornes dans la ville"); 
			System.out.println("8 : récupération du nombre de capteurs polluants dans la ville");
			System.out.println("9 : récupération du nombre de capteurs véhicule dans la ville");
			System.out.println("########################### Menu Namai-city-client #########################");

			client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
			JSONObject obj=new JSONObject();

			String rep = sc.nextLine();

			switch (rep) {

			case "1":
				// number of cars in city 
				System.out.println("########################### CAR INDICATOR #########################");

				obj.put("demandType", "CAR_INDICATOR");
				System.out.println("récupération du nombre de voitures par la date");

				System.out.println(obj);

				JSONObject reponseAll1 = client.sendMessage2(obj);
				System.out.println("affichage rep : " + reponseAll1);
				ArrayList<JSONObject> allCars = new ArrayList<JSONObject>();// Creation d'un tableau de type
				// CarIndicator
				allCars = (ArrayList<JSONObject>) reponseAll1.get("cars");
				int nbCars = 0;
				for (int i = 0; i < allCars.size(); i++) { // Creating a loop to display all sensors in the table
					// Frequentation_Voiture
					CarIndicator s = new CarIndicator();
					s.convertFromJson(allCars.get(i));
					System.out.println(" | nombre de voitures: " + s.getCarsNb() + " | date: " + s.getDate());
					nbCars += s.getCarsNb();

				}
				System.out.println("==============> Nb total : " + nbCars);
				client.stopConnection();
				break;


			case "2":
				// number of stations in city 
				System.out.println("########################### STATION INDICATOR #########################");

				obj.put("demandType", "STATION_INDICATOR");
				System.out.println("récupération du nombre de station par zone ");
				System.out.println(obj);
				JSONObject reponseStation = client.sendMessage2(obj);
				System.out.println("affichage rep : " + reponseStation);
				ArrayList<JSONObject> allStations = new ArrayList<JSONObject>();// Creation d'un tableau de type
				// StationIndicator
				allStations = (ArrayList<JSONObject>) reponseStation.get("stations");
				int nbTotalStation = 0;
				for (int i = 0; i < allStations.size(); i++) { // Creating a loop to display all sensors in the table
					// historique_Alerte
					StationIndicator s = new StationIndicator();
					s.convertFromJson(allStations.get(i));
					System.out.println(" | position : " + s.getPosition()
					+ " | nombre de stations par zone dans la ville : " + s.getStationNb());
					nbTotalStation += s.getStationNb();
				}
				System.out.println("==============> Nb total : " + nbTotalStation);
				client.stopConnection();

				break;

			case "3":
				// number of warning in city 
				System.out.println("########################### SENSOR  POLLUANT INDICATOR #########################");
				obj.put("demandType", "SENSOR_POLLUANT_INDICATOR");

				System.out.println(obj);
				JSONObject reponseSensorPolluant = client.sendMessage2(obj);
				System.out.println("affichage rep : " + reponseSensorPolluant);
				ArrayList<JSONObject> allSensorsPolluant = new ArrayList<JSONObject>();// Creation d'un tableau de type
				// SensorIndicator
				allSensorsPolluant = (ArrayList<JSONObject>) reponseSensorPolluant.get("sensorsPolluant");
				// System.out.println(allSensors.size());
				int nbTotal2 = 0;
				for (int i = 0; i < allSensorsPolluant.size(); i++) { // Creating a loop to display all sensors in the
					// table sensors
					SensorPolluantIndicator s = new SensorPolluantIndicator();
					s.convertFromJson(allSensorsPolluant.get(i));
					System.out.println("localisation: " + s.getLocalisation() + 
							" | nombre d'alertes en ville : " + s.getWarningNb()); 

					nbTotal2 += s.getWarningNb();
				}
				System.out.println("==============> Nb total : " + nbTotal2);

				client.stopConnection();

				break;

			case "4":
				// number of id of sensor polluant 
				System.out.println("########################### SENSOR  POLLUANT INDICATOR #########################");
				obj.put("demandType", "getIdSensorPolluant");

				System.out.println(obj);
				JSONObject reponseIdPolluant = client.sendMessage2(obj);
				System.out.println("affichage rep : " + reponseIdPolluant);
				ArrayList<JSONObject> allIdPolluant = new ArrayList<JSONObject>();// Creation d'un tableau de type
				// SensorIndicator
				allIdPolluant = (ArrayList<JSONObject>) reponseIdPolluant.get("sensorsIdPolluant");
				// System.out.println(allSensors.size());

				for (int i = 0; i < allIdPolluant.size(); i++) { // Creating a loop to display all sensors in the table
					// sensors
					SensorPolluantIndicator s = new SensorPolluantIndicator();
					s.convertFromJson(allIdPolluant.get(i));
					System.out.println("id: " + s.getId2());
				}

				client.stopConnection();

				break;

			case "5":
				// recovery of threshold for each polluant 
				System.out.println("########################### SEUIL CAPTEUR POLLUANT #########################");
				System.out.println("quel est le polluant concerné : ");
				Scanner polluantChoice = new Scanner(System.in);
				rep = polluantChoice.nextLine();

				obj.put("nomPolluant", String.valueOf(rep));
				obj.put("demandType", String.valueOf("getThresholdSensorPolluant"));

				switch (rep) {
				case "CO2":
					System.out.println("quel est l'id du capteur polluant concerné ?");
					int repSelect = 0;
					try {
						repSelect = polluantChoice.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("probleme de saisi de l'id");
						break;
					}

					obj.put("Id", Integer.valueOf(repSelect));
					System.out.println(obj);

					JSONObject reponseThresholdPolluant = client.sendMessage2(obj);
					System.out.println("affichage rep : " + reponseThresholdPolluant);
					ArrayList<JSONObject> allThreshold = new ArrayList<JSONObject>();// Creation d'un tableau de type
					// SensorIndicator
					allThreshold = (ArrayList<JSONObject>) reponseThresholdPolluant.get("sensorsPolluantCo2");
					if (allThreshold == null) {
						System.out.println("retourner liste vide");
						client.stopConnection();

					} else {
						for (int i = 0; i < allThreshold.size(); i++) { // Creating a loop to display all sensors in the
							// table sensors
							SensorPolluantIndicator s = new SensorPolluantIndicator();
							s.convertFromJson(allThreshold.get(i));
							System.out.println("seuil_co2 : " + s.getCo2());
						}
					}

					client.stopConnection();

					break;

				case "NO2":
					System.out.println("quel est l'id du capteur polluant concerné ?");
					int repNo2 = 0;
					try {
						repNo2 = polluantChoice.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("probleme de saisi de l'id");
						break;
					}

					obj.put("Id", Integer.valueOf(repNo2));
					System.out.println(obj);

					JSONObject reponseThresholdNo2 = client.sendMessage2(obj);
					System.out.println("affichage rep : " + reponseThresholdNo2);
					ArrayList<JSONObject> allThresholdNO2 = new ArrayList<JSONObject>();// Creation d'un tableau de type
					// SensorIndicator
					allThresholdNO2 = (ArrayList<JSONObject>) reponseThresholdNo2.get("sensorsPolluantNo2");
					if (allThresholdNO2 == null) {
						System.out.println("retourner liste vide");
						client.stopConnection();

					} else {
						for (int i = 0; i < allThresholdNO2.size(); i++) { // Creating a loop to display all sensors in
							// the table sensors
							SensorPolluantIndicator s = new SensorPolluantIndicator();
							s.convertFromJson(allThresholdNO2.get(i));
							System.out.println("seuil_co2 : " + s.getNo2());
						}
					}

					client.stopConnection();
					break;

				case "PF":
					System.out.println("quel est l'id du capteur polluant concerné ?");
					int repPF = 0;
					try {
						repPF = polluantChoice.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("probleme de saisi de l'id");
						break;
					}

					obj.put("Id", Integer.valueOf(repPF));
					System.out.println(obj);

					JSONObject reponseThresholdPf = client.sendMessage2(obj);
					System.out.println("affichage rep : " + reponseThresholdPf);
					ArrayList<JSONObject> allThresholdPf = new ArrayList<JSONObject>();// Creation d'un tableau de type
					// SensorIndicator
					allThresholdPf = (ArrayList<JSONObject>) reponseThresholdPf.get("sensorsPolluantPf");
					if (allThresholdPf == null) {
						System.out.println("retourner liste vide");
						client.stopConnection();

					} else {
						for (int i = 0; i < allThresholdPf.size(); i++) { // Creating a loop to display all sensors in
							// the table sensors
							SensorPolluantIndicator s = new SensorPolluantIndicator();
							s.convertFromJson(allThresholdPf.get(i));
							System.out.println("seuil_pf : " + s.getPf());
						}
					}

					client.stopConnection();

					break;

				case "TMP":
					System.out.println("quel est l'id du capteur polluant concerné ?");
					int repTmp = 0;
					try {
						repTmp = polluantChoice.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("probleme de saisi de l'id");
						break;
					}

					obj.put("Id", Integer.valueOf(repTmp));
					System.out.println(obj);

					JSONObject reponseThresholdTmp = client.sendMessage2(obj);
					System.out.println("affichage rep : " + reponseThresholdTmp);
					ArrayList<JSONObject> allThresholdTmp = new ArrayList<JSONObject>();// Creation d'un tableau de type
					// SensorIndicator
					allThresholdTmp = (ArrayList<JSONObject>) reponseThresholdTmp.get("sensorsPolluantTmp");
					if (allThresholdTmp == null) {
						System.out.println("retourner liste vide");
						client.stopConnection();

					} else {
						for (int i = 0; i < allThresholdTmp.size(); i++) { // Creating a loop to display all sensors in
							// the table sensors
							SensorPolluantIndicator s = new SensorPolluantIndicator();
							s.convertFromJson(allThresholdTmp.get(i));
							System.out.println("temperature minimum  : " + s.getTmpMin() + "| temperature maximum : "
									+ s.getTmpMax());
						}

					}
					client.stopConnection();
					break;
				}
				break;

			case "6":
				// recovery of the data of warning for one sensor 
				System.out.println("########################### ALERTE  INDICATOR #########################");
				System.out.println("quel est l'id du capteur polluant concerné : ");
				String idfk = sc.nextLine();
				Integer PolluantAlerte = Integer.parseInt(idfk);

				obj.put("demandType", "getWarningPolluant");
				obj.put("fk_id_capteur", Integer.valueOf(PolluantAlerte));

				System.out.println(obj);
				JSONObject reponseWarning = client.sendMessage2(obj);
				System.out.println("affichage rep : " + reponseWarning);
				ArrayList<JSONObject> allWarningPolluant = new ArrayList<JSONObject>();// Creation d'un tableau de type
				// SensorIndicator
				allWarningPolluant = (ArrayList<JSONObject>) reponseWarning.get("sensorsPolluantWarning");
				// System.out.println(allSensors.size());

				for (int i = 0; i < allWarningPolluant.size(); i++) { // Creating a loop to display all sensors in the
					// table sensors
					SensorPolluantIndicator s = new SensorPolluantIndicator();
					s.convertFromJson(allWarningPolluant.get(i));
					System.out.println("Id : " + s.getId2() + "|date :" + s.getDate() + "| val_co2 : " + s.getCo2()
					+ "| val_no2 : " + s.getNo2() + "| val_pf : " + s.getPf() + "| val_tmp : " + s.getTmp());
				}

				client.stopConnection();

				break;

			case "7":
				// number of terminal in city 
				System.out.println("########################### BORNE INDICATOR #########################");
				obj.put("demandType", "SENSOR_INDICATOR2");
				System.out.println("récupération du nombre de bornes par position");

				System.out.println(obj);
				JSONObject reponseBorne = client.sendMessage2(obj);
				System.out.println("affichage rep : " + reponseBorne);
				ArrayList<JSONObject> allBornes = new ArrayList<JSONObject>();// Creation d'un tableau de type
				// SensorIndicator
				allBornes = (ArrayList<JSONObject>) reponseBorne.get("bornes");
				// System.out.println(allSensors.size());
				int nbBornes = 0;
				for (int i = 0; i < allBornes.size(); i++) { // Creating a loop to display all sensors in the table
					// sensors
					SensorIndicator s = new SensorIndicator();
					s.convertFromJson(allBornes.get(i));
					System.out.println(
							" position: " + s.getLocalisation() + " | nombre de capteurs en ville : " + s.getBorneNb());

					nbBornes += s.getBorneNb();
				}
				System.out.println("==============> Nb total : " + nbBornes);

				client.stopConnection();

				break;

			case "8":
				// number of sensor polluant in city 
				System.out.println("########################### SENSOR  POLLUANT INDICATOR #########################");
				obj.put("demandType", "SENSOR_INDICATOR3");
				System.out.println("récupération du nombre de capteur par zone selon le type et la date");

				System.out.println(obj);
				JSONObject reponseCapPolluant = client.sendMessage2(obj);
				System.out.println("affichage rep : " + reponseCapPolluant);
				ArrayList<JSONObject> allPolluant = new ArrayList<JSONObject>();// Creation d'un tableau de type
				// SensorIndicator
				allPolluant = (ArrayList<JSONObject>) reponseCapPolluant.get("sensorPolluant");
				// System.out.println(allSensors.size());
				int nbPolluant = 0;
				for (int i = 0; i < allPolluant.size(); i++) { // Creating a loop to display all sensors in the table
					// sensors
					SensorIndicator s = new SensorIndicator();
					s.convertFromJson(allPolluant.get(i));
					System.out.println(" position: " + s.getLocalisation() + " | nombre de capteurs en ville : "
							+ s.getSensorPolluantNb());

					nbPolluant += s.getSensorPolluantNb();
				}
				System.out.println("==============> Nb total : " + nbPolluant);

				client.stopConnection();

				break;

// number of sensor car in city 
			case "9":
				System.out.println("########################### SENSOR CAR INDICATOR #########################");
				obj.put("demandType", "SENSOR_INDICATOR4");
				System.out.println("récupération du nombre de capteur vehicules par position");
				System.out.println(obj);
				JSONObject reponseSensorCar = client.sendMessage2(obj);
				System.out.println("affichage rep : " + reponseSensorCar);
				ArrayList<JSONObject> allSensorCar = new ArrayList<JSONObject>();// Creation d'un tableau de type
				// SensorIndicator
				allSensorCar = (ArrayList<JSONObject>) reponseSensorCar.get("sensorCar");
				// System.out.println(allSensors.size());
				int nbSensorCar = 0;
				for (int i = 0; i < allSensorCar.size(); i++) { // Creating a loop to display all sensors in the table
					// sensors
					SensorIndicator s = new SensorIndicator();
					s.convertFromJson(allSensorCar.get(i));
					System.out.println(" position: " + s.getPositionSensorCar()
					+ " | nombre de capteur polluant en ville : " + s.getSensorCarNb());

					nbSensorCar += s.getSensorCarNb();
				}
				System.out.println("==============> Nb total : " + nbSensorCar);

				client.stopConnection();

				break;

			default:
				System.out.println("Unrocognized command");
				break;


			}
		}
	}
}


