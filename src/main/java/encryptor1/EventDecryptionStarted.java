package encryptor1;

import java.util.ArrayList;

import observers.IDecryptionStartedObserver;

public class EventDecryptionStarted 
{
	ArrayList<IDecryptionStartedObserver> observers=
			new ArrayList<IDecryptionStartedObserver>();
	
	public void addObserver(IDecryptionStartedObserver observer)
	{
		observers.add(observer);
	}
	
	public void notifyObservers(long time)
	{
		for(int i=0; i<observers.size();i++)
		{
			observers.get(i).notifyDecryptionStarted(time);
		}
	}
}
