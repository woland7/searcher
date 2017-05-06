package data.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Measure {
	private int clientThreads, requestsPerThread;
	private double totalTime, throughput;
	
	public Measure(){
		
	}

	public int getClientThreads() {
		return clientThreads;
	}

	public void setClientThreads(int clientThreads) {
		this.clientThreads = clientThreads;
	}

	public int getRequestsPerThread() {
		return requestsPerThread;
	}

	public void setRequestsPerThread(int requestsPerThread) {
		this.requestsPerThread = requestsPerThread;
	}

	public double getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(double totalTime) {
		this.totalTime = Measure.round(totalTime,2);
	}

	public double getThroughput() {
		return throughput;
	}

	public void setThroughput(double throughput) {
		this.throughput = Measure.round(throughput,2);
	}
	
	public List<String> getRecord(){
		List<String> l = new ArrayList<String>();
		l.add(String.valueOf(this.getClientThreads()));
		l.add(String.valueOf(this.getRequestsPerThread()));
		l.add(String.valueOf(this.getTotalTime()));
		l.add(String.valueOf(this.getThroughput()));
		return l;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}