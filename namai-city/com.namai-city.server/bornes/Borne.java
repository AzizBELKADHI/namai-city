package bornes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import carsHistory.carsHistory;


public class Borne {
	
	private Connection c; 
	
	public Borne(Connection c) {
		this.c = c;
	}
	
	public Object BornesState() throws SQLException, InterruptedException {
		
		PreparedStatement stmt1 = c.prepareStatement("select * from Bornes;");
		ResultSet rs2 = stmt1.executeQuery();

		JSONObject obj=new JSONObject();
		// creation of users list 
		ArrayList<JSONObject> listBornes = new ArrayList<JSONObject>();

		while (rs2.next()) {
			JSONObject borne=new JSONObject();
			// recovery of each user's data (id/ name/ first name) 
			borne.put("Id_borne", rs2.getInt("id_borne"));
			borne.put("state", rs2.getString("state"));
			borne.put("position", rs2.getString("position"));
			System.out.println("je suis ici" + borne);

			// adding each user to the list already created
			listBornes.add(borne);


		}
		obj.put("bornes", listBornes);
		obj.put("actualCars", Integer.toString(carsHistory.totalCars));
		obj.put("maxCars", Integer.toString(carsHistory.maxCars));
		System.out.println("voici le json envoyé avec la liste des bornes: ");
		// displaying the Json
		System.out.println(obj);
		Thread.sleep(3000); 
		return obj; 
	}
	
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
