package com.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

import com.client.view.ConnectionNamaiCity;
import com.client.view.PanneauConfigurationPollution;
import com.commons.model.SocketClient;

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
		
		String [] tab = new String[7];
		tab[0] = pcp.getJtAdresseIp().getText();
		tab[1] = pcp.getJtLocalisation().getText();
		tab[2] = pcp.getJtSeuil_CO2().getText();
		tab[3] = pcp.getJtSeuil_NO2().getText();
		tab[4] = pcp.getJtSeuil_PM().getText();
		tab[5] = pcp.getJtSeuil_Min_Tmp().getText();
		tab[6] = pcp.getJtSeuil_Max_Tmp().getText();
		System.out.println(tab[0]);
		System.out.println(tab[1]);
		System.out.println(tab[2]);
		System.out.println(tab[3]);
		System.out.println(tab[4]);
		System.out.println(tab[5]);
		System.out.println(tab[6]);
		
		if(tab[0].isEmpty()|| tab[1].isEmpty() || tab[2].isEmpty() || tab[3].isEmpty() || tab[4].isEmpty() || tab[5].isEmpty() || tab[6].isEmpty()) {
			JOptionPane.showMessageDialog(null, "\r\n" + 
					"There are empty fields, please fill them completely", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
		JSONObject obj=new JSONObject();
		  obj.put("demandType",String.valueOf("INSERT_CAPTEUR_POLLUANT"));
		  obj.put("Adresse_Ip",tab[0]);
		  obj.put("localisation",tab[1]);
		  obj.put("Seuil_CO2",tab[2]);
		  obj.put("Seuil_NO2",tab[3]);
		  obj.put("Seuil_PM",tab[4]);
		  obj.put("Seuil_Min_Tmp",tab[5]);
		  obj.put("Seuil_Max_Tmp",tab[6]);
		  System.out.println(obj);
		  JSONObject reponse; 
		  
		  reponse = SocketClient.sendMessage(obj);
		  String repServer = (String)reponse.get("reponse");
		  String Adresse_Ip = (String)reponse.get("Adresse_Ip"); 
		  String localisation = (String)reponse.get("localisation"); 
		  String Seuil_CO2 = (String)reponse.get("Seuil_CO2");
		  String Seuil_NO2 = (String)reponse.get("Seuil_NO2");
		  String Seuil_PM = (String)reponse.get("Seuil_PM");
		  String Seuil_Min_Tmp = (String)reponse.get("Seuil_Min_Tmp");
		  String Seuil_Max_Tmp = (String)reponse.get("Seuil_Max_Tmp");
		  System.out.println(repServer +": \n" + Adresse_Ip + ": \n " + localisation + ": \n " + Seuil_CO2 + ": \n " + Seuil_NO2 + ": \n " + Seuil_PM + ": \n " + Seuil_Min_Tmp + ": \n" + Seuil_Max_Tmp + ": \n"); // Display data
			  JOptionPane.showMessageDialog(null, "\r\n" +
			  "La configuration de votre capteur a été un succes","Successful",
			  JOptionPane.INFORMATION_MESSAGE);
		}
			
			  
			 
	
	}
}
