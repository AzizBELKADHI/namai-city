package com.client.application;

import java.awt.FlowLayout;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.*;

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
	
	public static void main(String [] args) throws SQLException, IOException {
	//	TestJson t = new TestJson();
		PagePrincipale page = new PagePrincipale();
		page.setVisible(true);
	
		
	}
	
	
	public static JSONObject getBornes() throws SQLException, IOException,UnsupportedEncodingException {
		SocketClient client = new SocketClient();
		client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
		JSONObject obj=new JSONObject();  //JSONObject creation
		obj.put("demandType",String.valueOf("getInitialInfos")); 
		System.out.println(obj);	
		JSONObject reponseBornes = client.sendMessage(obj);
		System.out.println(reponseBornes);
		client.stopConnection(); 

		return reponseBornes; 
		
	}
	
	public static JSONObject launchSimulation() throws SQLException, IOException,UnsupportedEncodingException {
		System.out.println("je rentre deja dans la simulation");
		SocketClient client = new SocketClient();
		client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
		JSONObject obj=new JSONObject();  //JSONObject creation
		obj.put("demandType",String.valueOf("launchSimulation")); 
		System.out.println(obj);	
		JSONObject reponseBornes = client.sendMessage(obj);
		System.out.println(reponseBornes);
		client.stopConnection(); 

		return reponseBornes; 
		
	}
	
	
	public static JSONObject searchVehicule(String dateDebut, String dateFin, String zone, String type) throws SQLException, IOException,UnsupportedEncodingException {
		System.out.println("je rentre deja dans la recherche vehicules");
		SocketClient client = new SocketClient();
		client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
		JSONObject obj=new JSONObject();  //JSONObject creation
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
		JSONObject reponseSearch = client.sendMessage(obj);
		client.stopConnection(); 

		return reponseSearch; 
		
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
			System.out.println("8: récupération de l'indicateur du nombre de voitures");
			System.out.println("9: récupération de l'indicateur du nombre d'alertes");
			System.out.println("10: récupération de l'indicateur du nombre de stations");
			System.out.println("11: récupération de l'indicateur du nombre de personnes par station");
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

			default:
				System.out.println("Unrocognized command");
				break;
				
			case "7": 
				System.out.println("########################### SENSOR INDICATOR #########################");
				System.out.println("A quel date voulez-vous récupèrer les données du capteur de la qualité de l'air? ");
				String date  = sc.nextLine();
				Timestamp date2 = Timestamp.valueOf(date);
				obj.put("demandType", "SENSOR_INDICATOR");
				obj.put("date", date); 

				System.out.println(obj);
				JSONObject reponseSensor = client.sendMessage(obj);
				System.out.println("affichage rep : " + reponseSensor); 
				ArrayList<SensorIndicator> allSensors = new ArrayList<SensorIndicator>();// Creation d'un tableau de type SensorIndicator
				allSensors = (ArrayList<SensorIndicator>) reponseSensor.get("sensors");
				for(int i = 0; i<allSensors.size();i++) { // Creating a loop to display all sensors in the table sensors
					System.out.println("type: "+allSensors.get(i).getType()+
							" | position: "+allSensors.get(i).getPosition() +
							" | date: "+allSensors.get(i).getDate() + 
							" | nombre de capteurs en ville : "+allSensors.get(i).getSensorNb()); 
					
				}
				
				client.stopConnection();
				
				break; 
				
			case "8": 
				System.out.println("########################### CAR INDICATOR #########################");
				System.out.println("A quel date voulez-vous récupèrer les données du capteur permettant de comptabiliser le nombre de voitures? ");
				String date_car  = sc.nextLine();
				Timestamp date_car2 = Timestamp.valueOf(date_car);
				obj.put("demandType", "CAR_INDICATOR");
				obj.put("date", date_car); 

				System.out.println(obj);
				JSONObject reponseAll1 = client.sendMessage(obj);
				ArrayList<CarIndicator> allCars = new ArrayList<CarIndicator>();// Creation d'un tableau de type CarIndicator
				allCars = (ArrayList<CarIndicator>) reponseAll1.get("cars");
				System.out.println("Bonjour"); 
				for(int i = 0; i<allCars.size();i++) { // Creating a loop to display all sensors in the table Frequentation_Voiture
					System.out.println("id_voiture: "+allCars.get(i).getCarId() + 
							" | date: "+allCars.get(i).getDate()+
							" | nombre de voitures: "+allCars.get(i).getCarsNb() +
							" | id_capteur: "+allCars.get(i).getSensorId()); 
				}			 
				client.stopConnection();
				break; 
				
			case "9": 
				System.out.println("########################### WARNING INDICATOR #########################");
				System.out.println("A quel date voulez-vous récupèrer le nombre d'alertes ainsi que le taux de seuil de pollution de chacune ? ");
				String date_warning  = sc.nextLine();
				Timestamp date_warning2 = Timestamp.valueOf(date_warning);
				obj.put("demandType", "WARNING_INDICATOR");
				obj.put("date", date_warning); 

				System.out.println(obj);
				JSONObject reponseAll2 = client.sendMessage(obj);
				ArrayList<WarningIndicator> allWarnings = new ArrayList<WarningIndicator>();// Creation d'un tableau de type WarningIndicator
				allWarnings = (ArrayList<WarningIndicator>) reponseAll2.get("warnings");
				for(int i = 0; i<allWarnings.size();i++) { // Creating a loop to display all sensors in the table historique_Alerte
					System.out.println("id_alerte: "+allWarnings.get(i).getWarningId() + 
							" | l'état de l'alerte : "+allWarnings.get(i).getWarningState()+
							" | id_seuil: "+allWarnings.get(i).getThresholdId() +
							" | le taux du seuil: "+allWarnings.get(i).getThreshold() +
					" | date: "+allWarnings.get(i).getDate()); 
				}			 
				client.stopConnection();
				
				break; 
				
				
			case "10": 
				System.out.println("########################### STATION INDICATOR #########################");
				System.out.println("A quel date voulez-vous récupèrer le nombre de stations dans la ville? ");
				String date_station  = sc.nextLine();
				Timestamp date_station2 = Timestamp.valueOf(date_station);
				obj.put("demandType", "STATION_INDICATOR");
				obj.put("date", date_station); 

				System.out.println(obj);
				JSONObject reponseAll3 = client.sendMessage(obj);
				ArrayList<StationIndicator> allStations = new ArrayList<StationIndicator>();// Creation d'un tableau de type StationIndicator
				allStations = (ArrayList<StationIndicator>) reponseAll3.get("stations");
				for(int i = 0; i<allStations.size();i++) { // Creating a loop to display all sensors in the table historique_Alerte
					System.out.println("id_station: "+allStations.get(i).getStationId() + 
							" | nom de la station  : "+allStations.get(i).getStationName()+
							" | position : "+allStations.get(i).getPosition() +
					" | date: "+allStations.get(i).getDate()); 
				}			 
				client.stopConnection();
				
				break; 
				
			case "11": 
				System.out.println("###########################  PERSON PER STATION INDICATOR #########################");
				System.out.println("A quel date voulez-vous récupèrer le nombre de stations dans la ville? ");
				String date_pers_station  = sc.nextLine();
				Timestamp date_pers_station2 = Timestamp.valueOf(date_pers_station);
				obj.put("demandType", "STATION_INDICATOR");
				obj.put("date", date_pers_station); 

				System.out.println(obj);
				JSONObject reponseAll4 = client.sendMessage(obj);
				ArrayList<PersonStationIndicator> allStationsPers = new ArrayList<PersonStationIndicator>();// Creation d'un tableau de type StationIndicator
				allStationsPers = (ArrayList<PersonStationIndicator>) reponseAll4.get("PersonStations");
				for(int i = 0; i<allStationsPers.size();i++) { // Creating a loop to display all sensors in the table historique_Alerte
					System.out.println("id_Freq_station : "+allStationsPers.get(i).getFreqStationId() + 
							" | position  : "+allStationsPers.get(i).getPosition()+
							" | le nombre de personnes dans cette station  : "+allStationsPers.get(i).getPersonQty() +
					" | id_station : "+allStationsPers.get(i).getStationId()); 
				}			 
				client.stopConnection();
				
				break; 

			}

		}
	}
}

class PagePrincipale extends JFrame{
	public PagePrincipale() {
		setTitle("Un exemple de JLabel"); 
		setSize(400, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout());
		}
}
