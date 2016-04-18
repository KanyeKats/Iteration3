package Views;

import Models.Entities.Entity;
import Models.Entities.Stats.Stat;
import Models.Map.Tile;
import Utilities.Constants;
import javafx.geometry.Point3D;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by sergiopuleri on 4/17/16.
 */
public class DamageToasts extends View {

    // Message, and enitity to draw on
    private static class DamageMessage {
        int damage;
        Entity entity;

        public DamageMessage(int damage, Entity entity) {
            this.damage = damage;
            this.entity = entity;
        }

    }

    private static Font font = new Font("SansSerif", Font.BOLD, 14);
    private BufferedImage clear;
    private static ArrayList<DamageMessage> currentMessages = new ArrayList<>();
    private final String MSG_STRING = " HEALTH";
    private static Entity avatar;

    public DamageToasts(int width, int height, Entity avatar) {
        super(width, height);

        clear = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.avatar = avatar;
    }

    public static void addDamageToast(int damage, Entity entity) {
        int dmg = getDamageToDisplay(damage);
        DamageMessage newDmgMsg = new DamageMessage(dmg, entity);
        currentMessages.add(newDmgMsg);

        // After 3 seconds, remove the dmg message
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                currentMessages.remove(newDmgMsg);
            }
        }, 1500);
    }


    private void renderMessages() {
        viewContent.setData(clear.getRaster());
        Graphics2D g = (Graphics2D)viewContent.getGraphics();

        for (DamageMessage message : currentMessages) {
            // Get ready to draw the message
            g.setFont(font);

            // Draw the title
            g.setColor(Color.white);

            Point pixelPoint = message.entity.getPixelLocation();
            int x = (int)pixelPoint.getX() + message.entity.getImage().getWidth(null);
            int y = (int)pixelPoint.getY();
            y += Constants.TILE_HEIGHT/2;

            if (pixelPoint != null) {
                // Get sign
                String sign = message.damage > 0 ? "+" : "";

                // msg 2 display
                String messageToDisplay = sign + message.damage + MSG_STRING;

                // Draw it
                g.drawString(messageToDisplay, x, y);
            }
        }

    }

    private static int getObservationLevel() {
        return avatar.getStats().getStat(Stat.OBSERVATION);
    }

    private static int getDamageToDisplay(int damage) {
        int max = 100;
        int min = -100;

        // Get random modifier
        Random rand = new Random();
        int modifier = rand.nextInt(max - min + 1) + min;

        return damage + (modifier/getObservationLevel());
    }


    @Override
    protected void repaint() {
        renderMessages();
    }
}
