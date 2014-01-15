package game;

import gui.GameGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameActionListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(GameGUI.getInstance().getButton(0)))
		{
			GameGUI.getInstance().setDice(1, 2);
			GameGUI.getInstance().appendText("" + GameGUI.getInstance().getSize());
		}
	}
	
	
}
