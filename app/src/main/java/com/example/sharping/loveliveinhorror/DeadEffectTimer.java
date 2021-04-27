package com.example.sharping.loveliveinhorror;

import java.util.ArrayList;

/**
 * Created by SharPing on 2017-08-04.
 */

public class DeadEffectTimer extends CountTimer {

    private ArrayList<Zombie> zombies;
    private Zombie zombie;
    private ZombieDeadEffectShow zombieDeadEffectShow;

    public DeadEffectTimer(ArrayList<Zombie> zs, Zombie z, ZombieDeadEffectShow zdes){
        this.zombies = zs;
        this.zombie = z;
        this.zombieDeadEffectShow = zdes;
    }

    @Override
    public void run(){
        startedTime = System.currentTimeMillis();
        zombieDeadEffectShow.show = true;

        while((GameView.gameFlag == 1 || GameView.gameFlag == 3) && flag){
            if(System.currentTimeMillis() - startedTime >= limitTime){
                flag = false;
                break;
            }
        }

        if(!(zombie instanceof BossZombie && zombie.getZombiePower() > 0)) {
            zombie.flag = false;
            zombies.remove(zombie);
        }
        else {
            zombie.setLive(true);
        }

        zombieDeadEffectShow.show = false;
    }
}