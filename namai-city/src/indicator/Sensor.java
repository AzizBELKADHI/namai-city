package indicator;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Sensor {
	private Connection c; 

	private Object NbSensors (JSONObject JsonRecu) throws SQLException, InterruptedException {

		String date =(String) JsonRecu.get("date");
		System.out.println("bonjour voici les donnees recu apres traitement");
		System.out.println(date +  " " );

		PreparedStatement stmt1 = c.prepareStatement("select* ,count (*) as nombre_capteurs from capteur where date = ? group by type and position;"); 
		stmt1.setString(1, date);
		ResultSet rs2 = stmt1.executeQuery();

		JSONObject obj=new JSONObject();
		// creation of sensor list 
		ArrayList<SensorIndicator> listSensors = new ArrayList<SensorIndicator>(); 

		while (rs2.next()) {
			
			// Mapping de la classe SensorIndicator (passage des résultats de la BDD en un objet java grâce au resultset 
			SensorIndicator sensor = new SensorIndicator(rs2.getInt("id_cap"), rs2.getString("type"), rs2.getString("position"), rs2.getTimestamp("date")); 

			// adding each sensor to the list already created
			listSensors.add(sensor);
		}
		//System.out.println("voici l'arrayList : ");
		// displaying the list 
		//System.out.println(listUsers);

		obj.put("sensors", listSensors);
		System.out.println("voici le json envoyé avec le select: ");
		// displaying the Json
		System.out.println(obj);
		Thread.sleep(3000); 
		return obj; 

	}
}
