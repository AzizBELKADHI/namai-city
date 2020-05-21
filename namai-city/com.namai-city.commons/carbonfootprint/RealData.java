package carbonfootprint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class RealData {
	private Connection c; 

	public Object Footprint (JSONObject JsonRecu) throws SQLException, InterruptedException {
		if (JsonRecu.get("demandType").equals("CARBON_FOOTPRINT")) {

			System.out.println("bonjour voici les donnees reelles");
			
			PreparedStatement stmt1 = c.prepareStatement("select * from Moyen_transport"); 
			ResultSet rs2 = stmt1.executeQuery();

			JSONObject obj=new JSONObject();
			// creation de la liste des données
			ArrayList<JSONObject> listRealData = new ArrayList<JSONObject>();

			while (rs2.next()) {
				JSONObject user=new JSONObject();
				// récuperation données
				user.put("id_MT", rs2.getInt("id_MT"));
				user.put("Type_MT", rs2.getString("type_MT"));
				user.put("nombre d'utilisateurs", rs2.getString("nb_utilisateurs"));
				user.put("Co2 rejete par MT", rs2.getString("co2_rejete_par_mt"));

				// adding each user to the list already created
				listRealData.add(user);


			}
			//System.out.println("voici l'arrayList : ");
			// displaying the list 
			//System.out.println(listRealData);

			obj.put("users", listRealData);
			System.out.println("voici le json envoyé avec l'affichage de la table moyen_transport: ");
			// displaying the Json
			System.out.println(obj);
			Thread.sleep(3000); 
			return obj; 
		}
		return null; 

	}
}
