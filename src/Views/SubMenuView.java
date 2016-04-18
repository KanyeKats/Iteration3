package Views;

import Models.Menu.Menu;
import Views.Graphics.ImageLoader;

import java.awt.image.BufferedImage;

/**
 * Created by Bradley on 4/17/16.
 */
public abstract class SubMenuView extends MenuView{
    public SubMenuView(int width, int height, Menu menu, BufferedImage lastViewContent) {
        super(width, height, menu);
        this.viewContent = ImageLoader.copyImage(lastViewContent);
    }
}
