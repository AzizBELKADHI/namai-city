package indicator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class SensorPolluant {

	public JSONObject getIndicator (JSONObject JsonRecu, Connection c){
		JSONObject obj=new JSONObject();

		if (JsonRecu.get("demandType").equals("SENSOR_POLLUANT_INDICATOR")) {
			try {
				PreparedStatement stmt1 = c.prepareStatement("select count(*) as nb_alerte, localisation from historique_capteurpol, capteur_polluant where historique_capteurpol.fk_id_capteur = capteur_polluant.id group by (localisation);");
				ResultSet rs2 = stmt1.executeQuery();


				// creation of users list 
				ArrayList<JSONObject> listSensorPolluant = new ArrayList<JSONObject>();

				while (rs2.next()) {
					SensorPolluantIndicator sensor = new SensorPolluantIndicator(0,null, rs2.getString("localisation"), rs2.getInt("nb_alerte"),null,null,null,null); 
					//System.out.println("récuperation des résultats du select"); 
					JSONObject sensorJSON = sensor.convertToJSON();

					// adding each user to the list already created
					listSensorPolluant.add(sensorJSON);


				}
				//System.out.println("voici l'arrayList : ");
				// displaying the list 
				//System.out.println(listUsers);

				obj.put("sensorsPolluant", listSensorPolluant);
				System.out.println("voici le json envoyé avec getIndicator ");
				// displaying the Json
				System.out.println(obj);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		return obj;
	}
	// récupération de tous les id des capteurs polluants pour la comboBox 

	public JSONObject getIdSensorPolluant (JSONObject JsonRecu, Connection c){
		JSONObject obj=new JSONObject();

		if (JsonRecu.get("demandType").equals("getIdSensorPolluant")) {
			try {
				PreparedStatement stmt1 = c.prepareStatement("select id from capteur_polluant; ");
				ResultSet rs2 = stmt1.executeQuery();


				// creation of users list 
				ArrayList<JSONObject> listSensorIdPolluant = new ArrayList<JSONObject>();

				while (rs2.next()) {
					SensorPolluantIndicator sensor = new SensorPolluantIndicator( rs2.getInt("id")); 
					//System.out.println("récuperation des résultats du select"); 
					JSONObject sensorJSON = sensor.convertToJSON();

					// adding each user to the list already created
					listSensorIdPolluant.add(sensorJSON);


				}
				//System.out.println("voici l'arrayList : ");
				// displaying the list 
				//System.out.println(listUsers);

				obj.put("sensorsIdPolluant", listSensorIdPolluant);
				System.out.println("voici le json envoyé avec getIndicator ");
				// displaying the Json
				System.out.println(obj);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		return obj;
	}

	// récupération de chaque seuil selon le polluant demandé (CO2 / NO2 / pf/ tmp) 
	public JSONObject getThreshold (JSONObject JsonRecu, Connection c) {
		JSONObject obj=new JSONObject();

		if (JsonRecu.get("demandType").equals("getThresholdSensorPolluant")) {
			String polluantName =(String) JsonRecu.get("nomPolluant");
			System.out.println("bonjour voici le polluant recu apres traitement");
			System.out.println(polluantName);
		
			if (polluantName.equals("CO2")) {
				System.out.println("Je suis dans le if"); 
				long idcaste = Long.valueOf(JsonRecu.get("Id").toString());
				int id = (int) idcaste; 
				System.out.println("bonjour voici le ID recu apres traitement");
				System.out.println(id);
				
				try {
					PreparedStatement stmt1 = c.prepareStatement("select seuil_co2 from capteur_polluant where id = ?;");
					stmt1.setInt(1, id);
					ResultSet rs2 = stmt1.executeQuery();

					ArrayList<JSONObject> listPolluantCo2 = new ArrayList<JSONObject>();
					while (rs2.next()) {
						SensorPolluantIndicator sensor = new SensorPolluantIndicator(rs2.getString("seuil_co2"),null,null, null, null); 
						//System.out.println("récuperation des résultats du select"); 
						JSONObject sensorJSON = sensor.convertToJSON();

						// adding each user to the list already created
						listPolluantCo2.add(sensorJSON);

					}
					//System.out.println("voici l'arrayList : ");
					// displaying the list 
					//System.out.println(listUsers);

					obj.put("sensorsPolluantCo2", listPolluantCo2);
					System.out.println("voici le json envoyé avec getIndicator ");
					// displaying the Json
					System.out.println(obj);


				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();


				}
			} else if (polluantName.equals("NO2")) {
				System.out.println("Je suis dans le if"); 
				long idcaste = Long.valueOf(JsonRecu.get("Id").toString());
				int id = (int) idcaste; 
				System.out.println("bonjour voici le ID recu apres traitement");
				System.out.println(id);
				
				try {
					PreparedStatement stmt1 = c.prepareStatement("select seuil_no2 from capteur_polluant where id = ?;");
					stmt1.setInt(1, id);
					ResultSet rs2 = stmt1.executeQuery();

					ArrayList<JSONObject> listPolluantNo2 = new ArrayList<JSONObject>();
					while (rs2.next()) {
						SensorPolluantIndicator sensor = new SensorPolluantIndicator(null,rs2.getString("seuil_no2"),null, null, null); 
						//System.out.println("récuperation des résultats du select"); 
						JSONObject sensorJSON = sensor.convertToJSON();

						// adding each user to the list already created
						listPolluantNo2.add(sensorJSON);

					}
					//System.out.println("voici l'arrayList : ");
					// displaying the list 
					//System.out.println(listUsers);

					obj.put("sensorsPolluantNo2", listPolluantNo2);
					System.out.println("voici le json envoyé avec getIndicator ");
					// displaying the Json
					System.out.println(obj);


				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();


				}
				
			} else if (polluantName.equals("PF")) {
				System.out.println("Je suis dans le if"); 
				long idcaste = Long.valueOf(JsonRecu.get("Id").toString());
				int id = (int) idcaste; 
				System.out.println("bonjour voici le ID recu apres traitement");
				System.out.println(id);
				
				try {
					PreparedStatement stmt1 = c.prepareStatement("select seuil_pf from capteur_polluant where id = ?;");
					stmt1.setInt(1, id);
					ResultSet rs2 = stmt1.executeQuery();

					ArrayList<JSONObject> listPolluantPf = new ArrayList<JSONObject>();
					while (rs2.next()) {
						SensorPolluantIndicator sensor = new SensorPolluantIndicator(null,null,rs2.getString("seuil_pf"), null, null); 
						//System.out.println("récuperation des résultats du select"); 
						JSONObject sensorJSON = sensor.convertToJSON();

						// adding each user to the list already created
						listPolluantPf.add(sensorJSON);

					}
					//System.out.println("voici l'arrayList : ");
					// displaying the list 
					//System.out.println(listUsers);

					obj.put("sensorsPolluantPf", listPolluantPf);
					System.out.println("voici le json envoyé avec getIndicator ");
					// displaying the Json
					System.out.println(obj);


				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();


				}
			} else if (polluantName.equals("TMP")) {
				System.out.println("Je suis dans le if"); 
				long idcaste = Long.valueOf(JsonRecu.get("Id").toString());
				int id = (int) idcaste; 
				System.out.println("bonjour voici le ID recu apres traitement");
				System.out.println(id);
				
				try {
					PreparedStatement stmt1 = c.prepareStatement("select seuil_min_tmp,seuil_max_tmp from capteur_polluant where id = ?;");
					stmt1.setInt(1, id);
					ResultSet rs2 = stmt1.executeQuery();

					ArrayList<JSONObject> listPolluantTmp = new ArrayList<JSONObject>();
					while (rs2.next()) {
						SensorPolluantIndicator sensor = new SensorPolluantIndicator(null,null,null, rs2.getString("seuil_min_tmp"),rs2.getString("seuil_max_tmp")); 
						//System.out.println("récuperation des résultats du select"); 
						JSONObject sensorJSON = sensor.convertToJSON();

						// adding each user to the list already created
						listPolluantTmp.add(sensorJSON);

					}
					//System.out.println("voici l'arrayList : ");
					// displaying the list 
					//System.out.println(listUsers);

					obj.put("sensorsPolluantTmp", listPolluantTmp);
					System.out.println("voici le json envoyé avec getIndicator ");
					// displaying the Json
					System.out.println(obj);


				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
		return obj;
	}


	public JSONObject getWarning (JSONObject JsonRecu, Connection c) {
		JSONObject obj=new JSONObject();

		if (JsonRecu.get("demandType").equals("getWarningPolluant")) {
			long idcaste = Long.valueOf(JsonRecu.get("fk_id_capteur").toString());
			int id = (int) idcaste;

			try {

				PreparedStatement stmt1 = c.prepareStatement("select * from historique_capteurpol where fk_id_capteur = ?;");
				stmt1.setInt(1, id);
				ResultSet rs2 = stmt1.executeQuery();

				ArrayList<JSONObject> listPolluantAlerte = new ArrayList<JSONObject>();
				while (rs2.next()) {
					SensorPolluantIndicator sensor = new SensorPolluantIndicator(0,rs2.getTimestamp("start_date"), rs2.getString("val_co2"),rs2.getString("val_no2"),rs2.getString("val_pf"),rs2.getString("val_tmp"), rs2.getInt("fk_id_capteur")); 
					//System.out.println("récuperation des résultats du select"); 
					JSONObject sensorJSON = sensor.convertToJSON();

					// adding each user to the list already created
					listPolluantAlerte.add(sensorJSON);


				}
				//System.out.println("voici l'arrayList : ");
				// displaying the list 
				//System.out.println(listUsers);

				obj.put("sensorsPolluantWarning", listPolluantAlerte);
				System.out.println("voici le json envoyé avec getIndicator ");
				// displaying the Json
				System.out.println(obj);


			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}
		}

		return obj;
	}
	
	
}





