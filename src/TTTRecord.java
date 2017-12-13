

// Set up TTTRecord class for further implementation 
public class TTTRecord {
	private String Recordconfig; // private String for string for gameboard
	private int Recordscore; //int score
	private int Recordlevel;// int level 
	
	public TTTRecord(String config, int score, int level)
	{
		//constructor for TTTRecord and store values in private variables
		Recordconfig = config;
		Recordscore = score;
		Recordlevel = level;
	}
	
	public String getConfiguration()
	{
		//return current TTTRecord's config
		return Recordconfig;
	}
	
	public int getScore()
	{
		//return current TTTRecord's score
		return Recordscore;
	
	}
	
	public int getLevel()
	{
		//return current TTTRecord's level
		return Recordlevel;
	}

}
