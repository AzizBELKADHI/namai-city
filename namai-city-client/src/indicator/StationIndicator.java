package indicator;

import java.sql.Timestamp;

public class StationIndicator {
	
	private int idStation; 
	private String nomStation; 
	private String position; 
	private Timestamp date;
	
	public StationIndicator() {
		
	}
	
	public StationIndicator(int idS, String nom, String p, Timestamp d) {
		idStation = idS; 
		nomStation = nom; 
		position = p; 
		date = d; 
	}
	
	public int getIdStation() {
		return idStation;
	}
	public void setIdStation(int idStation) {
		this.idStation = idStation;
	}
	public String getNomStation() {
		return nomStation;
	}
	public void setNomStation(String nomStation) {
		this.nomStation = nomStation;
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
