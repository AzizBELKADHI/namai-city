package indicator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Car {
	private Connection c; 

	private Object NbCars (JSONObject JsonRecu) throws SQLException, InterruptedException {

		String date =(String) JsonRecu.get("date");
		System.out.println("bonjour voici les donnees recu apres traitement");
		System.out.println(date +  " ");

		PreparedStatement stmt1 = c.prepareStatement("select sum(nb_voitures) as nombre_voitures, avg(nb_voitures), max(nb_voitures) from Frequentation_Voiture where date = ? group by position;"); 
		stmt1.setString(1, date);
		ResultSet rs2 = stmt1.executeQuery();

		JSONObject obj=new JSONObject();
		// creation of sensor list 
		ArrayList<JSONObject> listCars = new ArrayList<JSONObject>(); 

		while (rs2.next()) {
			JSONObject car =new JSONObject();
			// recovery of each sensor's data 
			car.put("Id", rs2.getInt("id_voit"));
			car.put("date", rs2.getString("date"));
			car.put("nb_voitures", rs2.getString("nb_voitures")); 
			car.put("id_cap", rs2.getString("id_cap")); 

			// adding each sensor to the list already created
			listCars.add(car);
		}
		//System.out.println("voici l'arrayList : ");
		// displaying the list 
		//System.out.println(listUsers);

		obj.put("Cars", listCars);
		System.out.println("voici le json envoyé avec le select : ");
		// displaying the Json
		System.out.println(obj);
		Thread.sleep(3000); 
		return obj; 

	}
}
