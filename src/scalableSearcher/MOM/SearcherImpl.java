package scalableSearcher.MOM;
import java.rmi.RemoteException;
import scalableSearcher.Searcher;

public class SearcherImpl implements Searcher {
	/*private DBInterfacer db;

	protected SearcherImpl(DBInterfacer db) throws RemoteException {
		super();
		this.db = db;
	}*/
	protected SearcherImpl(){}
	
	public boolean findObject(Object o) throws RemoteException{
		/*int in = (Integer) o;
		boolean result = ((in % 2)==0);*/
		
		for(long i = 0; i < 1000000L; i++)
			for(long j = 0; j < 100L; j++);
		//System.out.println("Messaggio " + in + " ricevuto ed elaborato")
		return true;
	}
}