package encryptor1;

import exceptions.InvalidEncryptionKeyException;
import exceptions.InvalidPathException;

public class MulEncryption extends Algorithm
{
	/**** constructor ***/
	public MulEncryption(String path,OneKey key)
	{
		super(path);
		this.key=key.getKey().intValue();
		setType("encrypted");
	}
	
	
	//specific action for encryption- multiply the key value from the letter
	public char doAction(char curr)
	{
		return (char) ((curr*getKey())%256);
	}

	//write the results of encryption into files and write proper message to the user
	public void finishAction() throws InvalidPathException 
	{
		String message="the key and the encryptefd file are in path: ";
		String only_path=writeResult(message);
		only_path+="key.txt";
		getWriter().write(only_path, Integer.toString(getKey()));
	}
}
