package Models.Menu;

import Utilities.Action;

import java.util.ArrayList;

/**
 * Created by Bradley on 4/4/2016.
 */
public interface MenuOption {

    String getTitle();
    ArrayList<Action> getActions();
    Object getAttachment();
}
