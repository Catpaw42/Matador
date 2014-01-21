package game;

import gui.GameGUI;

import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameActionListener implements ActionListener, KeyEventDispatcher
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(GameGUI.getInstance().getButton(0)))
		{
			//do stuff here
			GameGUI.getInstance().setDice(1, 2);
		}
	}

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
	
	
}
