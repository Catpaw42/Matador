package game;

import gui.GUI;

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
	
	private GameController()
	{
		
	}
	
	public void advanceGame()
	{
		if (currentState == BEFORE_TURN_STATE)
		{
			currentState = INSIDE_TURN_STATE;
			//do player turn here
			turnCtrl.playerTurn(currentPlayer);
			gui.appendTextToTextArea("now in player turn");
			gui.setMainButtonText("End turn");
		}
		else if(currentState == INSIDE_TURN_STATE)
		{
			currentState = BEFORE_TURN_STATE;
			gui.setMainButtonText("Begin turn (Roll)");
			gui.appendTextToTextArea("Now outside of player turn");
		}
		
		
		
	}
}
