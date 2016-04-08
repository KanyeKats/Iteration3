package Views;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Bradley on 4/4/2016.
 */
public abstract class View extends Observable implements Observer{

    protected int width;
    protected int height;
    protected BufferedImage viewContent;

    public View (int width, int height){

        this.width = width;
        this.height = height;
        viewContent = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    protected abstract void repaint();

    // Render will be called every tick and will just display the buffered image (so that it doesnt have to get re constructed every time)
    public void render(Graphics g){
        g.drawImage(viewContent, 0, 0, width, height, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
