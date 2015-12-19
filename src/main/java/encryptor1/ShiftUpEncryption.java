package encryptor1;

import exceptions.InvalidEncryptionKeyException;
import exceptions.InvalidPathException;

public class ShiftUpEncryption implements EncryptionAlgorithm
{
	Algorithm a=null;
	
	// making the encryption algorithm with the action of adding the key
	public void makeEncryption(String path,Key key) 
			throws InvalidEncryptionKeyException, InvalidPathException 
	{
		a=new Encryption(path,(OneKey)key);
		a.makeAction();
	}

	// making the encryption algorithm with the action of decreasing the key
	public void makeDecryption(String path,String key_path) 
			throws InvalidEncryptionKeyException, InvalidPathException
	{
		a=new Decryption(path,key_path);
		a.makeAction();
	}

	//calculate the key strength (num of digits)
	public int getKeyStrength() 
	{
		int count=0;
		if(null==a)
			return 0;
		else
		{
			int key=a.getKey();
			while(key!=0)
			{
				count++;
				key/=10;
			}
		}
		return count;
	}
	

	public EncryptionAlgorithm clone()
	{
		return new ShiftUpEncryption();
	}

}
