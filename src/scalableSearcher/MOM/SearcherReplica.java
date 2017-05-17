package scalableSearcher.MOM;

import java.rmi.RemoteException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import it.unisannio.requestReplyAPI.Replier;
import it.unisannio.requestReplyAPI.ReplierImpl;
import scalableSearcher.Searcher;

public class SearcherReplica {
	public static void main(String[] args){
		try{
			Replier replier = new SearcherReplierImpl("searchQueue");
			replier.start();
			System.out.println("Replica has been started");
		}catch(Exception e){System.err.println("Replica failed");}
	}
}

class SearcherReplierImpl extends ReplierImpl{
	private Searcher searcher = new SearcherImpl();
	public SearcherReplierImpl(String queueName) throws JMSException{
		super(queueName, true, "169.254.253.40", true);
	}
	public Message onRequest(Message msg) throws JMSException{
		ObjectMessage om = (ObjectMessage)msg;
		Object input = om.getObject();
		boolean r = false;
		try {
			r = searcher.findObject(input);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		ObjectMessage resMsg = createObjectMessage();
		resMsg.setObject(r);
		return resMsg;	
	}
}