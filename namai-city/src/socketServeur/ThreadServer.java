package socketServeur;

import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import com.connectionPool.DataSource;
import controller.DBConnectController;
import indicator.Car;
import indicator.Sensor;
import indicator.SensorPolluant;
import indicator.Tram;
import indicator.Warning;
import indicator.WarningIndicator;

import java.io.*; 
import java.net.*;


public class ThreadServer extends Thread {
	private Socket clientSocket; 
	public PrintWriter outJson;
	private BufferedReader inJson;
	private Connection c; 


	public ThreadServer(Socket socket, Connection connection) {
		this.clientSocket = socket;
		this.c = connection; 

	}

	public void run()  {

		try {
			outJson = new PrintWriter(clientSocket.getOutputStream(), true);
			inJson  = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 


			// processing part of Json 

			outJson = new PrintWriter(clientSocket.getOutputStream(), true);
			inJson = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String resp = inJson.readLine();
			System.out.println("----bonjour je viens de récuperer le JSON");
			System.out.println(resp);

			Object obj=JSONValue.parse(resp); 
			System.out.println("----bonjour je parse le JSON");
			System.out.println(resp);
			JSONObject jsonObject = (JSONObject) obj;  
			System.out.println("----bonjour je viens de parser le JSON");
			System.out.println(resp);

			JSONObject objResponse = crud(jsonObject); 
			// Once the Json had been processed, closing the socket and releasing the connection
			String respToSend = objResponse.toString();
			outJson.println(respToSend);
			DataSource.releaseConnection(c); 
			inJson.close();
			outJson.close();
			clientSocket.close();
		} catch (Exception e) {
			System.out.println("--------Un client s'est déconnecté de manière précipitée !-------");
			e.printStackTrace();
		}
		DBConnectController.clientsState(false);
	}


	// crud method allowing to according to customer's choice (select / insert/ update / delete) to do the request
	private JSONObject crud(JSONObject JsonRecu)  {

		try {
			if(JsonRecu.get("demandType").equals("SELECT")) {
				long idcaste = Long.valueOf(JsonRecu.get("Id").toString());
				int idJson = (int) idcaste;
				System.out.println("bonjour voici le ID recu apres traitement");
				System.out.println(idJson);
				if(idJson == 0) {

					PreparedStatement stmt1 = c.prepareStatement("select * from utilisateur;");
					ResultSet rs2 = stmt1.executeQuery();

					JSONObject obj=new JSONObject();
					// creation of users list 
					ArrayList<JSONObject> listUsers = new ArrayList<JSONObject>();

					while (rs2.next()) {
						JSONObject user=new JSONObject();
						// recovery of each user's data (id/ name/ first name) 
						user.put("Id", rs2.getInt("id_user"));
						user.put("nom", rs2.getString("nom"));
						user.put("prenom", rs2.getString("prenom"));

						// adding each user to the list already created
						listUsers.add(user);


					}
					//System.out.println("voici l'arrayList : ");
					// displaying the list 
					//System.out.println(listUsers);

					obj.put("users", listUsers);
					System.out.println("voici le json envoyé avec le select All: ");
					// displaying the Json
					System.out.println(obj);
					Thread.sleep(3000); 
					return obj; 
				}
				else {
					PreparedStatement stmtJson = c.prepareStatement("select * from utilisateur where id_user = ?");
					stmtJson.setInt(1, idJson);
					ResultSet jsonResponse = stmtJson.executeQuery();
					JSONObject obj=new JSONObject(); 
					int cpt = 0;

					while (jsonResponse.next()) {
						//recovery of the data of the user in question 
						cpt++;
						obj.put("Id",jsonResponse.getInt("id_user"));
						obj.put("nom",jsonResponse.getString("nom"));
						obj.put("prenom",jsonResponse.getString("prenom"));
					}
					if(cpt == 0) {
						obj.put("reponse", "verifier l'id insere");
					}
					// displaying the json 
					System.out.println(obj);
					Thread.sleep(3000); 
					return obj; 
				}
			}

			if(JsonRecu.get("demandType").equals("INSERT")) {
				System.out.println("Je suis rentré dans la requête INSERT"); 
				// recovery of data that the client had completed (name / first name
				String nomInsert =(String) JsonRecu.get("nom");
				String prenomInsert =(String) JsonRecu.get("prenom");
				System.out.println("bonjour voici les donnees recu apres traitement");
				System.out.println(nomInsert +  " " + prenomInsert);

				PreparedStatement stmt3 = c.prepareStatement("insert into utilisateur(nom,prenom) values (?,?);");
				// the request takes name and first name already retrieved 
				stmt3.setString(1, nomInsert);
				stmt3.setString(2,prenomInsert);
				// query execution 


				JSONObject obj=new JSONObject(); 

				// if (insertion bien passé) => executer les lignes suivantes sinon dire erreur
				if(stmt3.executeUpdate()>=1) {
					obj.put("reponse",String.valueOf("insertion reussi"));
					obj.put("nom",String.valueOf(nomInsert));
					obj.put("prenom",String.valueOf(prenomInsert));
				}
				else {
					obj.put("reponse",String.valueOf("erreur lors de l'insertion"));
				}
				System.out.println(obj);
				return obj; 
			}


			if(JsonRecu.get("demandType").equals("UPDATE")) {
				System.out.println("Je suis rentré dans la requete UPDATE");
				String nomUpdate =(String) JsonRecu.get("nom");
				String prenomUpdate =(String) JsonRecu.get("prenom");
				System.out.println("J'accede a la donnée id ");
				long idcaste = Long.valueOf(JsonRecu.get("Id").toString());
				int idJson = (int) idcaste;
				System.out.println("acces réussi");
				System.out.println("bonjour voici les donnees recu apres traitement");
				System.out.println(nomUpdate + prenomUpdate + idJson);
				PreparedStatement stmt4 = c.prepareStatement("update utilisateur set nom= ?, prenom = ? where id_user = ?;  ");
				stmt4.setString(1, nomUpdate);
				stmt4.setString(2,prenomUpdate);
				System.out.println("je met le idJson dans la requete "); 
				stmt4.setInt(3, idJson);
				System.out.println("l'id est bien mis"); 
				JSONObject obj=new JSONObject(); 


				//if (update  bien passé) => executer les lignes suivantes sinon dire erreur

				if(stmt4.executeUpdate()>=1) {
					obj.put("reponse",String.valueOf("mise à jour reussie"));
					obj.put("nom",String.valueOf(nomUpdate));
					obj.put("prenom",String.valueOf(prenomUpdate));
					obj.put("Id", Integer.valueOf(idJson)); 
				}
				else {
					obj.put("reponse",String.valueOf("erreur lors de la mise a jour verifier l'id"));
				}
				System.out.println(obj);
				return obj; 
			}

			if(JsonRecu.get("demandType").equals("DELETE")) {

				long idcaste = Long.valueOf(JsonRecu.get("Id").toString());
				int idJson = (int) idcaste;
				System.out.println("bonjour voici l'ID à supprimer");
				System.out.println(idJson);
				PreparedStatement stmt5 = c.prepareStatement("delete from utilisateur where id_user = ?");
				stmt5.setInt(1, idJson);


				JSONObject obj=new JSONObject(); 
				if(stmt5.executeUpdate()>=1) {
					obj.put("reponse",String.valueOf("suppression réussie"));
					obj.put("Id", Integer.valueOf(idJson));
					System.out.println(obj);
				}
				else {
					obj.put("reponse",String.valueOf("echec lors de la suppression verifier l'Id insere"));
					System.out.println(obj);		
				}
				return obj;
			}

			// MOCK TEST 
			if (JsonRecu.get("demandType").equals("MOCK_SENSOR_INSERT")) {
				System.out.println("CAPTEUR");
				SensorInsert sensorInsert = new SensorInsert(); 
				sensorInsert.insertSensor(JsonRecu, c); 
			}

			if (JsonRecu.get("demandType").equals("MOCK_CAR_INSERT")) {
				System.out.println("FREQUENTATION-VOITURE");
				CarInsert carInsert = new CarInsert(); 
				carInsert.insertCar(JsonRecu, c); 
			}

			if (JsonRecu.get("demandType").equals("MOCK_STATION_INSERT")) {
				System.out.println("STATION ");
				StationTramInsert stationInsert = new StationTramInsert(); 
				stationInsert.insertStation(JsonRecu, c); 
			}

			if (JsonRecu.get("demandType").equals("MOCK_PERS_STATION_INSERT")) {
				System.out.println("FREQUENTATION PERSONNE STATION ");
				StationTramInsert stationInsert = new StationTramInsert(); 
				stationInsert.insertPersStation(JsonRecu, c); 
			}

			if (JsonRecu.get("demandType").equals("MOCK_SENSOR_POLLUANT_INSERT")) {
				System.out.println(" CAPTEUR POLLUANT"); 
				SensorPolluantInsert sensorPolluantInsert = new SensorPolluantInsert(); 
				sensorPolluantInsert.insertSensorPolluant(JsonRecu,c); 
			}
			if (JsonRecu.get("demandType").equals("MOCK_HISTORICAL_SENSOR_POLLUANT_INSERT")) {
				System.out.println(" ALERTE"); 
				HistoricalSensorPolluantInsert historicalSensorPolluantInsert = new HistoricalSensorPolluantInsert(); 
				historicalSensorPolluantInsert.insertHistoricalSensorPolluant(JsonRecu,c); 
			}
			
			if (JsonRecu.get("demandType").equals("MOCK_BORNE_INSERT")) {
				System.out.println(" BORNE"); 
				BornesInsert bornesInsert = new BornesInsert(); 
				bornesInsert.insertBorne(JsonRecu,c); 
			}
			
			
			// FIN MOCK TEST 
			
			// BEGIN TEST REQUESTS

			if (JsonRecu.get("demandType").equals("SENSOR_INDICATOR")) {

				System.out.println("le nombre de capteurs par zone selon la date et le type (qualité de l'air, borne...."); 
				Sensor sensor = new Sensor(); 
				System.out.println("initialisation de la classe Sensor");
				JSONObject obj = sensor.getIndicator(JsonRecu,c);
				return obj; 

			}
			
			if (JsonRecu.get("demandType").equals("SENSOR_INDICATOR2")) {

				System.out.println("le nombre de capteurs par zone selon la date et le type (qualité de l'air, borne...."); 
				Sensor sensor = new Sensor(); 
				System.out.println("initialisation de la classe Sensor");
				JSONObject obj = sensor.getIndicatorBorne(JsonRecu,c);
				return obj; 

			}
			if (JsonRecu.get("demandType").equals("SENSOR_INDICATOR3")) {

				System.out.println("le nombre de capteurs par zone selon la date et le type (qualité de l'air, borne...."); 
				Sensor sensor = new Sensor(); 
				System.out.println("initialisation de la classe Sensor");
				JSONObject obj = sensor.getIndicatorSensorPolluant(JsonRecu,c);
				return obj; 

			}
			if (JsonRecu.get("demandType").equals("STATION_INDICATOR")) {

				System.out.println("le nombre de station par zone dans la ville: "); 
				Tram tram = new Tram(); 
				System.out.println("initialisation de la classe tram");
				JSONObject obj = tram.stationDAO(JsonRecu,c); 
				return obj; 

			}

			if (JsonRecu.get("demandType").equals("PERSON_STATION_INDICATOR")) {

				System.out.println("le nombre de personnes par zone dans la ville: "); 
				Tram tram = new Tram(); 
				System.out.println("initialisation de la classe tram"); 
				JSONObject obj = tram.personStationDAO(JsonRecu,c);
				return obj; 
			}
			if (JsonRecu.get("demandType").equals("CAR_INDICATOR")) {

				System.out.println("le nombre de voitures par date dans la ville : "); 
				Car car = new Car(); 
				System.out.println("initialisation de la classe Car"); 
				JSONObject obj = car.carDAO(JsonRecu,c);
				return obj; 
		

			}
			if (JsonRecu.get("demandType").equals("WARMING_INDICATOR")) {

				System.out.println("le nombre d'alerte par date dans la ville : "); 
				Warning  warning = new Warning(); 
				System.out.println("initialisation de la classe WarningIndicator"); 
				//return warning.warmingDAO(JsonRecu,c); 

			}
			if (JsonRecu.get("demandType").equals("SENSOR_POLLUANT_INDICATOR")) {

				System.out.println("le nombre d'alerte par date dans la ville : "); 
				SensorPolluant warning = new SensorPolluant(); 
				System.out.println("initialisation de la classe sensorPolluant"); 
				JSONObject obj = warning.getIndicator(JsonRecu,c);
				return obj;

			}
			if (JsonRecu.get("demandType").equals("getIdSensorPolluant")) {

				System.out.println("affichage de tous les id des capteurs polluant: "); 
				SensorPolluant id = new SensorPolluant(); 
				System.out.println("initialisation de la classe sensorPolluant"); 
				JSONObject obj = id.getIdSensorPolluant(JsonRecu,c);
				return obj;

			}
			if (JsonRecu.get("demandType").equals("getThresholdSensorPolluant")) {

				System.out.println("Consultation des polluants"); 
				SensorPolluant polluant = new SensorPolluant(); 
				System.out.println("initialisation de la classe sensorPolluant"); 
				JSONObject obj = polluant.getThreshold(JsonRecu,c);
				return obj;

			}
			
			if (JsonRecu.get("demandType").equals("getWarningPolluant")) {

				System.out.println("Consultation des polluants"); 
				SensorPolluant polluant = new SensorPolluant(); 
				System.out.println("initialisation de la classe sensorPolluant"); 
				JSONObject obj = polluant.getWarning(JsonRecu,c);
				return obj;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		// Case where no if is checked 
		return new JSONObject();
	}

}
