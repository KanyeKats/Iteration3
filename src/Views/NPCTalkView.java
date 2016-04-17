package Views;

import Models.Menu.MenuOption;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Josh on 4/16/2016.
 */
public class NPCTalkView extends MenuView {

    private final String TITLE = "Talk";
    private int optionWidth;
    private int optionHeight;
    private Font titleFont;
    private Font optionFont;
    private Font textFont;
    private Color background;
    private Color color1;
    private Color color2;
    private int borderRadius;
    private int yStart;
    private int xStart;
    private int menuHeight;
    private int menuWidth;
    private int textWidth;
    private int textStart;
    private int textYSpacing;
    private String response;

    public NPCTalkView(int width, int height, Models.Menu.Menu menu, String response) {
        super(width,height,menu);

        yStart = height * 4/5;
        xStart = width * 3/10;
        menuHeight = height/5;
        menuWidth = width * 4/10;
        optionWidth = width / 8;
        optionHeight = height / 25;
        titleFont = new Font("SansSerif",Font.BOLD, width/ 12);
        optionFont = new Font("SansSerif", Font.BOLD, width / 86);
        textFont = new Font("SansSerif", Font.BOLD, width / 78);
        background = new Color(70, 70, 70);
        color2 = Color.gray;
        color1 = Color.white;
        borderRadius = 10;
        textStart = xStart + menuWidth/10;
        textWidth = menuWidth*8/10;
        this.response = response;
        this.textYSpacing = 20;

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
        int verticalStart = yStart + menuHeight/2;

        // Set the font
        g.setFont(optionFont);
        FontMetrics fm = g.getFontMetrics(optionFont);

            // Get the current option;
        MenuOption option = menu.getMenuOptions().get(0);

        // See if this is the selected option.
        Color primaryColor = color1;
        Color secondaryCOlor = color2;

        // Determine the dimensions of the box.
        Rectangle2D optionRect = fm.getStringBounds(option.getTitle(), g);

        int boxX = width*3/8 - optionWidth/2;
        int boxY = optionHeight + verticalStart;

        int stringX = width*3/8 - (int)(optionRect.getWidth() / 2);
        int stringY = optionHeight + (int)(optionRect.getHeight() /2) + fm.getAscent() + verticalStart;

        // draw the option.
        g.setColor(primaryColor);
        g.fillRoundRect(boxX, boxY, optionWidth, optionHeight, borderRadius, borderRadius);
        g.setColor(secondaryCOlor);
        g.drawString(option.getTitle(), stringX, stringY);

        // Print the response
        printResponseWithLineBreaks();

        g.dispose();
    }


    private void printResponseWithLineBreaks(){
        Graphics2D g = (Graphics2D)viewContent.getGraphics();

        //AA
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(rh);
        g.setColor(color1);

        g.setFont(textFont);
        FontMetrics fm = g.getFontMetrics(textFont);

        String[] splitResponse = response.split(" ");
        int length = 0;
        int textYstart = yStart + menuHeight/10;

        Rectangle2D rect;
        String responseLine = "";
        for(String str : splitResponse){
            str += " ";
            rect = fm.getStringBounds(str, g);
            if(length + (int)rect.getWidth() < textWidth){
                responseLine += str;
                length += (int)rect.getWidth();
            }
            else{
                g.drawString(responseLine, textStart, textYstart);
                responseLine = str;
                length = str.length();
                textYstart += textYSpacing;
            }
        }

        g.drawString(responseLine, textStart, textYstart);
    }
}
