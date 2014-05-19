package gui;

import game.Board;
import game.GameController;
import game.Player;
import game.fields.Field;
import game.fields.Ownable;
import game.fields.Street;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import dbacces.DBCommunication;

public class GameActionListener implements KeyEventDispatcher
{
	GUI gui = new GUI();
	public GameActionListener(){}

	//--------------------------------------------------------------
	//Methods to handle Action events
	//--------------------------------------------------------------
	public void buyHouseButtonEvent()
	{
		Ownable[] fields = Board.getFieldsByPlayer(GameController.getInstance().getCurentPlayer());
		
		LinkedList<Street> buildableStreets = new LinkedList<Street>();
		for (int i = 0; i < fields.length; i++)
		{
			if(fields[i] instanceof Street)
			{
				if(((Street)fields[i]).getSeriesMultiplier() == 2);
					buildableStreets.add((Street)fields[i]);
			}
		}
		if(buildableStreets.size() > 0)
		{
			String[] options = new String[buildableStreets.size()];
			for (int i = 0; i < buildableStreets.size(); i++)
			{
				options[i] = buildableStreets.get(i).getName();
			}
			
			String choise = gui.getUserSelection(options, "What field would you like to build a house on?", "Buy House");
			
			for (int i = 0; i < buildableStreets.size(); i++)
			{
				if(buildableStreets.get(i).getName().equals(choise))
				{
					try
					{
						//withdraw cash
						
						buildableStreets.get(i).setHouses(buildableStreets.get(i).getHouses() + 1);
					}
					catch (Exception e)
					{
						//print not enough money
					}
					
					//update the GUI
					switch(buildableStreets.get(i).getHouses())
					{
					case 0:
						break;
					case 1:
						gui.setFieldPicture(buildableStreets.get(i).getFieldNumber(), FieldPanel.Builder.HOUSE1);
						break;

					case 2:
						gui.setFieldPicture(buildableStreets.get(i).getFieldNumber(), FieldPanel.Builder.HOUSE2);
						break;

					case 3:
						gui.setFieldPicture(buildableStreets.get(i).getFieldNumber(), FieldPanel.Builder.HOUSE3);
						break;

					case 4:
						gui.setFieldPicture(buildableStreets.get(i).getFieldNumber(), FieldPanel.Builder.HOUSE4);
						break;

					case 5:
						gui.setFieldPicture(buildableStreets.get(i).getFieldNumber(), FieldPanel.Builder.HOTEL);
						break;
					}
				}
			}
		}
		else
			gui.appendTextToTextArea("You cant build any houses");
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
		if(GameController.getInstance().getCurrentState() == GameController.GAME_OVER_STATE)
		{
			gui.setMainButtonText("Game over " + GameController.getInstance().getWinner().getName() + " has won");
		}
	}

	public void sellHouseButtonEvent()
	{
		System.out.println("Sell button was pressed");
	}

	public void loadGameEvent()
	{
		System.out.println("Load game event");
	}
	
	public void tradeButtonEvent()
	{
 		
	}

	public void saveGameButtonEvent()
	{
		System.out.println("Save game event");
		DBCommunication.saveGame();
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
			if(fields[i] instanceof Ownable)
				gui.setFieldPrice(i + 1, ((Ownable) fields[i]).getPrice());
			if(fields[i] instanceof Ownable /*&& ((Ownable)fields[i]).getOwner() != null*/ && ((Ownable)fields[i]).getRent() != 0)
				gui.setFieldRent(i + 1, ( (Ownable) fields[i]).getRent());
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
