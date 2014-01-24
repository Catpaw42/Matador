package gui;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class GUI
{
	
	private static int numberOfActivePlayers = 0;
	public GUI(){}

	public void create()
	{
		GameGUI.getInstance();
	}

	public void create(FieldPanel[] fields)
	{
		GameGUI.createCustomGUI(fields);
	}
	
	private BufferedImage CreateImage(String img)
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

	public void setDice(int i, int j)
	{
		GameGUI.getInstance().setDice(i, j);
	}
	
	public boolean addPlayer(String playerName, int carType, Color carColor)
	{
		if (numberOfActivePlayers >= 6)
			return false;
		if (carType > 3)
			return false;
		GameGUI.getInstance().addPlayer(playerName, carType, carColor);
		numberOfActivePlayers ++;
		return true;
	}
	
	public boolean removePlayer(String playerName)
	{
		if(GameGUI.getInstance().removePlayer(playerName))
		{
			numberOfActivePlayers--;
			return true;
		}
		return false;
	}
	
	public void setDisplayedText()
	{
		// TODO not yet done
	}
	
	public String getUserSelection(String[] options, String message, String title)
	{
		
		String selection = (String) JOptionPane.showInputDialog(new JFrame(),
				message, title, JOptionPane.PLAIN_MESSAGE, null,
				options, options[0]);
		return selection;
	}
	
	public String getUserSelection(String[] options, String message, String title, String imagePath)
	{
		return (String) JOptionPane.showInputDialog(new JFrame(),
				message, title, JOptionPane.QUESTION_MESSAGE, new ImageIcon(CreateImage(imagePath)),
				options, options[0]);
	}
	
	public int getUserYesNoCancelChoise(String message)
	{
		return JOptionPane.showConfirmDialog(new JFrame(), message);
	}
	
	public int getUserYesNoCancelChoise(String message, String title, String imagePath)
	{
		return JOptionPane.showConfirmDialog(new JFrame(),
				message, title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.YES_NO_CANCEL_OPTION,
				new ImageIcon(CreateImage(imagePath)));
	}
	
	public String getUserTextString(String message, String initialValue)
	{
		return JOptionPane.showInputDialog(message, initialValue);
	}
	
	public String getUserTextString(String message, String title,String imagePath, String initialValue)
	{
		return (String) JOptionPane.showInputDialog(new JFrame(),
				message, title, JOptionPane.QUESTION_MESSAGE, new ImageIcon(CreateImage(imagePath)),
				null, initialValue);
	}
	
	public void destroyGUI()
	{
		GameGUI.getInstance().destroyGUI();
	}
	
	public void setOwner(int fieldNumber, String owner)
	{
		GameGUI.getInstance().setOwner(owner, fieldNumber);
	}
	
	public void setTitle(int fieldNumber, String title)
	{
		GameGUI.getInstance().setTitle(title, fieldNumber);
	}
	
	public void setSubText(int fieldNumber, String subText)
	{
		GameGUI.getInstance().setSubtext(subText, fieldNumber);
	}
	
	protected void setPrice(int fieldNumber, String price)
	{
		GameGUI.getInstance().setPrice(price, fieldNumber);
	}
	
	protected void setPrice(int fieldNumber, int price)
	{
		GameGUI.getInstance().setPrice("" + price, fieldNumber);
	}
	
	public void setPriceText(int fieldNumber, String priceText)
	{
		GameGUI.getInstance().setPriceText(priceText, fieldNumber);
	}
	
	public void appendText(String text)
	{
		GameGUI.getInstance().appendText(text);
	}
	
	public void clearText()
	{
		GameGUI.getInstance().clearTextField();
	}
	
	public void setCar(int fieldNumber, int cartype, int carNr, Color c)
	{
		GameGUI.getInstance().setCar(fieldNumber, cartype, carNr, c);
	}
	
	public void removeCar(int fieldNumber, int carNr)
	{
		GameGUI.getInstance().removeCar(fieldNumber, carNr);
	}
}
