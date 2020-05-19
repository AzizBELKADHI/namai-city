package indicator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Tram {

	public JSONObject stationDAO (JSONObject JsonRecu,Connection c) throws SQLException, InterruptedException {
		JSONObject obj=new JSONObject();
		if (JsonRecu.get("demandType").equals("STATION_INDICATOR")) {
			System.out.println("execution de la requ�te "); 
			try {
				PreparedStatement stmt1 = c.prepareStatement("select count(*) as nombre_stations_position, position from station group by position;"); 
				ResultSet rs2 = stmt1.executeQuery();

				System.out.println("requ�te ex�cut�e "); 

				// creation of station list 
				ArrayList<JSONObject> listStations = new ArrayList<JSONObject>();

				//System.out.println(rs2.getFetchSize());
				System.out.println("mapping classe StationIndicator");
				while (rs2.next()) {

					// Mapping de la classe CarIndicator (passage des r�sultats de la BDD en un objet java gr�ce au resultset 
					StationIndicator station = new StationIndicator (0, null, rs2.getString("position"),rs2.getInt("nombre_stations_position"));

					System.out.println("r�cuperation des r�sultats du select"); 
					JSONObject stationJSON= station.convertToJSON();
					// adding each sensor to the list already created
					listStations.add(stationJSON);
					System.out.println("ajout d'une station dans la liste des stations"); 
				}
				//System.out.println("voici l'arrayList : ");
				// displaying the list 
				//System.out.println(listUsers);

				obj.put("stations", listStations);
				System.out.println("=====================================================================================================");
				System.out.println("voici le json envoy� avec le select: ");
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




}
