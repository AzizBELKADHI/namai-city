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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SensorPolluantInsert {
	
	public void insertSensorPolluant (JSONObject JsonRecu, Connection c) throws ParseException, UnsupportedEncodingException, SQLException {

		StringBuffer sb = new StringBuffer();

		// lecture du JSON afin de mettre chaque ligne en chaîne de caractère
		InputStream inputStream = FileReader.class.getClassLoader().getSystemResourceAsStream("SensorPolluant.json"); 
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
			String adresseIP = String.valueOf(jsonObject.get("adresse_ip")); 
			String position =  String.valueOf(jsonObject.get("localisation")); 
			String co2 =  String.valueOf(jsonObject.get("seuil_co2")); 
			String no2 =  String.valueOf(jsonObject.get("seuil_no2"));
			String pf =  String.valueOf(jsonObject.get("seuil_pf"));
			String tmpMin =  String.valueOf(jsonObject.get("seuil_min_tmp"));
			String tmpMax =  String.valueOf(jsonObject.get("seuil_max_tmp"));
			 
			System.out.println("Parcours de la liste des capteurs " + position ); 
		
			PreparedStatement stmt3 = c.prepareStatement("insert into capteur_polluant (adresse_ip,localisation,seuil_co2,seuil_no2,seuil_pf,seuil_min_tmp,seuil_max_tmp) values (?,?,?,?,?,?,?);");
			// the request takes name and first name already retrieved 
			stmt3.setString(1,adresseIP);
			stmt3.setString(2,position);
			stmt3.setString(3, co2);
			stmt3.setString(4, no2);
			stmt3.setString(5, pf);
			stmt3.setString(6, tmpMin);
			stmt3.setString(7, tmpMax);
			// query execution 
			
			System.out.println("recupération des données"); 


			JSONObject obj=new JSONObject(); 

			// if (insertion bien passé) => executer les lignes suivantes sinon dire erreur
			if(stmt3.executeUpdate()>=1) {
				obj.put("reponse",String.valueOf("insertion reussi"));
				obj.put("adresse_ip", adresseIP);
				obj.put("localisation",position);
				obj.put("seuil_co2",co2);
				obj.put("seuil_no2",no2);
				obj.put("seuil_pf",pf);
				obj.put("seuil_min_tmp",tmpMin);
				obj.put("seuil_max_tmp",tmpMax);
				
				System.out.println("Insertion des lignes en base faite"); 
			}
			else {
				obj.put("reponse",String.valueOf("erreur lors de l'insertion"));
			}
			System.out.println(obj);

		}
		
	}
}


