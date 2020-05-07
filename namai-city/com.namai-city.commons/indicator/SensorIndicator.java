package indicator;

import java.sql.Timestamp;

import org.json.simple.JSONObject;

public class SensorIndicator {
	
	private int sensorId; 
	private String type; 
	private String position; 
	private Timestamp date;
	private int sensorNb; 

	

	public SensorIndicator() {
		
	}
	
	public SensorIndicator(int id, String t, String p, Timestamp d,int snb) {
		sensorId = id; 
		type = t; 
		position = p; 
		date = d; 
		sensorNb = snb; 
	}
	
	@Override
	public String toString() {
		return "SensorIndicator [sensorId=" + sensorId + ", type=" + type + ", position=" + position + ", date=" + date
				+ ", sensorNb=" + sensorNb + "]";
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
	
	public JSONObject convertToJSON() {
		JSONObject json = new JSONObject();
		json.put("id_cap", sensorId);
		json.put("type", type); 
		json.put("position", position); 
		//json.put("date", date); 
		json.put("nombre_capteur_position",sensorNb); 
		
		return json;
	}
	
	public void convertFromJson(JSONObject json) {
		this.sensorId = Long.valueOf(String.valueOf(json.get("id_cap"))).intValue();
		this.type = String.valueOf(json.get("type")); 
		this.position = String.valueOf(json.get("position")); 
		//this.date = Timestamp.valueOf((String) json.get("date"));		
		this.sensorNb = Long.valueOf(String.valueOf(json.get("nombre_capteur_position"))).intValue(); 
	}
	
}
