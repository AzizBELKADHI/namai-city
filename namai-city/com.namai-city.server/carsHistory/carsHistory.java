package carsHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

public class carsHistory {
	public static int totalCars;
	public static int maxCars;
	private Connection c; 
	
	public carsHistory(Connection c) {
		this.c = c;
		try {
			totalCars = getCars();
			maxCars = carsLimit();
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public carsHistory() {
		try {
			totalCars = this.getCars();
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public int getCars() throws SQLException, InterruptedException {
		PreparedStatement stmtJson = c.prepareStatement("SELECT nb_voitures FROM frequentation_voiture ORDER BY date DESC LIMIT 1");
		ResultSet response = stmtJson.executeQuery();
		int cpt = 0;
		int voitures = 0;

		while (response.next())  {
			//recovery of the data of the user in question 
			cpt++;
			voitures = response.getInt("nb_voitures");
		}
		if(cpt == 0) {
			voitures = 0;
			System.out.println("erreur lors de la recuperation des vehicules dans la ville");
		}
		System.out.println("nombre de vehicules actuels dans la ville: " + voitures);
		// displaying the json 
		return voitures; 
	}
	
	public int carsLimit() throws SQLException, InterruptedException {
		PreparedStatement stmtJson = c.prepareStatement("SELECT max_voitures FROM voitures_limit");
		ResultSet response = stmtJson.executeQuery();
		int cpt = 0;
		int maxVoitures = 0;

		while (response.next())  {
			//recovery of the data of the user in question 
			cpt++;
			maxVoitures = response.getInt("max_voitures");
		}
		if(cpt == 0) {
			System.out.println("erreur lors de la recuperation du maxVehicules");
		}
		System.out.println("nombre max de vehicules autoris�: " + cpt);
		// displaying the json 
		return maxVoitures; 
	}
	
	
	public JSONObject updateMaxCars(int maxCars) throws SQLException {
		PreparedStatement stmt = c.prepareStatement("update voitures_limit set max_voitures= ?, last_update= current_timestamp;");
		stmt.setInt(1, maxCars); 
		JSONObject obj=new JSONObject(); 


		//if (update  bien pass�) => executer les lignes suivantes sinon dire erreur

		if(stmt.executeUpdate()>=1) {
			obj.put("reponse",String.valueOf("mise � jour reussie du nb_max vehicules"));
			System.out.println("mise � jour reussie du nb_max vehicules");
		}
		else {
			obj.put("reponse",String.valueOf("erreur lors de la mise a jour du max vehicules"));
		}
		System.out.println(obj);
		return obj; 
	}

	
	
	
	
	public Object addCarToHistory(String vehicule, String type, int sensorId ) throws SQLException, InterruptedException {
		System.out.println("je suis ici");
		PreparedStatement stmt = c.prepareStatement("insert into historique_vehicules values(?,?,CURRENT_TIMESTAMP,?);");
		 
		stmt.setString(1, vehicule);
		stmt.setString(2, type);
		stmt.setInt(3, sensorId);		

		JSONObject obj=new JSONObject(); 

		// if (insertion bien pass�) => executer les lignes suivantes sinon dire erreur
		if(stmt.executeUpdate()>=1) {
			obj.put("reponse",String.valueOf("insertion reussi"));
			obj.put("vehicule",String.valueOf(vehicule));
			obj.put("type",String.valueOf(type));
		}
		else {
			obj.put("reponse",String.valueOf("erreur lors de l'insertion"));
		}
		this.adjustCarsNb(vehicule,type);
		System.out.println(obj);
		return obj; 
	}
	
	
	public void adjustCarsNb(String vehicule, String type) throws SQLException, InterruptedException {
		PreparedStatement stmtGetInfos = c.prepareStatement("SELECT passage_sensor FROM historique_vehicules where voiture=? AND type=? ORDER BY schedule DESC LIMIT 1;");
		stmtGetInfos.setString(1, vehicule);
		stmtGetInfos.setString(2, type);
		ResultSet response = stmtGetInfos.executeQuery();
		int cpt = 0;
		int sensor = 0;

		while (response.next())  { 
			cpt++;
			sensor = response.getInt("passage_sensor");
		}
		if(cpt == 0) {
			sensor = 0;
		}
		
		
		PreparedStatement stmtGetSensor = c.prepareStatement("SELECT type FROM capteur_vehicule where id_capteur=?;");
		stmtGetSensor.setInt(1, sensor);
		ResultSet responseSensor = stmtGetSensor.executeQuery();
		cpt = 0;
		String sensorType = "";
		
		while (responseSensor.next())  { 
			cpt++;
			sensorType = responseSensor.getString("type");
		}
		if(cpt == 0) {
			sensorType = "SensorTypeError";
		}
		
		int currentNbCars = this.getCars();
		JSONObject obj=new JSONObject(); 
		
		if(sensorType.equals("IN")) {
			currentNbCars++;
			PreparedStatement stmt = c.prepareStatement("insert into frequentation_voiture(nb_voitures, date) values (?,CURRENT_TIMESTAMP);");
			stmt.setInt(1, currentNbCars);		

		

			if(stmt.executeUpdate()>=1) {
				obj.put("reponse",String.valueOf("nombre de v�hicules mis � jour"));

			}
			else {
				obj.put("reponse",String.valueOf("erreur lors de la mise � jour"));
			}
			System.out.println(obj); 
		}
		
		if(sensorType.equals("OUT")) {
			currentNbCars--;
			PreparedStatement stmt = c.prepareStatement("insert into frequentation_voiture(nb_voitures, date) values (?,CURRENT_TIMESTAMP);");
			stmt.setInt(1, currentNbCars);		

			if(stmt.executeUpdate()>=1) {
				obj.put("reponse",String.valueOf("nombre de v�hicules mis � jour"));

			}
			else {
				obj.put("reponse",String.valueOf("erreur lors de la mise � jour"));
			}
			System.out.println(obj); 
		}
		
		this.totalCars = currentNbCars;

		
		
	}
}
