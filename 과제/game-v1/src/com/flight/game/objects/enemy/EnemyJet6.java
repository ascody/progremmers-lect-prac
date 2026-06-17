package com.flight.game.objects.enemy;

import com.flight.game.frame.MainFrame;
import com.flight.game.objects.jet.EnemyJet;
import com.flight.game.objects.user.UserJet;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

import static com.flight.game.set.ImagesSet.ENEMY_JET_6;
import static com.flight.game.set.ImagesSet.EXPLOSION;
import static com.flight.game.set.PanelName.INIT_TITLE;
import static com.flight.game.set.ScreenSize.JET_H;
import static com.flight.game.set.ScreenSize.JET_W;

public class EnemyJet6 extends EnemyJet {

    // ================================= 멤버변수 =================================
    private MainFrame mf;
    private EnemyJet6 enemyJet6 = this;
    private EnemyMissile enemyMissile;
    private Vector<EnemyMissile> enemyMissileList;
    private boolean crushCheck2 = false;

    // ================================= 생성자 =================================
    public EnemyJet6( MainFrame mf, UserJet userJet, int x, int y, int w, int h ) {

        System.out.println( "[LOG] :::::::: Enemy Jet6 실행." );
        this.mf         = mf;
        this.userJet    = userJet;
        this.xPos       = x;
        this.yPos       = y;
        this.w          = w;
        this.h          = h;
        this.jetImgIcon = new ImageIcon( ENEMY_JET_6 );
        this.life       = 5;
        this.crushCheck = false;
        this.isLife     = true;
        enemyMissileList = new Vector<>();

        this.move();
//        this.crush();

    }
    // ================================= getter / setter 메서드 =================================

    // ================================= 메서드 =================================
    @Override
    public void move() {

        this.isMoveStart = true;
        missileSpeed = 0;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while ( isMoveStart ) {

                    try {

                        if ( yPos > 50 ) {
                            moveUp();
                            moveLeft();
                        }

                        if ( yPos > 900 || mf.getPlayGround().getAppearCnt() == 10000 ) {

                            System.out.println( "[LOG] :::::::: Enemy Jet6 종료." );
                            isMoveStart = false;

                        }

                        shootMissile();
//                        shootFire();
                        if ( missileSpeed % 10000 == 0 ) missileSpeed = 0;
                        ++missileSpeed;

                        Thread.sleep( 5 );

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }

            }
        });

        thread.setName( "Enemy Jet 6" );
        thread.start();

    }

    public void shootMissile() {

        if ( missileSpeed % 200 == 0 ) {

            enemyMissile = new EnemyMissile( mf, enemyJet6, userJet, xPos + 10, yPos + 40, 300, 2, 20, 20 );
            enemyMissileList.add( enemyMissile );

            enemyMissile = new EnemyMissile( mf, enemyJet6, userJet, xPos + 10, yPos + 40, 270, 2, 20, 20 );
            enemyMissileList.add( enemyMissile );

            enemyMissile = new EnemyMissile( mf, enemyJet6, userJet, xPos + 10, yPos + 40, 1300, 2, 20, 20 );
            enemyMissileList.add( enemyMissile );

        }

    }

    public void shootFire() {
        for (EnemyMissile e : enemyMissileList) e.missileFire();
    }

    @Override
    public boolean getIsCrushCheck() {
        return crushCheck;
    }

    @Override
    public void setIsCrushCheck(boolean crushCheck) {
        this.crushCheck = crushCheck;
    }

    @Override
    public void crush() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while ( !collision && yPos < 900 && yPos > 0 ) {
                    try {

                        if (
                                Math.abs( (mf.getUserJet().getxPos() + JET_W / 2 ) - (xPos + JET_W / 2) ) < (w / 2 + (JET_W / 2))
                                        &&
                                Math.abs( (mf.getUserJet().getyPos() + JET_H / 2 ) - (yPos + JET_H / 2) ) < (h / 2 + (JET_H / 2))
                        ) {
                            collision = true;
                        } else {
                            collision = false;
                        }

                        if ( collision ) {
                            userJet.setLife(userJet.getLife() - 1);
                            mf.getPlayGround().getEnemyJetList().remove( enemyJet6 );
                            if ( mf.getUserJet().getLife() <= 0 ) {
                                Thread.sleep(1000);
                                mf.changePanel(INIT_TITLE);
                                Thread.sleep(500);
                                mf.getPlayGround().setAppearCnt(10000);
                                mf.setIsStart( false );
                            }
                            Thread.sleep(2000);
                        }

                        if ( crushCheck ) {
                            setIsCrushCheck( true );
                            enemyJet6.setJetImgIcon( new ImageIcon( EXPLOSION ));
                            enemyJet6.repaint();
                            Thread.sleep(1000);
                        }
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }).start();
    }

    @Override
    public void obejectUpdate(Graphics g) {

        try {

            g.drawImage( this.jetImgIcon.getImage(), xPos, yPos, w, h, null );
            if ( !enemyMissileList.isEmpty() )
                for (EnemyMissile e : enemyMissileList) g.drawImage( e.getMissile4().getImage(), (int) e.getXPos(), (int) e.getYPos(), e.getW(), e.getH(), null);
        } catch ( Exception e ) {

        }

    }
}
