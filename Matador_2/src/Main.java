import gui.GameGUI;

import javax.swing.SwingUtilities;


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
				GameGUI.getInstance();
			}
		});
	}
}
