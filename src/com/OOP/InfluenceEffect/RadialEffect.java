package com.OOP.InfluenceEffect;

import java.awt.*;

/**
 * Created by johnkaufmann on 4/2/16.
 * TODO:
 */
public class RadialEffect extends Effect {

    public RadialEffect(int range, Point location, Consequence consequence, Map map) {
        super(range, location, consequence, map);
        start();
    }

    //propagate radially
    @Override
    protected void traverseThroughTiles() {

    }
}
