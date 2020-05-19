package indicator;

import java.sql.Timestamp;

import org.json.simple.JSONObject;


public class SensorPolluantIndicator {

	private int id; 
	private int id2; 
	private Timestamp date; 
	private String localisation; 
	private int warningNb; 
	private String co2; 
	private String pf; 
	private String no2; 
	private String tmp; 
	private String tmpMin; 
	private String tmpMax;
	private int IdFkCap; 



	public SensorPolluantIndicator() {

	}

	public SensorPolluantIndicator(int i, Timestamp d, String l, int w, String c, String p, String n, String t) {
		id = i; 
		date = d; 
		localisation = l; 
		warningNb = w; 
		co2 = c; 
		pf = p; 
		no2 = n; 
		tmp = t; 
	}
	
	public SensorPolluantIndicator(int i) {
		id2 = i; 
	}
	
	public SensorPolluantIndicator(int i, Timestamp d, String c, String n, String p, String t, int id) {
		id = i; 
		date = d; 
		co2 = c; 
		no2 = n; 
		pf = p; 
		tmp = t; 
		IdFkCap = id; 
		
	}
	
	public SensorPolluantIndicator(String c, String n, String p, String tMin, String tMax) {
		co2 = c; 
		no2 = n; 
		pf = p; 
		tmpMin = tMin; 
		tmpMax = tMax; 
		
		
	}

	
	public String toString() {
		return "SensorPolluantIndicator [id=" + id + ", id2=" + id2 + ", date=" + date + ", localisation="
				+ localisation + ", warningNb=" + warningNb + ", co2=" + co2 + ", pf=" + pf + ", no2=" + no2 + ", tmp="
				+ tmp + ", tmpMin=" + tmpMin + ", tmpMax=" + tmpMax + ", IdFkCap=" + IdFkCap + "]";
	}

	public String getCo2() {
		return co2;
	}

	public void setCo2(String co2) {
		this.co2 = co2;
	}
	public String getTmpMin() {
		return tmpMin;
	}

	public void setTmpMin(String tmpMin) {
		this.tmpMin = tmpMin;
	}

	public String getTmpMax() {
		return tmpMax;
	}

	public void setTmpMax(String tmpMax) {
		this.tmpMax = tmpMax;
	}

	public int getIdFkCap() {
		return IdFkCap;
	}

	public void setIdFkCap(int idFkCap) {
		IdFkCap = idFkCap;
	}

	public String getPf() {
		return pf;
	}

	public void setPf(String pf) {
		this.pf = pf;
	}

	public String getNo2() {
		return no2;
	}

	public void setNo2(String no2) {
		this.no2 = no2;
	}

	public String getTmp() {
		return tmp;
	}

	public void setTmp(String tmp) {
		this.tmp = tmp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public int getWarningNb() {
		return warningNb;
	}

	public void setWarningNb(int warningNb) {
		this.warningNb = warningNb;
	}
	public JSONObject convertToJSON() {
		JSONObject json = new JSONObject();
		json.put("fk_id_capteur", id);
		json.put("id", id2); 
		if(date != null) {
			json.put("start_date", date.toString());
		}
		json.put("localisation", localisation); 
		json.put("nb_alerte",warningNb); 
		json.put("val_co2", co2); 
		json.put("val_pf", pf); 
		json.put("val_no2", no2);
		json.put("val_tmp", tmp);
		json.put("seuil_min_tmp",tmpMin); 
		json.put("seuil_max_tmp",tmpMax); 
		
		
		return json;
	}
	public void convertFromJson(JSONObject json) {
		this.id = Long.valueOf(String.valueOf(json.get("fk_id_capteur"))).intValue();
		this.id2 = Long.valueOf(String.valueOf(json.get("id"))).intValue();
		if(json.get("start_date")!=null) {
		this.date = Timestamp.valueOf(String.valueOf(json.get("start_date"))); 
		}
		this.localisation = String.valueOf(json.get("localisation")); 	
		this.warningNb = Long.valueOf(String.valueOf(json.get("nb_alerte"))).intValue(); 
		this.co2 = String.valueOf(json.get("val_co2")); 
		this.pf = String.valueOf(json.get("val_pf"));
		this.no2 = String.valueOf(json.get("val_no2"));
		this.tmp = String.valueOf(json.get("val_tmp"));
		this.tmpMin = String.valueOf(json.get("seuil_min_tmp"));
		this.tmpMax = String.valueOf(json.get("seuil_max_tmp"));
		
	}

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}
}