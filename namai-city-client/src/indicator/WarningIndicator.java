package indicator;

import java.sql.Timestamp;

public class WarningIndicator {

	private int idAlerte; 
	private String alerteEtat; 
	private int idSeuil; 
	private int seuil;
	private Timestamp date; 
	
	public WarningIndicator() {
		
	}
	
	public WarningIndicator(int idA, String alerte,int idS, int s, Timestamp d ) {
		idAlerte = idA; 
		alerteEtat = alerte; 
		idSeuil = idS; 
		seuil = s; 
		date = d; 
	}
	
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getIdAlerte() {
		return idAlerte;
	}
	public void setIdAlerte(int idAlerte) {
		this.idAlerte = idAlerte;
	}
	public String getAlerteEtat() {
		return alerteEtat;
	}
	public void setAlerteEtat(String alerteEtat) {
		this.alerteEtat = alerteEtat;
	}
	public int getIdSeuil() {
		return idSeuil;
	}
	public void setIdSeuil(int idSeuil) {
		this.idSeuil = idSeuil;
	}
	public int getSeuil() {
		return seuil;
	}
	public void setSeuil(int seuil) {
		this.seuil = seuil;
	} 
	
	
}
