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
import org.json.simple.parser.JSONParser;

import com.connectionPool.DataSource;
import controller.DBConnectController;



public class Sensor {

	public JSONObject getIndicator (JSONObject JsonRecu, Connection c){
		JSONObject obj=new JSONObject();

		if (JsonRecu.get("demandType").equals("SENSOR_INDICATOR")) {
			try {
				PreparedStatement stmt1 = c.prepareStatement("select count(*) as nombre_capteur_position, position, type, date from capteur group by (position,type,date);");
				ResultSet rs2 = stmt1.executeQuery();


				// creation of users list 
				ArrayList<JSONObject> listSensors = new ArrayList<JSONObject>();

				while (rs2.next()) {
					SensorIndicator sensor = new SensorIndicator(0, rs2.getString("type"), rs2.getString("position"), rs2.getTimestamp("date"), rs2.getInt("nombre_capteur_position")); 
					//System.out.println("récuperation des résultats du select"); 
					JSONObject sensorJSON = sensor.convertToJSON();

					// adding each user to the list already created
					listSensors.add(sensorJSON);


				}
				//System.out.println("voici l'arrayList : ");
				// displaying the list 
				//System.out.println(listUsers);

				obj.put("sensors", listSensors);
				System.out.println("voici le json envoyé avec getIndicator ");
				// displaying the Json
				System.out.println(obj);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		return obj;
	}

}
