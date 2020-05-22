package com.client.view;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSpinner.ListEditor;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import org.json.simple.JSONObject;

import com.commons.model.AccessServer;
import com.commons.model.SocketClient;

import entities.CapteurPolluant;

public class ListCapteur extends AbstractTableModel{
	private String [] entetes   = {" ID", "Adresse_IP", "Localisation", "Seuil_CO2", 
    		"Seuil_NO2", "Seuil_PM","Seuil_temp_Min","Seuil_temp_max","Frequence Releve"};  

	JFrame f;
	SocketClient client;
	private JTable table;
	ArrayList<CapteurPolluant> listCapteur = null;
	public ListCapteur() {
		client=new SocketClient();
	

		try {
			listCapteur = selectAllCapteurPolluant();
			CapteurPolluant [] c = new CapteurPolluant[10];

		}catch (IOException e) {

		}
	}
	
	
	
	public int getRowCount() {
        return listCapteur.size();
    }
 
    public int getColumnCount() {
        return entetes.length;
    }
 
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return listCapteur.get(rowIndex).getId();
            case 1:
                return listCapteur.get(rowIndex).getAdresse_ip();
            case 2:
                return listCapteur.get(rowIndex).getLocalisation();
            case 3:
                return listCapteur.get(rowIndex).getSeuil_co2();
            case 4:
                return listCapteur.get(rowIndex).getSeuil_no2();
            case 5:
            	return listCapteur.get(rowIndex).getSeuil_pf();
            case 6:
            	return listCapteur.get(rowIndex).getSeuil_min_tmp();
            case 7:
            	return listCapteur.get(rowIndex).getSeuil_max_tmp();
            case 8:
            	return listCapteur.get(rowIndex).getFrequence();
            default:
                return null; //Ne devrait jamais arriver
        }
    
}
	

	private ArrayList<CapteurPolluant> selectAllCapteurPolluant() throws IOException {
		this.client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
		JSONObject obj=new JSONObject();
		obj.put("demandType",String.valueOf("SELECT_ALL_CAPTEUR_POLLUANT"));
		System.out.println(obj.toString());
		JSONObject reponse; 
		ArrayList<CapteurPolluant> listCapteurs = new ArrayList<CapteurPolluant>();
		reponse = SocketClient.sendMessage(obj);
		ArrayList<JSONObject> listRepServeur= (ArrayList<JSONObject>) reponse.get("listCapteurs");
		System.out.println( listRepServeur.toString()+"\n"); // Display data

		for (JSONObject repServeur : listRepServeur) {
			Long id = (Long) repServeur.get("id");
			String adresse_ip = (String)repServeur.get("adresse_ip");
			String localisation = (String)repServeur.get("localisation");
			String seuil_co2 =  (String)repServeur.get("seuil_co2");
			String seuil_no2=  (String)repServeur.get("seuil_no2");
			String seuil_pf = (String)repServeur.get("seuil_pf");
			String seuil_min_tmp = (String)repServeur.get("seuil_min_tmp");
			String seuil_max_tmp = (String)repServeur.get("seuil_max_tmp");
			String frequence = (String)repServeur.get("frequence");

			listCapteurs.add(new CapteurPolluant(id, adresse_ip, localisation, seuil_co2, seuil_no2, seuil_pf, seuil_min_tmp, seuil_max_tmp,frequence));
		}

		return listCapteurs;
	}



	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ListCapteur();
			}
		});
	}

}
