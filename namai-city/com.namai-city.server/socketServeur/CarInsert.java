package socketServeur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CarInsert {
	/*public Object InsertCar (JSONObject JsonRecu) throws ParseException, UnsupportedEncodingException {

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

		try {
			JSONParser parser = new JSONParser(); 
			JSONArray json = (JSONArray) parser.parse(myjsonstring); 

			for (int i = 0; i < json.size(); i++) {
				JSONObject jsonObject = (JSONObject) json.get(i);
				int sensorId = Integer.valueOf("id_cap"); 
				String type = String.valueOf("type"); 
				String position = String.valueOf("position");
				Timestamp date = Timestamp.valueOf("date");

			}
		} catch (IOException  e) {
			e.printStackTrace();
		}

		// récupèrer le JSon 

		PreparedStatement stmt3 = c.prepareStatement("insert into capteur (id_cap, type, position, date) values (?,?,?,?);");
		// the request takes name and first name already retrieved 
		stmt3.setInt(1, sensorId);
		stmt3.setString(2,type);
		stmt3.setString(3,position);
		stmt3.setTimestamp(4, date); 
		// query execution 


		JSONObject obj=new JSONObject(); 

		// if (insertion bien passé) => executer les lignes suivantes sinon dire erreur
		if(stmt3.executeUpdate()>=1) {
			obj.put("reponse",String.valueOf("insertion reussi"));
			obj.put("id_cap",Integer.valueOf(sensorId));
			obj.put("type",String.valueOf(type));
			obj.put("position",String.valueOf(position));
			obj.put("date",Timestamp.valueOf(date));
		}
		else {
			obj.put("reponse",String.valueOf("erreur lors de l'insertion"));
		}
		System.out.println(obj);
		return obj; 
	}*/




}
