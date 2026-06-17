package com.flight.game.frame;

import com.flight.game.frame.panel.ChooseJet;
import com.flight.game.frame.panel.GameOver;
import com.flight.game.frame.panel.GameTitle;
import com.flight.game.frame.panel.PlayGround;
import com.flight.game.objects.user.UserJet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.flight.game.set.PanelName.*;
import static com.flight.game.set.ScreenSize.SCREEN_HEIGHT;
import static com.flight.game.set.ScreenSize.SCREEN_WIDTH;

public class MainFrame extends JFrame {

    // ================================= 멤버변수 =================================
    // 패널관련
    private GameTitle gameTitle;
    private ChooseJet chooseJet;
    private PlayGround playGround;
    private GameOver gameOver;

    // 패널 스레드 관련
    private boolean isStart = false;

    // 플레이어관련
    private UserJet userJet;

    public MainFrame() {

        System.out.println( "[LOG] :::::::: Main Frame 실행." );
        // 시작화면 설정
        changePanel( INIT_TITLE );
        // 프레임생성
        generateFrame();
        // 리스너
        listener();

        // 화면에 보이게
        setVisible( true );

    }
    // ================================= getter/setter 메서드 =================================

    public ChooseJet getChooseJet() {
        return chooseJet;
    }

    public void setChooseJet(ChooseJet chooseJet) {
        this.chooseJet = chooseJet;
    }

    public PlayGround getPlayGround() {
        return playGround;
    }

    public void setPlayGround(PlayGround playGround) {
        this.playGround = playGround;
    }

    public UserJet getUserJet() {
        return userJet;
    }

    public void setUserJet(UserJet userJet) {
        this.userJet = userJet;
    }

    public boolean getIsStart() {
        return isStart;
    }

    public void setIsStart(boolean start) {
        isStart = start;
    }

    public GameOver getGameOver() {
        return gameOver;
    }

    public void setGameOver(GameOver gameOver) {
        this.gameOver = gameOver;
    }

    // ================================= 메서드 =================================
    /**
     * 프레임 생성
     */
    public void generateFrame() {

        setTitle( GAME_TITLE );
        setSize( SCREEN_WIDTH, SCREEN_HEIGHT );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocationRelativeTo( null );

    }

    /**
     * 패널교체
     */
    public void changePanel( String panelName ) {

        switch ( panelName ) {
            case INIT_TITLE:

                gameTitle = new GameTitle( this );
                drawPanel( gameTitle );
                break;

            case CHOOSE_JET: // 플레이어가 제트기를 선택하는 패널

                this.setChooseJet( new ChooseJet( this ) );
                drawPanel( this.getChooseJet() );
                break;

            case PLAY_GROUND: // 게임시작

                this.setPlayGround( new PlayGround( this ) );
                drawPanel( this.getPlayGround() );
                break;

            case GAME_OVER:
                System.out.println("game over");
                this.setGameOver( new GameOver( this ) );
                drawPanel( this.getGameOver() );
                break;

            default:
                break;
        }

    }

    /**
     * 패널을 그린다.
     * @param myPanel
     */
    public void drawPanel( JPanel myPanel ) {

        getContentPane().removeAll();
        getContentPane().add( myPanel );
        revalidate();
        repaint();

    }
    /**
     * 키 이벤트 리스너
     */
    public void listener() {

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch ( e.getKeyCode() ) {

                    case KeyEvent.VK_ENTER:
                        changePanel( CHOOSE_JET );
                        break;

                    case KeyEvent.VK_SPACE:
                        if ( userJet != null ) userJet.setShoot( true );
                        break;

                    case KeyEvent.VK_1:
                        if ( userJet != null ) userJet.setMissileLevel( userJet.getMissileLevel() +1 );
                        break;

                    case KeyEvent.VK_UP:
                        if ( userJet != null ) userJet.setUp( true );
                        break;

                    case KeyEvent.VK_DOWN:
                        if ( userJet != null ) userJet.setDown( true );
                        break;

                    case KeyEvent.VK_LEFT:
                        if ( userJet != null ) userJet.setLeft( true );
                        break;

                    case KeyEvent.VK_RIGHT:
                        if ( userJet != null ) userJet.setRight( true );
                        break;

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch ( e.getKeyCode() ) {

                    case KeyEvent.VK_SPACE:
                        if ( userJet != null ) userJet.setShoot( false );
                        break;

                    case KeyEvent.VK_UP:
                        if ( userJet != null ) userJet.setUp( false );
                        break;

                    case KeyEvent.VK_DOWN:
                        if ( userJet != null ) userJet.setDown( false );
                        break;

                    case KeyEvent.VK_LEFT:
                        if ( userJet != null ) userJet.setLeft( false );
                        break;

                    case KeyEvent.VK_RIGHT:
                        if ( userJet != null ) userJet.setRight( false );
                        break;
                }
            }
        });

    }

}
