package Views;

import Models.Entities.Entity;
import Models.Entities.Stats.Stat;
import Utilities.Constants;
import Views.View;

import java.awt.*;

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
        Graphics g = viewContent.getGraphics();

        int x = width/4;
        int y = height * 10/12 + height/48;
        g.setColor(Color.WHITE);
        g.drawRect(x + 10, y - 5, width/5, height/24);

        this.setChanged();
        this.notifyObservers();

        g.dispose();
    }
}
