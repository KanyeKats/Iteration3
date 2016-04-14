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
public class StatusViewPort extends View {

    private Entity avatar;

    public StatusViewPort(int width, int height, Entity avatar) {
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

        int x = width * 3/10;
        int y = height * 4/5;
        g.setColor(new Color(70, 70, 70));
        g.fillRect(x, y, width * 4/10, height/5);

        Font font = new Font("ROMAN_BASELINE",Font.BOLD,17);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("HEALTH:", x + 7,y + 31);
        g.drawString("MANA: ", x + 7, y + 88);

        //Health
        int healthBarx = width/2;
        int healthBary = height * 13/16;
        int healthBarwidth = width/4;
        int healthBarheight = height/16;
        double health = avatar.getStats().getStat(Stat.HEALTH);
        double maxHealth = avatar.getStats().getMaxHealth();
        g.setColor(Color.BLACK);
        g.drawRect(healthBarx - healthBarwidth/2,healthBary, healthBarwidth,healthBarheight);
        g.setColor(Color.RED);
        g.drawRect(healthBarx - healthBarwidth/2,healthBary, (int)(healthBarwidth * (health/maxHealth)),healthBarheight);
        g.fillRect(healthBarx - healthBarwidth/2,healthBary, (int)(healthBarwidth * (health/maxHealth)),healthBarheight);

        //Mana
        int manaBarx = width/2;
        int manaBary = height * 13/16 + healthBarheight;
        int manaBarwidth = width/4;
        int manaBarheight = height/16;
        double mana = avatar.getStats().getStat(Stat.MANA);
        double maxMana = avatar.getStats().getMaxMana();
        g.setColor(Color.BLACK);
        g.drawRect(manaBarx - manaBarwidth/2,manaBary, manaBarwidth,manaBarheight);
        g.setColor(Color.BLUE);
        g.drawRect(manaBarx - manaBarwidth/2,manaBary, (int)(manaBarwidth * (health/maxHealth)),manaBarheight);
        g.fillRect(manaBarx - manaBarwidth/2,manaBary, (int)(manaBarwidth * (health/maxHealth)),manaBarheight);


        this.setChanged();
        this.notifyObservers();

        g.dispose();
    }
}
