import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import indicator.SensorIndicator;

public class SensorInsert {
    private ArrayList <SensorIndicator> getSensors() throws ParseException, UnsupportedEncodingException {
    	
    	StringBuffer sb = new StringBuffer();
    	
    	// lecture du JSON afin de mettre chaque ligne en chaîne de caractère
    	InputStream inputStream = FileReader.class.getClassLoader().getSystemResourceAsStream("sensors.json"); 
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")); 
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
    		String myjsonstring = sb.toString(); 
    	
		try {
			JSONParser parser = new JSONParser(); 
			JSONArray json = (JSONArray) parser.parse(myjsonstring); 
			
			for (int i = 0; i < json.size(); i++) {
				JSONObject jsonObject = (JSONObject) json.get(i);
				int sensorId = Integer.valueOf("id_cap"); 
				String type = String.valueOf("type"); 
				String position = String.valueOf("position");
				Timestamp date = Timestamp.valueOf("date");
				
			}
		} catch (IOException  e) {
			e.printStackTrace();
		}
		
		
			
		}
	}

