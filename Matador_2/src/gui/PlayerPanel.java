package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel
{
	protected CarLabel car;
	protected JLabel textLabel;
	private String name;
	private String money;
	
	public PlayerPanel()
	{
		this.setSize(120, 40);
		this.setLayout(null);
		this.setOpaque(false);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		car = new CarLabel();
		car.setCar(3, Color.YELLOW);
		car.setBounds(0, this.getHeight() / 2 - 10, 40, 21);
		textLabel = new JLabel("<html>" + "Magnus" + "<br>" + "000000" + "</html>");
		textLabel.setHorizontalAlignment(SwingConstants.CENTER);
		textLabel.setBounds(40, 0, 80, 40);
		this.add(car);
		this.add(textLabel);
	}
	
	protected void setPlayerName(String name)
	{
		this.name = name;
		textLabel.setText("<html>" + this.name + "<br>" + this.money + "</html>");
	}
	
	protected void setPlayerMoney(String money)
	{
		this.money = money;
		textLabel.setText("<html>" + this.name + "<br>" + this.money + "</html>");
	}
}
