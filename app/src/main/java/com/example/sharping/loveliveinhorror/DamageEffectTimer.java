package com.example.sharping.loveliveinhorror;

/**
 * Created by SharPing on 2017-07-05.
 */

public class DamageEffectTimer extends CountTimer {

    private DamageBackColorShow damagedBackColorShow;

    public DamageEffectTimer(DamageBackColorShow dbcs){
        this.damagedBackColorShow = dbcs;
    }

    @Override
    public void run(){
        startedTime = System.currentTimeMillis();
        damagedBackColorShow.show = true;

        while((GameView.gameFlag == 1 || GameView.gameFlag == 3) && flag){
            if(System.currentTimeMillis() - startedTime >= limitTime){
                flag = false;
                break;
            }
        }

        damagedBackColorShow.show = false;
    }
}
