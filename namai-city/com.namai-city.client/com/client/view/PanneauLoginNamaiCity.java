package com.client.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class PanneauLoginNamaiCity extends JPanel {
	private JButton login;
	private JTextField user;
	private JPasswordField mp;
	private JLabel username;
	private JLabel password;
	//private JPanel form;
	
	public PanneauLoginNamaiCity() {
		super();
		this.setForeground(Couleur.getBgApp());
		this.setFont(new Font("Arial", Font.BOLD, 14) );
		this.setBorder(new LineBorder(Couleur.getBgTitle()));
		GridBagLayout b = new GridBagLayout();
		GridBagConstraints x = new GridBagConstraints();
		this.setLayout(b);
		//form = new JPanel();
		login = new JButton("Submit");
		user = new JTextField();
		mp = new JPasswordField();
		username = new JLabel("Username");
		password = new JLabel("Password");
		//form.add(username);
		//form.add(user);
		//form.add(password);
		//form.add(mp);
		//form.add(login);
		//form.setPreferredSize(new Dimension(500,500));	
		
		x.gridx = 0;
		x.gridy = 0;
		this.add(username,x);
		
		x.gridx = 0;
		x.gridy = 1;
		x.weightx = 0.5;
		user.setPreferredSize(new Dimension(150,25));
		this.add(user,x);
		
		x.gridx = 0;
		x.gridy = 4;
		this.add(password,x);
		
		x.gridx = 0;
		x.gridy = 5;
		x.weightx = 0.3;
		mp.setPreferredSize(new Dimension(150,25));
		this.add(mp,x);
		
		x.gridx = 0;
		x.gridy = 6;
		x.weightx = 0.5;
		x.insets = new Insets(25,25,25,25);
		login.setPreferredSize(new Dimension(100,25));
		this.add(login,x);
		
		//this.add(form);
		
		//username.setHorizontalAlignment(JLabel.CENTER);
		//password.setHorizontalAlignment(JLabel.CENTER);
		//password.setBackground(Couleur.getBgThem());
		//password.setForeground(Couleur.getBgApp());
		//password.setFont(new Font("Arial", Font.BOLD, 15) );
		//password.setBorder(new LineBorder(Couleur.getBgTitle()));
		//username.setBackground(Couleur.getBgThem());
		//username.setForeground(Couleur.getBgApp());
		//username.setFont(new Font("Arial", Font.BOLD, 15) );
		//username.setBorder(new LineBorder(Couleur.getBgTitle()));
		user.setPreferredSize((new Dimension(150, 25) ));
		
	}

	//public JPanel getForm() {
		//return form;
	//}

	public JLabel getUsername() {
		return username;
	}

	public void setUsername(JLabel username) {
		this.username = username;
	}

	public JLabel getPassword() {
		return password;
	}

	public void setPassword(JLabel password) {
		this.password = password;
	}

	public JButton getLogin() {
		return login;
	}

	public void setLogin(JButton login) {
		this.login = login;
	}

	public JTextField getUser() {
		return user;
	}

	public void setUser(JTextField user) {
		this.user = user;
	}

	public JPasswordField getMp() {
		return mp;
	}

	public void setMp(JPasswordField mp) {
		this.mp = mp;
	}


}
