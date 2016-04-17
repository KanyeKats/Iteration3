package Views;

import Models.Menu.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Josh on 4/14/2016.
 */
public class ReconfigureKeysView extends MenuView {

    private final String TITLE = "Controls";
    private int optionWidth;
    private int optionHeight;
    private Font titleFont;
    private Font optionFont;
    private Font smallFont;
    private int titleStartX;
    private int titleStartY;
    private int titleWidth;
    private int titleHeight;
    private int optionVerticalMargin;
    private Color background;
    private Color color1;
    private Color color2;
    private int borderRadius;
    private int optionVerticalSpacing;
    private int optionHorizontalSpacing;

    public ReconfigureKeysView(int width, int height, Models.Menu.Menu menu) {
        super(width, height, menu);

        optionWidth = width / 6;
        optionHeight = height / 25;
        titleFont = new Font("SansSerif", Font.BOLD, width / 12);
        optionFont = new Font("SansSerif", Font.BOLD, width / 86);
        smallFont = new Font("Courier New", 1, width / 67);
        titleStartX = (int) (width * 0.1);
        titleStartY = (int) (height * 0.05);
        titleWidth = (int) (width * 0.8);
        titleHeight = (int) (height * 0.15);
        optionVerticalMargin = (int) (width * 0.07);
        optionVerticalSpacing = 10;
        optionHorizontalSpacing = width/5;
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

        g.dispose();
    }

    public void renderOptions(){

        Graphics2D g = (Graphics2D)viewContent.getGraphics();
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(rh);

        // Draw the title.
        drawTitle(g);

        // Get ready to draw the instructions
        g.setFont(smallFont);
        String instructions = "Press [Enter] and then a new key to change a control.";
        FontMetrics fm2 = g.getFontMetrics(smallFont);
        Rectangle2D instRect = fm2.getStringBounds(instructions, g);

        // Get the location of the instr
        int instMargin = 15;
        int instX = titleStartX + titleWidth / 2 - (int) instRect.getWidth() / 2;
        int instY = titleStartY + titleHeight - instMargin;

        // Draw the instr
        g.drawString(instructions, instX, instY);


        // Find the starting point.
        if(titleFont==null){
            System.out.println("ITS NULL!");
        }
        int verticalStart = g.getFontMetrics(titleFont).getHeight() + optionVerticalMargin;

        // Set the font
        g.setFont(optionFont);
        FontMetrics fm = g.getFontMetrics(optionFont);

        int i;

        int remainder = menu.getMenuOptions().size() % 3;
        int leftColTotal = menu.getMenuOptions().size() / 3;
        int centerColTotal = leftColTotal;
        if(remainder > 0){
            leftColTotal = (menu.getMenuOptions().size() / 3) + 1;
            if(remainder == 1)
                centerColTotal = menu.getMenuOptions().size() / 3;
            else
                centerColTotal = leftColTotal;
        }
        int centerColumnStart = leftColTotal;
        int rightColumnStart = leftColTotal + centerColTotal;


        for(i=0; i < menu.getMenuOptions().size(); i++){

            // Get the current option;
            MenuOption option = menu.getMenuOptions().get(i);

            // See if this is the selected option.
            Color primaryColor = (menu.getSelectedIndex() == i) ? color1 : color2;
            Color secondaryCOlor = (menu.getSelectedIndex() == i) ?  color2: color1;

            // Determine the dimensions of the box.
            Rectangle2D optionRect = fm.getStringBounds(option.getTitle(), g);
            //int optionsPerColumn = (int)Math.ceil(menu.getMenuOptions().size() / 3);

            int boxX = width/9 - optionWidth/2 + optionHorizontalSpacing;
            int boxY = optionHeight * (i%leftColTotal) + verticalStart + (i%leftColTotal)* optionVerticalSpacing;
            int stringX = width/9 - (int)(optionRect.getWidth() / 2) + optionHorizontalSpacing;
            int stringY = (i%leftColTotal) * optionHeight + (int)(optionRect.getHeight() /2) + fm.getAscent() + verticalStart + (i%leftColTotal)*optionVerticalSpacing;


            if(i >= rightColumnStart){
                boxX = width/9 - optionWidth/2 + 3*optionHorizontalSpacing;
                boxY = optionHeight * (i%rightColumnStart) + verticalStart + (i%rightColumnStart)* optionVerticalSpacing;
                stringX = width/9 - (int)(optionRect.getWidth() / 2) + 3*optionHorizontalSpacing;
                stringY = (i%rightColumnStart) * optionHeight + (int)(optionRect.getHeight() /2) + fm.getAscent() + verticalStart + (i%rightColumnStart)*optionVerticalSpacing;
            }
            else if(i >= centerColumnStart){
                boxX = width/9 - optionWidth/2 + 2*optionHorizontalSpacing;
                boxY = optionHeight * (i%centerColumnStart) + verticalStart + (i%centerColumnStart)* optionVerticalSpacing;
                stringX = width/9 - (int)(optionRect.getWidth() / 2) + 2*optionHorizontalSpacing;
                stringY = (i%centerColumnStart) * optionHeight + (int)(optionRect.getHeight() /2) + fm.getAscent() + verticalStart + (i%centerColumnStart)*optionVerticalSpacing;
            }


            /*
            int boxX = width/4 - optionWidth/2 + i / optionsPerColumn * optionHorizontalSpacing;
            int boxY = optionHeight * (i%optionsPerColumn) + verticalStart + (i%optionsPerColumn)* optionVerticalSpacing;

            int stringX = width/4 - (int)(optionRect.getWidth() / 2) + i / optionsPerColumn * optionHorizontalSpacing;
            int stringY = (i%optionsPerColumn) * optionHeight + (int)(optionRect.getHeight() /2) + fm.getAscent() + verticalStart + (i%optionsPerColumn)*optionVerticalSpacing;
            */
            // draw the option.
            g.setColor(primaryColor);
            g.fillRoundRect(boxX, boxY, optionWidth, optionHeight, borderRadius, borderRadius);
            g.setColor(secondaryCOlor);
            g.drawString(option.getTitle(), stringX, stringY);
        }

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
