package indicator;

import java.sql.Timestamp;

public class StationIndicator {
	
	private int stationId; 
	private String stationName; 
	private String position; 
	private Timestamp date;
	
	public StationIndicator() {
		
	}
	
	public StationIndicator(int idS, String name, String p, Timestamp d) {
		stationId = idS; 
		stationName = name; 
		position = p; 
		date = d; 
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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
}
	
	