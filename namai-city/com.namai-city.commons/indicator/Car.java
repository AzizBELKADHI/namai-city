package indicator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Car {
	

	public Object carDAO (JSONObject JsonRecu, Connection c) {
		JSONObject obj=new JSONObject();
		if (JsonRecu.get("demandType").equals("CAR_INDICATOR")) {

			try {
				PreparedStatement stmt1 = c.prepareStatement("select sum(nb_voitures) as nombre_voitures_total_date, date from Frequentation_Voiture group by (date);"); 

				System.out.println("execution de la requête");
				ResultSet rs2 = stmt1.executeQuery();
				System.out.println("requête exécutée)"); 

				// creation of car list 
				ArrayList<JSONObject> listCars = new ArrayList<JSONObject>();
				System.out.println(rs2.getFetchSize());
				System.out.println("mapping classe CarIndicator");

				while (rs2.next()) {

					// Mapping de la classe CarIndicator (passage des résultats de la BDD en un objet java grâce au resultset 
					CarIndicator car = new CarIndicator(0, rs2.getInt("nb_voitures"), 0, rs2.getTimestamp("date"), rs2.getInt("nombre_voitures_total_date"));
					System.out.println("récuperation des résultats du select"); 
					obj= car.convertToJSON();
					// adding each sensor to the list already created
					listCars.add(obj);
					System.out.println("ajout d'une frequentation de voitures dans la liste des voitures"); 

				}
				//System.out.println("voici l'arrayList : ");
				// displaying the list 
				//System.out.println(listUsers);

				obj.put("cars", listCars);
				System.out.println("voici le json envoyé avec le select permettant de récupérer le nombre de voiture dans la ville: ");
				// displaying the Json
				System.out.println(obj);
				Thread.sleep(3000);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return obj;
	}
}

