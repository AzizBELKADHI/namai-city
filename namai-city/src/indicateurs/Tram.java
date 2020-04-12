package indicateurs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Tram {

	private Connection c; 
	private Object NbStations (JSONObject JsonRecu) throws SQLException, InterruptedException {


		String date =(String) JsonRecu.get("date");
		System.out.println("bonjour voici les donnees recu apres traitement");
		System.out.println(date +  " ");


		PreparedStatement stmt1 = c.prepareStatement("select count(*) as nombre_stations from station where date = ? group by position;"); 
		stmt1.setString(1, date);
		ResultSet rs2 = stmt1.executeQuery();

		JSONObject obj=new JSONObject();
		// creation of station list 
		ArrayList<JSONObject> listStations = new ArrayList<JSONObject>(); 

		while (rs2.next()) {
			JSONObject station =new JSONObject();
			// recovery of each emergency's data 
			station.put("Id", rs2.getInt("id_station"));
			station.put("nom_station", rs2.getInt("nom_station"));
			station.put("position", rs2.getInt("position"));
			station.put("date", rs2.getString("date"));


			// adding each sensor to the list already created
			listStations.add(station);
		}
		//System.out.println("voici l'arrayList : ");
		// displaying the list 
		//System.out.println(listUsers);

		obj.put("Stations", listStations);
		System.out.println("voici le json envoyé avec le select : ");
		// displaying the Json
		System.out.println(obj);
		Thread.sleep(3000); 
		return obj; 

	}

	private Object Nbpersons_station (JSONObject JsonRecu) throws SQLException, InterruptedException {


		String date =(String) JsonRecu.get("date");
		System.out.println("bonjour voici les donnees recu apres traitement");
		System.out.println(date +  " ");


		PreparedStatement stmt1 = c.prepareStatement("select sum(qte_pers), avg(qte_pers) as moyenne_personne , max(qte_pers) as maximum_personne from frequentation_station_tram where date = ? group by id_station;"); 
		stmt1.setString(1, date);
		ResultSet rs2 = stmt1.executeQuery();

		JSONObject obj=new JSONObject();
		// creation of emergency list 
		ArrayList<JSONObject> listPersons = new ArrayList<JSONObject>(); 

		while (rs2.next()) {
			JSONObject person =new JSONObject();
			// recovery of each emergency's data 
			person.put("Id", rs2.getInt("id_freq_station"));
			person.put("position", rs2.getInt("position"));
			person.put("date", rs2.getString("date"));
			person.put("qte_pers", rs2.getString("qte_pers")); 
			person.put("Id_station", rs2.getInt("id_station"));



			// adding each sensor to the list already created
			listPersons.add(person);
		}
		//System.out.println("voici l'arrayList : ");
		// displaying the list 
		//System.out.println(listUsers);

		obj.put("Persons", listPersons);
		System.out.println("voici le json envoyé avec le select : ");
		// displaying the Json
		System.out.println(obj);
		Thread.sleep(3000); 
		return obj; 

	}

}
