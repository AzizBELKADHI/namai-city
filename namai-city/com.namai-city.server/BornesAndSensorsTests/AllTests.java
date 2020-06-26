package BornesAndSensorsTests;

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
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import bornes.Borne;
import carsHistory.carsHistory;

public class AllTests {
	Connection c;
	
	public AllTests(Connection c) {
		this.c = c;
	}
	
	public JSONArray getDataForTests(String fileName) throws UnsupportedEncodingException, ParseException {
		InputStream inputStream = FileReader.class.getClassLoader().getSystemResourceAsStream(fileName);
		StringBuffer sb = new StringBuffer();
			
		System.out.println("je recupere les donnees de tests pour la fonctionnalite a partir du fichier JSON ");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")); 
		System.out.println("j'ai recuperer le fichier.JSON pour les tests");
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
		/* the test file is parsed and transformed to a json file in order to be analyzed 
		 * in the steps above
		 */
		System.out.println("voici les donnees contenu dans le fichier de tests : \n" + json);
		return json;
	}
	
	
	public Object testChangingMax() throws SQLException, InterruptedException, UnsupportedEncodingException, ParseException {
		JSONArray testFile = getDataForTests("maxCars.json");
		carsHistory cars = new carsHistory(this.c); 
		JSONObject obj=new JSONObject();
		System.out.println("max voitures actuels : " + carsHistory.maxCars);
		
		ArrayList<JSONObject> maxLimitsTest = new ArrayList<JSONObject>();
		
		for (int i = 0; i < testFile.size(); i++) {
			JSONObject max = new JSONObject();
			JSONObject jsonObject = (JSONObject) testFile.get(i);
			System.out.println("nouveau max a inserer pour tests : " + jsonObject.get("new_max"));
			int newMax = Integer.parseInt(String.valueOf(jsonObject.get("new_max")));
			max = cars.updateMaxCars(newMax);
			String insert = Integer.toString(newMax);
			max.put("max voitures: ", insert);
			maxLimitsTest.add(max);
			System.out.println("max voitures actuels en base apres insertion: " + cars.carsLimit());
			}
		System.out.println(maxLimitsTest);
		obj.put("testsResults", maxLimitsTest);
		System.out.println(obj);
		
		return obj;
		
	}

	
}
