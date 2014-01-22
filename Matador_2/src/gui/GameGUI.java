package gui;

import game.GameActionListener;

import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
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
	
	public static GameGUI getInstance()
	{
		if(instance == null)
			instance = new GameGUI();
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
	public static GameGUI createCustomGUI(FieldPanel[] fields)
	{
		if (instance == null)
			instance = new GameGUI(fields);
		else
			System.err.println("Instance already exists!!!");
		return instance;
		
	}
	//-----------------------------------------------------

	//constructor, this is used to create a standard board
	private GameGUI()
	{
		// manage the frame
		this.setTitle("Matador");
		this.setPreferredSize(new Dimension(1244, 710));
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		//add a windowlistener to manages what happens when the frame is closed.
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{	
				new GameActionListener().windowClosingEventDispatched();
			}
		});
		//add a keylistener
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new GameActionListener());
		
		this.setResizable(false);
		this.pack(); //pack here, so that .setBounds() works with the right size.

		//manage the board
		boardPanel = new BoardPanel();
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
	
	private GameGUI(FieldPanel[] fields)
	{
		// manage the frame
		this.setTitle("Matador");
		this.setPreferredSize(new Dimension(1244, 710));
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		//add a windowlistener to manages what happens when the frame is closed.
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{	
				new GameActionListener().windowClosingEventDispatched();
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
	public JButton getButton(int b)
	{
		return this.controlPanel.buttons[b];
	}
	
	public void appendText(String str)
	{
		this.controlPanel.textArea.append(str + "\n");
	}
	
	public void setDice(int value1, int value2)
	{
		this.boardPanel.setDice(value1, value2);
	}
	
	//----------------------------------------------------------------------------------------------------------------------------
}
