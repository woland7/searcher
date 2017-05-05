package scalabeSearcher.MOM;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import it.unisannio.requestReplyAPI.Requestor;
import it.unisannio.requestReplyAPI.RequestorImpl;
import scalableSearcher.Searcher;

public class SearcherProxy extends UnicastRemoteObject implements Searcher {
	private static final long serialVersionUID = 1L;
	private Requestor requestor;
	public SearcherProxy() throws RemoteException, JMSException{
		requestor = new RequestorImpl("searchQueue", true);
	}
	public boolean findObject(Object o) throws RemoteException{
		try{
			ObjectMessage input = requestor.createObjectMessage();
			input.setObject((Serializable)o);
			ObjectMessage resultMsg = (ObjectMessage)requestor.request(input);
			Boolean result = (Boolean)resultMsg.getObject();
			return result;
		}catch(JMSException e){
			return false;
		}
	}
}