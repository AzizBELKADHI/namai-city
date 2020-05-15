package model;

import java.util.Map;

import com.commons.model.SocketClient;

import entities.CapteurPolluant;

public class ThreadCapteurPolluant implements Runnable {
	private SocketClient client;
	private CapteurPolluant capteurPolluant;
	private Map<String, String> mapHistorique;
	
	public ThreadCapteurPolluant(CapteurPolluant capteurPolluant2, SocketClient client2, Map<String, String> mapHistoriques) {
		this.capteurPolluant = capteurPolluant2;
		this.client = client2;
		this.mapHistorique = mapHistoriques;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
