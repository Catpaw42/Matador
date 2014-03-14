package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel
{
	protected JButton[] buttons;
	private JScrollPane scrollPane;
	protected JTextArea textArea;
	private JLabel nameLabel;
	private JLabel dragLabel;
	private BufferedImage housePicture;
	private BufferedImage hotelPicture;


	protected ControlPanel()
	{
		//manage this panel
		this.setLayout(null);
		this.setSize(412, 682);
		this.setBackground(Color.ORANGE);

		//add a label to show the current players name
		nameLabel = new JLabel();
		this.add(nameLabel);
		//		nameLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(new Font(Font.SERIF, Font.BOLD, 26));
		nameLabel.setBounds(this.getWidth() * 4/20, this.getHeight() * 1/40, this.getWidth() * 12 / 20, this.getHeight() * 1/20);

		//add a textarea with a scrollpane to show output
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane = new JScrollPane(textArea);
		this.add(scrollPane);
		scrollPane.setBounds(this.getWidth() * 2 / 20, this.getHeight() * 2 / 20, this.getWidth() * 16 / 20, this.getHeight() * 9 / 20);

		//add a custom label for the drag and drop house / hotel
		hotelPicture = ImageFactory.CreateImage("pics/1House.png");
		housePicture = ImageFactory.CreateImage("pics/Hotel.png");
		dragLabel = new JLabel()
		{
			@Override
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				if (housePicture != null && hotelPicture != null)
				{
					//draw a house and a hotel, scaled up to 2x
					g.drawImage((Image) hotelPicture, 0, 0, hotelPicture.getWidth() * 2, hotelPicture.getHeight() * 2, null);
					g.drawImage((Image) housePicture, hotelPicture.getWidth() * 2, 0, housePicture.getWidth() * 2, housePicture.getHeight() * 2, null);
				}
			}
		};
		this.add(dragLabel);
		dragLabel.setBounds(this.getWidth() * 4 / 20, this.getHeight() * 12 / 20, this.getWidth() * 3 / 20, this.getHeight() * 1 / 20);
		dragLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		dragLabel.setTransferHandler(new TransferHandler("text")); //add a transferhandler, used to manage drag and drop
		dragLabel.getTransferHandler().setDragImage(ImageFactory.CreateImage("pics/1House.png"));
		dragLabel.addMouseMotionListener(new MouseAdapter()
		{
			@Override
			public void mouseDragged(MouseEvent e) //add a mouseMotion Listener, this is called when a "drag" event is fired on this label
			{
				JComponent c = (JComponent) e.getSource();
				TransferHandler handler = c.getTransferHandler();
				handler.exportAsDrag(c, e, TransferHandler.COPY);
			}
		});

		//add buttons to this panel
		buttons = new JButton[3];
		buttons[0] = new JButton("START"); //main button
		buttons[1] = new JButton("<html><u>B</u>uy</html>"); //Buy
		buttons[2] = new JButton("<html><u>S</u>ell</html>");//Sell

		//add actionlistners to all the buttons
		for (int i = 0; i < buttons.length; i++)
		{
			buttons[i].addActionListener(new ActionListener()
			{
				GameActionListener g = new GameActionListener();
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if (e.getSource().equals(buttons[0]))
					{
						g.mainButtonEvent();
					}
					else if (e.getSource().equals(buttons[1]))
					{
						g.buyButtonEvent();
					}
					else if (e.getSource().equals(buttons[1])) // Hvorfor står der ikke 2 her?
					{
						g.sellButtonEvent();
					}
				}
			});
					this.add(buttons[i]);
		}
		buttons[0].setBounds(this.getWidth() * 2 / 20, this.getHeight() * 17 / 20, this.getWidth() * 16 / 20, this.getHeight() * 2 / 20);
		buttons[1].setBounds(this.getWidth() * 2 / 20, this.getHeight() * 31 / 40, this.getWidth() * 7 / 20, this.getHeight() * 1 / 20);
		buttons[2].setBounds(this.getWidth() * 11 / 20, this.getHeight() * 31 / 40, this.getWidth() * 7 / 20, this.getHeight() * 1 / 20);
	}
	
	protected void setNameLabelText(String text)
	{
		this.nameLabel.setText(text);
	}
	
	protected void setButtonText(int i, String text)
	{
		this.buttons[i].setText(text);
	}
}
