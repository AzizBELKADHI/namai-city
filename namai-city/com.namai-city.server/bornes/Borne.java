package bornes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import carsHistory.carsHistory;

	/* class used to interact with the bornes in order to get the status or to change it */

public class Borne {
	
	private Connection c; 

	/* to initialise the object we must add a connection in order to get data from the data base */
	
	public Borne(Connection c) {
		this.c = c;
	}
	
	/*method used by the server to get the bornes informations from the dataBase and also the 
	 * the number of cars in town actually and the max cars authorised by getting the static variables */
	public Object BornesState() throws SQLException, InterruptedException {
		
		PreparedStatement stmt1 = c.prepareStatement("select * from Bornes;");
		ResultSet rs2 = stmt1.executeQuery();

		JSONObject obj=new JSONObject();
		// creation of bornes list 
		ArrayList<JSONObject> listBornes = new ArrayList<JSONObject>();

		while (rs2.next()) {
			JSONObject borne=new JSONObject();
			// recovery of each borne's data (id/ state/ position) 
			borne.put("Id_borne", rs2.getInt("id_borne"));
			borne.put("state", rs2.getString("state"));
			borne.put("position", rs2.getString("position"));
			System.out.println("je suis ici" + borne);

			// adding each borne to the list already created
			listBornes.add(borne);


		}
		obj.put("bornes", listBornes);
		/*adding the static variables to the response (the user gets immediatly after launching the
		 application the variables on cars) */
		obj.put("actualCars", Integer.toString(carsHistory.totalCars));
		obj.put("maxCars", Integer.toString(carsHistory.maxCars));
		System.out.println("voici le json envoyé avec la liste des bornes: ");
		// displaying the Json
		System.out.println(obj);
		return obj; 
	}
	
	/* method to know if the bornes are rised or not in order to let the cars to pass to town or not
	 * it search if one borne is rised if no result returned it means that the borne is lowered 
	 * and also all the bornes and if a result is returned it means that bornes are rised	
	 */
		
	public boolean forbiddenPassage() throws SQLException, InterruptedException {
		
		PreparedStatement stmt1 = c.prepareStatement("select state from Bornes where id_borne = 1;");
		ResultSet rs2 = stmt1.executeQuery();
		int i = 0;
		// creation of users list 

		while (rs2.next()) {
			i++;
			JSONObject borne=new JSONObject();
			borne.put("state", rs2.getString("state"));
			System.out.println("les bornes sont actuellement levé: " + borne);
		}
		if(i == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
/* method to change the bornes state in dataBase in order to rise them by updating the state
 * 1 => bornes rised
 * 0 => bornes lowered 	
 */
	
	public Object riseBornes() throws SQLException, InterruptedException{
			PreparedStatement stmt = c.prepareStatement("update bornes set state= ?;");
			stmt.setInt(1, 1); 
			JSONObject obj=new JSONObject();
			if(stmt.executeUpdate()>=1) {
				obj.put("reponse",String.valueOf("les bornes ont bien été rélevé")); 
			}
			else {
				obj.put("reponse",String.valueOf("erreur lors du changement d'état des bornes"));
			}
			System.out.println(obj);
			return obj; 
	}

	
	/* method to change the bornes state in dataBase in order to lower them by updating the state
	 * 1 => bornes rised
	 * 0 => bornes lowered 	
	 */	
	
	public Object lowerBornes() throws SQLException, InterruptedException{
		PreparedStatement stmt = c.prepareStatement("update bornes set state= ?;");
		stmt.setInt(1, 0); 
		JSONObject obj=new JSONObject();
		if(stmt.executeUpdate()>=1) {
			obj.put("reponse",String.valueOf("les bornes ont bien été baissé")); 
		}
		else {
			obj.put("reponse",String.valueOf("erreur lors du changement d'état des bornes"));
		}
		System.out.println(obj);
		return obj; 
}
}
