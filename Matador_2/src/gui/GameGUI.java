package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class GameGUI extends JFrame  implements ComponentListener
{
	private Dimension screenDimension;
	private Dimension gameDimension;
	private Dimension boardDimension;
	private Dimension controlDimension;
	private ControlPanel controlPanel;
	private BoardPanel boardPanel;
	JPanel backgroundPanel;
	private double GAME_SIZE_MODIFIER = 0.7;

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
	//-----------------------------------------------------

	private GameGUI()
	{
		screenDimension = Toolkit.getDefaultToolkit().getScreenSize();	
		gameDimension = new Dimension((int) (screenDimension.width * GAME_SIZE_MODIFIER), (int) (screenDimension.height * GAME_SIZE_MODIFIER));
		boardDimension = new Dimension((int) (gameDimension.width * 4.0 / 6.0), (int) (gameDimension.height));
		controlDimension = new Dimension((int) (gameDimension.width * 2.0 / 6.0), (int) (gameDimension.height));
		
		controlPanel = new ControlPanel();
		boardPanel = new BoardPanel();
		
		backgroundPanel = new JPanel();
		backgroundPanel.setLayout(null);
		backgroundPanel.add(boardPanel);
		backgroundPanel.add(controlPanel);
		
		
		
		
		// manage the frame
		this.setTitle("Matador");
		this.setLocation((int) (((1.0 - GAME_SIZE_MODIFIER) / 2.0) * screenDimension.width), (int) (((1.0 - GAME_SIZE_MODIFIER) / 2.0) * screenDimension.height));
		this.addComponentListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(backgroundPanel);
		this.setPreferredSize(gameDimension);
		this.setVisible(true);
		this.pack();
		
		updatePositions();
		
	}

	// manage the position of everything.
	private void updatePositions()
	{
		gameDimension = this.getSize();
		boardDimension.setSize( (this.getContentPane().getSize().width * 4.0 / 6.0), (this.getContentPane().getSize().height));
		controlDimension.setSize( (this.getContentPane().getSize().width * 2.0 / 6.0), (this.getContentPane().getSize().height));
		boardPanel.setBounds(0, 0, boardDimension.width, boardDimension.height);
		controlPanel.setBounds(boardDimension.width, 0, controlDimension.width, controlDimension.height);
		
		controlPanel.updatePositions();
		boardPanel.updatePositions();

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
	
	@Override
	public void componentHidden(ComponentEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e)
	{
		updatePositions();
		
	}

	@Override
	public void componentShown(ComponentEvent e)
	{
		// TODO Auto-generated method stub

	}
}
