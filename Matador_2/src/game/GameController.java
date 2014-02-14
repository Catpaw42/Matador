package game;

import gui.GUI;

import java.util.LinkedList;

public class GameController {

	//-----------------------------------------------------
	//Singleton design pattern
	//-----------------------------------------------------
	private static GameController instance = null;

	public static GameController getInstance()
	{
		if(instance == null)
			instance = new GameController();
		return instance;
	}
	//-----------------------------------------------------

	private final int BEFORE_TURN_STATE = 0;
	private final int INSIDE_TURN_STATE = 1;
	private int currentState = 0;
	private Player currentPlayer;
	private PlayerTurnController turnCtrl = new PlayerTurnController();
	private GUI gui = new GUI();
	private LinkedList<Player> playerQueue;

	private GameController()
	{
		//get data on players somehow
		Player[] players = new Player[4];
		players[0] = new Player("cat");
		players[1] = new Player("dog");
		players[2] = new Player("weasel");
		players[3] = new Player("ferret");
		playerQueue = new LinkedList<Player>();
		for (int i = 0; i < players.length; i++)
		{
			playerQueue.add(players[i]);
		}
		currentPlayer = playerQueue.remove();
	}

	public void advanceGame()
	{
		//This is playerturn
		if (currentState == BEFORE_TURN_STATE)
		{
			currentState = INSIDE_TURN_STATE;
			gui.setMainButtonText("End turn");
			gui.appendTextToTextArea("now in " + currentPlayer.getName() + "'s turn");
			currentPlayer.setBroke(turnCtrl.playerTurn(currentPlayer));
		}
		//This runs when the player end his turn
		else if(currentState == INSIDE_TURN_STATE)
		{
			currentState = BEFORE_TURN_STATE;
			gui.setMainButtonText("Begin turn (Roll)");
			gui.appendTextToTextArea("Now outside of player turn");

			if (!currentPlayer.isBroke())
			{
				//add player back to the queue
				playerQueue.add(currentPlayer);
			}
			//pick next player from the queue
			currentPlayer = playerQueue.remove();
		}
	}
}
