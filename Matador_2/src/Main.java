import game.GameController;
import game.GameOptions;
import gui.GUI;

import javax.swing.SwingUtilities;

import startmenugui.StartMenuDialog;

public class Main
{
	public static void main(String[] args)
	{	
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				//Do everything in here, this means everything is on the EDT-thread
				//if large tasks are required they should be dispatched in another thread
				//to not block the EDT (Blocked EDT = frozen GUI).
				GameOptions options = new StartMenuDialog().startDialog();
				GameController.createInstance(options);
				new GUI().create();
			}
		});
	}
}
