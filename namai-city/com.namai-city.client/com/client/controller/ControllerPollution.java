package com.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

import com.client.view.CapteurTableModel;
import com.client.view.ConnectionNamaiCity;
import com.client.view.PanneauConfigurationPollution;
import com.commons.model.AccessServer;
import com.commons.model.SocketClient;

import entities.CapteurPolluant;
//
public class ControllerPollution implements ActionListener {

	ConnectionNamaiCity fenetre;
	private PanneauConfigurationPollution pcp;
	private SocketClient client;

	public ControllerPollution(ConnectionNamaiCity fenetre) {
		this.pcp = fenetre.getPa().getPuc().getPollution();
		pcp.getSubmit().addActionListener(this);
		pcp.getList().addActionListener(this);
		this.fenetre = fenetre;


	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(pcp.getSubmit())) {
			
			if(pcp.getJtLocalisation().getText().equals("")) {
				JOptionPane.showMessageDialog(null, "the localisation must be filled", "Insertion Error", JOptionPane.ERROR_MESSAGE);

			}
			
			
			if(e.getSource().equals(pcp.getSubmit())) {
				
				if(pcp.getJtAdresseIp().getText().equals("")) {
					JOptionPane.showMessageDialog(null, "the adresse ip file must be filled", "Insertion Error", JOptionPane.ERROR_MESSAGE);

				}
			
			
			if (pcp.getjFrequence().getText().equals("")) {
				JOptionPane.showMessageDialog(null, "the frequency value must be filled", "Insertion Error", JOptionPane.ERROR_MESSAGE);

			}
			else if(!pcp.getjFrequence().getText().equals("") && !estUnEntier(pcp.getjFrequence().getText())) {
				JOptionPane.showMessageDialog(null, "\r\n" + 
						"The field reserved for the  frequency  must be an integer", "writing error", JOptionPane.ERROR_MESSAGE);
			} 
			


		if (pcp.getJtSeuil_CO2().getText().equals("")) {
			JOptionPane.showMessageDialog(null, " the CO2 threshold must be filled", "Insertion Error", JOptionPane.ERROR_MESSAGE);

		}
		else if(!pcp.getJtSeuil_CO2().getText().equals("") && !estUnEntier(pcp.getJtSeuil_CO2().getText())) {
			JOptionPane.showMessageDialog(null, "\r\n" + 
					"The field reserved for the  CO2 threshold is not an integer", "writing error", JOptionPane.ERROR_MESSAGE);
		} 


		if(pcp.getJtSeuil_NO2().getText().equals("")) {
			JOptionPane.showMessageDialog(null, "the NO2 threshold must be filled", "Insertion Error", JOptionPane.ERROR_MESSAGE);

		}

		else if(!pcp.getJtSeuil_NO2().getText().equals("") && !estUnEntier(pcp.getJtSeuil_NO2().getText())) {
			JOptionPane.showMessageDialog(null, "\r\n" + 
					"The field reserved for the  NO2 threshold is not an integer", "writing error", JOptionPane.ERROR_MESSAGE);
		}



		if(pcp.getJtSeuil_PM().getText().equals("")) {
			JOptionPane.showMessageDialog(null, "the PM threshold must be filled", "Insertion Error", JOptionPane.ERROR_MESSAGE);

		}
		else if(!pcp.getJtSeuil_PM().getText().equals("")&& !estUnEntier(pcp.getJtSeuil_PM().getText())) {
			JOptionPane.showMessageDialog(null, "\r\n" + 
					"The field reserved for the PM threshold is not an integer", "writing error", JOptionPane.ERROR_MESSAGE);
		}

		if (pcp.getJtSeuil_Min_Tmp().getText().equals("")) {
			JOptionPane.showMessageDialog(null, "the min temperature threshold must be filled", "Insertion Error", JOptionPane.ERROR_MESSAGE);

		}

		else if(!pcp.getJtSeuil_Min_Tmp().getText().equals("")&& !estUnEntier(pcp.getJtSeuil_Min_Tmp().getText())) {
			JOptionPane.showMessageDialog(null, "\r\n" + 
					"The field reserved for the minimal temperature threshold is not an integer", "writing error", JOptionPane.ERROR_MESSAGE);
		}
		if(pcp.getJtSeuil_Max_Tmp().getText().equals("")) {
			JOptionPane.showMessageDialog(null, "the max temperature threshold must be filled", "Insertion Error", JOptionPane.ERROR_MESSAGE);

		}
		else if(!pcp.getJtSeuil_Max_Tmp().getText().equals("") && !estUnEntier(pcp.getJtSeuil_Max_Tmp().getText())) {
			JOptionPane.showMessageDialog(null, "\r\n" + 
					"The field reserved for the maximal temperature threshold is not an integer", "writing error", JOptionPane.ERROR_MESSAGE);
		}
		
		int min = Integer.parseInt((pcp.getJtSeuil_Min_Tmp().getText()));
		int max = Integer.parseInt(pcp.getJtSeuil_Max_Tmp().getText());
		System.out.println(min);
		if(min>max) {
			JOptionPane.showMessageDialog(null, "\n" + 
					"the max temperature must be higher than the min temperature", "Insertion Error", JOptionPane.ERROR_MESSAGE);
			
		}
		

		if((!pcp.getJtSeuil_CO2().getText().equals("") && estUnEntier(pcp.getJtSeuil_CO2().getText())) && 
				(!pcp.getJtSeuil_NO2().getText().equals("") && estUnEntier(pcp.getJtSeuil_NO2().getText()))&&
				(!pcp.getJtSeuil_PM().getText().equals("")&& estUnEntier(pcp.getJtSeuil_PM().getText()))&&
				(!pcp.getJtSeuil_Min_Tmp().getText().equals("")&& estUnEntier(pcp.getJtSeuil_Min_Tmp().getText()))&&
				(!pcp.getJtSeuil_Max_Tmp().getText().equals("") && estUnEntier(pcp.getJtSeuil_Max_Tmp().getText()))&&
				(!pcp.getjFrequence().getText().equals("")&& estUnEntier(pcp.getjFrequence().getText()))&&
				(min<max) && 
				!pcp.getJtLocalisation().getText().equals("")&& 
				!pcp.getJtAdresseIp().getText().equals("")) {	

			CapteurPolluant cp = new CapteurPolluant();
			cp.setAdresse_ip(pcp.getJtAdresseIp().getText());
			cp.setLocalisation(pcp.getJtLocalisation().getText());
			cp.setSeuil_co2(pcp.getJtSeuil_CO2().getText());
			cp.setSeuil_no2(pcp.getJtSeuil_NO2().getText());
			cp.setSeuil_pf(pcp.getJtSeuil_PM().getText());
			cp.setSeuil_min_tmp(pcp.getJtSeuil_Min_Tmp().getText());
			cp.setSeuil_max_tmp(pcp.getJtSeuil_Max_Tmp().getText());
			cp.setFrequence(pcp.getjFrequence().getText());
			
			System.out.println(cp.toString());

			try {
				sendPolluant(cp);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		}
		if(e.getSource().equals(pcp.getList())) {
			CapteurTableModel ct= new CapteurTableModel();
		}
		
		}
		

	}






	private String  sendPolluant(CapteurPolluant cp) throws IOException {



		JSONObject obj = new JSONObject();
		client = new SocketClient();
		client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
		obj.put("demandType",String.valueOf("INSERT_CAPTEUR_POLLUANT"));
		obj.put("adresse_ip",String.valueOf(cp.getAdresse_ip()));
		obj.put("localisation",String.valueOf(cp.getLocalisation()));
		obj.put("seuil_co2",String.valueOf(cp.getSeuil_co2()));
		obj.put("seuil_no2",String.valueOf(cp.getSeuil_no2()));
		obj.put("seuil_pf",String.valueOf(cp.getSeuil_pf()));
		obj.put("seuil_min_tmp",String.valueOf(cp.getSeuil_min_tmp()));
		obj.put("seuil_max_tmp",String.valueOf(cp.getSeuil_max_tmp()));
		obj.put("frequence", String.valueOf(cp.getFrequence()));


		JSONObject reponse;
		String reponseServ = "KO";
		reponse = SocketClient.sendMessage(obj);
		//System.out.println(reponse);
		reponseServ = (String) reponse.get("reponse");


		JOptionPane.showMessageDialog(null, "\r\n" +
				"La configuration de votre capteur a été un succes","Successful",
				JOptionPane.INFORMATION_MESSAGE);



		return reponseServ;


	}


	public boolean estUnEntier(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e){
			return false;
		}

		return true;
	}
}
