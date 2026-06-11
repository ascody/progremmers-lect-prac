package org.example;



public class M_vending_machine {

    private int totalMoney;
    private M_drink[] drinks;

    public M_vending_machine() {
        totalMoney = 0;
        drinks = new M_drink[] {
                new M_coke(),  // 0
                new M_cider(), // 1
                new M_fanta(), // 2
                new M_water()  // 3
        };
    }

    public void insertMoney(int money) {
        totalMoney += money;
        System.out.println(money + "원을 넣었습니다.");
    }

    public void buy(int menuNumber) {
        M_drink drink = drinks[menuNumber - 1];

        if ( totalMoney < drink.getPrice() ) {
            System.out.println("잔돈이 부족합니다.");
            return;
        }

        totalMoney -= drink.getPrice();
        drink.dispense();
    }

    public int returnMoney() {
        int returnMoney = totalMoney;
        totalMoney = 0;

        return returnMoney;
    }

    public void printMenu() {
        System.out.println("============== 자판기 ==============");
        System.out.println("[1]콜라 : 500  [2]사이다 : 500  [3]환타 : 700  [4]물 : 200");
        System.out.println("[5]돈 넣기  [6]종료");
        System.out.println("현재 금액 : " + totalMoney);
        System.out.println("====================================");
    }
}
