package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PopUpField extends JFrame
{
	private JPanel backgroundPanel;
	private JLabel pictureLabel;
	private JLabel titleLabel;
	private JLabel subtextLabel;
	private JLabel desciptionLabel;
	
	public PopUpField(FieldPanel fp)
	{
		
		this.pictureLabel = new JLabel(fp.pictureLabel.getIcon());
		this.titleLabel = new JLabel(fp.titleLabel.getText());
		this.subtextLabel = new JLabel(fp.subTextLabel.getText());
		this.desciptionLabel = new JLabel(fp.description);
		this.backgroundPanel = new JPanel();
		
		backgroundPanel.setLayout(null);
		
		this.add(backgroundPanel);
		this.setSize(600, 400);
		this.setResizable(false);
		
		this.setUndecorated(true);
		this.setVisible(false);
	}
}
