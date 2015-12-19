package encryptor1;

import exceptions.InvalidEncryptionKeyException;
import exceptions.InvalidPathException;

public class MyEncryptThread implements Runnable 
{
		EncryptionAlgorithm a;
		String path;
		Key key;
		EventEncryptionStarted startEnc=new EventEncryptionStarted();
		EventEncryptionEnded endEnc=new EventEncryptionEnded();		
		EncryptionLogger encLogger=new EncryptionLogger();

	   public MyEncryptThread(EncryptionAlgorithm a, String path, Key key) 
	   {
	       this.a=a;
	       this.path=path;
	       this.key=key;
	   }

	   public void run() 
	   {
		 try 
		{
			startEnc.addObserver(encLogger);
			endEnc.addObserver(encLogger);
			startEnc.notifyObservers(System.currentTimeMillis());
			a.makeEncryption(path, key);
			endEnc.notifyObservers(System.currentTimeMillis(),
    				a.getClass().getName(),path);
		} catch (InvalidEncryptionKeyException e) 
		 {
			e.printStackTrace();
		} catch (InvalidPathException e) 
		{
			e.printStackTrace();
		}
	   }
	

}
