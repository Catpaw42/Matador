package gui;

import game.GameActionListener;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel
{
	protected JButton[] buttons;
	private JScrollPane scrollPane;
	protected JTextArea textArea;
	

	protected ControlPanel()
	{
		buttons = new JButton[3];
		buttons[0] = new JButton("START");
		buttons[1] = new JButton();
		buttons[2] = new JButton();
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
		
		this.setLayout(null);
		this.setSize(412, 682);
		this.setBackground(Color.ORANGE);
		this.add(scrollPane);
		this.add(buttons[0]);
		for (int i = 0; i < buttons.length; i++)
		{
			buttons[i].addActionListener(new GameActionListener());
		}

		textArea.setEditable(false);

		scrollPane.setBounds(this.getWidth() * 2 / 20, this.getHeight() * 3 / 20, this.getWidth() * 16 / 20, this.getHeight() * 9 / 20);
		
		buttons[0].setBounds(this.getWidth() * 2 / 20, this.getHeight() * 16 / 20, this.getWidth() * 16 / 20, this.getHeight() * 2 / 20);
	}
	
}
