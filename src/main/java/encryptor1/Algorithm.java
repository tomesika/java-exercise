package encryptor1;

import reader.MyFileReader;
import reader.MyReader;
import writer.MyFileWriter;
import writer.MyScreenWriter;
import writer.MyWriter;
import exceptions.InvalidPathException;

public abstract class Algorithm 
{
	private String content="";
	private String path="";
	private String new_content="";
	protected int key;
	private MyReader fr=new MyFileReader();
	private MyWriter fw=new MyFileWriter();
	private MyWriter sw=new MyScreenWriter();
	private String type="";
	
	public Algorithm(String path)
	{
		this.path=path;
	}
	
	//make encryption/decryption action to all the file's content
	public void makeAction() throws InvalidPathException
	{
		content=fr.read(path);
		for(int i=0;i<content.length();i++)
		{
			new_content+=""+doAction(content.charAt(i));
		}
		finishAction();
	}
	
	//writing the result of the encryption/decryption into file
	public String writeResult(String msg) throws InvalidPathException
	{
		String[] seperatedPath=path.split("\\\\");
		int len=seperatedPath.length;
		String[] seperatedName=(seperatedPath[len-1]).split("\\.");
		String new_path="";
		String only_path="";
		for(int i=0;i<len-1;i++)
		{
			new_path+=seperatedPath[i]+"\\";
			only_path+=seperatedPath[i]+"\\";
		}		
		new_path+=seperatedName[0]+"_"+type+"."+seperatedName[1];
		fw.write(new_path, new_content);
		sw.write(null, (msg+only_path.substring(0, only_path.length()-1)));
		return only_path;
	}
	
	/*each algorithm need to implement- the action that makes the 
	decryption/encryption to each letter in the file*/
	abstract char doAction(char c);
	
	//function that contains the proper message after making the action
	abstract void finishAction() throws InvalidPathException;
	
	/*********** getter and setter functions**********/
	public void setType(String type)
	{
		this.type=type;
	}
	
	public MyReader getReader()
	{
		return fr;
	}
	
	public MyWriter getWriter()
	{
		return fw;
	}

	public int getKey()
	{
		return key;
	}
	
	public void setKey(int key)
	{
		this.key=key;
	}
}
