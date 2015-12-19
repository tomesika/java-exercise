package xmlParsers;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomParser 
{
	File xmlFile;
	String algorithm;
	String keyPath;
	String sourceDirectory;
	String sourceFileName;
	public DomParser(File xmlFile)
	{
		this.xmlFile=xmlFile;
	}
	
	public void parse()
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try 
		{
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();					
			NodeList nList = doc.getElementsByTagName("ProcessSettings");
			Node nNode = nList.item(0);
	         if (nNode.getNodeType() == Node.ELEMENT_NODE) 
	         {
	            Element eElement = (Element) nNode;
	            algorithm=eElement.getElementsByTagName("Algorithm").item(0)
	            		.getTextContent();
	            System.out.println("algorithm : " +algorithm);
	            keyPath=eElement.getElementsByTagName("KeyPath").item(0)
	            		.getTextContent();
	            System.out.println("key path : "+keyPath);
	            sourceDirectory=eElement.getElementsByTagName("SourceDirectory").item(0)
	            		.getTextContent();
	            System.out.println("source directory : "+sourceDirectory);
	            sourceFileName=eElement.getElementsByTagName("SourceFileName").item(0)
	            		.getTextContent();
	            System.out.println("source file name : " +sourceFileName);
	         }
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
