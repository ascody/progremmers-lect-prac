package org.updown;

import java.util.Random;
import java.util.Scanner;

public class Updown {
    static Random rand = new Random();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int answer = rand.nextInt(100) + 1;

        int count = 0;
        while (true) {
            System.out.println("숫자를 맞혀보세요! (1 ~ 100)");
            int guess = 0;
            while (true) {
                try {
                    guess = sc.nextInt();
                    if (guess < 1 || guess > 100) {
                        System.out.println("1에서 100 사이의 수를 입력해주세요!");
                    } else break;
                } catch (Exception e) {
                    System.out.println("숫자를 입력해주세요!");
                    sc.nextLine();
                }
            }
            count++;

            System.out.println("입력 > " + guess);
            if (guess < answer) {
                System.out.println("UP! 더 큰 수입니다.");
            } else if (guess > answer) {
                System.out.println("DOWN! 더 작은 수입니다.");
            } else {
                System.out.println("정답입니다! " + count + "번 만에 맞혔어요.");
                break;
            }
        }
    }
}
