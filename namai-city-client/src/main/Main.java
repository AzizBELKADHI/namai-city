package main;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

import org.json.simple.*;

import socketClient.SocketClient;

public class Main {
	public static Connection c; // Creation of a connection
	private static String URL = "jdbc:postgresql://172.31.249.44:5432/NamaiDB"; // Database address
	private static String login = "toto" ; // Database login
	private static String password = "toto"; // Database password


	public static Connection createConnection() throws SQLException { 
		try {

			return  DriverManager.getConnection (URL, login, password);
		} catch (SQLException e) {
			throw new SQLException("Can't create connection", e);
		}

	}

	public static void main(String[] args) throws IOException, SQLException {
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
			System.out.println("########################### Menu Namai-city-client #########################");
			SocketClient client = new SocketClient();	// Socket creation
			client.startConnection("172.31.249.49", 6666); // Start of connection with socket
			JSONObject obj=new JSONObject();  //JSONObject creation
			String rep = sc.nextLine();



			switch (rep) {
			case "1": // Menu display
				System.out.println("########################### Menu Namai-city-client #########################");
				System.out.println("1: Afficher tous les utilisateurs");
				System.out.println("2: afficher un utilisateur en particulier");
				System.out.println("########################### Menu Namai-city-client #########################");	
				Scanner choice = new Scanner(System.in);
				rep = choice.nextLine(); 
				switch(rep) {
				case "1": // Request to display all users in the table "users"
					System.out.println("########################### SELECT #########################");
					obj.put("demandType",String.valueOf("SELECT"));
					obj.put("Id",Integer.valueOf(0)); 
					System.out.println(obj);
					JSONObject reponseAll = client.sendMessage(obj);
					ArrayList<JSONObject> allUsers = new ArrayList<JSONObject>();// Creation d'un tableau de type JSONObject
					allUsers = (ArrayList<JSONObject>) reponseAll.get("users");
					for(int i = 0; i<allUsers.size();i++) { // Creating a loop to display all users in the table users
						System.out.println("id: "+allUsers.get(i).get("Id")+ // ID display
								" | nom: "+allUsers.get(i).get("nom")+ // Name display
								" | prenom: "+allUsers.get(i).get("prenom")); // First name display 
					}			 
					client.stopConnection();  // Exit cases
					break;


				case "2": // Request to display one user in the table "users"
					choice = new Scanner(System.in);
					System.out.println("########################### SELECT #########################");
					System.out.println("quel est l'id de l'utilisateur à afficher ? ");
					rep = choice.nextLine();
					Integer id_user = Integer.parseInt(rep);
					obj.put("demandType",String.valueOf("SELECT"));
					obj.put("Id",Integer.valueOf(id_user)); 
					System.out.println(obj);
					JSONObject reponse = client.sendMessage(obj); // Response server
					String name = (String) reponse.get("nom");  
					String prenom = (String) reponse.get("prenom");  
					long idCaste = (long) reponse.get("Id");
					int id = (int) idCaste;
					System.out.println(name +": \n" + prenom + ": \n "+id+ ": \n");  // Display data
					client.stopConnection();  
					break; // Exit cases
				}
				break;

			case "2":
				// Request to insert a user in the table "users"
				System.out.println("########################### INSERT #########################");
				System.out.println("saisissez les informations de l'utilisateur:");
				System.out.println("nom:");
				String nom = sc.nextLine(); // Recovery of the name
				System.out.println("prénom:");
				String prenom = sc.nextLine(); // Recovery of the first name
				obj.put("demandType",String.valueOf("INSERT"));
				obj.put("nom",String.valueOf(nom));
				obj.put("prenom",String.valueOf(prenom));
				System.out.println(obj);
				JSONObject reponse = client.sendMessage(obj); // Response server
				String repServer = (String) reponse.get("reponse");  
				String prenomInsert = (String) reponse.get("prenom");  
				String nomInsert = (String) reponse.get("nom");
				System.out.println(repServer +": \n" + prenomInsert + ": \n " + nomInsert  + ": \n");  // Display data
				client.stopConnection();
				break; // Exit cases

			case "3": 
				// Request to update a user in the table "users"
				System.out.println("########################### INSERT #########################");
				System.out.println("quel est l'id à modifier?"); 
				String id = sc.nextLine(); // Recovery of the id
				Integer id_user = Integer.parseInt(id);
				System.out.print("le nom ? ");
				String nomUpdate = sc.nextLine(); // Recovery of the name
				System.out.print("le prenom ? ");
				String prenomUpdate = sc.nextLine(); // Recovery of the first name
				obj.put("demandType",String.valueOf("UPDATE"));
				obj.put("nom",String.valueOf(nomUpdate));
				obj.put("prenom",String.valueOf(prenomUpdate));
				obj.put("Id",String.valueOf(id_user));
				System.out.println(obj);
				JSONObject reponseUdpade = client.sendMessage(obj); // Response server
				String repServerUpdate = (String) reponseUdpade.get("reponse");  
				String prenomUpdate2 = (String) reponseUdpade.get("prenom");  
				String nomupdate2 = (String) reponseUdpade.get("nom");
				long idCaste = (long) reponseUdpade.get("Id");
				int idUpdate = (int) idCaste;
				System.out.println(repServerUpdate +": \n" + prenomUpdate2 + ": \n " + nomupdate2  + ": \n" + idUpdate  + ": \n");  // Display data
				System.out.println(repServerUpdate); 
				client.stopConnection();

				break; // Exit cases

			case "4" : 
				// Request to delete a user in the table "users"
				System.out.println("########################### DELETE  #########################");
				System.out.println("quel est l'id de l'utilisateur à supprimer ?"); 
				String id_delete = sc.nextLine(); // Recovery of the id
				Integer id_user_delete = Integer.parseInt(id_delete);


				obj.put("demandType",String.valueOf("DELETE"));

				obj.put("Id",String.valueOf(id_user_delete));
				System.out.println(obj);
				JSONObject reponseDelete = client.sendMessage(obj);
				String repServerDelete = (String) reponseDelete.get("reponse");  

				long idCasteDelete = (long) reponseDelete.get("Id");
				int idDelete = (int) idCasteDelete;
				System.out.println("Voici l'id de le l'utilisateur à supprimer : " + idDelete);  
				client.stopConnection();

				break; // Exit cases


			case "5":
				// Close all connection and socket
				System.out.println("########################### EXIT #########################");
				System.out.println("Merci de votre visite, A bientot!");
				client.stopConnection();
				System.exit(0);
				break; // Exit cases 

			case "6": 
				// This case is to show that cannot connect directly to the database 

				c = createConnection(); 
				System.out.println("nom:");
				String nomBDD = sc.nextLine();
				System.out.println("prénom:");
				String prenomBDD = sc.nextLine();

				PreparedStatement stmt3 = c.prepareStatement("insert into utilisateur(nom,prenom) values (?,?);"); // Preparation of the request
				stmt3.setString(1, nomBDD); 
				stmt3.setString(2,prenomBDD);
				stmt3.execute(); // Execution of the request
				break; // Exit cases

			default:
				System.out.println("Unrocognized command"); // If none of the cases, message display "Unrocognized command"
				break; // Exit cases

			}

		}
	}
}



