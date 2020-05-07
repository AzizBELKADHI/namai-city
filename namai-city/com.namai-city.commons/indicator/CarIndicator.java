package indicator;

import java.sql.Timestamp;

import org.json.simple.JSONObject;

public class CarIndicator {
	
	private int carId; 
	private Timestamp date; 
	private int carsNb;
	private int sensorId;
	private int carNbGlobal; 
	private int carNbAvg; 
	
	public CarIndicator() {
		
	}
	
	@Override
	public String toString() {
		return "CarIndicator [carId=" + carId + ", date=" + date + ", carsNb=" + carsNb + ", sensorId=" + sensorId
				+ ", carNbGlobal=" + carNbGlobal + ", carNbAvg=" + carNbAvg + "]";
	}

	public CarIndicator (int id, int nbV, int idC, Timestamp d, int nbG) {
		carId = id; 
		date = d; 
		carsNb = nbV; 
		sensorId = idC; 
		carNbGlobal = nbG ;
		
		
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getCarsNb() {
		return carsNb;
	}

	public void setCarsNb(int carsNb) {
		this.carsNb = carsNb;
	}

	public int getSensorId() {
		return sensorId;
	}

	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}

	public int getCarNbGlobal() {
		return carNbGlobal;
	}

	public void setCarNbGlobal(int carNbGlobal) {
		this.carNbGlobal = carNbGlobal;
	}

	public int getCarNbAvg() {
		return carNbAvg;
	}

	public void setCarNbAvg(int carNbAvg) {
		this.carNbAvg = carNbAvg;
	}
	
	public JSONObject convertToJSON() {
		JSONObject json = new JSONObject();
		json.put("id_voit", carId);
		json.put("nb_voitures", carsNb); 
		json.put("id_cap", sensorId); 
		json.put("date", date); 
		//json.put("nombre_voitures_total_date",carNbGlobal); 
		
		return json;
	}
	
	public void convertFromJson(JSONObject json) {
		this.carId = (int) json.get("id_voit");
		this.carsNb = (int) json.get("nb_voitures");
		this.sensorId = (int) json.get("id_cap"); 
		this.date = (Timestamp) json.get("date"); 
		//this.carNbGlobal = (int) json.get("nombre_voitures_total_date"); 
	}

	
}
