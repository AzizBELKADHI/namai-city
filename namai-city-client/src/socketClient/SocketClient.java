package socketClient;

import org.json.simple.*;    
import java.io.*;
import java.net.*;
import java.util.*;

public class SocketClient {

	private Socket socketClient;
	private PrintWriter outJson;
	private BufferedReader inJson;


	public void startConnection(String ip, int port) throws IOException {
		socketClient = new Socket(ip, port);
		outJson = new PrintWriter(socketClient.getOutputStream(), true);
		inJson = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
	}

	public JSONObject sendMessage(JSONObject JsonMsg) throws IOException {
		outJson.println(JsonMsg);
		String resp = inJson.readLine();
		Object obj=JSONValue.parse(resp); 
		JSONObject jsonObject = (JSONObject) obj;  
		return jsonObject;
	}

	public void stopConnection() throws IOException {
		inJson.close();
		outJson.close();
		socketClient.close();
	}

}
