package com.client.view;

import java.awt.BorderLayout;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import indicator.CarIndicator;
import indicator.SensorPolluantIndicator;

public class PanneauResultatCar extends JPanel {

	public PanneauResultatCar(Timestamp dateStart, Timestamp dateEnd, ArrayList<CarIndicator> liste) {
		System.out.println("début :"+dateStart.toString());
		System.out.println("fin :"+dateEnd.toString());

		int nb_voiture = 0;
		ArrayList<CarIndicator> listeCar = new ArrayList<CarIndicator>();
		for(int i =0;i<liste.size();i++) {
			if((liste.get(i).getDate().before(dateEnd)) && (liste.get(i).getDate().after(dateStart))) {
				listeCar.add(liste.get(i));
				nb_voiture=nb_voiture+liste.get(i).getCarsNb();
			}
		}
		
		if(listeCar.size()==0){
			JLabel errorMessage = new JLabel("Pas de voitures détectées entre ces dates");
			this.add(errorMessage, BorderLayout.CENTER);
			this.repaint();
		}else {
			long avg_car = nb_voiture/listeCar.size();
			String result = " - "+avg_car+" - ";
			Object[][] donnees = {
					{"Date de début :",  dateStart.toString()},
					{"Date de fin :",  dateEnd.toString()},
					{"Nombre de voiture moyen entre les 2 dates", result }
					
			};

			String[] entetes = {"Indicateur :", " "};
			JTable tablePosition = new JTable(donnees, entetes);
			tablePosition.setCellSelectionEnabled(false);
			this.add(tablePosition.getTableHeader(), BorderLayout.NORTH);
			this.add(new JScrollPane(tablePosition), BorderLayout.CENTER);
			this.repaint();
			System.out.println("normalement ca marche");
		}
	}

}
