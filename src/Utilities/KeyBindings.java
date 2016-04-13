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

    public Action getKeyAction(int keyCode){
        return bindings.get(keyCode);
    }

    public void removeBinding(int keyCode){
        bindings.remove(keyCode);
    }
}
