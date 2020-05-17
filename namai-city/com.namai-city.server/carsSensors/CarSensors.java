package carsSensors;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import carsHistory.carsHistory;

public class CarSensors extends Thread {
	Connection c;
	InputStream inputStream;
	
	public CarSensors(Connection c, InputStream inputStream) {
		this.c = c;
		this.inputStream = inputStream;
	}
	
	public void run() {
		
			try {
				this.LaunchSimulation(this.inputStream);
			} catch (ParseException | SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	
	
	public void LaunchSimulation (InputStream inputStream) throws ParseException, UnsupportedEncodingException, SQLException, IOException{

		StringBuffer sb = new StringBuffer();
		carsHistory carsSimulation = new carsHistory(this.c);
		ServerSocket server = new ServerSocket(3001);
		Socket s = server.accept();
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		
		
		System.out.println("je suis rentre dans launchSimulation et deja lance la socket");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")); 
		System.out.println("j'ai recuperer le fichier.JSON");
		try {
			String temp; 
			while ((temp = bufferedReader.readLine()) != null) 
				sb.append(temp); 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace(); 
			}
		}
		System.out.println(sb);
		String myjsonstring = sb.toString(); 
		

		JSONParser parser = new JSONParser(); 
		JSONArray json;
		json = (JSONArray) parser.parse(myjsonstring);
		 
		System.out.println(json);
		for (int i = 0; i < json.size(); i++) {
			JSONObject jsonObject = (JSONObject) json.get(i);
			int id_sensor = (int) jsonObject.get("id_sensor");  
			double taille = (double) jsonObject.get("taille");
			String objet =  String.valueOf(jsonObject.get("objet")); 
			
			if(taille >=2.50 && taille <12) {
				try {
					carsSimulation.addCarToHistory(objet, "voiture", id_sensor);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(taille >=12) {
				try {
					carsSimulation.addCarToHistory(objet, "poids-lourd", id_sensor);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else {
				System.out.println("detection d'un objet de petite taille non identifié");
			}
			dos.writeInt(carsHistory.totalCars);
			server.close();

		}
	}
}
