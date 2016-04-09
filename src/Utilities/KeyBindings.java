package Utilities;

import java.util.HashMap;

/**
 * Created by Bradley on 4/4/2016.
 */
public class KeyBindings {

    private HashMap<Integer, Action> bindings;

    public KeyBindings(){
        this.bindings = new HashMap<>();
    }

    public void addBinding(Integer key, Action value){
        bindings.put(key, value);
    }

    public void keyPressed(int keyCode){
        Action action = bindings.get(keyCode);
        if(action!=null){
            action.execute();
        }
    }

    public void keyReleased(int keyCode){
//        Action action = bindings.get(keyCode);
//        if(action!=null){
//            action.finish();
//        }
    }

    // TODO: Provide an interface to change keybindings with.
}
