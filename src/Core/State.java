package Core;

import Controllers.ViewController;
import Views.View;

/**
 * Created by johnkaufmann on 3/31/16.
 * TODO:
 */
public class State {

    protected ViewController viewController;
    protected View view;

    public State(ViewController viewController, View view){

        this.viewController = viewController;
        this.view = view;
    }

    public void update(){
        viewController.update();
    }

    public View getView(){
        return view;
    }

    public ViewController getViewController(){
        return viewController;
    }
}
