package com.example.sharping.loveliveinhorror;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;

/**
 * Created by SharPing on 2017-05-09.
 */

public class GameView extends View {

    public static int WIDTH;
    public static int HEIGHT;

    public static short gameFlag;
    /*
    0: 게임 미시작
    1: 승부 미판정
    2: 승리
    3: 패배
    */

    public static final int[] NUMBER_IDS = {R.drawable.char0, R.drawable.char1, R.drawable.char2, R.drawable.char3, R.drawable.char4, R.drawable.char5,
            R.drawable.char6, R.drawable.char7, R.drawable.char8, R.drawable.char9};

    public static final int ALPABET_e_IDS = R.drawable.chare;
    public static final int ALPABET_v_IDS = R.drawable.charv;
    public static final int ALPABET_l_IDS = R.drawable.charl;
    public static final int ALPABET_s_IDS = R.drawable.chars;
    public static final int ALPABET_t_IDS = R.drawable.chart;
    public static final int ALPABET_a_IDS = R.drawable.chara;
    public static final int ALPABET_g_IDS = R.drawable.charg;
    public static final int ALPABET_k_IDS = R.drawable.chark;
    public static final int ALPABET_i_IDS = R.drawable.chari;
    public static final int CHAR_BLANK_IDS = R.drawable.charblank;
    public static final int CHAR_DANGDANG_IDS = R.drawable.chardangdang;
    public static final int CHAR_SLASH_IDS = R.drawable.charslash;
    public static final int CHAR_PERCENTAGE_IDS = R.drawable.charpercentage;

    public static Bitmap[] IMG_NUMBER = new Bitmap[10];

    public static Bitmap IMG_ALPABET_e;
    public static Bitmap IMG_ALPABET_v;
    public static Bitmap IMG_ALPABET_l;
    public static Bitmap IMG_ALPABET_s;
    public static Bitmap IMG_ALPABET_t;
    public static Bitmap IMG_ALPABET_a;
    public static Bitmap IMG_ALPABET_g;
    public static Bitmap IMG_ALPABET_k;
    public static Bitmap IMG_ALPABET_i;
    public static Bitmap IMG_CHAR_BLANK;
    public static Bitmap IMG_CHAR_DANGDANG;
    public static Bitmap IMG_CHAR_SLASH;
    public static Bitmap IMG_CHAR_PERCENTAGE;

    private boolean setLength = false;
    private int MAX_HEALTH_LENGTH;

    private MainActivity activity;
    private GameManager manager;
    private ArrayList<Zombie> zombies;

    private BackgroundMusic introMusic;
    private BackgroundMusic gameMusic;
    private BackgroundMusic gameOverMusic;
    private BackgroundMusic gameClearMusic;

    private SoundEffect btnReleasedEffect;
    private SoundEffect swordEffect;
    private SoundEffect gunEffect;
    private SoundEffect shotgunEffect;
    private SoundEffect grenadeEffect;

    private SoundEffect primaryWeaponEffect;
    private SoundEffect secondaryWeaponEffect;

    private BackgroundMusic bgMusicState;
    private SoundEffect releasedEffectState;

    private Bitmap popupBackColor;
    private Bitmap damagedBackColor;
    private DamageBackColorShow damagedBackColorShow;

    private Bitmap btnStart;
    private Position btnStartPosition;
    private Length btnStartLength;

    private Bitmap btnInfo;
    private Position btnInfoPosition;
    private Length btnInfoLength;

    private Bitmap btnExit;
    private Position btnExitPosition;
    private Length btnExitLength;

    private Bitmap[][] btnStages = new Bitmap[5][5];
    private Position[][] btnStagePositions = new Position[5][5];
    private Length btnStageLength;

    private int[][] btnStageIds = {{R.drawable.btnstage11, R.drawable.btnstage12, R.drawable.btnstage13, R.drawable.btnstage14, R.drawable.btnstage15},
            {R.drawable.btnstage21, R.drawable.btnstage22, R.drawable.btnstage23, R.drawable.btnstage24, R.drawable.btnstage25},
            {R.drawable.btnstage31, R.drawable.btnstage32, R.drawable.btnstage33, R.drawable.btnstage34, R.drawable.btnstage35},
            {R.drawable.btnstage41, R.drawable.btnstage42, R.drawable.btnstage43, R.drawable.btnstage44, R.drawable.btnstage45},
            {R.drawable.btnstage51, R.drawable.btnstage52, R.drawable.btnstage53, R.drawable.btnstage54, R.drawable.btnstage55}};

    private int[][] btnStageDisabledIds = {{0, R.drawable.btnstagedisabled12, R.drawable.btnstagedisabled13, R.drawable.btnstagedisabled14, R.drawable.btnstagedisabled15},
            {R.drawable.btnstagedisabled21, R.drawable.btnstagedisabled22, R.drawable.btnstagedisabled23, R.drawable.btnstagedisabled24, R.drawable.btnstagedisabled25},
            {R.drawable.btnstagedisabled31, R.drawable.btnstagedisabled32, R.drawable.btnstagedisabled33, R.drawable.btnstagedisabled34, R.drawable.btnstagedisabled35},
            {R.drawable.btnstagedisabled41, R.drawable.btnstagedisabled42, R.drawable.btnstagedisabled43, R.drawable.btnstagedisabled44, R.drawable.btnstagedisabled45},
            {R.drawable.btnstagedisabled51, R.drawable.btnstagedisabled52, R.drawable.btnstagedisabled53, R.drawable.btnstagedisabled54, R.drawable.btnstagedisabled55}};

    private boolean[][] stageAvailable = new boolean[5][5];

    private Bitmap btnBack;
    private Position btnBackPosition;
    private Length btnBackLength;

    private Bitmap btnArmoryShop;
    private Position btnArmoryShopPosition;
    private Length btnArmoryShopLength;

    private Bitmap txtSelectStage;
    private Position txtSelectStagePosition;

    private Bitmap btnGameStart;
    private Position btnGameStartPosition;
    private Length btnGameStartLength;

    private Bitmap primaryWeaponIcon;
    private Position primaryWeaponIconPosition;

    private Bitmap secondaryWeaponIcon;
    private Position secondaryWeaponIconPosition;

    private Bitmap btnSelectPrimaryWpn;
    private Bitmap btnSelectPrimaryWpnState;
    private Bitmap btnSelectPrimaryWpnDisabled;
    private Position btnSelectPrimaryWpnPosition;
    private Length btnSelectPrimaryWpnLength;

    private Bitmap btnSelectSecondaryWpn;
    private Bitmap btnSelectSecondaryWpnState;
    private Bitmap btnSelectSecondaryWpnDisabled;
    private Position btnSelectSecondaryWpnPosition;
    private Length btnSelectSecondaryWpnLength;

    private Bitmap btnChangeWeapon;
    private Position btnChangeWeaponPosition;
    private Length btnChangeWeaponLength;

    private Bitmap btnNextStage;
    private Position btnNextStagePosition;
    private Length btnNextStageLength;

    private Bitmap popupBtnConfirm;
    private Position popupBtnConfirmPosition;
    private Length popupBtnConfirmLength;

    private Bitmap popupBtnCancel;
    private Position popupBtnCancelPosition;
    private Length popupBtnCancelLength;

    private Bitmap popupExit;
    private Bitmap popupGameStart;
    private Bitmap popupDemoVersion;
    private Bitmap popupGameOver;
    private Position popupExitPosition;

    private Bitmap popupStaffInfo;
    private Position popupStaffInfoPosition;

    private Bitmap swordIcon;
    private Position swordIconPosition;
    private Length swordIconLength;

    private Bitmap gunIcon;
    private Position gunIconPosition;
    private Length gunIconLength;

    private Bitmap shotgunIcon;
    private Position shotgunIconPosition;
    private Length shotgunIconLength;

    private Bitmap grenadeIcon;
    private Position grenadeIconPosition;
    private Length grenadeIconLength;

    private Bitmap heartIcon;
    private Position heartIconPosition;
    private Length heartIconLength;

    private Bitmap txtStageClear;
    private Position txtStageClearPosition;
    private Length txtStageClearLength;

    private Bitmap txtElapsedTime;
    private Position txtElapsedTimePosition;
    private Length txtElapsedTimeLength;

    private Bitmap txtCurrentStamina;
    private Position txtCurrentStaminaPosition;
    private Length txtCurrentStaminaLength;

    private Bitmap txtTotalScore;
    private Position txtTotalScorePosition;
    private Length txtTotalScoreLength;

    private Bitmap notAvailable;
    private Length notAvailableLength;

    private boolean[] weaponAvailables = new boolean[4];
    /*
    0: sword
    1: gun
    2: shotgun
    3: grenade
    */

    private Bitmap resizedEndLine;
    private Length resizedEndLineLength;

    private Bitmap hit;
    private Bitmap hitDamaged;
    private Length hitLength;

    private ArrayList<TouchPosition> touches;
    private DamageEffectTimer damageEffectTimer;

    private double MAX_HEALTH;
    private int TARGET_KILL;
    private int myHealth;

    private long startedTime;
    private String elapsedTime;
    private String totalScore;

    private int selectedLevel;
    private int selectedStage;

    private WeaponInfo[] weapons = new WeaponInfo[4];
    private WeaponInfo ownWeapon;

    private ImageString imgLevelStage;
    private ImageString imgNumOfKill;
    private ImageString imgElapsedTime;
    private ImageString imgRemainStamina;
    private ImageString imgTotalScore;

    private char primaryWeapon;
    private char secondaryWeapon;
    /*
    a: sword
    b: gun
    c: shotgun
    d: grenade  // 공격력 MAX: 60
    */

    private boolean primarySelecting;
    private boolean secondarySelecting;
    private boolean popupShowed;

    private char popupType;
    /*
    screenNumber == 1 일 때,
      a: 나가기 여부
      b: 게임 제작자 정보
    screemNumber == 4 일 때,
      a: 해당 스테이지 진행 여부
      b: 데모 버전 알림
    screemNumber == 2 일 때,
      a: 패배로 인한 재시작 여부
      b: 게임 중단 및 나가기 여부
      c: 데모 버전 알림
    */

    public int screenNumber = 1;
    /*
    1: 메뉴 선택 화면 (1) (★)
    2: 게임 화면 (4) (★)
    4: 스테이지 선택 화면 (2) (★)
    7: 무기 선택 화면 (3) (★)
    8: 무기 상점 화면
    9: 무기 업그레이드 화면
    */

    public boolean open1;
    public boolean open2;

    public GameView(MainActivity a, Context context) {
        super(context);
        this.activity = a;
        setView(context);
    }

    public GameView(MainActivity a, Context context, AttributeSet attrs) {
        super(context, attrs);
        this.activity = a;
        setView(context);
    }

    public void setStageButtons(){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){

                if((i == 0 && j == 0)|| (i == 0 && j == 4))
                    stageAvailable[i][j] = true;
                else
                    stageAvailable[i][j] = false;

                if(stageAvailable[i][j] == true)
                    btnStages[i][j] = BitmapFactory.decodeResource(getResources(), btnStageIds[i][j]);
                else
                    btnStages[i][j] = BitmapFactory.decodeResource(getResources(), btnStageDisabledIds[i][j]);
            }
        }
    }

    public void setImgCharacters(){
        for(int i=0; i<10; i++){
            IMG_NUMBER[i] = BitmapFactory.decodeResource(getResources(), NUMBER_IDS[i]);
        }

        IMG_ALPABET_e = BitmapFactory.decodeResource(getResources(), ALPABET_e_IDS);
        IMG_ALPABET_v = BitmapFactory.decodeResource(getResources(), ALPABET_v_IDS);
        IMG_ALPABET_l = BitmapFactory.decodeResource(getResources(), ALPABET_l_IDS);
        IMG_ALPABET_s = BitmapFactory.decodeResource(getResources(), ALPABET_s_IDS);
        IMG_ALPABET_t = BitmapFactory.decodeResource(getResources(), ALPABET_t_IDS);
        IMG_ALPABET_a = BitmapFactory.decodeResource(getResources(), ALPABET_a_IDS);
        IMG_ALPABET_g = BitmapFactory.decodeResource(getResources(), ALPABET_g_IDS);
        IMG_ALPABET_k = BitmapFactory.decodeResource(getResources(), ALPABET_k_IDS);
        IMG_ALPABET_i = BitmapFactory.decodeResource(getResources(), ALPABET_i_IDS);
        IMG_CHAR_BLANK = BitmapFactory.decodeResource(getResources(), CHAR_BLANK_IDS);
        IMG_CHAR_DANGDANG = BitmapFactory.decodeResource(getResources(), CHAR_DANGDANG_IDS);
        IMG_CHAR_SLASH = BitmapFactory.decodeResource(getResources(), CHAR_SLASH_IDS);
        IMG_CHAR_PERCENTAGE = BitmapFactory.decodeResource(getResources(), CHAR_PERCENTAGE_IDS);
    }

    public void setBitmaps1(Context c){
        btnStart = BitmapFactory.decodeResource(c.getResources(), R.drawable.btnstart);
        btnInfo = BitmapFactory.decodeResource(c.getResources(), R.drawable.btninfo);
        btnExit = BitmapFactory.decodeResource(c.getResources(), R.drawable.btnexit);

        btnStages[0][0] = BitmapFactory.decodeResource(c.getResources(), btnStageIds[0][0]);
        btnBack = BitmapFactory.decodeResource(c.getResources(), R.drawable.btnback);
        btnArmoryShop = BitmapFactory.decodeResource(c.getResources(), R.drawable.btnarmoryshop);
        txtSelectStage = BitmapFactory.decodeResource(c.getResources(), R.drawable.txtselectstage);

        swordIcon = BitmapFactory.decodeResource(c.getResources(), R.drawable.swordicon);
        gunIcon = BitmapFactory.decodeResource(c.getResources(), R.drawable.gunicon);
        shotgunIcon = BitmapFactory.decodeResource(c.getResources(), R.drawable.shotgunicon);
        grenadeIcon = BitmapFactory.decodeResource(c.getResources(), R.drawable.grenadeicon);

        txtStageClear = BitmapFactory.decodeResource(c.getResources(), R.drawable.txtstageclear);
        txtElapsedTime = BitmapFactory.decodeResource(c.getResources(), R.drawable.txtelapsedtime);
        txtCurrentStamina = BitmapFactory.decodeResource(c.getResources(), R.drawable.txtcurrnetstamina);
        txtTotalScore = BitmapFactory.decodeResource(c.getResources(), R.drawable.txttotalscore);

        notAvailable = BitmapFactory.decodeResource(c.getResources(), R.drawable.notavailable);
        hit = BitmapFactory.decodeResource(c.getResources(), R.drawable.smallhit);
    }

    public void setBitmaps2(Context c){
        primaryWeaponIcon = BitmapFactory.decodeResource(c.getResources(), R.drawable.primaryweapon);
        secondaryWeaponIcon = BitmapFactory.decodeResource(c.getResources(), R.drawable.secondaryweapon);

        btnSelectPrimaryWpn = BitmapFactory.decodeResource(c.getResources(), R.drawable.btnselectprimary);
        btnSelectSecondaryWpn = BitmapFactory.decodeResource(c.getResources(), R.drawable.btnselectsecondary);
        btnChangeWeapon = BitmapFactory.decodeResource(c.getResources(), R.drawable.btnchangeweapon);

        btnGameStart = BitmapFactory.decodeResource(c.getResources(), R.drawable.btngamestart);
        btnNextStage = BitmapFactory.decodeResource(c.getResources(), R.drawable.btnnextstage);
        heartIcon = BitmapFactory.decodeResource(c.getResources(), R.drawable.hearticon);

        popupBtnConfirm = BitmapFactory.decodeResource(c.getResources(), R.drawable.popupbtnconfirm);
        popupBtnCancel = BitmapFactory.decodeResource(c.getResources(), R.drawable.popupbtncancel);

        popupExit = BitmapFactory.decodeResource(c.getResources(), R.drawable.popupexit);
        popupGameStart = BitmapFactory.decodeResource(c.getResources(), R.drawable.popupgamestart);
        popupDemoVersion = BitmapFactory.decodeResource(c.getResources(), R.drawable.popupdemoversion);
        popupStaffInfo = BitmapFactory.decodeResource(c.getResources(), R.drawable.staffinfo);
        popupGameOver = BitmapFactory.decodeResource(c.getResources(), R.drawable.popupgameover);
    }

    public void setLengthes1(){
        btnStartLength = new Length(btnStart.getWidth(), btnStart.getHeight());
        btnInfoLength = new Length(btnStart.getWidth(), btnStart.getHeight());
        btnExitLength = new Length(btnStart.getWidth(), btnStart.getHeight());

        btnStageLength = new Length(btnStages[0][0].getWidth(), btnStages[0][0].getHeight());
        btnBackLength = new Length(btnBack.getWidth(), btnBack.getHeight());
        btnArmoryShopLength = new Length(btnArmoryShop.getWidth(), btnArmoryShop.getHeight());

        swordIconLength = new Length(swordIcon.getWidth(), swordIcon.getHeight());
        gunIconLength = new Length(swordIcon.getWidth(), swordIcon.getHeight());
        shotgunIconLength = new Length(swordIcon.getWidth(), swordIcon.getHeight());
        grenadeIconLength = new Length(swordIcon.getWidth(), swordIcon.getHeight());

        txtStageClearLength = new Length(txtStageClear.getWidth(), txtStageClear.getHeight());
        txtElapsedTimeLength = new Length(txtElapsedTime.getWidth(), txtElapsedTime.getHeight());
        txtCurrentStaminaLength = new Length(txtCurrentStamina.getWidth(), txtCurrentStamina.getHeight());
        txtTotalScoreLength = new Length(txtTotalScore.getWidth(), txtTotalScore.getHeight());

        notAvailableLength = new Length(notAvailable.getWidth(), notAvailable.getHeight());
        hitLength = new Length(hit.getWidth(), hit.getHeight());
    }

    public void setLengthes2(){
        btnSelectPrimaryWpnLength = new Length(btnSelectPrimaryWpn.getWidth(), btnSelectPrimaryWpn.getHeight());
        btnSelectSecondaryWpnLength = new Length(btnSelectSecondaryWpn.getWidth(), btnSelectSecondaryWpn.getHeight());
        btnChangeWeaponLength = new Length(btnChangeWeapon.getWidth(), btnChangeWeapon.getHeight());

        btnGameStartLength = new Length(btnGameStart.getWidth(), btnGameStart.getHeight());
        btnNextStageLength = new Length(btnNextStage.getWidth(), btnNextStage.getHeight());
        heartIconLength = new Length(heartIcon.getWidth(), heartIcon.getHeight());

        popupBtnConfirmLength = new Length(popupBtnConfirm.getWidth(), popupBtnConfirm.getHeight());
        popupBtnCancelLength = new Length(popupBtnCancel.getWidth(), popupBtnCancel.getHeight());
    }

    public void removeStageButtons(){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                btnStages[i][j].recycle();
                btnStages[i][j] = null;
            }
        }
    }

    public void removeImgCharacters(){
        for(int i=0; i<10; i++){
            IMG_NUMBER[i].recycle();
            IMG_NUMBER[i] = null;
        }

        IMG_ALPABET_e.recycle();
        IMG_ALPABET_v.recycle();
        IMG_ALPABET_l.recycle();
        IMG_ALPABET_s.recycle();
        IMG_ALPABET_t.recycle();
        IMG_ALPABET_a.recycle();
        IMG_ALPABET_g.recycle();
        IMG_ALPABET_k.recycle();
        IMG_ALPABET_i.recycle();
        IMG_CHAR_BLANK.recycle();
        IMG_CHAR_DANGDANG.recycle();
        IMG_CHAR_SLASH.recycle();
        IMG_CHAR_PERCENTAGE.recycle();

        IMG_ALPABET_e = null;
        IMG_ALPABET_v = null;
        IMG_ALPABET_l = null;
        IMG_ALPABET_s = null;
        IMG_ALPABET_t = null;
        IMG_ALPABET_a = null;
        IMG_ALPABET_g = null;
        IMG_ALPABET_k = null;
        IMG_ALPABET_i = null;
        IMG_CHAR_BLANK = null;
        IMG_CHAR_DANGDANG = null;
        IMG_CHAR_SLASH = null;
        IMG_CHAR_PERCENTAGE = null;
    }

    public void removeBitmaps1(){
        btnStages[0][0].recycle();
        btnBack.recycle();
        btnArmoryShop.recycle();
        txtSelectStage.recycle();

        swordIcon.recycle();
        gunIcon.recycle();
        shotgunIcon.recycle();
        grenadeIcon.recycle();

        txtStageClear.recycle();
        txtElapsedTime.recycle();
        txtCurrentStamina.recycle();
        txtTotalScore.recycle();

        notAvailable.recycle();
        hit.recycle();

        btnStages[0][0] = null;
        btnBack = null;
        btnArmoryShop = null;
        txtSelectStage = null;

        swordIcon = null;
        gunIcon = null;
        shotgunIcon = null;
        grenadeIcon = null;

        txtStageClear = null;
        txtElapsedTime = null;
        txtCurrentStamina = null;
        txtTotalScore = null;

        notAvailable = null;
        hit = null;
    }

    public void removeBitmaps2(){
        primaryWeaponIcon.recycle();
        secondaryWeaponIcon.recycle();

        btnSelectPrimaryWpn.recycle();
        btnSelectSecondaryWpn.recycle();
        btnChangeWeapon.recycle();

        btnGameStart.recycle();
        btnNextStage.recycle();
        heartIcon.recycle();

        primaryWeaponIcon = null;
        secondaryWeaponIcon = null;

        btnSelectPrimaryWpn = null;
        btnSelectSecondaryWpn = null;
        btnChangeWeapon = null;

        btnGameStart = null;
        btnNextStage = null;
        heartIcon = null;
    }

    public void getMyWeaponInfo(Context c){
        MAX_HEALTH = 100.0;

        weapons[0] = new WeaponInfo('a', 10, -1, 0, 1);
        weapons[1] = new WeaponInfo('b', 0, 0, 0, 0);
        weapons[2] = new WeaponInfo('c', 0, 0, 0, 0);
        weapons[3] = new WeaponInfo('d', 0, 0, 0, 0);

        weaponAvailables[0] = true;
        weaponAvailables[1] = false;
        weaponAvailables[2] = false;
        weaponAvailables[3] = false;

        primaryWeapon = 'a';
        secondaryWeapon = ' ';  // secondaryWeapon == ' ' : 무기가 1개밖에 없음

        setOwnWeapon(c.getResources());
        setWeaponSoundEffect(primaryWeapon, secondaryWeapon);
    }

    public void setOwnWeapon(Resources r){
        ownWeapon = getWeaponWithBinarySearch(primaryWeapon);

        if(ownWeapon.getAttackRange() == 1) {
            hit = BitmapFactory.decodeResource(r, R.drawable.smallhit);
            hitDamaged = BitmapFactory.decodeResource(r, R.drawable.smallhitdamaged);
        }
    }

    public void setWeaponSoundEffect(char pw, char sw){
        if(pw == 'a')
            primaryWeaponEffect = swordEffect;
        else if(pw == 'b')
            primaryWeaponEffect = gunEffect;
        else if(pw == 'c')
            primaryWeaponEffect = shotgunEffect;
        else if(pw == 'd')
            primaryWeaponEffect = grenadeEffect;

        if(sw == 'a')
            secondaryWeaponEffect = swordEffect;
        else if(sw == 'b')
            secondaryWeaponEffect = gunEffect;
        else if(sw == 'c')
            secondaryWeaponEffect = shotgunEffect;
        else if(sw == 'd')
            secondaryWeaponEffect = grenadeEffect;
        else
            secondaryWeaponEffect = null;
    }

    public void setView(Context c){
        this.setBackgroundResource(R.drawable.introbackground);

        setBitmaps1(c);
        setLengthes1();
        removeBitmaps1();
        setBitmaps2(c);
        setLengthes2();
        removeBitmaps2();

        zombies = new ArrayList<>();
        touches = new ArrayList<>();

        introMusic = new IntroMusic(this, c);
        gameMusic = new GameMusic(this, c);
        gameOverMusic = new GameOverMusic(c);
        gameClearMusic = new GameClearMusic(c);

        btnReleasedEffect = new BtnReleasedEffect(this, c);
        swordEffect = new SwordEffect(this, c);
        gunEffect = new GunEffect(this, c);
        shotgunEffect = new ShotGunEffect(this, c);
        grenadeEffect = new GrenadeEffect(this, c);

        bgMusicState = introMusic;
        releasedEffectState = btnReleasedEffect;
        damagedBackColorShow = new DamageBackColorShow();

        imgLevelStage = new ImageString();
        imgNumOfKill = new ImageString();
        imgElapsedTime = new ImageString();
        imgRemainStamina = new ImageString();
        imgTotalScore = new ImageString();

        getMyWeaponInfo(c);

        primarySelecting = false;
        secondarySelecting = false;
        popupShowed = false;
        gameFlag = 0;

        bgMusicState.playSound();
    }

    public int getCenterPosition(int pos, int length){
        return pos - (length/2);
    }

    public void drawButtons(Canvas canvas){
        if(screenNumber == 1) {
            canvas.drawBitmap(btnStart, btnStartPosition.getX(), btnStartPosition.getY(), null);
            canvas.drawBitmap(btnInfo, btnInfoPosition.getX(), btnInfoPosition.getY(), null);
            canvas.drawBitmap(btnExit, btnExitPosition.getX(), btnExitPosition.getY(), null);
        }
        else if(screenNumber == 2) {
            if(gameFlag == 1){
                canvas.drawBitmap(btnChangeWeapon, btnChangeWeaponPosition.getX(), btnChangeWeaponPosition.getY(), null);
            }
            else if(gameFlag == 2) {
                canvas.drawBitmap(btnBack, btnBackPosition.getX(), btnBackPosition.getY(), null);
                canvas.drawBitmap(btnNextStage, btnNextStagePosition.getX(), btnNextStagePosition.getY(), null);
            }
        }
        else if(screenNumber == 4) {
            for(int i=0; i<5; i++){
                for(int j=0; j<5; j++){
                    canvas.drawBitmap(btnStages[i][j], btnStagePositions[i][j].getX(), btnStagePositions[i][j].getY(), null);
                }
            }

            canvas.drawBitmap(btnBack, btnBackPosition.getX(), btnBackPosition.getY(), null);
            canvas.drawBitmap(btnArmoryShop, btnArmoryShopPosition.getX(), btnArmoryShopPosition.getY(), null);
        }
        else if(screenNumber == 7){
            canvas.drawBitmap(swordIcon, swordIconPosition.getX(), swordIconPosition.getY(), null);
            canvas.drawBitmap(gunIcon, gunIconPosition.getX(), gunIconPosition.getY(), null);
            canvas.drawBitmap(shotgunIcon, shotgunIconPosition.getX(), shotgunIconPosition.getY(), null);
            canvas.drawBitmap(grenadeIcon, grenadeIconPosition.getX(), grenadeIconPosition.getY(), null);

            if(!weaponAvailables[1])
                canvas.drawBitmap(notAvailable, getCenterPosition(gunIconPosition.getX() + gunIconLength.getWidth()/2, notAvailableLength.getWidth()),
                        getCenterPosition(gunIconPosition.getY() + gunIconLength.getHeight()/2, notAvailableLength.getHeight()), null);
            if(!weaponAvailables[2])
                canvas.drawBitmap(notAvailable, getCenterPosition(shotgunIconPosition.getX() + shotgunIconLength.getWidth()/2, notAvailableLength.getWidth()),
                        getCenterPosition(shotgunIconPosition.getY() + shotgunIconLength.getHeight()/2, notAvailableLength.getHeight()), null);
            if(!weaponAvailables[3])
                canvas.drawBitmap(notAvailable, getCenterPosition(grenadeIconPosition.getX() + grenadeIconLength.getWidth()/2, notAvailableLength.getWidth()),
                        getCenterPosition(grenadeIconPosition.getY() + grenadeIconLength.getHeight()/2, notAvailableLength.getHeight()), null);

            canvas.drawBitmap(btnSelectPrimaryWpnState, btnSelectPrimaryWpnPosition.getX(), btnSelectPrimaryWpnPosition.getY(), null);
            canvas.drawBitmap(btnSelectSecondaryWpnState, btnSelectSecondaryWpnPosition.getX(), btnSelectSecondaryWpnPosition.getY(), null);

            canvas.drawBitmap(btnGameStart, btnGameStartPosition.getX(), btnGameStartPosition.getY(), null);
            canvas.drawBitmap(btnBack, btnBackPosition.getX(), btnBackPosition.getY(), null);
        }
    }

    public void drawDamagedBackColor(Canvas canvas){
        if(damagedBackColorShow.show)
            canvas.drawBitmap(damagedBackColor, 0, 0, null);
    }

    public void drawTouchCircles(Canvas canvas){
        for(int i=0; i<touches.size(); i++){
            TouchPosition p = touches.get(i);

            if(p.isDamaged())
                canvas.drawBitmap(hitDamaged, getCenterPosition(p.getX(), hitLength.getWidth()), getCenterPosition(p.getY(), hitLength.getHeight()), null);
            else
                canvas.drawBitmap(hit, getCenterPosition(p.getX(), hitLength.getWidth()), getCenterPosition(p.getY(), hitLength.getHeight()), null);
        }
    }

    public void drawHealthBar(Canvas canvas){
        Paint healthFrameStyle = new Paint();
        healthFrameStyle.setColor(Color.WHITE);
        healthFrameStyle.setStyle(Paint.Style.STROKE);
        healthFrameStyle.setStrokeWidth(10);

        Paint healthStyle = new Paint();
        healthStyle.setColor(Color.RED);
        healthStyle.setStyle(Paint.Style.FILL);

        Paint zombiePowerStyle = new Paint();
        zombiePowerStyle.setColor(Color.rgb(255/2, 0, 0));
        zombiePowerStyle.setStyle(Paint.Style.FILL);

        for(int i=0; i<zombies.size(); i++) {
            Zombie zombie = zombies.get(i);

            if(zombie instanceof BossZombie) {
                Rect zombiePower = new Rect(zombie.getX(), zombie.getY() - 20 - 50 - 70, zombie.getX() + (int) zombie.convertToEnemyZombiePower(zombie.getZombiePower()), zombie.getY() - 20 - 70);
                Rect zombiePowerFrame = new Rect(zombie.getX(), zombie.getY() - 20 - 50 - 70, zombie.getX() + zombie.getWidth(), zombie.getY() - 20 - 70);

                canvas.drawRect(zombiePower, zombiePowerStyle);
                canvas.drawRect(zombiePowerFrame, healthFrameStyle);  // 적의 좀비파워바
            }

            Rect enemyHealthBar = new Rect(zombie.getX(), zombie.getY() - 20 - 50, zombie.getX() + (int) zombie.convertToEnemyHealth(zombie.getHealth()), zombie.getY() - 20);
            Rect enemyHealthFrame = new Rect(zombie.getX(), zombie.getY() - 20 - 50, zombie.getX() + zombie.getWidth(), zombie.getY() - 20);

            canvas.drawRect(enemyHealthBar, healthStyle);
            canvas.drawRect(enemyHealthFrame, healthFrameStyle);  // 적의 체력바
        }

        Rect myHealthBar = new Rect(50 + heartIconLength.getWidth() + 20, HEIGHT-100, (int) convertToMyHealth(myHealth), HEIGHT-50);
        Rect myHealthFrame = new Rect(50 + heartIconLength.getWidth() + 20, HEIGHT-100, MAX_HEALTH_LENGTH, HEIGHT-50);

        canvas.drawBitmap(heartIcon, heartIconPosition.getX(), heartIconPosition.getY(), null);
        canvas.drawRect(myHealthBar, healthStyle);
        canvas.drawRect(myHealthFrame, healthFrameStyle);  // 자신의 체력바
    }

    public void showPopupButtons(Canvas canvas, char type){
        if(screenNumber == 1 && popupType == 'b')
            popupBtnConfirmPosition.setY(popupStaffInfoPosition.getY() + popupStaffInfo.getHeight() - popupBtnConfirmLength.getHeight() - 50);
        else
            popupBtnConfirmPosition.setY(popupExitPosition.getY() + popupExit.getHeight() - popupBtnConfirmLength.getHeight() - 50);

        if(type == 'a'){
            popupBtnConfirmPosition.setX( getCenterPosition(WIDTH/2, popupBtnConfirmLength.getWidth()) );
            canvas.drawBitmap(popupBtnConfirm, popupBtnConfirmPosition.getX(), popupBtnConfirmPosition.getY(), null);
        }
        else if(type == 'c'){
            popupBtnConfirmPosition.setX( getCenterPosition(WIDTH/2 - 150, popupBtnConfirmLength.getWidth()) );
            canvas.drawBitmap(popupBtnConfirm, popupBtnConfirmPosition.getX(), popupBtnConfirmPosition.getY(), null);
            canvas.drawBitmap(popupBtnCancel, popupBtnCancelPosition.getX(), popupBtnCancelPosition.getY(), null);
        }
    }

    public void turnOnDamageEffectTimer(Zombie zombie){
        if(zombie instanceof SmallZombie)
            myHealth -= 20;
        else if(zombie instanceof BigZombie)
            myHealth -= 40;
        else if(zombie instanceof BossZombie)
            myHealth -= 30;

        damageEffectTimer = new DamageEffectTimer(damagedBackColorShow);
        damageEffectTimer.setLimitTime(100);
        damageEffectTimer.start();
    }

    public void confirmGameOver(){
        if (myHealth <= 0) {
            gameFlag = 3;
        }
    }

    @Override
    public void onDraw(Canvas canvas){

        if(!setLength){
            setWIdthHeight();
        }
        else{
            if(screenNumber == 1){
                drawButtons(canvas);

                if(popupShowed){
                    canvas.drawBitmap(popupBackColor, 0, 0, null);

                    if(popupType == 'a') {
                        canvas.drawBitmap(popupExit, popupExitPosition.getX(), popupExitPosition.getY(), null);
                        showPopupButtons(canvas, 'c');
                    }
                    else if(popupType == 'b') {
                        canvas.drawBitmap(popupStaffInfo, popupStaffInfoPosition.getX(), popupStaffInfoPosition.getY(), null);
                        showPopupButtons(canvas, 'a');
                    }
                }
            }
            else if(screenNumber == 4){
                canvas.drawBitmap(txtSelectStage, txtSelectStagePosition.getX(), txtSelectStagePosition.getY(), null);
                drawButtons(canvas);

                if(popupShowed){
                    canvas.drawBitmap(popupBackColor, 0, 0, null);

                    if(popupType == 'a') {
                        Paint textStyle = new Paint();
                        textStyle.setColor(Color.RED);
                        textStyle.setTextSize(80);

                        canvas.drawBitmap(popupGameStart, popupExitPosition.getX(), popupExitPosition.getY(), null);
                        //canvas.drawText("Level: " + selectedLevel + " & Stage: " + selectedStage, WIDTH / 2 - 300, popupExitPosition.getY() + 120, textStyle);
                        imgLevelStage.drawImageString(canvas, getCenterPosition(WIDTH/2, imgLevelStage.getWIdth()), popupExitPosition.getY() + 50);
                        showPopupButtons(canvas, 'c');
                    }
                    else if(popupType == 'b') {
                        canvas.drawBitmap(popupDemoVersion, popupExitPosition.getX(), popupExitPosition.getY(), null);
                        showPopupButtons(canvas, 'a');
                    }
                }
            }
            else if(screenNumber == 7){
                drawButtons(canvas);

                canvas.drawBitmap(primaryWeaponIcon, primaryWeaponIconPosition.getX(), primaryWeaponIconPosition.getY(), null);

                if(secondaryWeapon != ' ')
                    canvas.drawBitmap(secondaryWeaponIcon, secondaryWeaponIconPosition.getX(), secondaryWeaponIconPosition.getY(), null);
            }
            else if(screenNumber == 2) {
                if(gameFlag == 1) {  // 승부 미판정
                    if(selectedStage < 5)
                        canvas.drawBitmap(resizedEndLine, 0, HEIGHT - resizedEndLineLength.getHeight(), null);

                    for(int i=0; i<zombies.size(); i++) {
                        Zombie zombie = zombies.get(i);
                        zombie.draw(canvas);

                        if (zombie instanceof BossZombie)
                            zombie.createCircles();

                        if(zombie.isLive() && (zombie.getY() + zombie.getHeight() >= HEIGHT || zombie.isFailure())){
                            turnOnDamageEffectTimer(zombie);
                            confirmGameOver();
                            zombie.playLaughEffect();

                            if(zombie.getY() + zombie.getHeight() >= HEIGHT) {
                                zombie.flag = false;
                                zombies.remove(i);

                                if(manager.getCurrentNumOfZombie() <= 0) {
                                    GameView.gameFlag = 2;
                                    long durationTime = System.currentTimeMillis() - startedTime;
                                    elapsedTime = convertToHMS(durationTime);
                                    totalScore = calculateScore(durationTime);
                                }
                            }
                        }
                    }

                    drawHealthBar(canvas);
                    drawTouchCircles(canvas);
                    drawDamagedBackColor(canvas);
                    drawButtons(canvas);

                    Paint textStyle = new Paint();
                    textStyle.setColor(Color.YELLOW);
                    textStyle.setTextSize(100);
                    //canvas.drawText("KILL: " + (TARGET_KILL - manager.getCurrentNumOfZombie()) + " / " + TARGET_KILL, 50, 100, textStyle);
                    imgNumOfKill.drawImageString(canvas, 50, 50);
                }
                else if(gameFlag == 2){  // 승리
                    if(open2)
                        open2Operation();

                    Paint textStyle = new Paint();
                    textStyle.setColor(Color.YELLOW);
                    textStyle.setTextSize(100);

                    canvas.drawBitmap(popupBackColor, 0, 0, null);

                    canvas.drawBitmap(txtStageClear, txtStageClearPosition.getX(), txtStageClearPosition.getY(), null);
                    canvas.drawBitmap(txtElapsedTime, txtElapsedTimePosition.getX(), txtElapsedTimePosition.getY(), null);
                    canvas.drawBitmap(txtCurrentStamina, txtCurrentStaminaPosition.getX(), txtCurrentStaminaPosition.getY(), null);
                    canvas.drawBitmap(txtTotalScore, txtTotalScorePosition.getX(), txtTotalScorePosition.getY(), null);

                    //canvas.drawText(elapsedTime, txtElapsedTimeLength.getWidth() + 10, txtElapsedTimePosition.getY() + 100, textStyle);  // 지정한 스타일을 적용해 텍스트를 그림
                    //canvas.drawText(String.valueOf(myHealth) + "%", txtCurrentStaminaLength.getWidth() + 10, txtCurrentStaminaPosition.getY() + 100, textStyle);
                    //canvas.drawText(totalScore, txtTotalScore.getWidth() + 10, txtTotalScorePosition.getY() + 100, textStyle);
                    imgElapsedTime.drawImageString(canvas, txtElapsedTimePosition.getX() + txtElapsedTimeLength.getWidth() + 50, txtElapsedTimePosition.getY());
                    imgRemainStamina.drawImageString(canvas, txtCurrentStaminaPosition.getX() + txtCurrentStaminaLength.getWidth() + 50, txtCurrentStaminaPosition.getY());
                    imgTotalScore.drawImageString(canvas, txtTotalScorePosition.getX() + txtTotalScoreLength.getWidth() + 50, txtTotalScorePosition.getY());

                    drawButtons(canvas);

                    if(popupShowed && popupType == 'c') {
                        canvas.drawBitmap(popupBackColor, 0, 0, null);
                        canvas.drawBitmap(popupDemoVersion, popupExitPosition.getX(), popupExitPosition.getY(), null);
                        showPopupButtons(canvas, 'a');
                    }
                }
                else if(gameFlag == 3){  // 패배
                    if(open1)
                        open1Operation();

                    drawDamagedBackColor(canvas);

                    if(popupShowed) {
                        canvas.drawBitmap(popupBackColor, 0, 0, null);
                        canvas.drawBitmap(popupGameOver, popupExitPosition.getX(), popupExitPosition.getY(), null);
                        showPopupButtons(canvas, 'c');
                    }
                }
            }
        }

        invalidate();
    }

    public void open1Operation(){
        gameOverMusic.playSound();
        releasedEffectState.gameEnd();
        popupShowed = true;
        open1 = false;
    }

    public void open2Operation(){
        bgMusicState.stopSound();
        gameClearMusic.playSound();
        releasedEffectState.gameEnd();
        open2 = false;
    }

    public void gameStart(int level, int stage){
        btnReleasedEffect.playSound();
        bgMusicState.gameStart();
        releasedEffectState.gameStart();
        this.setBackgroundResource(R.drawable.gamebackground);

        myHealth = (int) MAX_HEALTH;
        gameFlag = 1;
        damagedBackColorShow.show = false;
        startedTime = System.currentTimeMillis();

        zombies.clear();
        touches.clear();

        manager = new GameManager(getResources(), getContext(), ownWeapon, zombies, level ,stage);
        TARGET_KILL = manager.getCurrentNumOfZombie();
        manager.start();
        imgNumOfKill.setImageString("kill: " + (TARGET_KILL - manager.getCurrentNumOfZombie()) + " / " + TARGET_KILL);

        open1 = true;
        open2 = true;
        screenNumber = 2;
    }

    public WeaponInfo getWeaponWithBinarySearch(char wi){
        int start = 0;
        int end = 3;

        while(start <= end){
            int mid = (start+end)/2;
            char ids = weapons[mid].getWId();

            if(wi == ids) {
                return weapons[mid];
            }
            else if(wi < ids) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }

        return null;
    }

    public void attackCharacter(int X, int Y){
        boolean damaged = false;

        for(int i=0; i<zombies.size(); i++) {
            Zombie zombie = zombies.get(i);

            zombie.circleTouch(X, Y);

            if(X >= zombie.getX() && X <= zombie.getX() + zombie.getWidth()
                    && Y >= zombie.getY() && Y <= zombie.getY() + zombie.getHeight()
                    && !zombie.HPTimerUsed()) {
                boolean victory = zombie.minusStamina(ownWeapon.getAttackPower());
                damaged = true;

                if(victory && zombie.isLive()) {
                    zombie.turnOnDeadEffectTImer(zombies);
                    manager.minusCurrentNumOfZombie();
                    imgNumOfKill.setImageString("kill: " + (TARGET_KILL - manager.getCurrentNumOfZombie()) + " / " + TARGET_KILL);

                    if(manager.getCurrentNumOfZombie() <= 0) {
                        GameView.gameFlag = 2;
                        long durationTime = System.currentTimeMillis() - startedTime;
                        elapsedTime = convertToHMS(durationTime);
                        totalScore = calculateScore(durationTime);

                        imgElapsedTime.setImageString(elapsedTime);
                        imgRemainStamina.setImageString(String.valueOf(myHealth) + "%");
                        imgTotalScore.setImageString(totalScore);
                    }
                }
            }
        }

        touches.add(new TouchPosition(X, Y, damaged));
    }

    public void removeTouchEffect(){
        if(touches.size() != 0)
            touches.remove(0);
    }

    public void showPrimaryWeaponIcon(int x, int y){
        primaryWeaponIconPosition.setX(x);
        primaryWeaponIconPosition.setY(y);

        primarySelecting = false;
        btnSelectPrimaryWpnState = btnSelectPrimaryWpn;
    }

    public void showSecondaryWeaponIcon(int x, int y){
        secondaryWeaponIconPosition.setX(x - 20);
        secondaryWeaponIconPosition.setY(y);

        secondarySelecting = false;
        btnSelectSecondaryWpnState = btnSelectSecondaryWpn;
    }

    public void selectCancelButton(){
        btnReleasedEffect.playSound();
        popupShowed = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        final int action = event.getAction();

        int X = (int) event.getRawX();
        int Y = (int) event.getRawY() - 100;

        int pointer_count = event.getPointerCount();

        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                if(screenNumber == 2 && gameFlag == 1) {
                    X = (int) event.getX(0);
                    Y = (int) event.getY(0);

                    attackCharacter(X, Y);
                    releasedEffectState.playSound();
                }
                else if(screenNumber == 7 && !popupShowed){
                    if(X >= btnSelectPrimaryWpnPosition.getX() && X <= btnSelectPrimaryWpnPosition.getX() + btnSelectPrimaryWpnLength.getWidth()
                            && Y >= btnSelectPrimaryWpnPosition.getY() && Y <= btnSelectPrimaryWpnPosition.getY() + btnSelectPrimaryWpnLength.getHeight()) {
                        releasedEffectState.playSound();

                        if(!primarySelecting && !secondarySelecting){
                            btnSelectPrimaryWpnState = btnSelectPrimaryWpnDisabled;
                            primarySelecting = true;
                        }
                        else {
                            btnSelectPrimaryWpnState = btnSelectPrimaryWpn;
                            primarySelecting = false;
                        }
                    }
                    else if(X >= btnSelectSecondaryWpnPosition.getX() && X <= btnSelectSecondaryWpnPosition.getX() + btnSelectSecondaryWpnLength.getWidth()
                            && Y >= btnSelectSecondaryWpnPosition.getY() && Y <= btnSelectSecondaryWpnPosition.getY() + btnSelectSecondaryWpnLength.getHeight()
                            && secondaryWeapon != ' ') {
                        releasedEffectState.playSound();

                        if(!secondarySelecting && !primarySelecting){
                            btnSelectSecondaryWpnState = btnSelectSecondaryWpnDisabled;
                            secondarySelecting = true;
                        }
                        else {
                            btnSelectSecondaryWpnState = btnSelectSecondaryWpn;
                            secondarySelecting = false;
                        }
                    }
                    else if(X >= swordIconPosition.getX() && X <= swordIconPosition.getX() + swordIconLength.getWidth()
                            && Y >= swordIconPosition.getY() && Y <= swordIconPosition.getY() + swordIconLength.getHeight()){
                        if(primarySelecting && secondaryWeapon != 'a'){
                            releasedEffectState.playSound();
                            primaryWeapon = 'a';
                            primaryWeaponEffect = swordEffect;
                            setOwnWeapon(getResources());
                            showPrimaryWeaponIcon(swordIconPosition.getX(), swordIconPosition.getY());
                        }
                        else if(secondarySelecting && primaryWeapon != 'a'){
                            releasedEffectState.playSound();
                            secondaryWeapon = 'a';
                            secondaryWeaponEffect = swordEffect;
                            showSecondaryWeaponIcon(swordIconPosition.getX(), swordIconPosition.getY());
                        }
                    }
                    else if(X >= gunIconPosition.getX() && X <= gunIconPosition.getX() + gunIconLength.getWidth()
                            && Y >= gunIconPosition.getY() && Y <= gunIconPosition.getY() + gunIconLength.getHeight()
                            && weaponAvailables[1]){
                        if(primarySelecting && secondaryWeapon != 'b'){
                            releasedEffectState.playSound();
                            primaryWeapon = 'b';
                            primaryWeaponEffect = gunEffect;
                            setOwnWeapon(getResources());
                            showPrimaryWeaponIcon(gunIconPosition.getX(), gunIconPosition.getY());
                        }
                        else if(secondarySelecting && primaryWeapon != 'b'){
                            releasedEffectState.playSound();
                            secondaryWeapon = 'b';
                            secondaryWeaponEffect = gunEffect;
                            showSecondaryWeaponIcon(gunIconPosition.getX(), gunIconPosition.getY());
                        }
                    }
                    else if(X >= shotgunIconPosition.getX() && X <= shotgunIconPosition.getX() + shotgunIconLength.getWidth()
                            && Y >= shotgunIconPosition.getY() && Y <= shotgunIconPosition.getY() + shotgunIconLength.getHeight()
                            && weaponAvailables[2]){
                        if(primarySelecting && secondaryWeapon != 'c'){
                            releasedEffectState.playSound();
                            primaryWeapon = 'c';
                            primaryWeaponEffect = shotgunEffect;
                            setOwnWeapon(getResources());
                            showPrimaryWeaponIcon(shotgunIconPosition.getX(), shotgunIconPosition.getY());
                        }
                        else if(secondarySelecting && primaryWeapon != 'c'){
                            releasedEffectState.playSound();
                            secondaryWeapon = 'c';
                            secondaryWeaponEffect = shotgunEffect;
                            showSecondaryWeaponIcon(shotgunIconPosition.getX(), shotgunIconPosition.getY());
                        }
                    }
                    else if(X >= grenadeIconPosition.getX() && X <= grenadeIconPosition.getX() + grenadeIconLength.getWidth()
                            && Y >= grenadeIconPosition.getY() && Y <= grenadeIconPosition.getY() + grenadeIconLength.getHeight()
                            && weaponAvailables[3]){
                        if(primarySelecting && secondaryWeapon != 'd'){
                            releasedEffectState.playSound();
                            primaryWeapon = 'd';
                            primaryWeaponEffect = grenadeEffect;
                            setOwnWeapon(getResources());
                            showPrimaryWeaponIcon(grenadeIconPosition.getX(), grenadeIconPosition.getY());
                        }
                        else if(secondarySelecting && primaryWeapon != 'd'){
                            releasedEffectState.playSound();
                            secondaryWeapon = 'd';
                            secondaryWeaponEffect = grenadeEffect;
                            showSecondaryWeaponIcon(grenadeIconPosition.getX(), grenadeIconPosition.getY());
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                if(screenNumber == 2 && gameFlag == 1) {
                    X = (int) event.getX(pointer_count-1);
                    Y = (int) event.getY(pointer_count-1);

                    attackCharacter(X, Y);
                    releasedEffectState.playSound();
                }
                break;
            case MotionEvent.ACTION_UP:
                if(screenNumber == 1) {
                    if(popupShowed) {
                        if (X >= popupBtnConfirmPosition.getX() && X <= popupBtnConfirmPosition.getX() + popupBtnConfirmLength.getWidth()
                                && Y >= popupBtnConfirmPosition.getY() && Y <= popupBtnConfirmPosition.getY() + popupBtnConfirmLength.getHeight()) {  // 확인 버튼을 눌렀으면
                            if(popupType == 'a'){
                                releasedEffectState.playSound();
                                System.exit(0);
                            }
                            else if(popupType == 'b'){
                                selectCancelButton();
                            }

                        } else if (X >= popupBtnCancelPosition.getX() && X <= popupBtnCancelPosition.getX() + popupBtnCancelLength.getWidth()
                                && Y >= popupBtnCancelPosition.getY() && Y <= popupBtnCancelPosition.getY() + popupBtnCancelLength.getHeight()) {  // 취소 버튼을 눌렀으면
                            selectCancelButton();
                        }
                    }
                    else {
                        if (X >= btnExitPosition.getX() && X <= btnExitPosition.getX() + btnExitLength.getWidth()
                                && Y >= btnExitPosition.getY() && Y <= btnExitPosition.getY() + btnExitLength.getHeight()) {  // Exit 버튼이 눌렸으면
                            releasedEffectState.playSound();
                            backPressed();
                        } else if (X >= btnInfoPosition.getX() && X <= btnInfoPosition.getX() + btnInfoLength.getWidth()
                                && Y >= btnInfoPosition.getY() && Y <= btnInfoPosition.getY() + btnInfoLength.getHeight()) {  // Info 버튼이 눌렸으면
                            releasedEffectState.playSound();
                            popupType = 'b';
                            popupShowed = true;
                        } else if (X >= btnStartPosition.getX() && X <= btnStartPosition.getX() + btnStartLength.getWidth()
                                && Y >= btnStartPosition.getY() && Y <= btnStartPosition.getY() + btnStartLength.getHeight()) {  // Start 버튼이 눌렸으면
                            releasedEffectState.playSound();

                            btnStart.recycle();
                            btnInfo.recycle();
                            btnExit.recycle();

                            btnStart = null;
                            btnInfo = null;
                            btnExit = null;

                            setStageButtons();
                            setImgCharacters();

                            btnArmoryShop = BitmapFactory.decodeResource(getResources(), R.drawable.btnarmoryshop);
                            btnBack = BitmapFactory.decodeResource(getResources(), R.drawable.btnback);
                            txtSelectStage = BitmapFactory.decodeResource(getResources(), R.drawable.txtselectstage);


                            this.setBackgroundResource(R.drawable.stagebackground);
                            screenNumber = 4;
                        }
                    }
                }
                else if(screenNumber == 2){
                    if(popupShowed) {
                        if (X >= popupBtnConfirmPosition.getX() && X <= popupBtnConfirmPosition.getX() + popupBtnConfirmLength.getWidth()
                                && Y >= popupBtnConfirmPosition.getY() && Y <= popupBtnConfirmPosition.getY() + popupBtnConfirmLength.getHeight()) {  // 확인 버튼을 눌렀으면
                            if(popupType == 'a'){
                                gameStart(selectedLevel, selectedStage);
                                popupShowed = false;
                            }
                            else if(popupType == 'c'){
                                selectCancelButton();
                            }
                        }
                        else if (X >= popupBtnCancelPosition.getX() && X <= popupBtnCancelPosition.getX() + popupBtnCancelLength.getWidth()
                                && Y >= popupBtnCancelPosition.getY() && Y <= popupBtnCancelPosition.getY() + popupBtnCancelLength.getHeight()
                                && popupType == 'a') {  // 취소 버튼을 눌렀으면
                            releasedEffectState.playSound();

                            popupShowed = false;
                            backPressed();
                        }
                    }
                    else if(gameFlag == 2) {
                        if (X >= btnBackPosition.getX() && X <= btnBackPosition.getX() + btnBackLength.getWidth()
                                && Y >= btnBackPosition.getY() && Y <= btnBackPosition.getY() + btnBackLength.getHeight()) {  // back 버튼이 눌렸으면
                            releasedEffectState.playSound();
                            backPressed();
                        } else if (X >= btnNextStagePosition.getX() && X <= btnNextStagePosition.getX() + btnNextStageLength.getWidth()
                                && Y >= btnNextStagePosition.getY() && Y <= btnNextStagePosition.getY() + btnNextStageLength.getHeight()) {  // next stage 버튼이 눌렸으면
                            releasedEffectState.playSound();
                            popupType = 'c';
                            popupShowed = true;
                        }
                    }
                    else if(gameFlag == 1) {
                        removeTouchEffect();
                    }
                }
                else if(screenNumber == 4){
                    if(popupShowed) {
                        if (X >= popupBtnConfirmPosition.getX() && X <= popupBtnConfirmPosition.getX() + popupBtnConfirmLength.getWidth()
                                && Y >= popupBtnConfirmPosition.getY() && Y <= popupBtnConfirmPosition.getY() + popupBtnConfirmLength.getHeight()) {  // 확인 버튼을 눌렀으면
                            if(popupType == 'a'){
                                releasedEffectState.playSound();

                                removeStageButtons();

                                btnArmoryShop.recycle();
                                txtSelectStage.recycle();

                                btnArmoryShop = null;
                                txtSelectStage = null;

                                swordIcon = BitmapFactory.decodeResource(getResources(), R.drawable.swordicon);
                                gunIcon = BitmapFactory.decodeResource(getResources(), R.drawable.gunicon);
                                shotgunIcon = BitmapFactory.decodeResource(getResources(), R.drawable.shotgunicon);
                                grenadeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.grenadeicon);

                                primaryWeaponIcon = BitmapFactory.decodeResource(getResources(), R.drawable.primaryweapon);
                                secondaryWeaponIcon = BitmapFactory.decodeResource(getResources(), R.drawable.secondaryweapon);

                                notAvailable = BitmapFactory.decodeResource(getResources(), R.drawable.notavailable);
                                btnSelectPrimaryWpn = BitmapFactory.decodeResource(getResources(), R.drawable.btnselectprimary);
                                btnSelectSecondaryWpn = BitmapFactory.decodeResource(getResources(), R.drawable.btnselectsecondary);
                                btnSelectPrimaryWpnDisabled = BitmapFactory.decodeResource(getResources(), R.drawable.btnselectprimarydisabled);
                                btnSelectSecondaryWpnDisabled = BitmapFactory.decodeResource(getResources(), R.drawable.btnselectsecondarydisabled);

                                btnSelectPrimaryWpnState = btnSelectPrimaryWpn;

                                if(secondaryWeapon == ' ')
                                    btnSelectSecondaryWpnState = btnSelectSecondaryWpnDisabled;
                                else
                                    btnSelectSecondaryWpnState = btnSelectSecondaryWpn;

                                btnGameStart = BitmapFactory.decodeResource(getResources(), R.drawable.btngamestart);

                                screenNumber = 7;
                                popupShowed = false;
                            }
                            else if(popupType == 'b'){
                                selectCancelButton();
                            }
                        }
                        else if (X >= popupBtnCancelPosition.getX() && X <= popupBtnCancelPosition.getX() + popupBtnCancelLength.getWidth()
                                && Y >= popupBtnCancelPosition.getY() && Y <= popupBtnCancelPosition.getY() + popupBtnCancelLength.getHeight()
                                && popupType == 'a') {  // 취소 버튼을 눌렀으면
                            selectCancelButton();
                        }
                    }
                    else {
                        if (X >= btnBackPosition.getX() && X <= btnBackPosition.getX() + btnBackLength.getWidth()
                                && Y >= btnBackPosition.getY() && Y <= btnBackPosition.getY() + btnBackLength.getHeight()) {  // back 버튼이 눌렸으면
                            releasedEffectState.playSound();
                            backPressed();
                        }
                        else if(X >= btnArmoryShopPosition.getX() && X <= btnArmoryShopPosition.getX() + btnArmoryShopLength.getWidth()
                                && Y >= btnArmoryShopPosition.getY() && Y <= btnArmoryShopPosition.getY() + btnArmoryShopLength.getHeight()) {  // 상점 버튼이 눌렸으면
                            releasedEffectState.playSound();
                            popupType = 'b';
                            popupShowed = true;
                        }
                        else {
                            checkButtonsWithBinarySearch(X, Y);
                        }
                    }
                }
                else if(screenNumber == 7){
                    if(popupShowed) {
                        if (X >= popupBtnConfirmPosition.getX() && X <= popupBtnConfirmPosition.getX() + popupBtnConfirmLength.getWidth()
                                && Y >= popupBtnConfirmPosition.getY() && Y <= popupBtnConfirmPosition.getY() + popupBtnConfirmLength.getHeight()) {  // 확인 버튼을 눌렀으면
                            selectCancelButton();
                        }
                    }
                    else {
                        if (X >= btnBackPosition.getX() && X <= btnBackPosition.getX() + btnBackLength.getWidth()
                                && Y >= btnBackPosition.getY() && Y <= btnBackPosition.getY() + btnBackLength.getHeight()) {  // back 버튼이 눌렸으면
                            releasedEffectState.playSound();
                            backPressed();
                        } else if (X >= btnGameStartPosition.getX() && X <= btnGameStartPosition.getX() + btnGameStartLength.getWidth()
                                && Y >= btnGameStartPosition.getY() && Y <= btnGameStartPosition.getY() + btnGameStartLength.getHeight()) {  // game start 버튼이 눌렸으면
                            swordIcon.recycle();
                            gunIcon.recycle();
                            shotgunIcon.recycle();
                            grenadeIcon.recycle();

                            primaryWeaponIcon.recycle();
                            secondaryWeaponIcon.recycle();

                            notAvailable.recycle();
                            btnSelectPrimaryWpn.recycle();
                            btnSelectSecondaryWpn.recycle();
                            btnSelectPrimaryWpnDisabled.recycle();
                            btnSelectSecondaryWpnDisabled.recycle();
                            btnGameStart.recycle();

                            swordIcon = null;
                            gunIcon = null;
                            shotgunIcon = null;
                            grenadeIcon = null;

                            primaryWeaponIcon = null;
                            secondaryWeaponIcon = null;

                            notAvailable = null;
                            btnSelectPrimaryWpn = null;
                            btnSelectSecondaryWpn = null;
                            btnSelectPrimaryWpnDisabled = null;
                            btnSelectSecondaryWpnDisabled = null;
                            btnGameStart = null;

                            hit = BitmapFactory.decodeResource(getResources(), R.drawable.smallhit);
                            hitDamaged = BitmapFactory.decodeResource(getResources(), R.drawable.smallhitdamaged);
                            heartIcon = BitmapFactory.decodeResource(getResources(), R.drawable.hearticon);
                            btnChangeWeapon = BitmapFactory.decodeResource(getResources(), R.drawable.btnchangeweapon);
                            btnNextStage = BitmapFactory.decodeResource(getResources(), R.drawable.btnnextstage);
                            txtStageClear = BitmapFactory.decodeResource(getResources(), R.drawable.txtstageclear);
                            txtElapsedTime = BitmapFactory.decodeResource(getResources(), R.drawable.txtelapsedtime);
                            txtCurrentStamina = BitmapFactory.decodeResource(getResources(), R.drawable.txtcurrnetstamina);
                            txtTotalScore = BitmapFactory.decodeResource(getResources(), R.drawable.txttotalscore);

                            if(secondaryWeapon == ' ')
                                btnChangeWeapon = BitmapFactory.decodeResource(getResources(), R.drawable.btnchangeweapondisabled);
                            else
                                btnChangeWeapon = BitmapFactory.decodeResource(getResources(), R.drawable.btnchangeweapon);

                            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                            gameStart(selectedLevel, selectedStage);
                        }
                    }
                }
                else if(screenNumber == 8){
                    if(X >= btnBackPosition.getX() && X <= btnBackPosition.getX() + btnBackLength.getWidth()
                            && Y >= btnBackPosition.getY() && Y <= btnBackPosition.getY() + btnBackLength.getHeight()) {  // back 버튼이 눌렸으면
                        releasedEffectState.playSound();
                        backPressed();
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                if(screenNumber == 2 && gameFlag == 1){
                    removeTouchEffect();
                }
                break;
            case MotionEvent.ACTION_OUTSIDE:
                break;
        }

        return true;
    }

    public void checkButtonsWithBinarySearch(int X, int Y){
        int pressedStage = -1;
        int pressedLevel = -1;

        int start = 0;
        int end = 4;

        while(start <= end){
            int mid = (start+end)/2;

            if (X >= btnStagePositions[0][mid].getX() && X <= btnStagePositions[0][mid].getX() + btnStageLength.getWidth()) {
                pressedStage = mid;
                break;
            }
            else if(X < btnStagePositions[0][mid].getX()){
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }

        if(pressedStage == -1)
            return;

        start = 0;
        end = 4;

        while(start <= end){
            int mid = (start+end)/2;

            if (Y >= btnStagePositions[mid][0].getY() && Y <= btnStagePositions[mid][0].getY() + btnStageLength.getHeight()) {
                pressedLevel = mid;
                break;
            }
            else if(Y < btnStagePositions[mid][0].getY()){
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }

        if (pressedLevel != -1) {
            if(stageAvailable[pressedLevel][pressedStage]) {
                releasedEffectState.playSound();

                selectedStage = pressedStage + 1;
                selectedLevel = pressedLevel + 1;
                imgLevelStage.setImageString("level " + selectedLevel + " stage " + selectedStage);

                popupType = 'a';
                popupShowed = true;
            }
        }
    }

    public void backToTheStage(){
        setStageButtons();
        btnArmoryShop = BitmapFactory.decodeResource(getResources(), R.drawable.btnarmoryshop);
        txtSelectStage = BitmapFactory.decodeResource(getResources(), R.drawable.txtselectstage);
        btnBack = BitmapFactory.decodeResource(getResources(), R.drawable.btnback);

        this.setBackgroundResource(R.drawable.stagebackground);
        screenNumber = 4;
    }

    public void backPressed(){
        if(!popupShowed) {
            if (screenNumber == 1) {
                popupType = 'a';
                popupShowed = true;
            } else if (screenNumber == 2) {
                gameFlag = 0;

                bgMusicState.gameEnd();
                releasedEffectState.gameEnd();

                btnNextStage.recycle();
                hit.recycle();
                hitDamaged.recycle();
                heartIcon.recycle();
                btnChangeWeapon.recycle();
                txtStageClear.recycle();
                txtElapsedTime.recycle();
                txtCurrentStamina.recycle();
                txtTotalScore.recycle();

                btnNextStage = null;
                hit = null;
                hitDamaged = null;
                heartIcon = null;
                btnChangeWeapon = null;
                txtStageClear = null;
                txtElapsedTime = null;
                txtCurrentStamina = null;
                txtTotalScore = null;

                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                backToTheStage();
            } else if (screenNumber == 4) {
                btnArmoryShop.recycle();
                btnBack.recycle();

                btnArmoryShop = null;
                btnBack = null;

                removeStageButtons();
                removeImgCharacters();

                btnStart = BitmapFactory.decodeResource(getResources(), R.drawable.btnstart);
                btnInfo = BitmapFactory.decodeResource(getResources(), R.drawable.btninfo);
                btnExit = BitmapFactory.decodeResource(getResources(), R.drawable.btnexit);

                this.setBackgroundResource(R.drawable.introbackground);
                screenNumber = 1;
            } else if (screenNumber == 7) {
                swordIcon.recycle();
                gunIcon.recycle();
                shotgunIcon.recycle();
                grenadeIcon.recycle();

                primaryWeaponIcon.recycle();
                secondaryWeaponIcon.recycle();

                notAvailable.recycle();
                btnSelectPrimaryWpn.recycle();
                btnSelectSecondaryWpn.recycle();
                btnGameStart.recycle();

                swordIcon = null;
                gunIcon = null;
                shotgunIcon = null;
                grenadeIcon = null;

                primaryWeaponIcon = null;
                secondaryWeaponIcon = null;

                notAvailable = null;
                btnSelectPrimaryWpn = null;
                btnSelectSecondaryWpn = null;
                btnGameStart = null;

                backToTheStage();

                screenNumber = 4;
            }
        }
    }

    public double convertToMyHealth(int n){
        return n / MAX_HEALTH * MAX_HEALTH_LENGTH;
    }

    public String convertToHMS(long t){  // 1초 - 1000ms
        long sec = t / 1000;

        int hour = (int)(sec / 3600);
        sec = (sec % 3600);

        int minute = (int)(sec / 60);
        sec = (sec % 60);

        int second = (int)(sec);

        String h = String.valueOf(hour);
        String m = String.valueOf(minute);
        String s = String.valueOf(second);

        if(hour <= 9)
            h = "0" + h;
        if(minute <= 9)
            m = "0" + m;
        if(second <= 9)
            s = "0" + s;

        return h + ":" + m + ":" + s;
    }

    public String calculateScore(long time){
        int score1;
        int score2;

        if(time < 180000){
            score1 = (int)(180000 - time);
            score1 /= 10;
        }
        else{
            score1 = 0;
        }

        score2 = myHealth * 100;

        String result = String.valueOf(score1 + score2);

        return result;
    }

    public void setWIdthHeight(){
        WIDTH = getWidth();
        HEIGHT = getHeight();

        int yPos = getCenterPosition(HEIGHT/2, btnStartLength.getHeight()) + (btnStartLength.getHeight()/2) + 50;
        int gap = 10;

        btnStartPosition = new Position(getCenterPosition(WIDTH/2, btnStartLength.getWidth()), yPos);
        btnInfoPosition = new Position(getCenterPosition(WIDTH/2, btnInfoLength.getWidth()), yPos + btnStartLength.getHeight() + gap);
        btnExitPosition = new Position(getCenterPosition(WIDTH/2, btnExitLength.getWidth()), yPos + (btnStartLength.getHeight()*2) + (gap*2));

        btnBackPosition = new Position(10, HEIGHT - btnBackLength.getHeight() - 10);
        btnArmoryShopPosition = new Position(WIDTH - btnArmoryShopLength.getWidth() - 10, HEIGHT - btnArmoryShopLength.getHeight() - 10);
        txtSelectStagePosition = new Position(0, 10);

        swordIconPosition = new Position(WIDTH/2 - swordIconLength.getWidth() - 10, HEIGHT/2 - swordIconLength.getHeight() - 20);
        gunIconPosition = new Position(WIDTH/2 + 10, HEIGHT/2 - swordIconLength.getHeight() - 20);
        shotgunIconPosition = new Position(WIDTH/2 - swordIconLength.getWidth() - 10, HEIGHT/2 + 20);
        grenadeIconPosition = new Position(WIDTH/2 + 10, HEIGHT/2 + 20);

        txtElapsedTimePosition = new Position(50, getCenterPosition(HEIGHT/2, txtCurrentStaminaLength.getHeight()) - 100 - txtElapsedTimeLength.getHeight());
        txtCurrentStaminaPosition = new Position(50, getCenterPosition(HEIGHT/2, txtCurrentStaminaLength.getHeight()));
        txtStageClearPosition = new Position( getCenterPosition(WIDTH/2, txtStageClearLength.getWidth()),
                getCenterPosition(HEIGHT/2, txtCurrentStaminaLength.getHeight()) - 100 - txtElapsedTimeLength.getHeight() - 200 - txtStageClearLength.getHeight() );
        txtTotalScorePosition = new Position(50, getCenterPosition(HEIGHT/2, txtCurrentStaminaLength.getHeight()) + txtCurrentStaminaLength.getHeight() + 200);

        btnSelectPrimaryWpnPosition = new Position(WIDTH - btnSelectPrimaryWpnLength.getWidth() - 10, 10);
        btnSelectSecondaryWpnPosition = new Position(WIDTH - btnSelectSecondaryWpnLength.getWidth() - 10, 10 + btnSelectPrimaryWpnLength.getHeight() + 20);
        btnChangeWeaponPosition = new Position(50, HEIGHT - 50*2 - 20 - btnChangeWeaponLength.getHeight());

        primaryWeaponIconPosition = new Position();
        secondaryWeaponIconPosition = new Position();

        if(primaryWeapon == 'a')
            showPrimaryWeaponIcon(swordIconPosition.getX(), swordIconPosition.getY());
        else if(primaryWeapon == 'b')
            showPrimaryWeaponIcon(gunIconPosition.getX(), gunIconPosition.getY());
        else if(primaryWeapon == 'c')
            showPrimaryWeaponIcon(shotgunIconPosition.getX(), shotgunIconPosition.getY());
        else if(primaryWeapon == 'd')
            showPrimaryWeaponIcon(grenadeIconPosition.getX(), grenadeIconPosition.getY());

        if(secondaryWeapon == 'a')
            showSecondaryWeaponIcon(swordIconPosition.getX(), swordIconPosition.getY());
        else if(secondaryWeapon == 'b')
            showSecondaryWeaponIcon(gunIconPosition.getX(), gunIconPosition.getY());
        else if(secondaryWeapon == 'c')
            showSecondaryWeaponIcon(shotgunIconPosition.getX(), shotgunIconPosition.getY());
        else if(secondaryWeapon == 'd')
            showSecondaryWeaponIcon(grenadeIconPosition.getX(), grenadeIconPosition.getY());

        btnGameStartPosition = new Position(WIDTH - btnGameStartLength.getWidth() - 10, HEIGHT - btnGameStartLength.getHeight() - 10);
        btnNextStagePosition = new Position(WIDTH - btnNextStageLength.getWidth() - 10, HEIGHT - btnNextStageLength.getHeight() - 10);
        heartIconPosition = new Position(50, HEIGHT-100);

        popupExitPosition = new Position( getCenterPosition(WIDTH/2, popupExit.getWidth()), getCenterPosition(HEIGHT/2, popupExit.getHeight()) );
        popupStaffInfoPosition = new Position( getCenterPosition(WIDTH/2, popupStaffInfo.getWidth()), getCenterPosition(HEIGHT/2, popupStaffInfo.getHeight()) );

        popupBtnConfirmPosition = new Position(0, popupExitPosition.getY() + popupExit.getHeight() - popupBtnConfirmLength.getHeight() - 50);
        popupBtnCancelPosition = new Position(getCenterPosition(WIDTH/2 + 150, popupBtnCancelLength.getWidth()), popupExitPosition.getY() + popupExit.getHeight() - popupBtnCancelLength.getHeight() - 50);

        Bitmap originalPopupBackColor = BitmapFactory.decodeResource(getResources(), R.drawable.popupbackcolor);
        Bitmap originalDamagedBackColor = BitmapFactory.decodeResource(getResources(), R.drawable.damagebackcolor);

        popupBackColor = Bitmap.createScaledBitmap(originalPopupBackColor, WIDTH, HEIGHT, true);
        damagedBackColor = Bitmap.createScaledBitmap(originalDamagedBackColor, WIDTH, HEIGHT, true);

        originalPopupBackColor.recycle();
        originalDamagedBackColor.recycle();

        originalPopupBackColor = null;
        originalDamagedBackColor = null;

        Bitmap endLine = BitmapFactory.decodeResource(getResources(), R.drawable.endline);
        resizedEndLine = Bitmap.createScaledBitmap(endLine, WIDTH, endLine.getHeight(), true);
        resizedEndLineLength = new Length(WIDTH, endLine.getHeight());

        endLine.recycle();
        endLine = null;

        int x = 10;
        int y = getCenterPosition(HEIGHT/4, btnStageLength.getHeight());

        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                btnStagePositions[i][j] = new Position(x, y);
                x += (btnStageLength.getWidth() + 10);
            }

            x = 10;
            y += (btnStageLength.getHeight() + 10);
        }

        MAX_HEALTH_LENGTH = WIDTH - 50 - 20;

        setLength = true;
    }

    public void stopBgMusic(){
        bgMusicState.stopSound();
    }

    public void playBgMusic(){
        bgMusicState.playSound();
    }

    public void setBgMusicState(BackgroundMusic bgm){
        this.bgMusicState = bgm;
    }

    public void setReleasedEffectState(SoundEffect se){
        this.releasedEffectState = se;
    }

    public BackgroundMusic getIntroMusic(){
        return this.introMusic;
    }

    public BackgroundMusic getGameMusic(){
        return this.gameMusic;
    }

    public SoundEffect getBtnReleasedEffect() {
        return this.btnReleasedEffect;
    }

    public SoundEffect getPrimaryWeaponEffect() {
        return this.primaryWeaponEffect;
    }

    public SoundEffect getSecondaryWeaponEffect() {
        return this.secondaryWeaponEffect;
    }
}