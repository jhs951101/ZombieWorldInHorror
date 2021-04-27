package com.example.sharping.loveliveinhorror;

/**
 * Created by SharPing on 2017-07-04.
 */

public abstract class CountTimer extends Thread {

    protected long startedTime;
    protected long limitTime;

    public boolean flag;

    public CountTimer(){
        limitTime = 0;
        flag = false;
    }

    @Override
    public abstract void run();

    public void setLimitTime(long lt){
        limitTime = lt;
        flag = true;
    }
}
