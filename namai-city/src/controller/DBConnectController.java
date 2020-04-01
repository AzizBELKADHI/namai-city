package controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.simple.JSONObject;
import connectionPool.DataSource;
import model.ModelTestPool;
import socketServeur.ThreadClient;
import view.TestPoolView;



public class DBConnectController {
	private ModelTestPool userModel;
	private TestPoolView shsView;
	private Scanner sc = new Scanner(System.in);
	private ServerSocket socketServeur;
	private Socket socketClient;
	private static int numberOfConnectedClients = 0; 


	public DBConnectController(TestPoolView v) throws SQLException, ClassNotFoundException {
		userModel = new ModelTestPool();
		shsView = v;
	}

	public void start() throws SQLException, IOException  {
		String rep = "";
		List<Connection> co =new ArrayList();
		Connection c = null; 

		System.out.println("Bonjour, Bienvenue sur votre serveur!"); 

		// Socket creation on the server side 
		socketServeur = new ServerSocket(6666);

		System.out.println("====================================================");
		System.out.println("=== Voici le nombre de clients connectés : " + numberOfConnectedClients); 
		System.out.println("====================================================");
	
		while(true) {

			try {
				// Waiting for customer connection 
				socketClient = socketServeur.accept();
				
				DBConnectController.clientsState(true); 

				// a connection is assigned to the client 

				co.add(DataSource.getConnection());
				//c = DataSource.getConnection();
				shsView.printScreen("Size of the pool: "+DataSource.getSize());
				shsView.printScreen("Number of connection asked: "+co.size());
				c = co.get(0);
				shsView.printScreen("Size of the pool: "+ DataSource.getSize());
				System.out.println("bonjour je vais traiter votre demande");


				// instanciation of thread client (with a socket  and a connection)
				ThreadClient client = new ThreadClient(socketClient,c);
				client.start(); 

			}
			
			
			/*


				case "2":/*
					DataSource.releaseConnection(co.get(0));
					co.remove(0);
					shsView.printScreen("Size of the pool: "+DataSource.getSize());
					shsView.printScreen("Number of connection asked: "+co.size());
					break;


				case "5":
					// requete affichage table 
					PreparedStatement stmt1 = c.prepareStatement("select * from utilisateur;");
					ResultSet rs2 = stmt1.executeQuery();
					System.out.println("########################### Menu CRUD #########################");
					System.out.println("REQUETE SELECT");


					// boucle qui va afficher le nom et le prenom 
					while (rs2.next()) {
						Integer id_user = rs2.getInt("id_user");
						String nom = rs2.getString("nom"); 
						String prenom = rs2.getString("prenom"); 
						System.out.println(id_user + " " + nom + " " + prenom);

					}

					SocketServeur server = new SocketServeur();
					JSONObject JsonRecu = server.start(6666); 
				    System.out.println("bonjour je traite le json recu");
				    System.out.println(JsonRecu);
				    long idcaste = (long) JsonRecu.get("Id");
				    int idJson = (int) idcaste;
				    System.out.println("bonjour voici le ID recu apres traitement");
				    System.out.println(idJson);
					PreparedStatement stmtJson = c.prepareStatement("select * from utilisateur where id_user = ?");
					stmtJson.setInt(1, idJson);
					ResultSet jsonResponse = stmtJson.executeQuery();
					JSONObject obj=new JSONObject(); 
					while (jsonResponse.next()) {
						obj.put("Id",jsonResponse.getInt("id_user"));
						obj.put("nom",jsonResponse.getString("nom"));
						obj.put("prenom",jsonResponse.getString("prenom"));
					}
					 System.out.println(obj);
					server.outJson.println(obj);
					server.stop();

					break; 
				case "6":
					// requete insertion dans table utilisateur
					System.out.println("########################### Menu CRUD #########################");
					System.out.println("REQUETE INSERT");
					System.out.println("nom:");
					String nom = sc.nextLine();
					System.out.println("prénom:");
					String prenom = sc.nextLine();
					PreparedStatement stmt3 = c.prepareStatement("insert into utilisateur(nom,prenom) values (?,?);");
					stmt3.setString(1, nom);
					stmt3.setString(2,prenom);
					stmt3.execute();
					break; 

				case "7": 
					// requete pour mettre à  jour la table utilisateur 
					System.out.println("########################### Menu CRUD #########################");
					System.out.println("REQUETE UPDATE");
					System.out.println("quel est l'id à modifier?"); 
					String id = sc.nextLine();
					Integer id_user = Integer.parseInt(id);
				    System.out.print("le nom ? ");
				    String nom2 = sc.nextLine(); 
				    System.out.print("le prenom ? ");
				    String prenom2 = sc.nextLine();
					PreparedStatement stmt4 = c.prepareStatement("update utilisateur set nom= ?, prenom = ? where id_user = ?;  ");
					stmt4.setString(1, nom2);
					stmt4.setString(2,prenom2);
					stmt4.setInt(3, id_user);
					stmt4.execute();
					break; 

				case "8" : 
					// crud requete delete de la table en BDD (NamaiDB / toto) 
					System.out.println("########################### Menu CRUD #########################");
					System.out.println("REQUETE DELETE");
					System.out.println("Quel est l'id de la ligne à supprimer?");
					String id1 = sc.nextLine();
					Integer id_user1 = Integer.parseInt(id1); 
					PreparedStatement stmt5 = c.prepareStatement("delete from utilisateur where id_user = ?");
					stmt5.setInt(1, id_user1);
					stmt5.execute();
					break; 
				case "9":
					System.exit(0);
					break;

				default:
					shsView.printScreen("Unrocognized command");
					break;

				}

			} */

			catch(Exception ex){
				System.err.println(ex.getMessage());
				// chemin de l'exception 
				//ex.printStackTrace();
			}
			
			/*finally {
DataSource.shutdown();
shsView.printScreen("Size of the pool after shutdown: "+DataSource.getSize());
}*/
		}
			
	}
	public static  synchronized void clientsState(boolean isNewConnection) {
		numberOfConnectedClients = (isNewConnection) ? (numberOfConnectedClients + 1) : (numberOfConnectedClients - 1);
		System.out.println("====================================================");
		System.out.println("=== Voici le nombre de clients connectés : " + numberOfConnectedClients); 
		System.out.println("====================================================");
	
	}

}