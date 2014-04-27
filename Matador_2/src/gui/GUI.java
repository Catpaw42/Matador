package gui;

import game.GameController;
import game.Player;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author Magnus Brandt Sløgedal
 *
 */
public class GUI
{
	public GUI(){}

	/**
	 * creates a GUI, with all the fields of a Standart Matador game.
	 */
	public void create()
	{
		GameGUI.getInstance();
		initialSetup();
	}

	/**
	 * Creates a GUI with the specified list of fields
	 * @param fields The list of fields that the GUI should show
	 */
	public void create(FieldPanel[] fields)
	{
		GameGUI.createCustomGUI(fields);
		initialSetup();
	}
	/**
	 * Private method to do the initiation work on the GUI
	 */
	private void initialSetup()
	{
		Player[] players = GameController.getInstance().getAllPlayers();
		//loop all players
		for (int i = 0; i < players.length; i++)
		{
			//add players
			addPlayer(players[i].getName(),
					players[i].getCarType(),
					players[i].getCarColour());
			//add cars
			setCar(1, players[i].getName());
			
			//add money
			this.setPlayerMoney(players[i].getName(), players[i].getAccount().getBalance());
		}
		setCurrentPlayerName(players[0].getName());
	}


	/**
	 * sets the two dice of the game to the specified integers, then moves them to a random
	 * place on the board, and rotates them at random.
	 * @param i Dice one face-value
	 * @param j Dice two face-value
	 */
	public void setDice(int i, int j)
	{
		GameGUI.getInstance().setDice(i, j);
	}
	
	public void setPlayerMoney(String playerName, int amount)
	{
		GameGUI.getInstance().setPlayerMoney(playerName, amount);		
	}
	
	/**
	 * Displays a line of text at the top of the control area.
	 * @param name The name to display
	 */
	public void setCurrentPlayerName(String name)
	{
		GameGUI.getInstance().setActivePlayerName(name);
	}

	/**
	 * adds a player to the field
	 * @param playerName The Name of the player
	 * @param carType An integer 0-3 specifying the type of car to show for this player
	 * @param carColor A Color object specifying the main color of the car
	 * @return True if there was lass than 6 players displayed, and the car was a integer between 0 and 3 inclusive, False otherwise.
	 */
	public boolean addPlayer(String playerName, int carType, Color carColor)
	{
		if (carType > 3)
			return false;
		GameGUI.getInstance().addPlayer(playerName, carType, carColor);
		return true;
	}

	/**
	 * Removes a given player from the board.
	 * @param playerName the name of the player to remove.
	 * @return true if the player was successfully removed, false otherwise.
	 */
	public boolean removePlayer(String playerName)
	{
		return GameGUI.getInstance().removePlayer(playerName);
	}

	/**
	 * displays a text-string on the board
	 * @param text the String to display
	 */
	public void setDisplayedText(String text)
	{
		GameGUI.getInstance().setDisplayedText(text);
	}

	/**
	 * changes the text of the Buy button
	 * @param text The new text for the button
	 */
	public void setMainButtonText(String text)
	{
		GameGUI.getInstance().setButtonText(0, text);
	}

	/**
	 * changes the text of the Buy button
	 * @param text The new text for the button
	 */
	public void setSellButtonText(String text)
	{
		GameGUI.getInstance().setButtonText(2, text);
	}

	/**
	 * changes the text of the Buy button
	 * @param text The new text for the button
	 */
	public void setBuyButtonText(String text)
	{
		GameGUI.getInstance().setButtonText(1, text);
	}

	/**
	 * asks the user to select from list of options
	 * @param options An array of string to chose from, the string at [0] will be used as the default choise
	 * @param message The message accompanying this choise
	 * @param title The title of the created Frame with the choise
	 * @return The string that the user chose
	 */
	public String getUserSelection(String[] options, String message, String title)
	{
		return (String) JOptionPane.showInputDialog(new JFrame(),
				message, title, JOptionPane.PLAIN_MESSAGE, null,
				options, options[0]);
	}

	/**
	 * asks the user to select a button
	 * @param options An array of string to chose from, the string at [0] will be used as the default choise
	 * @param message The message accompanying this choise
	 * @param title The title of the created Frame with the choise
	 * @return An integer indicating what the user chose, corresponding to the position in the options array.
	 */
	public int getUserButtonPressed(String[] options, String message, String title)
	{
		return JOptionPane.showOptionDialog(null, message, title,
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, options, options[0]);
	}

	/**
	 * asks the user to select from list of options
	 * @param options options An array of string to chose from, the string at [0] will be used as the default choise
	 * @param message message The message accompanying this choise
	 * @param title The title of the created Frame with the choise
	 * @param imagePath the path to the image accompanying this choise
	 * @return The string that the user chose
	 */
	public String getUserSelection(String[] options, String message, String title, String imagePath)
	{
		return (String) JOptionPane.showInputDialog(new JFrame(),
				message, title, JOptionPane.QUESTION_MESSAGE, new ImageIcon(ImageFactory.CreateImage(imagePath)),
				options, options[0]);
	}

	/**
	 * Asks the user to answer yes / no / cancel
	 * @param message The message accompanying this choise
	 * @return an integer, indicating the choise: 0 = Yes, 1 = No, 2 = Cancel
	 */
	public int getUserYesNoCancelChoise(String message)
	{
		return JOptionPane.showConfirmDialog(new JFrame(), message);
	}

	/**
	 * Asks the user to answer yes / no / cancel
	 * @param message The message accompanying this choise
	 * @param title The title of the created Frame with the choise
	 * @param imagePath the path to the image accompanying this choise
	 * @return an integer, indicating the choise: 0 = Yes, 1 = No, 2 = Cancel
	 */
	public int getUserYesNoCancelChoise(String message, String title, String imagePath)
	{
		return JOptionPane.showConfirmDialog(new JFrame(),
				message, title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.YES_NO_CANCEL_OPTION,
				new ImageIcon(ImageFactory.CreateImage(imagePath)));
	}

	/**
	 * Asks the user to input a textString
	 * @param message The message accompanying this choise
	 * @param initialValue The initial value shown in the textBox
	 * @return The string supplied by the user
	 */
	public String getUserTextString(String message, String initialValue)
	{
		return JOptionPane.showInputDialog(message, initialValue);
	}

	/**
	 * Asks the user to input a textString
	 * @param message The message accompanying this choise
	 * @param title The title of the created Frame with the choise
	 * @param imagePath the path to the image accompanying this choise
	 * @param initialValue The initial value shown in the textBox
	 * @return The string supplied by the user
	 */
	public String getUserTextString(String message, String title,String imagePath, String initialValue)
	{
		return (String) JOptionPane.showInputDialog(new JFrame(),
				message, title, JOptionPane.QUESTION_MESSAGE, new ImageIcon(ImageFactory.CreateImage(imagePath)),
				null, initialValue);
	}

	/**
	 * Disposes of the GUI, and sets the singleton instance to null, enabling the creation of a new GUI
	 */
	public void destroyGUI()
	{
		GameGUI.getInstance().destroyGUI();
	}

	/**
	 * Sets the picture of a given field
	 * @param fieldNumber the integer specifying the field
	 * @param pictureName The string specifying the picture (available statically via FieldPanel.Builder) 
	 */
	public void setFieldPicture(int fieldNumber, String picturename)
	{
		GameGUI.getInstance().setFieldPicture(picturename, fieldNumber);
	}
	
	/**
	 * Sets the owner of a given field
	 * @param fieldNumber the integer specifying the field
	 * @param owner The string specifying the new owners name
	 */
	public void setFieldOwner(int fieldNumber, String owner)
	{
		GameGUI.getInstance().setOwner(owner, fieldNumber);
	}

	/**
	 * Sets the title of a given field
	 * @param fieldNumber the integer specifying the field
	 * @param title The string specifying the new title of the field
	 */
	public void setFieldTitle(int fieldNumber, String title)
	{
		GameGUI.getInstance().setTitle(title, fieldNumber);
	}

	/**
	 * Sets the subtext of a given field
	 * @param fieldNumber the integer specifying the field
	 * @param subText The string specifying the new subtext of the field
	 */
	public void setFieldSubText(int fieldNumber, String subText)
	{
		GameGUI.getInstance().setSubtext(subText, fieldNumber);
	}

	/**
	 * Sets the price of a given field
	 * @param fieldNumber the integer specifying the field
	 * @param price The string specifying the new price of the field
	 */
	protected void setFieldPrice(int fieldNumber, String price)
	{
		GameGUI.getInstance().setPrice(price, fieldNumber);
	}
	
	protected void setFieldRent(int fieldNumber, String rent)
	{
		GameGUI.getInstance().setRent(rent, fieldNumber);
	}

	/**
	 * Sets the price of a given field
	 * @param fieldNumber the integer specifying the field
	 * @param price The integer specifying the new price of the field
	 */
	protected void setFieldPrice(int fieldNumber, int price)
	{
		GameGUI.getInstance().setPrice("" + price, fieldNumber);
	}
	
	protected void setFieldRent(int fieldNumber, int rent)
	{
		GameGUI.getInstance().setRent("" + rent, fieldNumber);
	}

	/**
	 * Sets the priceText of a given field, example "price" when not owned versus "rent" when someone owns the field
	 * @param fieldNumber the integer specifying the field
	 * @param priceText The string specifying the new priceText of the field
	 */
	public void setFieldPriceText(int fieldNumber, String priceText)
	{
		GameGUI.getInstance().setPriceText(priceText, fieldNumber);
	}

	/**
	 * Appends a string to the games textArea
	 * @param text the string to append
	 */
	public void appendTextToTextArea(String text)
	{
		GameGUI.getInstance().appendText(text);
	}

	/**
	 * clears all text from the games textArea
	 */
	public void clearTextFromTextArea()
	{
		GameGUI.getInstance().clearTextField();
	}

	/**
	 * Adds a car to the given field
	 * @param fieldNumber the number of the field
	 * @param cartype An integer 0-3 (inclusive) specifying the type of car to show
	 * @param carNr a number 1-6(inclusive) specifying the layer the car should be at
	 * @param color The Color of the car
	 */
	public void setCar(int fieldNumber, String playerName)
	{
		Player[] players = GameController.getInstance().getAllPlayers();
		for (int i = 0; i < players.length; i++)
		{
			if(players[i].getName().equals(playerName))
				GameGUI.getInstance().setCar(fieldNumber, players[i].getCarType(), i + 1, players[i].getCarColour());
		}
	}

	/**
	 * Removes a given car from the given field
	 * @param fieldNumber  the number of the field
	 * @param carNr the number 1-6(inclusive) specifying the layer the car that should be removed is at
	 */
	public void removeCar(int fieldNumber, int carNr)
	{
		GameGUI.getInstance().removeCar(fieldNumber, carNr);
	}

	public void removeAllCars(String name)
	{
		Player[] players = GameController.getInstance().getAllPlayers();
		for (int i = 0; i < players.length; i++)
		{
			if(players[i].getName().equals(name))
			{
				for (int j = 1; j <= 40; j++)
				{
					removeCar(j, i + 1);
				}
			}
		}
	}
}
