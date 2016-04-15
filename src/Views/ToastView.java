package Views;


import Utilities.Toast;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Magic_Buddha on 4/14/2016.
 */
public class ToastView extends View {

    private Font toastFont;
    private String message;
    private int toastWidth;
    private int toastHeight;
    private int messageWidth;
    private int messageHeight;

    private Toast toast;

    public ToastView(int width, int height) {
        super(width,height);

        toast = Toast.getInstance();

        toast.addObserver(this);

        toastFont = new Font("Curier New", Font.PLAIN, 12);

        repaint();
    }

    @Override
    protected void repaint() {
        renderToast();
    }

    private void renderToast() {
//        System.out.println("in renderToast method before isActive check");
        Graphics2D g2d = (Graphics2D) viewContent.getGraphics();
        if (toast.isActive()) {
            RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHints(rh);

            message = toast.getCurrentMessage();
            System.out.println("MESSAGE: " + message);
            FontMetrics fm = g2d.getFontMetrics(toastFont);
            Rectangle2D rect = fm.getStringBounds(message, g2d);
            messageWidth = (int) rect.getWidth();
            messageHeight = (int) rect.getHeight();

            toastHeight = messageHeight * 2;
            toastWidth = (int) (messageWidth * 1.5);

            g2d.setColor(new Color(64, 64, 64, 200));
            g2d.fillRoundRect(150,150,toastWidth,toastHeight, 50, 50);

            g2d.setColor(Color.WHITE);
            g2d.setFont(toastFont);
            g2d.drawString(message, 180,170);
        } else {

            g2d.setComposite(
                    AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
            Rectangle2D.Double rect =
                    new Rectangle2D.Double(0,0,width,height);
            g2d.fill(rect);
            setChanged();
            notifyObservers();
        }
    }
}
