package Core;

import Views.View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Bradley on 4/4/2016.
 */
public class Display extends JPanel{

    private View activeView;

    public Display(int screenWidth, int screenHeight){

        // Setup the JPanel
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(screenWidth, screenHeight));
    }

    public void setActiveView(View view){
        this.activeView = view;
    }

    @Override
    public void paintComponent(Graphics g){
        try{
            activeView.render(g);
        } catch(NullPointerException e){
            System.out.println("No active view found.");
            e.printStackTrace();
        }
    }

    public BufferedImage getCurrentViewContent(){
        return activeView.getViewContent();
    }
}
