package com.client.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.client.application.TestJson;
import com.client.view.ConnectionNamaiCity;
import com.commons.model.AccessServer;




public class ConnectionController implements ActionListener {
	 private ConnectionNamaiCity  fenetre;
	 private SocketClient client = new SocketClient();
	 TestJson  tj = new TestJson();
		
		public ConnectionController(ConnectionNamaiCity fenetre) {
			this.fenetre = fenetre;
			fenetre.getPan().getLogin().addActionListener(this);
			fenetre.getPan().getUsername().setBackground(Color.BLUE);
			//fenetre.getPan().getPassword().setBackground(Color.BLUE);
			//fenetre.getPan().getForm().setBackground(Color.PINK);
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String [] T = new String[2];
			T[0]=fenetre.getPan().getUser().getText();
			T[1]=new String (fenetre.getPan().getMp().getPassword());
			System.out.println(T[0]);
			System.out.println(T[1]);
			if(T[0].isEmpty() || T[1].isEmpty()) {
				JOptionPane.showMessageDialog(null, "the entry is empty", "connection error", JOptionPane.ERROR_MESSAGE);
			}
			
			else if(T[0].equals("namai") &&	T[1].equals("namai")) {
				try {
					client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
					client.stopConnection();
					//tj.go();
				} catch (IOException e1) {
					System.out.println("impossible de se connecter au serveur");
					e1.printStackTrace();
				}
				fenetre.getSuperpos().next(fenetre.getContentPane());
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Invalid password or username", "Try again", JOptionPane.ERROR_MESSAGE);
			}
				}
	 
	

}
