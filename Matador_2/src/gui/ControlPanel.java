package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel
{
	protected JButton[] buttons;
	private JScrollPane scrollPane;
	protected JTextArea textArea;
	private JLabel nameLabel;

	protected ControlPanel()
	{
		//manage this panel
		this.setLayout(null);
		this.setSize(412, 682);
		this.setBackground(Color.ORANGE);

		//add a label to show the current players name
		nameLabel = new JLabel();
		this.add(nameLabel);
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(new Font(Font.SERIF, Font.BOLD, 26));
		nameLabel.setBounds(this.getWidth() * 4/20, this.getHeight() * 1/40, this.getWidth() * 12 / 20, this.getHeight() * 1/20);

		//add a textarea with a scrollpane to show output
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane = new JScrollPane(textArea);
		this.add(scrollPane);
		scrollPane.setBounds(this.getWidth() * 2 / 20, this.getHeight() * 2 / 20, this.getWidth() * 16 / 20, this.getHeight() * 9 / 20);

		//add buttons to this panel
		buttons = new JButton[3];
		buttons[0] = new JButton("START"); //main button
		buttons[1] = new JButton("<html><u>B</u>uy</html>"); //Buy
		buttons[2] = new JButton("<html><u>S</u>ell</html>");//Sell

		//add actionlistners to all the buttons
		for (int i = 0; i < buttons.length; i++)
		{
			buttons[i].addActionListener(new ActionListener()
			{
				GameActionListener g = new GameActionListener();
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if (e.getSource().equals(buttons[0]))
					{
						g.mainButtonEvent();
					}
					else if (e.getSource().equals(buttons[1]))
					{
						g.buyButtonEvent();
					}
					else if (e.getSource().equals(buttons[1])) // Hvorfor står der ikke 2 her?
					{
						g.sellButtonEvent();
					}
				}
			});
					this.add(buttons[i]);
		}
		buttons[0].setBounds(this.getWidth() * 2 / 20, this.getHeight() * 17 / 20, this.getWidth() * 16 / 20, this.getHeight() * 2 / 20);
		buttons[1].setBounds(this.getWidth() * 2 / 20, this.getHeight() * 31 / 40, this.getWidth() * 7 / 20, this.getHeight() * 1 / 20);
		buttons[2].setBounds(this.getWidth() * 11 / 20, this.getHeight() * 31 / 40, this.getWidth() * 7 / 20, this.getHeight() * 1 / 20);
	}
	
	protected void setNameLabelText(String text)
	{
		this.nameLabel.setText(text);
	}
	
	protected void setButtonText(int i, String text)
	{
		this.buttons[i].setText(text);
	}
}
