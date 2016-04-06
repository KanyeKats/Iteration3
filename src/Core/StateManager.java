package Core;

import jdk.internal.util.xml.impl.Input;

import java.util.InputMismatchException;
import java.util.Stack;

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

    public void goToPreviousState(){
        if(states.size() > 1){
            states.pop();
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
}
