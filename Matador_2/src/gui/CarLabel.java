package gui;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class CarLabel extends JLabel
{
	private BufferedImage[] carImages;
	private static final int DEFAULTPRIMARYCOLOR = 0xffff0000;
	
	protected CarLabel()
	{
		carImages = new BufferedImage[4];
		setupCarIcons();
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	private void setupCarIcons()
	{
		try
		{
			//get the image from the folder
			File f = new File("pics/cars.png");
			BufferedImage image = ImageIO.read(f);
			
			//create an imageIcon for each face value of the Die
			for (int value = 0; value < carImages.length; value++)
			{
				//keep the same y-value
				int y = 0;
				//increment with the size of the cars in the picture in the x direction
				int x = 41 * value;
				//"cut out" a sub-image equivalent to each car-type, and store them in the array.
				this.carImages[value] = image.getSubimage(x, y, 40, 21);
			}
		} catch (IOException ex)
		{
			System.err.println("Error loading the car Icons");
			ex.printStackTrace();
		}
		
	}
	protected void setCar(int i, Color c)
	{
		if(i <0 || i >= carImages.length)
			throw new RuntimeException("the selected integer does not match a car");
		this.setIcon(new ImageIcon(setCarColor(carImages[i], c)));
	}
	
	private BufferedImage setCarColor(BufferedImage img, Color c)
	{
		for (int y = 0; y < img.getHeight(); y++)
        {
            for (int x = 0; x < img.getWidth(); x++)
            {
                if (img.getRGB(x, y) == DEFAULTPRIMARYCOLOR)
                    img.setRGB(x, y, c.getRGB());
            }
        }
        return img;
	}
	
	
}
