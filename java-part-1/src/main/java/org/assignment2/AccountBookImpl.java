package org.assignment2;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class AccountBookImpl implements AccountBook {
    private final String DIR = "accountbook"; // 저장 폴더
    private Scanner sc = new Scanner(System.in);

    AccountBookImpl() {
        File folder = new File(DIR);
        if (!folder.exists()) folder.mkdir(); // 폴더 없으면 생성
    }

    public void addAccount()    {
        String today = LocalDate.now().toString();      // "2024-09-04"
        File file = new File(DIR, today + ".txt");

        int total = 0;
        StringBuilder sb = new StringBuilder();
        // 반복: 이름/금액 입력 → sb에 "이름 : 금액원\n" 추가, total += 금액
        // 반복 끝나면 sb에 "합계 : total원\n" 추가
        while (true) {
            System.out.print("이름 입력: ");
            String name = sc.nextLine();

            System.out.print("금액 입력: ");
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

            total += price;
            sb.append(name + ": " + price + "원\n");

            System.out.print("추가 하시겠습니까?(y/n): ");
            String choice;
            while (true) {
                choice = sc.next();
                if (choice.equals("n") || choice.equals("y")) break;
            }
            if (choice.equals("n")) break;
        }

        try (FileWriter fw = new FileWriter(file, true)) { // true = 이어쓰기
            fw.write(sb.toString());
        } catch (IOException e) {
            System.out.println("저장 중 오류: " + e.getMessage());
        }
    }
    public void showAccount()   {
        File folder = new File(DIR);
        String[] files = folder.list();           // 파일명 배열
        // files 가 null 이거나 length==0 이면 기록 없음

        if (files == null ||  files.length == 0){
            System.out.println("비어있습니다.");
            return;
        }
        for (String name : files) {
            if (name.endsWith(".txt"))
                System.out.println(name.replace(".txt", "")); // 날짜만
        }

        // 날짜 입력 후 읽기
        System.out.println("날짜 입력");
        String date = sc.nextLine();
        File file = new File(DIR, date + ".txt");
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("오류가 발생했습니다: " + e.getMessage());
            }
        }
    }
    public void deleteAccount() {
        File folder = new File(DIR);
        String[] files = folder.list();

        for (String name : files) {
            if (name.endsWith(".txt"))
                System.out.println(name.replace(".txt", "")); // 날짜만
        }

        System.out.println("날짜 입력");
        String date = sc.nextLine();
        File file = new File(DIR, date + ".txt");

        if (file.exists()) {
            if (file.delete()) System.out.println("삭제되었습니다");
            else               System.out.println("삭제 실패");
        } else {
            System.out.println("그런 날짜가 없습니다");
        }
    }
}
