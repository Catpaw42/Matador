package gui;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class CarLabel extends JLabel
{
	private BufferedImage[] carImages;
	//the default color of the images(red) used to search and replace
	private static final int DEFAULTPRIMARYCOLOR = 0xffff0000;
	
	protected CarLabel()
	{
		carImages = ImageFactory.setupCarIcons();
		this.setVisible(false);
	}
	
	protected void setCar(int i, Color c)
	{
		if(i <0 || i >= carImages.length)
			throw new RuntimeException("the selected integer does not match a car");
		this.setIcon(new ImageIcon(ImageFactory.replaceColor(carImages[i], c, DEFAULTPRIMARYCOLOR)));
	}

}
