package com.example.sharping.loveliveinhorror;

import java.util.ArrayList;

/**
 * Created by SharPing on 2017-07-05.
 */

public class HitPointTimer extends CountTimer {

    public static boolean usedFlag = false;

    private ArrayList<HitPosition> hitPoints;

    private boolean timerUsed;
    private boolean failure;

    public HitPointTimer(ArrayList<HitPosition> hps){
        this.hitPoints = hps;

        failure = false;
        timerUsed = false;
    }

    @Override
    public void run(){
        usedFlag = true;
        timerUsed = true;
        startedTime = System.currentTimeMillis();

        while(GameView.gameFlag == 1 && flag){
            long elapsedTime = System.currentTimeMillis() - startedTime;

            for(int i=0; i<hitPoints.size(); i++) {
                try{
                    hitPoints.get(i).setGreen(elapsedTime);
                } catch (java.lang.NullPointerException | java.lang.IndexOutOfBoundsException e1){
                    break;
                }
            }

            if(elapsedTime >= limitTime){
                failure = true;
                flag = false;
                break;
            }
        }

        usedFlag = false;
        timerUsed = false;
    }

    public void setLimitTime(long lt){
        super.setLimitTime(lt);
        failure = false;
    }

    public boolean isFailure(){
        return this.failure;
    }

    public void returnFailure(){
        failure = false;
    }

    public boolean isTimerUsed(){
        return this.timerUsed;
    }
}
