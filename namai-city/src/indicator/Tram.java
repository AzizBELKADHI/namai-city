package indicator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Tram {

	private Connection c; 
	private Object StationDAO (JSONObject JsonRecu) throws SQLException, InterruptedException {

		if (JsonRecu.get("demandType").equals("STATION_INDICATOR")) {
			String date =(String) JsonRecu.get("date");
			System.out.println("bonjour voici les donnees recu apres traitement");
			System.out.println(date +  " ");


			PreparedStatement stmt1 = c.prepareStatement("select *,  count(*) as nombre_stations from station where date = ? group by position;"); 
			stmt1.setString(1, date);
			ResultSet rs2 = stmt1.executeQuery();

			JSONObject obj=new JSONObject();
			// creation of station list 
			ArrayList<StationIndicator> listStations = new ArrayList<StationIndicator>(); 


			while (rs2.next()) {

				// Mapping de la classe CarIndicator (passage des résultats de la BDD en un objet java grâce au resultset 
				StationIndicator station = new StationIndicator (rs2.getInt("id_station"), rs2.getString("nom_station"), rs2.getString("position"), rs2.getTimestamp("date")); 

				// adding each sensor to the list already created
				listStations.add(station);
			}
			//System.out.println("voici l'arrayList : ");
			// displaying the list 
			//System.out.println(listUsers);

			obj.put("stations", listStations);
			System.out.println("voici le json envoyé avec le select: ");
			// displaying the Json
			System.out.println(obj);
			Thread.sleep(3000); 

			return obj;
		}
		return null; 

	}


	private Object personStationDAO (JSONObject JsonRecu) throws SQLException, InterruptedException {
		if (JsonRecu.get("demandType").equals("PERSON_STATION_INDICATOR")) {

			String date =(String) JsonRecu.get("date");
			System.out.println("bonjour voici les donnees recu apres traitement");
			System.out.println(date +  " ");


			PreparedStatement stmt1 = c.prepareStatement("select *, sum(qte_pers), avg(qte_pers) as moyenne_personne , max(qte_pers) as maximum_personne from frequentation_station_tram where date = ? group by id_station;"); 
			stmt1.setString(1, date);
			ResultSet rs2 = stmt1.executeQuery();

			JSONObject obj=new JSONObject();
			// creation of station list 
			ArrayList<PersonStationIndicator> listPersonStation = new ArrayList<PersonStationIndicator>(); 


			while (rs2.next()) {

				// Mapping de la classe CarIndicator (passage des résultats de la BDD en un objet java grâce au resultset 
				PersonStationIndicator PersonStation = new PersonStationIndicator (rs2.getInt("id_station"), rs2.getString("nom_station"),rs2.getTimestamp("date"), rs2.getInt("qte_pers"), rs2.getInt("id_Station")); 

				// adding each sensor to the list already created
				listPersonStation.add(PersonStation);
			}
			//System.out.println("voici l'arrayList : ");
			// displaying the list 
			//System.out.println(listUsers);

			obj.put("PersonStations", listPersonStation);
			System.out.println("voici le json envoyé avec le select: ");
			// displaying the Json
			System.out.println(obj);
			Thread.sleep(3000); 

			return obj;
		}
		return null; 

	}
}