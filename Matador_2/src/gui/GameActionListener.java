package gui;

import game.GameController;
import game.Player;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class GameActionListener implements KeyEventDispatcher
{
	GUI gui = new GUI();
	public GameActionListener(){}

	//--------------------------------------------------------------
	//Methods to handle Action events
	//--------------------------------------------------------------
	public void buyButtonEvent()
	{

	}

	public void mainButtonEvent()
	{
		int oldState = GameController.getInstance().getCurrentState();
		GameController.getInstance().advanceGame();
		this.updateCars();
		this.updateDice(oldState);
		this.updatePlayers();
		if(GameController.getInstance().getCurrentState() == GameController.ROLL_STATE)
		{
			gui.setMainButtonText("Roll Dice");
		}
		if(GameController.getInstance().getCurrentState() == GameController.END_TURN_STATE)
		{
			gui.setMainButtonText("End Turn");
		}
	}

	public void sellButtonEvent()
	{

	}

	public void menuOneEvent()
	{

	}

	public void menuTwoEvent()
	{

	}

	//--------------------------------------------------------------
	//Method to handle key events
	//--------------------------------------------------------------

	//called when a keyEvent is fired. This is called once for KEY_PRESSED, and once for KEY_RELEASED
	//therefore they should be separated, example: if = KEY_RELEASED will only catch one of the events
	@Override
	public boolean dispatchKeyEvent(KeyEvent e)
	{
		if (e.getID() == KeyEvent.KEY_RELEASED)
		{
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				mainButtonEvent(); //redirects "return key" to main button
			}
		}
		return false;
	}

	//--------------------------------------------------------------
	//Method to handle window closing events
	//--------------------------------------------------------------
	public void windowClosingEvent()
	{
		System.exit(0);
	}

	//--------------------------------------------------------------
	//Methods to update the GUI after each "main button" event.
	//--------------------------------------------------------------
	
	private void updateCars()
	{
		LinkedList<Player> queue = GameController.getInstance().getPlayerQueue();
		for (int i = 0; i < queue.size(); i++)
		{
			gui.removeAllCars(queue.get(i).getName());
			gui.setCar(queue.get(i).getPosition(), queue.get(i).getName());
		}
		Player currentPlayer = GameController.getInstance().getCurentPlayer();
		gui.removeAllCars(currentPlayer.getName());
		gui.setCar(currentPlayer.getPosition(), currentPlayer.getName());
		gui.setCurrentPlayerName(currentPlayer.getName());
	}
	
	private void updateFields()
	{
		
	}
	
	private void updatePlayers()
	{
		LinkedList<Player> queue = GameController.getInstance().getPlayerQueue();
		for (int i = 0; i < queue.size(); i++)
		{
			gui.setPlayerMoney(queue.get(i).getName(), queue.get(i).getAccount().getBalance());
		}
		Player currentPlayer = GameController.getInstance().getCurentPlayer();
		gui.setPlayerMoney(currentPlayer.getName(), currentPlayer.getAccount().getBalance());
	}
	
	private void updateDice(int state)
	{
		if(state == GameController.ROLL_STATE)
			gui.setDice(GameController.getInstance().getDiceCup().getDiceFaceValues()[0], GameController.getInstance().getDiceCup().getDiceFaceValues()[1]);
	}
}
