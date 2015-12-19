package encryptor1;

import exceptions.InvalidEncryptionKeyException;
import exceptions.InvalidPathException;

public interface IDirectoryProcessor 
{
	public void encryptDirectory(EncryptionAlgorithm a,String dirPath,Key key) throws InvalidEncryptionKeyException, InvalidPathException;
	public void decryptDirectory(EncryptionAlgorithm a,String dirPath,String keyPath) throws InvalidEncryptionKeyException, InvalidPathException;
}
