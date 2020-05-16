package com.client.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import indicator.SensorPolluantIndicator;

public class PanneauResultatWarning extends JPanel {

	public PanneauResultatWarning(String nomPolluant, List<Integer> listeSeuil, ArrayList<SensorPolluantIndicator> listeWarning) {
		
		if(!nomPolluant.equals("TMP")) {
		double dptMoyen = 0.00;
		switch(nomPolluant) {
		case "CO2" :
			for(int i=0;i<listeWarning.size();i++) {
				dptMoyen=dptMoyen+(Integer.valueOf(listeWarning.get(i).getCo2())-listeSeuil.get(0));
				
			}
			break;
		case "NO2" :
			for(int i=0;i<listeWarning.size();i++) {
				dptMoyen=dptMoyen+(Integer.valueOf(listeWarning.get(i).getNo2())-listeSeuil.get(0));
				
			}
			break;
		case "PF" :
			for(int i=0;i<listeWarning.size();i++) {
				dptMoyen=dptMoyen+(Integer.valueOf(listeWarning.get(i).getPf())-listeSeuil.get(0));
				
			}
			break;
		}
		dptMoyen=dptMoyen/(listeWarning.size());
		double txDpt = listeSeuil.get(0)*dptMoyen/100;
		
		Object[][] donnees = {
				{"Nombre d’alertes pour le capteur "+listeWarning.get(0).getIdFkCap()+" dépassant le seuil maximal de "+nomPolluant+" :"+listeSeuil.get(0),  listeWarning.size()},
				{"id du capteur",  listeWarning.get(0).getIdFkCap()},
				{"dépassement du seuil de :",  nomPolluant},
				{"affichage:",  listeSeuil.get(0)},
				{"Dépassement moyen du seuil",  dptMoyen},
				{"Taux de dépassement", txDpt+"%"}
		};

		String[] entetes = {"Indicateur :", " "};
		JTable tablePosition = new JTable(donnees, entetes);
		tablePosition.setCellSelectionEnabled(false);
		this.add(tablePosition.getTableHeader(), BorderLayout.NORTH);
		this.add(new JScrollPane(tablePosition), BorderLayout.CENTER);
		this.repaint();
		}else {
			int listeTmpMax = 0;
			double dptMoyenMax = 0.00;
			int listeTmpMin = 0;
			double dptMoyenMin = 0.00;
			
			for(int i=0;i<listeWarning.size();i++) {
				if(Integer.valueOf(listeWarning.get(i).getTmp())>listeSeuil.get(1)) {
					listeTmpMax++;
					dptMoyenMax=dptMoyenMax+(Integer.valueOf(listeWarning.get(i).getTmp())-listeSeuil.get(1));
				}else if(Integer.valueOf(listeWarning.get(i).getTmp())<listeSeuil.get(0)) {
					listeTmpMin++;
					dptMoyenMin=dptMoyenMin+(Integer.valueOf(listeWarning.get(i).getTmp())-listeSeuil.get(0));
				}
			}		
			double txDptMax = listeSeuil.get(1)*dptMoyenMax/100;
			double txDptMin = listeSeuil.get(0)*dptMoyenMin/100;
			
			Object[][] donnees = {
					{"Nombre d’alertes pour le capteur "+listeWarning.get(0).getIdFkCap()+" dépassant le seuil maximal de température : "+listeSeuil.get(0),  listeTmpMax},
					{"Nombre d’alertes pour le capteur "+listeWarning.get(0).getIdFkCap()+" dépassant le seuil minimal de température : "+listeSeuil.get(0),  listeTmpMin},
					{"Dépassement moyen du seuil maximal", dptMoyenMax},
					{"Taux de dépassement du seuil maximal", txDptMax+"%"},
					{"Dépassement moyen du seuil minimal", dptMoyenMin},
					{"Taux de dépassement du seuil minimal", txDptMin+"%"}
			};

			String[] entetes = {"Position", "Nombre de station dans la ville"};
			JTable tablePosition = new JTable(donnees, entetes);
			tablePosition.setCellSelectionEnabled(false);
			this.add(tablePosition.getTableHeader(), BorderLayout.NORTH);
			this.add(new JScrollPane(tablePosition), BorderLayout.CENTER);
			this.repaint();
		}
	}
	

}
