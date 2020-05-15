package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;

import com.commons.model.SocketClient;

import entities.CapteurPolluant;
import entities.HistoriqueCapteurPolluant;

public class ThreadCapteurPolluant implements Runnable {
	private SocketClient client;
	private CapteurPolluant capteurPolluant;
	private Map<String, String> mapHistoriques = new HashMap<String, String>();
	private List<HistoriqueCapteurPolluant> listScenarios = new ArrayList<>();
	
	
	public ThreadCapteurPolluant(CapteurPolluant capteurPolluant2, SocketClient client2, Map<String, String> mapHistoriques) {
		this.capteurPolluant = capteurPolluant2;
		this.client = client2;
		this.mapHistoriques = mapHistoriques;
		
	}

	@Override
	public void run() {
	//je cree la liste des historiques des capteurs
		//je remplis les valeurs de pollution
		for (Map.Entry<String, String> val  : this.mapHistoriques.entrySet()) {
			//je cree l'historique 
			HistoriqueCapteurPolluant h = new HistoriqueCapteurPolluant();
			h.setFk_id_capteur(capteurPolluant.getId());
			
			//Recuperation des valeurs de pollution d'un historique
			String [] valPollution = val.getValue().split(",");
			for (int i = 0; i < valPollution.length; i++) {
				String [] nombre = valPollution[i].split(":");
				if(nombre[0].equals("co2")) {
					h.setVal_co2(nombre[1]);
				}
				
				if(nombre[0].equals("no2")) {
					h.setVal_no2(nombre[1]);
				}
				
				if(nombre[0].equals("pf")) {
					h.setVal_pf(nombre[1]);
				}
				
				if(nombre[0].equals("tmp")) {
					h.setVal_tmp(nombre[1]);
				}
			}
			listScenarios.add(h);
		}
		System.out.println(this.listScenarios);
		//reponse du serveur
		String reponseServ="OK";
		
		synchronized (client) {
		//envoie de l'historique au serveur
			for (HistoriqueCapteurPolluant h : this.listScenarios) {
				reponseServ = sendPolluantHistorique(h);
				System.out.println("Capteur :" +this.capteurPolluant.getId()+"Reponse Serveur :" +reponseServ);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				
			}
		}
		
	}

	private String sendPolluantHistorique(HistoriqueCapteurPolluant h) {
		JSONObject obj = new JSONObject();
		obj.put("demandType", String.valueOf("SEND_MESSAGE_CAPTEUR_POLLUANT"));
		obj.put("start_date", String.valueOf(h.getStart_date()));
		obj.put("val_co2", String.valueOf(h.getVal_co2()));
		obj.put("val_no2", String.valueOf(h.getVal_no2()));
		obj.put("val_pf", String.valueOf(h.getVal_pf()));
		obj.put("val_tmp", String.valueOf(h.getVal_tmp()));
		obj.put("fk_id_capteur", String.valueOf(h.getFk_id_capteur()));
		obj.put("thread", String.valueOf(Thread.currentThread().getName()));
		
		JSONObject reponse;
		String reponseServ = "OK";
		
		try {
			reponse = SocketClient.sendMessage(obj);
			reponseServ = (String) reponse.get("reponse");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reponseServ;
	}
}
