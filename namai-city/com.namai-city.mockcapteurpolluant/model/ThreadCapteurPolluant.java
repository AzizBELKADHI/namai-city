package model;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.simple.JSONObject;

import com.commons.model.AccessServer;
import com.commons.model.SocketClient;

import entities.CapteurPolluant;
import entities.HistoriqueCapteurPolluant;

public class ThreadCapteurPolluant implements Runnable {
	private SocketClient client;
	private CapteurPolluant capteurPolluant;
	private String frequence;
	private Random rd;
	private Map<String, String> mapHistoriques = new HashMap<String, String>();
	private List<HistoriqueCapteurPolluant> listScenarios = new ArrayList<>();
//
	public ThreadCapteurPolluant(CapteurPolluant capteurPolluant2, SocketClient client2,
			Map<String, String> mapHistoriques,String frequence) {
		this.client = client2;
		this.capteurPolluant = capteurPolluant2;
		rd = new Random();
		this.mapHistoriques = mapHistoriques;
		//this.frequence = capteurPolluant2.getFrequence();
	}

	@Override
	public void run() {

		// Creation liste historique des capteurs
		// remplissage valeurs de pollution
		for (Map.Entry<String, String> val : this.mapHistoriques.entrySet()) {

			// creation historique
			HistoriqueCapteurPolluant h = new HistoriqueCapteurPolluant();
			h.setFk_id_capteur(this.capteurPolluant.getId());

			// Récupération des valeurs de pollution d'un historiques
			String[] valeurs_pollution = val.getValue().split(",");
			//System.err.println(valeurs_pollution[0]);
			for (int k = 0; k <valeurs_pollution.length; k++) {
				String[] nombre = valeurs_pollution[k].split(":");

				if (nombre[0].equals("co2")) {
					h.setVal_co2(nombre[1]);
				}
				if (nombre[0].equals("no2")) {
					h.setVal_no2(nombre[1]);
				}
				if (nombre[0].equals("pf")) {
					h.setVal_pf(nombre[1]);
				}
				if (nombre[0].equals("tmp")) {
					h.setVal_tmp(nombre[1]);
				}
			}

			this.listScenarios.add(h);
		}

		System.out.println(this.listScenarios.toString());


		//envoie messagse du scenario properties
		String reponseServ="OK";
		
		//récupération de la dernière temperature
		int tmp =0;
		
		synchronized (client) { 
			//envoi historique pollution au serveur 
			try { 
				for (HistoriqueCapteurPolluant h : this.listScenarios) {
					reponseServ = sendPllutionHistorical(h);
					System.out.println("Capteur: "+this.capteurPolluant.getId()+"-Reponse Serveur: "+reponseServ);
					
					//récupération de la dernière temperature
					tmp = Integer.parseInt(h.getVal_tmp());
					
					Thread.sleep(3000);
				}
				

			} catch (IOException | InterruptedException e) {
				e.printStackTrace(); 
			}


		}


	
		while(true) {

			// creation historique
			HistoriqueCapteurPolluant hRandom = new HistoriqueCapteurPolluant();
			hRandom.setFk_id_capteur(this.capteurPolluant.getId());
			hRandom.setVal_co2(rd_co2());
			hRandom.setVal_no2(rd_no2());
			hRandom.setVal_pf(rd_pf());
			
			//continuité tmp
			int chiox = rd.nextInt(1);
			if(chiox==1)
				tmp+=1;
			else
				tmp-=4;
				
			hRandom.setVal_tmp(String.valueOf(tmp));

			//envoie message 
			synchronized (client) { 
				//envoi historique pollution au serveur 
				try { 

					reponseServ = sendPllutionHistorical(hRandom);
					System.out.println("Capteur: "+this.capteurPolluant.getId()+"-Reponse Serveur: "+reponseServ);
					Thread.sleep(3000); 


				} catch (IOException | InterruptedException e) {
					e.printStackTrace(); 
				}


			}
		}


	}

	private String rd_co2() {
		int min = Math.abs(380);
		int max = Math.abs(Integer.parseInt(capteurPolluant.getSeuil_co2()));

		return String.valueOf(rd.nextInt(max + 1 - min) + min);

	}

	private String rd_no2() {
		int min = Math.abs(0);
		int max = Math.abs(Integer.parseInt(capteurPolluant.getSeuil_no2()));

		return String.valueOf(rd.nextInt(max + 1 - min) + min);

	}

	private String rd_pf() {
		int min = Math.abs(0);
		int max = Math.abs(Integer.parseInt(capteurPolluant.getSeuil_pf()));

		return String.valueOf(rd.nextInt(max + 1 - min) + min);

	}

	private String rd_tmp() {
		int min = Integer.parseInt(capteurPolluant.getSeuil_min_tmp());
		int max = Integer.parseInt(capteurPolluant.getSeuil_max_tmp());

		return String.valueOf(rd.nextInt(max + 1 - min) + min);

	}

	private String sendPllutionHistorical(HistoriqueCapteurPolluant h) throws IOException {

		JSONObject obj = new JSONObject();

		obj.put("demandType", String.valueOf("SEND_MESSAGE_CAPTEUR_POLLUANT"));
		obj.put("start_date", String.valueOf(h.getStart_date()));
		obj.put("val_co2", String.valueOf(h.getVal_co2()));
		obj.put("val_no2", String.valueOf(h.getVal_no2()));
		obj.put("val_pf", String.valueOf(h.getVal_pf()));
		obj.put("val_tmp", String.valueOf(h.getVal_tmp()));
		obj.put("fk_id_capteur", String.valueOf(h.getFk_id_capteur()));
		obj.put("thread", String.valueOf(Thread.currentThread().getName()));

		// System.out.println(obj);
		JSONObject reponse;
		String reponseServ = "KO";
		reponse = SocketClient.sendMessage(obj);
		//System.out.println(reponse);
		reponseServ = (String) reponse.get("reponse");

	
		return reponseServ;
	}

}