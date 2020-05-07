package com.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;

import com.client.controller.SocketClient;
import com.commons.model.AccessServer;

import indicator.Sensor;


public class PanneauIndicateur extends JFrame{
	
	JPanel firstPanel = new JPanel(); 
	JPanel secondPanel = new JPanel(); 
	JPanel thirdPanel = new JPanel(); 
	JPanel fourthPanel = new JPanel(); 
	
	JLabel firstLabel = new JLabel("Indicateur concernant les capteurs"); 
	JLabel secondLabel = new JLabel("Indicateur concernant les voitures");
	JLabel thirdLabel = new JLabel("Indicateur concernant le tram"); 
	JLabel fourthLabel = new JLabel("Indicateur concernant les alertes"); 
	
	//JTable tableau = new JTable(listSensors); 
	JTabbedPane tabbedPane = new JTabbedPane(); 
	public PanneauIndicateur()  {
			this.setBackground(Color.blue);
			// TODO Auto-generated constructor stub
			firstPanel.add(firstLabel); 
			secondPanel.add(secondLabel); 
			thirdPanel.add(thirdLabel); 
			fourthPanel.add(fourthLabel); 
			
			tabbedPane.add("INDICATEUR CAPTEUR",firstPanel);
			tabbedPane.add("INDICATEUR VOITURE",secondPanel); 
			tabbedPane.add("INDICATEUR TRAM", thirdPanel); 
			tabbedPane.add("INDICATEUR ALERTE", fourthPanel); 
			add(tabbedPane); 
		}
	
	public static void main(String[] args) {
		PanneauIndicateur tp = new PanneauIndicateur(); 
		tp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tp.setSize(600,400); 
		tp.setVisible(true);
	
		
		
	}

}
