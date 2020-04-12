package indicateurs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Pollution_threshold {

	private Connection c; 
	
	private Object NbEmergency (JSONObject JsonRecu) throws SQLException, InterruptedException {

		
		
		String date =(String) JsonRecu.get("date");
		System.out.println("bonjour voici les donnees recu apres traitement");
		System.out.println(date +  " ");

		
		PreparedStatement stmt1 = c.prepareStatement("select count(*) as nombre_alerte, seuil, max(nombre_alerte), avg(nombre_alerte) from historique_alerte where date = ? group by position;"); 
		stmt1.setString(1, date);
		ResultSet rs2 = stmt1.executeQuery();

		JSONObject obj=new JSONObject();
		// creation of emergency list 
		ArrayList<JSONObject> listEmergency = new ArrayList<JSONObject>(); 

		while (rs2.next()) {
			JSONObject emergency =new JSONObject();
			// recovery of each emergency's data 
			emergency.put("Id", rs2.getInt("id_alerte"));
			emergency.put("Id", rs2.getInt("id_cap"));
			emergency.put("alerte_etat", rs2.getInt("alerte_etat"));
			emergency.put("date", rs2.getString("date"));
			emergency.put("seuil", rs2.getString("seuil")); 

			// adding each sensor to the list already created
			listEmergency.add(emergency);
		}
		//System.out.println("voici l'arrayList : ");
		// displaying the list 
		//System.out.println(listUsers);

		obj.put("emergencies", listEmergency);
		System.out.println("voici le json envoyé avec le select : ");
		// displaying the Json
		System.out.println(obj);
		Thread.sleep(3000); 
		return obj; 

	}
}
