package com.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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
	private JComboBox listeChoix; 
	private JComboBox listeChoixStation; 
	private JComboBox listeChoixCapteur; 
	private JComboBox listeChoixPolluant; 
	


	JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
	JPanel firstPanel = new JPanel();
	JPanel secondPanel = new JPanel();
	JPanel fourthPanel = new JPanel();
	
	PanneauResultatSensor result;
	PanneauResultatStation result2;
	PanneauResultatWarning result3;
	
	GridBagLayout a  = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	public PanneauIndicateur()  {
		this.setForeground(Couleur.getBgApp());
		this.setFont(new Font("Arial", Font.BOLD, 14) );
		this.setBorder(new LineBorder(Couleur.getBgTitle()));
		this.setPreferredSize(new Dimension(600,800));

		firstPanel.setLayout(a);
		JLabel firstLabel = new JLabel("Indicateur concernant les capteurs");
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 10;
		c.insets = new Insets(30,30,100,30);
		firstPanel.add(firstLabel,c);

		Object[] choix = new Object[] { "Position", "Type", "Date"}; 
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



		//firstPanel.setSize(this.getParent().getSize());
		onglets.addTab("INDICATEUR CAPTEUR",firstPanel);

		// PANNEAU STATION 
		secondPanel.setLayout(a);
		JLabel secondLabel = new JLabel("Indicateur concernant le tram");
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 10;
		c.insets = new Insets(30,30,100,30);
		secondPanel.add(secondLabel,c);

		Object[] choixStation = new Object[] { "Position", "Date"}; 
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

		
		secondPanel.setLayout(a);
		JLabel secondLabel2 = new JLabel("Nombre de personnes dans la ville : ");
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 10;
		c.insets = new Insets(30,30,100,30);
		secondPanel.add(secondLabel2,c);
		

		secondPanel.setPreferredSize(new Dimension(400,600));
		onglets.addTab("INDICATEUR TRAM",secondPanel);

		JPanel thirdPanel = new JPanel();
		JLabel thirdLabel = new JLabel("Indicateur concernant les voitures");
		thirdPanel.add(thirdLabel);
		thirdPanel.setPreferredSize(new Dimension(400,600));
		onglets.addTab("INDICATEUR VOITURE",thirdPanel);


		
		JLabel fourthLabel = new JLabel("Indicateur concernant les alertes");

		fourthPanel.add(fourthLabel);

		Integer[] choixCapteur = selectCapteur(); 
		listeChoixCapteur = new JComboBox(choixCapteur);
		c.gridx = 1;
		fourthPanel.add(listeChoixCapteur,c);

		Object[] choixPolluant = new Object[] { "CO2", "NO2", "PF", "TMP"}; 
		listeChoixPolluant = new JComboBox(choixPolluant);
		c.gridx = 2;
		fourthPanel.add(listeChoixPolluant,c);

		c.gridx = 3;
		valider = new JButton("valider");
		valider.addActionListener(this);
		valider.setPreferredSize(new Dimension(100,25));
		fourthPanel.add(valider,c);

		fourthPanel.setPreferredSize(new Dimension(400,600));
		onglets.addTab("INDICATEUR ALERTE",fourthPanel);

		onglets.setOpaque(true);
		this.add(onglets);

		/*
			secondPanel.add(secondLabel); 
			thirdPanel.add(thirdLabel); 
			fourthPanel.add(fourthLabel); 


			tabbedPane.add("INDICATEUR VOITURE",secondPanel); 
			tabbedPane.add("INDICATEUR TRAM", thirdPanel); 
			tabbedPane.add("INDICATEUR ALERTE", fourthPanel); 
			add(tabbedPane);

			JPanel secondPanel = new JPanel(); 
			JPanel thirdPanel = new JPanel(); 
			JPanel fourthPanel = new JPanel(); 


			JLabel secondLabel = new JLabel("Indicateur concernant les voitures");
			JLabel thirdLabel = new JLabel("Indicateur concernant le tram"); 
			JLabel fourthLabel = new JLabel("Indicateur concernant les alertes");
		 */

	}


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
				c.gridx = 1;
				c.gridy = 1;
				secondPanel.add(result2,c);
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
				c.gridx = 1;
				c.gridy = 1;
				secondPanel.add(result2,c);
				result2.setVisible(true);
				secondPanel.revalidate();			

			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {

				e1.printStackTrace();

			}

			
		} else if (e.getSource().equals(valider)) {
			System.out.println("Bonjour Capteur"); 
			TestJson t = new TestJson(); 
			try {
				List<Integer> listeSeuil = t.getThreshold(listeChoixPolluant.getSelectedItem().toString(),Integer.valueOf(listeChoixCapteur.getSelectedItem().toString()));
				ArrayList<SensorPolluantIndicator> listeWarning = t.getWarning(listeChoixPolluant.getSelectedItem().toString(), Integer.valueOf(listeChoixCapteur.getSelectedItem().toString()),listeSeuil);
				if(result3!= null) {
					result3.removeAll();
				}
				result3 = new PanneauResultatWarning(listeChoixPolluant.getSelectedItem().toString(),listeSeuil,listeWarning);
				c.gridx = 1;
				c.gridy = 1;
				fourthPanel.add(result3,c);
				result3.setVisible(true);
				fourthPanel.revalidate();			

			} catch (IOException e1) {

				e1.printStackTrace();


			}
		}
		
	}
}


