package scalableSearcher;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class SearcherClient {
	public static final int nT = 20;
	public static void main(String[] args) {
		try {       
			Searcher s = (Searcher)Naming.lookup("rmi://127.0.0.1/searcher");  
			Worker[] w = new Worker[nT];
			//
			long elapsedTime = System.currentTimeMillis();
			for(int i = 0; i < nT; i++)
				(w[i] = new Worker(s)).start();
			//
			for(int i = 0; i < nT; i++)
				w[i].join();
			elapsedTime = System.currentTimeMillis() - elapsedTime;
			System.out.println("Tempo totale: " +  elapsedTime + " ms");
			System.out.println("Throughput: " + (nT*Worker.nR*1000)/(double)elapsedTime + " req/s");
         
		} catch (NotBoundException e) {      
			System.err.println("Request obect not bound "+ e); 
		} catch (MalformedURLException e) {   
			System.err.println("Wrong URL" + e); 
		} catch (RemoteException e) {      
			System.err.println("Network or Server Error" + e);  
		} catch (InterruptedException e) {
			e.printStackTrace();
		}   
	}
}

class Worker extends Thread{
	public static final int nR = 50;
	public Searcher s;
	public Worker(Searcher s){
		this.s = s;
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