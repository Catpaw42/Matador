package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class DiceLabel extends JLabel
{
	private ImageIcon[] diceIcons = new ImageIcon[6]; // array for the Dice face values.
	private int rotation = 0;
	
	protected DiceLabel()
	{
		setupDiceIcons();
		this.setVisible(false);
	}

	//generate the Icons for the Dice
	private void setupDiceIcons()
	{
		try
		{
			//get the image from the folder
			File f = new File("pics/Dice.png");
			BufferedImage image = ImageIO.read(f);
			
			//create an imageIcon for each face value of the Die
			for (int value = 0; value < 6; value++)
			{
				//keep the same x-value
				int x = 0;
				//increment with the size of the Dice in the picture in the y direction
				int y = 55 * value;
				//"cut out" a sub-image equivalent to each die-value, and store them in the array.
				this.diceIcons[value] = new ImageIcon(image.getSubimage(x, y, 54, 54));
			}
		} catch (IOException ex)
		{
			System.err.println("Error loading the dice Icons");
			ex.printStackTrace();
		}
	}
	/**
	 * 
	 * @param value dieFaceValue between 1 and 6 (inclusive)
	 */
	protected void setDice(int value){
		if(value < 1 || value > 6)
			throw new IndexOutOfBoundsException();
		Random r = new Random();
		this.rotation = r.nextInt(360);
		this.setIcon(diceIcons[value-1]);
		this.repaint();
		//TODO: fix to certain rotations only? to avoid "tearing" the die-Image
	}
	
	//overrides the paintComponent to enable rotating the dice
	@Override
	protected void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		if(this.getIcon() != null)
		{
			g2d.rotate(Math.toRadians(this.rotation), this.getIcon().getIconWidth()/2 + 5, this.getIcon().getIconWidth()/2 + 5);
		}
		super.paintComponent(g);
	}
}
