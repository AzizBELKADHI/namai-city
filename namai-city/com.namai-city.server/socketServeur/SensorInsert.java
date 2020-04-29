package socketServeur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.connectionPool.DataSource;
import controller.DBConnectController;

public class SensorInsert {

	public void insertSensor (JSONObject JsonRecu, Connection c) throws ParseException, UnsupportedEncodingException, SQLException {

		StringBuffer sb = new StringBuffer();

		// lecture du JSON afin de mettre chaque ligne en chaîne de caractère
		InputStream inputStream = FileReader.class.getClassLoader().getSystemResourceAsStream("sensors.json"); 
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")); 
		try {
			String temp; 
			while ((temp = bufferedReader.readLine()) != null) 
				sb.append(temp); 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace(); 
			}
		}
		String myjsonstring = sb.toString(); 

		JSONParser parser = new JSONParser(); 
		JSONArray json = (JSONArray) parser.parse(myjsonstring); 

		for (int i = 0; i < json.size(); i++) {
			JSONObject jsonObject = (JSONObject) json.get(i);
			Long id = (Long) (jsonObject.get("id_cap"));
			int sensorId = id.intValue(); 
			String type = String.valueOf(jsonObject.get("type")); 
			String position =  String.valueOf(jsonObject.get("position")); 
			Timestamp date = Timestamp.valueOf((String) jsonObject.get("date")); 
			System.out.println("Parcours de la liste des capteurs " + sensorId); 
		
			PreparedStatement stmt3 = c.prepareStatement("insert into capteur (id_cap, type, position, date) values (?,?,?,?);");
			// the request takes name and first name already retrieved 
			stmt3.setInt(1, sensorId);
			stmt3.setString(2,type);
			stmt3.setString(3,position);
			stmt3.setTimestamp(4, date); 
			// query execution 
			
			System.out.println("recupération des données"); 


			JSONObject obj=new JSONObject(); 

			// if (insertion bien passé) => executer les lignes suivantes sinon dire erreur
			if(stmt3.executeUpdate()>=1) {
				obj.put("reponse",String.valueOf("insertion reussi"));
				obj.put("id_cap",sensorId);
				obj.put("type", type);
				obj.put("position",position);
				obj.put("date",date);
				
				System.out.println("Insertion des lignes en base faite"); 
			}
			else {
				obj.put("reponse",String.valueOf("erreur lors de l'insertion"));
			}
			System.out.println(obj);

		}
		
	}
}



