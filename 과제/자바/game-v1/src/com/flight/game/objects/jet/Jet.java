package com.flight.game.objects.jet;

import javax.swing.*;
import java.awt.*;

public abstract class Jet extends JLabel {

    protected ImageIcon jetImgIcon;
    protected int xPos, yPos;
    protected int w, h;
    protected int life;

    protected boolean isMoveStart = false;
    protected int missileSpeed;

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
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

    public boolean getIsMoveStart() {
        return isMoveStart;
    }

    public void setIsMoveStart(boolean moveStart) {
        isMoveStart = moveStart;
    }

    public ImageIcon getJetImgIcon() {
        return jetImgIcon;
    }

    public void setJetImgIcon(ImageIcon jetImgIcon) {
        this.jetImgIcon = jetImgIcon;
    }

    protected abstract void move();
    public abstract void obejectUpdate( Graphics g );
}
