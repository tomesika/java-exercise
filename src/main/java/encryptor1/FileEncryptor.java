package encryptor1;

import exceptions.InvalidEncryptionKeyException;
import exceptions.InvalidPathException;

public class FileEncryptor
{
	EncryptionAlgorithm algo=null;
	EventEncryptionStarted startEnc=new EventEncryptionStarted();
	EventEncryptionEnded endEnc=new EventEncryptionEnded();
	EventDecryptionStarted startDec=new EventDecryptionStarted();
	EventDecryptionEnded endDec=new EventDecryptionEnded();
	EncryptionLogger encLogger=new EncryptionLogger();
	
	/***constructor- gets encryptor algorithm and make actions using it ****/
	public FileEncryptor(EncryptionAlgorithm a)
	{
		algo=a;
		startEnc.addObserver(encLogger);
		startDec.addObserver(encLogger);
		endEnc.addObserver(encLogger);
		endDec.addObserver(encLogger);
	}
	
	// making the encryption action using the algorithm
	public void makeEncription(String path,Key key)
			throws InvalidEncryptionKeyException, InvalidPathException 
	{
		startEnc.notifyObservers(System.currentTimeMillis());
		algo.makeEncryption(path,key);
		endEnc.notifyObservers(System.currentTimeMillis(),
				algo.getClass().getName(),path);
	}

	//make decryption using the algorithm
	public void makeDecryption(String path,String key_path) 
			throws InvalidEncryptionKeyException, InvalidPathException
	{
		startDec.notifyObservers(System.currentTimeMillis());
		algo.makeDecryption(path, key_path);
		endDec.notifyObservers(System.currentTimeMillis(),
				algo.getClass().getName(),path);
	}
}
