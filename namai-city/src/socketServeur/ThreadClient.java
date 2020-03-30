package socketServeur;

import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import connectionPool.DataSource;

import java.io.*; 
import java.net.*;


public class ThreadClient extends Thread {
	private Socket clientSocket; 
	public PrintWriter outJson;
	private BufferedReader inJson;
	private Connection c; 


	public ThreadClient(Socket socket, Connection connection) {
		this.clientSocket = socket;
		this.c = connection; 

	}

	public void run()  {
		try {
			outJson = new PrintWriter(clientSocket.getOutputStream(), true);
			inJson  = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  
			// partie traitement du Json 

			outJson = new PrintWriter(clientSocket.getOutputStream(), true);
			inJson = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String resp = inJson.readLine();
			System.out.println("bonjour je viens de récuperer le JSON");
			System.out.println(resp);
			Object obj=JSONValue.parse(resp); 
			System.out.println("bonjour je parse le JSON");
			System.out.println(resp);
			JSONObject jsonObject = (JSONObject) obj;  
			System.out.println("bonjour je viens de parser le JSON");
			System.out.println(resp);
			
			obj = crud(jsonObject); 

			outJson.println(obj);
			DataSource.releaseConnection(c); 
			inJson.close();
			outJson.close();
			clientSocket.close();
		} catch (IOException | SQLException e) {}
	}
	
	

	private Object crud(JSONObject JsonRecu) throws SQLException {



		if(JsonRecu.get("demandType").equals("SELECT")) {
			if((long)JsonRecu.get("Id") == 0) {
				// à completer
			}
			else {
				long idcaste = Long.valueOf(JsonRecu.get("Id").toString());
				int idJson = (int) idcaste;
				System.out.println("bonjour voici le ID recu apres traitement");
				System.out.println(idJson);
				PreparedStatement stmtJson = c.prepareStatement("select * from utilisateur where id_user = ?");
				stmtJson.setInt(1, idJson);
				ResultSet jsonResponse = stmtJson.executeQuery();
				JSONObject obj=new JSONObject(); 
				while (jsonResponse.next()) {
					obj.put("Id",jsonResponse.getInt("id_user"));
					obj.put("nom",jsonResponse.getString("nom"));
					obj.put("prenom",jsonResponse.getString("prenom"));
				}
				System.out.println(obj);
				return obj; 
			}
		}
		// ne pas utiliser valuOff mais plutôt getString / getInt

		if(JsonRecu.get("demandType").equals("INSERT")) {

			String nomInsert =(String) JsonRecu.get("nom");
			String prenomInsert =(String) JsonRecu.get("prenom");
			System.out.println("bonjour voici les donnees recu apres traitement");
			System.out.println(nomInsert +  " " + prenomInsert);
			PreparedStatement stmt3 = c.prepareStatement("insert into utilisateur(nom,prenom) values (?,?);");
			stmt3.setString(1, nomInsert);
			stmt3.setString(2,prenomInsert);
			stmt3.execute();
			JSONObject obj=new JSONObject(); 
			// if (insertion bien passé) => executer les lignes suivantes sinon dire erreur
			obj.put("reponse",String.valueOf("insertion réussi"));
			obj.put("nom",String.valueOf(nomInsert));
			obj.put("prenom",String.valueOf(prenomInsert));
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
			stmt4.execute();
			JSONObject obj=new JSONObject(); 


			//if (update  bien passé) => executer les lignes suivantes sinon dire erreur
			obj.put("reponse",String.valueOf("mise à jour réussie"));
			obj.put("nom",String.valueOf(nomUpdate));
			obj.put("prenom",String.valueOf(prenomUpdate));
			obj.put("Id", Integer.valueOf(idJson));

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
			stmt5.execute();

			JSONObject obj=new JSONObject(); 

			obj.put("reponse",String.valueOf("suppression réussie"));
			obj.put("Id", Integer.valueOf(idJson));
			System.out.println(obj);
			return obj; 



		}
		
		// Cas où aucun if n'est vérifier
		return new JSONObject();
	}

}
