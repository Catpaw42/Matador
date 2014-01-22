package game;

import gui.GameGUI;

import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameActionListener implements ActionListener, KeyEventDispatcher
{
	//called when an actionEvent is fired from the GUI, So far this is the Buttons
	@Override
	public void actionPerformed(ActionEvent e)
	{
		//figure out what button was pressed
		if (e.getSource().equals(GameGUI.getInstance().getButton(0)))
		{
			//do stuff here
			GameGUI.getInstance().setDice(1, 2);
		}
	}

	//called when a keyEvent is fired. This is called once for KEY_PRESSED, and once for KEY_RELEASED
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

	//custom method for handling drop-events
	public void dropEventDispatched(Object source, String data)
	{
		System.out.println("s: " + data.toString());
		
	}
	
	//custom method for handling windowClosed
	public void windowClosingEventDispatched()
	{
		System.exit(0);
	}
	
	
}
