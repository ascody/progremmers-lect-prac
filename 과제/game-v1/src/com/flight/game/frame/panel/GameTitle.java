package com.flight.game.frame.panel;

import com.flight.game.frame.MainFrame;
import com.flight.game.set.Util;

import javax.swing.*;
import java.awt.*;

import static com.flight.game.set.ImagesSet.INIT_TITLE_PATH;
import static com.flight.game.set.ScreenSize.SCREEN_HEIGHT;
import static com.flight.game.set.ScreenSize.SCREEN_WIDTH;

public class GameTitle extends JPanel {

    private ImageIcon titleIcon = Util.makeJet( INIT_TITLE_PATH, 340, 590 );

    private Image titleImg = titleIcon.getImage();

    private MainFrame mf;

    public GameTitle(MainFrame mf) {
        setLayout( null );
        this.mf = mf;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage( titleImg, 0, 0, SCREEN_WIDTH - 15, SCREEN_HEIGHT - 15, 0, 0, 338, 594, this );
    }
}
