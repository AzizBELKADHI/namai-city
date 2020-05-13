package socketServeur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HistoricalSensorPolluantInsert {
	
	public void insertHistoricalSensorPolluant (JSONObject JsonRecu, Connection c) throws ParseException, UnsupportedEncodingException, SQLException {

		StringBuffer sb = new StringBuffer();

		// lecture du JSON afin de mettre chaque ligne en chaîne de caractère
		InputStream inputStream = FileReader.class.getClassLoader().getSystemResourceAsStream("historicalSensorPolluant.json"); 
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
			Time hour = Time.valueOf((String) jsonObject.get("start_heure")); 
			Date date =  Date.valueOf((String) jsonObject.get("start_date")); 
			String co2 =  String.valueOf(jsonObject.get("val_co2")); 
			String no2 =  String.valueOf(jsonObject.get("val_no2"));
			String pf =  String.valueOf(jsonObject.get("val_pf"));
			String tmp =  String.valueOf(jsonObject.get("val_tmp"));
			Long id = (Long) (jsonObject.get("fk_id_capteur"));
			int foreignId = id.intValue(); 
			
			 
			System.out.println("Parcours de la liste des capteurs " + date ); 
		
			PreparedStatement stmt3 = c.prepareStatement("insert into historique_capteurpol (start_heure,start_date,val_co2,val_no2,val_pf,val_tmp,fk_id_capteur) values (?,?,?,?,?,?,?);");
			// the request takes name and first name already retrieved 
			stmt3.setTime(1,hour);
			stmt3.setDate(2,date);
			stmt3.setString(3, co2);
			stmt3.setString(4, no2);
			stmt3.setString(5, pf);
			stmt3.setString(6, tmp);
			stmt3.setInt(7, foreignId);
			// query execution 
			
			System.out.println("recupération des données"); 


			JSONObject obj=new JSONObject(); 

			// if (insertion bien passé) => executer les lignes suivantes sinon dire erreur
			if(stmt3.executeUpdate()>=1) {
				obj.put("reponse",String.valueOf("insertion reussi"));
				obj.put("start_heure", hour);
				obj.put("start_date",date);
				obj.put("val_co2",co2);
				obj.put("val_no2",no2);
				obj.put("val_pf",pf);
				obj.put("val_tmp",tmp);
				obj.put("fk_id_capteur",foreignId);
				
				System.out.println("Insertion des lignes en base faite"); 
			}
			else {
				obj.put("reponse",String.valueOf("erreur lors de l'insertion"));
			}
			System.out.println(obj);

		}
		
	}
}


