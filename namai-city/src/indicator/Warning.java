package indicator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Warning {

	private Connection c; 

	private Object WarmingDAO (JSONObject JsonRecu) throws SQLException, InterruptedException {
		if (JsonRecu.get("demandType").equals("WARMING_INDICATOR")) {

			String date =(String) JsonRecu.get("date");
			System.out.println("bonjour voici les donnees recu apres traitement");
			System.out.println(date +  " ");


			PreparedStatement stmt1 = c.prepareStatement("select *,  count(*) as nombre_alerte, seuil, max(nombre_alerte), avg(nombre_alerte) from historique_alerte where date = ? group by position;"); 
			stmt1.setString(1, date);
			ResultSet rs2 = stmt1.executeQuery();

			JSONObject obj=new JSONObject();
			// creation of warming list 
			ArrayList<WarningIndicator> listWarning = new ArrayList<WarningIndicator>(); 


			while (rs2.next()) {

				// Mapping de la classe WarningIndicator (passage des résultats de la BDD en un objet java grâce au resultset 
				WarningIndicator warning = new WarningIndicator(rs2.getInt("id_alerte"), rs2.getString("alerte_etat"), rs2.getInt("id_seuil"), rs2.getInt("seuil"), rs2.getTimestamp("date")); 

				// adding each sensor to the list already created
				listWarning.add(warning);
			}
			//System.out.println("voici l'arrayList : ");
			// displaying the list 
			//System.out.println(listUsers);

			obj.put("warnings", listWarning);
			System.out.println("voici le json envoyé avec le select: ");
			// displaying the Json
			System.out.println(obj);
			Thread.sleep(3000); 

			return obj;
		}
		return null; 
	}
}
