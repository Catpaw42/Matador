package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PopUpField extends JFrame
{
	private JPanel backgroundPanel;
	private JLabel pictureLabel;
	private JLabel titleLabel;
	private JLabel desciptionLabel;
	private JLabel fieldNumberLabel;
	private JLabel ownerLabel;
	private JLabel priceLabel;
	private JLabel rentLabel;
	private String priceText;
	private String price;
	private String rent;
	
	protected PopUpField(final FieldPanel fp)
	{
		//managethis frame
		this.setSize(250, 260);
		this.setResizable(false);
		this.setFocusableWindowState(false); //we do'nt want this frame to grab focus as its only here to display data
		this.setUndecorated(true); //we do'nt want a border / buttons
		
		//add a background panel
		backgroundPanel = new JPanel();
		backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		backgroundPanel.setLayout(null);
		backgroundPanel.setBackground(fp.getBackground());
		this.add(backgroundPanel);
		
		//add a titleLabel
		titleLabel = new JLabel(fp.titleLabel.getText());
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		titleLabel.setBounds(this.getWidth() * 2 / 20, this.getHeight() * 2/20, this.getWidth() * 16/20, this.getHeight() * 2/20);
		backgroundPanel.add(titleLabel);
		
		//add a descriptionLabel
		desciptionLabel = new JLabel(fp.description);
		desciptionLabel.setVerticalAlignment(SwingConstants.TOP);
		desciptionLabel.setBounds(0, this.getHeight() * 14/20, this.getWidth(), this.getHeight() * 6/20 );
		backgroundPanel.add(desciptionLabel);
		
		//add a fieldnumberLabel
		fieldNumberLabel = new JLabel("" + fp.fieldNumber);
		fieldNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fieldNumberLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		fieldNumberLabel.setBounds(this.getWidth() * 8/20, 0, this.getWidth() * 4/20, this.getHeight() * 2/20);
		backgroundPanel.add(fieldNumberLabel);
		
		//add an ownerLabel
		ownerLabel = new JLabel();
		ownerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ownerLabel.setBounds(0, this.getHeight() * 6/20, this.getWidth() * 6/20, this.getHeight() * 4 / 20);
		backgroundPanel.add(ownerLabel);
		
		//add a priceLabel
		priceLabel = new JLabel();
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceLabel.setBounds(this.getWidth() * 14/20, this.getHeight() * 6/20, this.getWidth() * 6/20, this.getHeight() * 4 / 20);
		backgroundPanel.add(priceLabel);
		
		//add a rentLabel
		rentLabel = new JLabel();
		rentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rentLabel.setBounds(0, this.getHeight() * 12/20, this.getWidth() * 6/20, this.getHeight() * 4 / 20);
		backgroundPanel.add(rentLabel);

		//if there's a picture on the associated field, add it here using a custom JLabel to scale it up
		if(fp.picture != null)
		{
			this.pictureLabel = new JLabel()
			{
				@Override
				public void paintComponent(Graphics g)
				{
					super.paintComponent(g);
					if (fp.picture != null)
					{
						g.drawImage(fp.picture, 0, 0, fp.picture.getWidth() * 2, fp.picture.getHeight() * 2, null);
					}
				}
			};
			pictureLabel.setBounds((this.getWidth() * 1 / 2) - (fp.picture.getWidth()), this.getHeight() * 5 / 20, fp.picture.getWidth() * 2, fp.picture.getHeight() * 2);
			backgroundPanel.add(pictureLabel);
		}
		this.setVisible(false);
	}
	
	protected void setPicture(String pic)
	{
		final BufferedImage picture = ImageFactory.CreateImage(pic);
		this.pictureLabel = new JLabel()
		{
			@Override
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				if (picture != null)
				{
					g.drawImage(picture, 0, 0, picture.getWidth() * 2, picture.getHeight() * 2, null);
				}
			}
		};
		pictureLabel.setBounds((this.getWidth() * 1 / 2) - (picture.getWidth()), this.getHeight() * 5 / 20, picture.getWidth() * 2, picture.getHeight() * 2);
		backgroundPanel.add(pictureLabel);
	}
	
	protected void setTitleText(String text)
	{
		this.titleLabel.setText(text);
	}
	
	protected void setOwner(String text)
	{
		this.ownerLabel.setText("<html>" + "Owner:" + "<br>" + text + "</html>");
	}
	
	protected void setPrice(String price)
	{
		this.price = price;
		this.priceLabel.setText("<html>" + "Price" + ":" + "<br>" + this.price + "</html>"); // change from 'this.priceText' to '"Price"'
	}
	
	protected void setRent(String rent)
	{
		this.rent = rent;
		this.rentLabel.setText("<html>" + "Rent" + ":" + "<br>" + this.rent + "</html>");
	}
	
	protected void setPriceText(String priceText)
	{
		this.priceText = priceText;
		this.priceLabel.setText("<html>" + this.priceText + ":" + "<br>" + this.price + "</html>");
	}
	
	protected void setDescription(String text)
	{
		text = text.replaceAll("\n", "<br>");
		this.desciptionLabel.setText("<html>" + text + "</html>");
	}	
}
