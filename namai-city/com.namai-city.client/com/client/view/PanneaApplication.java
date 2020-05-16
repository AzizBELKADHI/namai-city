package com.client.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.client.controller.ApplicationController;

public class PanneaApplication extends JPanel {
	private MenuApplication ma;
	private PanneauUC puc;
	private BorderLayout bl;
	
	
	public PanneaApplication() {
		
		
		puc = new PanneauUC();
		ma = new MenuApplication();
		bl = new BorderLayout();
		this.setLayout(bl);
		this.add(puc, BorderLayout.CENTER);
		this.add(ma, BorderLayout.WEST);
		
		
	}

	public MenuApplication getMa() {
		return ma;
	}

	public PanneauUC getPuc() {
		return puc;
	}

}
