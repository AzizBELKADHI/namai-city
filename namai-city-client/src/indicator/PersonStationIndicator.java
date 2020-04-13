package indicator;

import java.sql.Timestamp;

public class PersonStationIndicator {

	private int idFreqStation; 
	private String position; 
	private Timestamp date;
	private int qtePerson; 
	private int idStation;
	
	public PersonStationIndicator() {
		
	}
	
	public PersonStationIndicator(int idFreq, String p, Timestamp d, int qteP, int idS) {
		idFreqStation = idFreq; 
		position = p; 
		date = d; 
		qtePerson = qteP;  
		idStation = idS; 
	}
	public int getIdFreqStation() {
		return idFreqStation;
	}
	public void setIdFreqStation(int idFreqStation) {
		this.idFreqStation = idFreqStation;
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
	public int getQtePerson() {
		return qtePerson;
	}
	public void setQtePerson(int qtePerson) {
		this.qtePerson = qtePerson;
	}
	public int getIdStation() {
		return idStation;
	}
	public void setIdStation(int idStation) {
		this.idStation = idStation;
	} 
	
}
