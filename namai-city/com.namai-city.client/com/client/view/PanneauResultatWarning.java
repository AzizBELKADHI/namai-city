package com.client.view;

import java.awt.BorderLayout;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import indicator.SensorPolluantIndicator;

public class PanneauResultatWarning extends JPanel {

	public PanneauResultatWarning(Timestamp dateStart, Timestamp dateEnd, String namePolluant, List<Integer> listThreshold, ArrayList<SensorPolluantIndicator> listeWarningWithoutDate) {
		System.out.println("début :"+dateStart.toString());
		System.out.println("fin :"+dateEnd.toString());
		ArrayList<SensorPolluantIndicator> listeWarning = new ArrayList<SensorPolluantIndicator>();
		for(int i =0;i<listeWarningWithoutDate.size();i++) {
			if((listeWarningWithoutDate.get(i).getDate().before(dateEnd)) && (listeWarningWithoutDate.get(i).getDate().after(dateStart))) {
				listeWarning.add(listeWarningWithoutDate.get(i));
			}
		}
		
		if(listeWarning.size()==0){
			
			JLabel errorMessage = new JLabel("Pas d'alertes pour cette plage de date");
			this.add(errorMessage, BorderLayout.CENTER);
			this.repaint();
		}else {			
		
		if(!namePolluant.equals("TMP")) {
		double dptMoyen = 0.00;
		switch(namePolluant) {
		case "CO2" :
			for(int i=0;i<listeWarning.size();i++) {
				dptMoyen=dptMoyen+(Integer.valueOf(listeWarning.get(i).getCo2())-listThreshold.get(0));
				
			}
			break;
		case "NO2" :
			for(int i=0;i<listeWarning.size();i++) {
				dptMoyen=dptMoyen+(Integer.valueOf(listeWarning.get(i).getNo2())-listThreshold.get(0));
				
			}
			break;
		case "PF" :
			for(int i=0;i<listeWarning.size();i++) {
				dptMoyen=dptMoyen+(Integer.valueOf(listeWarning.get(i).getPf())-listThreshold.get(0));
				
			}
			break;
		}
		dptMoyen=dptMoyen/(listeWarning.size());
		double txDpt = (100*dptMoyen)/listThreshold.get(0);
		
		Object[][] donnees = {
				{"Date de début :",  dateStart.toString()},
				{"Date de fin :",  dateEnd.toString()},
				{"Nombre d’alertes pour le capteur ",  listeWarning.size()},
				{"dépassement du seuil de :",  namePolluant},
				{"seuil du polluant :",  listThreshold.get(0)},
				{"Dépassement moyen du seuil",  dptMoyen},
				{"Taux de dépassement", txDpt+"%"}
		};

		String[] entetes = {"Indicateur :", " "};
		JTable TableThreshold = new JTable(donnees, entetes);
		TableThreshold.setCellSelectionEnabled(false);
		this.add(TableThreshold.getTableHeader(), BorderLayout.NORTH);
		this.add(new JScrollPane(TableThreshold), BorderLayout.CENTER);
		this.repaint();
		
		}else {
			int cptTmpMax = 0;
			double dptAvgMax = 0.00;
			int cptTmpMin = 0;
			double dptAvgMin = 0.00;
			
			for(int i=0;i<listeWarning.size();i++) {
				if(Integer.valueOf(listeWarning.get(i).getTmp())>listThreshold.get(1)) {
					cptTmpMax++;
					dptAvgMax=dptAvgMax+(Integer.valueOf(listeWarning.get(i).getTmp())-listThreshold.get(1));
				}else if(Integer.valueOf(listeWarning.get(i).getTmp())<listThreshold.get(0)) {
					cptTmpMin++;
					dptAvgMin=dptAvgMin+(Integer.valueOf(listeWarning.get(i).getTmp())-listThreshold.get(0));
				}
			}		
			dptAvgMax=dptAvgMax/cptTmpMax;
			dptAvgMin=dptAvgMin/cptTmpMin;
			double txDptMax = (100*dptAvgMax)/listThreshold.get(1);
			double txDptMin = (100*dptAvgMin)/listThreshold.get(0);
			
			Object[][] donnees = {
					{"Date de début :",  dateStart.toString()},
					{"Date de fin :",  dateEnd.toString()},
					{"Nb d’alertes sur le seuil maximal : "+listThreshold.get(1),  cptTmpMax},
					{"Nb d’alertes sous le seuil minimal : "+listThreshold.get(0),  cptTmpMin},
					{"Dépassement moyen du seuil maximal", dptAvgMax},
					{"Taux de dépassement du seuil maximal", txDptMax+"%"},
					{"Dépassement moyen du seuil minimal", dptAvgMin},
					{"Taux de dépassement du seuil minimal", txDptMin+"%"}
			};

			String[] entetes = {"Indicateur :", " "};
			JTable TableThreshold = new JTable(donnees, entetes);
			TableThreshold.setCellSelectionEnabled(false);
			this.add(TableThreshold.getTableHeader(), BorderLayout.NORTH);
			this.add(new JScrollPane(TableThreshold), BorderLayout.CENTER);
			this.repaint();
		}
	}
	}
}
