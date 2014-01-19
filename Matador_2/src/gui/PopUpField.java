package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

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
	
	protected PopUpField(final FieldPanel fp)
	{
		backgroundPanel = new JPanel();
		backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		titleLabel = new JLabel(fp.titleLabel.getText());
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		desciptionLabel = new JLabel(fp.description);
		desciptionLabel.setVerticalAlignment(SwingConstants.TOP);
		fieldNumberLabel = new JLabel(""+fp.FieldNumber);
		fieldNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fieldNumberLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		ownerLabel = new JLabel();
		ownerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceLabel = new JLabel();
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.setSize(250, 260);
		this.add(backgroundPanel);
		this.setResizable(false);
		this.setFocusableWindowState(false);
		this.setUndecorated(true);

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
						g.drawImage((Image)fp.picture, 0, 0, fp.picture.getWidth() * 2, fp.picture.getHeight() * 2, null);
					}
				}
			};
			pictureLabel.setBounds((this.getWidth() * 1 / 2) - (fp.picture.getWidth()), this.getHeight() * 5 / 20, fp.picture.getWidth() * 2, fp.picture.getHeight() * 2);
//			pictureLabel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
			backgroundPanel.add(pictureLabel);
		}
		
		backgroundPanel.setLayout(null);
		backgroundPanel.add(titleLabel);
		backgroundPanel.add(desciptionLabel);
		backgroundPanel.add(fieldNumberLabel);
		backgroundPanel.add(ownerLabel);
		backgroundPanel.add(priceLabel);
		backgroundPanel.setBackground(fp.getBackground());
		
		fieldNumberLabel.setBounds(this.getWidth() * 8/20, 0, this.getWidth() * 4/20, this.getHeight() * 2/20);
		titleLabel.setBounds(this.getWidth() * 2 / 20, this.getHeight() * 2/20, this.getWidth() * 16/20, this.getHeight() * 2/20);
		desciptionLabel.setBounds(0, this.getHeight() * 14/20, this.getWidth(), this.getHeight() * 6/20 );
		ownerLabel.setBounds(0, this.getHeight() * 10/20, this.getWidth() * 6/20, this.getHeight() * 4 / 20);
		priceLabel.setBounds(this.getWidth() * 14/20, this.getHeight() * 10/20, this.getWidth() * 6/20, this.getHeight() * 4 / 20);
		
//		ownerLabel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
//		priceLabel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
//		titleLabel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
//		desciptionLabel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
//		fieldNumberLabel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		this.setDescription("hej jeg er en ny testmetode, lad os lege teste leg i haven!! Her i havernes have har vi i dag havernes ikke særlig mange folk at lege med");
		this.setVisible(false);
	}
	
	
	protected void setTitleText(String text)
	{
		this.titleLabel.setText(text);
	}
	
	protected void setOwner(String text)
	{
		this.ownerLabel.setText("<html>" + "Owner:" + "<br>" + text + "</html>");
	}
	
	protected void setPrice(String price, String priceType)
	{
		this.priceLabel.setText("<html>" + priceType + ":" + "<br>" + price + "</html>");
	}
	
	protected void setDescription(String text)
	{
		text.replaceAll("\n", "<br>"); //works???
		this.desciptionLabel.setText("<html>" + text + "</html>");
	}
	
	
}
