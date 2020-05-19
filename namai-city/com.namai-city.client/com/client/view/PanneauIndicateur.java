package com.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.json.simple.JSONObject;

import com.client.application.TestJson;
import com.client.controller.SocketClient;
import com.commons.model.AccessServer;

import indicator.CarIndicator;
import indicator.Sensor;
import indicator.SensorIndicator;
import indicator.SensorPolluantIndicator;
import indicator.StationIndicator;


public class PanneauIndicateur extends JPanel implements ActionListener{
	private JButton tableau;
	private JButton tableauStation;
	private JButton graphique;
	private JButton graphiqueStation; 
	private JButton valider; 
	private JButton valider5; 
	private JButton tableauBorne; 
	private JButton graphiqueBorne; 
	private JButton tableauSensorCar; 
	private JButton graphiqueSensorCar; 

	private JComboBox listeChoix; 
	private JComboBox listeChoixStation; 
	private JComboBox listeChoixCapteur; 
	private JComboBox listeChoixPolluant; 
	private JComboBox listeChoixBorne; 
	private JComboBox listeChoiceSensorCar; 
	private JComboBox dayDateDebut;
	private JComboBox monthDateDebut;
	private JComboBox yearDateDebut;
	private JComboBox dayDateFin;
	private JComboBox monthDateFin;
	private JComboBox yearDateFin;
	private JComboBox dayDateDebut5;
	private JComboBox monthDateDebut5;
	private JComboBox yearDateDebut5;
	private JComboBox dayDateFin5;
	private JComboBox monthDateFin5;
	private JComboBox yearDateFin5;



	JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
	JPanel firstPanel = new JPanel();
	JPanel secondPanel = new JPanel();
	JPanel thirdPanel = new JPanel(); 
	JPanel fourthPanel = new JPanel();
	JPanel fifthPanel = new JPanel(); 
	JPanel sixthPanel = new JPanel(); 

	PanneauResultatSensor result;
	PanneauResultatStation result2;
	PanneauResultatWarning result3;
	PanneauResultatBorne result4; 
	PanneauResultatCar result5;
	PanneauResultatSensorCar result6; 

	GridBagLayout a  = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	public PanneauIndicateur()  {
		this.setForeground(Couleur.getBgApp());
		this.setFont(new Font("Arial", Font.BOLD, 14) );
		this.setBorder(new LineBorder(Couleur.getBgTitle()));
		this.setPreferredSize(new Dimension(600,800));


		// PANNEAU CAPTEUR POLLUANT
		firstPanel.setLayout(a);
		JLabel firstLabel = new JLabel("Indicateur concernant le  nombre de capteurs polluant");
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 10;
		c.insets = new Insets(30,30,100,30);
		firstPanel.add(firstLabel,c);

		Object[] choix = new Object[] { "Position"}; 
		listeChoix = new JComboBox(choix);
		c.gridx = 1;
		firstPanel.add(listeChoix,c);
		c.gridx = 2;
		tableau = new JButton("Tableau");
		tableau.addActionListener(this);


		tableau.setPreferredSize(new Dimension(100,25));
		firstPanel.add(tableau,c);	
		System.out.println("insertion bouton fait");

		c.gridx = 3;
		graphique = new JButton("Graphique");
		graphique.addActionListener(this);
		graphique.setPreferredSize(new Dimension(100,25));
		firstPanel.add(graphique,c);	
		onglets.addTab("INDICATEUR CAPTEUR POLLUANT",firstPanel);


		// PANNEAU STATION TRAM
		secondPanel.setLayout(a);
		JLabel secondLabel = new JLabel("Indicateur concernant le nombre de stations du tram");
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 10;
		c.insets = new Insets(30,30,100,30);
		secondPanel.add(secondLabel,c);

		Object[] choixStation = new Object[] { "Position"}; 
		listeChoixStation = new JComboBox(choixStation);
		c.gridx = 1;
		secondPanel.add(listeChoixStation,c);

		c.gridx = 2;
		tableauStation = new JButton("Tableau");
		tableauStation.addActionListener(this);
		tableauStation.setPreferredSize(new Dimension(100,25));
		secondPanel.add(tableauStation,c);	

		c.gridx = 3;
		graphiqueStation = new JButton("Graphique");
		graphiqueStation.addActionListener(this);
		graphiqueStation.setPreferredSize(new Dimension(100,25));
		secondPanel.add(graphiqueStation,c);


		secondPanel.setPreferredSize(new Dimension(400,600));
		onglets.addTab("INDICATEUR STATION",secondPanel);

		// PANNEAU BORNE : 

		thirdPanel.setLayout(a);
		JLabel thirdLabel = new JLabel("Indicateur concernant le nombre de bornes dans la ville ");
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 10;
		c.insets = new Insets(30,30,100,30);
		thirdPanel.add(thirdLabel,c);
		
		// combox box : permettant de filtrer le nombre de bornes selon la position 
		Object[] choixBorne = new Object[] {"Position"}; 
		listeChoixBorne = new JComboBox(choixBorne);
		c.gridx = 1;
		thirdPanel.add(listeChoixBorne,c);

		c.gridx = 2;
		tableauBorne = new JButton("Tableau");
		tableauBorne.addActionListener(this);
		tableauBorne.setPreferredSize(new Dimension(100,25));
		thirdPanel.add(tableauBorne,c);	

		c.gridx = 3;
		graphiqueBorne = new JButton("Graphique");
		graphiqueBorne.addActionListener(this);
		graphiqueBorne.setPreferredSize(new Dimension(100,25));
		thirdPanel.add(graphiqueBorne,c);

		thirdPanel.setPreferredSize(new Dimension(400,600));
		onglets.addTab("INDICATEUR BORNES",thirdPanel);


		// PANNEAU : récupération du taux de dépassement / du nb d'alerte déclanché par chaque capteur et par quel polluant 
		//fourthPanel.setLayout(a);
		JLabel fourthLabel = new JLabel("Indicateur concernant le détail de chaque alerte");
		c.gridx = 0;
		fourthPanel.add(fourthLabel, c);
		c.gridy = 1;
		// selectionner le capteur ayant déclancher une alerte 
		Integer[] choixCapteur = selectCapteur(); 
		listeChoixCapteur = new JComboBox(choixCapteur);
		c.gridx = 0;
		fourthPanel.add(listeChoixCapteur,c);

		// selectionner l'un des polluants
		Object[] choixPolluant = new Object[] { "CO2", "NO2", "PF", "TMP"}; 
		listeChoixPolluant = new JComboBox(choixPolluant);
		c.gridx = 1;
		fourthPanel.add(listeChoixPolluant,c);

		// selectionner la période de récupération de l'alerte 
		//DATE DEBUT
		c.gridy = 2;
		c.gridx = 0;
		JLabel dayDebutLabel = new JLabel("Jour début");
		fourthPanel.add(dayDebutLabel,c);
		c.gridx = 1;
		Integer[] choixDayDebut = new Integer[31];
		for(int i=1;i<=31;i++) {
			choixDayDebut[i-1]=i;
		}
		dayDateDebut = new JComboBox(choixDayDebut);
		fourthPanel.add(dayDateDebut,c);
		c.gridx = 2;
		JLabel monthDebutLabel = new JLabel("Mois début");
		fourthPanel.add(monthDebutLabel,c);
		c.gridx = 3;
		Integer[] choixMonthDebut = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
		monthDateDebut = new JComboBox(choixMonthDebut);
		fourthPanel.add(monthDateDebut,c);
		c.gridx = 4;
		JLabel yearDebutLabel = new JLabel("Année début");
		fourthPanel.add(yearDebutLabel,c);
		c.gridx = 5;
		Integer[] choixYearDebut = new Integer[100];
		for(int i=2000;i<2100;i++) {
			choixYearDebut[i-2000]=i;
		}		
		yearDateDebut = new JComboBox(choixYearDebut);
		fourthPanel.add(yearDateDebut,c);
		c.gridy = 3;
		c.gridx = 0;

		// FIN DATE DEBUT 

		// DATE FIN
		JLabel dayFinLabel = new JLabel("Jour fin");
		fourthPanel.add(dayFinLabel,c);
		c.gridx = 1;
		Integer[] choixDayFin = new Integer[31];
		for(int i=1;i<=31;i++) {
			choixDayFin[i-1]=i;
		}
		dayDateFin = new JComboBox(choixDayFin);
		fourthPanel.add(dayDateFin,c);
		c.gridx = 2;
		JLabel monthFinLabel = new JLabel("Mois fin");
		fourthPanel.add(monthFinLabel,c);
		c.gridx = 3;
		Integer[] choixMonthFin = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
		monthDateFin = new JComboBox(choixMonthFin);
		fourthPanel.add(monthDateFin,c);
		c.gridx = 4;
		JLabel yearFinLabel = new JLabel("Année fin");
		fourthPanel.add(yearFinLabel,c);
		c.gridx = 5;
		Integer[] choixYearFin = new Integer[100];
		for(int i=2000;i<2100;i++) {
			choixYearFin[i-2000]=i;
		}		
		yearDateFin = new JComboBox(choixYearFin);
		fourthPanel.add(yearDateFin,c);

		// FIN DATE FIN 
		c.gridy = 4;		
		c.gridx = 2;
		valider = new JButton("valider");
		valider.addActionListener(this);
		valider.setPreferredSize(new Dimension(100,25));
		fourthPanel.add(valider,c);

		fourthPanel.setPreferredSize(new Dimension(400,600));
		onglets.addTab("INDICATEUR  DETAIL ALERTE",fourthPanel);


		// PANNEAU NB VOITURE: 

		//fifthPanel.setLayout(a);
		JLabel fifthLabel = new JLabel("Indicateur concernant le nombre de voitures selon la date");
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 10;
		c.insets = new Insets(30,30,100,30);
		fifthPanel.add(fifthLabel,c);
		c.gridy = 1;
		c.gridx = 0;
		JLabel dayDebutLabel5 = new JLabel("Jour début");
		fifthPanel.add(dayDebutLabel5,c);
		c.gridx = 1;
		Integer[] choixDayDebut5 = new Integer[31];
		for(int i=1;i<=31;i++) {
			choixDayDebut5[i-1]=i;
		}
		dayDateDebut5 = new JComboBox(choixDayDebut5);
		fifthPanel.add(dayDateDebut5,c);
		c.gridx = 2;
		JLabel monthDebutLabel5 = new JLabel("Mois début");
		fifthPanel.add(monthDebutLabel5,c);
		c.gridx = 3;
		Integer[] choixMonthDebut5 = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
		monthDateDebut5 = new JComboBox(choixMonthDebut5);
		fifthPanel.add(monthDateDebut5,c);
		c.gridx = 4;
		JLabel yearDebutLabel5 = new JLabel("Année début");
		fifthPanel.add(yearDebutLabel5,c);
		c.gridx = 5;
		Integer[] choixYearDebut5 = new Integer[100];
		for(int i=2000;i<2100;i++) {
			choixYearDebut5[i-2000]=i;
		}		
		yearDateDebut5 = new JComboBox(choixYearDebut5);
		fifthPanel.add(yearDateDebut5,c);
		c.gridy = 2;
		c.gridx = 0;
		JLabel dayFinLabel5 = new JLabel("Jour fin");
		fifthPanel.add(dayFinLabel5,c);
		c.gridx = 1;
		Integer[] choixDayFin5 = new Integer[31];
		for(int i=1;i<=31;i++) {
			choixDayFin5[i-1]=i;
		}
		dayDateFin5 = new JComboBox(choixDayFin5);
		fifthPanel.add(dayDateFin5,c);
		c.gridx = 2;
		JLabel monthFinLabel5 = new JLabel("Mois fin");
		fifthPanel.add(monthFinLabel5,c);
		c.gridx = 3;
		Integer[] choixMonthFin5 = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
		monthDateFin5 = new JComboBox(choixMonthFin5);
		fifthPanel.add(monthDateFin5,c);
		c.gridx = 4;
		JLabel yearFinLabel5 = new JLabel("Année fin");
		fifthPanel.add(yearFinLabel5,c);
		c.gridx = 5;
		Integer[] choixYearFin5 = new Integer[100];
		for(int i=2000;i<2100;i++) {
			choixYearFin5[i-2000]=i;
		}		
		yearDateFin5 = new JComboBox(choixYearFin5);
		fifthPanel.add(yearDateFin5,c);

		c.gridy = 3;		
		c.gridx = 2;
		valider5 = new JButton("valider");
		valider5.addActionListener(this);
		valider5.setPreferredSize(new Dimension(100,25));
		fifthPanel.add(valider5,c);
		fifthPanel.setPreferredSize(new Dimension(400,600));
		onglets.addTab("INDICATEUR NB VOITURE",fifthPanel);


		// PANNEAU NOMBRE DE CAPTEUR VEHICULE: 

		sixthPanel.setLayout(a);
		JLabel sixthLabel = new JLabel("Indicateur concernant le nombre de capteur véhicule par position");
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 10;
		c.insets = new Insets(30,30,100,30);
		sixthPanel.add(sixthLabel,c);

		Object[] choiceSensorCar = new Object[] { "Position"}; 
		listeChoiceSensorCar = new JComboBox(choiceSensorCar);
		c.gridx = 1;
		sixthPanel.add(listeChoiceSensorCar,c);

		c.gridx = 2;
		tableauSensorCar = new JButton("Tableau");
		tableauSensorCar.addActionListener(this);
		tableauSensorCar.setPreferredSize(new Dimension(100,25));
		sixthPanel.add(tableauSensorCar,c);	

		c.gridx = 3;
		graphiqueSensorCar = new JButton("Graphique");
		graphiqueSensorCar.addActionListener(this);
		graphiqueSensorCar.setPreferredSize(new Dimension(100,25));
		sixthPanel.add(graphiqueSensorCar,c);


		sixthPanel.setPreferredSize(new Dimension(400,600));
		onglets.addTab("INDICATEUR NB CAPTEUR VEHICULE",sixthPanel);



		onglets.setOpaque(true);
		this.add(onglets);

	}


	// récupération de la liste des capteurs ayant déclanché une alerte
	private Integer[] selectCapteur() {
		TestJson t = new TestJson();
		ArrayList<Integer> liste = new ArrayList<Integer>();
		Integer[] tableau = null;
		try {
			liste = t.getIdSensorPolluant();
			tableau = new Integer[liste.size()];
			for(int i =0;i<liste.size();i++) {
				tableau[i]=liste.get(i);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
		return tableau;
	}


	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(tableau)) { 
			System.out.println("Bonjour"); 
			TestJson t = new TestJson(); 
			try {
				ArrayList<SensorIndicator> liste = t.goSensor();
				if(result!=null) {
					result.removeAll();
				}
				result = new PanneauResultatSensor("tableau", listeChoix.getSelectedItem().toString(),liste);
				c.gridx = 1;
				c.gridy = 1;
				firstPanel.add(result,c);
				result.setVisible(true);
				firstPanel.revalidate();			

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {

				e1.printStackTrace();
			}		

		} else if (e.getSource().equals(graphique)) {
			System.out.println("Bonjour graphique capteurs "); 
			TestJson t = new TestJson(); 
			try {
				ArrayList<SensorIndicator> liste = t.goSensor();
				if(result!=null) {
					result.removeAll();
				}
				result = new PanneauResultatSensor("graphique", listeChoix.getSelectedItem().toString(),liste);
				c.gridx = 1;
				c.gridy = 1;
				firstPanel.add(result,c);
				result.setVisible(true);
				firstPanel.revalidate();			

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		} else if (e.getSource().equals(tableauStation)) {
			System.out.println("Bonjour  tableau Station"); 
			TestJson t = new TestJson(); 
			try {
				ArrayList<StationIndicator> liste2 = t.goStation();
				if(result2!= null) {
					result2.removeAll();
				}
				result2 = new PanneauResultatStation("tableauStation", listeChoixStation.getSelectedItem().toString(),liste2);
				result2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				result2.setSize(new Dimension(600,800));
				result2.pack();
				result2.setVisible(true);
				secondPanel.revalidate();				

			} catch (SQLException e1) {
				//< TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {

				e1.printStackTrace();


			}
		} else if (e.getSource().equals(graphiqueStation)) {
			System.out.println("Bonjour graphique Station "); 
			TestJson t = new TestJson(); 
			try {
				ArrayList<StationIndicator> liste2 = t.goStation();
				if(result2!=null) {
					result2.removeAll();
				}
				result2 = new PanneauResultatStation("graphiqueStation", listeChoixStation.getSelectedItem().toString(),liste2);
				result2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				result2.setSize(new Dimension(600,800));
				result2.pack();
				result2.setVisible(true);
				secondPanel.revalidate();			

			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {

				e1.printStackTrace();

			}

		} else if (e.getSource().equals(tableauBorne)) {
			System.out.println("Bonjour  tableau Borne"); 
			TestJson t = new TestJson(); 
			try {
				ArrayList<SensorIndicator> liste2 = t.goBorne();
				if(result4!= null) {
					result4.removeAll();
				}
				result4 = new PanneauResultatBorne("tableauBorne", listeChoixBorne.getSelectedItem().toString(),liste2);
				result4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				result4.setSize(new Dimension(600,800));
				result4.pack();
				result4.setVisible(true);
				thirdPanel.revalidate();			

			} catch (SQLException e1) {
				//< TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {

				e1.printStackTrace();


			}
		} else if (e.getSource().equals(graphiqueBorne)) {
			System.out.println("Bonjour graphique Borne "); 
			TestJson t = new TestJson(); 
			try {
				ArrayList<SensorIndicator> liste2 = t.goBorne();
				if(result4!=null) {
					result4.removeAll();
				}
				result4 = new PanneauResultatBorne("graphiqueBorne", listeChoixBorne.getSelectedItem().toString(),liste2);
				result4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				result4.setSize(new Dimension(600,800));
				result4.pack();
				result4.setVisible(true);
				thirdPanel.revalidate();


			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {

				e1.printStackTrace();

			}

		} else if(e.getSource().equals(tableauSensorCar)) { 
			System.out.println("Bonjour"); 
			TestJson t = new TestJson(); 
			try {
				ArrayList<SensorIndicator> liste = t.goSensorCar();
				if(result6!=null) {
					result6.removeAll();
				}
				result6 = new PanneauResultatSensorCar ("tableauSensorCar", listeChoiceSensorCar.getSelectedItem().toString(),liste);
				result6.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				result6.setSize(new Dimension(600,800));
				result6.pack();
				result6.setVisible(true);
				sixthPanel.revalidate();			

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {

				e1.printStackTrace();
			}		

		} else if (e.getSource().equals(graphiqueSensorCar)) {
			System.out.println("Bonjour graphique capteur vehicule "); 
			TestJson t = new TestJson(); 
			try {
				ArrayList<SensorIndicator> liste = t.goSensorCar();
				if(result6!=null) {
					result6.removeAll();
				}
				result6 = new PanneauResultatSensorCar ("graphiqueSensorCar", listeChoiceSensorCar.getSelectedItem().toString(),liste);
				result6.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				result6.setSize(new Dimension(600,800));
				result6.pack();
				result6.setVisible(true);
				sixthPanel.revalidate();			

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}

		else if (e.getSource().equals(valider)) {
			System.out.println("Bonjour Capteur polluant"); 
			TestJson t = new TestJson(); 
			try {
				List<Integer> listeSeuil = t.getThreshold(listeChoixPolluant.getSelectedItem().toString(),Integer.valueOf(listeChoixCapteur.getSelectedItem().toString()));
				ArrayList<SensorPolluantIndicator> listeWarning = t.getWarning(listeChoixPolluant.getSelectedItem().toString(), Integer.valueOf(listeChoixCapteur.getSelectedItem().toString()),listeSeuil);
				if(result3!= null) {
					result3.removeAll();
				}
				int year1 = Integer.valueOf((String) yearDateDebut.getSelectedItem().toString());
				int month1 = Integer.valueOf((String) monthDateDebut.getSelectedItem().toString());
				int day1 = Integer.valueOf((String) dayDateDebut.getSelectedItem().toString());
				System.out.println("debut"+year1+"|"+month1+"|"+day1);
				int year2 = Integer.valueOf((String) yearDateFin.getSelectedItem().toString());
				int month2 = Integer.valueOf((String) monthDateFin.getSelectedItem().toString());
				int day2 = Integer.valueOf((String) dayDateFin.getSelectedItem().toString());
				System.out.println("fin"+year2+"|"+month2+"|"+day2);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date date = sdf.parse(day1+"/"+month1+"/"+year1);
				System.out.println(date.toString());
				Timestamp dateDebut = new Timestamp(date.getTime());				
				date = sdf.parse(day2+"/"+month2+"/"+year2);
				Timestamp dateFin = new Timestamp(date.getTime());
				result3 = new PanneauResultatWarning(dateDebut,dateFin,listeChoixPolluant.getSelectedItem().toString(),listeSeuil,listeWarning);
				c.gridx = 1;
				c.gridy = 1;
				fourthPanel.add(result3,c);
				result3.setVisible(true);
				fourthPanel.revalidate();			

			} catch (IOException | ParseException e1) {

				e1.printStackTrace();


			}
		}  else if (e.getSource().equals(valider5)) {
			System.out.println("Bonjour Voiture"); 
			TestJson t = new TestJson(); 
			try {
				ArrayList<CarIndicator> liste = t.goCar();
				if(result5!= null) {
					result5.removeAll();
				}
				int year1 = Integer.valueOf((String) yearDateDebut5.getSelectedItem().toString());
				int month1 = Integer.valueOf((String) monthDateDebut5.getSelectedItem().toString());
				int day1 = Integer.valueOf((String) dayDateDebut5.getSelectedItem().toString());
				System.out.println("debut"+year1+"|"+month1+"|"+day1);
				int year2 = Integer.valueOf((String) yearDateFin5.getSelectedItem().toString());
				int month2 = Integer.valueOf((String) monthDateFin5.getSelectedItem().toString());
				int day2 = Integer.valueOf((String) dayDateFin5.getSelectedItem().toString());
				System.out.println("fin"+year2+"|"+month2+"|"+day2);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date date = sdf.parse(day1+"/"+month1+"/"+year1);
				System.out.println(date.toString());
				Timestamp dateDebut = new Timestamp(date.getTime());				
				date = sdf.parse(day2+"/"+month2+"/"+year2);
				Timestamp dateFin = new Timestamp(date.getTime());
				result5 = new PanneauResultatCar(dateDebut,dateFin,liste);
				c.gridx = 1;
				c.gridy = 1;
				fifthPanel.add(result5,c);
				result5.setVisible(true);
				fifthPanel.revalidate();			

			} catch (IOException | ParseException | SQLException e1) {

				e1.printStackTrace();

			}

		}
	}
}


