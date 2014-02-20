package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageFactory
{
	//private constructor, this is a static only class
	private ImageFactory(){}
	/**
	 * helper method, used to generate a Image from a string path
	 * @param img a path to the imageFile
	 * @return a buffered image, created from the specified path.
	 */
	public static BufferedImage CreateImage(String img)
	{
		try
		{
			//get the image from the folder
			File f = new File(img);
			if(!f.exists())
				throw new FileNotFoundException("Can't locate image");

			BufferedImage image = ImageIO.read(f);

			return image;
		} catch (IOException ex)
		{
			System.err.println("Error loading the image");
			ex.printStackTrace();
		}
		//should not be reachable
		return null;
	}

	//custom method to create the images of the cars
	public static BufferedImage[] setupCarIcons()
	{
		BufferedImage[] BIArray = new BufferedImage[4];
		try
		{
			//get the image from the folder
			File f = new File("pics/cars.png");
			BufferedImage image = ImageIO.read(f);

			//create an imageIcon for each face value of the Die
			for (int value = 0; value < 4; value++)
			{
				//keep the same y-value
				int y = 0;
				//increment with the size of the cars in the picture in the x direction
				int x = 41 * value;
				//"cut out" a sub-image equivalent to each car-type, and store them in the array.
				BIArray[value] = image.getSubimage(x, y, 40, 21);
			}
		} 
		catch (IOException ex)
		{
			System.err.println("Error loading the car Icons");
			ex.printStackTrace();
		}
		return BIArray;
	}

	//method used to paint the cars in different colors, by replacing the Default_color
	public static BufferedImage replaceColor(BufferedImage img, Color c, int primaryColorHexCode)
	{
		BufferedImage temp = deepCopy(img);
		for (int y = 0; y < temp.getHeight(); y++)
		{
			for (int x = 0; x < temp.getWidth(); x++)
			{
				if (temp.getRGB(x, y) == primaryColorHexCode)
					temp.setRGB(x, y, c.getRGB());
			}
		}
		return temp;
	}
	//creates a deep copy, not just a new reference, of a given image
	private static BufferedImage deepCopy(BufferedImage buffImg) {
		//new blank image
		BufferedImage copy = new BufferedImage(buffImg.getWidth(),buffImg.getHeight(), buffImg.getType());
		//get a graphics object for the copy
		Graphics g = copy.createGraphics();
		//draw the original to the copy
		g.drawImage(buffImg, 0, 0, null);
		return copy;
	}
}
