package com.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.json.simple.JSONObject;

import com.client.view.ConnectionNamaiCity;
import com.client.view.PanneauConfigurationPollution;

public class ControllerPollution implements ActionListener {
	
	ConnectionNamaiCity fenetre;
	private PanneauConfigurationPollution pcp;
	
	public ControllerPollution(ConnectionNamaiCity fenetre) {
		this.pcp = fenetre.getPa().getPuc().getPollution();
		pcp.getSubmit().addActionListener(this);
		this.fenetre = fenetre;
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(pcp.getSubmit())) {
		
		String [] tab = new String[2];
		tab[0] = pcp.getJtCapteurType().getText();
		tab[1] = pcp.getJtLocalisation().getText();
		System.out.println(tab[0]);
		JSONObject obj=new JSONObject();
		obj.put("demandType",String.valueOf("INSERT_CAPTEUR"));
		obj.put("type_capteur",String.valueOf(tab[0]));
		obj.put("localisation",String.valueOf(tab[1]));
		System.out.println(obj);
		JSONObject reponse;
		try {
			
			reponse = SocketClient.sendMessage(obj);
			String repServer = (String) reponse.get("reponse");  
			String type_capteur = (String) reponse.get("type_capteur");  
			String localisation = (String) reponse.get("localisation");
			System.out.println(repServer +": \n" + type_capteur + ": \n " + localisation  + ": \n");  // Display data
		
		} catch (Exception e1) {
			
			e1.printStackTrace();
		} 
		
	}
	}

}
