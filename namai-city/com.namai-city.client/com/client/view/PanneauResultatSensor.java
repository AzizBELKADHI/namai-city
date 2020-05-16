package com.client.view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import indicator.SensorIndicator;

public class PanneauResultatSensor extends JPanel{

	public PanneauResultatSensor(String button, String choix, ArrayList<SensorIndicator> liste) {
		if(button== "tableau") {

			switch(choix) {
			case "Position" : 
				int cptNord=0; 
				int cptSud = 0; 
				int cptOuest =0; 
				int cptEst = 0;
				for(SensorIndicator s :liste) {
					switch(s.getPosition()){
					case "Nord" :
						cptNord=cptNord+s.getSensorNb();
						break;
					case "Sud" :
						cptSud= cptSud+ s.getSensorNb();
						break;
					case "Ouest" :
						cptOuest = cptOuest + s.getSensorNb();
						break;
					case "Est" :
						cptEst = cptEst + s.getSensorNb();

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

				String[] entetes = {"Position", "Nombre de capteur relevé"};
				JTable tablePosition = new JTable(donnees, entetes);
				tablePosition.setCellSelectionEnabled(false);
				this.add(tablePosition.getTableHeader(), BorderLayout.NORTH);
				this.add(new JScrollPane(tablePosition), BorderLayout.CENTER);
				this.repaint();
				break; 

			case "Type" : 
				int cpt1=0; 
				int cpt2 = 0; 
				int cpt3 =0; 
				for(SensorIndicator s :liste) {
					switch(s.getType()){
					case "calcul_nb_voiture" :
						cpt1=cpt1+s.getSensorNb();
						break;
					case "qualite_air" :
						cpt2= cpt2+ s.getSensorNb();
						break;
					case "borne" :
						cpt3 = cpt3 + s.getSensorNb();
						break;

					}				
				}

				Object[][] donnees2 = {
						{"calcul_nb_voiture",  cpt1},
						{"qualite_air",  cpt2},
						{"borne", cpt3},
						{"Total", cpt1+cpt2+cpt3}
				};

				String[] entetes2 = {"Type", "Nombre de capteur relevé"};
				JTable tableType = new JTable(donnees2, entetes2);
				tableType.setCellSelectionEnabled(false);
				this.add(tableType.getTableHeader(), BorderLayout.NORTH);
				this.add(new JScrollPane(tableType), BorderLayout.CENTER);
				this.repaint();


				break; 

			case "Date": 

				break; 


			}
		} else if (button== "graphique") {
			switch (choix) {
			case "Position" : 
				int cptNord=0; 
				int cptSud = 0; 
				int cptOuest =0; 
				int cptEst = 0;
				for(SensorIndicator s :liste) {
					switch(s.getPosition()){
					case "Nord" :
						cptNord=cptNord+s.getSensorNb();
						break;
					case "Sud" :
						cptSud= cptSud+ s.getSensorNb();
						break;
					case "Ouest" :
						cptOuest = cptOuest + s.getSensorNb();
						break;
					case "Est" :
						cptEst = cptEst + s.getSensorNb();

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

			case "Type" : 
				int cpt1=0; 
				int cpt2 = 0; 
				int cpt3 =0; 
				for(SensorIndicator s :liste) {
					switch(s.getType()){
					case "calcul_nb_voiture" :
						cpt1=cpt1+s.getSensorNb();
						break;
					case "qualite_air" :
						cpt2= cpt2+ s.getSensorNb();
						break;
					case "borne" :
						cpt3 = cpt3 + s.getSensorNb();
						break;

					}				
				}

				DefaultPieDataset pieDataset2 = new DefaultPieDataset();

				pieDataset2.setValue("calcul_nb_voiture",cpt1);
				pieDataset2.setValue("qualite_air",cpt2);
				pieDataset2.setValue("borne",cpt3);
				
				
				JFreeChart pieChart2 = ChartFactory.createPieChart("Résultat", pieDataset2, true, false, false);
				ChartPanel cPanel2 = new ChartPanel(pieChart2);
				this.add(cPanel2,BorderLayout.CENTER);
				cPanel2.setVisible(true);


				break; 

			case "Date": 

				break;
			}
		}
	}
}
