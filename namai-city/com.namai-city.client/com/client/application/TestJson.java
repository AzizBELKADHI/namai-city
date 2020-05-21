
package com.client.application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;

import com.client.controller.SocketClient;
import com.commons.model.AccessServer;

import indicator.CarIndicator;
import indicator.SensorIndicator;
import indicator.SensorPolluantIndicator;
import indicator.StationIndicator;


public class TestJson {
	private SocketClient client = new SocketClient();
	public static Connection c;
	private static String URL = "jdbc:postgresql://172.31.249.44:5432/NamaiDB";
	private static String login = "toto";
	private static String password = "toto";

	public static Connection createConnection() throws SQLException {
		try {

			return DriverManager.getConnection(URL, login, password);
		} catch (SQLException e) {
			throw new SQLException("Can't create connection", e);
		}

	}

	public static void main(String[] args) {
		TestJson t = new TestJson();
		try {
			t.go();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//method for recover the number of pollutant sensors in the city
	public ArrayList<SensorIndicator> goSensor() throws SQLException, IOException {
		client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
		JSONObject obj = new JSONObject();

		obj.put("demandType", "SENSOR_INDICATOR3");
		System.out.println("récupération du nombre de capteurs polluant par position");

		System.out.println(obj);
		JSONObject reponseCapPolluant = client.sendMessage2(obj);
		System.out.println("affichage rep : " + reponseCapPolluant);
		ArrayList<JSONObject> allPolluant = new ArrayList<JSONObject>();// Creation of table SensorIndicator
		allPolluant = (ArrayList<JSONObject>) reponseCapPolluant.get("sensorPolluant");
		
		int nbPolluant = 0;
		ArrayList<SensorIndicator> liste = new ArrayList<SensorIndicator>();
		for (int i = 0; i < allPolluant.size(); i++) { // Creating a loop to display all sensors in the table sensors
			SensorIndicator s = new SensorIndicator();
			s.convertFromJson(allPolluant.get(i));
			// add each sensor in list 
			liste.add(s);
			System.out.println(" position: " + s.getLocalisation() + " | nombre de capteurs en ville : "
					+ s.getSensorPolluantNb());

			nbPolluant += s.getSensorPolluantNb();
		}
		System.out.println("==============> Nb total : " + nbPolluant);

		client.stopConnection();
		return liste;
	}
//method for recover the number of terminals in the city as a list
		public ArrayList<SensorIndicator> goBorne() throws SQLException, IOException {
		client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
		JSONObject obj = new JSONObject(); // JSONObject creation
		obj.put("demandType", "SENSOR_INDICATOR2");
		System.out.println(obj);
		JSONObject reponseBorne = client.sendMessage2(obj);
		System.out.println("affichage rep : " + reponseBorne);
		ArrayList<JSONObject> allBornes = new ArrayList<JSONObject>();// Creation of table in type SensorIndicator
		allBornes = (ArrayList<JSONObject>) reponseBorne.get("bornes");
		int nbBornes = 0;
		ArrayList<SensorIndicator> liste = new ArrayList<SensorIndicator>();
		for (int i = 0; i < allBornes.size(); i++) { // Creating a loop to display all sensors in the table sensors
			SensorIndicator s = new SensorIndicator();
			s.convertFromJson(allBornes.get(i));
			liste.add(s);
			System.out.println(
					" position: " + s.getLocalisation() + " | nombre de capteurs en ville : " + s.getBorneNb());

			nbBornes += s.getBorneNb();
		}
		System.out.println("==============> Nb total : " + nbBornes);

		client.stopConnection();
		return liste;
	}

		// method for recover the number of car in the city
	public ArrayList<CarIndicator> goCar() throws SQLException, IOException {
		client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
		JSONObject obj = new JSONObject(); // JSONObject creation
		obj.put("demandType", "CAR_INDICATOR");

		System.out.println("récupération du nombre de voitures par la date");

		System.out.println(obj);

		JSONObject reponseAll1 = client.sendMessage2(obj);
		System.out.println("affichage rep : " + reponseAll1);
		ArrayList<JSONObject> allCars = new ArrayList<JSONObject>();// Creation d'un tableau de type CarIndicator
		allCars = (ArrayList<JSONObject>) reponseAll1.get("cars");
		ArrayList<CarIndicator> liste = new ArrayList<CarIndicator>();
		int nbCars = 0;
		for (int i = 0; i < allCars.size(); i++) { // Creating a loop to display all sensors in the table frequentation_Voiture
			CarIndicator s = new CarIndicator();
			s.convertFromJson(allCars.get(i));
			liste.add(s);
			System.out.println(" | nombre de voitures: " + s.getCarsNb() + " | date: " + s.getDate());
			nbCars += s.getCarsNb();

		}
		System.out.println("==============> Nb total : " + nbCars);
		client.stopConnection();
		return liste;
	}

	
	// method for recover the number of car's sensor in a list 
	public ArrayList<SensorIndicator> goSensorCar() throws SQLException, IOException {
		client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
		JSONObject obj = new JSONObject(); // JSONObject creation
		obj.put("demandType", "SENSOR_INDICATOR4");
		System.out.println("récupération du nombre de capteur vehicules par position");
		System.out.println(obj);
		JSONObject reponseSensorCar = client.sendMessage2(obj);
		System.out.println("affichage rep : " + reponseSensorCar);
		ArrayList<JSONObject> allSensorCar = new ArrayList<JSONObject>();// Creation of table in type sensorIndicator 
		// SensorIndicator
		allSensorCar = (ArrayList<JSONObject>) reponseSensorCar.get("sensorCar");
		int nbSensorCar = 0;
		ArrayList<SensorIndicator> liste = new ArrayList<SensorIndicator>();
		for (int i = 0; i < allSensorCar.size(); i++) { // Creating a loop to display all sensors in the table sensors
			SensorIndicator s = new SensorIndicator();
			s.convertFromJson(allSensorCar.get(i));
			liste.add(s);
			System.out.println(" position: " + s.getPositionSensorCar() + " | nombre de capteur polluant en ville : "
					+ s.getSensorCarNb());

			nbSensorCar += s.getSensorCarNb();
		}
		System.out.println("==============> Nb total : " + nbSensorCar);

		client.stopConnection();
		return liste;
	}
// method for recover the number of station in the city in a list 
	public ArrayList<StationIndicator> goStation() throws SQLException, IOException {
		client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
		JSONObject obj = new JSONObject(); // JSONObject creation

		obj.put("demandType", "STATION_INDICATOR");
		System.out.println("récupération du nombre de station par zone ");
		System.out.println(obj);
		JSONObject reponseStation = client.sendMessage2(obj);
		System.out.println("affichage rep : " + reponseStation);
		ArrayList<JSONObject> allStations = new ArrayList<JSONObject>();// Creation d'un tableau de type
		// StationIndicator
		allStations = (ArrayList<JSONObject>) reponseStation.get("stations");
		int nbTotalStation = 0;
		ArrayList<StationIndicator> liste = new ArrayList<StationIndicator>();
		for (int i = 0; i < allStations.size(); i++) { // Creating a loop to display all sensors in the table
			StationIndicator s = new StationIndicator();
			s.convertFromJson(allStations.get(i));
			liste.add(s);
			System.out.println(" | position : " + s.getPosition() + " | nombre de stations par zone dans la ville : "
					+ s.getStationNb());
			nbTotalStation += s.getStationNb();
		}
		System.out.println("==============> Nb total : " + nbTotalStation);
		client.stopConnection();
		return liste;
	}

	
	//recovery of each id's warning for insert them in a comboBox
	public ArrayList<Integer> getIdSensorPolluant() throws IOException, SQLException {
		client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
		JSONObject obj = new JSONObject(); // JSONObject creation
		obj.put("demandType", "getIdSensorPolluant");

		System.out.println(obj);
		JSONObject reponseIdPolluant = client.sendMessage2(obj);
		System.out.println("affichage rep : " + reponseIdPolluant);
		ArrayList<JSONObject> allIdPolluant = new ArrayList<JSONObject>();// Creation d'un tableau de typSensorIndicator
		allIdPolluant = (ArrayList<JSONObject>) reponseIdPolluant.get("sensorsIdPolluant");
		// System.out.println(allSensors.size());
		ArrayList<Integer> liste = new ArrayList<Integer>();
		for (int i = 0; i < allIdPolluant.size(); i++) { // Creating a loop to display all sensors in the table sensors
			SensorPolluantIndicator s = new SensorPolluantIndicator();
			s.convertFromJson(allIdPolluant.get(i));
			liste.add(s.getId2());
			System.out.println("id: " + s.getId2());
		}

		client.stopConnection();
		return liste;
	}

	//recovery of the threshold of each sensor for each pollutant (CO2 / NO2 / PF/ TMP) 
	public List<Integer> getThreshold(String polluant, int idCapteur) throws IOException {
		client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
		JSONObject obj = new JSONObject();

		System.out.println("quel est le polluant concerné : ");
		obj.put("nomPolluant", polluant);
		obj.put("demandType", String.valueOf("getThresholdSensorPolluant"));

		switch (polluant) {
		case "CO2":

			obj.put("Id", idCapteur);
			System.out.println(obj);

			JSONObject reponseThresholdPolluant = client.sendMessage2(obj);
			System.out.println("affichage rep : " + reponseThresholdPolluant);
			ArrayList<JSONObject> allThreshold = new ArrayList<JSONObject>();// Creation d'un tableau de type
			// SensorIndicator
			allThreshold = (ArrayList<JSONObject>) reponseThresholdPolluant.get("sensorsPolluantCo2");
			if (allThreshold == null) {
				System.out.println("retourner liste vide");
				client.stopConnection();
				return Arrays.asList(-1);

			} else {
				// Creating a loop to display all sensors in the table sensors
				SensorPolluantIndicator s = new SensorPolluantIndicator();
				s.convertFromJson(allThreshold.get(0));
				System.out.println("seuil_co2 : " + s.getCo2());
				client.stopConnection();
				return Arrays.asList(Integer.valueOf(s.getCo2()));
			}
		case "NO2":

			obj.put("Id", Integer.valueOf(idCapteur));
			System.out.println(obj);

			JSONObject reponseThresholdNo2 = client.sendMessage2(obj);
			System.out.println("affichage rep : " + reponseThresholdNo2);
			ArrayList<JSONObject> allThresholdNO2 = new ArrayList<JSONObject>();// Creation d'un tableau de type
			// SensorIndicator
			allThresholdNO2 = (ArrayList<JSONObject>) reponseThresholdNo2.get("sensorsPolluantNo2");
			if (allThresholdNO2 == null) {
				System.out.println("retourner liste vide");
				client.stopConnection();
				return Arrays.asList(-1);

			} else {
				SensorPolluantIndicator s = new SensorPolluantIndicator();
				s.convertFromJson(allThresholdNO2.get(0));
				System.out.println("seuil_co2 : " + s.getNo2());
				client.stopConnection();
				return Arrays.asList(Integer.valueOf(s.getNo2()));
			}

		case "PF":

			obj.put("Id", Integer.valueOf(idCapteur));
			System.out.println(obj);

			JSONObject reponseThresholdPf = client.sendMessage2(obj);
			System.out.println("affichage rep : " + reponseThresholdPf);
			ArrayList<JSONObject> allThresholdPf = new ArrayList<JSONObject>();// Creation d'un tableau de type
			// SensorIndicator
			allThresholdPf = (ArrayList<JSONObject>) reponseThresholdPf.get("sensorsPolluantPf");
			if (allThresholdPf == null) {
				System.out.println("retourner liste vide");
				client.stopConnection();
				return Arrays.asList(-1);

			} else {
				SensorPolluantIndicator s = new SensorPolluantIndicator();
				s.convertFromJson(allThresholdPf.get(0));
				System.out.println("seuil_pf : " + s.getPf());
				client.stopConnection();
				return Arrays.asList(Integer.valueOf(s.getPf()));
			}
		case "TMP":

			obj.put("Id", Integer.valueOf(idCapteur));
			System.out.println(obj);

			JSONObject reponseThresholdTmp = client.sendMessage2(obj);
			System.out.println("affichage rep : " + reponseThresholdTmp);
			ArrayList<JSONObject> allThresholdTmp = new ArrayList<JSONObject>();// Creation d'un tableau de type
			// SensorIndicator
			allThresholdTmp = (ArrayList<JSONObject>) reponseThresholdTmp.get("sensorsPolluantTmp");
			if (allThresholdTmp == null) {
				client.stopConnection();
				return Arrays.asList(-1);

			} else {
				SensorPolluantIndicator s = new SensorPolluantIndicator();
				s.convertFromJson(allThresholdTmp.get(0));
				System.out.println(
						"temperature minimum  : " + s.getTmpMin() + "| temperature maximum : " + s.getTmpMax());
				client.stopConnection();
				return Arrays.asList(Integer.valueOf(s.getTmpMin()), Integer.valueOf(s.getTmpMax()));
			}
		default:

			System.out.println("Unrocognized command");
			return Arrays.asList(-1);

		}
	}

	// recovery of each warning 
	public ArrayList<SensorPolluantIndicator> getWarning(String nomPolluant, Integer idCapteur,
			List<Integer> listeSeuil) throws IOException {

		client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
		JSONObject obj = new JSONObject(); // JSONObject creation

		obj.put("demandType", "getWarningPolluant");
		obj.put("fk_id_capteur", idCapteur);

		System.out.println(obj);
		JSONObject reponseWarning = client.sendMessage2(obj);
		System.out.println("affichage rep : " + reponseWarning);
		ArrayList<JSONObject> allWarningPolluant = new ArrayList<JSONObject>();// Creation of table in type SensorPolluantIndicator
		allWarningPolluant = (ArrayList<JSONObject>) reponseWarning.get("sensorsPolluantWarning");
		ArrayList<SensorPolluantIndicator> liste = new ArrayList<SensorPolluantIndicator>();
		ArrayList<SensorPolluantIndicator> listeResultat = new ArrayList<SensorPolluantIndicator>();
		// System.out.println(allSensors.size());

		for (int i = 0; i < allWarningPolluant.size(); i++) { // Creating a loop to display all sensors in the table sensors
			SensorPolluantIndicator s = new SensorPolluantIndicator();
			s.convertFromJson(allWarningPolluant.get(i));
			liste.add(s);
			System.out.println("Id : " + s.getId2() + "| val_co2 : " + s.getCo2() + "| val_no2 : " + s.getNo2()
			+ "| val_pf : " + s.getPf() + "| val_tmp : " + s.getTmp());

		}
		client.stopConnection();
		switch (nomPolluant) {
		case "CO2":
			int seuilCO2 = listeSeuil.get(0);
			for (int i = 0; i < liste.size(); i++) {
				if (Integer.valueOf(liste.get(i).getCo2()) > seuilCO2) {
					listeResultat.add(liste.get(i));
				}
			}
			break;

		case "NO2":
			int seuilNO2 = listeSeuil.get(0);
			for (int i = 0; i < liste.size(); i++) {
				if (Integer.valueOf(liste.get(i).getNo2()) > seuilNO2) {
					listeResultat.add(liste.get(i));
				}
			}
			break;

		case "PF":
			int seuilPF = listeSeuil.get(0);
			for (int i = 0; i < liste.size(); i++) {
				if (Integer.valueOf(liste.get(i).getPf()) > seuilPF) {
					listeResultat.add(liste.get(i));
				}
			}
			break;

		case "TMP":
			int seuilTempMin = listeSeuil.get(0);
			int seuilTempMax = listeSeuil.get(1);
			for (int i = 0; i < liste.size(); i++) {
				if (Integer.valueOf(liste.get(i).getPf()) > seuilTempMax
						|| Integer.valueOf(liste.get(i).getPf()) < seuilTempMin) {
					listeResultat.add(liste.get(i));
				}
			}
			break;

		default:

			break;
		}
		return listeResultat;
	}

	public void go() throws SQLException, IOException {

		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		while (true) { // Menu display
			System.out.println("########################### Menu Namai-city-client #########################");
			System.out.println("1: Afficher");
			System.out.println("2: Créer");
			System.out.println("3: Mettre à jour");
			System.out.println("4: Supprimer");
			System.out.println("5: Exit");
			System.out.println("6: Tentative de connexion à la BDD depuis le client ");
			System.out.println("########################### Menu Namai-city-client #########################");

			client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
			JSONObject obj = new JSONObject(); // JSONObject creation
			String rep = sc.nextLine();

			switch (rep) {
			case "1":
				System.out.println("########################### Menu Namai-city-client #########################");
				System.out.println("1: Afficher tout les utilisateurs");
				System.out.println("2: afficher un utilisateur en particulier");
				System.out.println("########################### Menu Namai-city-client #########################");
				Scanner choice = new Scanner(System.in);
				rep = choice.nextLine();
				switch (rep) {
				case "1":
					System.out.println("########################### SELECT #########################");
					obj.put("demandType", String.valueOf("SELECT"));
					obj.put("Id", Integer.valueOf(0));
					System.out.println(obj);
					JSONObject reponseAll = client.sendMessage(obj);
					ArrayList<JSONObject> allUsers = new ArrayList<JSONObject>();
					allUsers = (ArrayList<JSONObject>) reponseAll.get("users");
					for (int i = 0; i < allUsers.size(); i++) {
						System.out.println("id: " + allUsers.get(i).get("Id") + " | nom: " + allUsers.get(i).get("nom")
								+ " | prenom: " + allUsers.get(i).get("prenom"));
					}
					client.stopConnection();
					break;

				case "2":
					choice = new Scanner(System.in);
					System.out.println("########################### SELECT #########################");
					System.out.println("quel est l'id de l'utilisateur à afficher ? ");
					int repSelect = 0;
					try {
						repSelect = choice.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("probleme de saisi de l'id");
						break;
					}
					obj.put("demandType", String.valueOf("SELECT"));
					obj.put("Id", Integer.valueOf(repSelect));
					System.out.println(obj);
					JSONObject reponse = client.sendMessage(obj);
					if (reponse.containsKey("reponse")) {
						System.out.println(reponse.get("reponse"));
					} else {
						String name = (String) reponse.get("nom");
						String prenom = (String) reponse.get("prenom");
						long idCaste = (long) reponse.get("Id");
						int id = (int) idCaste;
						System.out.println("voici les informations de l'utilisateur: \n" + name + "\n" + prenom + "\n "
								+ id + "\n");
					}
					client.stopConnection();
					break;
				}
				break;

			case "2":
				// request insertion user in table users
				System.out.println("########################### INSERT #########################");
				System.out.println("saisissez les informations de l'utilisateur:");
				System.out.println("nom:");
				String nom = sc.nextLine();
				System.out.println("prénom:");
				String prenom = sc.nextLine();
				obj.put("demandType", String.valueOf("INSERT"));
				obj.put("nom", String.valueOf(nom));
				obj.put("prenom", String.valueOf(prenom));
				System.out.println(obj);
				JSONObject reponse = client.sendMessage(obj);
				String repServer = (String) reponse.get("reponse");
				if (repServer.equals("insertion réussi")) {
					String prenomInsert = (String) reponse.get("prenom");
					String nomInsert = (String) reponse.get("nom");
					System.out.println(repServer + "\n voici les informations insere: \n" + prenomInsert + "\n "
							+ nomInsert + "\n");
				} else {
					System.out.println(repServer + "\n");
				}
				client.stopConnection();
				break;

			case "3":
				// request for update one user 
				System.out.println("########################### UPDATE #########################");
				System.out.println("quel est l'id à modifier?");

				String id_update = sc.nextLine();
				Integer id_user_update = Integer.parseInt(id_update);
				System.out.print("le nom ? ");
				String nomUpdate = sc.nextLine();
				System.out.print("le prenom ? ");
				String prenomUpdate = sc.nextLine();
				obj.put("demandType", String.valueOf("UPDATE"));
				obj.put("nom", String.valueOf(nomUpdate));
				obj.put("prenom", String.valueOf(prenomUpdate));
				obj.put("Id", id_user_update);
				System.out.println(obj);
				JSONObject reponseUdpade = client.sendMessage(obj);
				String repServerUpdate = (String) reponseUdpade.get("reponse");
				if (repServerUpdate.contentEquals("mise à jour reussie")) {
					String prenomUpdate2 = (String) reponseUdpade.get("prenom");
					String nomupdate2 = (String) reponseUdpade.get("nom");
					long idCaste = (long) reponseUdpade.get("Id");
					int idUpdate = (int) idCaste;
					System.out.println(repServerUpdate + "\n voici les donnees mises a jour: \n" + prenomUpdate2 + "\n "
							+ nomupdate2 + "\n" + idUpdate);
				} else {
					System.out.println(repServerUpdate);
				}
				client.stopConnection();

				break;

			case "4":
				// request for delete a user 
				System.out.println("########################### DELETE  #########################");
				System.out.println("quel est l'id de l'utilisateur à supprimer ?");
				String id_delete = sc.nextLine();
				Integer id_user_delete = Integer.parseInt(id_delete);

				obj.put("demandType", String.valueOf("DELETE"));

				obj.put("Id", String.valueOf(id_user_delete));
				System.out.println(obj);
				JSONObject reponseDelete = client.sendMessage(obj);
				String repServerDelete = (String) reponseDelete.get("reponse");

				if (repServerDelete.equals("suppression réussie")) {
					long idCasteDelete = (long) reponseDelete.get("Id");
					int idDelete = (int) idCasteDelete;
					System.out.println(repServerDelete + "\n Voici l'id de le l'utilisateur à supprimer : " + idDelete);
				} else {
					System.out.println(repServerDelete);
				}
				client.stopConnection();

				break;

			case "5":
				// exit and stop connection 
				System.out.println("########################### EXIT #########################");
				System.out.println("Merci de votre visite, A bientot!");
				client.stopConnection();
				System.exit(0);
				break;

			case "6":
				// to show that the user don't access to VM BDD
				c = createConnection();
				System.out.println("nom:");
				String nomBDD = sc.nextLine();
				System.out.println("prénom:");
				String prenomBDD = sc.nextLine();

				PreparedStatement stmt3 = c.prepareStatement("insert into utilisateur(nom,prenom) values (?,?);");
				stmt3.setString(1, nomBDD);
				stmt3.setString(2, prenomBDD);
				stmt3.execute();
				break;
				
			default:
				System.out.println("Unrocognized command");
				break;

			}

		}
	}
}
