package Views;

import Models.Entities.Equipment;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Models.Entities.Stats.Stats;
import Models.Items.Takable.Equippable.EquippableItem;
import Models.Items.Takable.TakableItem;
import Views.Graphics.Assets;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by sergiopuleri on 4/16/16.
 */
public class EquipmentView extends View {


    // Model
    private Equipment equipment;
    private Stats stats;
    private String occupationString;
    private int selected;
    private int capacity;

    //// View Config ////

    // Title
    private int titleStartY;

    // Spacing
    private int veritcalSpaceMargin;
    private int horizontalPaneMargin;

    // Stats Pane
    private int statsPaneStartX;
    private int statsPaneStartY;
    private int statsPaneHeight;
    private int statsPaneWidth;

    // Equipment Item Pane
    private int itemsPaneStartX;
    private int itemsPaneStartY;
    private int itemsPaneHeight;
    private int itemsPaneWidth;
    private int itemSlotSize;
    private int itemImageSize;

    // Info Pane
    private int infoPaneStartX;
    private int infoPaneStartY;
    private int infoPaneHeight;
    private int infoPaneWidth;

    // Fonts
    private Font littleFont;
    private Font littleFontSansSerif;
    private Font titleFont;
    private Font regularFont;
    private Font miniFontSansSerif;
    private Font superMini;

    // Colors
    private Color slotColor;
    private Color highlightColor;


    // Construtor
    public EquipmentView(int width, int height, Equipment equipment, Stats stats, String occupationString) {
        super(width, height);

        // Set models
        this.equipment = equipment;
        this.stats = stats;
        this.occupationString = occupationString;

        // Interaction variables
        this.selected = 0;
        this.capacity = Equipment.Slot.values().length;

        // View stuff
        littleFont = new Font("Courier New", 1, width / 67);
        littleFontSansSerif = new Font("SansSerif", 1, width / 67);
        miniFontSansSerif = new Font("SansSerif", 1, width / 100);
        superMini = new Font("SansSerif", 1, width / 115);
        regularFont = new Font("Courier New", 1, width / 50);
        titleFont = new Font("Courier New", 1, width / 22);

        titleStartY = (int) (height * 0.09);

        veritcalSpaceMargin = 30;
        horizontalPaneMargin = 5;

        statsPaneStartX = (int) (width * 0.10);
        statsPaneWidth = width/4;
        statsPaneHeight = (int)(height/1.5);
        statsPaneStartY = titleStartY + veritcalSpaceMargin*3;


        itemsPaneStartX = statsPaneStartX + statsPaneWidth + horizontalPaneMargin;
        itemsPaneStartY = statsPaneStartY;
        itemsPaneHeight = statsPaneHeight;
        itemsPaneWidth = (int)(statsPaneWidth * 1.2);
        itemSlotSize = 80;
        itemImageSize = 55;


        infoPaneStartX = itemsPaneStartX + itemsPaneWidth + horizontalPaneMargin;
        infoPaneStartY = statsPaneStartY;
        infoPaneHeight = statsPaneHeight;
        infoPaneWidth = statsPaneWidth;

        slotColor = new Color(242, 242,242);
        highlightColor = Color.yellow;

        repaint();
    }


    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public EquippableItem getSelectedItem() {
        return equipment.getItemAtSlot(getSelectedSlot());
    }

    public Equipment.Slot getSelectedSlot() {
        return Equipment.Slot.values()[selected];
    }

    public void next() {
        selected++;
        if (selected >= capacity)
            selected = 0;
    }

    public void previous() {
        selected--;
        if (selected < 0)
            selected  = capacity - 1;
    }

    @Override
    protected void repaint() {
        // Extract the graphics from the view content
        Graphics2D g = (Graphics2D)viewContent.getGraphics();
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(rh);

        // Do the paintin'!!!
        renderBackground(g);
        drawTitle(g);
        drawPanes(g);
        renderStatsView(g);
        renderItemsView(g);
        renderInfoView(g);
        drawBottomInstructions(g);

        g.dispose();
    }

    public void renderBackground(Graphics2D g) {
        // Draw a black background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

    }

    private void drawTitle(Graphics2D g) {
        // Get ready to draw the title
        g.setFont(titleFont);
        String title = "Equipment";
        FontMetrics fm = g.getFontMetrics(titleFont);
        Rectangle2D rect = fm.getStringBounds(title, g);

        // Get the location of the title
        int titleX = (width - (int) rect.getWidth())/2;
        int titleY = titleStartY;

        // Draw the title
        g.setColor(Color.white);
        g.drawString(title, titleX, titleY);

        // Get ready to draw the instructions
        g.setFont(littleFont);
        String instructions = "Use arrow keys to select an item & Press [Enter] to unequip an item";
        fm = g.getFontMetrics(littleFont);
        rect = fm.getStringBounds(instructions, g);

        // Get the location of the instr
        int instX = (width - (int) rect.getWidth())/2;
        int instY = titleStartY + (int)rect.getHeight() + fm.getAscent();

        // Draw the instructions
        g.drawString(instructions, instX, instY);
    }

    private void drawPanes(Graphics2D g) {
        g.setColor(Color.LIGHT_GRAY);

        // Draw stats
        g.fillRect(statsPaneStartX , statsPaneStartY, statsPaneWidth, statsPaneHeight);

        // Draw items pane
        g.fillRect(itemsPaneStartX, itemsPaneStartY, itemsPaneWidth, itemsPaneHeight);

        // Info pane
        g.fillRect(infoPaneStartX, infoPaneStartY, infoPaneWidth, infoPaneHeight);
    }

    private void renderStatsView(Graphics2D g) {
        // setup fonty stuff
        g.setColor(Color.BLACK);
        g.setFont(regularFont);
        String title = "Current Stats";
        FontMetrics fm = g.getFontMetrics(regularFont);
        Rectangle2D rect = fm.getStringBounds(title, g);

        int titleX = statsPaneStartX + (statsPaneWidth - (int)rect.getWidth())/2;
        int titleY = statsPaneStartY + veritcalSpaceMargin;
        g.drawString(title, titleX, titleY);

        // Draw stats yo
        g.setColor(Color.black);
        g.setFont(littleFontSansSerif);

        // Occupation + lvl
        String text = "Level " + stats.getStat(Stat.LEVEL).toString() + " " + occupationString;
        fm = g.getFontMetrics(littleFontSansSerif);

        int statY = titleY + (int)rect.getHeight() + fm.getAscent();
        rect = fm.getStringBounds(text, g);
        int statX = statsPaneStartX + (statsPaneWidth - (int)rect.getWidth())/2;

        g.drawString(text, statX, statY);

        // Max Health
        g.setFont(miniFontSansSerif);
        statX = statsPaneStartX + horizontalPaneMargin*2;
        statY = statY + (int)rect.getHeight() + fm.getAscent();

        text = "Max Health: " + stats.getStat(Stat.MAX_HEALTH).toString();
        fm = g.getFontMetrics(miniFontSansSerif);
        rect = fm.getStringBounds(text, g);
        g.drawString(text, statX, statY);

        // Max Mana
        statY = statY + (int)rect.getHeight() + fm.getAscent();
        text = "Max Mana: " + stats.getStat(Stat.MAX_MANA).toString();
        rect = fm.getStringBounds(text, g);
        g.drawString(text, statX, statY);

        // Movement Speed
        statY = statY + (int)rect.getHeight() + fm.getAscent();
        text = "Movement Speed: " + stats.getStat(Stat.MOVEMENT).toString();
        rect = fm.getStringBounds(text, g);
        g.drawString(text, statX, statY);

        // Strength
        statY = statY + (int)rect.getHeight() + fm.getAscent();
        text = "Strength: " + stats.getStat(Stat.STRENGTH).toString();
        rect = fm.getStringBounds(text, g);
        g.drawString(text, statX, statY);

        // Agility
        statY = statY + (int)rect.getHeight() + fm.getAscent();
        text = "Agility: " + stats.getStat(Stat.AGILITY).toString();
        rect = fm.getStringBounds(text, g);
        g.drawString(text, statX, statY);

        // Intellect
        statY = statY + (int)rect.getHeight() + fm.getAscent();
        text = "Intellect: " + stats.getStat(Stat.INTELLECT).toString();
        rect = fm.getStringBounds(text, g);
        g.drawString(text, statX, statY);

        // Offensive Rating
        statY = statY + (int)rect.getHeight() + fm.getAscent();
        text = "Offensive Rating: " + stats.getStat(Stat.OFFSENSIVE_RATING).toString();
        rect = fm.getStringBounds(text, g);
        g.drawString(text, statX, statY);

        // Defensive Rating
        statY = statY + (int)rect.getHeight() + fm.getAscent();
        text = "Defensive Rating: " + stats.getStat(Stat.DEFENSIVE_RATING).toString();
        rect = fm.getStringBounds(text, g);
        g.drawString(text, statX, statY);

        // Weapon Modifier
        statY = statY + (int)rect.getHeight() + fm.getAscent();
        text = "Weapon Modifier: " + stats.getStat(Stat.WEAPON_MODIFIER).toString();
        rect = fm.getStringBounds(text, g);
        g.drawString(text, statX, statY);

        // Armor Modifier
        statY = statY + (int)rect.getHeight() + fm.getAscent();
        text = "Armor Modifier: " + stats.getStat(Stat.ARMOR_MODIFIER).toString();
        rect = fm.getStringBounds(text, g);
        g.drawString(text, statX, statY);

        // Weight
        statY = statY + (int)rect.getHeight() + fm.getAscent();
        text = "Weight: " + stats.getStat(Stat.TOTAL_WEIGHT).toString() + " kg";
        rect = fm.getStringBounds(text, g);
        g.drawString(text, statX, statY);
    }

    public void renderItemsView(Graphics2D g) {
        // setup fonty stuff
        g.setColor(Color.BLACK);
        g.setFont(regularFont);
        String title = "Equipped Items";
        FontMetrics fm = g.getFontMetrics(regularFont);
        Rectangle2D rect = fm.getStringBounds(title, g);

        // Draw title
        int titleX = itemsPaneStartX + (itemsPaneWidth - (int)rect.getWidth())/2;
        int titleY = itemsPaneStartY + veritcalSpaceMargin;
        g.drawString(title, titleX, titleY);

        // Setuup points for item boxes
        int itemBoxesStartX = itemsPaneStartX + horizontalPaneMargin;
        int itemBoxesStartY = titleY + (int)rect.getHeight();

        // Setup columns and rows.
        int xFirstCol = itemBoxesStartX + 1 * itemsPaneWidth / 4 - itemSlotSize / 2;
        int xSecondCol = itemBoxesStartX + 2 * itemsPaneWidth / 4 - itemSlotSize / 2;
        int xThirdCol = itemBoxesStartX + 3 * itemsPaneWidth / 4 - itemSlotSize / 2;
        int yFirstRow = itemBoxesStartY + itemsPaneWidth / 6 - itemSlotSize / 2;
        int ySecondRow = itemBoxesStartY + 3 * itemsPaneWidth / 6 - itemSlotSize / 2;
        int yThirdRow = itemBoxesStartY + 5 * itemsPaneWidth / 6 - itemSlotSize / 2;

        // Init variables for the loopdy loop
        Rectangle2D rec;
        int x;
        int y;
        int colCount = 1;

        // Loop thru the slots.
        for (Equipment.Slot slot : Equipment.Slot.values()) {
            // Assign row
            if (slot.ordinal() < 1) {
                y = yFirstRow;
            } else if (slot.ordinal() >= 1 && slot.ordinal() < 4) {
                y = ySecondRow;
            } else {
                y = yThirdRow;
            }
            // Assign column

            if (colCount == 1) {
                x = xSecondCol;
            }
            else if (colCount % 3 == 1) {
                x = xFirstCol;
            } else if (colCount % 3 == 2) {
                x = xSecondCol;
            } else {
                x = xThirdCol;
            }
            colCount++;
            // Draw slots
            g.setColor(slotColor);
            g.fillRect(x, y, itemSlotSize, itemSlotSize);

            if (selected == slot.ordinal()) {
                g.setColor(highlightColor);
                g.drawRect(x - 3, y - 3, itemSlotSize + 6, itemSlotSize + 6);
                g.setColor(slotColor);
            }
            // Get image path for appropiate slot if item is equipped there
            EquippableItem currentItem = equipment.getItemAtSlot(slot);

            // Image we will render at this slot
            Image itemImage;
            if (currentItem != null) {
                itemImage = currentItem.getImage();

                // Draw the name of the item underneath it rly smallly
                String itemName = currentItem.getName();
                g.setColor(Color.BLACK);
                fm = g.getFontMetrics(superMini);
                g.setFont(superMini);
                rec = fm.getStringBounds(itemName, g);
                g.drawString(itemName, x + (itemSlotSize - (int) rec.getWidth()) / 2, y + itemSlotSize - ((int) (rec.getHeight()) / 2));

            } else {
                // No item equipped for this slot
                // Render a place holder image
                // TODO: FIX PLACEHOLDER
                itemImage = Assets.PLACEHOLDER;
            }
            // Draw item image
//            g.drawImage(itemImage, x, y, itemImageSize, itemImageSize, null);
            g.drawImage(itemImage, x + (itemSlotSize - itemImageSize) / 2, y + (itemSlotSize - itemImageSize) / 2, itemImageSize, itemImageSize, null);

            g.setColor(Color.white);
            g.setFont(superMini);
            fm = g.getFontMetrics(superMini);

            // Draw equipment slot text
            rec = fm.getStringBounds(slot.getDescriptor(), g);
            // If the text contains more than 5 letters (e.g. "primary weapon") we want to render it differently
            if (slot.getDescriptor().length() > 5) {
                g.drawString(slot.getDescriptor(), x, y + itemSlotSize + ((int) (rec.getHeight()) + fm.getAscent()));
            } else
                g.drawString(slot.getDescriptor(), x + (int) rec.getWidth() / 2, y + itemSlotSize + ((int) (rec.getHeight()) + fm.getAscent()));
        }
    }

    private void renderInfoView(Graphics2D g) {
        // Get selected slot
        Equipment.Slot slot = getSelectedSlot();
        EquippableItem currentItem = getSelectedItem();

        String title;
        FontMetrics fm;
        boolean itemIsEquipped = false;

        if (currentItem == null) {
            title = "Nothing equipped at " + slot.getDescriptor();
            g.setFont(littleFont);
            fm = g.getFontMetrics(littleFont);
        } else {
            itemIsEquipped = true;
            title = currentItem.getName();
            g.setFont(regularFont);
            fm = g.getFontMetrics(regularFont);
        }

        // setup fonty stuff
        g.setColor(Color.BLACK);
        Rectangle2D rect = fm.getStringBounds(title, g);

        // Draw title
        int titleX = infoPaneStartX + (infoPaneWidth - (int)rect.getWidth())/2;
        int titleY = infoPaneStartY + veritcalSpaceMargin;
        g.drawString(title, titleX, titleY);

        // Info about equipment if something is equipped
        if (itemIsEquipped) {
            String text = currentItem.getDescription();
            StatModificationList statMods = currentItem.getStatModificationList();

            // Set font for description
            g.setFont(littleFont);
            fm = g.getFontMetrics(littleFont);

            int textX = infoPaneStartX + horizontalPaneMargin*2;
            int textY = titleY + (int) rect.getHeight() + fm.getAscent();
            rect = fm.getStringBounds(text, g);

            g.drawString(text, textX, textY);

            // Now draw statmods
            g.setFont(littleFontSansSerif);
            g.setColor(Color.red);
            fm = g.getFontMetrics(littleFontSansSerif);

            for (StatModification mod : statMods.getMods()) {
                // Get text of current mod
                text = mod.toString();

                // Position
                textY = textY + (int) rect.getHeight() + fm.getAscent();

                // Draw it
                g.drawString(text, textX, textY);
            }

        }

    }

    private void drawBottomInstructions(Graphics2D g) {
        // Get ready to draw the title
        g.setFont(titleFont);
        String title = "Equipment";
        FontMetrics fm = g.getFontMetrics(titleFont);
        Rectangle2D rect = fm.getStringBounds(title, g);

        // Get the location of the title
        int titleX = (width - (int) rect.getWidth())/2;
        int titleY = titleStartY;

        // Draw the title
        g.setColor(Color.white);

        // Get ready to draw the instructions p1
        g.setFont(littleFont);
        String instructions = "[ESC] or [P] to exit";
        fm = g.getFontMetrics(littleFont);
        Rectangle2D rect1 = fm.getStringBounds(instructions, g);

        // Get ready to draw the instructions p1
        g.setFont(littleFont);
        String instructions2 = "[I] to go to Inventory";
        fm = g.getFontMetrics(littleFont);
        Rectangle2D rect2 = fm.getStringBounds(instructions, g);

        // Get the location of the instr
        int inst2X = (width - (int) rect2.getWidth())/2;
        int inst1X = (width - (int) rect1.getWidth())/2;

        int inst2Y = height - (int)rect2.getHeight() - fm.getAscent();
        int inst1Y = inst2Y - (int)rect1.getHeight() - fm.getAscent() ;

        // Draw the instructions
        g.drawString(instructions2, inst2X, inst2Y);
        g.drawString(instructions, inst1X, inst1Y);
    }


    public void refresh() {
        repaint();
    }
}
