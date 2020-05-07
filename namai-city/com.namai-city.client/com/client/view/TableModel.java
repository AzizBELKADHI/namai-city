package com.client.view;

import java.awt.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel{

	private final String[] colonne = { "type", " position", "date",
	"nombre de capteurs selon la position et la date" };
	private final ArrayList capteur = new ArrayList();

	public int getColumnCount() {
		return colonne.length;
	}

	public int getRowCount() {
		return capteur.size();
	}

	public String getColumnName(int column) {
		return colonne[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return capteur.get(row).getType();
		case 1:
			return capteur.get(row).getPosition();
		case 2:
			return capteur.get(row).getDate();
		case 3:
			return capteur.get(row).getSensorNb();
		default:
			return "";
		}
	}
	*/
}

