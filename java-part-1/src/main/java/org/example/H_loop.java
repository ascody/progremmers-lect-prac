package org.example;

// * 반복문
// 어떤 작업이 반복적으로 수행되도록 할 때 사용

import java.util.Scanner;

public class H_loop {

    /*
        * for 문
        for (초기값; 조건식; 증감식) {
            조건식이 참일 때 실행된 문장들을 적는다.
        }
     */

    public static void exam1() {
        for (int i = 0; i < 10; i++) {
            System.out.println("cnt: " + i);
        }
    }

    public static void exam2() {
        for (int i = 1; i < 10; i++) {
            System.out.println("2 * " + i + " = " + (2 * i));
        }
    }

    public static void exam3() {
        for (int i = 1; i < 3; i++) {
            System.out.println("i = " + i);
            for (int j = 1; j < 5; j++) {
                System.out.println("j = " + j);
            }
        }
    }

    public static void practice1() {
        for (int i = 1; i < 10; i++) {
            System.out.println("======= " + i + "단 =======");
            for (int j = 1; j < 10; j++) {
                System.out.println(i + " * " + j + " = " + (i * j));
            }
        }
    }

    // continue
    public static void exam4() {
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 1) continue;
            System.out.println("i = " + i);
        }
    }

    // break
    public static void exam5() {
        for (int i = 1; i <= 100; i++) {
            if (i == 30) break;
            System.out.println("i = " + i);
        }
    }

    public static void practice2() {
        for (int i = 1; i <= 100; i+=2) System.out.println("i = " + i);
    }

    public static void exam6() {
        for (int i = 0; i < 100; i++) {
            System.out.println(" - 첫번째 루프: " + i);
            for (int j = 0; j < 100; j++) {
                System.out.println(" - 두번째 루프: " + j);
                for (int k = 0; k < 100; k++) {
                    System.out.println(" - 세번째 루프: " + k);
                }
            }
        }
    }

    public static void exam7() {
        int i = 0;
        for (; i < 3;) {
            System.out.println(i++);
        }
    }

    public static void exam8() {
        int i = 0;
        for (;;) {
            if (i > 10) break;
            System.out.println(i++);
        }
    }

    public static void exam9() {
        int i = 0;
        for (;true;) {
            System.out.println(i++);
        }
    }

    public static void exam10() {
        for (int i = 9; i >= 0; i--) {
            System.out.println(i);
        }
    }

    public static void practice3() {
        for (int i = 9; i >= 1; i--) {
            System.out.println("===== " + i + "단 =====");
            for (int j = 1; j < 9; j++) {
                System.out.println(i + " * " + j + " = " + (i * j));
            }
        }
    }

    public static void exam11() {
        int cnt = 0;
        while (cnt <= 10) {
            System.out.println("cnt = " + cnt);
            cnt++;
        }
    }

    public static void practice4() {
        int i = 1;
        System.out.println("구구단 2단 출력");
        while (i < 10) {
            System.out.println("2 * " + i + " = " + (2 * i++));
        }
    }

    public static void practice5() {
        int i = 1;
        while (i < 10) {
            int j = 1;
            System.out.println("===== " + i + "단 =====");
            while (j < 10) {
                System.out.println(i + " * " + j + " = " + (i * j++));
            }
            i++;
        }
    }

    public static void exam12() {
        int i = 3;
        while (true) {
            if (i == 0) break;
            System.out.println(i--);
        }
    }

    public static void exam13() {
        int i = 10;
        while (--i > 0) {
            System.out.println(i);
        }

        System.out.println("=========");

        i = 10;
        while (i-- > 0) {
            System.out.println(i);
        }
    }

    public static void practice6() {
        System.out.println("n까지의 합");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("숫자를 입력하세요. (0 입력 시 종료)");
            int n = sc.nextInt();
            if (n == 0) break;
            int result = 0;
            for (int i = 1; i <= n; i++) {
                result = result + i;
            }
            System.out.println("답: " + result);
        }
    }

    public static void practice7() {
        System.out.println("구구단 n단");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("숫자를 입력하세요. (0 입력 시 종료)");
            int n = sc.nextInt();
            if (n == 0) {
                System.out.println("종료");
                break;
            }

            System.out.println("===== " + n + "단 =====");
            for (int i = 1; i <= 9; i++) {
                System.out.println(n + " * " + i + " = " + (n * i));
            }
        }
    }

    public static void exam14() {
        int i = 2;
        while (i <= 10) {
            System.out.println(i);
            i += 2;
        }
    }

    // * do-while문 : 최소한 한번은 수행될 것을 보장한다.
    /*
           do {
                // 조건식의 연산결과가 참일 때 수행될 문장들을 적는다.
           } while (조건식)
           ...
     */
    public static void exam15() {
        int i = 0;

        while ( i != 0 ) {
            System.out.println("while문입니다.");
        }

        do {
            System.out.println("do-while문입니다.");
        } while ( i != 0 );

    }

    public static void main(String[] args) {
        exam15();
    }
}
