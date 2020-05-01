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

public class WarningInsert {

public void warningHistoricalInsert (JSONObject JsonRecu, Connection c) throws ParseException, UnsupportedEncodingException, SQLException {

		StringBuffer sb = new StringBuffer();

		// lecture du JSON afin de mettre chaque ligne en chaîne de caractère
		// reading the json in order to put each line into a string 
		// a stocké les lignes du fichier json dans un string 
		InputStream inputStream = FileReader.class.getClassLoader().getSystemResourceAsStream("warning_historical.json"); 
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
		// conversion de ce string en jsonArray 
// le mock possede ce jsonArray contenant les differentes données de l'historique de chaque alerte à insérer 
		for (int i = 0; i < json.size(); i++) {
			JSONObject jsonObject = (JSONObject) json.get(i);
			Long id = (Long) (jsonObject.get("id_seuil"));
			int thresholdId = id.intValue(); 
			String warningState = String.valueOf(jsonObject.get("alerte_etat"));
			Long th =  (Long) jsonObject.get("seuil");
			int threshold = th.intValue(); 
			Long thM = (Long) (jsonObject.get("seuil_max"));
			int thresholdMax = thM.intValue(); 
			String position = String.valueOf(jsonObject.get("position"));
			Timestamp dateStart = Timestamp.valueOf((String) jsonObject.get("date_debut"));
			Timestamp dateEnd = Timestamp.valueOf((String) jsonObject.get("date_fin")); 
			System.out.println("Parcours de la liste de l'historique des alertes avec leur seuil dans le jsonArray  : " + threshold); 
		
			
			PreparedStatement stmt3 = c.prepareStatement("insert into historique_alerte (id_seuil,alerte_etat, seuil ,seuil_max, position,  date_debut, date_fin) values (?,?,?,?,?,?,?);");
			// la requête récupère les données insérées dans la json et donc dans le JsonArray afin de les insérer en base 
		
			stmt3.setInt(1, thresholdId);
			stmt3.setString(2,warningState);
			stmt3.setInt(3,threshold);
			stmt3.setInt(4,thresholdMax);
			stmt3.setString(5,position);
			stmt3.setTimestamp(6, dateStart); 
			stmt3.setTimestamp(7, dateEnd); 
			// query execution 
			
			System.out.println("recupération des données"); 

			
			JSONObject obj=new JSONObject(); 

			// if (insertion bien passé) => executer les lignes suivantes sinon dire erreur
			if(stmt3.executeUpdate()>=1) {
				obj.put("reponse",String.valueOf("insertion reussi"));
				obj.put("id_seuil",thresholdId);
				obj.put("alerte_etat", warningState);
				obj.put("seuil",threshold);
				obj.put("seuilMax",thresholdMax);
				obj.put("position",position);
				obj.put("date_debut",dateStart);
				obj.put("date_fin",dateEnd);
				
				System.out.println("Insertion des lignes en base faite"); 
			}
			else {
				obj.put("reponse",String.valueOf("erreur lors de l'insertion"));
			}
			System.out.println(obj);

		}
		
	}
}
