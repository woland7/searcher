package scalableSearcher.MOM;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.jms.JMSException;

import scalableSearcher.FailureDetector;
import scalableSearcher.Searcher;

public class LocalDetector extends Thread {
	public LocalDetector(){
		
	}
	
	public void run(){
		FailureDetector fd;
		try {
			fd = (FailureDetector)Naming.lookup("rmi://127.0.0.1/detector");
			System.out.println("Secondary server running...");

			while(true){
				fd.isAlive();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			try {
				Searcher s = new SearcherProxy();
				System.out.println("Main server failed");
				Naming.rebind("rmi://127.0.0.1/searcher", s);
				System.out.println("Main server has been replaced.");
			} catch (RemoteException | MalformedURLException | JMSException e2) {
				e2.printStackTrace();
			}
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}