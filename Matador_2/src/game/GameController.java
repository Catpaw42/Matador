package game;

import java.awt.Color;
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
			instance = new GameController();
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
	private final int ROLL_STATE = 0;
	private final int END_TURN_STATE = 1;
	private int mainButtonState = 0;
	private Player currentPlayer;
	private PlayerTurnController turnCtrl = new PlayerTurnController(dice);
	private LinkedList<Player> playerQueue;
	private GameOptions options;

	private GameController()
	{
		//get data on players somehow
		Player[] players = new Player[4];
		players[0] = new Player("cat", Color.RED,2);
		players[1] = new Player("dog", Color.BLACK,1);
		players[2] = new Player("weasel", Color.GREEN,0);
		players[3] = new Player("ferret", Color.BLUE,3);
		playerQueue = new LinkedList<Player>();
		for (int i = 0; i < players.length; i++)
		{
			playerQueue.add(players[i]);
		}
		currentPlayer = playerQueue.remove();
	}

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
			
			if(dice.getTwoOfAKind() == 0 || currentPlayer.isBroke())
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
}
