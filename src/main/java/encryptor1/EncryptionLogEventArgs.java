package encryptor1;

public class EncryptionLogEventArgs 
{
	private long time;
	private String nameOfFile;
	private String algorithm;
	private String newName;
	
	public EncryptionLogEventArgs(long time1,long time2,String algoName,
			String path,String type)
	{
		this.setTime(time2-time1);
		this.setAlgorithm(algoName);
		
	}
	
	public void calculateFilesNames(String path,String type)
	{
		String[] seperatedPath=path.split("\\\\");
		int len=seperatedPath.length;
		this.setNameOfFile(seperatedPath[len-1]);
		String[] seperatedName=(seperatedPath[len-1]).split("\\.");	
		this.setNewName(seperatedName[0]+"_"+type+"."+seperatedName[1]);
	}

	public long getTime() 
	{
		return time;
	}

	public void setTime(long time) 
	{
		this.time = time;
	}

	public String getNameOfFile() 
	{
		return nameOfFile;
	}

	public void setNameOfFile(String nameOfFile) 
	{
		this.nameOfFile = nameOfFile;
	}

	public String getAlgorithm() 
	{
		return algorithm;
	}

	public void setAlgorithm(String algorithm) 
	{
		this.algorithm = algorithm;
	}

	public String getNewName() 
	{
		return newName;
	}

	public void setNewName(String newName) 
	{
		this.newName = newName;
	}
	
	public boolean equals(EncryptionLogEventArgs args)
	{
		if((args.getTime()==time) && (args.getAlgorithm().equals(algorithm)) &&
			(args.getNameOfFile().equals(nameOfFile)) &&
			(args.getNewName().equals(newName)))
			return true;
		return false;
	}
	
	public int hashCode()
	{
		return (int) (time+algorithm.hashCode()+nameOfFile.hashCode()+
				newName.hashCode());
	}
	
}
