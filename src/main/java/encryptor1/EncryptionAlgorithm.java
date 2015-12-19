package encryptor1;

import exceptions.InvalidEncryptionKeyException;
import exceptions.InvalidPathException;

public interface EncryptionAlgorithm
{
	/*********interface for encryption algorithm ************/
	public void makeEncryption(String path,Key key)
			throws InvalidEncryptionKeyException,InvalidPathException;
	public void makeDecryption(String path,String key_path)
			throws InvalidEncryptionKeyException, InvalidPathException;
	public int getKeyStrength();
	public EncryptionAlgorithm clone();

}
