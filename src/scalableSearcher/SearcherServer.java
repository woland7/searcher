package scalableSearcher;
import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class SearcherServer {
	public static void main(String[] args) {
		try {        
			Searcher s = new SearcherImpl();       
			Naming.bind("rmi://127.0.0.1/searcher", s);      
		} catch (AccessException e) { 
			System.err.println("Bind operation not permitted");      
		} catch (RemoteException e) { 
			System.err.println("Registry could not be contacted");
		} catch (MalformedURLException e) { 
			System.err.println("Wrong URL for binding");      
		} catch (AlreadyBoundException e) {        
			System.err.println("Object alreay bound to the registry");      
		}   
	}
}
