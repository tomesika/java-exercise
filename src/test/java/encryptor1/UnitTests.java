package encryptor1;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.mockito.Mockito;

import reader.MyFileReader;
import reader.MyReader;
import xmlParsers.DomParser;
import xmlParsers.JaxBHandler;
import xmlParsers.SaxHandler;
import exceptions.InvalidEncryptionKeyException;
import exceptions.InvalidPathException;

import java.io.File;

import javax.xml.bind.*;
public class UnitTests {
	ShiftUpEncryption test = Mockito.mock(ShiftUpEncryption.class);
	String path="C:\\tests\\";
	String dir_path="";
	MyReader fr=new MyFileReader();

		
	/****************************Tests*****************************/
	
	
	/******************* 5 tests to check encryption**********************/
	private void helpTestEncryptor(int i,String result){
		 String content_path="";
		 String str="";
		 ShiftUpEncryption tester = new ShiftUpEncryption();
		 try 
		 {
			Integer key=Integer.parseInt(fr.read(path+Integer.toString(i)+"\\key.txt"));
			OneKey keyObj=new OneKey(key);
			tester.makeEncryption(path+Integer.toString(i)+"\\file.txt",keyObj);
			content_path=path+Integer.toString(i)+"\\file_encrypted.txt";
			str= fr.read(content_path);
		} 
		catch (InvalidPathException e) 
		{
			System.out.println(e.getMessage());
		}
		catch (InvalidEncryptionKeyException e)
		{
			System.out.println(e.getMessage());
		}
		 assertEquals(result,str);
	 
	}
	
	
	 @Test
	 public void testEncryptor1(){
		 helpTestEncryptor(1,"cdef");
	 }
	 
	 @Test
	 public void testEncryptor2(){
		 helpTestEncryptor(2,";<=>?");
	 }
	 @Test
	 public void testEncryptor3(){
		 helpTestEncryptor(3,"FfGgHh");
	 }
	 @Test
	 public void testEncryptor4(){
		 helpTestEncryptor(4,"$C&'(a)-+,");
	 }
	 @Test
	 public void testEncryptor5(){
		 helpTestEncryptor(5,".gh;i.");
	 }
	 
	 
	 /******************* 5 tests to check decryption**********************/
	 private void helpTestDecryptor(int i) {
		 String content_path="";
		 String s="",s1="";
		 ShiftUpEncryption tester = new ShiftUpEncryption();
		 dir_path=path+Integer.toString(i);
		try 
		{
		tester.makeDecryption(dir_path+"\\file_encrypted.txt"
				 			  ,dir_path+"\\key.txt");
		content_path= path+Integer.toString(i)
			 	   +"\\file_encrypted_decrypted.txt";
		 s1= fr.read(content_path);
		 s=fr.read(path+Integer.toString(i)+"\\file.txt");
		 
		} 
		catch (InvalidEncryptionKeyException e) 
		{
			System.out.println(e.getMessage());
		}
		catch(InvalidPathException e)
		{
			System.out.println(e.getMessage());
		}
		 assertEquals(s,s1);
	 }
	  
	 @Test
	 public void testDeccryptor1() {
		 helpTestDecryptor(1);
	 }
	 @Test
	 public void testDeccryptor2() {
		 helpTestDecryptor(2);
	 }
	 @Test
	 public void testDeccryptor3() {
		 helpTestDecryptor(3);
	 }
	 @Test
	 public void testDeccryptor4() {
		 helpTestDecryptor(4);
	 }
	 @Test
	 public void testDeccryptor5() {
		 helpTestDecryptor(5);
	 }
	 
	 
	 
	 /**************** 5 tests for multiply encryption**************/
	 public void helpTestMulEncryptor(int i,String result) {
		String content_path="",str="";
		ShiftMultiplyEncryption tester = new ShiftMultiplyEncryption();
		try 
		{
			Integer key=Integer.parseInt(fr.read(path+Integer.toString(i)+"\\key.txt"));
			OneKey keyObj=new OneKey(key);
			tester.makeEncryption(path+Integer.toString(i)+"\\file.txt",keyObj);
			content_path=path+Integer.toString(i)+"\\file_encrypted.txt";
			str= fr.read(content_path);
		} catch (InvalidEncryptionKeyException e) 
		{
			System.out.println(e.getMessage());
		}
		catch(InvalidPathException e)
		{
			System.out.println(e.getMessage());
		}
		 
		 assertEquals(result,str);
	 }
	 
	 @Test
	 public void testMulEncryptor1() {
		 helpTestMulEncryptor(6,"abcd");
	 }
	 
	 @Test
	 public void testMulEncryptor2() {
		 helpTestMulEncryptor(7,"bdfhj");
	 }
	 
	 @Test
	 public void testMulEncryptor3() {
		 helpTestMulEncryptor(8,"cciicc");
	 }
	 
	 @Test
	 public void testMulEncryptor4() {
		 helpTestMulEncryptor(9,"c¿ilor~x{");
	 }
	 
	 @Test
	 public void testMulEncryptor5() {
		 helpTestMulEncryptor(10,"D∂∏^∫D");
	 }	
	 
	 /**************** 5 tests for multiply decryption**************/
	 public void helpTestMulDecryptor(int i) {
		 String content_path="",s="",s1="";
		 ShiftMultiplyEncryption tester = new ShiftMultiplyEncryption();
		 dir_path=path+Integer.toString(i);
		 try 
		 {
			tester.makeDecryption(dir_path+"\\file_encrypted.txt"
					 			  ,dir_path+"\\key.txt");
			content_path= path+Integer.toString(i)
				 	   +"\\file_encrypted_decrypted.txt";
			 s1= fr.read(content_path);
			 s=fr.read(path+Integer.toString(i)+"\\file.txt");
		 } 
		 catch (InvalidEncryptionKeyException e) 
		 {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		 }
		 catch(InvalidPathException e)
		 {
			System.out.println(e.getMessage());
		 }
		 assertEquals(s,s1);
	}
	 
	 @Test
	 public void testMulDecryptor1() {
		 helpTestMulDecryptor(6);
	 }
	 
	 @Test
	 public void testMulDecryptor2() {
		 helpTestMulDecryptor(7);
	 }
	 
	 @Test
	 public void testMulDecryptor3() {
		 helpTestMulDecryptor(8);
	 }
	 
	 @Test
	 public void testMulDecryptor4() {
		 helpTestMulDecryptor(9);
	 }
	 
	 @Test
	 public void testMulDecryptor5() {
		 helpTestMulDecryptor(10);
	 }
	 	 

		/***************** 5 tests to check double encryption*****************/
	 	public void helpTestDoubleEncryptor(int i,String result) {
		 String content_path="",str="";
		 ShiftUpEncryption enc = new ShiftUpEncryption();
		 DoubleEncryption tester=new DoubleEncryption(enc);
		 try 
		 {
			Integer key=Integer.parseInt(fr.read(path+Integer.toString(i)+"\\key.txt"));
			Integer key2=Integer.parseInt(fr.read(path+Integer.toString(i)+"\\key2.txt"));
			ArrayList<Integer> list=new ArrayList<Integer>();
			list.add(key);
			list.add(key2);
			MultipleKeys keyObj=new MultipleKeys(list);
			tester.makeEncryption(path+Integer.toString(i)+"\\file.txt",keyObj);
			content_path=path+Integer.toString(i)+"\\file_encrypted.txt";
			str= fr.read(content_path);
		 } 
		 catch (InvalidEncryptionKeyException e) 
		 {
			System.out.println(e.getMessage());
		 }
		 catch(InvalidPathException e)
		 {
			System.out.println(e.getMessage());
		 }
		 assertEquals(result,str);			 
	 }
	 
	 	@Test
		public void testDoubleEncryptor1() {
	 		helpTestDoubleEncryptor(11,";<=>?");
	 	}
	 	@Test
		public void testDoubleEncryptor2() {
	 		helpTestDoubleEncryptor(12,"JjKkLl");
	 	}
	 	@Test
		public void testDoubleEncryptor3() {
	 		helpTestDoubleEncryptor(13,",K./0i1534");
	 	}
	 	@Test
		public void testDoubleEncryptor4() {
	 		helpTestDoubleEncryptor(14,"/hi<j/");
	 	}
	 	@Test
		public void testDoubleEncryptor5() {
	 		helpTestDoubleEncryptor(15,"rstu");
	 	}
	 	
		 
		 /************** 5 tests to check double decryption**************/
	 	public void helpTestDoubleDecryptor(int i) {
			String content_path="",s="",s1="";
			ShiftUpEncryption enc = new ShiftUpEncryption();
			DoubleEncryption tester=new DoubleEncryption(enc);
			dir_path=path+Integer.toString(i);
			try {
				tester.makeDecryption(dir_path+"\\file_encrypted.txt"
						 			  ,dir_path+"\\key.txt");
				content_path= path+Integer.toString(i)
					 	   +"\\file_encrypted_decrypted.txt";
				s1= fr.read(content_path);
				s=fr.read(path+Integer.toString(i)+"\\file.txt");
			} 
			catch (InvalidEncryptionKeyException e) 
			{
				System.out.println(e.getMessage());
			}
			catch(InvalidPathException e)
			{
				System.out.println(e.getMessage());
			}
			 assertEquals(s,s1);
		 	
		 } 
	 	
	 	 @Test
		 public void testDoubleDecryptor1() {
	 		helpTestDoubleDecryptor(11);
	 	 }
	 	@Test
		 public void testDoubleDecryptor2() {
	 		helpTestDoubleDecryptor(12);
	 	 }
	 	@Test
		 public void testDoubleDecryptor3() {
	 		helpTestDoubleDecryptor(13);
	 	 }
	 	@Test
		 public void testDoubleDecryptor4() {
	 		helpTestDoubleDecryptor(14);
	 	 }
	 	@Test
		 public void testDoubleDecryptor5() {
	 		helpTestDoubleDecryptor(15);
	 	 }
	 	
		 
		 @Test(expected=InvalidEncryptionKeyException.class)
		 public void testCheckInvalidKey() throws InvalidEncryptionKeyException 
		 {
			String content_path="";
			ShiftUpEncryption tester = new ShiftUpEncryption();
			try 
			{
				OneKey key=new OneKey();
				tester.makeEncryption(path+"16",key);
				content_path=path+"16\\file_encrypted.txt";
				fr.read(content_path);
			} 
			catch (InvalidPathException e) 
			{
				System.out.println(e.getMessage());
			}
		 }
		 
		 @Test(expected=InvalidPathException.class)
		 public void testCheckInvalidPath() throws InvalidPathException
		 {
			String content_path="";
			ShiftUpEncryption tester = new ShiftUpEncryption();
			try 
			{
				OneKey key=new OneKey();
				tester.makeEncryption(path+"1",key);
				content_path=path+"1\\abcd.txt";
				fr.read(content_path);
			} 
			
			catch (InvalidEncryptionKeyException e) 
			{
				System.out.println(e.getMessage());
			}
		 }
		 
		 @Test
		 public void testCompareKeys() 
		 {
			EncryptionAlgorithm tester = new ShiftUpEncryption();
			EncryptionAlgorithm tester2=new ShiftMultiplyEncryption();
			EncryptionAlgorithm tester3=new ShiftMultiplyEncryption();

			CompareKeys c=new CompareKeys();
			int flag=0;
			try 
			{
				Integer key=Integer.parseInt(fr.read(path+"1"+"\\key.txt"));
				Integer key2=Integer.parseInt(fr.read(path+"2"+"\\key.txt"));
				OneKey keyObj=new OneKey(key);
				OneKey keyObj2=new OneKey(key2);
				tester.makeEncryption(path+"1"+"\\file.txt",keyObj);
				tester2.makeEncryption(path+"1"+"\\file.txt",keyObj);
				tester3.makeEncryption(path+"2"+"\\file.txt",keyObj2);
				assertEquals(c.compare(tester, tester2),0);
				if(c.compare(tester2, tester3)==0)
					flag=1;
				assertEquals(flag,0);
			} 
			catch (InvalidEncryptionKeyException e) 
			{
				System.out.println(e.getMessage());
			}
			catch (InvalidPathException e) 
			{
				System.out.println(e.getMessage());
			}

		 }
		 
		 @Test
			public void readFilesSyncEncrypt()
			{
				IDirectoryProcessor p=new SyncDirectoryProcessor();
				EncryptionAlgorithm a=new ShiftUpEncryption();
				OneKey key=new OneKey(2);
				try {
					p.encryptDirectory(a, path+"17", key);
				} catch (InvalidEncryptionKeyException e) 
				{
					e.printStackTrace();
				} catch (InvalidPathException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Test
			public void readFilesSyncDecrypt()
			{
				IDirectoryProcessor p=new SyncDirectoryProcessor();
				EncryptionAlgorithm a=new ShiftUpEncryption();
				try {
					p.decryptDirectory(a, path+"18",path+"18\\key.txt");
				} catch (InvalidEncryptionKeyException e) 
				{
					e.printStackTrace();
				} catch (InvalidPathException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Test
			public void checkXmlSaxParser()
			{
				SAXParserFactory factory = SAXParserFactory.newInstance();
		        try 
		        {
		            InputStream xmlInput  =new FileInputStream(path+"input.xml");
		            SAXParser  saxParser = factory.newSAXParser();
		            SaxHandler handler   = new SaxHandler();
		            saxParser.parse(xmlInput, handler);
		        } catch (Throwable err) 
		        {
		            err.printStackTrace ();
		        }
			}
			
			@Test
			public void checkXmlDomParser()
			{
				File fXmlFile = new File(path+"input.xml");
				DomParser parser=new DomParser(fXmlFile);
				parser.parse();
			}
			
			
			@Test
			public void checkJaxb()
			{
			  JAXBContext jc;
			try {
				jc = JAXBContext.newInstance(JaxBHandler.class);
				 Unmarshaller unmarshaller = jc.createUnmarshaller();
		        JaxBHandler h = (JaxBHandler) unmarshaller.unmarshal(new File(path+"input.xml"));

		        Marshaller marshaller = jc.createMarshaller();
		        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		        marshaller.marshal(h, System.out);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		       ;
			}
}
