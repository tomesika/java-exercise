package encryptor1;

import exceptions.InvalidEncryptionKeyException;
import exceptions.InvalidPathException;

public class MyDecryptThread implements Runnable 
{
	EncryptionAlgorithm a;
	String path;
	String keyPath;
	EventDecryptionStarted startDec=new EventDecryptionStarted();
	EventDecryptionEnded endDec=new EventDecryptionEnded();
	EncryptionLogger encLogger=new EncryptionLogger();
	
   public MyDecryptThread(EncryptionAlgorithm a, String path, String keyPath) 
   {
       this.a=a;
       this.path=path;
       this.keyPath=keyPath;
   }

   public void run() 
   {
		try 
		{
			startDec.addObserver(encLogger);
			endDec.addObserver(encLogger);
			startDec.notifyObservers(System.currentTimeMillis());
			a.makeDecryption(path, keyPath);
			endDec.notifyObservers(System.currentTimeMillis(),
    				a.getClass().getName(),path);
		} catch (InvalidEncryptionKeyException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPathException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
   
}