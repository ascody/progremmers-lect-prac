package com.flight.game.objects.jet;

import com.flight.game.objects.user.UserJet;

import javax.swing.*;
import java.awt.*;

import static com.flight.game.set.ImagesSet.EXPLOSION;

public abstract class EnemyJet extends Jet {

    // ================================= 멤버변수 =================================
    protected UserJet userJet;
    protected boolean collision = false; // 기체 충돌 체크
    protected boolean missileCollision = false; // 총알 충돌 체크
    protected boolean crushCheck;
    protected boolean isLife;
    // ================================= 생성자 =================================
    // ================================= getter/setter 메서드 =================================

    public boolean getIsCrushCheck() {
        return crushCheck;
    }

    public void setIsCrushCheck(boolean crushCheck) {
        this.crushCheck = crushCheck;
    };

    // ================================= 추상 메서드 =================================
    public abstract void crush();

    // ================================= 메서드 =================================
    public void moveUp () {
        --this.yPos;
        setLocation( xPos, yPos );
    }

    public void moveDown () {
        ++this.yPos;
        setLocation( xPos, yPos );
    }

    public void moveLeft () {
        --this.xPos;
        setLocation( xPos, yPos );
    }

    public void moveRight() {
        ++this.xPos;
        setLocation( xPos, yPos );
    }

    public void explosure( EnemyJet enemyJet ) {

        try {
            enemyJet.setJetImgIcon( new ImageIcon( EXPLOSION ));
            Thread.sleep(1000);
            enemyJet.repaint();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
