package gui;

import game.GameActionListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
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
		this.setLayout(null);
		this.setSize(412, 682);
		this.setBackground(Color.ORANGE);
		
		nameLabel = new JLabel("MAGNUS");
		this.add(nameLabel);
//		nameLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(new Font(Font.SERIF, Font.BOLD, 26));
		nameLabel.setBounds(this.getWidth() * 4/20, this.getHeight() * 1/40, this.getWidth() * 12 / 20, this.getHeight() * 1/20);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		
		scrollPane = new JScrollPane(textArea);
		this.add(scrollPane);
		scrollPane.setBounds(this.getWidth() * 2 / 20, this.getHeight() * 2 / 20, this.getWidth() * 16 / 20, this.getHeight() * 9 / 20);
		
		hotelPicture = CreateImage("pics/1House.png");
		housePicture = CreateImage("pics/Hotel.png");
		dragLabel = new JLabel()
		{
			@Override
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				if (housePicture != null && hotelPicture != null)
				{
					g.drawImage((Image) hotelPicture, 0, 0, hotelPicture.getWidth() * 2, hotelPicture.getHeight() * 2, null);
					g.drawImage((Image) housePicture, hotelPicture.getWidth() * 2, 0, housePicture.getWidth() * 2, housePicture.getHeight() * 2, null);
				}
			}
		};
		this.add(dragLabel);
		dragLabel.setBounds(this.getWidth() * 4 / 20, this.getHeight() * 12 / 20, this.getWidth() * 3 / 20, this.getHeight() * 1 / 20);
		dragLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		dragLabel.setTransferHandler(new TransferHandler()
		{
			@Override
			public int getSourceActions(JComponent c)
			{
				return COPY_OR_MOVE;
			}
			@Override
			protected Transferable createTransferable(JComponent c)
			{
				return new StringSelection("Test");
			}
			@Override
			protected void exportDone(JComponent source, Transferable data, int action)
			{
				System.out.println("inside exportDone method");
			}
		});
		
		buttons = new JButton[3];
		buttons[0] = new JButton("START");
		buttons[1] = new JButton("<html><u>B</u>uy</html>");
		buttons[2] = new JButton("<html><u>S</u>ell</html>");
		
		for (int i = 0; i < buttons.length; i++)
		{
			buttons[i].addActionListener(new GameActionListener());
			this.add(buttons[i]);
		}
		buttons[0].setBounds(this.getWidth() * 2 / 20, this.getHeight() * 17 / 20, this.getWidth() * 16 / 20, this.getHeight() * 2 / 20);
		buttons[1].setBounds(this.getWidth() * 2 / 20, this.getHeight() * 31 / 40, this.getWidth() * 7 / 20, this.getHeight() * 1 / 20);
		buttons[2].setBounds(this.getWidth() * 11 / 20, this.getHeight() * 31 / 40, this.getWidth() * 7 / 20, this.getHeight() * 1 / 20);
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
}
