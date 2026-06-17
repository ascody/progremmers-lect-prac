package com.flight.game.set;

import javax.swing.*;
import java.awt.*;

public class Util {

    public static boolean mainGameStart = true;

    public static void printLoading() {
        char[] box = new char[50];
        // char배열을 사용해서 로딩 박스 채우기.
        for(int i = 0 ; i < 50; i++) {
            box[i] = '□';
        }

        for(int i = 0; i < 50; i++) {
            for (int k = 0; k < 60; k++) System.out.println(); // 계속해서 엔터치기. ( 콘솔창 내용 지우기 역할 )

            // 상단 부분.
            System.out.print("┌");
            for(int q = 0; q < 60; q++)System.out.print("─");
            System.out.println("┐");



            System.out.println("\n                 World War 3에 오신걸 환영합니다.\n");
            System.out.print("       ");
            System.out.println(" --------------- Loading . . . . --------------- ");

            // 로딩 박스 출력.
            System.out.print("       ");
            for(int j = 0 ; j < 49; j++) {System.out.print(box[j]);}
            System.out.println();


            // 하단 부분.
            System.out.println();
            System.out.print("└");
            for(int w = 0; w < 60; w++)System.out.print("─");
            System.out.print("┘");

            box[i] = '■'; // 로딩박스 채우기.

            // 진짜 로딩되는 것처럼 보이기 위해 간격두기위한 sleep.
            try {
                Thread.sleep(80);
                System.out.println();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for (int k = 0; k < 60; k++) System.out.println(); // 계속해서 엔터치기. ( 콘솔창 내용 지우기 역할 )
    }

    /**
     * 제트기의 이미지와 사이즈를 파라미터로 받아와서 생성한다.
     * @param src
     * @param w
     * @param h
     * @return
     */
    public static ImageIcon makeJet( String src, int w, int h ) {

        Image pImg = new ImageIcon ( src ).getImage();
        pImg = pImg.getScaledInstance( w, h,  java.awt.Image.SCALE_SMOOTH );

        return new ImageIcon( pImg );
    }

}
