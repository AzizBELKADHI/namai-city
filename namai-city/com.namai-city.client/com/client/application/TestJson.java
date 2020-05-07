
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



public class TestJson {
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
	
	public static void main(String [] args) {
		TestJson t = new TestJson();
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

		while(true) { // Menu display
			System.out.println("########################### Menu Namai-city-client #########################");
			System.out.println("1: Afficher");
			System.out.println("2: Créer");
			System.out.println("3: Mettre à jour");
			System.out.println("4: Supprimer");
			System.out.println("5: Exit");
			System.out.println("6: Tentative de connexion à la BDD depuis le client ");
			System.out.println("7: récupération de l'indicateur du nombre de capteurs");
			System.out.println("8: récupération de l'indicateur du nombre de voitures par date dans la ville ");
			System.out.println("9: récupération de l'indicateur du nombre d'alertes");
			System.out.println("10: récupération de l'indicateur du nombre de stations");
			System.out.println("11: récupération de l'indicateur du nombre de personnes par station");
			System.out.println("12: insérer des données dans la table Capteur");
			System.out.println("13: insérer des données dans la table Frequentation_Voiture");
			System.out.println("14: insérer des données dans la table station"); 
			System.out.println("15: insérer des données dans la table Frequentation_station_tram"); 
			System.out.println("16: insérer des données dans la table Historique_alerte"); 

			System.out.println("########################### Menu Namai-city-client #########################");
			
			client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
			JSONObject obj=new JSONObject();  //JSONObject creation
			String rep = sc.nextLine();
			

			switch (rep) {
			case "1":
				System.out.println("########################### Menu Namai-city-client #########################");
				System.out.println("1: Afficher tout les utilisateurs");
				System.out.println("2: afficher un utilisateur en particulier");
				System.out.println("########################### Menu Namai-city-client #########################");	
				Scanner choice = new Scanner(System.in);
				rep = choice.nextLine(); 
				switch(rep) {
				case "1":
					System.out.println("########################### SELECT #########################");
					obj.put("demandType",String.valueOf("SELECT"));
					obj.put("Id",Integer.valueOf(0)); 
					System.out.println(obj);
					JSONObject reponseAll = client.sendMessage(obj);
					ArrayList<JSONObject> allUsers = new ArrayList<JSONObject>();
					allUsers = (ArrayList<JSONObject>) reponseAll.get("users");
					for(int i = 0; i<allUsers.size();i++) {
						System.out.println("id: "+allUsers.get(i).get("Id")+
								" | nom: "+allUsers.get(i).get("nom")+
								" | prenom: "+allUsers.get(i).get("prenom"));
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
					}
					catch(InputMismatchException e){
						System.out.println("probleme de saisi de l'id");
						break;
					}
					obj.put("demandType",String.valueOf("SELECT"));
					obj.put("Id",Integer.valueOf(repSelect)); 
					System.out.println(obj);
					JSONObject reponse = client.sendMessage(obj);
					if(reponse.containsKey("reponse")) {
						System.out.println(reponse.get("reponse"));
					}
					else {					
						String name = (String) reponse.get("nom");  
						String prenom = (String) reponse.get("prenom");  
						long idCaste = (long) reponse.get("Id");
						int id = (int) idCaste;
						System.out.println("voici les informations de l'utilisateur: \n" + name +"\n" + prenom + "\n "+id+ "\n");  
					}
					client.stopConnection();  
					break;
				}
				break;

			case "2":
				// requete insertion dans table utilisateur
				System.out.println("########################### INSERT #########################");
				System.out.println("saisissez les informations de l'utilisateur:");
				System.out.println("nom:");
				String nom = sc.nextLine();
				System.out.println("prénom:");
				String prenom = sc.nextLine();
				obj.put("demandType",String.valueOf("INSERT"));
				obj.put("nom",String.valueOf(nom));
				obj.put("prenom",String.valueOf(prenom));
				System.out.println(obj);
				JSONObject reponse = client.sendMessage(obj);
				String repServer = (String) reponse.get("reponse");  
				if(repServer.equals("insertion réussi")) {
					String prenomInsert = (String) reponse.get("prenom");  
					String nomInsert = (String) reponse.get("nom");
					System.out.println(repServer +"\n voici les informations insere: \n" + prenomInsert + "\n " + nomInsert  + "\n");  
				}
				else {
					System.out.println(repServer +"\n");
				}
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

			case "4" : 
				// crud requete delete de la table en BDD (NamaiDB / toto) 
				System.out.println("########################### DELETE  #########################");
				System.out.println("quel est l'id de l'utilisateur à supprimer ?"); 
				String id_delete = sc.nextLine();
				Integer id_user_delete = Integer.parseInt(id_delete);


				obj.put("demandType",String.valueOf("DELETE"));

				obj.put("Id",String.valueOf(id_user_delete));
				System.out.println(obj);
				JSONObject reponseDelete = client.sendMessage(obj);
				String repServerDelete = (String) reponseDelete.get("reponse");  

				if(repServerDelete.equals("suppression réussie")) {
					long idCasteDelete = (long) reponseDelete.get("Id");
					int idDelete = (int) idCasteDelete;
					System.out.println(repServerDelete + "\n Voici l'id de le l'utilisateur à supprimer : " + idDelete);  
				}
				else {
					System.out.println(repServerDelete);
				}
				client.stopConnection();

				break; 


			case "5":
				// dans la partie exit fermeture de toutes les connexions et fermeture de la socket 
				System.out.println("########################### EXIT #########################");
				System.out.println("Merci de votre visite, A bientot!");
				client.stopConnection();
				System.exit(0);
				break;

			case "6": 
				//pour montrer que le client n'a pas accés a la BDD

				c = createConnection(); 
				System.out.println("nom:");
				String nomBDD = sc.nextLine();
				System.out.println("prénom:");
				String prenomBDD = sc.nextLine();

				PreparedStatement stmt3 = c.prepareStatement("insert into utilisateur(nom,prenom) values (?,?);");
				stmt3.setString(1, nomBDD);
				stmt3.setString(2,prenomBDD);
				stmt3.execute();
				break; 
				
			case "7": 
				System.out.println("########################### SENSOR INDICATOR #########################");
				obj.put("demandType", "SENSOR_INDICATOR");
				System.out.println("récupération du nombre de capteur par zone selon le type et la date"); 

				System.out.println(obj);
				JSONObject reponseSensor = client.sendMessage(obj);
				System.out.println("affichage rep : " + reponseSensor); 
				ArrayList<JSONObject> allSensors = new ArrayList<JSONObject>();// Creation d'un tableau de type SensorIndicator
				allSensors = (ArrayList<JSONObject>) reponseSensor.get("sensors");
				//System.out.println(allSensors.size()); 
				int nbTotal = 0;
				for(int i = 0; i<allSensors.size();i++) { // Creating a loop to display all sensors in the table sensors
					SensorIndicator s = new SensorIndicator(); 
					s.convertFromJson(allSensors.get(i));
					System.out.println("type: "+s.getType()+
							" | position: "+s.getPosition() +
							" | date: Pas encore recuperee " + 
							" | nombre de capteurs en ville : "+s.getSensorNb()); 
				
					nbTotal += s.getSensorNb();
				}
				System.out.println("==============> Nb total : " + nbTotal);
				
				
				client.stopConnection();
				/*
				client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
				JSONObject obj2 =new JSONObject();
				obj2.put("demandType", "SENSOR_INDICATOR");
				System.out.println("récupération du nombre totale de capteurs dans la ville"); 
				
				System.out.println(obj);
				JSONObject reponseSensor2 = client.sendMessage(obj2);
				System.out.println("affichage rep : " + reponseSensor); 
				ArrayList<SensorIndicator> allSensors2 = new ArrayList<SensorIndicator>();// Creation d'un tableau de type SensorIndicator
				allSensors = (ArrayList<SensorIndicator>) reponseSensor.get("sensors");
				for(int i = 0; i<allSensors.size();i++) { // Creating a loop to display all sensors in the table sensors
					System.out.println("type: "+allSensors.get(i).getType()+
							" | position: "+allSensors.get(i).getPosition() +
							" | date: "+allSensors.get(i).getDate() + 
							" | nombre de capteurs en ville : "+allSensors.get(i).getSensorNb()); 
					
				} 
				*/
				
				break; 
				
			case "8": 
				System.out.println("########################### CAR INDICATOR #########################");
			
				obj.put("demandType", "CAR_INDICATOR");
				System.out.println("récupération du nombre de voitures par la date"); 


				System.out.println(obj);
				
				JSONObject reponseAll1 = client.sendMessage(obj);
				System.out.println("affichage rep : " + reponseAll1); 
				ArrayList<JSONObject> allCars = new ArrayList<JSONObject>();// Creation d'un tableau de type CarIndicator
				allCars = (ArrayList<JSONObject>) reponseAll1.get("cars");
				
				for(int i = 0; i<allCars.size();i++) { // Creating a loop to display all sensors in the table Frequentation_Voiture
					CarIndicator s = new CarIndicator(); 
					s.convertFromJson(allCars.get(i));
					System.out.println(" | nombre de voitures: "+ s.getCarsNb() +
							" date: " + s.getDate()+
							" | nombre de voitures totale par date : "+ s.getCarNbGlobal()); 
							
		
				}			 
				client.stopConnection();
				break; 
				
				
			case "9": 
				System.out.println("########################### WARNING INDICATOR #########################");
				obj.put("demandType", "WARNING_INDICATOR");

				System.out.println("récupération du nombre d'alertes par DATE"); 

				System.out.println(obj);
				JSONObject reponseAll2 = client.sendMessage(obj);
				System.out.println("affichage rep : " + reponseAll2);
				ArrayList<JSONObject> allWarnings = new ArrayList<JSONObject>();// Creation d'un tableau de type WarningIndicator
				allWarnings = (ArrayList<JSONObject>) reponseAll2.get("warnings");
				for(int i = 0; i<allWarnings.size();i++) { // Creating a loop to display all sensors in the table historique_Alerte
					WarningIndicator s = new WarningIndicator(); 
					s.convertFromJson(allWarnings.get(i));
					System.out.println("position: "+ s.getPosition() + 
							" | date : "+ s.getDateStart()+
							" | nombre d'alertes dans la ville : "+s.getWarningNb()); 
				}			 
				client.stopConnection();
				
				break; 
				
				
			case "10": 
				System.out.println("########################### STATION INDICATOR #########################");
			
				
				obj.put("demandType", "STATION_INDICATOR");
				System.out.println("récupération du nombre de station par zone "); 
				System.out.println(obj);
				JSONObject reponseAll3 = client.sendMessage(obj);
				System.out.println("affichage rep : " + reponseAll3); 
				ArrayList<JSONObject> allStations = new ArrayList<JSONObject>();// Creation d'un tableau de type StationIndicator
				allStations = (ArrayList<JSONObject>) reponseAll3.get("stations");
				for(int i = 0; i<allStations.size();i++) { // Creating a loop to display all sensors in the table historique_Alerte
					StationIndicator s = new StationIndicator(); 
					s.convertFromJson(allStations.get(i));
					System.out.println(" | position : "+s.getPosition() +
							" | nombre de stations par zone dans la ville : "+ s.getStationNb()); 
				}			 
				client.stopConnection();
				
				break; 
				
			case "11": 
				System.out.println("###########################  PERSON PER STATION INDICATOR #########################");
				obj.put("demandType", "PERSON_STATION_INDICATOR");
				

				System.out.println(obj);
				JSONObject reponseAll4 = client.sendMessage(obj);
				ArrayList<JSONObject> allStationsPers = new ArrayList<JSONObject>();// Creation d'un tableau de type StationIndicator
				allStationsPers = (ArrayList<JSONObject>) reponseAll4.get("PersonStations");
				for(int i = 0; i<allStationsPers.size();i++) { // Creating a loop to display all sensors in the table historique_Alerte
					PersonStationIndicator s = new PersonStationIndicator(); 
					s.convertFromJson(allStationsPers.get(i));
					System.out.println(" position  : "+ s.getPosition()+
							" | le nombre de personne dans cette station  : "+ s.getPersonQty() +
					" | date : "+ s.getDate() + 
					" | nombre de personne dans cette zone  : " + s.getPersNb());
				}			 
				client.stopConnection();
				
				break; 
				
			case "12": 
				System.out.println("########################### INSERT SENSOR #########################");
				obj.put("demandType",String.valueOf("MOCK_SENSOR_INSERT"));
			
				
				JSONObject reponseInsertSensor = client.sendMessage(obj);
				String repServerSensor = (String) reponseInsertSensor.get("reponse");
				System.out.println(repServerSensor);
			
				client.stopConnection();
				break; 
				
			case "13" : 
				System.out.println("########################### INSERT CAR #########################");
				obj.put("demandType",String.valueOf("MOCK_CAR_INSERT"));
			
				
				JSONObject reponseInsertCar = client.sendMessage(obj);
				String repServerCar = (String) reponseInsertCar.get("reponse");
				System.out.println(repServerCar);
			
				client.stopConnection();
				break; 
				
			case "14": 
				System.out.println("########################### INSERT STATION #########################");
				obj.put("demandType",String.valueOf("MOCK_STATION_INSERT"));
			
				
				JSONObject reponseInsertStation = client.sendMessage(obj);
				String repServerStation = (String) reponseInsertStation.get("reponse");
				System.out.println(repServerStation);
			
				client.stopConnection();
				break; 
				
			case "15": 
				System.out.println("########################### INSERT  PERS STATION #########################");
				obj.put("demandType",String.valueOf("MOCK_PERS_STATION_INSERT"));
			
				
				JSONObject reponseInsertPersStation = client.sendMessage(obj);
				String repServerPersStation = (String) reponseInsertPersStation.get("reponse");
				System.out.println(repServerPersStation);
			
				client.stopConnection();
				break; 
				
			case "16": 
				System.out.println("########################### INSERT HISTORICAL WARNING #########################");
				obj.put("demandType",String.valueOf("MOCK_WARNING_HISTORICAL_INSERT"));
			
				
				JSONObject reponseInsertWarning = client.sendMessage(obj);
				String repServerWarning = (String) reponseInsertWarning.get("reponse");
				System.out.println(repServerWarning);
			
				client.stopConnection();
				break; 

			default:
				System.out.println("Unrocognized command");
				break;
				

			}

		}
	}
}

