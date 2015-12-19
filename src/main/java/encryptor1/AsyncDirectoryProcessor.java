package encryptor1;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import reader.MyFileReader;
import reader.MyReader;
import writer.MyFileWriter;
import writer.MyWriter;
import exceptions.InvalidEncryptionKeyException;
import exceptions.InvalidPathException;

public class AsyncDirectoryProcessor implements IDirectoryProcessor
{
	MyReader fr=new MyFileReader();
	MyWriter fw=new MyFileWriter();
	EventEncryptionStarted startEnc=new EventEncryptionStarted();
	EventEncryptionEnded endEnc=new EventEncryptionEnded();
	EventDecryptionStarted startDec=new EventDecryptionStarted();
	EventDecryptionEnded endDec=new EventDecryptionEnded();
	EncryptionLogger encLogger=new EncryptionLogger();

	public void encryptDirectory(EncryptionAlgorithm a, String dirPath, Key key)
			throws InvalidEncryptionKeyException, InvalidPathException 
	{
		startEnc.addObserver(encLogger);
		endEnc.addObserver(encLogger);
		startEnc.notifyObservers(System.currentTimeMillis());
		final File folder = new File(dirPath); 
		boolean success = (new File(dirPath+"\\encrypted")).mkdirs();
		if (!success) {
			//////logger
		}
		ExecutorService es = Executors.newCachedThreadPool();
		for (final File fileEntry : folder.listFiles()) 
		{
	        if (!fileEntry.isDirectory()) 
	        {
	        	String path=dirPath+"\\"+fileEntry.getName();
	        	/*************  create new thread for the encryption*************/
	        	Runnable r=new MyEncryptThread(a.clone(),path,key.clone());
	        	es.execute(r);	
	        }
	    }
		es.shutdown();
    	try {
			boolean finshed = es.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (final File fileEntry : folder.listFiles()) 
		{
	        if (!fileEntry.isDirectory()) 
	        {
	        	String name=fileEntry.getName();
	            if(name.contains("_encrypted"))
	            {
		        	/*************  create new thread********/
		        	Runnable r=new MyThreadMoveDir(fileEntry,"encrypted",dirPath);
		        	new Thread(r).start();	
	            }
	            else if(name.equals("key.txt"))
	            {
	         	  try    	  
	         	  {
	         		  String content=fr.read(dirPath+"\\"+name);
	     	    	   fw.write(dirPath+"\\encrypted\\"+name, content);
	     	    	   fileEntry.delete();
	         	  }
	         	  catch (InvalidPathException e) 
	         	  {
	       			e.printStackTrace();
	         	  }
	       		}
	        }    
	    }
		 endEnc.notifyObservers(System.currentTimeMillis(),
 				a.getClass().getName(),dirPath);
	}

	public void decryptDirectory(EncryptionAlgorithm a, String dirPath,
			String keyPath) throws InvalidEncryptionKeyException,
			InvalidPathException {
		startDec.addObserver(encLogger);
		endDec.addObserver(encLogger);
		startDec.notifyObservers(System.currentTimeMillis());
		final File folder = new File(dirPath); 
		boolean success = (new File(dirPath+"\\decrypted")).mkdirs();
		if (!success) 
		{
			//////logger
		}
		ExecutorService es = Executors.newCachedThreadPool();
		for (final File fileEntry : folder.listFiles()) 
		{
	        if (!fileEntry.isDirectory()) 
	        {
	        	String name=fileEntry.getName();
	        	if(!name.equals("key.txt"))
	        	{
	        		String path=dirPath+"\\"+fileEntry.getName();
		        	/*************  create new thread for the encryption*************/
		        	Runnable r=new MyDecryptThread(a.clone(),path,keyPath);
		        	es.execute(r);	
	        	}
	        }
	    }
		es.shutdown();	
		for (final File fileEntry : folder.listFiles()) 
		{
	        if (!fileEntry.isDirectory()) 
	        {
	        	String name=fileEntry.getName();
	           if(name.contains("_decrypted"))
	           {
	        	   /*************  create new thread********/
		        	Runnable r=new MyThreadMoveDir(fileEntry,"decrypted",dirPath);
		        	new Thread(r).start();
	           }
	        }
	    }
		endDec.notifyObservers(System.currentTimeMillis(),
				a.getClass().getName(),dirPath);
	}

}
