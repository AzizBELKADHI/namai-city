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


public class CarInsert {
	
public void insertCar (JSONObject JsonRecu, Connection c) throws ParseException, UnsupportedEncodingException, SQLException {

		StringBuffer sb = new StringBuffer();

		// lecture du JSON afin de mettre chaque ligne en chaîne de caractère
		InputStream inputStream = FileReader.class.getClassLoader().getSystemResourceAsStream("cars.json"); 
		BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")); 
		try {
			String temp; 
			while ((temp = bufferedReader2.readLine()) != null) 
				sb.append(temp); 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader2.close();
			} catch (IOException e) {
				e.printStackTrace(); 
			}
		}
		String myjsonstring = sb.toString(); 

		JSONParser parser = new JSONParser(); 
		JSONArray json = (JSONArray) parser.parse(myjsonstring); 

		for (int i = 0; i < json.size(); i++) {
			JSONObject jsonObject = (JSONObject) json.get(i);
			Long nb = (Long) (jsonObject.get("nb_voitures"));
			int carsNb = nb.intValue(); 
			Long id2 = (Long) (jsonObject.get("id_cap"));
			int sensorId = id2.intValue(); 
			Timestamp date = Timestamp.valueOf((String) jsonObject.get("date")); 
			System.out.println("Parcours de la liste des voitures Fréquentés  " + carsNb); 
		
			PreparedStatement stmt3 = c.prepareStatement("insert into Frequentation_Voiture (nb_voitures, id_cap, date) values (?,?,?);");
			// the request takes name and first name already retrieved 
			stmt3.setInt(1,carsNb);
			stmt3.setInt(2,sensorId);
			stmt3.setTimestamp(3, date); 
			// query execution 
			
			System.out.println("recupération des données"); 


			JSONObject obj=new JSONObject(); 

			// if (insertion bien passé) => executer les lignes suivantes sinon dire erreur
			if(stmt3.executeUpdate()>=1) {
				obj.put("reponse",String.valueOf("insertion reussi"));
				obj.put("nb_voitures", carsNb);
				obj.put("id_cap",sensorId);
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

