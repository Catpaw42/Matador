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
	 * alternatively it simply returns the singleton instance.
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


	private GameGUI()
	{
		backgroundPanel = new JPanel();
		boardPanel = new BoardPanel();
		controlPanel = new ControlPanel();
		
		// manage the frame
		this.setTitle("Matador");
		this.setPreferredSize(new Dimension(1244, 710));
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new GameActionListener());
		this.setResizable(false);
		this.add(backgroundPanel);
		this.pack();

		// manage the background panel
		backgroundPanel.setLayout(null);
		backgroundPanel.setSize(this.getContentPane().getWidth(), this.getContentPane().getHeight());
		backgroundPanel.add(boardPanel);
		backgroundPanel.add(controlPanel);
		
		//manage the board
		boardPanel.setBounds(0, 0, (int) (this.getContentPane().getWidth() * 4.0 / 6.0), this.getContentPane().getHeight());
		
		//manage the controller
		controlPanel.setBounds((int) (this.getContentPane().getWidth() * 4.0 / 6.0), 0, (int) (this.getContentPane().getWidth() * 2.0 / 6.0), this.getContentPane().getHeight());

		//visibility and location
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (this.getWidth() / 2), (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (this.getHeight() / 2));
		this.setVisible(true);
	}
	
	private GameGUI(FieldPanel[] fields)
	{
//		backgroundPanel = new JPanel();
//		boardPanel = new BoardPanel();
//		controlPanel = new ControlPanel();
//		
//		// manage the frame
//		this.setTitle("Matador");
//		this.setPreferredSize(new Dimension(1236, 720));
//		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (this.getWidth() / 2), (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (this.getHeight() / 2));
//		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//		this.getRootPane().setDefaultButton(this.getButton(0));
//		this.setResizable(false);
//		this.add(backgroundPanel);
//		this.pack();
//
//		// manage the background panel
//		backgroundPanel.setLayout(null);
//		backgroundPanel.setSize(this.getContentPane().getWidth(), this.getContentPane().getHeight());
//		backgroundPanel.add(boardPanel);
//		backgroundPanel.add(controlPanel);
//		
//		//manage the board
//		boardPanel.setBounds(0, 0, (int) (this.getContentPane().getWidth() * 4.0 / 6.0), this.getContentPane().getHeight());
//		
//		//manage the controller
//		controlPanel.setBounds((int) (this.getContentPane().getWidth() * 4.0 / 6.0), 0, (int) (this.getContentPane().getWidth() * 2.0 / 6.0), this.getContentPane().getHeight());
//		controlPanel.updatePositions();
//
//		//visibility
//		this.setVisible(true);
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
