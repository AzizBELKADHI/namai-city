package entities;

import java.util.Date;

public class Station {
	String name;
	String position;
	int nbrPeople;
	Date date_mesure;
	CityCard cityCard;

	public Station(String name, String position, int nbrPeople, Date date_mesure, CityCard cityCard) {

		this.name = name;
		this.position = position;
		this.nbrPeople = nbrPeople;
		this.date_mesure = date_mesure;
		this.cityCard = cityCard;
	}

	public Station() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getNbrPeople() {
		return nbrPeople;
	}

	public void setNbrPeople(int nbrPeople) {
		this.nbrPeople = nbrPeople;
	}

	public Date getDate_mesure() {
		return date_mesure;
	}

	public void setDate_mesure(Date date_mesure) {
		this.date_mesure = date_mesure;
	}

	public CityCard getCityCard() {
		return cityCard;
	}

	public void setCityCard(CityCard cityCard) {
		this.cityCard = cityCard;
	}

	@Override
	public String toString() {
		return "Station [name=" + name + ", position=" + position + ", nbrPeople=" + nbrPeople + ", date_mesure="
				+ date_mesure + "]";
	}

}
