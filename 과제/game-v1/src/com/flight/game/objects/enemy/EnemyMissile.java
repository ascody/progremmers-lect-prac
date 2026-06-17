package com.flight.game.objects.enemy;

import com.flight.game.frame.MainFrame;
import com.flight.game.objects.jet.EnemyJet;
import com.flight.game.objects.missile.Missile;
import com.flight.game.objects.user.UserJet;

import javax.swing.*;

import static com.flight.game.set.ImagesSet.EXPLOSION;

public class EnemyMissile extends Missile implements Runnable {

    private MainFrame mf;
    private EnemyJet enemyJet;

    public EnemyMissile( MainFrame mf, EnemyJet enemyJet, UserJet userJet, int xPos, int yPos, double angle, double speed, int w, int h ) {

        this.enemyJet = enemyJet;
        this.userJet  = userJet;
        this.xPos     = xPos;
        this.yPos     = yPos;
        this.angle    = angle;
        this.speed    = speed;
        this.w        = w;
        this.h        = h;
        this.isMissileStart = true;
        this.mf       = mf;

        new Thread( this ).start();

    }

    @Override
    public void missileFire() {
        xPos -= Math.cos(Math.toRadians(angle)) * speed;
        yPos -= Math.sin(Math.toRadians(angle)) * speed;
    }

    public boolean crush() {
        if (
                Math.abs( (userJet.getxPos() - 11) + (userJet.getW()) - (xPos + w / 3) ) < ( w / 3 + userJet.getW() / 3 )
                    &&
                Math.abs( (userJet.getyPos() - 5) + (userJet.getH() / 3) - (yPos + h / 3) ) < ( h / 3 + userJet.getW() / 3 )
        ) {
            return true;
        } else {
            return false;
        }
    }

    public boolean crash() { // 적 총알이 아군 비행기에 부딪쳤을 시 충돌연산
        if (Math.abs(
                ((userJet.getX() - 11) + userJet.getWidth() / 3) - (xPos + w / 3)) < (w / 3 + userJet.getWidth() / 3)
                && Math.abs(((userJet.getY() - 5) + userJet.getHeight() / 3) - (yPos + h / 3)) < (h / 3
                + userJet.getHeight() / 3)) {
            return true;
        } else {
            return false;
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

    public void explosion(UserJet userJet) {
        userJet.setLife( userJet.getLife() - 1 );
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if ( userJet.getLife() <= 0 ) {

        }

    }

    @Override
    public void run() {

        while ( isMissileStart ) {

            missileFire();

            if ( xPos > 1000 || xPos < -500 || yPos < -500 || yPos > 1000 )
                isMissileStart = false;

            try {
//                if ( isCrush(userJet.getxPos(), userJet.getyPos(), (int) xPos, (int) yPos, userJet.getW(), userJet.getH(), w / 2, h / 2) ) explosion( userJet );
                if ( crash() ) explosion( mf.getUserJet() );
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
