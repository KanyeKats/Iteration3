package Views;

import Models.Entities.Inventory;
import Models.Items.Takable.TakableItem;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by Josh on 4/15/2016.
 */
public class NPCShopView extends View {

    // Model
    private Inventory avatarInventory, npcInventory, currentInventory;
    private ArrayList<TakableItem> avatarSelectedItems, npcSelectedItems, currentSelectedItems;
    private int selectedItem;
    private int avatarCapacity, npcCapacity, currentCapacity;

    private final int ITEMS_PER_ROW = 15;

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
    private int lineYStart;

    //ITEM VIEW DIMENSION
    private int itemViewXStart;
    private int npcItemViewYStart;
    private int avatarItemViewYStart;
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

    public NPCShopView(int width, int height, Inventory avatarInventory, Inventory npcInventory) {
        super(width, height);

        this.selectedItem = 0;

        this.avatarInventory = avatarInventory;
        this.avatarCapacity = avatarInventory.getCapacity();
        this.avatarSelectedItems = new ArrayList<>();

        this.npcInventory = npcInventory;
        this.npcCapacity = npcInventory.getCapacity();
        this.npcSelectedItems = new ArrayList<>();

        this.currentInventory = avatarInventory;
        this.currentCapacity = avatarCapacity;
        this.currentSelectedItems = avatarSelectedItems;


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
        inventoryViewYStart = (int) (height * 0.2);
        inventoryViewWidth = (int) (width * 0.8);
        inventoryViewHeight = (int) (height * 0.67);
        lineYStart = inventoryViewYStart + inventoryViewHeight/2;


        //ITEM VIEW DIMENSION
        itemViewXStart = inventoryViewXStart;
        npcItemViewYStart = inventoryViewYStart;
        avatarItemViewYStart = inventoryViewYStart + inventoryViewHeight*5/8;
        itemViewWidth = inventoryViewWidth;
//        itemViewHeight = (int) (inventoryViewHeight * 0.7);
        itemViewHeight = inventoryViewHeight;

        itemMargin = width / 80;
        itemWidth = (itemViewWidth - (ITEMS_PER_ROW + 1) * itemMargin) / ITEMS_PER_ROW;
        itemHeight = itemWidth;

        //INFO VIEW DIMENSION
        infoViewXStart = inventoryViewXStart;
        infoViewYStart = inventoryViewYStart + itemViewHeight;
        infoViewWidth = inventoryViewWidth;
        infoViewHeight = inventoryViewHeight - itemViewHeight;

        infoXMargin = width / 30;
        infoYMargin = (int) (infoViewHeight * 0.2);
        infoElementHeight = infoViewHeight - infoYMargin * 2;
        infoDescriptionWidth = infoViewWidth - 2 * infoElementHeight - 4 * infoXMargin;

        repaint();
    }

    public void switchToNPCInventory(){
        currentInventory = npcInventory;
        currentCapacity = npcCapacity;
        currentSelectedItems = npcSelectedItems;
        selectedItem = 0;
        refresh();
    }

    public void switchToAvatarInventory(){
        currentInventory = avatarInventory;
        currentCapacity = avatarCapacity;
        currentSelectedItems = avatarSelectedItems;
        selectedItem = 0;
        refresh();
    }

    public void selectItem(){
        TakableItem item = null;
        if(selectedItem < currentInventory.getCurrentSize()){
            item = currentInventory.getItems().get(selectedItem);
            if(currentSelectedItems.contains(item))
                currentSelectedItems.remove(item);
            else
                currentSelectedItems.add(item);
        }
        refresh();
    }

    public void nextItem() {
        selectedItem++;
        if (selectedItem >= currentCapacity)
            selectedItem = 0;

        refresh();
    }

    public void previousItem() {
        selectedItem--;
        if (selectedItem < 0)
            selectedItem  = currentCapacity - 1;

        refresh();
    }

    //TODO: better way to compare items?
    //TODO: Toast that says something about a failed trade attempt
    public void makeTrade(){
        if(avatarSelectedItems.size() == 0 && npcSelectedItems.size() == 0){
            System.out.println("No items selected!");
        }
        else if(avatarSelectedItems.size() >= npcSelectedItems.size()){
            if(avatarInventory.size() + npcSelectedItems.size() > avatarInventory.getCapacity()){
                System.out.println("Not enough inventory capacity!");
                System.out.println("Trade unsuccessful");
            }
            else if(npcInventory.size() + avatarSelectedItems.size() > npcInventory.getCapacity()){
                System.out.println("The shop doesn't have enough inventory space for that!");
                System.out.println("Trade unsuccessful");
            }
            else {
                for (TakableItem item : avatarSelectedItems) {
                    avatarInventory.removeItem(item);
                    npcInventory.addItem(item);
                }
                for (TakableItem item : npcSelectedItems) {
                    npcInventory.removeItem(item);
                    avatarInventory.addItem(item);
                }
                npcSelectedItems.clear();
                avatarSelectedItems.clear();
                System.out.println("Trade successful!");
            }
        }
        else{
            System.out.println("That trade is too expensive!");
            System.out.println("Trade unsuccessful");
        }

        refresh();
    }

    public void renderBackground() {

        // Extract the graphics from the view content
        Graphics2D g = (Graphics2D)viewContent.getGraphics();
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(rh);

        // Draw a black background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.lightGray);
        g.drawLine(inventoryViewXStart, lineYStart, inventoryViewXStart+inventoryViewWidth, lineYStart+5);

        g.dispose();
    }

    @Override
    protected void repaint() {
        renderBackground();
        drawTitle();
        renderItemsView(npcInventory, npcSelectedItems, npcCapacity, npcItemViewYStart);
        renderItemsView(avatarInventory, avatarSelectedItems, avatarCapacity, avatarItemViewYStart);
        renderInfoView(npcInventory);
        renderInfoView(avatarInventory);
    }

    public void renderItemsView(Inventory inventory, ArrayList<TakableItem> selectedItems, int capacity, int itemViewYStart) {

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

            //in selected item list
            if(selectedItems.contains(item)){
                g.setColor(Color.CYAN);
                g.drawRect(xpos - 3, ypos - 3, this.itemWidth + 6, this.itemHeight + 6);
            }

            //current
            if (selectedItem == i && currentInventory == inventory) {
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
        String title = "Shop";
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
        String instructions = "Press [Enter] to select items and [Space] to attempt a trade.";
        FontMetrics fm2 = g.getFontMetrics(smallFont);
        Rectangle2D instRect = fm2.getStringBounds(instructions, g);

        // Get the location of the instr
        int instX = titleStartX + titleWidth / 2 - (int) instRect.getWidth() / 2;
        int instY = titleStartY + titleHeight - instMargin;

        // Draw the instr
        g.drawString(instructions, instX, instY);
    }


    private void renderInfoView(Inventory inventory) {
        // Get graffics
        Graphics2D g = (Graphics2D) viewContent.getGraphics();
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(rh);

        //paint background
        g.setColor(new Color(25, 25, 25));
        g.fillRect(this.infoViewXStart, this.infoViewYStart, this.infoViewWidth, this.infoViewHeight);

        TakableItem item = null;
        if (selectedItem < inventory.getCurrentSize() && inventory == currentInventory) {
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
