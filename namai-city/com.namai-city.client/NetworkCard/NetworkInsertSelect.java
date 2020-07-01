package NetworkCard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class NetworkInsertSelect {

	private final String jsonFileName = "CarteVille.json"; 
	private JSONObject reponseTest=new JSONObject();
	public JSONObject  insertNetwork (JSONObject jsonRequest,Connection c) throws ParseException, UnsupportedEncodingException, SQLException, JSONException {


		
		if(jsonRequest.get("demandType").equals("demande insertion")) {
			System.out.println("Je suis rentre dans la requete INSERT"); 
			// recovery of data that the client had completed (name / first name
			String libelle =(String) jsonRequest.get("libelle");
			String shape =(String) jsonRequest.get("shape");
			Double length = (Double) jsonRequest.get("length");
			Double width = (Double) jsonRequest.get("width");
			Integer nb_points = (Integer) jsonRequest.get("nb_points");
			Double cost = (Double) jsonRequest.get("cost");
			System.out.println("bonjour voici les donnees recu apres traitement");
			System.out.println(libelle +  " " + shape+" "+length+" "+width+" "+nb_points+" "+cost);
			//testRedundancy(jsonRequest);
			PreparedStatement stmt3 = c.prepareStatement("insert into CarteVille(libelle, shape,length,width,nb_points, cost) values (?,?,?,?,?,?);");
			// the request takes name and first name already retrieved 
			stmt3.setString(1, libelle);
			stmt3.setString(2,shape);
			stmt3.setDouble(3, length);
			stmt3.setDouble(4, width);
			stmt3.setDouble(5, nb_points);
			stmt3.setDouble(6, cost);
			// query execution 


			JSONObject obj=new JSONObject(); 

			// if (insertion bien pass?) => executer les lignes suivantes sinon dire erreur
			if(stmt3.executeUpdate()>=1) {
				obj.put("reponse",String.valueOf("insertion reussi"));
				obj.put("libelle",String.valueOf(libelle));
				obj.put("shape",String.valueOf(shape));
				obj.put("length",String.valueOf(length));
				obj.put("width",String.valueOf(width));
				obj.put("nb_points",String.valueOf(nb_points));
				obj.put("cost",String.valueOf(cost));
				writeJsonFile(obj);
			}
			else {
				obj.put("reponse",String.valueOf("erreur lors de l'insertion"));
			}
			System.out.println(obj);
			return obj; 
		}else {
			return null;
		}


		
		
	}
	
	 public void writeJsonFile(JSONObject networkInserts) {
		 try (FileWriter file = new FileWriter(jsonFileName)) {
			 
	            file.write(networkInserts.toString());
	            file.flush();
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
	 public void testRedundancy(JSONObject jsonForInsert) {
		//JSON parser object to parse read file
	        JSONParser jsonParser = new JSONParser();
	         
	        try (FileReader reader = new FileReader(jsonFileName))
	        {
	            //Read JSON file
	            Object obj = jsonParser.parse(reader);
	 
	            JSONArray networkList = (JSONArray) obj;
	            System.out.println(networkList);
	             
	            //Iterate over employee array
	            networkList.forEach( net -> {
					try {
						parseAndTestExist( (JSONObject) net,jsonForInsert );
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} );
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	 }
	 private static void parseAndTestExist(JSONObject netInserted,JSONObject netForInsert) throws JSONException 
	    {
		
	        String libelleInserted = (String) netInserted.get("libelle");    
	        String libelleForInsert = (String) netForInsert.get("libelle"); 
	        String shapeInserted = (String) netInserted.get("shape");    
	        String shapeForInsert = (String) netForInsert.get("shape"); 
	        Double lengthInserted = (Double) netInserted.get("length");    
	        Double lengthForInsert = (Double) netForInsert.get("length");
	        Double widthInserted = (Double) netInserted.get("width");    
	        Double widthForInsert = (Double) netForInsert.get("width");
	        int nb_pointsInserted = (int) netInserted.get("nb_points");    
	        int nb_pointsForInsert = (int) netForInsert.get("nb_points");
	        Double costInserted = (Double) netInserted.get("cost");    
	        Double costForInsert = (Double) netForInsert.get("cost");
	        if( libelleInserted.toUpperCase().equals(libelleForInsert.toUpperCase()) && 
	        		shapeInserted.toUpperCase().equals(shapeForInsert.toUpperCase()) && 
	        		lengthInserted.equals(lengthForInsert) && 
	        		widthInserted.equals(widthForInsert) && 
	        		nb_pointsInserted==nb_pointsForInsert && 
	        		costInserted.equals(costForInsert)) 
	        {
	        
	        }
	        
	       
	    }

}

