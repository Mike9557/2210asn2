//this class implement methods need to play TicTacToe
//Min Zhu
//250852969
public class BlockedTicTacToe {
	
	//private instance variable
	//o represents computers'play
	//x represents human'play
	//b represents a block
	//empty represents a empty space
	//board_size is the given size for the board
	//line is number of adjacent symbol together to win the game
	private int board_size;
	private int line;
	private int max_levels;
	private char [][] gameboard;
	private TTTDictionary dict;
	private final char COMPUTER = 'o';
	private final char HUMAN = 'x';
	private final char BLOCK = 'b';
	private final char EMPTY = ' ';
	
	//initialize private instance variable
	public BlockedTicTacToe (int board_size, int line, int max_levels) {
		this.board_size = board_size;
		this.line = line;
		this.max_levels = max_levels;
		
		//create a new empty game board
		gameboard = new char[board_size][board_size];
		for(int row=0; row<board_size; row++) {
			for (int col=0; col<board_size; col++) {
				gameboard [row][col] = ' ';
			}
		}
	}
	
	//return a dictionary with given board size
	public TTTDictionary createDictionary() {
		dict = new TTTDictionary(board_size);
		return dict;
	}
	
	
	//to check if the configuration is already exists in the dictionary
	//if it's already in the dictionary, return the score of it
	//return -1 if not in the dictionary
	public int repeatedConfig(TTTDictionary configurations) {
		String stringName = "";
		for(int row=0; row<board_size; row++) {
			for(int col=0; col<board_size; col++) {
				stringName = gameboard[row][col] + stringName;
			}
		}
		if(configurations.get(stringName) == null) {
			return -1;
		}
		else {
			return configurations.get(stringName).getScore();
		}
	}
	
	
	//first change the configuration to a string
	//then put the string with its score and level into the dictionary
	//if there already exists the string throw exception
	public void insertConfig (TTTDictionary configurations, int score, int level) {
		String stringName = "";
		for(int row=0; row<board_size; row++) {
			for(int col=0; col<board_size; col++) {
				stringName = gameboard[row][col] + stringName;
			}
		}
		try {
			configurations.put(new TTTRecord(stringName, score, level));
		}
		catch(DuplicatedKeyException e){
			System.out.println("Duplicate Key Exception inserting");
		}
	}
	
	//store the given symbol into given row and col
	public void storePlay(int row, int col, char symbol) {
		gameboard[row][col] = symbol;
	}

	//check if given row and col is empty or not
	//return true if it is empty
	//and vice versa
	public boolean squareIsEmpty(int row, int col) {
		if (gameboard[row][col] == EMPTY) {
			return true;
		}
		return false;
	}

	//check which player wins the game
	//check all the possibility horizontally, vertically, diagonally
	//return true if the number of symbol reach in line number
	//and false if it does not reach the in line number
	public boolean wins (char symbol) {
		boolean flag = false;
		int n = 0;
		
		//find the number of adjacent occurrence of symbol in rows
		for(int row = 0; row < board_size; row ++){
			//set the ner to 0
			n = 0;
			for(int col = 0; col < board_size; col ++) {
				if(gameboard[row][col] == symbol) {
					n ++;
					if(n == line){
						flag = true;
						break;
						//stop finding and exit the for loop if the number of in line symbol is reached
					}
				}
				else {
					//if there is an opposite symbol occur
					//reset the ner back to 0
					n = 0;
				}
			}
		}
		
		//find the number of adjacent occurrence of symbol in columns
			for(int col = 0; col < board_size; col ++){
				n = 0;
				for(int row = 0; row < board_size; row ++) {
					if(gameboard[row][col] == symbol) {
						n ++;
						if(n == line){
							flag = true;
							break;
						}
					}
					else {
						n = 0;
					}
				}
			}
		
		//find the number of adjacent occurrence of symbol in diagonal
			
			//right down
			for(int col = 0; col < board_size; col ++){
			
				for(int row = 0; row < board_size; row ++) {
					
				   if (gameboard[col][row] == symbol){
					   int win = 0;
					   int recordedcol = col;
					   int recordedrow = row;
					   
					   while ((recordedcol < board_size)&& (recordedrow < board_size) ){
						   if (gameboard[recordedcol][recordedrow] == symbol){
							   win = win + 1;
						   }
						   else{
							   win = 0;
						   }
						   if ( win == line){
							   return true;
						   }
						   else{
							   recordedcol ++;
							   recordedrow ++;
						   }
						   
						   
					   }
				   }
				}
				}
			//right up
			for (int col = 0; col < board_size; col++){
				for (int row = 0; row < board_size;row++){
					if (gameboard[col][row]==symbol){
						int win = 0;
						int recordedcol = col;
						int recordedrow = row;
						while ((recordedcol < board_size)&&(recordedrow >= 0)){
							if(gameboard[recordedcol][recordedrow]==symbol){
								win = win + 1;
							}
							else{
								win = 0;
							}
							if(win ==line){
								return true;
							}
							else{
								recordedcol++;
								recordedrow--;
							}
						}
					}
				}
			}
	
			
			
			
			
			
			return flag;
		
		
		
	}
	
	
	//check if the game is a draw or not
	//first check if human wins, if not, return false
	//second check if computer wins, if not, return false
	//if there is no empty space inside the game board,return false
	public boolean isDraw() {
		boolean flag = true;
		if(wins(HUMAN)) {
			flag = false;
		}
		if(wins(COMPUTER)) {
			flag = false;
		}
		else {
			for(int row=0; row<board_size; row++) {
				for(int col=0; col<board_size; col++) {
					if(gameboard[row][col] == ' ') {
						flag = false;
					}
				}
			}
		}
		return flag;
	}
	
	
	//evaluate the result of the game
	//return 3 if computer wins
	//return 0 if human wins
	//return 1 if no one wins
	//return 2 if its a draw
	public int evalBoard() {
		if (wins(COMPUTER)){
			return 3;
		}
		else if (wins(HUMAN)){
			return 0;
		}
		else if (isDraw()){
			return 2;
		}
		else 
			return 1;
	}
	
	
}
