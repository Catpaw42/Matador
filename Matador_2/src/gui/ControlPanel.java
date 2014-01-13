package gui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel
{
	private JButton[] buttons;
	

	protected ControlPanel()
	{
		buttons = new JButton[3];
		buttons[0] = new JButton("START");
		buttons[1] = new JButton();
		buttons[2] = new JButton();

		// manage control panel
		this.setLayout(null);
		this.setBackground(Color.LIGHT_GRAY);
		this.add(buttons[0]);

	}

	protected void updatePositions()
	{
		buttons[0].setBounds(this.getWidth() * 2 / 20, this.getHeight() * 16 / 20, this.getWidth() * 16 / 20, this.getHeight() * 2 / 20);
	}
	
	
}
