package encryptor1;

import exceptions.InvalidPathException;
import observers.IDecryptionEndedObserver;
import observers.IDecryptionStartedObserver;
import observers.IEncryptionEndedObserver;
import observers.IEncryptionStartedObserver;
import writer.MyScreenWriter;
import writer.MyWriter;

public class EncryptionLogger implements IDecryptionStartedObserver,
	IEncryptionEndedObserver,IEncryptionStartedObserver,
	IDecryptionEndedObserver
{
	long beginTimeEnc;
	long beginTimeDec;
	public void notifyEncryptionStarted(long time)
	{
		beginTimeEnc=time;
	}

	public void notifyEncryptionEnded(long time, String algorithmName,
			String path) 
	{
		EncryptionLogEventArgs args=new EncryptionLogEventArgs(beginTimeEnc,
				time,algorithmName,path,"encrypted");
		writeLoggerMessage("encrypt",args.getNameOfFile(),
				args.getAlgorithm(),args.getTime(),args.getNewName());
		
	}

	public void notifyDecryptionStarted(long time)
	{
		beginTimeDec=time;
	}

	public void notifyDecryptionEnded(long time, String algorithmName,
			String path) 
	{
		EncryptionLogEventArgs args=new EncryptionLogEventArgs(beginTimeDec,
				time,algorithmName,path,"decrypted");
		writeLoggerMessage("decrypt",args.getNameOfFile(),
				args.getAlgorithm(),args.getTime(),args.getNewName());
	}
	
	public void writeLoggerMessage(String type,String X,String Y,long Z,String F)
	{
		String message="the "+type+"ion for file"+X+" with algorithm "+Y+" took"+
				Z+"miliseconds. The "+type+"ed file is located in file "+F;
		System.out.println(message);
	}
	

}
