<<<<<<< HEAD
package com.client.view;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class PanneauUC extends JPanel {
	private PanneauBorne borne;
	private PanneauConfigurationPollution pollution;
	private PanneauDetecteur detecteur;
	private PanneauDetecteur empreinte;
	private PanneauIndicator indicateur;
	private MenuApplication ma;
	private CardLayout cl;
	private PanneauBienvenueNamaiCity bienvenue;
	
	
	public PanneauUC() {
		
		borne = new PanneauBorne();
		pollution = new PanneauConfigurationPollution();
		detecteur = new PanneauDetecteur();
		empreinte = new PanneauDetecteur();
		indicateur = new PanneauIndicator();
		bienvenue = new PanneauBienvenueNamaiCity();
		ma = new MenuApplication();
		//pollution.setBackground(Color.BLUE);
		
		cl = new CardLayout();
		this.setLayout(cl);
		this.add("panneauBienvenue", bienvenue);
		this.add("panneauBorne", borne);
		this.add("panneauPollution", pollution);
		this.add("panneauDetecteur", detecteur);
		this.add("panneauDetecteur", detecteur);
		this.add("panneauIndicateur", indicateur);
		//cl.show(bienvenue, "panneauBienvenue");
	}
	public void setCard(String name) {
		cl.show(this, name);
	}
	public PanneauBorne getBorne() {
		return borne;
	}
	public PanneauConfigurationPollution getPollution() {
		return pollution;
	}
	public PanneauDetecteur getDetecteur() {
		return detecteur;
	}
	public PanneauDetecteur getEmpreinte() {
		return empreinte;
	}
	public PanneauIndicator getIndicateur() {
		return indicateur;
	}
	public CardLayout getCl() {
		return cl;
	}
	public PanneauBienvenueNamaiCity getBienvenue() {
		return bienvenue;
	}

}
=======
package com.client.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.swing.JPanel;

public class PanneauUC extends JPanel {
	private PanneauBorne borne;
	private PanneauConfigurationPollution pollution;
	private PanneauDetecteur detecteur;
	private PanneauEmpreinte empreinte;
	private PanneauIndicateur indicateur;
	private MenuApplication ma;
	private CardLayout cl;
	private PanneauBienvenueNamaiCity bienvenue;
	
	
	public PanneauUC() throws UnsupportedEncodingException, SQLException, IOException {
		
		pollution = new PanneauConfigurationPollution();
		borne = new PanneauBorne();
		detecteur = new PanneauDetecteur();
		empreinte = new PanneauEmpreinte();
		indicateur = new PanneauIndicateur();
		bienvenue = new PanneauBienvenueNamaiCity();
		ma = new MenuApplication();
		//pollution.setBackground(Color.BLUE);
		
		cl = new CardLayout();
		this.setLayout(cl);
		System.out.println("bonjour Je suis ici");
		this.add("panneauBienvenue", bienvenue);
		this.add("panneauPollution", pollution);
		this.add("panneauBorne", borne);
		this.add("panneauDetecteur", detecteur);
		this.add("panneauEmpreinte", empreinte);
		this.add("panneauIndicateur", indicateur);
		//cl.show(bienvenue, "panneauBienvenue");
	}
	public void setCard(String name) {
		cl.show(this, name);
	}
	public PanneauBorne getBorne() {
		return borne;
	}
	public PanneauConfigurationPollution getPollution() {
		return pollution;
	}
	public PanneauDetecteur getDetecteur() {
		return detecteur;
	}
	public PanneauEmpreinte getEmpreinte() {
		return empreinte;
	}
	public PanneauIndicateur getIndicateur() {
		return indicateur;
	}
	public CardLayout getCl() {
		return cl;
	}
	public PanneauBienvenueNamaiCity getBienvenue() {
		return bienvenue;
	}

}
>>>>>>> refs/remotes/origin/IdirHamdis
