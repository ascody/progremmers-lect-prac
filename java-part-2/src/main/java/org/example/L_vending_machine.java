package org.example;

public class L_vending_machine {
    private int totalMoney;
    private L_drink[] drinks;

    public L_vending_machine() {
        totalMoney = 0;
        drinks = new L_drink[] {
                new L_coke(),
                new L_cider(),
                new L_fanta(),
                new L_water()
        };
    }

    public void insertMoney(int money) {
        totalMoney += money;
        System.out.println(money + "원을 넣었습니다.");
    }

    public void buy(int menuNumber) {
        L_drink drink = drinks[menuNumber-1];

        if (totalMoney < drink.getPrice()) {
            System.out.println("잔돈이 부족합니다.");
        }

        totalMoney -= drink.getPrice();
        drink.dispense();
    }

    public int returnMoney() {
        int returnMoney = totalMoney;
        totalMoney = 0;
        return returnMoney;
    }

    public void printMenu(){
        System.out.println("=============================== 자판기 ==============================");
        System.out.println("[1]콜라-500원 [2]사이다-700원 [3]환타-300원 [4]물-200원 [5]돈넣기 [6]종료");
        System.out.println("현재 금액 : " + totalMoney + "원");
        System.out.println("======================================================================");
    }
}
