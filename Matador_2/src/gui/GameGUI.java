package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameGUI extends JFrame
{
	private ControlPanel controlPanel;
	private BoardPanel boardPanel;
	private JPanel backgroundPanel;
	
	//-----------------------------------------------------
	//Singleton design pattern
	//-----------------------------------------------------
	private static GameGUI instance = null;

	protected static GameGUI getInstance()
	{
		if(instance == null)
			instance = new GameGUI(null);
		return instance;
	}
	/**
	 * This method creates the GUI with a custom list of fields
	 * for it to have any effect this MUST be run before any calls to getInstance
	 * alternatively it simply returns the singleton instance, and prints
	 * to .err that you're calling the function wrong.
	 * @param fields the list of fields you want on your GUI
	 * @return the singleton instance
	 */
	protected static GameGUI createCustomGUI(FieldPanel[] fields)
	{
		if (instance == null)
			instance = new GameGUI(fields);
		else
			System.err.println("Instance already exists!!!");
		return instance;

	}
	//-----------------------------------------------------

	//constructor, this is used to create a standard board
	private GameGUI(FieldPanel[] fields)
	{
		// manage the frame
		this.setTitle("Matador");
		this.setPreferredSize(new Dimension(1294, 720));
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		//add a windowlistener to manages what happens when the frame is closed.
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{	
				new GameActionListener().windowClosingEvent();
			}
		});
		//add a keylistener
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new GameActionListener());

		this.setResizable(false);
		this.pack(); //pack here, so that .setBounds() works with the right size.
				
		//manage the board
		boardPanel = new BoardPanel(fields);
		boardPanel.setBounds(0, 0, (int) (this.getContentPane().getWidth() * 4.0 / 6.0), this.getContentPane().getHeight());

		//manage the controller
		controlPanel = new ControlPanel();
		controlPanel.setBounds((int) (this.getContentPane().getWidth() * 4.0 / 6.0), 0, (int) (this.getContentPane().getWidth() * 2.0 / 6.0), this.getContentPane().getHeight());

		// manage the background panel
		backgroundPanel = new JPanel();
		this.add(backgroundPanel);
		backgroundPanel.setLayout(null);
		backgroundPanel.setSize(this.getContentPane().getWidth(), this.getContentPane().getHeight());
		backgroundPanel.add(boardPanel);
		backgroundPanel.add(controlPanel);

		//visibility and location
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (this.getWidth() / 2), (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (this.getHeight() / 2));
		this.setVisible(true);
	}

	//----------------------------------------------------------------------------------------------------------------------------
	// interface to the rest of the GUI
	//----------------------------------------------------------------------------------------------------------------------------
	protected void setFieldPicture(String pic, int fieldNr)
	{
		this.boardPanel.setFieldPicture(pic, fieldNr);
	}
	
	protected void setDisplayedText(String text)
	{
		this.boardPanel.setDisplayedText(text);
	}
	
	protected void setPlayerMoney(String player, int amount)
	{
		this.boardPanel.setPlayerMoney(player, amount);
	}
	
	protected void setButtonText(int i, String text)
	{
		this.controlPanel.setButtonText(i, text);
	}
	
	protected void setActivePlayerName(String text)
	{
		this.controlPanel.setNameLabelText(text);
	}
	
	protected void appendText(String str)
	{
		this.controlPanel.textArea.append(str + "\n");
	}
	
	protected void clearTextField()
	{
		this.controlPanel.textArea.setText(null);
	}

	protected void setDice(int value1, int value2)
	{
		this.boardPanel.setDice(value1, value2);
	}
	
	protected void setSubtext(String subText, int fieldNr)
	{
		this.boardPanel.setSubtext(subText, fieldNr);
	}
	
	protected void setOwner(String owner, int fieldNr)
	{
		this.boardPanel.setOwner(owner, fieldNr);
	}
	
	protected void setPrice(String price, int fieldNr)
	{
		this.boardPanel.setPrice(price, fieldNr);
	}
	
	protected void setRent(String rent, int fieldNr)
	{
		this.boardPanel.setRent(rent, fieldNr);
	}
	
	protected void setPriceText(String priceText, int fieldNr)
	{
		this.boardPanel.setPriceText(priceText, fieldNr);
	}
	
	protected void setDescription(String descText, int fieldNr)
	{
		this.boardPanel.setDescription(descText, fieldNr);
	}
	
	protected void setTitle(String title, int fieldNr)
	{
		this.boardPanel.setTitle(title, fieldNr);
	}
	
	protected void setCar(int fieldNr, int cartype, int carNr, Color c)
	{
		this.boardPanel.setCar(fieldNr, cartype, carNr, c);
	}
	
	protected void removeCar(int fieldNr, int carNr)
	{
		this.boardPanel.removeCar(fieldNr, carNr);
	}
	
	protected boolean addPlayer(String playerName, int carType, Color carColor)
	{
		for (int i = 0; i < this.boardPanel.playerFields.length; i++)
		{
			if (this.boardPanel.playerFields[i].isVisible() == false)
			{
				this.boardPanel.playerFields[i].car.setCar(carType, carColor);
				this.boardPanel.playerFields[i].setPlayerName(playerName);
				this.boardPanel.playerFields[i].setPlayerMoney(0);
				this.boardPanel.playerFields[i].setVisible(true);
				return true;
			}
		}
		return false;
	}
	
	protected boolean removePlayer(String playerName)
	{
		for (int i = 0; i < this.boardPanel.playerFields.length; i++)
		{
			if (this.boardPanel.playerFields[i].textLabel.getText().equals(playerName))
			{
				this.boardPanel.playerFields[i].setVisible(false);
				return true;
			}
		}
		return false;
	}
	
	protected void destroyGUI()
	{
		instance.dispose();
		GameGUI.instance = null;
	}

	//----------------------------------------------------------------------------------------------------------------------------
}
