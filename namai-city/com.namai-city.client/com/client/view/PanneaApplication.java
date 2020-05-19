package com.client.view;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.swing.JPanel;

import com.client.controller.ApplicationController;

public class PanneaApplication extends JPanel {
	private MenuApplication ma;
	private PanneauUC puc;
	private BorderLayout bl;
	
	
	public PanneaApplication() throws UnsupportedEncodingException, SQLException, IOException {
		
		
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
