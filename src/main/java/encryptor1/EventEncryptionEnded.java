package encryptor1;

import java.util.ArrayList;

import observers.IEncryptionEndedObserver;

public class EventEncryptionEnded 
{
	ArrayList<IEncryptionEndedObserver> observers=
			new ArrayList<IEncryptionEndedObserver>();
	
	public void addObserver(IEncryptionEndedObserver observer)
	{
		observers.add(observer);
	}
	
	public void notifyObservers(long time, String algorithmName, String path)
	{
		for(int i=0; i<observers.size();i++)
		{
			observers.get(i).notifyEncryptionEnded(time,algorithmName,path);
		}
	}
}
