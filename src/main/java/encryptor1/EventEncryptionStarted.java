package encryptor1;

import java.util.ArrayList;
import observers.IEncryptionStartedObserver;

public class EventEncryptionStarted 
{
	ArrayList<IEncryptionStartedObserver> observers=
			new ArrayList<IEncryptionStartedObserver>();
	
	public void addObserver(IEncryptionStartedObserver observer)
	{
		observers.add(observer);
	}
	
	public void notifyObservers(long time)
	{
		for(int i=0; i<observers.size();i++)
		{
			observers.get(i).notifyEncryptionStarted(time);
		}
	}
}
