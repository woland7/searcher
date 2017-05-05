package scalableSearcher.remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import data.DBInterfacer;
import scalableSearcher.Searcher;

public class SearcherImpl extends UnicastRemoteObject implements Searcher {
	private DBInterfacer db;
	private static final long serialVersionUID = 1L;
	protected SearcherImpl(DBInterfacer db) throws RemoteException {
		this.db = db;
	}
	
	public boolean findObject(Object o) throws RemoteException{
		return db.exists((Integer) o);
	}
}