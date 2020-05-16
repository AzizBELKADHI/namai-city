package entities;

import java.sql.Timestamp;
import java.util.Date;

public class AlertePolluant {
	private int id;;
	private Date date_alerte;
	private String description;
	private int fk_id_capteur;
	
	public AlertePolluant() {
		
		
		
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDate_alerte() {
		return date_alerte;
	}
	public void setDate_alerte(Timestamp date_alerte) {
		this.date_alerte = date_alerte;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

	public int getFk_id_capteur() {
		return fk_id_capteur;
	}

	public void setFk_id_capteur(int fk_id_capteur) {
		this.fk_id_capteur = fk_id_capteur;
	}

	@Override
	public String toString() {
		return "AlertePolluant [id=" + id + ", date_alerte=" + date_alerte + ", description=" + description
				+ ", fk_id_capteur=" + fk_id_capteur + "]";
	}
	
	

}