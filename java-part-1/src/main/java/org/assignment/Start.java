package org.assignment;

import java.util.Scanner;

public class Start {
    static Scanner sc = new Scanner(System.in);
    static AccountBook book = new AccountBookImpl();

    public static void main(String[] args) {
        while(true) {
            System.out.println("===== 가계부 =====");
            System.out.println("메뉴를 선택해주세요.");
            System.out.println("[1]내역 추가 [2]내역 조회 [3]전체 삭제 [4]내역 삭제 [5]종료");
            System.out.println("===================");

            int menu = 0;
            while (true) {
                try {
                    menu = sc.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("숫자를 입력해주세요.");
                    sc.nextLine();
                }
            }
            switch (menu) {
                case 1: book.addAccount(); break;
                case 2: book.showAccount(); break;
                case 3: book.deleteAll(); break;
                case 4: book.deleteItem(); break;
                case 5:
                    System.out.println("종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 번호입니다.");
            }
        }
    }
}
