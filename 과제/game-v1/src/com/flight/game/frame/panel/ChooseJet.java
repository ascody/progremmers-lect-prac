package com.flight.game.frame.panel;

import com.flight.game.frame.MainFrame;
import com.flight.game.objects.user.UserJet;
import com.flight.game.set.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.flight.game.set.ImagesSet.*;
import static com.flight.game.set.PanelName.PLAY_GROUND;
import static com.flight.game.set.PlayerJet.*;
import static com.flight.game.set.ScreenSize.*;

public class ChooseJet extends JPanel {

    // ================================= 멤버변수 =================================
    private MainFrame mf;

    private ImageIcon player1, player2, player3;

    private ImageIcon chooseJetBackgroundIcon;
    private Image chooseJetBackground;

    // ================================= 생성자 =================================
    public ChooseJet ( MainFrame mf ) {

        System.out.println( "[LOG] :::::::: Choose Jet Panel 실행." );
        setLayout( null );
        this.mf = mf;
        init();

    }

    // ================================= 메서드 =================================
    /**
     * 초기화
     */
    public void init() {
        chooseJetBackgroundIcon = Util.makeJet( CHOOSE_JET_BACKGROUND, 200, 180 );
        chooseJetBackground = chooseJetBackgroundIcon.getImage();

        player1 = Util.makeJet( PLAYER_JET_1_PATH, JET_W, JET_H );
        player2 = Util.makeJet( PLAYER_JET_2_PATH, JET_W, JET_H );
        player3 = Util.makeJet( PLAYER_JET_3_PATH, JET_W, JET_H );

        JButton btn1 = new JButton( player1 );
        JButton btn2 = new JButton( player2 );
        JButton btn3 = new JButton( player3 );

        // 버튼 옵션 셋팅
        setBtnOpt( btn1,100, 640, JET_W, JET_H );
        setBtnOpt( btn2,250, 640, JET_W, JET_H );
        setBtnOpt( btn3,400, 640, JET_W, JET_H );

        // 버튼 이벤트리스너
        addMouseEvent( btn1, PLAYER_JET_1 );
        addMouseEvent( btn2, PLAYER_JET_2 );
        addMouseEvent( btn3, PLAYER_JET_3 );

        // 패널에 추가
        this.add( btn1 );
        this.add( btn2 );
        this.add( btn3 );

    }

    /**
     * 제트기를 선택 시 선택된 기체와 다음 화면으로 넘어가도록
     * @param btn
     * @param playerJet
     */
    public void addMouseEvent( JButton btn, String playerJet ) {

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                mf.setUserJet( new UserJet( mf, playerJet ) ); // 제트기를 배치 시킨다.
                mf.changePanel( PLAY_GROUND ); // 패널을 PLAY_GROUND로 교체
                mf.getPlayGround().add( mf.getUserJet() );

            }
        });

    }

    /**
     * btn들의 옵션을 셋팅한다.
     * @param btn
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public void setBtnOpt( JButton btn, int x, int y, int w, int h ) {

        btn.setBorderPainted( false );
        btn.setContentAreaFilled( false );
        btn.setFocusPainted( false );
        btn.setBounds( x, y, w, h );

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage( chooseJetBackground, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, 0, 196, 182, this);
        repaint();

    }
}
