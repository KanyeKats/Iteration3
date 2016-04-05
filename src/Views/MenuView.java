package Views;

//import java.awt.*;


import Models.Menu.Menu;

/**
 * Created by Bradley on 4/4/2016.
 */
public abstract class MenuView extends View{

    protected Menu menu;

    public MenuView(int width, int height, Menu menu) {
        super(width, height);
        this.menu = menu;
        menu.addObserver(this);
    }

    @Override
    public void repaint() {
        renderBackground();
        renderOptions();
    }

    public abstract void renderBackground();
    public abstract void renderOptions();
}
