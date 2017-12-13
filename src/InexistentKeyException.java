
public class InexistentKeyException extends Exception {
	
	public  InexistentKeyException ()
	{
		//exception class for remove function in TTTDictionary
		super("This Config is not found in the hash table");
		
	}

}
