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
		while(true) {
			try {

				System.out.println("########################### Menu #########################");
				System.out.println("1: Get a connection");
				System.out.println("2: Release a connection");
				System.out.println("3: Get Size of the pool");
				System.out.println("4: ShutDown all connection");
				System.out.println("5: Exit");
				System.out.println("########################### Menu #########################");

				rep = sc.nextLine();


				switch (rep) {
				case "1":
					co.add(DataSource.getConnection());
					shsView.printScreen("Size of the pool: "+DataSource.getSize());
					shsView.printScreen("Number of connection asked: "+co.size());
					Connection c = DataSource.getConnection();
					
						PreparedStatement stmt = c.prepareStatement("select * from utilisateur;");
						//stmt.setInt(1,1);
						ResultSet rs = stmt.executeQuery();
						
						// boucle qui va afficher le nom et le prenom 
						while (rs.next()) {
							String nom = rs.getString("nom"); 
							String prenom = rs.getString("prenom"); 
							System.out.println(nom + " " + prenom);
						}
						
					break;
				case "2":
					DataSource.releaseConnection(co.get(0));co.remove(0);
					shsView.printScreen("Size of the pool: "+DataSource.getSize());
					shsView.printScreen("Number of connection asked: "+co.size());
					break;
				case "3":
					shsView.printScreen("Size of the pool: "+ DataSource.getSize());
					break;
				case "4":
					System.exit(0);
					break;
				default:
					shsView.printScreen("Unrocognized command");
					break;
				}

			}

			catch(Exception ex){
				System.err.println(ex.getMessage());
			}
			/*finally {
DataSource.shutdown();
shsView.printScreen("Size of the pool after shutdown: "+DataSource.getSize());
}*/
		}
	}

}