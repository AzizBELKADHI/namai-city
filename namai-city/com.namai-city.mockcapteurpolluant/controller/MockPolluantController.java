package controller;

import java.io.IOException;
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
	SocketClient client;
	private Map<String, Map<String, String>> scenarios;

	public MockPolluantController() {
		client=new SocketClient();
	}

	public void startMock() {
		ArrayList<CapteurPolluant> listCapteurs = null;
		try {
			this.client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());


			//recherche de tous les capteurs de la base de données
			listCapteurs = selectAllCapteurPolluant();
			System.out.println(listCapteurs);

			//SocketClient.stopConnection();
			
			//rechercher les scenarios des capteurs
			this.scenarios = new HashMap<String, Map<String,String>>();
			
			String scenarios = GetScenarios.getScenarios();
			String [] tabCapteurs = scenarios.split("!");
			for (int i = 0; i < tabCapteurs.length; i++) {
				String [] tab =  tabCapteurs[i].split("-"); 
				String id = tab[0];
				String hists_pollution = tab[1];
				
				//récupère le chiffre de l'id du capteur
				//La clé est l'id du capteur
				String[] tab2 = id.split(":");
				id = tab2[1];
				
				//Je sépare les historiques de pollutions
				String[] tab3 = hists_pollution.split(";");
				Map<String, String> mapVarPollution = new HashMap<String, String>();
				
				for(int j = 0; j < tab3.length; j++) {
					// recherche les noms des variables 
					String[] historique = tab3[j].split("/");
					String nom_hist_pollution = historique[0];
					//recherche laeurs de pollution
					String valHistorique = historique[1];
					
					//Remplissage de la seconde Map : nom  de la variables de pollution (ex:co2) en clé et en valeurs= scenarios (ex val1:400-2) 
					mapVarPollution.put(nom_hist_pollution, valHistorique);
				}
				
				//Remplisssage de la première Map : clé =id du capteur et en valeur= la seconde Map
				this.scenarios.put(id, mapVarPollution);
				
			}

			//instanciation des threads capteurs polluant
			for (CapteurPolluant capteurPolluant : listCapteurs) {
				//Récupération des scenarios du capteur
				Map<String, String> mapHistoriques = new HashMap<String, String>();
				mapHistoriques = this.scenarios.get(String.valueOf(capteurPolluant.getId()));
					
				Thread thread = new Thread(new ThreadCapteurPolluant(capteurPolluant, client, mapHistoriques));
				thread.start();
			}
			

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

	}

	private ArrayList<CapteurPolluant> selectAllCapteurPolluant() throws IOException {
		JSONObject obj=new JSONObject();
		obj.put("demandType",String.valueOf("SELECT_ALL_CAPTEUR_POLLUANT"));
		System.out.println(obj);
		JSONObject reponse; 
		ArrayList<CapteurPolluant> listCapteurs = new ArrayList<CapteurPolluant>();
		reponse = SocketClient.sendMessage(obj);
		ArrayList<JSONObject> listRepServeur= (ArrayList<JSONObject>) reponse.get("listCapteurs");
		//System.out.println( listRepServeur.toString()+"\n"); // Display data

		for (JSONObject repServeur : listRepServeur) {
			Long id = (Long) repServeur.get("id");
			String adresse_ip = (String)repServeur.get("adresse_ip");
			String localisation = (String)repServeur.get("localisation");
			String seuil_co2 =  (String)repServeur.get("seuil_co2");
			String seuil_no2=  (String)repServeur.get("seuil_no2");
			String seuil_pf = (String)repServeur.get("seuil_pf");
			String seuil_min_tmp = (String)repServeur.get("seuil_min_tmp");
			String seuil_max_tmp = (String)repServeur.get("seuil_max_tmp");

			listCapteurs.add(new CapteurPolluant(id, adresse_ip, localisation, seuil_co2, seuil_no2, seuil_pf, seuil_min_tmp, seuil_max_tmp));
		}

		return listCapteurs;
	}
	

	public void stopMock() {
		try {
			SocketClient.stopConnection();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}