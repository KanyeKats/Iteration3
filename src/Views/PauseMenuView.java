package Views;

import Models.Menu.Menu;
import Models.Menu.MenuOption;

import java.awt.*;

/**
 * Created by rokas on 4/13/16.
 */
public class PauseMenuView extends MenuView {

    private final String TITLE = "Pause";
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

    public PauseMenuView(int width, int height, Menu menu) {
        super(width,height,menu);

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

    @Override
    public void renderBackground() {
        Graphics2D g2d = (Graphics2D)viewContent.getGraphics();
        g2d.setColor(Color.BLACK);
        g2d.dispose();
    }

    @Override
    public void renderOptions() {

        Graphics2D g = (Graphics2D)viewContent.getGraphics();

        //AA
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(rh);
        
        for (MenuOption option : menu.getMenuOptions()) {
        for ( int i = 0; i < menu)
            // See if this is the selected option.
            Color primaryColor = (menu.getSelectedIndex() == i) ? color1 : color2;
            Color secondaryCOlor = (menu.getSelectedIndex() == i) ?  color2: color1;

        }
    }
}
