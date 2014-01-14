package gui;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class FieldPanel extends JPanel
{
	private JLabel subTextLabel;
	private JLabel titleLabel;
	private JLabel pictureLabel;
	private String description;

	private FieldPanel(Builder b)
	{
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		titleLabel = new JLabel("<html>" +  b.title + "</html>",SwingConstants.CENTER);
		this.add(titleLabel);
		
		subTextLabel = new JLabel("<html>" +  b.subText + "</html>",SwingConstants.CENTER);
		this.add(subTextLabel);
		
		if(b.img != null)
		{
			pictureLabel = new JLabel(CreateImageIcon(b.img));
			this.add(pictureLabel);
		}
		
		this.description = b.description;
		this.setBackground(b.bgColor);
	}
	
	protected void updatePositions()
	{
		titleLabel.setBounds(this.getSize().width / 12, this.getSize().height / 12, this.getSize().width * 10 / 12, this.getSize().height * 3 / 12);
		subTextLabel.setBounds(this.getSize().width / 12, this.getSize().height * 9/ 12, this.getSize().width * 10 / 12, this.getSize().height * 3 / 12);
		
		if(pictureLabel != null)
			pictureLabel.setBounds(this.getSize().width / 12, this.getSize().height * 4/ 12, this.getSize().width * 10/ 12, this.getSize().height * 5/ 12);
	}

	private ImageIcon CreateImageIcon(String img)
	{
		try
		{
			//get the image from the folder
			File f = new File(img);
			if(!f.exists())
				throw new FileNotFoundException("Can't locate image");
			
			BufferedImage image = ImageIO.read(f);
			
			return new ImageIcon(image);
		} catch (IOException ex)
		{
			System.err.println("Error loading the image");
			ex.printStackTrace();
		}
		//should not be reachable
		return null;
		
	}

	public static class Builder
	{
		private String title = "Dummy";
		private String subText = "dummySubText";
		private String description = "dummyDescription";
		private String img = null;
		private Color bgColor = Color.WHITE;
		
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

}
