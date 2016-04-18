package Models.Map.MapUtilities;

import Models.Entities.Entity;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.Stats;
import Utilities.Constants;


import java.awt.*;
import java.awt.geom.Rectangle2D;


/**
 * Created by sergiopuleri on 4/10/16.
 */
public class EntityDrawer {

    public static void drawEntity(Entity entity, Graphics g) {
        if(entity!=null && entity.getImage()!=null){
            int x = (int)entity.getPixelLocation().getX();
            int y = (int)entity.getPixelLocation().getY();
            Graphics2D g2 = (Graphics2D)g;

            if (!entity.isVisible()) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
            }
            drawComponent(entity.getImage(), g2, x , y);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            drawHealthBar(entity, g2, x, y);

        }
    }

    public static void drawComponent(Image image, Graphics2D g, int x, int y){

        int finalx = x + (Constants.TILE_WIDTH -  image.getWidth(null)) / 2;
        int finaly = y + (Constants.TILE_HEIGHT - image.getHeight(null)) / 2;
        g.drawImage(image, finalx, finaly, null);
    }

    private static void drawHealthBar(Entity entity, Graphics2D g,  int x, int y) {

        // Get healths
        Stats stats = entity.getStats();
        int health = stats.getStat(Stat.HEALTH);
        int maxHealth = stats.getStat(Stat.MAX_HEALTH);

        // Set health
        health = health > maxHealth ? maxHealth : health;
        health = health < 0 ? 0 : health;

        // Sizes
        int healthBarWidth = Constants.SCREEN_WIDTH / 13;
        int healthBarHeight = Constants.SCREEN_HEIGHT / 53;

        // Set the font
        Font f = new Font("Courier New", 1, 14);
        g.setFont(f);


        // Set the location and size of the health bar.
        int healthBarX = x;
        int healthBarY = y - healthBarHeight;


        // Draw the outline of the health bar.
        g.setColor(Color.RED);
        g.fillRect(healthBarX, healthBarY, healthBarWidth, healthBarHeight);

        // Determine what fraction of the healthbar should be shown.
        double healthFraction = (double) health / (double) maxHealth;
        int healthFillWidth = (int) (healthFraction * healthBarWidth);

        // Fill the healthbar
        g.setColor(Color.GREEN);
        g.fillRect(healthBarX, healthBarY, healthFillWidth, healthBarHeight);

        // Display the fraction of health
        g.setColor(Color.WHITE);
        String healthFractionString = "(" + health + "/" + maxHealth + ")";
        FontMetrics fm = g.getFontMetrics(f);

        // Place the font at the right of the bar
        Rectangle2D healthFractionRect = fm.getStringBounds(healthFractionString, g);

        int healthFractionX = healthBarX + (healthBarWidth - (int) healthFractionRect.getWidth()) / 2;
        int healthFractionY = healthBarY + healthBarHeight - 4;

        g.drawString(healthFractionString, healthFractionX, healthFractionY);
    }
}
