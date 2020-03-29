package main;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.*;

import socketClient.SocketClient;

public class Main {
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		while(true) {
				System.out.println("########################### Menu Namai-city-client #########################");
				System.out.println("1: Afficher");
				System.out.println("2: Créer");
				System.out.println("3: Mettre à jour");
				System.out.println("4: Supprimer");
				System.out.println("5: Exit");
				System.out.println("########################### Menu Namai-city-client #########################");
				SocketClient client = new SocketClient();	
				client.startConnection("172.31.249.49", 6666);
				JSONObject obj=new JSONObject();
				String rep = sc.nextLine();
				
				// ne pas oublier de fermer chaque connexion apres chaque type de demande et de la remettre dans la liste de connexion. 

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
						
				// a completer		
						
					
					case "2":
						choice = new Scanner(System.in);
						System.out.println("quel est l'id de l'utilisateur à afficher ? ");
						rep = choice.nextLine();
						Integer id_user = Integer.parseInt(rep);
						obj.put("demandType",String.valueOf("SELECT"));
						obj.put("Id",Integer.valueOf(id_user)); 
						System.out.println(obj);
						JSONObject reponse = client.sendMessage(obj);
						    String name = (String) reponse.get("nom");  
						    String prenom = (String) reponse.get("prenom");  
						    long idCaste = (long) reponse.get("Id");
						    int id = (int) idCaste;
						    System.out.println(name +": \n" + prenom + ": \n "+id+ ": \n");  
						client.stopConnection();   
					}
					break;
			/*	SocketClient client = new SocketClient();	
				client.startConnection("172.31.249.49", 6666);
				JSONObject obj=new JSONObject();  */    
			/*		
				obj.put("Id",Integer.valueOf(0)); 
				System.out.println(obj);
				JSONObject reponse = client.sendMessage(obj);
				
				    String name = (String) reponse.get("nom");  
				    String prenom = (String) reponse.get("prenom");  
				    long idCaste = (long) reponse.get("Id");
				    int id = (int) idCaste;
				    System.out.println(name +" "+prenom+" "+id);  
				client.stopConnection();   
				*/
				case "2":
					// requete insertion dans table utilisateur
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
					    String prenomInsert = (String) reponse.get("prenom");  
					    String nomInsert = (String) reponse.get("nom");
					    System.out.println(repServer +": \n" + prenomInsert + ": \n " + nomInsert  + ": \n");  
					client.stopConnection();
					break; 
					
				case "3": 
					// requete pour mettre à  jour la table utilisateur 
					System.out.println("quel est l'id à modifier?"); 
					String id = sc.nextLine();
					Integer id_user = Integer.parseInt(id);
				    System.out.print("le nom ? ");
				    String nomUpdate = sc.nextLine(); 
				    System.out.print("le prenom ? ");
				    String prenomUpdate = sc.nextLine();
				    obj.put("demandType",String.valueOf("UPDATE"));
					obj.put("nom",String.valueOf(nomUpdate));
					obj.put("prenom",String.valueOf(prenomUpdate));
					obj.put("Id",String.valueOf(id_user));
					System.out.println(obj);
					JSONObject reponseUdpade = client.sendMessage(obj);
					    String repServerUpdate = (String) reponseUdpade.get("reponse");  
					    String prenomUpdate2 = (String) reponseUdpade.get("prenom");  
					    String nomupdate2 = (String) reponseUdpade.get("nom");
					    long idCaste = (long) reponseUdpade.get("Id");
					    int idUpdate = (int) idCaste;
					    System.out.println(repServerUpdate +": \n" + prenomUpdate2 + ": \n " + nomupdate2  + ": \n" + idUpdate  + ": \n");  
					client.stopConnection();
					
				    break; 
				
				case "4" : 
					// crud requete delete de la table en BDD (NamaiDB / toto) 
					
					System.out.println("quel est l'id de l'utilisateur à modifier ?"); 
					String id_delete = sc.nextLine();
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
					
				    break; 

					
				case "5":
					// dans la partie exit fermeture de toutes les connexions et fermeture de la socket 
					System.exit(0);
					break;
					
				default:
					System.out.println("Unrocognized command");
					break;
					
				}

			}
		}
}



