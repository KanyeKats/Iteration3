package Views;

import Controllers.MenuViewController;
import Models.Entities.Inventory;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.Stats;
import Models.Items.Takable.TakableItem;
import Models.Menu.Menu;
import Models.Menu.MenuOption;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by sergiopuleri on 4/14/16.
 */
public class InventoryView extends View {

    // Model
    private Inventory inventory;
    private int selectedItem;
    private int capacity;

    private final int ITEMS_PER_ROW = 10;

    //INVENTORY TITLE
    private int titleStartX;
    private int titleStartY;
    private int titleWidth;
    private int titleHeight;


    //INVETORY DIMENSION
    private int inventoryViewXStart;
    private int inventoryViewYStart;
    private int inventoryViewWidth;
    private int inventoryViewHeight;

    //ITEM VIEW DIMENSION
    private int itemViewXStart;
    private int itemViewYStart;
    private int itemViewWidth;
    private int itemViewHeight;

    private int itemMargin = 15;
    private int itemWidth;
    private int itemHeight;

    //INFO VIEW DIMENSION
    private int infoViewXStart;
    private int infoViewYStart;
    private int infoViewWidth;
    private int infoViewHeight;

    private int infoXMargin;
    private int infoYMargin;
    private int infoElementHeight;
    private int infoDescriptionWidth;

    private Font font;
    private Font smallFont;
    private Font largeFont;
    private Font titleFont;
    
    public InventoryView(int width, int height, Inventory inventory) {
        super(width, height);

        this.inventory = inventory;
        this.selectedItem = 0;
        this.capacity = inventory.getCapacity();


        font = new Font("Courier New", 1, width / 50);
        smallFont = new Font("Courier New", 1, width / 67);
        largeFont = new Font("Courier New", 1, width / 30);
        titleFont = new Font("Courier New", 1, width / 22);

        //INVENTORY TITLE
        titleStartX = (int) (width * 0.1);
        titleStartY = (int) (height * 0.05);
        titleWidth = (int) (width * 0.8);
        titleHeight = (int) (height * 0.15);


        //INVETORY DIMENSION
        inventoryViewXStart = (int) (width * 0.1);
        inventoryViewYStart = (int) (height * 0.3);
        inventoryViewWidth = (int) (width * 0.8);
        inventoryViewHeight = (int) (height * 0.52);


        //ITEM VIEW DIMENSION
        itemViewXStart = inventoryViewXStart;
        itemViewYStart = inventoryViewYStart;
        itemViewWidth = inventoryViewWidth;
//        itemViewHeight = (int) (inventoryViewHeight * 0.7);
        itemViewHeight = inventoryViewHeight;

        itemMargin = width / 80;
        itemWidth = (itemViewWidth - (ITEMS_PER_ROW + 1) * itemMargin) / ITEMS_PER_ROW;
        itemHeight = itemWidth;

        //INFO VIEW DIMENSION
        infoViewXStart = inventoryViewXStart;
        infoViewYStart = itemViewYStart + itemViewHeight;
        infoViewWidth = inventoryViewWidth;
        infoViewHeight = inventoryViewHeight - itemViewHeight;

        infoXMargin = width / 30;
        infoYMargin = (int) (infoViewHeight * 0.2);
        infoElementHeight = infoViewHeight - infoYMargin * 2;
        infoDescriptionWidth = infoViewWidth - 2 * infoElementHeight - 4 * infoXMargin;

        repaint();
    }

    public TakableItem getSelectedItem() {
        // could return null... View Controller will check fo this.
        TakableItem item = null;
        if (selectedItem < inventory.getCurrentSize()) {
            item = inventory.getItems().get(selectedItem);
        }
        return item;
    }

    public int getSelectedIndex() {
        return selectedItem;
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void nextItem() {
        selectedItem++;
        if (selectedItem >= capacity)
            selectedItem = 0;
    }

    public void previousItem() {
        selectedItem--;
        if (selectedItem < 0)
            selectedItem  = capacity - 1;
    }

    public void renderBackground() {

        // Extract the graphics from the view content
        Graphics2D g = (Graphics2D)viewContent.getGraphics();
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(rh);

        // Draw a black background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        g.dispose();
    }

    @Override
    protected void repaint() {
        renderBackground();
        drawTitle();
        renderItemsView();
        renderInfoView();
    }

    public void renderItemsView() {

        Graphics2D g = (Graphics2D)viewContent.getGraphics();
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(rh);

        // Get the size of the inventory
        int size = inventory.getItems().size();

        int xPosStart = itemMargin + itemViewXStart;
        int xPosInc = itemMargin + itemWidth;
        int yPosInc = itemMargin + itemHeight;
        int xpos = xPosStart;
        int ypos = itemMargin + itemViewYStart;

        for (int i = 0; i < capacity; i++) {
            // Get the item (could be null if dont have an item at that slot
            // Which is OK. paintIcon checks this
            TakableItem item = null;
            if (i < size) {
                item = inventory.getItems().get(i);
            }

            //selected
            if (selectedItem == i) {
                g.setColor(Color.RED);
                //change
                g.drawRect(xpos - 3, ypos - 3, this.itemWidth + 6, this.itemHeight + 6);
            }
            // Draw it
            paintIcon(g, xpos, ypos, item, false);

            //increment for next paint
            if ((i + 1) % ITEMS_PER_ROW == 0) {
                xpos = xPosStart;
                ypos += yPosInc;
            } else {
                xpos += xPosInc;
            }
        }
    }

    private void drawTitle() {
        // Get graffics
        Graphics2D g = (Graphics2D)viewContent.getGraphics();
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(rh);

        int titleMargin = 5;
        int instMargin = 15;

        // Draw the background
        g.setColor(new Color(25, 25, 25));
        g.fillRect(titleStartX, titleStartY, titleWidth, titleHeight);

        // Get ready to draw the title
        g.setFont(titleFont);
        String title = "Inventory";
        FontMetrics fm = g.getFontMetrics(titleFont);
        Rectangle2D titleRect = fm.getStringBounds(title, g);

        // Get the location of the title
        int titleX = titleStartX + titleWidth / 2 - (int) titleRect.getWidth() / 2;
        int titleY = titleStartY + (int) titleRect.getHeight() + titleMargin;

        // Draw the title
        g.setColor(Color.lightGray);
        g.drawString(title, titleX, titleY);

        // Get ready to draw the instructions
        g.setFont(smallFont);
        String instructions = "Press [Enter] to equip an item or [D] to drop it.";
        FontMetrics fm2 = g.getFontMetrics(smallFont);
        Rectangle2D instRect = fm2.getStringBounds(instructions, g);

        // Get the location of the instr
        int instX = titleStartX + titleWidth / 2 - (int) instRect.getWidth() / 2;
        int instY = titleStartY + titleHeight - instMargin;

        // Draw the instr
        g.drawString(instructions, instX, instY);
    }


    private void renderInfoView() {
        // Get graffics
        Graphics2D g = (Graphics2D) viewContent.getGraphics();
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(rh);

        //paint background
        g.setColor(new Color(25, 25, 25));
        g.fillRect(this.infoViewXStart, this.infoViewYStart, this.infoViewWidth, this.infoViewHeight);

        TakableItem item = null;
        if (selectedItem < inventory.getCurrentSize()) {
            item = inventory.getItems().get(selectedItem);
        }

        if (item != null) {
            // Get coords
            int itemX = this.infoViewXStart + itemMargin;
            int itemY = this. infoViewYStart + itemMargin;

            // Draw the items image
            paintIcon(g, itemX, itemY, item, true);
            g.drawImage(item.getImage(), itemX, itemY, itemWidth, itemHeight, null);

            // Draw the items title
            // Get ready to draw the title
            g.setFont(largeFont);
            String title = item.getName();
            FontMetrics fm = g.getFontMetrics(largeFont);
            Rectangle2D rect = fm.getStringBounds(title, g);

            // Get the location of the title
            int titleX = infoViewXStart + (infoViewWidth - (int)rect.getWidth())/2;
            int titleY = infoViewYStart + (int)rect.getHeight()/2 + infoYMargin;
            int titleEnd = titleY + (int)rect.getHeight();

            // Draw Title
            g.drawString(title, titleX, titleY);

            // Config stuff for Description
            g.setFont(smallFont);
            String desc = item.getDescription();
            fm = g.getFontMetrics(smallFont);
            rect = fm.getStringBounds(desc, g);

            // Get location
            int descX = infoViewXStart + (infoViewWidth - (int)rect.getWidth())/2;
            int descY = titleEnd;

            // Draw it
            g.drawString(desc, descX, descY);
        }

    }



    private void paintIcon(Graphics g, int xpos, int ypos, TakableItem item, boolean round) {
        if (item == null) {
            //draw empty slot
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(xpos, ypos, itemWidth, itemHeight);

            g.setFont(largeFont);
            g.setColor(Color.BLACK);
        } else {
            // Draw Background
            g.setColor(Color.LIGHT_GRAY);
            if (round) {
                g.fillRoundRect(xpos, ypos, itemWidth, itemHeight, itemWidth/2, itemHeight/2);
            } else {
                g.fillRect(xpos, ypos, itemWidth, itemHeight);
            }

            // Draw image
            g.drawImage(item.getImage(), xpos, ypos, this.itemWidth, this.itemHeight, null);
        }
    }

    public void refresh() {
        repaint();
    }

}
