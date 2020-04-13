package indicator;

import java.sql.Timestamp;

public class WarningIndicator {

	private int warningId; 
	private String warningState; 
	private int thresholdId; 
	private int threshold;
	private Timestamp date; 
	
	public WarningIndicator() {
		
	}
	
	public WarningIndicator(int idA, String warning,int idS, int s, Timestamp d ) {
		warningId = idA; 
		warningState = warning; 
		thresholdId = idS; 
		threshold = s; 
		date = d; 
	}

	public int getWarningId() {
		return warningId;
	}

	public void setWarningId(int warningId) {
		this.warningId = warningId;
	}

	public String getWarningState() {
		return warningState;
	}

	public void setWarningState(String warningState) {
		this.warningState = warningState;
	}

	public int getThresholdId() {
		return thresholdId;
	}

	public void setThresholdId(int thresholdId) {
		this.thresholdId = thresholdId;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	
}