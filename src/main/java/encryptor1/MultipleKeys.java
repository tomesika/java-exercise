package encryptor1;

import java.util.ArrayList;

public class MultipleKeys extends Key<ArrayList<Integer>>
{
	private KeyCreator keyCreator=new KeyCreator();
	public MultipleKeys(int numOfKeys)
	{
		ArrayList<Integer> keys=new ArrayList<Integer>();
		for(int i=0;i<numOfKeys;i++)
		{
			keys.add(keyCreator.createKey());
		}
		setKey(keys);
	}
	public MultipleKeys(ArrayList<Integer> key)
	{
		setKey(key);
	}
	
	public Key clone()
	{
		ArrayList<Integer> ls=new ArrayList<Integer>();
		ls.add(getKey().get(0).intValue());
		ls.add(getKey().get(1).intValue());
		Key key=new MultipleKeys(ls);
		return key;
	}
}
