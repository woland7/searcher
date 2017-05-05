package scalableSearcher.remote;
import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import data.DBInterfacer;
import scalableSearcher.Searcher;

public class SearcherServerRemote {
	public static void main(String[] args) {
		try {
			DBInterfacer db = new DBInterfacer();
			Searcher s = new SearcherImpl(db);       
			Naming.rebind("rmi://127.0.0.1/searcher", s);      
		} catch (AccessException e) { 
			System.err.println("Bind operation not permitted");      
		} catch (RemoteException e) { 
			System.err.println("Registry could not be contacted");
		} catch (MalformedURLException e) { 
			System.err.println("Wrong URL for binding");      
		}   
	}
}
