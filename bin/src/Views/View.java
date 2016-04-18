package Views;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Bradley on 4/4/2016.
 */
public abstract class View{

    protected int width;
    protected int height;
    protected BufferedImage viewContent;


    public View (int width, int height){

        this.width = width;
        this.height = height;
        viewContent = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    protected abstract void repaint();

    // Render will be called every tick and will just display the buffered image (so that it doesnt have to get re constructed every time)
    public void render(Graphics g){
        repaint();
        g.drawImage(viewContent, 0, 0, width, height, null);
    }

    public BufferedImage getViewContent(){
        return viewContent;
    }

}
