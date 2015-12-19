package observers;

public interface IDecryptionEndedObserver 
{
	public void notifyDecryptionEnded(long time, String algorithmName, String path);
}
