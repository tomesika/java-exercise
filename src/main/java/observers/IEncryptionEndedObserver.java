package observers;

public interface IEncryptionEndedObserver 
{
	public void notifyEncryptionEnded(long time, String algorithmName, String path);
}
