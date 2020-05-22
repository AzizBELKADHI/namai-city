package com.client.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class PanneauConfigurationPollution extends JPanel {
	
	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	private JLabel m1;
	private JLabel m2;
	private JLabel m3;
	private JLabel a1;
	private JLabel a2;
	private JLabel a3;
	private JLabel fre;
	private JTextField jtLocalisation;
	private JTextField jtAdresseIp;
	private JTextField jtSeuil_CO2;
	private JTextField jtSeuil_NO2;
	private JTextField jtSeuil_PM;
	private JTextField jtSeuil_Min_Tmp;
	private JTextField jtSeuil_Max_Tmp;
	private JTextField jFrequence ;
	private JButton submit;
	private JButton list;
	public PanneauConfigurationPollution() {
		//this.setBackground(Couleur.getBgThem());
		this.setForeground(Couleur.getBgApp());
		this.setFont(new Font("Arial", Font.BOLD, 14) );
		this.setBorder(new LineBorder(Couleur.getBgTitle()));
		l1 = new JLabel("Seuil_Min_Tmp");
		l2 = new JLabel("Localisation");
		l3 = new JLabel("Adresse_Ip");
		m1 = new JLabel("Seuil_CO2");
		m2 = new JLabel("Seuil_Max_Tmp");
		m3 = new JLabel("Seuil_NO2");
		a2 = new JLabel("Seuil_PM");
		fre = new JLabel("Frequence");
		
		jtSeuil_Min_Tmp = new JTextField();
		jtLocalisation = new JTextField();
		jtAdresseIp = new JTextField();
		jtSeuil_CO2 = new JTextField();
		jtSeuil_NO2 = new JTextField();
		jtSeuil_PM = new JTextField();
		jtSeuil_Max_Tmp = new JTextField();
		jFrequence = new  JTextField();
		
		submit = new JButton("SUBMIT"); 
		list = new JButton("ListCapteur");
		
		GridBagLayout a  = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		this.setLayout(a);
		
		c.gridx = 0;
		c.gridy = 0;
		this.add(l3,c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.5;
		jtAdresseIp.setPreferredSize(new Dimension(150,25));
		this.add(jtAdresseIp,c);
		
		c.gridx = 1;
		c.gridy = 0;
		this.add(l2,c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.5;
		jtLocalisation.setPreferredSize(new Dimension(150,25));
		this.add(jtLocalisation,c);
		
		
		c.gridx = 0;
		c.gridy = 2;
		this.add(m1,c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0.2;
		jtSeuil_CO2.setPreferredSize(new Dimension(150,25));
		this.add(jtSeuil_CO2,c);
		
		c.gridx = 1;
		c.gridy = 2;
		this.add(m3,c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 0.2;
		jtSeuil_NO2.setPreferredSize(new Dimension(150,25));
		this.add(jtSeuil_NO2,c);
		
		c.gridx = 2;
		c.gridy = 2;
		this.add(a2,c);
		
		c.gridx = 2;
		c.gridy = 3;
		c.weightx = 0.5;
		jtSeuil_PM.setPreferredSize(new Dimension(150,25));
		this.add(jtSeuil_PM,c);
		
		c.gridx = 0;
		c.gridy = 4;
		this.add(l1,c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.weightx = 0.5;
		jtSeuil_Min_Tmp.setPreferredSize(new Dimension(150,25));
		this.add(jtSeuil_Min_Tmp,c);
		
		c.gridx = 1;
		c.gridy = 4;
		this.add(m2,c);
		
		c.gridx = 1;
		c.gridy = 5;
		c.weightx = 0.5;
		jtSeuil_Max_Tmp.setPreferredSize(new Dimension(150,25));
		this.add(jtSeuil_Max_Tmp,c);
		
		
		c.gridx = 2;
		c.gridy = 4;
		c.weightx = 0.5;
		c.insets = new Insets(25,25,25,25);
		fre.setPreferredSize(new Dimension(100,25));
		this.add(fre,c);
		c.gridx =2;
		c.gridy = 5;
		c.weightx = 0.5;
		c.insets = new Insets(25,25,25,25);
		jFrequence.setPreferredSize(new Dimension(100,25));
		this.add(jFrequence,c);	
		
		/*
		 * c.gridx =3; c.gridy = 4; c.weightx = 0.5; c.insets = new Insets(25,25,25,25);
		 * list.setPreferredSize(new Dimension(100,25)); this.add(list,c)
		 */;
		
		c.gridx = 3;
		c.gridy = 2;
		c.weightx = 0.3;
		this.add(list,c);
		
		c.gridx =3;
		c.gridy = 3;
		c.weightx = 0.5;
		c.insets = new Insets(25,25,25,25);
		submit.setPreferredSize(new Dimension(100,25));
		this.add(submit,c);	
		
		
		
		
		System.out.println("cool");
		
		
	}
	public JLabel getL1() {
		return l1;
	}
	public JLabel getL2() {
		return l2;
	}
	public JLabel getL3() {
		return l3;
	}
	public JLabel getM1() {
		return m1;
	}
	public JLabel getM2() {
		return m2;
	}
	public JLabel getM3() {
		return m3;
	}
	public JLabel getA1() {
		return a1;
	}
	public JLabel getA2() {
		return a2;
	}
	public JLabel getA3() {
		return a3;
	}
	public JTextField getJtLocalisation() {
		return jtLocalisation;
	}
	public JTextField getJtAdresseIp() {
		return jtAdresseIp;
	}
	public JTextField getJtSeuil_CO2() {
		return jtSeuil_CO2;
	}
	public JTextField getJtSeuil_NO2() {
		return jtSeuil_NO2;
	}
	public JTextField getJtSeuil_PM() {
		return jtSeuil_PM;
	}
	public JTextField getJtSeuil_Min_Tmp() {
		return jtSeuil_Min_Tmp;
	}
	public JTextField getJtSeuil_Max_Tmp() {
		return jtSeuil_Max_Tmp;
	}
	public JButton getSubmit() {
		return submit;
	}
	public JButton getList() {
		return list;
	}
	public void setList(JButton list) {
		this.list = list;
	}
	public JTextField getjFrequence() {
		return jFrequence;
	}
	public void setjFrequence(JTextField jFrequence) {
		this.jFrequence = jFrequence;
	}
	
}

