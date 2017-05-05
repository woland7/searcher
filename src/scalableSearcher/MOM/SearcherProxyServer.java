package scalableSearcher.MOM;

import java.rmi.Naming;

import scalableSearcher.Searcher;

public class SearcherProxyServer {
	public static void main(String[] args){
		try{
			//Installing the main object
			Searcher s = new SearcherProxy();
			Naming.rebind("rmi://127.0.0.1/searcher", s);
		}catch(Exception e) {};
	}
}
