package com.flight.game.objects.enemy;

import com.flight.game.frame.MainFrame;
import com.flight.game.objects.jet.EnemyJet;
import com.flight.game.objects.user.UserJet;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

import static com.flight.game.set.ImagesSet.ENEMY_BOSS;
import static com.flight.game.set.ScreenSize.*;
import static com.flight.game.set.Util.mainGameStart;

public class Boss extends EnemyJet {

    private MainFrame mf;
    private Boss boss = this;
    private EnemyMissile enemyMissile;
    private Vector<EnemyMissile> enemyMissileList;
    private double way;
    private boolean changeFire = false;
    private boolean crushCheck2 = false;

    // ================================= 멤버변수 =================================
    public Boss(MainFrame mf, UserJet userJet, int x, int y) {

        this.mf         = mf;
        this.jetImgIcon = new ImageIcon( ENEMY_BOSS );
        this.userJet    = userJet;
        this.xPos       = (SCREEN_WIDTH / 2) - (jetImgIcon.getImage().getWidth(null) / 2) + 50;
        this.yPos       = y;
        this.w          = jetImgIcon.getImage().getWidth(null);
        this.h          = jetImgIcon.getImage().getHeight(null);
        this.life       = 100;
        this.crushCheck = false;
        this.isLife     = true;
        enemyMissileList = new Vector<>();

        this.move();
    }


    @Override
    public void move() {

        isMoveStart = true;
        missileSpeed = 0;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while ( isMoveStart ) {

                    try {

                        shootMissile();
                        shootFire();
                        if ( missileSpeed % 10000 == 0 ) missileSpeed = 0;
                        ++missileSpeed;

                        if ( crushCheck2 ) {
                            System.out.println("Boss 처치");
                            System.out.println("[World War 3]");
                            System.out.println("[1] 전투\n[2] 종료");
                            isMoveStart = false;

                            mainGameStart = false;
                            mf.dispose();
                        }

                        Thread.sleep( 5 );

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }

            }
        });

        thread.setName( "Boss" );
        thread.start();

    }

    @Override
    public boolean getIsCrushCheck() {
        return super.crushCheck;
    }

    @Override
    public void setIsCrushCheck(boolean crushCheck) {
        this.crushCheck2 = crushCheck;
    }

    @Override
    public void crush() {

    }

    public void shootMissile() {

        if ( missileSpeed % 200 == 0 ) {

            enemyMissile = new EnemyMissile( mf, boss, userJet, xPos + 220, yPos + 300, 300,2, BOSS_MISSILE_W, BOSS_MISSILE_H );
            enemyMissileList.add( enemyMissile );

            enemyMissile = new EnemyMissile( mf, boss, userJet, xPos + 220, yPos + 300, 270,2, BOSS_MISSILE_W, BOSS_MISSILE_H );
            enemyMissileList.add( enemyMissile );

            enemyMissile = new EnemyMissile( mf, boss, userJet, xPos + 220, yPos + 300, 700,2, BOSS_MISSILE_W, BOSS_MISSILE_H );
            enemyMissileList.add( enemyMissile );

            enemyMissile = new EnemyMissile( mf, boss, userJet, xPos + 220, yPos + 300, 1300,2, BOSS_MISSILE_W, BOSS_MISSILE_H );
            enemyMissileList.add( enemyMissile );

            enemyMissile = new EnemyMissile( mf, boss, userJet, xPos + 220, yPos + 300, 900,2, BOSS_MISSILE_W, BOSS_MISSILE_H );
            enemyMissileList.add( enemyMissile );

            if ( changeFire == false ) {
                changeFire = true;
                for (int j = 1; j <= 5; j++) {
                    way = 180 + (30 * j);
                    enemyMissile = new EnemyMissile( mf, boss, userJet, xPos + 80, yPos + 400, way, 0.5, BOSS_MISSILE_W, BOSS_MISSILE_H);
                    enemyMissileList.add(enemyMissile);
                }

                for (int j = 1; j <= 5; j++) {
                    way = 180 + (30 * j);
                    enemyMissile = new EnemyMissile( mf, boss, userJet, xPos + 400, yPos + 400, way, 0.5, BOSS_MISSILE_W, BOSS_MISSILE_H); // 총알이 생성되는 위치
                    enemyMissileList.add(enemyMissile);
                }
            } else {
                changeFire = false;
                for (int j = 1; j <= 5; j++) {
                    way = 180 + (25 * j);
                    enemyMissile = new EnemyMissile( mf, boss, userJet, xPos + 80, yPos + 400, way, 0.5, BOSS_MISSILE_W, BOSS_MISSILE_H);
                    enemyMissileList.add(enemyMissile);
                }

                for (int j = 1; j <= 5; j++) {
                    way = 180 + (25 * j);
                    enemyMissile = new EnemyMissile( mf, boss, userJet, xPos + 400, yPos + 400, way, 0.5, BOSS_MISSILE_W, BOSS_MISSILE_H); // 총알이 생성되는 위치
                    enemyMissileList.add(enemyMissile);
                }
            }

        }

        if (missileSpeed % 600 == 0) {
            for (int j = 1; j <= 7; j++) {
                way = 180 + (25 * j);
                enemyMissile = new EnemyMissile( mf, boss, userJet, xPos + 280, yPos + 400, way, 1, BOSS_MISSILE_W, BOSS_MISSILE_H); // 총알이 생성되는																											// 위치
                enemyMissileList.add(enemyMissile);
            }
        }

    }

    public void shootFire() {
        for (EnemyMissile e : enemyMissileList) e.missileFire();
    }

    @Override
    public void obejectUpdate(Graphics g) {
        try {
            g.drawImage(jetImgIcon.getImage(), xPos, yPos, 500, 500, null);

            for ( EnemyMissile e : enemyMissileList )
                g.drawImage(e.getMissile1().getImage(), (int) e.getXPos(), (int) e.getYPos(), e.getW() / 2, e.getH() / 2, null);

        } catch ( Exception e ) {

        }
    }
}
