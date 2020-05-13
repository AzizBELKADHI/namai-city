package indicator;

import java.sql.Timestamp;

import org.json.simple.JSONObject;

public class StationIndicator {
	
	private int stationId; 
	private String stationName; 
	private String position; 
	private int stationNb; 
	
	
	public int getStationNb() {
		return stationNb;
	}

	public void setStationNb(int stationNb) {
		this.stationNb = stationNb;
	}

	public StationIndicator() {
		
	}
	
	@Override
	public String toString() {
		return "StationIndicator [stationId=" + stationId + ", stationName=" + stationName + ", position=" + position
				+ ", stationNb=" + stationNb + "]";
	}

	public StationIndicator(int idS, String name, String p, int nb) {
		stationId = idS; 
		stationName = name; 
		position = p;
		stationNb = nb; 
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public JSONObject convertToJSON() {
		JSONObject json = new JSONObject();
		json.put("id_station", stationId);
		json.put("nom_station", stationName); 
		json.put("position", position); 
		json.put("nombre_stations_position", stationNb); 
		
		return json;
	}
	
	public void convertFromJson(JSONObject json) {
		this.stationId = Long.valueOf(String.valueOf(json.get("id_station"))).intValue();
		this.position = String.valueOf(json.get("position")); 
		this.stationNb = Long.valueOf(String.valueOf(json.get("nombre_stations_position"))).intValue();
	}
}
	
	