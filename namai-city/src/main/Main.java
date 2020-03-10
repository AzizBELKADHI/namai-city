package main;

import java.sql.SQLException;

import controller.DBConnectController;
import view.TestPoolView;

public class Main {
	static DBConnectController namaicityController;

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		namaicityController = new DBConnectController(new TestPoolView());
		namaicityController.start();
		

	}

}
