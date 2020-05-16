package indicator;

import java.sql.Timestamp;

import org.json.simple.JSONObject;

public class SensorIndicator {
	
	private int sensorId; 
	private String type; 
	private String position; 
	private Timestamp date;
	private int sensorNb; 

	// partie borne: 
	
	private int borneId; 
	private String positionBorne;  
	private int borneNb; 
	
	//partie capteur_véhicules 
	private int sensorIdCar; 
	private String positionSensorCar; 
	private int sensorCarNb; 
	
	//partie capteur_polluant: 
	
	private int idPolluant; 
	private String localisation; 
	private int sensorPolluantNb; 
	

	public SensorIndicator() {
		
	}
	
	public SensorIndicator(int id, String t, String p, Timestamp d,int snb) {
		sensorId = id; 
		type = t; 
		position = p; 
		date = d; 
		sensorNb = snb; 
	}
	

	public SensorIndicator (int id, String p,int nb,  int id2, String p2,int nb2, int id3, String p3,int nb3) {
		borneId = id; 
		positionBorne = p; 
		borneNb = nb; 
		sensorIdCar = id2; 
		positionSensorCar = p2; 
		sensorCarNb = nb2; 
		idPolluant = id3; 
		localisation = p3; 
		sensorPolluantNb = nb3; 
		
		
	}
	public SensorIndicator(int id, String l, int nb) {
		borneId = id; 
		localisation =l ; 
		borneNb = nb;
	}
	public int getBorneId() {
		return borneId;
	}

	public void setBorneId(int borneId) {
		this.borneId = borneId;
	}

	
	public String getPositionBorne() {
		return positionBorne;
	}

	public void setPositionBorne(String positionBorne) {
		this.positionBorne = positionBorne;
	}

	public int getSensorIdCar() {
		return sensorIdCar;
	}

	public void setSensorIdCar(int sensorIdCar) {
		this.sensorIdCar = sensorIdCar;
	}

	public String getPositionSensorCar() {
		return positionSensorCar;
	}

	public void setPositionSensorCar(String positionSensorCar) {
		this.positionSensorCar = positionSensorCar;
	}

	public int getIdPolluant() {
		return idPolluant;
	}

	public void setIdPolluant(int idPolluant) {
		this.idPolluant = idPolluant;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public int getSensorNb() {
		return sensorNb;
	}

	public void setSensorNb(int sensorNb) {
		this.sensorNb = sensorNb;
	}
	public int getSensorId() {
		return sensorId;
	}
	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	public int getBorneNb() {
		return borneNb;
	}

	public void setBorneNb(int borneNb) {
		this.borneNb = borneNb;
	}

	public int getSensorCarNb() {
		return sensorCarNb;
	}

	public void setSensorCarNb(int sensorCarNb) {
		this.sensorCarNb = sensorCarNb;
	}

	public int getSensorPolluantNb() {
		return sensorPolluantNb;
	}

	public void setSensorPolluantNb(int sensorPolluantNb) {
		this.sensorPolluantNb = sensorPolluantNb;
	}

	public JSONObject convertToJSON() {
		JSONObject json = new JSONObject();
		json.put("id_cap", sensorId);
		json.put("type", type); 
		json.put("position", position); 
		json.put("nombre_capteur_position", sensorNb); 
		//json.put("date", date); 
		
		json.put("id_borne",borneId); 
		json.put("position",positionBorne);
		json.put("nombre_bornes_position",borneNb);
		
		json.put("id_capteur",sensorIdCar); 
		json.put("position",positionSensorCar);
		json.put("nombre_capteur_voiture", sensorCarNb); 
		
		
		json.put("id",idPolluant); 
		json.put("localisation",localisation);
		json.put("nombre_capteur_polluant",sensorPolluantNb);
		
		
		return json;
	}
	
	public void convertFromJson(JSONObject json) {
		this.sensorId = Long.valueOf(String.valueOf(json.get("id_cap"))).intValue();
		this.type = String.valueOf(json.get("type")); 
		this.position = String.valueOf(json.get("position")); 
		//this.date = Timestamp.valueOf((String) json.get("date"));		
		this.sensorNb = Long.valueOf(String.valueOf(json.get("nombre_capteur_position"))).intValue(); 
		
		this.borneId = Long.valueOf(String.valueOf(json.get("id_borne"))).intValue();
		this.positionBorne = String.valueOf(json.get("position")); 
		this.borneNb = Long.valueOf(String.valueOf(json.get("nombre_bornes_position"))).intValue();
		
		this.sensorIdCar = Long.valueOf(String.valueOf(json.get("id_capteur"))).intValue();
		this.positionSensorCar = String.valueOf(json.get("position")); 
		this.sensorCarNb = Long.valueOf(String.valueOf(json.get("nombre_capteur_voiture"))).intValue();
		
		this.idPolluant = Long.valueOf(String.valueOf(json.get("id"))).intValue();
		this.localisation = String.valueOf(json.get("localisation"));
		this.sensorPolluantNb = Long.valueOf(String.valueOf(json.get("nombre_capteur_polluant"))).intValue();
	}
	
}
