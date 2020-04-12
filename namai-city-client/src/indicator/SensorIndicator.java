package indicator;

import java.sql.Timestamp;

public class SensorIndicator {
	
	private int idCap; 
	private String type; 
	private String position; 
	private Timestamp date; 

	public SensorIndicator() {
		
	}
	
	public SensorIndicator(int id, String t, String p, Timestamp d) {
		idCap = id; 
		type = t; 
		position = p; 
		date = d; 
	}
	
	public int getIdCap() {
		return idCap;
	}
	public void setIdCap(int idCap) {
		this.idCap = idCap;
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
	
}
