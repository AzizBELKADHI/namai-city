package com.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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


public class PanneauIndicateur extends JPanel implements ActionListener{
	private JButton tableau;
	private JButton graphique;
	private JComboBox listeChoix; 
	
	//JTable tableau = new JTable(listSensors); 
	JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
	JPanel firstPanel = new JPanel();
	PanneauResultatSensor result;
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
			
			JPanel secondPanel = new JPanel();
			JLabel secondLabel = new JLabel("Indicateur concernant les voitures");
			secondPanel.add(secondLabel);
			secondPanel.setPreferredSize(new Dimension(400,600));
			onglets.addTab("INDICATEUR VOITURE",secondPanel);
				
			JPanel thirdPanel = new JPanel();
			JLabel thirdLabel = new JLabel("Indicateur concernant le tram");
			thirdPanel.add(thirdLabel);
			thirdPanel.setPreferredSize(new Dimension(400,600));
			onglets.addTab("INDICATEUR TRAM",thirdPanel);
				
				
			JPanel fourthPanel = new JPanel();
			JLabel fourthLabel = new JLabel("Indicateur concernant les alertes");
			fourthPanel.add(fourthLabel);
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
			System.out.println("Bonjour"); 
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
		}
	}
	/*
	public static void main(String[] args) {
		PanneauIndicateur tp = new PanneauIndicateur(); 
		//tp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tp.setSize(600,400); 
		tp.setVisible(true);
	
		
	
	}
	*/
	

}
