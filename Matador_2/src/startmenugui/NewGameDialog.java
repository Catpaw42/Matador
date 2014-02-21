package startmenugui;

import game.Player;
import gui.ImageFactory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

@SuppressWarnings("serial")
public class NewGameDialog extends JDialog implements ActionListener, PopupMenuListener
{
	private JPanel panel;
	private JTextField[] playerNames;
	private BufferedImage[] carArray;
	private MyImageIcon[] colors;
	private JComboBox<Icon>[] carTypes;
	private JComboBox<MyImageIcon>[] colorComboBox;
	private JCheckBox[] checkBoxArray;
	private JButton okButton;
	private JButton cancelButton;
	
	private boolean okTrue = false;

	@SuppressWarnings("unchecked")
	public NewGameDialog()
	{
		panel = new JPanel();

		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setPreferredSize(new Dimension(450,350));
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e)
			{
				((JDialog) e.getSource()).setVisible(false);
				((JDialog) e.getSource()).dispose();
			}
		});
		this.pack();
		this.getContentPane().add(panel);

		panel.setLayout(null);
		playerNames = new JTextField[6];
		
		colors = new MyImageIcon[12];
		colors[0] = createColorIcon(Color.BLACK);
		colors[1] = createColorIcon(Color.RED);
		colors[2] = createColorIcon(Color.BLUE);
		colors[3] = createColorIcon(Color.YELLOW);
		colors[4] = createColorIcon(Color.GREEN);
		colors[5] = createColorIcon(Color.CYAN);
		colors[6] = createColorIcon(Color.PINK);
		colors[7] = createColorIcon(new Color(211, 0, 120)); //Purple
		colors[8] = createColorIcon(new Color(255, 110, 0)); //Orange
		colors[9] = createColorIcon(Color.MAGENTA);
		colors[10] = createColorIcon(Color.WHITE);
		colors[11] = createColorIcon(new Color(0x009999)); //lyseblå?
		
		carArray = ImageFactory.setupCarIcons();
		carTypes = (JComboBox<Icon>[]) new JComboBox[6];
		checkBoxArray = new JCheckBox[6];
		colorComboBox = (JComboBox<MyImageIcon>[]) new JComboBox[6];

		//trash data for first setup
		JLabel[] arr = new JLabel[1];
		arr[0] = new JLabel();
		
		for (int i = 0; i < playerNames.length; i++)
		{
			playerNames[i] = new JTextField("Player" + (1 + i));
			playerNames[i].setBounds(40, 5 + i * 40, 120, 30);
			colorComboBox[i] = new JComboBox<MyImageIcon>(colors);
			colorComboBox[i].setSelectedIndex(i);
			colorComboBox[i].setBounds(165, 5 + i * 40, 50, 30);
			colorComboBox[i].addActionListener(this);
			colorComboBox[i].addPopupMenuListener(this);
			carTypes[i] = new JComboBox<Icon>();
			carTypes[i].setModel(updateCarTypesColor(i));
			carTypes[i].setSelectedIndex(0);
			carTypes[i].setBounds(220, 5 + i * 40, 100, 30);
			checkBoxArray[i] = new JCheckBox();
			checkBoxArray[i].addActionListener(this);
			checkBoxArray[i].setBounds(5, 5 + i * 40, 30, 30);
			if(i == 0 || i == 1)
				checkBoxArray[i].setSelected(true);
			if(i!= 0 && i != 1)
				setCheckBoxState(i, false);

			panel.add(playerNames[i]);
			panel.add(colorComboBox[i]);
			panel.add(carTypes[i]);
			panel.add(checkBoxArray[i]);
		}
		
		okButton = new JButton("OK");
		okButton.setBounds(200, 260, 100, 30);
		okButton.addActionListener(this);
		panel.add(okButton);
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(310, 260, 100, 30);
		cancelButton.addActionListener(this);

		panel.add(cancelButton);
	}

	public Player[] showNewGameMenu()
	{
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (this.getWidth() / 2), (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (this.getHeight() / 2));
		this.setVisible(true);

		if(okTrue)
			return generatePlayerArray();
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		int playercount = 0;

		for (int i = 0; i < 6; i++)
		{
			if (checkBoxArray[i].isSelected())
				playercount++;

			if (e.getSource() == colorComboBox[i])
			{
				carTypes[i].setModel(updateCarTypesColor(i));
			}
			else if (e.getSource() == checkBoxArray[i])
			{
				setCheckBoxState(i, checkBoxArray[i].isSelected());
			}
		}
		if (e.getSource() == okButton)
		{
			okTrue = true;
			this.setVisible(false);
			this.dispose();
		}
		else if (e.getSource() == cancelButton)
		{
			okTrue = false;
			this.setVisible(false);
			this.dispose();
		}

		if (playercount < 2)
			okButton.setEnabled(false);
		else
			okButton.setEnabled(true);

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void popupMenuWillBecomeVisible(PopupMenuEvent e)
	{
		((JComboBox<MyImageIcon>) e.getSource()).setModel(updateColorComboboxModel());
	}

	private Player[] generatePlayerArray()
	{
		//hvow many players are there?
		int numberOfPlayers = 0;
		for (int i = 0; i < checkBoxArray.length; i++)
		{
			if (checkBoxArray[i].isSelected())
			{
				numberOfPlayers++;
			}
		}
		Player[] players = new Player[numberOfPlayers];
		for (int i = 0; i < players.length ; i++)
		{
			players[i] = new Player(playerNames[i].getText(), ((MyImageIcon)colorComboBox[i].getSelectedItem()).getColor(), carTypes[i].getSelectedIndex());
		}
		return  players;
	}

	private void setCheckBoxState(int line, boolean state)
	{
		playerNames[line].setEnabled(state);
		colorComboBox[line].setEnabled(state);
		carTypes[line].setEnabled(state);
	}
	
	private DefaultComboBoxModel<MyImageIcon> updateColorComboboxModel()
	{
		ArrayList<MyImageIcon> colorArr = new ArrayList<MyImageIcon>();
		for (int i = 0; i < colors.length; i++)
		{
			boolean isSelectedColor = false;
			for (int j = 0; j < colorComboBox.length; j++)
			{
				MyImageIcon temp = (MyImageIcon) colorComboBox[j].getSelectedItem();
				if (temp != null && temp.getColor().equals(colors[i].getColor()))
					isSelectedColor = true;
			}
			if (!isSelectedColor)
			{
				colorArr.add(colors[i]);
			}
		}
		MyImageIcon[] tempArr = new MyImageIcon[colorArr.size()];
		for (int i = 0; i < colorArr.size(); i++)
			tempArr[i] = colorArr.get(i);
		
		return new DefaultComboBoxModel<MyImageIcon>(tempArr);
	}
	
	private MyImageIcon createColorIcon(Color c)
	{
		BufferedImage buffImg = new BufferedImage(30, 30, BufferedImage.TYPE_INT_RGB);
		Graphics g = buffImg.getGraphics();
		g.setColor(c);
		g.fillRect(0, 0, buffImg.getWidth(), buffImg.getHeight());
		MyImageIcon icon = new MyImageIcon(buffImg);
		icon.setColor(c);
		return icon;
	}

	private DefaultComboBoxModel<Icon> updateCarTypesColor(int playerRow)
	{
		//the default color of the images(red) used to search and replace
		final int DEFAULTPRIMARYCOLOR = 0xffff0000;
		
		Icon[] iconArr = new Icon[carArray.length];
		
		for (int i = 0; i < carArray.length; i++)
			iconArr[i] = new ImageIcon(ImageFactory.replaceColor(carArray[i], ((MyImageIcon)colorComboBox[playerRow].getSelectedItem()).getColor(), DEFAULTPRIMARYCOLOR));
		
		return new DefaultComboBoxModel<Icon>(iconArr);
	}
	
	private class MyImageIcon extends ImageIcon
	{
		public MyImageIcon(BufferedImage img)
		{
			super(img);
		}
		private Color thisColor;

		private Color getColor()
		{
			return thisColor;
		}

		private void setColor(Color c)
		{
			this.thisColor = c;
		}
	}

	//-----------------------------------------------------------
	//unused events
	//-----------------------------------------------------------
	@Override
	public void popupMenuCanceled(PopupMenuEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e)
	{
		// TODO Auto-generated method stub
		
	}


}
