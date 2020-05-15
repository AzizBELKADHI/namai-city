package indicator;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.connectionPool.DataSource;
import controller.DBConnectController;



public class Sensor {
	private Connection c;
	
	
	public Object getIndicator (JSONObject JsonRecu) throws SQLException, InterruptedException {
		if (JsonRecu.get("demandType").equals("SENSOR_INDICATOR")) {
			String date =(String) JsonRecu.get("date");
			System.out.println("bonjour voici les donnees recu apres traitement");
			System.out.println(date +  " " );

			System.out.println("execution de la requête"); 
			PreparedStatement stmt1 = c.prepareStatement("select type, position, count(*) as nombre_cap from capteur where date = ? group by (type, position);"); 
			stmt1.setString(1, date);
			ResultSet rs2 = stmt1.executeQuery();

			JSONObject obj=new JSONObject();
			// creation of sensor list 
			ArrayList<SensorIndicator> listSensors = new ArrayList<SensorIndicator>(); 
			System.out.println(rs2.getFetchSize());
			while (rs2.next()) {

				// Mapping de la classe SensorIndicator (passage des résultats de la BDD en un objet java grâce au resultset 
				SensorIndicator sensor = new SensorIndicator(0, rs2.getString("type"), rs2.getString("position"), rs2.getTimestamp("date"), rs2.getInt("nombre_cap")); 
				System.out.println("récuperation des résultats du select"); 
				//System.out.println("affichage: " + sensor.toString()); 
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
		return null;
	}
}
