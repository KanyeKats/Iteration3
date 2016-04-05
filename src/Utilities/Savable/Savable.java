package Utilities.Savable;

import java.util.ArrayList;

/**
 * Created by johnkaufmann on 3/31/16.
 * TODO:
 */
public interface Savable {
    public String save();
    public void load(ArrayList<String> data);
}
