package com.flight.game.objects.enemy;

import com.flight.game.frame.MainFrame;
import com.flight.game.objects.jet.EnemyJet;
import com.flight.game.objects.user.UserJet;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

import static com.flight.game.set.ImagesSet.ENEMY_JET_2;
import static com.flight.game.set.ImagesSet.EXPLOSION;
import static com.flight.game.set.PanelName.INIT_TITLE;
import static com.flight.game.set.ScreenSize.JET_H;
import static com.flight.game.set.ScreenSize.JET_W;

public class EnemyJet2 extends EnemyJet {


    // ================================= 멤버변수 =================================
    private MainFrame mf;
    // 본인 객체를 넘겨야하는데 스레드 안에서는 this의 주체가 바뀌므로 따로 변수에 넣어둔다.
    private EnemyJet2 enemyJet2 = this;
    private EnemyMissile enemyMissile;
    private Vector<EnemyMissile> enemyMissileList;

    private boolean crushCheck2 = false;
    // ================================= 생성자 =================================

    public EnemyJet2( MainFrame mf, UserJet userJet, int x, int y, int w, int h ) {

        System.out.println( "[LOG] :::::::: Enemy Jet2 실행." );
        this.mf         = mf;
        this.userJet    = userJet;
        this.xPos       = x;
        this.yPos       = y;
        this.w          = w;
        this.h          = h;
        this.jetImgIcon = new ImageIcon( ENEMY_JET_2 );
        this.life       = 3;
        this.crushCheck = false;
        this.isLife     = true;
        this.enemyMissileList = new Vector<>();

        this.move();
//        this.crush();

    }

    // ================================= getter/setter 메서드 =================================

    // ================================= 메서드 =================================
    @Override
    public void move() {

        this.isMoveStart = true;
        missileSpeed = 0;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while ( isMoveStart )
                try {

                    if ( yPos > 100 ) {
                        moveUp();
                    }

                    shootMissile();
//                    shootFire();
                    if ( missileSpeed % 10000 == 0 ) missileSpeed = 0;
                    ++missileSpeed;

                    if ( yPos > 900 || mf.getPlayGround().getAppearCnt() == 10000 ) {
                        System.out.println( "[LOG] :::::::: Enemy Jet2 종료." );
                        isMoveStart = false;
                    }

                    Thread.sleep( 5 );

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

        });

        thread.setName( "Enemy Jet 2" );
        thread.start();

    }

    public void shootMissile() {

        if ( missileSpeed % 100 == 0 ) {

            enemyMissile = new EnemyMissile( mf, enemyJet2, userJet, xPos, yPos + 40, 300,2, 30, 30 );
            enemyMissileList.add( enemyMissile );

            enemyMissile = new EnemyMissile( mf, enemyJet2, userJet, xPos, yPos + 40, 1300,2, 30, 30 );
            enemyMissileList.add( enemyMissile );

        }

    }

    @Override
    public boolean getIsCrushCheck() {
        return super.crushCheck;
    }

    @Override
    public void setIsCrushCheck(boolean crushCheck) {
        this.crushCheck2 = crushCheck;
        super.crushCheck = crushCheck;
    }

    @Override
    public void crush() {
        // 쓰레드 구현
        new Thread(new Runnable() {
            @Override
            public void run() {
                while ( yPos < 900 && yPos > -300 ) {
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
                            mf.getPlayGround().getEnemyJetList().remove( enemyJet2 );
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
                            enemyJet2.setJetImgIcon( new ImageIcon( EXPLOSION ));
                            enemyJet2.repaint();
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

            // 적기 이미지
            g.drawImage( this.jetImgIcon.getImage(), xPos, yPos, w, h, null );

            // 미사일 발사
            for (EnemyMissile e : enemyMissileList) {
//                if ( enemyMissileList.size() < 1 && mf.getPlayGround().getAppearCnt() == 10000 ) break;
                g.drawImage(e.getMissile1().getImage(), (int) e.getXPos(), (int) e.getYPos(), e.getW(), e.getH(), null);
            }

        } catch ( Exception e ) {

        }

    }


}
