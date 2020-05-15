package indicator;

import java.sql.Timestamp;

public class CarIndicator {
	
	private int carId; 
	private Timestamp date; 
	private int carsNb;
	private int sensorId;
	
	public CarIndicator() {
		
	}
	
	public CarIndicator (int id, Timestamp d, int nbV, int idC) {
		carId = id; 
		date = d; 
		carsNb = nbV; 
		sensorId = idC; 
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
	
}
