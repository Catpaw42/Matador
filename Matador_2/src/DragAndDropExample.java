import java.awt.FlowLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

@SuppressWarnings("serial")
public class DragAndDropExample extends JLabel implements DropTargetListener {
    DragAndDropExample(final String name) {
        super(name);
        new DropTarget(this, this);
        this.setTransferHandler(new TransferHandler("text"));
        final MouseListener listener = new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent me) {
                final JLabel comp = (JLabel) me.getSource();
                System.out.println(comp);

                final TransferHandler handler = comp.getTransferHandler();
                handler.exportAsDrag(comp, me, TransferHandler.COPY);
            }
        };
        this.addMouseListener(listener);
    }

    @Override
    public void dragEnter(final DropTargetDragEvent dtde) {
        // TODO Auto-generated method stub

    }

    @Override
    public void dragExit(final DropTargetEvent dte) {
        // TODO Auto-generated method stub

    }

    @Override
    public void dragOver(final DropTargetDragEvent dtde) {
        // TODO Auto-generated method stub

    }

    @Override
    public void drop(final DropTargetDropEvent dtde) {

        // DropTarget dt = (DropTarget) dtde.getSource();
        // NameSlot ns = (NameSlot) dt.getComponent();

        try {
            final String s = (String) dtde.getTransferable().getTransferData(
                    new DataFlavor("application/x-java-jvm-local-objectref; class=java.lang.String"));

            System.out.println("drop detected from " + s + " to " + this.getText());
        }
        catch (final UnsupportedFlavorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (final ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void dropActionChanged(final DropTargetDragEvent dtde) {
        // TODO Auto-generated method stub

    }

    public static void main(final String[] args) {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        final DragAndDropExample ns = new DragAndDropExample("test");
        frame.add(ns);

        final DragAndDropExample ns2 = new DragAndDropExample("test2");
        frame.add(ns2);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}