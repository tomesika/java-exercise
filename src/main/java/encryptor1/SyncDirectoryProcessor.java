package encryptor1;

import java.io.File;

import reader.MyFileReader;
import reader.MyReader;
import writer.MyFileWriter;
import writer.MyWriter;
import exceptions.InvalidEncryptionKeyException;
import exceptions.InvalidPathException;

public class SyncDirectoryProcessor implements IDirectoryProcessor
{
	MyReader fr=new MyFileReader();
	MyWriter fw=new MyFileWriter();
	EventEncryptionStarted startEnc=new EventEncryptionStarted();
	EventEncryptionEnded endEnc=new EventEncryptionEnded();
	EventDecryptionStarted startDec=new EventDecryptionStarted();
	EventDecryptionEnded endDec=new EventDecryptionEnded();
	EncryptionLogger encLogger=new EncryptionLogger();
	

	public void encryptDirectory(EncryptionAlgorithm a, String dirPath, Key key) throws InvalidEncryptionKeyException, InvalidPathException 
	{
		startEnc.addObserver(encLogger);
		endEnc.addObserver(encLogger);
		final File folder = new File(dirPath); 
		boolean success = (new File(dirPath+"\\encrypted")).mkdirs();
		if (!success) {
			//////logger
		}
		for (final File fileEntry : folder.listFiles()) 
		{
	        if (!fileEntry.isDirectory()) 
	        {
	        	startEnc.notifyObservers(System.currentTimeMillis());
	            a.makeEncryption(dirPath+"\\"+fileEntry.getName(), key);
	            endEnc.notifyObservers(System.currentTimeMillis(),
	    				a.getClass().getName(),dirPath);
	        }
	    }
		for (final File fileEntry : folder.listFiles()) 
		{
	        if (!fileEntry.isDirectory()) 
	        {
	        	String name=fileEntry.getName();
	           if(name.contains("_encrypted"))
	           {
	        	   String content=fr.read(dirPath+"\\"+name);
	        	   String simpleName=getSimpleName(name);
	        	   fw.write(dirPath+"\\encrypted\\"+simpleName, content);
	        	   fileEntry.delete();
	           }
	           else if(name.equals("key.txt"))
	           {
	        	   String content=fr.read(dirPath+"\\"+name);
	        	   fw.write(dirPath+"\\encrypted\\"+name, content);
	        	   fileEntry.delete();
	           }
	        }    
	    }
	}

	private String getSimpleName(String name) 
	{
		String[] seperatedName=name.split("[_.]");
		String str="";
		int size=seperatedName.length;
		for(int i=0;i<size-2;i++)
		{
			str+=seperatedName[i];
		}
		str+="."+seperatedName[size-1];
		return str;
	}

	public void decryptDirectory(EncryptionAlgorithm a, String dirPath,
			String keyPath) throws InvalidEncryptionKeyException, InvalidPathException 
	{
		startDec.addObserver(encLogger);
		endDec.addObserver(encLogger);
		final File folder = new File(dirPath); 
		boolean success = (new File(dirPath+"\\decrypted")).mkdirs();
		if (!success) {
			//////logger
		}
		for (final File fileEntry : folder.listFiles()) 
		{
	        if (!fileEntry.isDirectory()) 
	        {
	        	String name=fileEntry.getName();
	        	if(!name.equals("key.txt"))
	        	{
	        		startDec.notifyObservers(System.currentTimeMillis());
	        		a.makeDecryption(dirPath+"\\"+name, keyPath);
	        		endDec.notifyObservers(System.currentTimeMillis(),
	        				a.getClass().getName(),dirPath);
	        	}
	        }
	    }
		for (final File fileEntry : folder.listFiles()) 
		{
	        if (!fileEntry.isDirectory()) 
	        {
	        	String name=fileEntry.getName();
	           if(name.contains("_decrypted"))
	           {
	        	   String content=fr.read(dirPath+"\\"+name);
	        	   String simpleName=getSimpleName(name);
	        	   fw.write(dirPath+"\\decrypted\\"+simpleName, content);
	        	   fileEntry.delete();
	           }
	        }
	    }
	}
	

}
