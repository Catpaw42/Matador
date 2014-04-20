package game;

import game.fields.Field;



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
	public static GameController createInstance(GameData options)
	{
		if(instance == null)
			instance = new GameController(options);
		else
			System.err.println("GameController instance already exists!!!");
		return instance;
	}
	//-----------------------------------------------------

	public static final int ROLL_STATE = 0;
	public static final int END_TURN_STATE = 1;
	public static final int GAME_OVER_STATE = 2;
	private int mainButtonState = 0;
	
	private DiceCup dice;
	private Board board;
	private Player currentPlayer;
	private MoveController moveController;
	private FieldController fieldController;
	private LinkedList<Player> playerQueue;

	private GameController(GameData data)
	{
		this.dice = data.getDice();
		this.board = new Board(data.getFields(), data.getCards());
		this.moveController = new MoveController(data.getDice());
		this.fieldController = new FieldController(board);
		
		this.playerQueue = new LinkedList<Player>();
		for (int i = 0; i < data.getPlayers().length; i++)
		{
			playerQueue.add(data.getPlayers()[i]);
		}

		this.currentPlayer = playerQueue.remove();

	}
	public void advanceGame()
	{

		if(mainButtonState != GAME_OVER_STATE) 
		{
			if(amountNotBroke() == 1)
			{
				mainButtonState = GAME_OVER_STATE;
			}
			
			if (mainButtonState == ROLL_STATE)
			{
				boolean playerBroke = moveController.playerTurn(currentPlayer);
				
				if(!playerBroke)
					playerBroke = fieldController.LandOnField(currentPlayer, currentPlayer.getPosition());
				
				currentPlayer.setBroke(playerBroke);

				if(!dice.isTwoOfAKind() || currentPlayer.isBroke() || currentPlayer.isInPrisson())
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
		// slut spillet her	
	}
	
	private int amountNotBroke()
	{
		int sum = 0;
		Player[] players = getAllPlayers();
		for (int i = 0; i <players.length; i++)
		{
			if(!players[i].isBroke())
				sum++;
		}
		return sum;
	}
	
	public Player getCurentPlayer()
	{
		return currentPlayer;
	}
	
	public Player getWinner()
	{
		Player[] players = getAllPlayers();
		int Avalue = 0;
		for (int i = 0; i <players.length; i++)
		{
			if(!players[i].isBroke())
				Avalue = i;
		}
		return players[Avalue];
	}
	
	public Player[] getAllPlayers()
	{
		Player[] players = new Player[this.playerQueue.size() + 1];
	
		for (int i = 0; i < this.playerQueue.size(); i++)
		{
			players[i] = playerQueue.get(i);
		}
		players[this.playerQueue.size()] = currentPlayer;
		
		return players;
	}
	
	public int getCurrentState()
	{
		return mainButtonState;
	}
	
	public DiceCup getDiceCup()
	{
		return this.dice;
	}
	
	public Field[] getFields()
	{
		return this.board.getAllFields();
	}
}
