package com.client.view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import indicator.StationIndicator;

public class PanneauResultStation extends JFrame{
	public PanneauResultStation(String button, String choice, ArrayList<StationIndicator> list) {
		// recovery of the result of request in the form of graphic and table
		if(button== "tableStation") {

			switch(choice) {
			case "Position" : 
				int cptNorth=0; 
				int cptSouth = 0; 
				int cptWest =0; 
				int cptEast = 0;

				for (StationIndicator s: list) {
					switch(s.getPosition()) {
					case "Nord" : 
						cptNorth = s.getStationNb();
						break; 
					case "Sud" :
						cptSouth = s.getStationNb();
						break; 
					case "Ouest" : 
						cptWest = s.getStationNb(); 
						break; 
					case "Est" : 
						cptEast = s.getStationNb(); 
						break; 
					}
				}

				Object[][] donnees = {
						{"Nord",  cptNorth},
						{"Sud",  cptSouth},
						{"Ouest", cptWest},
						{"Est", cptEast},
						{"Total", cptNorth+cptSouth+cptEast+cptWest}
				};

				String[] entetes = {"Position", "Nombre de station dans la ville"};
				JTable tablePosition = new JTable(donnees, entetes);
				tablePosition.setCellSelectionEnabled(false);
				this.add(tablePosition.getTableHeader(), BorderLayout.NORTH);
				this.add(new JScrollPane(tablePosition), BorderLayout.CENTER);
				this.repaint();
				break;

 
			}
		} else if (button == "graphicStation") {
			switch (choice) {
			case "Position" : 
				int cptNorth=0; 
				int cptSouth = 0; 
				int cptWest =0; 
				int cptEast = 0;
				for(StationIndicator s :list) {
					switch(s.getPosition()){
					case "Nord" :
						cptNorth=cptNorth+s.getStationNb();
						break;
					case "Sud" :
						cptSouth= cptSouth+ s.getStationNb();
						break;
					case "Ouest" :
						cptWest = cptWest + s.getStationNb();
						break;
					case "Est" :
						cptEast = cptEast + s.getStationNb();

						break;
					}				
				}
				DefaultPieDataset pieDataset = new DefaultPieDataset();

				pieDataset.setValue("Nord",cptNorth);
				pieDataset.setValue("Sud",cptSouth);
				pieDataset.setValue("Est",cptEast);
				pieDataset.setValue("Ouest",cptWest);

				JFreeChart pieChart = ChartFactory.createPieChart("Résultat concernant le nombre de stations", pieDataset, true, false, false);
				ChartPanel cPanel = new ChartPanel(pieChart);
				this.add(cPanel,BorderLayout.CENTER);
				cPanel.setVisible(true);
				break; 

			}
		}
	}
}




