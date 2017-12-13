import java.util.Dictionary;


public class TTTDictionary implements TTTDictionaryADT {
	// private variables for TTTDictionary
	// TTTDictionary is a array contains TTTRecord Linearnode
	private linearnode<TTTRecord>[] table;
	private int N;
	private int number;
	
	
	
	private int hash(String word, int tablesize){
		// private hash function will produce an int value (index) based on input string and tale size;
		int hashValue = 0; 
		int x = 10;
		for (int i = 0; i < word.length(); i++){
			hashValue = ( x * hashValue + word.charAt(i))% tablesize;
		}
		return hashValue;
		
	}

	public  TTTDictionary(int size){
		// constructor for TTTDictionary and set all the elements in the array equal to Null;
		 N = size;
		 table = new linearnode[size];
		 number = 0;
		 for(int i = 0;i < N; i++){ // set all elements to null
			 table[i] = null;
		 }
	}
	public int put (TTTRecord record) throws DuplicatedKeyException{
		//retrieve TTTRecord string variable value and use hash function to produce its index
		int index = hash(record.getConfiguration(),N);
		//check if the position in the table is null or not
		if( table[index] == null)
		{   //if it is null, then current position is equal to this TTTRecrod linear node
			linearnode node = new linearnode(record);
			table[index] = node;
			//count +1
			number = number + 1;
			//return 0 since there is no collision
			return 0; 
		}
		else if (table[index] != null)
		{
		    // if it is not null, then insert this node into the linklist with same index	
			//set up 2 pointers to go through the linked list 
			linearnode<TTTRecord> p = table[index];
			linearnode<TTTRecord> lastp = table[index];
			while ( p!= null)
			{   //if p is not null, p = p.next
				//last p is p 
				if (p.getElement().getConfiguration()== record.getConfiguration())
				{  //if there is an element with same string value return duplicatedkeyexception
				   	
					throw new DuplicatedKeyException();
					
				}
				lastp = p;
				p = p.getNext();
				
			}
			//find the node with node.next = null; then insert it ,count ++
			linearnode node = new linearnode(record);
			lastp.setNext(node);
			number++;
			
		}
		//return 1 since there is a collision
		
		return 1; 	
	}


	public void remove(String config)throws InexistentKeyException{
		//remove the element with same string value
		int index = hash(config,N);
		//find corresponding index 
		if (table[index] == null){
			//if the element is not found, then return inexisentkeyexception()
			throw new InexistentKeyException();
		}
		else if (table[index].getElement().getConfiguration().equals(config)){
			//if the first element is the node we want to find, then check the node.next
			//if it is not null; check node.next is null or not.
			if(table[index].getNext()!= null){
				//not null, set next node as the head of list
				table[index] = table[index].getNext();
				
			}
			else{
				//else set position equal to null
				table[index] = null;
			}
			//count -1
			number--;
		}
		
		else  
		{   // go through entire list to find the right node
			//need two pointer, same as put
			linearnode<TTTRecord> p = table[index];
			linearnode<TTTRecord> lastp = table[index];
			while(p.getElement().getConfiguration()!= config){
				//go through the list 
				lastp = p;
				p = p.getNext();
			}
			//let previous node connect the rest of list
			lastp.setNext(p.getNext());
			number--;
			
		}
		 
	}
	
	public TTTRecord get(String config){
		//return TTTRecord with same String value
		int index = hash(config,N);
		// find its index with hash funciton
		if ( table[index] != null)
		{
			//if the table[index] is not null
			//set up a pointer to go through list
			linearnode<TTTRecord> p = table[index];
			while ( p != null ){
			    if( p.getElement().getConfiguration().equals(config))  
			    {   // find it then return it
			    	return p.getElement();
			    }
			    // else go next node
				p = p.getNext();
			      
			}
			
		}
		//if we can't find it return null;
		return null;		
	}
	
	public int numElements()
	{
		//return the number of elements
		return number;
	}


}
