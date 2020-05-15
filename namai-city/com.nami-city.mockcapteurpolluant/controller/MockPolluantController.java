package controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.commons.model.AccessServer;
import com.commons.model.SocketClient;

import entities.CapteurPolluant;
import model.GetScenarios;
import model.ThreadCapteurPolluant;

public class MockPolluantController {
	private SocketClient client;
	private Map<String , Map<String,String>> scenarios;

	public MockPolluantController() {
		client = new SocketClient();
	}

	private void startMock() {
		ArrayList<CapteurPolluant> listCapteurs = null;
		try {
			this.client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
			
			//recherche de tous les capteurs de la BD
			listCapteurs = selectAllCapteurPolluant();
			System.out.println(listCapteurs);
			this.scenarios = new HashMap<String, Map<String,String>>();
			String scenarios =GetScenarios.getScenarios();
			
			// permet de diviser les scénarios en utilisant les séparateurs "!"
			String [] tabCapteurs = scenarios.split("!");
			for(int i=0; i<tabCapteurs.length; i++) {
				String [] tab = tabCapteurs[i].split("-");
				String id = tab[0];
				String hists_Pollution = tab[1];
				
				//on recupere le chiffre de l'id du capteur 
				//on recupere la clé de l'id du capteur
				String [] tab2 = id.split(":");
				id = tab2[0];
				
				//je sépare les historiques de pollution
				String [] tab3 = hists_Pollution.split(";");
				Map<String, String> mapVarPollution = new HashMap<String, String>();
				for(int j=0; j<tab3.length; j++) {
					
					//on recherche les noms des valeurs
					String [] historique = tab3[j].split("/");
					String mon_hist_pollution = historique[0];
					
					//je recherche les valeurs de pollution
					String valHistorique = historique[1];
					
					//je remplis la seconde map comportant comme clé le nom de l'historique et pour valeur celle de chaque historique
					//exemple(co2:400)
					mapVarPollution.put(mon_hist_pollution, valHistorique);
					
					//je remplis la premiere avec pour cle l'id et pour valeur la seconde map
					this.scenarios.put(id, mapVarPollution);
					
					
					
					
				}
				//instanciation des threads pour capteur polluant
				for (CapteurPolluant capteurPolluant : listCapteurs) {
					//je recupere les scenarios du capteur
					Map<String, String> mapHistoriques = new HashMap<String, String>();
					mapHistoriques = this.scenarios.get(String.valueOf(capteurPolluant.getId()));
					Thread t = new Thread(new ThreadCapteurPolluant(capteurPolluant, client, mapHistoriques));
					t.start();
					
					
					
				}
				
				
				}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}


	}

	private ArrayList<CapteurPolluant> selectAllCapteurPolluant() {
		JSONObject obj = new JSONObject();
		obj.put("demandType", String.valueOf("SELECT_ALL_CAPTEUR_POLLUANT"));
		JSONObject reponse;
		System.out.println(obj);
		ArrayList<CapteurPolluant> listCapteurs = new ArrayList<CapteurPolluant>();
		try {
			reponse = SocketClient.sendMessage(obj);
			ArrayList<JSONObject> listRepServeur = (ArrayList<JSONObject>) reponse.get("listCapteurs");
			for (JSONObject repServeur : listRepServeur) {
				Long id = (Long) repServeur.get("id");
				String adresse_ip = (String) repServeur.get("adresse_ip");
				String localisation = (String) repServeur.get("localisation");
				String seuil_co2 = (String)repServeur.get("Seuil_CO2");
				String seuil_no2 = (String)repServeur.get("Seuil_NO2");
				String seuil_pf = (String)repServeur.get("Seuil_PF");
				String seuil_min_tmp = (String)repServeur.get("Seuil_Min_Tmp");
				String seuil_max_tmp = (String)repServeur.get("Seuil_Max_Tmp");
				CapteurPolluant cp = new CapteurPolluant(id, adresse_ip, localisation, seuil_co2, seuil_no2, seuil_pf, seuil_min_tmp, seuil_max_tmp);
				listCapteurs.add(cp);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return listCapteurs;
	}
}
