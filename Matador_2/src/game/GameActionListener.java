package game;

public class GameActionListener
{
	//---------------------------------------------------------
	//Singleton design pattern
	//---------------------------------------------------------
	private static GameActionListener instance = null;
	
	protected GameActionListener(){};
	
	public static GameActionListener getInstance()
	{
		if(instance == null)
			instance = new GameActionListener();
		return instance;
	}
	//---------------------------------------------------------
	
	
}
