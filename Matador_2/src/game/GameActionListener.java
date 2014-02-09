package game;

import gui.GUI;

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
		// TODO Auto-generated method stub
		
	}

	public void mainButtonEvent()
	{
		GameController.getInstance().advanceGame();
		
	}

	public void sellButtonEvent()
	{
		// TODO Auto-generated method stub

	}

	public void menuOneEvent()
	{
		// TODO Auto-generated method stub

	}

	public void menuTwoEvent()
	{
		// TODO Auto-generated method stub

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
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				//do stuff here
			}
		}
		return false;
	}

	//--------------------------------------------------------------
	//Method to handle drop events
	//--------------------------------------------------------------
	public void dropEvent(int source, String data)
	{
		System.out.println("Source: field " + source);
		System.out.println("data: " + data);

	}

	//--------------------------------------------------------------
	//Method to handle window closing events
	//--------------------------------------------------------------
	public void windowClosingEvent()
	{
		System.exit(0);
	}






}
