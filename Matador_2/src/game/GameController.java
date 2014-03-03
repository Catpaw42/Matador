package game;

import java.util.LinkedList;

public class GameController
{

	//-----------------------------------------------------
	//Singleton design pattern
	//-----------------------------------------------------
	private static GameController instance = null;

	/**
	 * Used to return the GameController object.
	 * @return The Gamecontroller Instance.
	 */
	public static GameController getInstance()
	{
		if(instance == null)
			System.err.println("Error no instance exists");
		return instance;
	}
	/**
	 * Creates a Gamecontroller with a specific options object, this MUST be called
	 * before any calls to getInstance to have any effect.
	 * @param options A GameOptions object containing startup data for the game.
	 * @return The Gamecontroller Instance.
	 */
	public static GameController createInstance(GameOptions options)
	{
		if(instance == null)
			instance = new GameController(options);
		else
			System.err.println("GameController instance already exists!!!");
		return instance;
	}
	//-----------------------------------------------------

	private DiceCup dice = new DiceCup();
	public static final int ROLL_STATE = 0;
	public static final int END_TURN_STATE = 1;
	private int mainButtonState = 0;
	private Player currentPlayer;
	private PlayerTurnController turnCtrl = new PlayerTurnController(dice);
	private LinkedList<Player> playerQueue;
	private GameOptions options;

	private GameController(GameOptions options)
	{
		this.options = options;
		
		playerQueue = new LinkedList<Player>();
		for (int i = 0; i < options.getPlayers().length; i++)
		{
			playerQueue.add(options.getPlayers()[i]);
		}
		currentPlayer = playerQueue.remove();
	}
	public void advanceGame()
	{
		if (mainButtonState == ROLL_STATE)
		{
			currentPlayer.setBroke(turnCtrl.playerTurn(currentPlayer));
			
			if(dice.getTwoOfAKind() == 0 || currentPlayer.isBroke() || currentPlayer.isInPrisson())
				mainButtonState = END_TURN_STATE;
		}
		//This runs when the player end his turn
		else if(mainButtonState == END_TURN_STATE)
		{
			if (!currentPlayer.isBroke())
			{
				//add player back to the queue
				playerQueue.add(currentPlayer);
			}
			//pick next player from the queue
			currentPlayer = playerQueue.remove();
			currentPlayer.setTwoOfAKindRollCount(0);
			mainButtonState = ROLL_STATE;
		}
	}
	public GameOptions getOptions()
	{
		return this.options;
	}
	public Player getCurentPlayer()
	{
		return currentPlayer;
	}
	public int getCurrentState()
	{
		return mainButtonState;
	}
}
