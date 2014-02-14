package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StartMenuGUI extends JFrame implements ActionListener
{
	private JPanel panel = new JPanel(new FlowLayout());
	private JButton[] buttons = new JButton[4];
	
	
	public StartMenuGUI()
	{
		for (int i = 0; i < buttons.length; i++)
		{
			buttons[i] = new JButton();
			buttons[i].addActionListener(this);
			buttons[i].setPreferredSize(new Dimension(200,60));
			panel.add(buttons[i]);
		}
		
		buttons[0].setText("Start New Game");
		buttons[1].setText("Load Game");
		buttons[2].setText("Options");
		buttons[3].setText("Quit");
		this.setPreferredSize(new Dimension(250, 310));
		this.setTitle("Matador");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(panel);
		this.pack();
		
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (this.getWidth() / 2), (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (this.getHeight() / 2));
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == buttons[0])
		{
			
		}
		else if(e.getSource() == buttons[1])
		{
			
		}
		else if(e.getSource() == buttons[2])
		{
			
		}
		else if(e.getSource() == buttons[3])
		{
			System.out.println("Quit game");
			System.exit(0);
		}
	}
	
	public static void main(String[] args)
	{
		new StartMenuGUI();
	}
}
