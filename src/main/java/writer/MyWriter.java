package writer;

import exceptions.InvalidPathException;

public interface MyWriter
{
	public void write(String path,String content) throws InvalidPathException;
}
