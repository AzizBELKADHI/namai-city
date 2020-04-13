package indicator;

import java.sql.Timestamp;

public class CarIndicator {
	
	private int idVoit; 
	private Timestamp date; 
	private int nbVoitures;
	private int idCap;
	
	public CarIndicator() {
		
	}
	
	public CarIndicator (int id, Timestamp d, int nbV, int idC) {
		idVoit = id; 
		date = d; 
		nbVoitures = nbV; 
		idCap = idC; 
	}
	
	public int getIdVoit() {
		return idVoit;
	}
	public void setIdVoit(int idVoit) {
		this.idVoit = idVoit;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getNbVoitures() {
		return nbVoitures;
	}
	public void setNbVoitures(int nbVoitures) {
		this.nbVoitures = nbVoitures;
	}
	public int getIdCap() {
		return idCap;
	}
	public void setIdCap(int idCap) {
		this.idCap = idCap;
	} 
	
	
	
	

}
