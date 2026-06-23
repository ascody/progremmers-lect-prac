package com.flight.game.objects.enemy;

import com.flight.game.frame.MainFrame;
import com.flight.game.objects.jet.EnemyJet;
import com.flight.game.objects.user.UserJet;

import javax.swing.*;

import java.awt.*;
import java.util.Vector;

import static com.flight.game.set.ImagesSet.ENEMY_JET_1;
import static com.flight.game.set.ImagesSet.EXPLOSION;
import static com.flight.game.set.PanelName.*;
import static com.flight.game.set.ScreenSize.JET_H;
import static com.flight.game.set.ScreenSize.JET_W;

public class EnemyJet1 extends EnemyJet {

    // ================================= 멤버변수 =================================
    private MainFrame mf;
    private EnemyJet1 enemyJet1 = this;
    private Vector<EnemyJet> dummyEnemyJetList;

    // ================================= 생성자 =================================
    public EnemyJet1( MainFrame mf, UserJet userJet, int x, int y, int w, int h ) {

        System.out.println( "[LOG] :::::::: Enemy Jet1 실행." );
        this.mf         = mf;
        this.userJet    = userJet;
        this.xPos       = x;
        this.yPos       = y;
        this.w          = w;
        this.h          = h;
        this.jetImgIcon = new ImageIcon( ENEMY_JET_1 );
        this.life       = 1;
        this.crushCheck = false;
        this.isLife     = true;
        this.dummyEnemyJetList = new Vector<>();

        this.move();
//        this.crush();

    }

    // ================================= getter/setter 메서드 =================================

    public boolean getIsCrushCheck() {
        return super.crushCheck;
    }

    /*
    public void setIsCrushCheck(boolean crushCheck) {
        this.crushCheck = crushCheck;
    }
     */
    // ================================= 메서드 =================================
    @Override
    public void move() {

        setIsMoveStart( true );

        // 쓰레드 구현
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while ( getIsMoveStart() ) {

                    try {

                        moveDown();

                        if ( yPos > 900 || mf.getPlayGround().getAppearCnt() == 10000 ) {
                            System.out.println( "[LOG] :::::::: Enemy Jet1 종료." );
                            setIsMoveStart( false );
                        }

                        Thread.sleep(3);

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }

            }
        });

        thread.setName( "Enemy Jet 1" );
        thread.start();

    }

    @Override
    public void crush() {
        // 쓰레드 구현
        new Thread(new Runnable() {
            @Override
            public void run() {
                while ( yPos < 900 ) {
                    try {
                        if (
                            Math.abs( (mf.getUserJet().getxPos() + JET_W / 2 ) - (enemyJet1.xPos + JET_W / 2) ) < (enemyJet1.w / 2 + (JET_W / 2))
                                &&
                            Math.abs( (mf.getUserJet().getyPos() + JET_H / 2 ) - (enemyJet1.yPos + JET_H / 2) ) < (enemyJet1.h / 2 + (JET_H / 2))
                        ) {
                            collision = true;
                        } else {
                            collision = false;
                        }
                        if ( collision ) {
                            userJet.setLife(userJet.getLife() - 1);
                            mf.getPlayGround().getEnemyJetList().remove( enemyJet1 );
                            if ( userJet.getLife() <= 0 ) {

                                mf.dispose();
//                                new MainFrame();

                            }
                            Thread.sleep(2000);

                        }

                        if ( crushCheck ) {
                            setIsCrushCheck( true );
                            enemyJet1.setJetImgIcon( new ImageIcon( EXPLOSION ));
                            enemyJet1.repaint();
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

        g.drawImage(getJetImgIcon().getImage(), xPos, yPos, w, h, null );

    }


}
