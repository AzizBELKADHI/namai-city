package indicator;

import java.sql.Timestamp;

import org.json.simple.JSONObject;

public class WarningIndicator {

	private int warningId; 
	private int thresholdId;
	private String warningState; 
	private int threshold;
	private int thresholdMax; 
	private String position; 
	private Timestamp dateStart; 
	private Timestamp dateEnd; 
	private int warningNb; 
	
	public WarningIndicator() {
		
	}
	
	public WarningIndicator(int idA, int idS, String warning, int s,int sM, String p,  Timestamp dS, Timestamp dE, int wNb) {
		warningId = idA; 
		thresholdId = idS; 
		warningState = warning; 
		threshold = s; 
		thresholdMax = sM; 
		position = p; 
		dateStart = dS; 
		dateEnd = dE; 
		warningNb = wNb; 
	}
	public JSONObject convertToJSON() {
		JSONObject json = new JSONObject();
		json.put("id_alerte", warningId);
		json.put("id_seuil", thresholdId); 
		json.put("alerte_etat", warningState); 
		json.put("seuil", threshold); 
		json.put("seuil_max", thresholdMax); 
		json.put("position", position); 
		json.put("date_debut", dateStart); 
		json.put("date_fin", dateEnd); 
		//json.put("nb_alerte", warningNb); 
		
		return json;
	}
	
	public void convertFromJson(JSONObject json) {
		this.warningId = (int) json.get("id_alerte");
		this.thresholdId = (int) json.get("id_seuil"); 
		this.warningState = (String) json.get("alerte_etat"); 
		this.threshold = (int) json.get("seuil"); 
		this.thresholdMax = (int) json.get("seuil_max"); 
		this.position = (String) json.get("position"); 
		this.dateStart = (Timestamp) json.get("date_debut"); 
		this.dateEnd = (Timestamp) json.get("date_fin"); 
		//this.warningNb = (int) json.get("nb_alerte"); 
	}


	@Override
	public String toString() {
		return "WarningIndicator [warningId=" + warningId + ", thresholdId=" + thresholdId + ", warningState="
				+ warningState + ", threshold=" + threshold + ", thresholdMax=" + thresholdMax + ", position="
				+ position + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", warningNb=" + warningNb + "]";
	}

	public int getWarningNb() {
		return warningNb;
	}

	public void setWarningNb(int warningNb) {
		this.warningNb = warningNb;
	}

	public int getThresholdMax() {
		return thresholdMax;
	}

	public void setThresholdMax(int thresholdMax) {
		this.thresholdMax = thresholdMax;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Timestamp getDateStart() {
		return dateStart;
	}

	public void setDateStart(Timestamp dateStart) {
		this.dateStart = dateStart;
	}

	public Timestamp getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Timestamp dateEnd) {
		this.dateEnd = dateEnd;
	}

	public int getWarningId() {
		return warningId;
	}

	public void setWarningId(int warningId) {
		this.warningId = warningId;
	}

	public String getWarningState() {
		return warningState;
	}

	public void setWarningState(String warningState) {
		this.warningState = warningState;
	}

	public int getThresholdId() {
		return thresholdId;
	}

	public void setThresholdId(int thresholdId) {
		this.thresholdId = thresholdId;
	}



	
}