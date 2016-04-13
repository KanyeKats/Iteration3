package Views;

import Models.Entities.Stats.Stat;
import Models.Entities.Stats.Stats;
import Models.Menu.Menu;
import Models.Menu.MenuOption;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Josh on 4/13/2016.
 */
public class SkillViewPort extends MenuView {

    private final String TITLE = "Skills";
    private int optionWidth;
    private int optionHeight;
    private Font titleFont;
    private Font optionFont;
    private int optionVerticalMargin;
    private Color background;
    private Color color1;
    private Color color2;
    private int borderRadius;
    private int optionVerticalSpacing;
    private Stats stats;

    public SkillViewPort(int width, int height, Menu menu, Stats stats) {
        super(width, height, menu);

        this.stats = stats;
        optionWidth = width / 6;
        optionHeight = height / 25;
        titleFont = new Font("SansSerif", Font.BOLD, width / 12);
        optionFont = new Font("SansSerif", Font.BOLD, width / 86);
        optionVerticalMargin = (int) (width * 0.15);
        optionVerticalSpacing = 10;
        background = Color.black;
        color2 = Color.gray;
        color1 = Color.white;
        borderRadius = 10;

        repaint();
    }

    public void renderBackground(){

        // Extract the graphics from the view content
        Graphics2D g = (Graphics2D)viewContent.getGraphics();
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(rh);

        // Draw a black background
        g.setColor(background);
        g.fillRect(0, 0, width, height);

        // Draw the title.
        drawTitle(g);
        g.dispose();
    }

    public void renderOptions(){
        Graphics g = viewContent.getGraphics();

        // Find the starting point.
        if(titleFont==null){
            System.out.println("ITS NULL!");
        }
        int verticalStart = g.getFontMetrics(titleFont).getHeight() + optionVerticalMargin;

        // Set the font
        g.setFont(optionFont);
        FontMetrics fm = g.getFontMetrics(optionFont);

        int i;
        for(i=0; i < menu.getMenuOptions().size(); i++){

            // Get the current option;
            MenuOption option = menu.getMenuOptions().get(i);

            // See if this is the selected option.
            Color primaryColor = (menu.getSelectedIndex() == i) ? color1 : color2;
            Color secondaryCOlor = (menu.getSelectedIndex() == i) ?  color2: color1;

            // Determine the dimensions of the box.
            Rectangle2D optionRect = fm.getStringBounds(option.getTitle(), g);

            int boxX = width/2 - optionWidth/2;
            int boxY = optionHeight * i + verticalStart + i* optionVerticalSpacing;

            int stringX = width/2 - (int)(optionRect.getWidth() / 2);
            int stringY = i * optionHeight + (int)(optionRect.getHeight() /2) + fm.getAscent() + verticalStart + i*optionVerticalSpacing;

            // draw the option.
            g.setColor(primaryColor);
            g.fillRoundRect(boxX, boxY, optionWidth, optionHeight, borderRadius, borderRadius);
            g.setColor(secondaryCOlor);
            g.drawString(option.getTitle(), stringX, stringY);
        }

        //Write out how many skill points there are
        String skillPointString = "You have " + stats.getStat(Stat.SKILL_POINTS) + " skill points remaining.";
        Rectangle2D stringRect = fm.getStringBounds(skillPointString, g);
        int stringX = width/2 - (int)(stringRect.getWidth() / 2);
        int stringY = (i+1) * optionHeight + (int)(stringRect.getHeight() /2) + fm.getAscent() + verticalStart + (i+1)*optionVerticalSpacing;
        g.drawString(skillPointString, stringX, stringY);

        g.dispose();
    }

    private void drawTitle(Graphics g){
        g.setFont(titleFont);
        FontMetrics fm = g.getFontMetrics();

        int titleWidth = fm.stringWidth(TITLE);
        int x = width/2 - titleWidth/2;
        int y = fm.getHeight();

        g.setColor(color1);
        g.drawString(TITLE, x, y);
    }
}
