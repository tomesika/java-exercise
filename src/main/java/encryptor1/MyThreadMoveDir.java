package encryptor1;

import java.io.File;

import exceptions.InvalidPathException;
import reader.MyFileReader;
import reader.MyReader;
import writer.MyFileWriter;
import writer.MyWriter;

public class MyThreadMoveDir implements Runnable 
{
	File fileEntry;
	String actionType;
	String dirPath;
	MyReader fr=new MyFileReader();
	MyWriter fw=new MyFileWriter();

   public MyThreadMoveDir(File fileEntry,String actionType,String dirPath) 
   {
       this.fileEntry=fileEntry;
       this.actionType=actionType;
       this.dirPath=dirPath;
   }

   private String getSimpleName(String name) 
	{
		String[] seperatedName=name.split("[_.]");
		String str="";
		int size=seperatedName.length;
		for(int i=0;i<size-2;i++)
		{
			str+=seperatedName[i];
		}
		str+="."+seperatedName[size-1];
		return str;
	}
   
   public void run() 
   {
	   String name=fileEntry.getName();
       
    	String content;
		try 
		{
			content = fr.read(dirPath+"\\"+name);
    	   String simpleName=getSimpleName(name);
    	  
			fw.write(dirPath+"\\"+actionType+"\\"+simpleName, content);
		} catch (InvalidPathException e) 
		{
			e.printStackTrace();
		}
    	   fileEntry.delete();
   }
}