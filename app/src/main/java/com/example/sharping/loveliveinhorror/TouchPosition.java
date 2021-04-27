package com.example.sharping.loveliveinhorror;

/**
 * Created by SharPing on 2017-06-29.
 */

public class TouchPosition extends Position {
    private boolean damaged;

    public TouchPosition(int x, int y, boolean d){
        super(x, y);
        this.damaged = d;
    }

    public boolean isDamaged(){
        return this.damaged;
    }
}
