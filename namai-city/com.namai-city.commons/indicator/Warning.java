package indicator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Warning {

	public Object warmingDAO (JSONObject JsonRecu, Connection c) {
		JSONObject obj=new JSONObject();

		if (JsonRecu.get("demandType").equals("WARMING_INDICATOR")) {
			System.out.println("execution de la requête"); 
			
			try {
				PreparedStatement stmt1 = c.prepareStatement("select count(*) as nb_alerte, date_debut, position from historique_alerte group by (position,date_debut);"); 
				ResultSet rs2 = stmt1.executeQuery();
				System.out.println("requête exécutée)"); 
				
				// creation of warming list 
				ArrayList<JSONObject> listWarning = new ArrayList<JSONObject>();
				System.out.println(rs2.getFetchSize());
				System.out.println("mapping classe WarningIndicator");

				while (rs2.next()) {

					//Mapping de la classe WarningIndicator (passage des résultats de la BDD en un objet java grâce au resultset 
					WarningIndicator warning = new WarningIndicator(0,0,null,0,0,rs2.getString("position"), rs2.getTimestamp("date_debut"), null, rs2.getInt("nb_alerte"));
					System.out.println("récuperation des résultats du select"); 
					obj= warning.convertToJSON();
				// adding each sensor to the list already created
				listWarning.add(obj);
				System.out.println("ajout d'une alerte  dans la liste de l'historique"); 
				}
				//System.out.println("voici l'arrayList : ");
				// displaying the list 
				//System.out.println(listUsers);

				obj.put("warnings", listWarning);
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
}
