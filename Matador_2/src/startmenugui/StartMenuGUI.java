package startmenugui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StartMenuGUI extends JDialog implements ActionListener
{
	private game.GameOptions options = null;
	private JButton[] buttons;
	private JPanel panel;
	
	public StartMenuGUI()
	{
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		panel = new JPanel(new FlowLayout());
		buttons = new JButton[4];
		
		for (int i = 0; i < buttons.length; i++)
		{
			buttons[i] = new JButton();
			buttons[i].addActionListener(this);
			buttons[i].setPreferredSize(new Dimension(200,60));
			panel.add(buttons[i]);
		}
		
		buttons[0].setText("Start New Game");
		buttons[1].setText("Load Game");
		buttons[2].setText("Options");
		buttons[3].setText("Quit");
		
		this.setPreferredSize(new Dimension(250, 310));
		this.setTitle("Matador");
		this.add(panel);
		this.pack();
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.out.println("lalala");
				((JDialog) e.getSource()).setVisible(false);
				((JDialog) e.getSource()).dispose();
			}
		});
	}
	
	public game.GameOptions startDialog()
	{		  
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (this.getWidth() / 2), (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (this.getHeight() / 2));
		this.setVisible(true);
		
		return options;
	}
	
	
	
//	private void showLoadGameMenu()
//	{
//		final JDialog dialog = new JDialog(new JFrame(), true);
//		JPanel panel = new JPanel();
//		
//		dialog.setPreferredSize(new Dimension(800,600));
//		dialog.addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent e)
//			{
//				dialog.setVisible(false);
//				dialog.dispose();
//			}
//		});
//		dialog.pack();
//		dialog.add(panel);
//		
//		
//		dialog.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (dialog.getWidth() / 2), (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (dialog.getHeight() / 2));
//		dialog.setVisible(true);
//	}
//	
//	private void showOptionsMenu()
//	{
//		final JDialog dialog = new JDialog(new JFrame(), true);
//		JPanel panel = new JPanel();
//		
//		dialog.setPreferredSize(new Dimension(800,600));
//		dialog.addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent e)
//			{
//				dialog.setVisible(false);
//				dialog.dispose();
//			}
//		});
//		dialog.pack();
//		dialog.add(panel);
//		
//		
//		dialog.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (dialog.getWidth() / 2), (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (dialog.getHeight() / 2));
//		dialog.setVisible(true);
//	}
	
	

	public static void main(String[] args)
	{
		game.GameOptions g = new StartMenuGUI().startDialog();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		//new game button
		if(e.getSource() == buttons[0])
		{
			//show new frame to set options
			options = new NewGameDialog().showNewGameMenu();
			if(options != null)
			{
				this.setVisible(false);
				this.dispose();
			}
		}
		//load game button
		else if(e.getSource() == buttons[1])
		{
			//for later, when we get to the sql work
//			showLoadGameMenu();
		}
		//options button
		else if(e.getSource() == buttons[2])
		{
			//may be unnecessary
//			showOptionsMenu();
		}
		//quit button
		else if(e.getSource() == buttons[3])
		{
			System.out.println("Quit Game");
			System.exit(0);
		}
		
	}
}
