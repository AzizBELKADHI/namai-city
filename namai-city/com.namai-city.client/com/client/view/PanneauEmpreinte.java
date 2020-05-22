package com.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PanneauEmpreinte extends JPanel {
	
	private JButton simuler;
	private JTextField disttram;
	private JTextField distvoiture;
	private JTextField distvelo;
	private JTextField distmarche;
	private JTextField usertram;
	private JTextField uservoiture;
	private JTextField uservelo;
	private JTextField usermarche;
	private JTextField co2tram;
	private JTextField co2voiture;
	private JTextField co2velo;
	private JTextField co2marche;

	private JLabel vide;
	private JLabel tramlabel;
	private JLabel voiturelabel;
	private JLabel velolabel;
	private JLabel marchelabel;
	
	private JLabel distance;
	private JLabel utilisateurs;
	private JLabel co2;
	
	private JPanel form;
	
	public PanneauEmpreinte() {
		super();
		disttram = new JTextField();
		distvoiture = new JTextField();
		distvelo = new JTextField();
		distmarche = new JTextField();
		usertram = new JTextField();
		uservoiture = new JTextField();
		uservelo = new JTextField();
		usermarche = new JTextField();
		co2tram = new JTextField();
		co2voiture = new JTextField();
		co2velo = new JTextField();
		co2marche = new JTextField();
	
		vide = new JLabel("------");
		tramlabel = new JLabel("TRAM");
		voiturelabel = new JLabel("VOITURE");
		velolabel = new JLabel("VELO");
		marchelabel = new JLabel("MARCHE");
		distance = new JLabel("DISTANCE MOY");
		utilisateurs = new JLabel("NB UTILISATEURS");
		co2 = new JLabel("CO2");
		    
		form = new JPanel();
		form.setLayout(new GridLayout(5,5));
		simuler = new JButton("Simuler");
		
		form.add(vide);
		form.add(tramlabel);
		form.add(voiturelabel);
		form.add(velolabel);
		form.add(marchelabel);
		form.add(distance);
		form.add(disttram);
		form.add(distvoiture);
		form.add(distvelo);
		form.add(distmarche);
		form.add(utilisateurs);
		form.add(usertram);
		form.add(uservoiture);
		form.add(uservelo);
		form.add(usermarche);
		form.add(co2);
		form.add(co2tram);
		form.add(co2voiture);
		form.add(co2velo);
		form.add(co2marche);
		form.add(simuler);
		form.add(simuler);
		form.add(simuler);
		form.add(simuler);
		form.add(simuler);
		form.setPreferredSize(new Dimension(500,500));
		this.add(form);	
		
		//km.setHorizontalAlignment(JLabel.LEFT);
		//velo.setHorizontalAlignment(JLabel.RIGHT);
		//velo.setBackground(Couleur.getBgThem());
		//password.setForeground(Couleur.getBgApp());
		//password.setFont(new Font("Arial", Font.BOLD, 25) );
		//password.setBorder(new LineBorder(Couleur.getBgTitle()));
		//username.setBackground(Couleur.getBgThem());
		//username.setForeground(Couleur.getBgApp());
		//username.setFont(new Font("Arial", Font.BOLD, 25) );
		//username.setBorder(new LineBorder(Couleur.getBgTitle()));
		//user.setPreferredSize((new Dimension(55, 50) ));
	} 
}
