package Views;

import Models.Entities.Entity;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.Stats;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by Aidan on 4/12/2016.
 */
public class StatusViewPort extends View {

    private Entity avatar;
    private Stats stats;
    private BufferedImage clear;

    public StatusViewPort(int width, int height, Entity avatar) {
        super(width, height);

        this.avatar = avatar;
        this.stats = avatar.getStats();
        clear = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        repaint();
    }

    @Override
    public void repaint() {
        renderStats();
    }

    public void renderStats() {
        Graphics2D g = (Graphics2D) viewContent.getGraphics();
        viewContent.setData(clear.getRaster());
        //aa
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(rh);

        // Set up some useful variables.
        int marginVertical = 16;
        int marginHorizontal = 20;
        int borderRadius = 12;

        Font primaryFont = new Font("Courier New", 1, 18);
        Font secondaryFont = new Font("Courier New", 1, 14);


        // DRAW THE HEALTH BAR

        // Get the necessary stats
        int health = stats.getStat(Stat.HEALTH);
        int maxHealth = stats.getMaxHealth();


        // Determine how large text is and where to place the Health string
        FontMetrics fm = g.getFontMetrics(primaryFont);
        g.setFont(primaryFont);

        Rectangle2D healthRect = fm.getStringBounds("HP: ", g);
        int healthStringX = marginHorizontal;
        int healthStringY = marginVertical * 2;

        // Draw the health string.
        g.setColor(Color.WHITE);
        g.drawString("HP: ", healthStringX, healthStringY);

        // Set the location and size of the health bar.
        int healthBarWidth = 210;
        int healthBarHeight = 16;
        int healthBarX = healthStringX + (int) healthRect.getWidth();
        int healthBarY = healthStringY - (int) healthRect.getHeight()/2;

        // Determine what fraction of the health bar should be shown.
        double healthFraction = (double) health / (double) maxHealth;
        int healthFillWidth = (int) (healthFraction * healthBarWidth);

        // Fill the health bar
        g.setColor(Color.RED);
        g.fillRoundRect(healthBarX, healthBarY, healthFillWidth, healthBarHeight, borderRadius, borderRadius);

        // Draw the outline of the health bar.
        g.setColor(Color.WHITE);
        g.drawRoundRect(healthBarX - 1, healthBarY - 1, healthBarWidth, healthBarHeight, borderRadius, borderRadius);

        // Display the fraction of health
        g.setColor(Color.WHITE);
        String healthFractionString = "(" + health + "/" + maxHealth + ")";

        // Place the font at the right of the bar
        fm = g.getFontMetrics(secondaryFont);
        g.setFont(secondaryFont);
        Rectangle2D healthFractionRect = fm.getStringBounds(healthFractionString, g);

        int healthFractionX = healthBarX + healthBarWidth - (int) healthFractionRect.getWidth() + 15;
        int healthFractionY = healthBarY + healthBarHeight - 5;
        g.drawString(healthFractionString, healthFractionX - 17, healthFractionY);




        // DRAW THE MANA BAR

        // Get the necessary stats
        int mana = stats.getStat(Stat.MANA);
        int maxMana = stats.getMaxMana();


        // Determine how large text is and where to place the Health string
        fm = g.getFontMetrics(primaryFont);
        g.setFont(primaryFont);

        Rectangle2D manaRect = fm.getStringBounds("MP: ", g);
        int manaStringX = healthStringX;
        int manaStringY = healthStringY + healthBarHeight + marginVertical;

        // Draw the mana string.
        g.setColor(Color.WHITE);
        g.drawString("MP: ", manaStringX, manaStringY);

        // Set the location and size of the health bar.
        int manaBarWidth = 210;
        int manaBarHeight = 16;
        int manaBarX = manaStringX + (int) manaRect.getWidth();
        int manaBarY = manaStringY - (int) manaRect.getHeight()/2;

        // Determine what fraction of the health bar should be shown.
        double manaFraction = (double) mana / (double) maxMana;
        int manaFillWidth = (int) (manaFraction * manaBarWidth);

        // Fill the health bar
        g.setColor(Color.BLUE);
        g.fillRoundRect(manaBarX, manaBarY, manaFillWidth, manaBarHeight, borderRadius, borderRadius);

        // Draw the outline of the mana bar.
        g.setColor(Color.WHITE);
        g.drawRoundRect(manaBarX - 1, manaBarY - 1, manaBarWidth, manaBarHeight, borderRadius, borderRadius);

        // Display the fraction of health
        g.setColor(Color.WHITE);
        String manaFractionString = "(" + mana + "/" + maxMana + ")";

        // Place the font at the right of the bar
        fm = g.getFontMetrics(secondaryFont);
        g.setFont(secondaryFont);
        Rectangle2D manaFractionRect = fm.getStringBounds(manaFractionString, g);

        int manaFractionX = manaBarX + manaBarWidth - (int) manaFractionRect.getWidth() + 15;
        int manaFractionY = manaBarY + manaBarHeight - 4;
        g.drawString(manaFractionString, manaFractionX - 17, manaFractionY);







        // DRAW THE XP BAR

        // Get the necessary stats
        int xp = stats.getStat(Stat.EXPERIENCE);
        int expToLvl = stats.getExpRequiredToLevelUp();
        int expForCurrentLvl = stats.getExpForLevel(stats.getStat(Stat.LEVEL));

        // Determine how large text is and where to place the Health string
        fm = g.getFontMetrics(primaryFont);
        g.setFont(primaryFont);

        Rectangle2D xpRect = fm.getStringBounds("XP: ", g);
        int xpStringX = manaStringX;
        int xpStringY = manaStringY + manaBarHeight + marginVertical;

        // Draw the xp string.
        g.setColor(Color.WHITE);
        g.drawString("XP: ", xpStringX, xpStringY);

        // Set the location and size of the health bar.
        int xpBarWidth = 210;
        int xpBarHeight = 16;
        int xpBarX = xpStringX + (int) xpRect.getWidth();
        int xpBarY = xpStringY - (int) xpRect.getHeight()/2;

        // Determine what fraction of the health bar should be shown.
        double xpFraction = (double) (xp - expForCurrentLvl) / (double)(expToLvl - expForCurrentLvl);
        int xpFillWidth = (int) (xpFraction * xpBarWidth);

        // Fill the health bar
        g.setColor(new Color(255,153,0));
        g.fillRoundRect(xpBarX, xpBarY, xpFillWidth, xpBarHeight, borderRadius, borderRadius);

        // Draw the outline of the xp bar.
        g.setColor(Color.WHITE);
        g.drawRoundRect(xpBarX - 1, xpBarY - 1, xpBarWidth, xpBarHeight, borderRadius, borderRadius);

        // Display the fraction of health
        g.setColor(Color.WHITE);
        String xpFractionString = "(" + xp + "/" + expToLvl + ")";

        // Place the font at the right of the bar
        fm = g.getFontMetrics(secondaryFont);
        g.setFont(secondaryFont);
        Rectangle2D xpFractionRect = fm.getStringBounds(xpFractionString, g);

        int xpFractionX = xpBarX + xpBarWidth - (int) xpFractionRect.getWidth() + 15;
        int xpFractionY = xpBarY + xpBarHeight - 5;
        g.drawString(xpFractionString, xpFractionX - 17, xpFractionY);


        // Draw lives
        // Get the necessary stats
        int lives = avatar.getStats().getStat(Stat.LIVES);
        int level = avatar.getStats().getStat(Stat.LEVEL);

        // Determine how large text is and where to place the Health string
        fm = g.getFontMetrics(primaryFont);
        g.setFont(primaryFont);

        String livesString = "Level: " + level + "    ";

        livesString += "Lives: ";
        for(int i=0; i< lives; i++){
            livesString += "| ";
        }

        int livesStringX = xpStringX;
        int livesStringY = xpStringY + xpBarHeight + marginVertical;

        // Draw the lives string.
        g.setColor(Color.WHITE);
        g.drawString(livesString, livesStringX, livesStringY);



//        int x = width * 3/10;
//        int y = height * 4/5;
//        g.setColor(new Color(70, 70, 70));
//        g.fillRect(x, y, width * 4/10, height/5);
//
//        Font font = new Font("ROMAN_BASELINE",Font.BOLD,17);
//        g.setFont(font);
//        g.setColor(Color.WHITE);
//        g.drawString("HEALTH:", x + 7,y + 31);
//        g.drawString("MANA: ", x + 7, y + 88);
//
//        //Health
//        int healthBarx = width/2;
//        int healthBary = height * 13/16;
//        int healthBarwidth = width/4;
//        int healthBarheight = height/16;
//        double health = avatar.getStats().getStat(Stat.HEALTH);
//        double maxHealth = avatar.getStats().getMaxHealth();
//        g.setColor(Color.BLACK);
//        g.drawRect(healthBarx - healthBarwidth/2,healthBary, healthBarwidth,healthBarheight);
//        g.setColor(Color.RED);
//        g.drawRect(healthBarx - healthBarwidth/2,healthBary, (int)(healthBarwidth * (health/maxHealth)),healthBarheight);
//        g.fillRect(healthBarx - healthBarwidth/2,healthBary, (int)(healthBarwidth * (health/maxHealth)),healthBarheight);
//
//        //Mana
//        int manaBarx = width/2;
//        int manaBary = height * 13/16 + healthBarheight;
//        int manaBarwidth = width/4;
//        int manaBarheight = height/16;
//        double mana = avatar.getStats().getStat(Stat.MANA);
//        double maxMana = avatar.getStats().getMaxMana();
//        g.setColor(Color.BLACK);
//        g.drawRect(manaBarx - manaBarwidth/2,manaBary, manaBarwidth,manaBarheight);
//        g.setColor(Color.BLUE);
//        g.drawRect(manaBarx - manaBarwidth/2,manaBary, (int)(manaBarwidth * (mana/maxMana)),manaBarheight);
//        g.fillRect(manaBarx - manaBarwidth/2,manaBary, (int)(manaBarwidth * (mana/maxMana)),manaBarheight);

        g.dispose();
    }
}
