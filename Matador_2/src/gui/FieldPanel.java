package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;

@SuppressWarnings("serial")
public class FieldPanel extends JPanel implements MouseMotionListener , MouseListener
{
	protected JLabel subTextLabel;
	protected JLabel titleLabel;
	protected PopUpField popUpField;
	protected BufferedImage picture;
	protected String description;
	protected final int fieldNumber;

	private JLabel pictureLabel;
	private JLayeredPane layered;
	private CarLabel carLabels[];

	private FieldPanel(Builder b) 
	{
		this.fieldNumber = Builder.fieldNumber; //store and increment the field-number
		Builder.fieldNumber++;

		//manage this panel
		this.setLayout(null);
		this.setSize(75, 62);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setTransferHandler(new TransferHandler("text"));
		this.setBackground(b.bgColor);

		//add a label for the title
		titleLabel = new JLabel("<html>" +  b.title + "</html>",SwingConstants.CENTER);
		titleLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 10));
		titleLabel.setBounds(0, 0, this.getWidth(), this.getSize().height * 4 / 12);
		this.add(titleLabel);

		//add a label for the subText
		subTextLabel = new JLabel("<html>" +  b.subText + "</html>",SwingConstants.CENTER);
		subTextLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 10));
		subTextLabel.setBounds(0, this.getSize().height * 9/ 12, this.getWidth(), this.getSize().height * 3 / 12);
		this.add(subTextLabel);

		//add a layered-pane for the cars
		layered = new JLayeredPane();
		layered.setBounds(0, 0, this.getWidth(), this.getHeight());
		carLabels = new CarLabel[6];
		for (int i = 0; i < carLabels.length; i++) //make room for 6 cars
		{
			carLabels[i] = new CarLabel();
			layered.add(carLabels[i], 5 + i);
			carLabels[i].setBounds(3 + 5 * i, 3 + 5 * i, 40, 21);
		}
		this.add(layered);

		//if an image was selected in the Builder, add it to a label
		if(b.img != null)
		{
			picture = ImageFactory.CreateImage(b.img);
			pictureLabel = new JLabel(new ImageIcon(picture));
			pictureLabel.setBounds(this.getSize().width / 12, this.getSize().height * 4/ 12, this.getSize().width * 10/ 12, this.getSize().height * 5/ 12);
			this.add(pictureLabel);
		}
		//store the description from the Builder
		this.description = b.description;

		//Create a pop-up field associated with this field
		popUpField = new PopUpField(this);
		popUpField.setAlwaysOnTop(true);
	}
	
	protected void setPicture(String pic)
	{
		this.picture = ImageFactory.CreateImage(pic);
		this.pictureLabel = new JLabel(new ImageIcon(this.picture));
		this.popUpField.setPicture(pic);
	}

	protected void setCar(int cartype, int carNr, Color c)
	{
		carLabels[carNr - 1].setCar(cartype, c);
		carLabels[carNr - 1].setVisible(true);
	}
	
	protected void removeCar(int carNr)
	{
		carLabels[carNr - 1].setVisible(false);
	}

	//---------------------------------------------------------------------------------------------
	//Builder class for field
	//---------------------------------------------------------------------------------------------
	public static class Builder
	{
		private String title = "";
		private String subText = "";
		private String description = "";
		private String img = null;
		private Color bgColor = Color.WHITE;
		private static int fieldNumber = 1;

		public static final String BREWERY1 = "pics/Brewery1.jpg";
		public static final String BREWERY2 = "pics/Brewery2.jpg";
		public static final String CONES = "pics/Cones.jpg";
		public static final String FERRY1 = "pics/Ferry1.jpg";
		public static final String FERRY2 = "pics/Ferry2.jpg";
		public static final String FERRY3 = "pics/Ferry3.jpg";
		public static final String FERRY4 = "pics/Ferry4.jpg";
		public static final String GOTOJAIL = "pics/GoToJail.jpg";
		public static final String JAIL = "pics/Jail.jpg";
		public static final String PRØVLYKKEN = "pics/Prøv_lykken_small.png";
		public static final String HOUSE1 = "pics/1House.png";
		public static final String HOUSE2 = "pics/2House.png";
		public static final String HOUSE3 = "pics/3House.png";
		public static final String HOUSE4 = "pics/4House.png";
		public static final String HOTEL = "pics/Hotel.png";


		public Builder(){}

		public Builder setTitle(String title)
		{
			this.title = title;
			return this;
		}

		public Builder setSubText(String subText)
		{
			this.subText = subText;
			return this;
		}

		public Builder setDescription(String description)
		{
			this.description = description;
			return this;
		}

		public Builder setBGColor(Color c)
		{
			this.bgColor = c;
			return this;
		}

		public Builder setPicture(String imgID)
		{
			this.img = imgID;
			return this;
		}

		public FieldPanel build()
		{
			return new FieldPanel(this);
		}
	}

	//---------------------------------------------------------------------------------------------
	//mouse event handling
	//---------------------------------------------------------------------------------------------
	@Override
	public void mouseEntered(MouseEvent e)
	{
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		Point p = MouseInfo.getPointerInfo().getLocation();
		int x = 20;
		int y = 20;
		if((p.getX() + x + 250) > screenDim.getWidth())
			x = -270;
		if((p.getY() + y + 260) > screenDim.getHeight())
			y = -280;
		this.popUpField.setBounds((int) p.getX() + x, (int) p.getY() + y, 250, 260);
		this.popUpField.setVisible(true);
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		this.popUpField.setVisible(false);
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		Point p = MouseInfo.getPointerInfo().getLocation();
		int x = 20;
		int y = 20;
		if((p.getX() + x + 250) > screenDim.getWidth())
			x = -270;
		if((p.getY() + y + 260) > screenDim.getHeight())
			y = -280;
		this.popUpField.setBounds((int) p.getX() + x, (int) p.getY() + y, 250, 260);
	}

	//---------------------------------------------------------------------------------------------
	//Unused mouse event handlers
	//---------------------------------------------------------------------------------------------

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

}
