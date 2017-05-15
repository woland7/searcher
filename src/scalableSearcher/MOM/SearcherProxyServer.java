package scalableSearcher.MOM;

import java.rmi.Naming;
import java.util.Scanner;

import scalableSearcher.FailureDetector;
import scalableSearcher.FailureDetectorImpl;
import scalableSearcher.Searcher;


public class SearcherProxyServer {
	public static void main(String[] args){		
		System.out.println("Inserisci il tipo: ");
		Scanner sc = new Scanner(System.in);
		String tipo = sc.nextLine();
		sc.close();
		if(tipo.equals("main")){
			try{

				Searcher s = new SearcherProxy();
				Naming.rebind("rmi://127.0.0.1/searcher", s);
				FailureDetector fd = new FailureDetectorImpl();
				Naming.rebind("rmi://127.0.0.1/detector", fd);
				System.out.println("Main server running...");

			}catch(Exception e){
				System.err.println("There's some error.");
			}
		}
		else if(tipo.equals("secondary")){
			LocalDetector ld = new LocalDetector();
			ld.start();
		}
		else
			System.out.println("Tipo non identificato");
	}
}
