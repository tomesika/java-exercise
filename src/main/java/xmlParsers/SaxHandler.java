package xmlParsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler
{
	String algorithm;
	String keyPath;
	String sourceDirectory;
	String sourceFileName;
	
	boolean algo=false;
	boolean kp=false;
	boolean sd=false;
	boolean sfn=false;
	
	
	public void startElement(String uri, String localName,String qName, 
            Attributes attributes) throws SAXException 
	{

		if (qName.equalsIgnoreCase("Algorithm")) 
		{
			algo = true;
		}
		if (qName.equalsIgnoreCase("KeyPath")) 
		{
			kp = true;
		}
		if (qName.equalsIgnoreCase("SourceDirectory")) 
		{
			sd = true;
		}
		if (qName.equalsIgnoreCase("SourceFileName")) 
		{
			sfn = true;
		}
	}

	public void characters(char ch[], int start, int length) throws SAXException 
	{
		if (algo) 
		{
			algorithm=new String(ch, start, length);
			System.out.println("algorithm : " + algorithm);
			algo = false;
		}
		if (kp) 
		{
			keyPath=new String(ch, start, length);
			System.out.println("key path : " + keyPath);
			kp = false;
		}

		if (sd) 
		{
			sourceDirectory=new String(ch, start, length);
			System.out.println("source directory : " + sourceDirectory);
			sd = false;
		}

		if (sfn) 
		{
			sourceFileName=new String(ch, start, length);
			System.out.println("source file name : " + sourceFileName);
			sfn = false;
		}

	}
	
	public String getAlgorithm()
	{
		return algorithm;
	}
	
	public String getKeyPath()
	{
		return keyPath;
	}
	
	public String getSourceDirectory()
	{
		return sourceDirectory;
	}
	
	public String getSourceFileName()
	{
		return sourceFileName;
	}

}
