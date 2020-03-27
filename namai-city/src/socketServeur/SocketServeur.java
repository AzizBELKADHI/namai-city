package socketServeur;

import java.net.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;

public class SocketServeur {
	private ServerSocket socketServeur;
	private Socket socketClient;
	public PrintWriter outJson;
	private BufferedReader inJson;
	
	public JSONObject start(int port) throws IOException {
	socketServeur = new ServerSocket(port);
	socketClient = socketServeur.accept();
	outJson = new PrintWriter(socketClient.getOutputStream(), true);
	inJson = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
	String resp = inJson.readLine();
	System.out.println("bonjour je viens de récuperer le JSON");
	System.out.println(resp);
    Object obj=JSONValue.parse(resp); 
    System.out.println("bonjour je parse le JSON");
    System.out.println(resp);
    JSONObject jsonObject = (JSONObject) obj;  
    System.out.println("bonjour je viens de parser le JSON");
    System.out.println(resp);

    return jsonObject;
	}
	public void stop() throws IOException {
	inJson.close();
	outJson.close();
	socketClient.close();
	socketServeur.close();
	}

}
