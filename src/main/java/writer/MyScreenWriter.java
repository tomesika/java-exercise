package writer;

import exceptions.InvalidPathException;

public class MyScreenWriter implements MyWriter
{

	public void write(String path, String content) throws InvalidPathException {
		System.out.println(content);
	}

}
