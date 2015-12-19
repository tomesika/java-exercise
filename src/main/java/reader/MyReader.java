package reader;

import exceptions.InvalidPathException;

public interface MyReader {
	public String read(String path) throws InvalidPathException;
}
