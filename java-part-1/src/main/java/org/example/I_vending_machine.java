package org.example;

import java.sql.SQLOutput;
import java.util.Scanner;

public class I_vending_machine {
    // [요구사항]
    // 사용자 메뉴 출력
    // 돈 넣기
    // 메뉴 선택 시 -> 음료가 나온다, 돈이 차감된다
    // 종료 시 잔돈이 반환된다

    static final int COKE = 500, CIDER = 700, FANTA = 300, WATER = 200;

    public static void printMenu(int totalMoney){
        System.out.println("=============================== 자판기 ==============================");
        System.out.println("[1]콜라-500원 [2]사이다-700원 [3]환타-300원 [4]물-200원 [5]돈넣기 [6]종료");
        System.out.println("현재 금액 : " + totalMoney + "원");
        System.out.println("======================================================================");
    }

    public static int getChoice(){
        Scanner sc = new Scanner(System.in);
        System.out.println("원하는 메뉴를 선택하세요.");
        return sc.nextInt();
    }

    public static int getMoney(){
        Scanner sc = new Scanner(System.in);
        System.out.println("돈을 넣으세요.");
        return sc.nextInt();
    }

    public static int calcMoney(int totalMoney, int price){
        return totalMoney - price;
    }

    public static void calcMoneyException() {
        System.out.println("잔돈이 부족합니다.");
    }

    public static void main(String[] args) {
        int totalMoney = 0;

        while (true) {
            printMenu(totalMoney);
            int choice = getChoice();
            int result = -1;
            switch (choice) {
                case 1:
                    result = calcMoney(totalMoney, COKE);
                    if (result < 0) {
                        calcMoneyException();
                    } else {
                        totalMoney = result;
                        System.out.println("콜라가 나왔습니다.");
                    }
                    break;
                case 2:
                    result = calcMoney(totalMoney, CIDER);
                    if (result < 0) {
                        calcMoneyException();
                    } else {
                        totalMoney = result;
                        System.out.println("사이다가 나왔습니다.");

                    }
                    break;
                case 3:
                    result = calcMoney(totalMoney, FANTA);
                    if (result < 0) {
                        calcMoneyException();
                    } else {
                        totalMoney = result;
                        System.out.println("환타가 나왔습니다.");
                    }
                    break;
                case 4:
                    result = calcMoney(totalMoney, WATER);
                    if (result < 0) {
                        calcMoneyException();
                    } else {
                        totalMoney = result;
                        System.out.println("물이 나왔습니다.");
                    }
                    break;
                case 5:
                    int money = getMoney();
                    if (money < 0) System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                    else totalMoney += getMoney();
                    break;
                case 6:
                    System.out.println("\n 잔돈 " + totalMoney + "원이 반환되었습니다.");
                    return;
                default:
                    System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
            }

            break;
        }
    }
}
