package com.flight.game.objects.missile;

import com.flight.game.frame.MainFrame;
import com.flight.game.objects.user.UserJet;
import com.flight.game.set.Util;

import javax.swing.*;

import static com.flight.game.set.ImagesSet.*;

public abstract class Missile {

    // ================================= 멤버변수 =================================
    protected MainFrame mf;
    protected UserJet userJet;
    protected boolean collision;

    protected ImageIcon missile1    = new ImageIcon( MISSILE_1 );
    protected ImageIcon missile2    = new ImageIcon( MISSILE_2 );
    protected ImageIcon missile3    = new ImageIcon( MISSILE_3 );
    protected ImageIcon missile4    = new ImageIcon( MISSILE_4 );
    protected ImageIcon bossMissile = new ImageIcon( BOSS_MISSILE );

    protected double xPos, yPos, angle, speed;
    protected int w, h;
    protected boolean isMissileStart = false;

    // ================================= 생성자 =================================

    // ================================= getter/setter 메서드 =================================

    public ImageIcon getMissile1() {
        return missile1;
    }

    public void setMissile1(ImageIcon missile1) {
        this.missile1 = missile1;
    }

    public ImageIcon getMissile2() {
        return missile2;
    }

    public void setMissile2(ImageIcon missile2) {
        this.missile2 = missile2;
    }

    public ImageIcon getMissile3() {
        return missile3;
    }

    public void setMissile3(ImageIcon missile3) {
        this.missile3 = missile3;
    }

    public ImageIcon getMissile4() {
        return missile4;
    }

    public void setMissile4(ImageIcon missile4) {
        this.missile4 = missile4;
    }

    public ImageIcon getBossMissile() {
        return bossMissile;
    }

    public void setBossMissile(ImageIcon bossMissile) {
        this.bossMissile = bossMissile;
    }

    public double getXPos() {
        return xPos;
    }

    public void setXPos(double xPos) {
        this.xPos = xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public void setYPos(double yPos) {
        this.yPos = yPos;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    // ================================= 메서드 =================================
    public abstract void missileFire();

}
