package com.example.sharping.loveliveinhorror;

/**
 * Created by SharPing on 2017-07-05.
 */

public class KnockOutTimer extends CountTimer {

    private BossZombie zombie;
    private boolean used;

    public KnockOutTimer(BossZombie c){
        this.zombie = c;
        used = false;
    }

    @Override
    public void run(){
        used = true;
        startedTime = System.currentTimeMillis();

        while(GameView.gameFlag == 1 && flag){
            if(System.currentTimeMillis() - startedTime >= limitTime){
                zombie.initializeStartedTime();
                zombie.revive();
                flag = false;
                break;
            }
        }

        used = false;
    }

    public boolean isTimerUsed(){
        return this.used;
    }
}
