package scalableSearcher;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FailureDetector extends Remote {
	
	public boolean isAlive() throws RemoteException;

}
