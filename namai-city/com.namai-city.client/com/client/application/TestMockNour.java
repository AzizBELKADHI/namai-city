package com.client.application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import com.client.controller.SocketClient;
import com.commons.model.AccessServer;

public class TestMockNour {
	private SocketClient client = new SocketClient();
	public static Connection c; 
	private static String URL = "jdbc:postgresql://172.31.249.44:5432/NamaiDB";
	private static String login = "toto" ;
	private static String password = "toto";
	
	public static void main (String[] args) {
		TestMockNour t = new TestMockNour();
		try {
			t.go();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void go() throws SQLException, IOException {
	client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
	JSONObject obj=new JSONObject();

	System.out.println("########################### INSERT SENSOR #########################");
	obj.put("demandType",String.valueOf("MOCK_SENSOR_INSERT"));

	
	JSONObject reponseInsertSensor = client.sendMessage(obj);
	String repServerSensor = (String) reponseInsertSensor.get("reponse");
	System.out.println(repServerSensor);
	
	client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
	JSONObject obj2 =new JSONObject();
	System.out.println("########################### INSERT CAR #########################");
	obj2.put("demandType",String.valueOf("MOCK_CAR_INSERT"));
	
	JSONObject reponseInsertCar = client.sendMessage(obj2);
	String repServerCar = (String) reponseInsertCar.get("reponse");
	System.out.println(repServerCar);
	
	client.stopConnection();
	
	client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
	JSONObject obj3 =new JSONObject();
	System.out.println("########################### INSERT STATION #########################");
	obj3.put("demandType",String.valueOf("MOCK_STATION_INSERT"));

	
	JSONObject reponseInsertStation = client.sendMessage(obj3);
	String repServerStation = (String) reponseInsertStation.get("reponse");
	System.out.println(repServerStation);

	client.stopConnection();
	
	client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
	JSONObject obj4 =new JSONObject();
	System.out.println("########################### INSERT  PERS STATION #########################");
	obj4.put("demandType",String.valueOf("MOCK_PERS_STATION_INSERT"));

	
	JSONObject reponseInsertPersStation = client.sendMessage(obj4);
	String repServerPersStation = (String) reponseInsertPersStation.get("reponse");
	System.out.println(repServerPersStation);

	client.stopConnection();
	
	client.startConnection(AccessServer.getSERVER(), AccessServer.getPORT_SERVER());
	JSONObject obj5 =new JSONObject();
	System.out.println("########################### INSERT HISTORICAL WARNING #########################");
	obj5.put("demandType",String.valueOf("MOCK_WARNING_HISTORICAL_INSERT"));

	
	JSONObject reponseInsertWarning = client.sendMessage(obj5);
	String repServerWarning = (String) reponseInsertWarning.get("reponse");
	System.out.println(repServerWarning);

	client.stopConnection();

	
	}
}
