package org.assignment;

import java.time.LocalDate;
import java.util.*;

public class AccountBookImpl implements AccountBook {
    private Map<String, List<Item>> data = new HashMap<>();
    private Scanner sc =  new Scanner(System.in);

    public void addAccount()  {
        System.out.println("년 입력");
        int year = sc.nextInt();
        System.out.println("월 입력");
        int month = sc.nextInt();
        System.out.println("일 입력");
        int day = sc.nextInt();
        String date = LocalDate.of(year, month, day).toString();
        System.out.println("입력된 날짜: " + date);

        data.computeIfAbsent(date, k -> new ArrayList<>());

        while (true) {
            System.out.print("항목 명을 입력해주세요: ");
            String name = sc.next();
            System.out.print("금액을 입력해주세요: ");
            int price = 0;
            while (true) {
                try {
                    price = sc.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("숫자를 입력해주세요.");
                    sc.nextLine();
                }
            }
            data.get(date).add(new Item(name, price));

            System.out.print("추가 하시겠습니까?(y/n): ");
            String choice;
            while (true) {
                choice = sc.next();
                if (choice.equals("n") || choice.equals("y")) break;
            }
            if (choice.equals("n")) break;
        }

        int total = data.get(date).stream().map(Item::getPrice).reduce(0, Integer::sum);

        System.out.println("[" + date + "] 등록 완료");
        for (Item item : data.get(date)) {
            System.out.println(item.getName() + ": " +  item.getPrice() + "원");
        }
        System.out.println("합계: " + total + "원");
    }
    public void showAccount() {
        List<String> dates = new ArrayList<>(data.keySet());
        Collections.sort(dates, Collections.reverseOrder());
        System.out.println("===== 기록된 날짜 =====");

        if (data.isEmpty()) {
            System.out.println("비어있습니다.");
            return;
        }

        for (String d : dates) System.out.println(d);

        System.out.print("조회할 날짜 입력: ");
        String date = sc.next();

        if (!data.containsKey(date)) System.out.println("그런 날짜가 없습니다.");
        else {
            int total = 0;
            for (Item item : data.get(date)) {
                System.out.println(item.getName() + ": " + item.getPrice() + "원");
                total += item.getPrice();
            }
            System.out.println("합계: " + total + "원");
        }
    }
    public void deleteAll()   {
        if (data.isEmpty()) {
            System.out.println("비어있습니다.");
            return;
        }

        List<String> dates = new ArrayList<>(data.keySet());
        Collections.sort(dates, Collections.reverseOrder());
        System.out.println("===== 기록된 날짜 =====");
        for (String d : dates) System.out.println(d);

        System.out.print("삭제할 날짜 입력: ");
        String date = sc.next();

        if (!data.containsKey(date)) System.out.println("그런 날짜가 없습니다.");
        else {
            data.remove(date);
            System.out.println("삭제되었습니다.");
        }
    }
    public void deleteItem()  {
        if (data.isEmpty()) {
            System.out.println("비어있습니다.");
            return;
        }

        List<String> dates = new ArrayList<>(data.keySet());
        Collections.sort(dates, Collections.reverseOrder());
        System.out.println("===== 기록된 날짜 =====");
        for (String d : dates) System.out.println(d);

        System.out.print("삭제할 날짜 입력: ");
        String date = sc.next();

        if (!data.containsKey(date)) System.out.println("그런 날짜가 없습니다.");
        else {
            System.out.println("삭제할 항목 선택");
            int i = 1;
            for (Item item : data.get(date)) {
                System.out.println(i++ + ". " + item.getName() + ": " + item.getPrice() + "원");
            }
            i = sc.nextInt();
            data.get(date).remove(i-1);
            if (data.get(date).isEmpty()) data.remove(date);
        }
    }
}
