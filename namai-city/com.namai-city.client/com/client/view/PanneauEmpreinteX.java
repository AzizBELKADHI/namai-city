package com.client.view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.Color;

public class PanneauEmpreinteX extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_17;
	private JTextField textField_16;
	private JLabel lblNewLabel_13;
	private JLabel lblNewLabel_14;
	private JLabel lblNewLabel_10;
	private JTextField textField_18;
	private JLabel lblNewLabel_8_1_2;


	/**
	 * Create the panel.
	 */
	public PanneauEmpreinteX() {
		setBackground(new Color(60, 179, 113));
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(334, 240, 59, 19);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(196, 240, 59, 19);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(265, 240, 59, 19);
		add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(127, 240, 59, 19);
		add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(334, 211, 59, 19);
		add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(265, 211, 59, 19);
		add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(196, 211, 59, 19);
		add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(127, 211, 59, 19);
		add(textField_7);
		
		JButton btnNewButton = new JButton("Simuler");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String RSVoiture;
				String RSVelo;
				String RSMarche;
				
				String distanceTram = textField_7.getText();
				float i = Float.parseFloat(distanceTram);
				float j = i*3;
				String usersTram = textField_3.getText();
				float k = Float.parseFloat(usersTram);
				float finalTram = (j/k);
				
				String distanceVoiture = textField_6.getText();
				float i2 = Float.parseFloat(distanceVoiture);
				String usersVoiture = textField_1.getText();
				float k2 = Float.parseFloat(usersVoiture);
				float finalVoiture = 166*i2*k2;
				
				String distanceVelo = textField_5.getText();
				float i3 = Float.parseFloat(distanceVelo);
				String usersVelo = textField_2.getText();
				float k3 = Float.parseFloat(usersVelo);
				float finalVelo = 15*i3*k3;
				
				String distanceMarche = textField_4.getText();
				float i4 = Float.parseFloat(distanceMarche);
				String usersMarche = textField.getText();
				float k4 = Float.parseFloat(usersMarche);
				float finalMarche = 15*i4*k4;
				
				float FINAL = finalTram+finalVoiture+finalVelo+finalMarche;
				textField_16.setText(FINAL+"");
				
				float FINALKG = FINAL/1000;
				textField_18.setText(FINALKG+"");
			}
		});
		btnNewButton.setBounds(127, 269, 85, 21);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Calculer");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(127, 117, 85, 21);
		add(btnNewButton_1);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(127, 88, 59, 19);
		add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(196, 88, 59, 19);
		add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(265, 88, 59, 19);
		add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(334, 88, 59, 19);
		add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(334, 59, 59, 19);
		add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(265, 59, 59, 19);
		add(textField_13);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(196, 59, 59, 19);
		add(textField_14);
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(127, 59, 59, 19);
		add(textField_15);
		
		textField_17 = new JTextField();
		textField_17.setColumns(10);
		textField_17.setBounds(334, 118, 59, 19);
		add(textField_17);
		
		JLabel lblNewLabel = new JLabel("Tram");
		lblNewLabel.setBounds(127, 188, 46, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Voiture");
		lblNewLabel_1.setBounds(196, 188, 46, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Vélo");
		lblNewLabel_2.setBounds(265, 188, 46, 13);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Marche");
		lblNewLabel_3.setBounds(334, 188, 46, 13);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Distance moyenne");
		lblNewLabel_4.setBounds(10, 214, 107, 13);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Nombre utilisateurs");
		lblNewLabel_5.setBounds(10, 243, 118, 13);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Tram");
		lblNewLabel_6.setBounds(127, 36, 46, 13);
		add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Voiture");
		lblNewLabel_7.setBounds(196, 36, 46, 13);
		add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Vélo");
		lblNewLabel_8.setBounds(265, 36, 46, 13);
		add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Marche");
		lblNewLabel_9.setBounds(334, 36, 46, 13);
		add(lblNewLabel_9);
		
		JLabel lblNewLabel_8_1 = new JLabel("Résultat (g de Co2)");
		lblNewLabel_8_1.setBounds(222, 121, 112, 13);
		add(lblNewLabel_8_1);
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		textField_16.setBounds(334, 269, 59, 19);
		add(textField_16);
		
		JLabel lblNewLabel_8_1_1 = new JLabel("Résultat (g de Co2)");
		lblNewLabel_8_1_1.setBounds(222, 273, 112, 13);
		add(lblNewLabel_8_1_1);
		
		JLabel lblNewLabel_12 = new JLabel("Simulation");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_12.setBounds(206, 158, 118, 20);
		add(lblNewLabel_12);
		
		lblNewLabel_13 = new JLabel("Données Réelles");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_13.setBounds(196, 6, 132, 20);
		add(lblNewLabel_13);
		
		lblNewLabel_14 = new JLabel("Distance moyenne");
		lblNewLabel_14.setBackground(Color.WHITE);
		lblNewLabel_14.setBounds(10, 62, 107, 13);
		add(lblNewLabel_14);
		
		lblNewLabel_10 = new JLabel("Nombre utilisateurs");
		lblNewLabel_10.setBounds(10, 91, 118, 13);
		add(lblNewLabel_10);
		
		textField_18 = new JTextField();
		textField_18.setColumns(10);
		textField_18.setBounds(334, 302, 59, 19);
		add(textField_18);
		
		lblNewLabel_8_1_2 = new JLabel("Résultat (Kg de Co2)");
		lblNewLabel_8_1_2.setBounds(222, 305, 138, 13);
		add(lblNewLabel_8_1_2);
		

		
		

	}
}
