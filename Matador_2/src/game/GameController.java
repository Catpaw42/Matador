package game;

import game.chance_cards.ChanceCard;
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
	private Field[] fields;
	private ChanceCard[] cards;
	private Player currentPlayer;
	private PlayerTurnController turnCtrl;
	private LinkedList<Player> playerQueue;

	private GameController(GameData data)
	{
		this.dice = data.getDice();
		this.turnCtrl = new PlayerTurnController(data.getDice(), data.getFields(), data.getCards());
		this.fields = data.getFields();
		this.cards = data.getCards();
		
		playerQueue = new LinkedList<Player>();
		for (int i = 0; i < data.getPlayers().length; i++)
		{
			playerQueue.add(data.getPlayers()[i]);
		}
		currentPlayer = playerQueue.remove();
	}
	public void advanceGame()
	{

		if(mainButtonState != GAME_OVER_STATE) 
		{
			if(amountNotBroke() == 1) // virker ikke.. Tror ikke vores spillere bliver sat ordenligt broke.
			{
				mainButtonState = GAME_OVER_STATE;
			}
			
			if (mainButtonState == ROLL_STATE)
			{
				boolean playerBroke = turnCtrl.playerTurn(currentPlayer);
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
	
	public LinkedList<Player> getPlayerQueue()
	{
		return this.playerQueue;
	}

	public Player getCurentPlayer()
	{
		return currentPlayer;
	}
	
	public Player[] getAllPlayers()
	{
		Player[] players = new Player[this.playerQueue.size() + 1];
		
		players[0] = currentPlayer;
	
		for (int i = 1; i < this.playerQueue.size(); i++)
		{
			players[i] = playerQueue.get(i);
		}
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
		return fields;
	}
	
	public ChanceCard[] getChanceCards()
	{
		return this.cards;
	}
}
