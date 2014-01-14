package game;

import gui.GameGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameActionListener implements ActionListener
{
	//---------------------------------------------------------
	//Singleton design pattern
	//---------------------------------------------------------
	private static GameActionListener instance = null;
	
	protected GameActionListener(){};
	
	public static GameActionListener getInstance()
	{
		if(instance == null)
			instance = new GameActionListener();
		return instance;
	}
	//---------------------------------------------------------

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(GameGUI.getInstance().getButton(0)))
		{
			GameGUI.getInstance().appendText("lalalal");
		}
	}
	
	
}
