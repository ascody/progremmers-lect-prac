package com.flight.game.objects.user;

import com.flight.game.frame.MainFrame;
import com.flight.game.objects.enemy.Boss;
import com.flight.game.objects.jet.EnemyJet;
import com.flight.game.objects.jet.Jet;
import com.flight.game.set.Util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import static com.flight.game.set.ImagesSet.*;
import static com.flight.game.set.PlayerJet.*;
import static com.flight.game.set.ScreenSize.*;

public class UserJet extends Jet {

    // ================================= 멤버변수 =================================
    private MainFrame mf;
    private String player;
    private boolean isUp = false, isDown = false, isLeft = false, isRight = false, isShoot = false;
    private ImageIcon playerJetIcon;
    private EnemyJet enemyJet;
    private int missileLevel;
    private UserJetMissile userJetMissile;
    private Boss boss;

    private List<UserJetMissile> userMissileList;
    private Vector<EnemyJet> dummyEnemyJetList;

    // ================================= 생성자 =================================
    public UserJet(MainFrame mf, String playerJet ) {

        System.out.println( "[LOG] :::::::: User Jet " + playerJet + " 실행." );

        // 적기 객체를 담는 배열
        userMissileList  = new ArrayList<>();
        dummyEnemyJetList    = new Vector<>();

        this.missileLevel = 0;
        // 시작 시 하단 중앙에 기체를 배치 시킨다.
        xPos = (SCREEN_WIDTH / 2) - (JET_W / 2);
        yPos = (SCREEN_HEIGHT - (JET_H * 2));

        // 목숨 * 3
        super.life = 3;

        this.player = playerJet;
        this.mf = mf;

        if ( playerJet.equals( PLAYER_JET_1 ) ) playerJetIcon = Util.makeJet(PLAYER_JET_1_PATH, JET_W, JET_H);
        else if ( playerJet.equals( PLAYER_JET_2 ) )  playerJetIcon = Util.makeJet(PLAYER_JET_2_PATH, JET_W, JET_H);
        else if ( playerJet.equals( PLAYER_JET_3 ) ) playerJetIcon  = Util.makeJet(PLAYER_JET_3_PATH, JET_W, JET_H);

        setIcon( playerJetIcon );
        setLocation( xPos, yPos );
        setSize( JET_W, JET_H );

        move();
    }

    // ================================= getter/setter 메서드 =================================
    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getLife() {
        return life;
    }

    public String getPlayer() {
        return player;
    }

    public void setUp(boolean up) {
        isUp = up;
    }

    public void setDown(boolean down) {
        isDown = down;
    }

    public void setLeft(boolean left) {
        isLeft = left;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public boolean getShoot() {
        return isShoot;
    }

    public void setShoot(boolean shoot) {
        isShoot = shoot;
    }

    public int getMissileLevel() {
        return missileLevel;
    }

    public void setMissileLevel(int missileLevel) {

        if ( this.missileLevel >= 4 ) this.missileLevel = 0;
        else this.missileLevel = missileLevel;

    }

    // ================================= 메서드 =================================

    /**
     * 이동키 이벤트에 따른 처리
     */
    private void keyListener() {

        if ( isUp ) yPos    -= 3;
        if ( isDown ) yPos  += 3;
        if ( isLeft ) xPos  -= 3;
        if ( isRight ) xPos += 3;

    }

    public void shootMissile() {

        if (  getShoot() && missileSpeed % 30 == 0 ) {

            if ( missileLevel == 0 ) {
                userJetMissile = new UserJetMissile( enemyJet, xPos + 10, yPos - 40,  90, 2 );
                userMissileList.add( userJetMissile );
            }

            if ( missileLevel == 1 ) {

                userJetMissile = new UserJetMissile( enemyJet, xPos - 10, yPos - 40,  90, 2 );
                userMissileList.add( userJetMissile );

                userJetMissile = new UserJetMissile( enemyJet, xPos + 40, yPos - 40,  90, 2 );
                userMissileList.add( userJetMissile );

            }

            if ( missileLevel == 2 ) {

                userJetMissile = new UserJetMissile( enemyJet, xPos - 30, yPos - 40,  90, 2 );
                userMissileList.add( userJetMissile );

                userJetMissile = new UserJetMissile( enemyJet, xPos + 15, yPos - 40,  90, 2 );
                userMissileList.add( userJetMissile );

                userJetMissile = new UserJetMissile( enemyJet, xPos + 60, yPos - 40,  90, 2 );
                userMissileList.add( userJetMissile );

            }

            if ( missileLevel == 3 ) {

                userJetMissile = new UserJetMissile( enemyJet, xPos - 55, yPos - 40,  90, 2 );
                userMissileList.add( userJetMissile );

                userJetMissile = new UserJetMissile( enemyJet, xPos - 18, yPos - 40,  90, 2 );
                userMissileList.add( userJetMissile );

                userJetMissile = new UserJetMissile( enemyJet, xPos + 18, yPos - 40,  90, 2 );
                userMissileList.add( userJetMissile );

                userJetMissile = new UserJetMissile( enemyJet, xPos + 55, yPos - 40,  90, 2 );
                userMissileList.add( userJetMissile );

            }

            if ( missileLevel == 4 ) {

                userJetMissile = new UserJetMissile( enemyJet, xPos - 70, yPos - 40,  80, 2 );
                userMissileList.add( userJetMissile );

                userJetMissile = new UserJetMissile( enemyJet, xPos - 55, yPos - 40,  90, 2 );
                userMissileList.add( userJetMissile );

                userJetMissile = new UserJetMissile( enemyJet, xPos - 18, yPos - 40,  90, 2 );
                userMissileList.add( userJetMissile );

                userJetMissile = new UserJetMissile( enemyJet, xPos + 18, yPos - 40,  90, 2 );
                userMissileList.add( userJetMissile );

                userJetMissile = new UserJetMissile( enemyJet, xPos + 55, yPos - 40,  90, 2 );
                userMissileList.add( userJetMissile );

                userJetMissile = new UserJetMissile( enemyJet, xPos + 70, yPos - 40,  100, 2 );
                userMissileList.add( userJetMissile );

            }

        }

    }

    public void shootFire() {

        for ( int i = 0; i < userMissileList.size(); ++i ) {

            userJetMissile = userMissileList.get( i );
            userJetMissile.missileFire();

        }

    }

    /**
     * 사용자가 쏜 미사일이 적기에 맞추었을 때
     */
    public void missileControl() {

        for ( int u = 0; u < userMissileList.size(); ++u ) { // 미사일 리스트

            UserJetMissile uObj = userMissileList.get( u );

            for ( int e = 0; e < mf.getPlayGround().getEnemyJetList().size(); ++e ) { // 적기 객체 리스트

                EnemyJet eObj = mf.getPlayGround().getEnemyJetList().get( e );

                if (
                        isCrush( (int) uObj.getXPos(), (int) uObj.getYPos(), eObj.getX(), eObj.getY(), uObj.getMissile2().getImage().getWidth(null), uObj.getMissile2().getImage().getHeight(null), eObj.getW() / 5, eObj.getH() / 5 )
                ) {
                    if ( u > 0 && userMissileList.size() > 0) {
                        userMissileList.remove( u );
                        u = u - 1;

                    }
                    eObj.setLife( eObj.getLife() - 1 );

                    if ( eObj.getLife() == 0 ) {

                        eObj.setIsCrushCheck( true );
                        dummyEnemyJetList.add( eObj );

                    }

                }

            }


        }

        for ( EnemyJet e : dummyEnemyJetList ) {
            mf.getPlayGround().getEnemyJetList().remove( e );
        }


    }

    public boolean isCrush( int xPos1, int yPos1, int xPos2, int yPos2, int w1, int h1, int w2, int h2 ) {

        boolean result = false;

        if (
                Math.abs( (( xPos1 + w1 ) / 2 ) - (( xPos2 + w2 ) / 2) ) < ((w2 / 2) + (w1 / 2))
                &&
                Math.abs( ((yPos1 + h1) / 2) - ( (yPos2 + h2) / 2 )) < ( (h2 / 2) + (h1 / 2) )
        ) result = true;
        else result = false;

        return result;

    }

    /**
     * 사용자 이동
     */
    @Override
    protected void move() {

        this.isMoveStart = true;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                missileSpeed = 0;
                while ( isMoveStart ) {

                    try {

                        keyListener();
                        shootMissile();
                        shootFire();
                        missileControl();

                        setLocation(xPos, yPos);

                        Thread.sleep( 5 );

                        if ( missileSpeed == 30001) missileSpeed = 0;
                        ++missileSpeed;

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }

            }
        });

        thread.setName( player );
        thread.start();

    }

    @Override
    public void obejectUpdate(Graphics g) {
        try {
            for ( int i = 0; i < userMissileList.size(); ++i ) {
                if ( userJetMissile != null ) {
                    userJetMissile = userMissileList.get( i );
                    g.drawImage( userJetMissile.getMissile2().getImage(), (int) userJetMissile.getXPos(), (int) userJetMissile.getYPos(), null );
                }

            }
        } catch ( Exception e ) {

        }


    }
}
