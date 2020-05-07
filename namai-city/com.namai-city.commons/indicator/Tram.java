package indicator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Tram {

	public Object stationDAO (JSONObject JsonRecu,Connection c) throws SQLException, InterruptedException {
		JSONObject obj=new JSONObject();
		if (JsonRecu.get("demandType").equals("STATION_INDICATOR")) {
			System.out.println("execution de la requête "); 
			try {
				PreparedStatement stmt1 = c.prepareStatement("select count(*) as nombre_stations_position, position from station group by position;"); 
				ResultSet rs2 = stmt1.executeQuery();
				
				System.out.println("requête exécutée "); 
				
				// creation of station list 
				ArrayList<JSONObject> listStations = new ArrayList<JSONObject>();
				
				System.out.println(rs2.getFetchSize());
				System.out.println("mapping classe StationIndicator");
				while (rs2.next()) {

					// Mapping de la classe CarIndicator (passage des résultats de la BDD en un objet java grâce au resultset 
					StationIndicator station = new StationIndicator (0, null, rs2.getString("position"),rs2.getInt("nombre_stations_position"));
					
					System.out.println("récuperation des résultats du select"); 
					obj= station.convertToJSON();
					// adding each sensor to the list already created
					listStations.add(obj);
					System.out.println("ajout d'une station dans la liste des stations"); 
				}
				//System.out.println("voici l'arrayList : ");
				// displaying the list 
				//System.out.println(listUsers);

				obj.put("stations", listStations);
				System.out.println("=====================================================================================================");
				System.out.println("voici le json envoyé avec le select: ");
				// displaying the Json
				System.out.println(obj);
				//Thread.sleep(3000);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return obj; 
	}
		
		
		// requête pour le nombre de stations totale dans la ville : select count(*) as nb_stations_totale from station;  
	public Object personStationDAO (JSONObject JsonRecu,Connection c){
		JSONObject obj=new JSONObject();
		if (JsonRecu.get("demandType").equals("PERSON_STATION_INDICATOR")) {
			
			System.out.println("execution de la requête"); 
			
			try {
				PreparedStatement stmt1 = c.prepareStatement("select sum(qte_pers) as nombre_personne_position, position,date_mesure from frequentation_station_tram group by (position,date_mesure);"); 
				ResultSet rs2 = stmt1.executeQuery();
				System.out.println("requête exécutée)"); 
				
				// creation of station list 
				ArrayList<JSONObject> listPersonStation = new ArrayList<JSONObject>(); 
				System.out.println(rs2.getFetchSize());
				System.out.println("mapping classe PersonStationIndicator");


				while (rs2.next()) {

					// Mapping de la classe CarIndicator (passage des résultats de la BDD en un objet java grâce au resultset 
					PersonStationIndicator personStation = new PersonStationIndicator (0 ,rs2.getString("position"),rs2.getInt("qte_pers"),0, null, rs2.getTimestamp("date_mesure"),rs2.getInt("nombre_personne_position"));
					
					System.out.println("récuperation des résultats du select"); 
					obj= personStation.convertToJSON();
					// adding each sensor to the list already created
					listPersonStation.add(obj);
					System.out.println("ajout d'un groupe de personne  dans la liste des personnes par station"); 
				}
				//System.out.println("voici l'arrayList : ");
				// displaying the list 
				//System.out.println(listUsers);

				obj.put("PersonStations", listPersonStation);
				System.out.println("=====================================================================================================");

				System.out.println("voici le json envoyé avec le select: ");
				// displaying the Json
				System.out.println(obj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
			return obj;
		} 
		
		//ajouter requête taux de fréquentation de chaque zone en Tram 
		// ajouter requete nombre de pers par position

	}
