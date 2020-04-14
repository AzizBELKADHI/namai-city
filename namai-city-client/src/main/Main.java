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
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

import org.json.simple.*;

import socketClient.SocketClient;

public class Main {
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

	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		while(true) {
			System.out.println("########################### Menu Namai-city-client #########################");
			System.out.println("1: Afficher");
			System.out.println("2: Créer");
			System.out.println("3: Mettre à jour");
			System.out.println("4: Supprimer");
			System.out.println("5: Exit");
			System.out.println("6: Tentative de connexion à la BDD depuis le client ");
			System.out.println("########################### Menu Namai-city-client #########################");
			SocketClient client = new SocketClient();	
			client.startConnection("172.31.249.49", 6666);
			JSONObject obj=new JSONObject();
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

			}

		}
	}
}



