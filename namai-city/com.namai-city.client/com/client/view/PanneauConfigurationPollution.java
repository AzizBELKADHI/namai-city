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
	private JTextField jtCapteurType;
	private JTextField jtLocalisation;
	private JButton submit;
	
	public PanneauConfigurationPollution() {
		//this.setBackground(Couleur.getBgThem());
		this.setForeground(Couleur.getBgApp());
		this.setFont(new Font("Arial", Font.BOLD, 14) );
		this.setBorder(new LineBorder(Couleur.getBgTitle()));
		l1 = new JLabel("TypeCapteur");
		l2 = new JLabel("Localisation");
		jtCapteurType = new JTextField();
		jtLocalisation = new JTextField();
		submit = new JButton("SUBMIT");
		GridBagLayout a  = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		this.setLayout(a);
		
		c.gridx = 0;
		c.gridy = 0;
		this.add(l1,c);
		
		c.gridx = 1;
		c.gridy = 0;
		this.add(l2,c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.3;
		jtCapteurType.setPreferredSize(new Dimension(150,25));
		this.add(jtCapteurType,c);
		
		
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.3;
		jtLocalisation.setPreferredSize(new Dimension(150,25));
		this.add(jtLocalisation,c);
		
		
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 0.2;
		c.insets = new Insets(30,30,30,30);
		submit.setPreferredSize(new Dimension(100,25));
		this.add(submit,c);	
		System.out.println("cool");
	}
	public JTextField getJtCapteurType() {
		return jtCapteurType;
	}
	public JTextField getJtLocalisation() {
		return jtLocalisation;
	}
	public JButton getSubmit() {
		return submit;
	}

}
