package indicator;

import java.sql.Timestamp;

public class PersonStationIndicator {

	private int freqStationId; 
	private String position; 
	private Timestamp date;
	private int personQty; 
	private int stationId;
	
	public PersonStationIndicator() {
		
	}
	
	public PersonStationIndicator(int idFreq, String p, Timestamp d, int qteP, int idS) {
		freqStationId = idFreq; 
		position = p; 
		date = d; 
		personQty = qteP;  
		stationId = idS; 
	}

	public int getFreqStationId() {
		return freqStationId;
	}

	public void setFreqStationId(int freqStationId) {
		this.freqStationId = freqStationId;
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

	public int getPersonQty() {
		return personQty;
	}

	public void setPersonQty(int personQty) {
		this.personQty = personQty;
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}
	
}
	