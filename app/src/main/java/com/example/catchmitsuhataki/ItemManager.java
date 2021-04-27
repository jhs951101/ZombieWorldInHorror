package com.example.catchmitsuhataki;

/**
 * Created by 심지훈 on 2017-02-06.
 */

public class ItemManager extends Thread {

    private GameView view;
    private Kotori taki;

    boolean signal = false;
    boolean response = false;

    public ItemManager(GameView v){
        view = v;
    }

    public void setTaki(Kotori t){
        taki = t;
    }

    @Override
    public void run(){
        try{

            while(GameView.gameFlag){
                if(signal && !response){
                    response = true;

                    if(! Item.itemFlag) {
                        int n = (int) (Math.random() * 16) + 1;
                        view.item = createItem(n);
                        view.item.setPosition();

                        Thread itemMover = new Thread(view.item);
                        itemMover.start();
                    }

                    signal = false;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private Item createItem(int n){

        if(n == 1 || n == 2)
            return new LongItem(taki, view);
        if(n == 3 || n == 4)
            return new ShortItem(taki, view);
        else if(n == 5 || n == 6)
            return new SlowItem(taki, view);
        else if(n == 7 || n == 8)
            return new ReturnItem(taki, view);
        else if(n >= 9 && n <= 16)
            return new NoItem(taki, view);

        return null;
    }
}
