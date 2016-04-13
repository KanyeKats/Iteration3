package Views;

import Models.Entities.Entity;
import Models.Entities.Stats.Stat;
import Utilities.Constants;
import Views.View;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Aidan on 4/12/2016.
 */
public class StatusViewport extends View {

    private Entity avatar;

    public StatusViewport(int width, int height, Entity avatar) {
        super(width, height);

        this.avatar = avatar;
        avatar.addObserver(this);
        repaint();
    }

    @Override
    public void repaint() {
        renderStats();
    }

    public void renderStats() {
        Graphics2D g = (Graphics2D) viewContent.getGraphics();

        int x = 0;
        int y = height * 4/5;
        g.setColor(new Color(70, 70, 70));
        g.fillRect(x, y, width, height/5);

        this.setChanged();
        this.notifyObservers();

        g.dispose();
    }
}
