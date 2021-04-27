package com.example.sharping.loveliveinhorror;

/**
 * Created bheight SharPing on 2017-07-30.
 */

public class Length {
    private int width;
    private int height;

    public Length(){
        this.width = 0;
        this.height = 0;
    }

    public Length(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }
}
