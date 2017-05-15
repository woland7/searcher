package scalableSearcher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FailureDetectorImpl extends UnicastRemoteObject implements FailureDetector {
	private static final long serialVersionUID = 1L;

	public FailureDetectorImpl() throws RemoteException {
		super();
	}

	@Override
	public boolean isAlive() throws RemoteException {
		return true;
	}
}
