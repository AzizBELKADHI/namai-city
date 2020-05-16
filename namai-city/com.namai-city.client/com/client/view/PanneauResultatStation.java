package com.client.view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import indicator.SensorIndicator;
import indicator.StationIndicator;

public class PanneauResultatStation extends JPanel{
	public PanneauResultatStation(String button, String choix2, ArrayList<StationIndicator> liste) {
		if(button== "tableauStation") {

			switch(choix2) {
			case "Position" : 
				int cptNord=0; 
				int cptSud = 0; 
				int cptOuest =0; 
				int cptEst = 0;

				for (StationIndicator s: liste) {
					switch(s.getPosition()) {
					case "Nord" : 
						cptNord = s.getStationNb();
						break; 
					case "Sud" :
						cptSud = s.getStationNb();
						break; 
					case "Ouest" : 
						cptOuest = s.getStationNb(); 
						break; 
					case "Est" : 
						cptEst = s.getStationNb(); 
						break; 
					}
				}

				Object[][] donnees = {
						{"Nord",  cptNord},
						{"Sud",  cptSud},
						{"Ouest", cptOuest},
						{"Est", cptEst},
						{"Total", cptNord+cptSud+cptEst+cptOuest}
				};

				String[] entetes = {"Position", "Nombre de station dans la ville"};
				JTable tablePosition = new JTable(donnees, entetes);
				tablePosition.setCellSelectionEnabled(false);
				this.add(tablePosition.getTableHeader(), BorderLayout.NORTH);
				this.add(new JScrollPane(tablePosition), BorderLayout.CENTER);
				this.repaint();
				break;

			case "Date" : 

				break; 
			}
		} else if (button == "graphiqueStation") {
			switch (choix2) {
			case "Position" : 
				int cptNord=0; 
				int cptSud = 0; 
				int cptOuest =0; 
				int cptEst = 0;
				for(StationIndicator s :liste) {
					switch(s.getPosition()){
					case "Nord" :
						cptNord=cptNord+s.getStationNb();
						break;
					case "Sud" :
						cptSud= cptSud+ s.getStationNb();
						break;
					case "Ouest" :
						cptOuest = cptOuest + s.getStationNb();
						break;
					case "Est" :
						cptEst = cptEst + s.getStationNb();

						break;
					}				
				}
				DefaultPieDataset pieDataset = new DefaultPieDataset();

				pieDataset.setValue("Nord",cptNord);
				pieDataset.setValue("Sud",cptSud);
				pieDataset.setValue("Est",cptEst);
				pieDataset.setValue("Ouest",cptOuest);

				JFreeChart pieChart = ChartFactory.createPieChart("Résultat", pieDataset, true, false, false);
				ChartPanel cPanel = new ChartPanel(pieChart);
				this.add(cPanel,BorderLayout.CENTER);
				cPanel.setVisible(true);
				break; 

			}
		}
	}
}




