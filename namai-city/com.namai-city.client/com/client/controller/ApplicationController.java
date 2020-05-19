package com.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JMenuItem;

import com.client.view.ConnectionNamaiCity;
import com.client.view.MenuApplication;
import com.client.view.PanneaApplication;
import com.client.view.PanneauUC;

public class ApplicationController implements ActionListener {
	
	
	private PanneauUC puc;
	private MenuApplication ma;
	private ConnectionNamaiCity cn;
	private PanneaApplication pa;
	private int i = 0;
	
	public ApplicationController(ConnectionNamaiCity cn) throws UnsupportedEncodingException, SQLException, IOException {
		this.ma=ma;
		pa = new PanneaApplication();
		 ma = new MenuApplication();
		this.cn = cn;
		puc = new PanneauUC();
		cn.getPa().getMa().getIndicateur().addActionListener(this);
		cn.getPa().getMa().getPollution().addActionListener(this);
		cn.getPa().getMa().getBorne().addActionListener(this);
		cn.getPa().getMa().getDetecteur().addActionListener(this);
		cn.getPa().getMa().getEmpreinte().addActionListener(this);
		cn.getPa().getMa().getAccueil().addActionListener(this);
		//pa.getMa().getMen().addActionListener(this);
		 System.out.println("bonsoir");
		
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("bonjour");
		if(e.getSource() instanceof JButton ) {
			String choix = e.getActionCommand();
			System.out.println(choix);
			if(choix.equals("configPollution")) {
				System.out.println("vous y etes");
				cn.getPa().getPuc().setCard("panneauPollution");
			}
			else if(choix.equals("Accueil")) {
				cn.getPa().getPuc().setCard("panneauBienvenue");
			}
			
			else if(choix.equals("empreinteCarbone")) {
				cn.getPa().getPuc().setCard("panneauEmpreinte");
			}
			else if(choix.equals("configBorne")) {
				System.out.println("j'appui sur ce putain de panneau bornes");
				try {
					if(i == 0) {
					cn.getPa().getPuc().getBorne().init();
					i = 1;
					}
					else {
					cn.revalidate();
					cn.repaint();
					}
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cn.getPa().getPuc().setCard("panneauBorne");
			}
			else if(choix.equals("analyse")) {
				cn.getPa().getPuc().setCard("panneauIndicateur");
			}
			else if(choix.equals("configdetecteur")) {
				cn.getPa().getPuc().setCard("panneauDetecteur");
			}
			
			
			
			/*
			 * else if(choix.equals("Neutre")) { cd.show(getContentPane(),"neutre"); }
			 */
		}
		
	}

}
