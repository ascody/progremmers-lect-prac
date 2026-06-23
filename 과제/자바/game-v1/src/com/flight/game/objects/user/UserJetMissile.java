package com.flight.game.objects.user;

import com.flight.game.objects.jet.EnemyJet;
import com.flight.game.objects.missile.Missile;

public class UserJetMissile extends Missile {

    // ================================= 멤버변수 =================================
    private EnemyJet enemyJet;

    // ================================= 생성자 =================================

    public UserJetMissile( EnemyJet enemyJet, int xPos, int yPos, double angle, double speed ) {

        if ( enemyJet != null ) this.enemyJet = enemyJet;

        this.xPos      = xPos;
        this.yPos      = yPos;
        this.angle     = angle;
        this.speed     = speed;
        this.collision = false;

    }

    // ================================= getter/setter 메서드 =================================
    // ================================= 메서드 =================================

    @Override
    public void missileFire() {

        xPos -= Math.cos(Math.toRadians(this.angle)) * speed;
        yPos -= Math.sin(Math.toRadians(this.angle)) * speed;

    }

}
