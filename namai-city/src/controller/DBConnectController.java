package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import connectionPool.DataSource;
import model.ModelTestPool;
import view.TestPoolView;

// Fais le lien entre la vue(print) et le model(getteur)

public class DBConnectController {
	private ModelTestPool userModel;
	private TestPoolView shsView;
	private Scanner sc = new Scanner(System.in);


	public DBConnectController(TestPoolView v) throws SQLException, ClassNotFoundException {
		userModel = new ModelTestPool();
		shsView = v;

	}

	public void start() throws SQLException  {
		String rep = "";
		List<Connection> co =new ArrayList();
		Connection c = null; 
		while(true) {
			try {

				System.out.println("########################### Menu #########################");
				System.out.println("1: Get a connection");
				System.out.println("2: Release a connection");
				System.out.println("3: Get Size of the pool");
				System.out.println("4: ShutDown all connection");
				System.out.println("5: Afficher");
				System.out.println("6: Créer");
				System.out.println("7: Mettre à jour");
				System.out.println("8: Supprimer");
				System.out.println("9: Exit");
				System.out.println("########################### Menu #########################");

				rep = sc.nextLine();

				switch (rep) {
				case "1":
					co.add(DataSource.getConnection());
					//c = DataSource.getConnection();
					shsView.printScreen("Size of the pool: "+DataSource.getSize());
					shsView.printScreen("Number of connection asked: "+co.size());
					c = co.get(0);
					
					break;
					
				case "2":
					DataSource.releaseConnection(co.get(0));
					co.remove(0);
					shsView.printScreen("Size of the pool: "+DataSource.getSize());
					shsView.printScreen("Number of connection asked: "+co.size());
					break;
				case "3":
					shsView.printScreen("Size of the pool: "+ DataSource.getSize());
					break;
				case "4":
					System.out.println("Fonctionnalité bientôt disponible");
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

			}

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

}