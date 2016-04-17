package Core;

import Controllers.MenuViewController;
import Utilities.Constants;
import Views.StartMenuView;

import java.awt.image.BufferedImage;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by johnkaufmann on 3/31/16.
 */
public class StateManager {
    private Display display;
    private InputDispatcher inputDispatcher;
    private Stack<State> states;

    public StateManager(Display display, InputDispatcher inputDispatcher) {

        this.display = display;
        this.inputDispatcher = inputDispatcher;
        this.states = new Stack<>();
    }

    public void setActiveState(State state){

        // Set the controller and view as the active components and push the state onto the stack.
        inputDispatcher.setActiveController(state.getViewController());
        display.setActiveView(state.getView());
        states.push(state);
    }

    //Do this weird stuff so that transparent views still work...
    //Draw the view from two states ago, wait a frame, and then draw the correct (previous) state
    public void goToPreviousState(){
        if(states.size() > 1){
            states.pop();
            if(states.size() > 3) {
                State newState = states.pop();
                reActivate(states.peek());
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        setActiveState(newState);
                    }
                }, 1000 / Constants.FRAME_RATE);
            }
            else
                reActivate(states.peek());
        }else{
            System.exit(0);
        }
    }

    // Called once per clock cycle.
    public void tick(){
        if(!states.isEmpty()){
            states.peek().update();
        }
        display.repaint();
    }

    // Simply activates the state without pushing it onto the stack.
    private void reActivate(State state){
        inputDispatcher.setActiveController(state.getViewController());
        display.setActiveView(state.getView());
    }

    public void startOver(){
        states.removeAllElements();
        Models.Menu.Menu startMenu = Models.Menu.Menu.createStartMenu(this);
        MenuViewController startMenuController = new MenuViewController(this, startMenu);
        StartMenuView startMenuView = new StartMenuView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, startMenu);
        setActiveState(new State(startMenuController, startMenuView));
    }

    public BufferedImage getCurrentViewContent(){
        return display.getCurrentViewContent();
    }
}
