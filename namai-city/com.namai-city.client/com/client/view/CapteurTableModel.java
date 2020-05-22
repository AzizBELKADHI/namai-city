package com.client.view;

import com.client.view.*;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CapteurTableModel  {
	private ListCapteur modele = new ListCapteur();
	private JTable tableau;
	private JFrame f;

	public CapteurTableModel() {
		super();
		f = new JFrame();
		f.setTitle("Liste Des Capteur");
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tableau = new JTable(modele);

		f.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);

		JPanel boutons = new JPanel();
		f.setVisible(true);


		//  boutons.add(new JButton(new AddAction())); boutons.add(new JButton(new
		//  RemoveAction()));

		// getContentPane().add(boutons, BorderLayout.SOUTH);


		f.pack();
	}

	public static void main(String[] args) {
		CapteurTableModel c =new CapteurTableModel();

	}
	
}