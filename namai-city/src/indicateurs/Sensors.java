package indicateurs;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Sensors {
	private Connection c; 

	private Object NbSensors (JSONObject JsonRecu) throws SQLException, InterruptedException {

		String date =(String) JsonRecu.get("date");
		System.out.println("bonjour voici les donnees recu apres traitement");
		System.out.println(date +  " " );

		PreparedStatement stmt1 = c.prepareStatement("select count (*) as nombre_capteurs from capteur where date = ? group by type and position;"); 
		stmt1.setString(1, date);
		ResultSet rs2 = stmt1.executeQuery();

		JSONObject obj=new JSONObject();
		// creation of sensor list 
		ArrayList<JSONObject> listSensors = new ArrayList<JSONObject>(); 

		while (rs2.next()) {
			JSONObject sensor =new JSONObject();
			// recovery of each sensor's data 
			sensor.put("Id", rs2.getInt("id_cap"));
			sensor.put("type", rs2.getString("type"));
			sensor.put("position", rs2.getString("position")); 
			sensor.put("date", rs2.getString("date")); 

			// adding each sensor to the list already created
			listSensors.add(sensor);
		}
		//System.out.println("voici l'arrayList : ");
		// displaying the list 
		//System.out.println(listUsers);

		obj.put("sensors", listSensors);
		System.out.println("voici le json envoy� avec le select: ");
		// displaying the Json
		System.out.println(obj);
		Thread.sleep(3000); 
		return obj; 

	}
}
