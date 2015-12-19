package encryptor1;

import java.util.ArrayList;

import reader.MyFileReader;
import reader.MyReader;
import writer.MyFileWriter;
import writer.MyWriter;
import exceptions.InvalidEncryptionKeyException;
import exceptions.InvalidPathException;

public class DoubleEncryption implements EncryptionAlgorithm 
{
	EncryptionAlgorithm a=null;
	MyReader fr=null;
	MyWriter fw=null;
	
	/**** constructor ****/
	public DoubleEncryption(EncryptionAlgorithm e)
	{
		a=e;
		fr=new MyFileReader();
		fw=new MyFileWriter();
	}
	
	//making action of double encryption (using two different keys)
	public void makeEncryption(String path,Key key) 
			throws InvalidEncryptionKeyException, InvalidPathException 
	{
			ArrayList<Integer> keys=((MultipleKeys)key).getKey();
			int key1=keys.get(0);
			int key2=keys.get(1);
			String origin="";
			//make the first encryption -with the first key
			a.makeEncryption(path,new OneKey(key1));
			String[] seperatedPath=path.split("\\\\");
			int len=seperatedPath.length;
			String only_path="";
			for(int i=0;i<len-1;i++)
			{
				only_path+=seperatedPath[i]+"\\";
			}	
			fw.write(only_path+"\\key2.txt",fr.read(only_path+"\\key.txt"));
			origin= fr.read(path);
			fw.write(path,fr.read(only_path	+"\\file_encrypted.txt"));
			//make second encryption with the second key
			a.makeEncryption(path,new OneKey(key2));
			//if(random)
				fw.write(path,origin);
		}
		
	//get the path of 2 files- key1 and key2 and swap their values
	private void swapFilesContent(String path) throws InvalidPathException
	{
		String str=fr.read(path+"\\key.txt");
		fw.write(path+"\\key.txt", fr.read(path+"\\key2.txt"));
		fw.write(path+"\\key2.txt", str);
	}

	//making action of double decryption using the 2 given different keys
	public void makeDecryption(String path, String key_path) 
			throws InvalidEncryptionKeyException, InvalidPathException 
	{	
		//first decryption using the first key
		a.makeDecryption(path, key_path);
		String[] seperatedPath=path.split("\\\\");
		int len=seperatedPath.length;
		String only_path="";
		for(int i=0;i<len-1;i++)
		{
			only_path+=seperatedPath[i]+"\\";
		}		
		String saved_key=fr.read(key_path);
		fw.write(key_path, fr.read(only_path+"key2.txt"));
		fw.write(only_path+"key2.txt", saved_key);
		String content=fr.read(only_path+"file_encrypted.txt");
		//set the encrypted_file value to the content after first decryption
		fw.write(only_path+"file_encrypted.txt", fr.read(only_path
				+"file_encrypted_decrypted.txt"));	
		//second decryption
		a.makeDecryption(path, key_path);
		fw.write(only_path+"file_encrypted.txt",content);
	}

	//calculate key strength- number of digits
	public int getKeyStrength() 
	{
		return a.getKeyStrength();
	}
	
	public EncryptionAlgorithm clone()
	{
		return new DoubleEncryption(a.clone());
	}
	
}
