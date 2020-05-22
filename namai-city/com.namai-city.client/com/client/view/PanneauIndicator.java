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


public class PanneauIndicator extends JPanel implements ActionListener{
	private JButton table;
	private JButton tableStation;
	private JButton graphic;
	private JButton graphicStation; 
	private JButton validate; 
	private JButton validate5; 
	private JButton tableBorne; 
	private JButton graphicBorne; 
	private JButton tableSensorCar; 
	private JButton graphicSensorCar; 

	private JComboBox listChoice; 
	private JComboBox listChoiceStation; 
	private JComboBox listChoiceCapteur; 
	private JComboBox listChoicePolluant; 
	private JComboBox listChoiceBorne; 
	private JComboBox listeChoiceSensorCar; 
	private JComboBox dayDateStart;
	private JComboBox monthDateStart;
	private JComboBox yearDateDebut;
	private JComboBox dayDateEnd;
	private JComboBox monthDateEnd;
	private JComboBox yearDateEnd;
	private JComboBox dayDateStart5;
	private JComboBox monthDateStart5;
	private JComboBox yearDateStart5;
	private JComboBox dayDateEnd5;
	private JComboBox monthDateEnd5;
	private JComboBox yearDateEnd5;



	JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
	// creation of panel of each indicator
	JPanel firstPanel = new JPanel();
	JPanel secondPanel = new JPanel();
	JPanel thirdPanel = new JPanel(); 
	JPanel fourthPanel = new JPanel();
	JPanel fifthPanel = new JPanel(); 
	JPanel sixthPanel = new JPanel(); 

	// creation of pane result for the display of each request
	PanneauResultSensor result;
	PanneauResultStation result2;
	PanneauResultWarning result3;
	PanneauResultBorne result4; 
	PanneauResultCar result5;
	PanneauResultSensorCar result6; 

	GridBagLayout a  = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	public PanneauIndicator()  {
		this.setForeground(Couleur.getBgApp());
		this.setFont(new Font("Arial", Font.BOLD, 14) );
		this.setBorder(new LineBorder(Couleur.getBgTitle()));
		this.setPreferredSize(new Dimension(600,800));


		// PANE CAPTEUR POLLUANT
		firstPanel.setLayout(a);
		JLabel firstLabel = new JLabel("Indicateur concernant le  nombre de capteurs polluant");
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 10;
		c.insets = new Insets(30,30,100,30);
		firstPanel.add(firstLabel,c);

		Object[] choice = new Object[] { "Position"}; 
		listChoice = new JComboBox(choice);
		c.gridx = 1;
		firstPanel.add(listChoice,c);
		c.gridx = 2;
		table = new JButton("table");
		table.addActionListener(this);


		table.setPreferredSize(new Dimension(100,25));
		firstPanel.add(table,c);	
		System.out.println("insertion bouton fait");

		c.gridx = 3;
		graphic = new JButton("graphic");
		graphic.addActionListener(this);
		graphic.setPreferredSize(new Dimension(100,25));
		firstPanel.add(graphic,c);	
		onglets.addTab("INDICATEUR CAPTEUR POLLUANT",firstPanel);


		// PANE STATION TRAM
		secondPanel.setLayout(a);
		JLabel secondLabel = new JLabel("Indicateur concernant le nombre de stations du tram");
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 10;
		c.insets = new Insets(30,30,100,30);
		secondPanel.add(secondLabel,c);

		Object[] choiceStation = new Object[] { "Position"}; 
		listChoiceStation = new JComboBox(choiceStation);
		c.gridx = 1;
		secondPanel.add(listChoiceStation,c);

		c.gridx = 2;
		tableStation = new JButton("table");
		tableStation.addActionListener(this);
		tableStation.setPreferredSize(new Dimension(100,25));
		secondPanel.add(tableStation,c);	

		c.gridx = 3;
		graphicStation = new JButton("graphic");
		graphicStation.addActionListener(this);
		graphicStation.setPreferredSize(new Dimension(100,25));
		secondPanel.add(graphicStation,c);


		secondPanel.setPreferredSize(new Dimension(400,600));
		onglets.addTab("INDICATEUR STATION",secondPanel);

		// PANE BORNE : 

		thirdPanel.setLayout(a);
		JLabel thirdLabel = new JLabel("Indicateur concernant le nombre de bornes dans la ville ");
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 10;
		c.insets = new Insets(30,30,100,30);
		thirdPanel.add(thirdLabel,c);
		
		// combox box : filter the number of terminals according to the position
		Object[] choixBorne = new Object[] {"Position"}; 
		listChoiceBorne = new JComboBox(choixBorne);
		c.gridx = 1;
		thirdPanel.add(listChoiceBorne,c);

		c.gridx = 2;
		tableBorne = new JButton("table");
		tableBorne.addActionListener(this);
		tableBorne.setPreferredSize(new Dimension(100,25));
		thirdPanel.add(tableBorne,c);	

		c.gridx = 3;
		graphicBorne = new JButton("graphic");
		graphicBorne.addActionListener(this);
		graphicBorne.setPreferredSize(new Dimension(100,25));
		thirdPanel.add(graphicBorne,c);

		thirdPanel.setPreferredSize(new Dimension(400,600));
		onglets.addTab("INDICATEUR BORNES",thirdPanel);


		// PANNEAU : recovery of exceedance rates / number of warning triggered by each sensor and by wchich polluant
		//fourthPanel.setLayout(a);
		JLabel fourthLabel = new JLabel("Indicateur concernant le détail de chaque alerte");
		c.gridx = 0;
		fourthPanel.add(fourthLabel, c);
		c.gridy = 1;
		// select the sensor that triggered an warning
		Integer[] choiceCapteur = selectCapteur(); 
		listChoiceCapteur = new JComboBox(choiceCapteur);
		c.gridx = 0;
		fourthPanel.add(listChoiceCapteur,c);

		// select one of the polluant 
		Object[] choicePolluant = new Object[] { "CO2", "NO2", "PF", "TMP"}; 
		listChoicePolluant = new JComboBox(choicePolluant);
		c.gridx = 1;
		fourthPanel.add(listChoicePolluant,c);

		// select  the period of recevery of an warning
		//DATE START
		c.gridy = 2;
		c.gridx = 0;
		JLabel dayStartLabel = new JLabel("Jour début");
		fourthPanel.add(dayStartLabel,c);
		c.gridx = 1;
		Integer[] choiceDayStart = new Integer[31];
		for(int i=1;i<=31;i++) {
			choiceDayStart[i-1]=i;
		}
		dayDateStart = new JComboBox(choiceDayStart);
		fourthPanel.add(dayDateStart,c);
		c.gridx = 2;
		JLabel monthStartLabel = new JLabel("Mois début");
		fourthPanel.add(monthStartLabel,c);
		c.gridx = 3;
		Integer[] choiceMonthStart = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
		monthDateStart = new JComboBox(choiceMonthStart);
		fourthPanel.add(monthDateStart,c);
		c.gridx = 4;
		JLabel yearStartLabel = new JLabel("Année début");
		fourthPanel.add(yearStartLabel,c);
		c.gridx = 5;
		Integer[] choiceYearStart = new Integer[100];
		for(int i=2000;i<2100;i++) {
			choiceYearStart[i-2000]=i;
		}		
		yearDateDebut = new JComboBox(choiceYearStart);
		fourthPanel.add(yearDateDebut,c);
		c.gridy = 3;
		c.gridx = 0;

		// FIN DATE START

		// DATE END
		JLabel dayEndLabel = new JLabel("Jour fin");
		fourthPanel.add(dayEndLabel,c);
		c.gridx = 1;
		Integer[] choiceDayEnd = new Integer[31];
		for(int i=1;i<=31;i++) {
			choiceDayEnd[i-1]=i;
		}
		dayDateEnd = new JComboBox(choiceDayEnd);
		fourthPanel.add(dayDateEnd,c);
		c.gridx = 2;
		JLabel monthEndLabel = new JLabel("Mois fin");
		fourthPanel.add(monthEndLabel,c);
		c.gridx = 3;
		Integer[] choiceMonthEnd = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
		monthDateEnd = new JComboBox(choiceMonthEnd);
		fourthPanel.add(monthDateEnd,c);
		c.gridx = 4;
		JLabel yearEndLabel = new JLabel("Année fin");
		fourthPanel.add(yearEndLabel,c);
		c.gridx = 5;
		Integer[] choiceYearEnd = new Integer[100];
		for(int i=2000;i<2100;i++) {
			choiceYearEnd[i-2000]=i;
		}		
		yearDateEnd = new JComboBox(choiceYearEnd);
		fourthPanel.add(yearDateEnd,c);

		// FIN DATE END
		c.gridy = 4;		
		c.gridx = 2;
		validate = new JButton("validate");
		validate.addActionListener(this);
		validate.setPreferredSize(new Dimension(100,25));
		fourthPanel.add(validate,c);

		fourthPanel.setPreferredSize(new Dimension(400,600));
		onglets.addTab("INDICATEUR  DETAIL ALERTE",fourthPanel);


		// PANE NB CAR: 

		//fifthPanel.setLayout(a);
		JLabel fifthLabel = new JLabel("Indicateur concernant le nombre de voitures selon la date");
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 10;
		c.insets = new Insets(30,30,100,30);
		fifthPanel.add(fifthLabel,c);
		c.gridy = 1;
		c.gridx = 0;
		
		// the same thing for date like the pane of threhold
		JLabel dayStartLabel5 = new JLabel("Jour début");
		fifthPanel.add(dayStartLabel5,c);
		c.gridx = 1;
		Integer[] choiceDayStart5 = new Integer[31];
		for(int i=1;i<=31;i++) {
			choiceDayStart5[i-1]=i;
		}
		dayDateStart5 = new JComboBox(choiceDayStart5);
		fifthPanel.add(dayDateStart5,c);
		c.gridx = 2;
		JLabel monthStartLabel5 = new JLabel("Mois début");
		fifthPanel.add(monthStartLabel5,c);
		c.gridx = 3;
		Integer[] choiceMonthStart5 = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
		monthDateStart5 = new JComboBox(choiceMonthStart5);
		fifthPanel.add(monthDateStart5,c);
		c.gridx = 4;
		JLabel yearStartLabel5 = new JLabel("Année début");
		fifthPanel.add(yearStartLabel5,c);
		c.gridx = 5;
		Integer[] choiceYearStart5 = new Integer[100];
		for(int i=2000;i<2100;i++) {
			choiceYearStart5[i-2000]=i;
		}		
		yearDateStart5 = new JComboBox(choiceYearStart5);
		fifthPanel.add(yearDateStart5,c);
		c.gridy = 2;
		c.gridx = 0;
		JLabel dayEndLabel5 = new JLabel("Jour fin");
		fifthPanel.add(dayEndLabel5,c);
		c.gridx = 1;
		Integer[] choiceDayEnd5 = new Integer[31];
		for(int i=1;i<=31;i++) {
			choiceDayEnd5[i-1]=i;
		}
		dayDateEnd5 = new JComboBox(choiceDayEnd5);
		fifthPanel.add(dayDateEnd5,c);
		c.gridx = 2;
		JLabel monthEndLabel5 = new JLabel("Mois fin");
		fifthPanel.add(monthEndLabel5,c);
		c.gridx = 3;
		Integer[] choiceMonthEnd5 = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
		monthDateEnd5 = new JComboBox(choiceMonthEnd5);
		fifthPanel.add(monthDateEnd5,c);
		c.gridx = 4;
		JLabel yearEndLabel5 = new JLabel("Année fin");
		fifthPanel.add(yearEndLabel5,c);
		c.gridx = 5;
		Integer[] choiceYearEnd5 = new Integer[100];
		for(int i=2000;i<2100;i++) {
			choiceYearEnd5[i-2000]=i;
		}		
		yearDateEnd5 = new JComboBox(choiceYearEnd5);
		fifthPanel.add(yearDateEnd5,c);

		c.gridy = 3;		
		c.gridx = 2;
		validate5 = new JButton("validate");
		validate5.addActionListener(this);
		validate5.setPreferredSize(new Dimension(100,25));
		fifthPanel.add(validate5,c);
		fifthPanel.setPreferredSize(new Dimension(400,600));
		onglets.addTab("INDICATEUR NB VOITURE",fifthPanel);


		// PANE NUMBER OF SENSOR CAR 

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
		tableSensorCar = new JButton("table");
		tableSensorCar.addActionListener(this);
		tableSensorCar.setPreferredSize(new Dimension(100,25));
		sixthPanel.add(tableSensorCar,c);	

		c.gridx = 3;
		graphicSensorCar = new JButton("graphic");
		graphicSensorCar.addActionListener(this);
		graphicSensorCar.setPreferredSize(new Dimension(100,25));
		sixthPanel.add(graphicSensorCar,c);


		sixthPanel.setPreferredSize(new Dimension(400,600));
		onglets.addTab("INDICATEUR NB CAPTEUR VEHICULE",sixthPanel);



		onglets.setOpaque(true);
		this.add(onglets);

	}


	// recovery of id sensor for each sensor who triggered a warning 
	private Integer[] selectCapteur() {
		TestJson t = new TestJson();
		ArrayList<Integer> liste = new ArrayList<Integer>();
		Integer[] table = null;
		try {
			liste = t.getIdSensorPolluant();
			table = new Integer[liste.size()];
			for(int i =0;i<liste.size();i++) {
				table[i]=liste.get(i);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
		return table;
	}

// the event that happens with each click on the buttons
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(table)) { 
			System.out.println("Bonjour"); 
			TestJson t = new TestJson(); 
			try {
				ArrayList<SensorIndicator> liste = t.goSensor();
				if(result!=null) {
					result.removeAll();
				}
				result = new PanneauResultSensor("table", listChoice.getSelectedItem().toString(),liste);
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

		} else if (e.getSource().equals(graphic)) {
			System.out.println("Bonjour graphic capteurs "); 
			TestJson t = new TestJson(); 
			try {
				ArrayList<SensorIndicator> liste = t.goSensor();
				if(result!=null) {
					result.removeAll();
				}
				result = new PanneauResultSensor("graphic", listChoice.getSelectedItem().toString(),liste);
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
		} else if (e.getSource().equals(tableStation)) {
			System.out.println("Bonjour  table Station"); 
			TestJson t = new TestJson(); 
			try {
				ArrayList<StationIndicator> liste2 = t.goStation();
				if(result2!= null) {
					result2.removeAll();
				}
				result2 = new PanneauResultStation("tableStation", listChoiceStation.getSelectedItem().toString(),liste2);
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
		} else if (e.getSource().equals(graphicStation)) {
			System.out.println("Bonjour graphic Station "); 
			TestJson t = new TestJson(); 
			try {
				ArrayList<StationIndicator> liste2 = t.goStation();
				if(result2!=null) {
					result2.removeAll();
				}
				result2 = new PanneauResultStation("graphicStation", listChoiceStation.getSelectedItem().toString(),liste2);
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

		} else if (e.getSource().equals(tableBorne)) {
			System.out.println("Bonjour  table Borne"); 
			TestJson t = new TestJson(); 
			try {
				ArrayList<SensorIndicator> liste2 = t.goBorne();
				if(result4!= null) {
					result4.removeAll();
				}
				result4 = new PanneauResultBorne("tableBorne", listChoiceBorne.getSelectedItem().toString(),liste2);
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
		} else if (e.getSource().equals(graphicBorne)) {
			System.out.println("Bonjour graphic Borne "); 
			TestJson t = new TestJson(); 
			try {
				ArrayList<SensorIndicator> liste2 = t.goBorne();
				if(result4!=null) {
					result4.removeAll();
				}
				result4 = new PanneauResultBorne("graphicBorne", listChoiceBorne.getSelectedItem().toString(),liste2);
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

		} else if(e.getSource().equals(tableSensorCar)) { 
			System.out.println("Bonjour"); 
			TestJson t = new TestJson(); 
			try {
				ArrayList<SensorIndicator> liste = t.goSensorCar();
				if(result6!=null) {
					result6.removeAll();
				}
				result6 = new PanneauResultSensorCar ("tableSensorCar", listeChoiceSensorCar.getSelectedItem().toString(),liste);
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

		} else if (e.getSource().equals(graphicSensorCar)) {
			System.out.println("Bonjour graphic capteur vehicule "); 
			TestJson t = new TestJson(); 
			try {
				ArrayList<SensorIndicator> liste = t.goSensorCar();
				if(result6!=null) {
					result6.removeAll();
				}
				result6 = new PanneauResultSensorCar ("graphicSensorCar", listeChoiceSensorCar.getSelectedItem().toString(),liste);
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

		else if (e.getSource().equals(validate)) {
			System.out.println("Bonjour Capteur polluant"); 
			TestJson t = new TestJson(); 
			try {
				List<Integer> listeSeuil = t.getThreshold(listChoicePolluant.getSelectedItem().toString(),Integer.valueOf(listChoiceCapteur.getSelectedItem().toString()));
				ArrayList<SensorPolluantIndicator> listeWarning = t.getWarning(listChoicePolluant.getSelectedItem().toString(), Integer.valueOf(listChoiceCapteur.getSelectedItem().toString()),listeSeuil);
				if(result3!= null) {
					result3.removeAll();
				}
				int year1 = Integer.valueOf((String) yearDateDebut.getSelectedItem().toString());
				int month1 = Integer.valueOf((String) monthDateStart.getSelectedItem().toString());
				int day1 = Integer.valueOf((String) dayDateStart.getSelectedItem().toString());
				System.out.println("debut"+year1+"|"+month1+"|"+day1);
				int year2 = Integer.valueOf((String) yearDateEnd.getSelectedItem().toString());
				int month2 = Integer.valueOf((String) monthDateEnd.getSelectedItem().toString());
				int day2 = Integer.valueOf((String) dayDateEnd.getSelectedItem().toString());
				System.out.println("fin"+year2+"|"+month2+"|"+day2);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date date = sdf.parse(day1+"/"+month1+"/"+year1);
				System.out.println(date.toString());
				Timestamp dateDebut = new Timestamp(date.getTime());				
				date = sdf.parse(day2+"/"+month2+"/"+year2);
				Timestamp dateFin = new Timestamp(date.getTime());
				result3 = new PanneauResultWarning(dateDebut,dateFin,listChoicePolluant.getSelectedItem().toString(),listeSeuil,listeWarning);
				c.gridx = 1;
				c.gridy = 1;
				fourthPanel.add(result3,c);
				result3.setVisible(true);
				fourthPanel.revalidate();			

			} catch (IOException | ParseException e1) {

				e1.printStackTrace();


			}
		}  else if (e.getSource().equals(validate5)) {
			System.out.println("Bonjour Voiture"); 
			TestJson t = new TestJson(); 
			try {
				ArrayList<CarIndicator> liste = t.goCar();
				if(result5!= null) {
					result5.removeAll();
				}
				int year1 = Integer.valueOf((String) yearDateStart5.getSelectedItem().toString());
				int month1 = Integer.valueOf((String) monthDateStart5.getSelectedItem().toString());
				int day1 = Integer.valueOf((String) dayDateStart5.getSelectedItem().toString());
				System.out.println("debut"+year1+"|"+month1+"|"+day1);
				int year2 = Integer.valueOf((String) yearDateEnd5.getSelectedItem().toString());
				int month2 = Integer.valueOf((String) monthDateEnd5.getSelectedItem().toString());
				int day2 = Integer.valueOf((String) dayDateEnd5.getSelectedItem().toString());
				System.out.println("fin"+year2+"|"+month2+"|"+day2);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date date = sdf.parse(day1+"/"+month1+"/"+year1);
				System.out.println(date.toString());
				Timestamp dateDebut = new Timestamp(date.getTime());				
				date = sdf.parse(day2+"/"+month2+"/"+year2);
				Timestamp dateFin = new Timestamp(date.getTime());
				result5 = new PanneauResultCar(dateDebut,dateFin,liste);
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


