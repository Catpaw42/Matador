package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;

@SuppressWarnings("serial")
public class FieldPanel extends JPanel implements MouseMotionListener , MouseListener
{
	private JLabel pictureLabel;

	protected JLabel subTextLabel;
	protected PopUpField popUpField;
	protected BufferedImage picture;
	protected String description;
	protected final int FieldNumber;
	protected JLabel titleLabel;

	private JLayeredPane layered;
	private CarLabel carLabels[];

	@SuppressWarnings("static-access")
	private FieldPanel(Builder b)
	{
		this.FieldNumber = b.fieldNumber;
		b.fieldNumber++;
		this.setLayout(null);
		this.setSize(75, 62);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setTransferHandler(new TransferHandler("icon"));
		this.setBackground(b.bgColor);

		new MyDropTargetListener(this);

		titleLabel = new JLabel("<html>" +  b.title + "</html>",SwingConstants.CENTER);
		titleLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 10));
		titleLabel.setBounds(0, 0, this.getWidth(), this.getSize().height * 4 / 12);
		this.add(titleLabel);


		subTextLabel = new JLabel("<html>" +  b.subText + "</html>",SwingConstants.CENTER);
		subTextLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 10));
		subTextLabel.setBounds(0, this.getSize().height * 9/ 12, this.getWidth(), this.getSize().height * 3 / 12);
		this.add(subTextLabel);

		layered = new JLayeredPane();
		layered.setBounds(0, 0, this.getWidth(), this.getHeight());
		carLabels = new CarLabel[6];
		for (int i = 0; i < carLabels.length; i++)
		{
			carLabels[i] = new CarLabel();
			layered.add(carLabels[i], 5 + i);
			carLabels[i].setBounds(3 + 5 * i, 3 + 5 * i, 40, 21);
		}

		this.add(layered);

		if(b.img != null)
		{
			picture = CreateImage(b.img);
			pictureLabel = new JLabel(new ImageIcon(picture));
			pictureLabel.setBounds(this.getSize().width / 12, this.getSize().height * 4/ 12, this.getSize().width * 10/ 12, this.getSize().height * 5/ 12);
			this.add(pictureLabel);
		}

		this.description = b.description;

		popUpField = new PopUpField(this);
		popUpField.setAlwaysOnTop(true);
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

	protected void setCar(int cartype, int layer, Color c)
	{
		carLabels[layer].setCar(cartype, c);
	}

	class MyDropTargetListener extends DropTargetAdapter {

		private DropTarget dropTarget;
		private JPanel panel;

		public MyDropTargetListener(JPanel panel) {
			this.panel = panel;

			dropTarget = new DropTarget(panel, DnDConstants.ACTION_COPY, 
					this, true, null);
		}


		public void drop(DropTargetDropEvent event) {
			try
			{
				System.out.println("we're almost there!!");
				pictureLabel.setIcon(new ImageIcon(CreateImage(Builder.HOUSE1)));
				Transferable tr = event.getTransferable();
				JComponent com = (JComponent) tr.getTransferData(TransferableObject.componentFlavor);
				
				
				if (event.isDataFlavorSupported(TransferableObject.componentFlavor)) {

					event.acceptDrop(DnDConstants.ACTION_COPY);
					System.out.println("lalala");
					event.dropComplete(true);
					return;
				}
				event.rejectDrop();
			} catch (Exception e) {
				e.printStackTrace();
				event.rejectDrop();
			}
		}
	}

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



	@Override
	public void mouseEntered(MouseEvent e)
	{
		Point p = MouseInfo.getPointerInfo().getLocation();
		this.popUpField.setBounds((int) p.getX() +20, (int) p.getY() + 20, 250, 260);
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
		Point p = MouseInfo.getPointerInfo().getLocation();
		this.popUpField.setBounds((int) p.getX() +20, (int) p.getY() + 20, 250, 260);
	}

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
