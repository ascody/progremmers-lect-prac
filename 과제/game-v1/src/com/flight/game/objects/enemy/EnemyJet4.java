package com.flight.game.objects.enemy;

import com.flight.game.frame.MainFrame;
import com.flight.game.objects.jet.EnemyJet;
import com.flight.game.objects.user.UserJet;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

import static com.flight.game.set.ImagesSet.ENEMY_JET_4;
import static com.flight.game.set.ImagesSet.EXPLOSION;
import static com.flight.game.set.PanelName.INIT_TITLE;
import static com.flight.game.set.ScreenSize.JET_H;
import static com.flight.game.set.ScreenSize.JET_W;

public class EnemyJet4 extends EnemyJet {

    // ================================= 멤버변수 =================================
    private MainFrame mf;
    private EnemyJet4 enemyJet4 = this;
    private EnemyMissile enemyMissile;
    private Vector<EnemyMissile> enemyMissileList;
    private boolean crushCheck2 = true;

    // ================================= 생성자 =================================
    public EnemyJet4( MainFrame mf, UserJet userJet, int x, int y, int w, int h ) {

        System.out.println( "[LOG] :::::::: Enemy Jet4 실행." );
        this.mf         = mf;
        this.userJet    = userJet;
        this.xPos       = x;
        this.yPos       = y;
        this.w          = w;
        this.h          = h;
        this.jetImgIcon = new ImageIcon( ENEMY_JET_4 );
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

                        moveDown();
                        moveDown();

                        if ( yPos < 400 ) moveRight();
                        if ( yPos > 900 || mf.getPlayGround().getAppearCnt() == 10000 ) {

                            System.out.println( "[LOG] :::::::: Enemy Jet4 종료." );
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

        thread.setName( "Enemy Jet 4" );
        thread.start();

    }

    public void shootMissile() {

        if ( missileSpeed % 200 == 0 ) {

            enemyMissile = new EnemyMissile( mf, enemyJet4, userJet, xPos + 10, yPos + 40, 270, 5, 20, 20 );
            enemyMissileList.add( enemyMissile );

        }

    }

    public void shootFire() {
/*
// 맵에서 벗어난 플레이어총알을 탐지해 list에서 제거
//		for (int i = 0; i < this.playerAttackList.size(); i++) {
//			if (playerAttackList.get(i).getY() < -100) {
//				playerAttackList.remove(i);
//			}
//		}
 */
        for (EnemyMissile e : enemyMissileList) {
            e.missileFire();
        }

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
        new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println(yPos);
                while ( yPos < 900 && yPos > 30 ) {
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
                            mf.getPlayGround().getEnemyJetList().remove( enemyJet4 );
                            if ( userJet.getLife() <= 0 ) {
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
                            enemyJet4.setJetImgIcon( new ImageIcon( EXPLOSION ));
                            enemyJet4.repaint();
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

            for ( EnemyMissile e : enemyMissileList ) g.drawImage( e.getMissile2().getImage(), (int) e.getXPos(), (int) e.getYPos(), e.getW(), e.getH(), null);

        } catch ( Exception e ) {

        }

    }
}
