package indicator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Car {
	private Connection c; 

	public Object CarDAO (JSONObject JsonRecu) throws SQLException, InterruptedException {
		if (JsonRecu.get("demandType").equals("CAR_INDICATOR")) {

			String date =(String) JsonRecu.get("date");
			System.out.println("bonjour voici les donnees recu apres traitement");
			System.out.println(date +  " ");

			PreparedStatement stmt1 = c.prepareStatement("select *, sum(nb_voitures) as nombre_voitures, avg(nb_voitures), max(nb_voitures) from Frequentation_Voiture where date = ? group by position;"); 
			stmt1.setString(1, date);
			ResultSet rs2 = stmt1.executeQuery();

			JSONObject obj=new JSONObject();
			// creation of car list 
			ArrayList<CarIndicator> listCars = new ArrayList<CarIndicator>(); 


			while (rs2.next()) {

				// Mapping de la classe CarIndicator (passage des résultats de la BDD en un objet java grâce au resultset 
				CarIndicator car = new CarIndicator(rs2.getInt("id_voit"), rs2.getTimestamp("date"), rs2.getInt("nb_voitures"), rs2.getInt("id_cap")); 

				// adding each sensor to the list already created
				listCars.add(car);
			}
			//System.out.println("voici l'arrayList : ");
			// displaying the list 
			//System.out.println(listUsers);

			obj.put("cars", listCars);
			System.out.println("voici le json envoyé avec le select: ");
			// displaying the Json
			System.out.println(obj);
			Thread.sleep(3000); 

			return obj;
		}
		return null; 

	}
}
