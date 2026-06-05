package org.example;

import java.util.Scanner;

public class Z_assignment {
    static final int COKE = 500, CIDER = 700, FANTA = 300, WATER = 200;
    static Scanner sc = new Scanner(System.in);

    public static void printMenu(int totalMoney) {
        System.out.println("================================= 자판기 ================================");
        while (true) {
            System.out.println("[1]콜라-500원 [2]사이다-700원 [3]환타-300원 [4]물-200원 [5]돈넣기 [6]종료");
            int choice = sc.nextInt();

            if (choice == 1) {
                if (totalMoney < 500) {
                    System.out.println("돈이 부족합니다.");
                } else {
                    System.out.println("콜라가 나왔습니다.");
                    totalMoney -= 500;
                }
            }
            else if (choice == 2) {
                if  (totalMoney < 700) {
                    System.out.println("돈이 부족합니다.");
                } else {
                    System.out.println("사이다가 나왔습니다.");
                    totalMoney -= 700;
                }
            }
            else if (choice == 3) {
                if  (totalMoney < 300) {
                    System.out.println("돈이 부족합니다.");
                } else {
                    System.out.println("환타가 나왔습니다.");
                    totalMoney -= 300;
                }
            }
            else if (choice == 4) {
                if  (totalMoney < 200) {
                    System.out.println("돈이 부족합니다.");
                } else {
                    System.out.println("물이 나왔습니다.");
                    totalMoney -= 200;
                }
            }
            else if (choice == 5) {
                System.out.print("돈을 넣어주세요: ");
                int add = sc.nextInt();
                totalMoney += add;
            }
            else if (choice == 6) {
                break;
            }

            System.out.println("현재 금액 : " + totalMoney + "원");
        }
        System.out.println("==========================================================================");
    }

    public static void main(String[] args) {
        System.out.println("자판기에 돈을 넣어주세요.");
        printMenu(sc.nextInt());
    }
}
