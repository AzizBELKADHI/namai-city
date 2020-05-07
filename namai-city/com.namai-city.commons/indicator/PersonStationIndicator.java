package indicator;

import java.sql.Timestamp;

import org.json.simple.JSONObject;

public class PersonStationIndicator {

	private int freqStationId; 
	private String position; 
	private Timestamp date;
	private int personQty; 
	private int stationId;
	private String stationName; 
	private int persNb; 
	
	public PersonStationIndicator() {
		
	}
	
	@Override
	public String toString() {
		return "PersonStationIndicator [freqStationId=" + freqStationId + ", position=" + position + ", date=" + date
				+ ", personQty=" + personQty + ", stationId=" + stationId + ", stationName=" + stationName + ", persNb="
				+ persNb + "]";
	}

	public PersonStationIndicator(int idFreq, String p, int qteP, int idS, String nameS, Timestamp date, int persN) {
		freqStationId = idFreq; 
		position = p; 
		personQty = qteP;  
		stationId = idS; 
		stationName = nameS; 
		date = date; 
		persNb = persN; 
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public int getPersNb() {
		return persNb;
	}

	public void setPersNb(int persNb) {
		this.persNb = persNb;
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
	
	public JSONObject convertToJSON() {
		JSONObject json = new JSONObject();
		json.put("id_freq_station", freqStationId);
		json.put("position", position); 
		json.put("qte_pers",personQty); 
		json.put("id_station", stationId); 
		json.put("nom_station", stationName); 
		json.put("date_mesure", date);
		//json.put("nombre_personne_position", persNb);
		return json;
	}
	
	public void convertFromJson(JSONObject json) {
		this.freqStationId = (int) json.get("id_freq_station");
		this.position = (String) json.get("position"); 
		this.personQty = (int) json.get("qte_pers"); 
		this.stationId = (int) json.get("id_station"); 
		this.stationName = (String) json.get("nom_station"); 
		this.date = (Timestamp) json.get("date_mesure"); 
		//this.persNb = (int) json.get("nombre_personne_position"); 
	}
	
}
	