package scalableSearcher;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import data.model.Measure;

public class SearcherClient {
	private static final Object [] FILE_HEADER = {"clientThreads","requestsPerThread","totalTime","throughput"};
	public static final int nR = 50;
	public static final int nT = 20;
	public static void main(String[] args) {
		try {       
			Searcher s = (Searcher)Naming.lookup("rmi://127.0.0.1/searcher");
			
			FileWriter fileWriter = null;
			CSVPrinter csvFilePrinter = null;
			CSVFormat csvFileFormat = CSVFormat.DEFAULT;
			fileWriter = new FileWriter(new File("prova.txt"));
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
			csvFilePrinter.printRecord(FILE_HEADER);
			
			
			Measure m = new Measure();
			Worker[] w = new Worker[nT];
			//
			long elapsedTime = System.currentTimeMillis();
			for(int i = 0; i < nT; i++)
				(w[i] = new Worker(s, nR)).start();
			//
			for(int i = 0; i < nT; i++)
				w[i].join();
			elapsedTime = System.currentTimeMillis() - elapsedTime;
			System.out.println("Tempo totale: " +  elapsedTime + " ms");
			double throughput = (nT*Worker.nR*1000)/(double)elapsedTime;
			System.out.println("Throughput: " + throughput + " req/s");
			m.setClientThreads(nT);
			m.setRequestsPerThread(nR);
			m.setTotalTime(elapsedTime);
			m.setThroughput(throughput);
			csvFilePrinter.printRecord(m.getRecord());
			fileWriter.flush();
			fileWriter.close();
			csvFilePrinter.close();
		} catch (NotBoundException e) {      
			System.err.println("Request obect not bound "+ e); 
		} catch (MalformedURLException e) {   
			System.err.println("Wrong URL" + e); 
		} catch (RemoteException e) {      
			System.err.println("Network or Server Error" + e);  
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
}

class Worker extends Thread{
	public static int nR;
	public Searcher s;
	public Worker(Searcher s, int nR){
		this.s = s;
		Worker.nR = nR;
	}
	public void run(){
		//
		long elapsedTime = System.currentTimeMillis();
		for(int i = 0; i < nR; i++){
			try {
				boolean result = s.findObject(i);
				System.out.println("Object with key " + i + " exists: " + result);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		//
		elapsedTime = System.currentTimeMillis() - elapsedTime;
		System.out.println("Elaborazione locale al thread: " + this + " in " + elapsedTime + " ms");
		System.out.println("Tempo di risposta medio: " + elapsedTime/(double)nR + " ms");
	}
}