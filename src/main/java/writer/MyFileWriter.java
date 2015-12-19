package writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import exceptions.InvalidPathException;

public class MyFileWriter implements MyWriter
{
	public void write(String path,String content) throws InvalidPathException
	{
		//create the output file
		File file = new File(path);
		try 
		{
			try
			{
				file.createNewFile();
			}
			catch(Exception e)
			{
				throw new InvalidPathException();
			}
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			//write the string into the file
			output.write(content);
			output.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
