package com.flight.game.frame.panel;

import com.flight.game.frame.MainFrame;
import com.flight.game.objects.enemy.*;
import com.flight.game.objects.jet.EnemyJet;
import com.flight.game.set.Util;

import javax.swing.*;

import java.awt.*;
import java.util.Vector;

import static com.flight.game.set.ImagesSet.*;
import static com.flight.game.set.ScreenSize.*;

public class PlayGround extends JPanel {

    // ================================= 멤버변수 =================================
    private MainFrame mf;

    private ImageIcon stageBackgroundIcon, bossStageBackgroundIcon;
    private Image stageBackgroundImg, bossStageBackgroundImg;
    private int stageY;
    // 적기 출현 시점을 잡는 cnt
    private int appearCnt = 1;

    // **** 보스 관련 ****
    private Boss boss;
    private int bossStage1, bossStage2;

    // ****************
    // 적기 리스트
    Vector<EnemyJet> enemyJetList;

    private JLabel life1, life2, life3;

    // ================================= 생성자 =================================
    public PlayGround(MainFrame mf) {

        this.mf = mf;

        // 배경
        this.stageBackgroundIcon     = new ImageIcon( STAGE_BACKGROUND_PATH );
        this.bossStageBackgroundIcon = new ImageIcon( BOSS_STAGE_BACKGROUND_PATH );
        this.stageBackgroundImg      = this.stageBackgroundIcon.getImage();
        this.bossStageBackgroundImg  = this.bossStageBackgroundIcon.getImage();

        // 배경 이미지의 y좌표
        this.stageY = -( stageBackgroundImg.getHeight(null) - bossStageBackgroundImg.getHeight(null) );
        this.bossStage1 = -( stageBackgroundImg.getHeight(null) );
        this.bossStage2 = -( stageBackgroundImg.getHeight(null) ) + bossStageBackgroundImg.getHeight(null);

        // 목숨
        this.life1 = new JLabel( Util.makeJet( LIFE_IMG_PATH, LIFE_W, LIFE_H) );
        this.life2 = new JLabel( Util.makeJet( LIFE_IMG_PATH, LIFE_W, LIFE_H) );
        this.life3 = new JLabel( Util.makeJet( LIFE_IMG_PATH, LIFE_W, LIFE_H) );

        panelAdd( this.life1, 0, 0, LIFE_W, LIFE_H );
        panelAdd( this.life2, 50, 0, LIFE_W, LIFE_H );
        panelAdd( this.life3, 100, 0, LIFE_W, LIFE_H );

        // 적기 리스트 초기화
        enemyJetList = new Vector<>();

        battleStart();

        setLayout( null );
    }


    // ================================= getter/setter 메서드 =================================
    public int getAppearCnt() {
        return appearCnt;
    }

    public void setAppearCnt(int appearCnt) {
        this.appearCnt = appearCnt;
    }

    public Vector<EnemyJet> getEnemyJetList() {
        return enemyJetList;
    }

    public void setEnemyJetList(Vector<EnemyJet> enemyJetList) {
        this.enemyJetList = enemyJetList;
    }
    // ================================= 메서드 =================================

    /**
     * 게임시작
     */
    public void battleStart() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                mf.setIsStart( true );

                while ( mf.getIsStart() ) {

                    try {
                        setLayout( null );

                        ++stageY;
                        ++bossStage1;
                        ++bossStage2;
                        ++appearCnt;

                        lifeCounting();
                        enemyBatch();

                        if ( stageY > bossStageBackgroundImg.getHeight(null) ) {
                            stageY = bossStageBackgroundImg.getHeight( null );
                            if ( bossStage1 > (bossStageBackgroundImg.getHeight(null) - 1) ) {
                                bossStage1 = -(bossStageBackgroundImg.getHeight(null) - 1);
                            }
                            if ( bossStage2 > (bossStageBackgroundImg.getHeight(null) - 1) ) {
                                bossStage2 = -(bossStageBackgroundImg.getHeight(null) - 1);
                            }
                        }
                        repaint();

                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }).start();
    }

    /**
     * panel에 label추가
     * @param label
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public void panelAdd(JLabel label, int x, int y, int w, int h) {
        this.add( label );
        label.setBounds(x, y, w, h);
    }

    /**
     * 목숨 카운팅
     */
    public void lifeCounting() {

        if ( mf.getUserJet().getLife() == 3 ) {
            life1.setVisible( true );
            life2.setVisible( true );
            life3.setVisible( true );
        } else if ( mf.getUserJet().getLife() == 2 ) {
            life1.setVisible( true );
            life2.setVisible( true );
            life3.setVisible( false );
        } else if ( mf.getUserJet().getLife() == 1 ) {
            life1.setVisible( true );
            life2.setVisible( false );
            life3.setVisible( false );
        } else {
            life1.setVisible( false );
            life2.setVisible( false );
            life3.setVisible( false );
        }

    }

    /**
     * 적기 배치
     */
    public void enemyBatch() {

        if ( this.appearCnt == 1000 || this.appearCnt == 3000 ) {

            this.enemyJetList.add( new EnemyJet1( mf, mf.getUserJet(), 50, 0,    ENEMY_JET_W, ENEMY_JET_H ) );
            this.enemyJetList.add( new EnemyJet1( mf, mf.getUserJet(), 100, -50,  ENEMY_JET_W, ENEMY_JET_H ) );
            this.enemyJetList.add( new EnemyJet1( mf, mf.getUserJet(), 150, -100, ENEMY_JET_W, ENEMY_JET_H ) );
            this.enemyJetList.add( new EnemyJet1( mf, mf.getUserJet(), 200, -150, ENEMY_JET_W, ENEMY_JET_H ) );
            this.enemyJetList.add( new EnemyJet1( mf, mf.getUserJet(), 250, -200, ENEMY_JET_W, ENEMY_JET_H ) );

        }

        if ( this.appearCnt == 2000 || this.appearCnt == 4000 ) {

            this.enemyJetList.add( new EnemyJet1( mf, mf.getUserJet(), 500, 0,    ENEMY_JET_W, ENEMY_JET_H ) );
            this.enemyJetList.add( new EnemyJet1( mf, mf.getUserJet(), 450, -50,  ENEMY_JET_W, ENEMY_JET_H ) );
            this.enemyJetList.add( new EnemyJet1( mf, mf.getUserJet(), 400, -100, ENEMY_JET_W, ENEMY_JET_H ) );
            this.enemyJetList.add( new EnemyJet1( mf, mf.getUserJet(), 350, -150, ENEMY_JET_W, ENEMY_JET_H ) );
            this.enemyJetList.add( new EnemyJet1( mf, mf.getUserJet(), 300, -200, ENEMY_JET_W, ENEMY_JET_H ) );

        }

        if ( this.appearCnt == 5000 ) {

            this.enemyJetList.add( new EnemyJet2( mf, mf.getUserJet(), 100, 300, ENEMY_JET_W, ENEMY_JET_H ) );
            this.enemyJetList.add( new EnemyJet2( mf, mf.getUserJet(), 500, 300, ENEMY_JET_W, ENEMY_JET_H ) );

        }

        if ( this.appearCnt == 500 || this.appearCnt == 1500 || this.appearCnt == 3500 || this.appearCnt == 5000 || this.appearCnt == 6000) {

            this.enemyJetList.add( new EnemyJet3( mf, mf.getUserJet(), 600, -200, ENEMY_JET_W, ENEMY_JET_H ) );
            this.enemyJetList.add( new EnemyJet4( mf, mf.getUserJet(), 0, 0, ENEMY_JET_W, ENEMY_JET_H ) );

        }

        if ( this.appearCnt == 6000 ) {

            this.enemyJetList.add( new EnemyJet5( mf, mf.getUserJet(), 300, -50, ENEMY_JET_W, ENEMY_JET_H ) );
            this.enemyJetList.add( new EnemyJet5( mf, mf.getUserJet(), 500, -50, ENEMY_JET_W, ENEMY_JET_H ) );

        }

        if ( this.appearCnt == 7000 ) {

            this.enemyJetList.add( new EnemyJet6( mf, mf.getUserJet(), 650, 400, ENEMY_JET_W, ENEMY_JET_H ) );

        }

        if ( this.appearCnt == 8000 ) {

            this.enemyJetList.add( new EnemyJet5( mf, mf.getUserJet(), 300, 0, ENEMY_JET_W, ENEMY_JET_H ) );
            this.enemyJetList.add( new EnemyJet1( mf, mf.getUserJet(), 100, 0, ENEMY_JET_W, ENEMY_JET_H ) );
            this.enemyJetList.add( new EnemyJet1( mf, mf.getUserJet(), 200, 0, ENEMY_JET_W, ENEMY_JET_H ) );
            this.enemyJetList.add( new EnemyJet1( mf, mf.getUserJet(), 300, 0, ENEMY_JET_W, ENEMY_JET_H ) );
            this.enemyJetList.add( new EnemyJet1( mf, mf.getUserJet(), 400, 0, ENEMY_JET_W, ENEMY_JET_H ) );
            this.enemyJetList.add( new EnemyJet5( mf, mf.getUserJet(), 200, 0, ENEMY_JET_W, ENEMY_JET_H ) );

        }

        // 보스 출현
        if ( this.appearCnt == 10000 ) {

            enemyJetList.removeAllElements();
            this.enemyJetList.add( new Boss( mf, mf.getUserJet(), 0, -150 ) );

        }


    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // background img
        g.drawImage( this.stageBackgroundImg, 0, stageY, null );
        g.drawImage( this.bossStageBackgroundImg, 0, bossStage1, null );
        g.drawImage( this.bossStageBackgroundImg, 0, bossStage2, null );

        // 적기 출현
        for ( int i = 0; i < enemyJetList.size(); ++i ) {
            if ( this.enemyJetList.get(i) != null ) this.enemyJetList.get( i ).obejectUpdate( g );
        }

        if ( mf.getUserJet() != null ) mf.getUserJet().obejectUpdate( g );

        repaint();

    }
}
