package gui;

import game.GameController;
import game.Player;
import game.fields.Field;
import game.fields.Ownable;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

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
		this.updateFields();
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

	public void saveGameEvent()
	{
		System.out.println("Save game event");
	}

	public void loadGameEvent()
	{
		System.out.println("Load game event");
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
		Player[] players = GameController.getInstance().getAllPlayers();
		for (int i = 0; i < players.length; i++)
		{
			gui.removeAllCars(players[i].getName());
			gui.setCar(players[i].getPosition(), players[i].getName());
		}
		
		gui.setCurrentPlayerName(GameController.getInstance().getCurentPlayer().getName());
	}
	
	private void updateFields()
	{
		Field[] fields = GameController.getInstance().getFields();
		for (int i = 0; i < fields.length; i++)
		{
			if(fields[i] instanceof Ownable && ((Ownable)fields[i]).getOwner() != null)
				gui.setFieldOwner(i + 1, ((Ownable) fields[i]).getOwner().getName());
		}
	}
	
	private void updatePlayers()
	{
		Player[] players = GameController.getInstance().getAllPlayers();
		for (int i = 0; i < players.length; i++)
		{
			gui.setPlayerMoney(players[i].getName(), players[i].getAccount().getBalance());
		}
	}
	
	private void updateDice(int state)
	{
		if(state == GameController.ROLL_STATE)
			gui.setDice(GameController.getInstance().getDiceCup().getDiceFaceValues()[0], GameController.getInstance().getDiceCup().getDiceFaceValues()[1]);
	}
}
