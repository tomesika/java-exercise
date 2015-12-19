package encryptor1;

import exceptions.InvalidEncryptionKeyException;
import exceptions.InvalidPathException;

public class Main {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		UnitTests u=new UnitTests();
		u.testEncryptor1();
		u.testEncryptor2();
		u.testEncryptor3();
		u.testEncryptor4();
		u.testEncryptor5();
		u.testDeccryptor1();
		u.testDeccryptor2();
		u.testDeccryptor3();
		u.testDeccryptor4();
		u.testDeccryptor5();
		u.testDoubleEncryptor1();
		u.testDoubleEncryptor2();
		u.testDoubleEncryptor3();
		u.testDoubleEncryptor4();
		u.testDoubleEncryptor5();
		u.testDoubleDecryptor1();
		u.testDoubleDecryptor2();
		u.testDoubleDecryptor3();
		u.testDoubleDecryptor4();
		u.testDoubleDecryptor5();
		u.testMulEncryptor1();
		u.testMulDecryptor2();
		u.testMulDecryptor3();
		u.testMulDecryptor4();
		u.testMulDecryptor5();
		u.testMulDecryptor1();
		u.testMulDecryptor2();
		u.testMulDecryptor3();
		u.testMulDecryptor3();
		u.testMulDecryptor4();
		u.testMulDecryptor5();
		u.testCompareKeys();
		try 
		{
			u.testCheckInvalidKey();
			System.out.println("test for checking invalid key failed");
			System.exit(1);
		} catch (InvalidEncryptionKeyException e){}
		try 
		{
			u.testCheckInvalidPath();
			System.out.println("test for checking invalid path failed");
			System.exit(1);
		} catch (InvalidPathException e) {}
		
		u.readFilesSyncEncrypt();
		u.readFilesSyncDecrypt();
		
	}
		

}
