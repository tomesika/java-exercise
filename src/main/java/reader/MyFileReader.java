package reader;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import exceptions.InvalidPathException;

public class MyFileReader implements MyReader{
	
	public String read(String path) throws InvalidPathException
	{
		//open the file
		FileInputStream fileStream;
		try 
		{
	
			fileStream = new FileInputStream(path);
			DataInputStream input=new DataInputStream(fileStream);
			BufferedReader br=new BufferedReader(new InputStreamReader(input));
			String line=br.readLine();
			String content="";
			while(line!=null)
			{
				content+=line;
				line=br.readLine();
				
			}
			//close the file
		  	br.close();		
		  	return content;
		}
	  	catch (Exception e)
	  	{
	  		throw new InvalidPathException();
	  	}		
	}

}
