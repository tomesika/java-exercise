package encryptor1;

public class OneKey extends Key<Integer>
{
	private KeyCreator keyCreator=new KeyCreator();
	public OneKey()
	{
		setKey(keyCreator.createKey());
	}
	public OneKey(Integer key)
	{
		setKey(key);
	}
	public Key clone()
	{
		Key key=new OneKey(getKey().intValue());
		return key;
	}
}
