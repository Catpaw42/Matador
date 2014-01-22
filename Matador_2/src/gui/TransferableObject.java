package gui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

import javax.swing.JComponent;

class TransferableObject implements Transferable
{	 
    protected static DataFlavor componentFlavor =
        new DataFlavor(JComponent.class, "A JComponent Object");

    protected static DataFlavor[] supportedFlavors = {
    	componentFlavor,
        DataFlavor.stringFlavor,
    };

    String text;

    public TransferableObject(String s) {this.text = s;}

    public DataFlavor[] getTransferDataFlavors() { return supportedFlavors; }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
    if (flavor.equals(componentFlavor) || 
        flavor.equals(DataFlavor.stringFlavor)) return true;
    return false;
  }


   public Object getTransferData(DataFlavor flavor) 
        throws UnsupportedFlavorException
   {
     if (flavor.equals(componentFlavor))
         return text;
     else if (flavor.equals(DataFlavor.stringFlavor)) 
         return text;
     else 
         throw new UnsupportedFlavorException(flavor);
   }
}