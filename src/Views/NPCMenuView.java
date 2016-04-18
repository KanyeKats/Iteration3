package Views;

import Models.Menu.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by Josh on 4/15/2016.
 */
public class NPCMenuView extends SubMenuView {

    private final String TITLE = "NPC Menu";
    private int optionWidth;
    private int optionHeight;
    private Font titleFont;
    private Font optionFont;
    private Color background;
    private Color color1;
    private Color color2;
    private int borderRadius;
    private int optionVerticalSpacing;
    private int optionHorizontalSpacing;
    private int yStart;
    private int xStart;
    private int menuHeight;
    private int menuWidth;

    public NPCMenuView(int width, int height, Models.Menu.Menu menu, BufferedImage lastViewContent) {
        super(width,height,menu, lastViewContent);

        yStart = height * 4/5;
        xStart = width * 3/10;
        menuHeight = height/5;
        menuWidth = width * 4/10;
        optionWidth = width / 8;
        optionHeight = height / 25;
        titleFont = new Font("SansSerif", Font.BOLD, width / 12);
        optionFont = new Font("SansSerif", Font.BOLD, width / 86);
        optionVerticalSpacing = optionHeight;
        optionHorizontalSpacing = width/4;
        background = new Color(70, 70, 70);
        color2 = Color.gray;
        color1 = Color.white;
        borderRadius = 10;

        repaint();

    }

    @Override
    public void renderBackground() {
        // Extract the graphics from the view content
        Graphics2D g = (Graphics2D)viewContent.getGraphics();
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(rh);

        // Draw a background
        g.setColor(background);
        g.fillRect(xStart, yStart, menuWidth, menuHeight);

        g.dispose();
    }

    @Override
    public void renderOptions() {

        Graphics2D g = (Graphics2D)viewContent.getGraphics();

        //AA
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(rh);

        if(titleFont==null){
            System.out.println("ITS NULL!");
        }
        //int verticalStart = g.getFontMetrics(titleFont).getHeight() + optionVerticalMargin;
        int verticalStart = yStart + menuHeight/5;

        // Set the font
        g.setFont(optionFont);
        FontMetrics fm = g.getFontMetrics(optionFont);

        for(int i=0; i < menu.getMenuOptions().size(); i++){

            // Get the current option;
            MenuOption option = menu.getMenuOptions().get(i);

            // See if this is the selected option.
            Color primaryColor = (menu.getSelectedIndex() == i) ? color1 : color2;
            Color secondaryCOlor = (menu.getSelectedIndex() == i) ?  color2: color1;

            // Determine the dimensions of the box.
            Rectangle2D optionRect = fm.getStringBounds(option.getTitle(), g);

            int optionsPerColumn = (int)Math.ceil(menu.getMenuOptions().size() / 2);

            int boxX = width*3/8 - optionWidth/2 + i / optionsPerColumn * optionHorizontalSpacing;
            int boxY = optionHeight * (i%optionsPerColumn) + verticalStart + (i%optionsPerColumn)* optionVerticalSpacing;

            int stringX = width*3/8 - (int)(optionRect.getWidth() / 2) + i / optionsPerColumn * optionHorizontalSpacing;
            int stringY = (i%optionsPerColumn) * optionHeight + (int)(optionRect.getHeight() /2) + fm.getAscent() + verticalStart + (i%optionsPerColumn)*optionVerticalSpacing;

            // draw the option.
            g.setColor(primaryColor);
            g.fillRoundRect(boxX, boxY, optionWidth, optionHeight, borderRadius, borderRadius);
            g.setColor(secondaryCOlor);
            g.drawString(option.getTitle(), stringX, stringY);
        }
        g.dispose();
    }
}
