package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartMenuGUI
{
	private static final game.GameOptions options = new game.GameOptions();
	
	
	public StartMenuGUI()
	{
		
	}
	
	public static game.GameOptions startDialog()
	{
		final JDialog dialog = new JDialog(new JFrame(),true);
		
		JPanel panel = new JPanel(new FlowLayout());
		
		final JButton[] buttons = new JButton[4];
		
		for (int i = 0; i < buttons.length; i++)
		{
			buttons[i] = new JButton();
			buttons[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					//new game button
					if(e.getSource() == buttons[0])
					{
						//show new frame to set options
						showNewGameMenu();
					}
					//load game button
					else if(e.getSource() == buttons[1])
					{
						//for later, when we get to the sql work
					}
					//options button
					else if(e.getSource() == buttons[2])
					{
						//may be unnecessary
					}
					//quit button
					else if(e.getSource() == buttons[3])
					{
						System.out.println("Quit Game");
						System.exit(0);
					}
				}
			});
			buttons[i].setPreferredSize(new Dimension(200,60));
			panel.add(buttons[i]);
		}
		
		buttons[0].setText("Start New Game");
		buttons[1].setText("Load Game");
		buttons[2].setText("Options");
		buttons[3].setText("Quit");
		dialog.setPreferredSize(new Dimension(250, 310));
		dialog.setTitle("Matador");
		dialog.add(panel);
		dialog.pack();
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e)
			{
				dialog.setVisible(false);
				dialog.dispose();
			}
		});
		dialog.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (dialog.getWidth() / 2), (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (dialog.getHeight() / 2));
		dialog.setVisible(true);

		return options;
	}
	
	private static void showNewGameMenu()
	{
		final JDialog dialog = new JDialog(new JFrame(), true);
		JPanel panel = new JPanel();
		
		dialog.setPreferredSize(new Dimension(800,600));
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.out.println("This works!!");
				dialog.setVisible(false);
				dialog.dispose();
			}
		});
		dialog.pack();
		dialog.add(panel);
		
		dialog.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (dialog.getWidth() / 2), (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (dialog.getHeight() / 2));
		dialog.setVisible(true);
	}

	public static void main(String[] args)
	{
		game.GameOptions g = StartMenuGUI.startDialog();
	}
}
