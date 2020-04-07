package indicateurs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class NbCapteurs {
	private Connection c; 

	private Object NbCapteurs (JSONObject JsonRecu) throws SQLException, InterruptedException {

		
		String type =(String) JsonRecu.get("type");
		String position =(String) JsonRecu.get("position");
		System.out.println("bonjour voici les donnees recu apres traitement");
		System.out.println(type +  " " + position);
		
		PreparedStatement stmt1 = c.prepareStatement("select count (*) from capteur where type = ? and position = ?;");
		stmt1.setString(1, type);
		stmt1.setString(2, position);
		ResultSet rs2 = stmt1.executeQuery();

		JSONObject obj=new JSONObject();
		// creation of sensor list 
		ArrayList<JSONObject> listSensors = new ArrayList<JSONObject>(); 

		while (rs2.next()) {
			JSONObject sensor =new JSONObject();
			// recovery of each sensor's data 
			sensor.put("Id", rs2.getInt("id_cap"));
			sensor.put("type", rs2.getString("type"));
			sensor.put("seuil_pollution_max", rs2.getString("seuil_pollution_max"));
			sensor.put("taux", rs2.getString("taux"));
			sensor.put("position", rs2.getString("position")); 
			sensor.put("etat", rs2.getString("etat")); 

			// adding each sensor to the list already created
			listSensors.add(sensor);
		}
		//System.out.println("voici l'arrayList : ");
		// displaying the list 
		//System.out.println(listUsers);

		obj.put("sensors", listSensors);
		System.out.println("voici le json envoyé avec le select All: ");
		// displaying the Json
		System.out.println(obj);
		Thread.sleep(3000); 
		return obj; 

	}
}
