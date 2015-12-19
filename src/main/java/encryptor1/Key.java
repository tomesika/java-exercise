package encryptor1;

public class Key<T> 
{
	private T key;
	
	public void setKey(T key)
	{
		this.key=key;
	}
	
	public T getKey()
	{
		return key;
	}
	public Key clone()
	{
		return new Key();
	}
}
