import game.GameController;
import gui.GUI;

import javax.swing.SwingUtilities;


public class Main
{
	
	public static void main(String[] args)
	{	
		System.out.println("Der går noget galt her.");
		
		System.out.println("en nye linje");
		
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				//Do everything in here, this means everything is on the EDT-thread
				//if large tasks are required they should be dispatched in another thread
				//to not block the EDT (Blocked EDT = frozen GUI).
				GameController.getInstance();
				new GUI().create();
			}
		});
	}
}
