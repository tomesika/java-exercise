package encryptor1;

import java.util.ArrayList;

import observers.IDecryptionEndedObserver;

public class EventDecryptionEnded 
{
	ArrayList<IDecryptionEndedObserver> observers=
			new ArrayList<IDecryptionEndedObserver>();
	
	public void addObserver(IDecryptionEndedObserver observer)
	{
		observers.add(observer);
	}
	
	public void notifyObservers(long time, String algorithmName, String path)
	{
		for(int i=0; i<observers.size();i++)
		{
			observers.get(i).notifyDecryptionEnded(time,algorithmName,path);
		}
	}
}
