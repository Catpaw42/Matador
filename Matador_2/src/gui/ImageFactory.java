package gui;

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

}
