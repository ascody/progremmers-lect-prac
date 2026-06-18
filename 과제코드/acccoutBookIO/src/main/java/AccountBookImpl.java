import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class AccountBookImpl implements AccountBook {
    private final String DIR = "accountbook";
    private Scanner sc = new Scanner(System.in);

    public AccountBookImpl() {
        File folder = new File(DIR);
        if (!folder.exists()) folder.mkdir(); // 폴더 없으면 생성
    }

    public void addAccount()    {
        String today = LocalDate.now().toString();
        File file = new File(DIR, today + ".txt");

        int total = 0;
        StringBuilder sb = new StringBuilder();
        while (true) {
            System.out.print("이름 입력: ");
            String name = sc.nextLine();

            System.out.print("금액 입력: ");
            int price = 0;
            while (true) {
                try {
                    price = Integer.parseInt(sc.nextLine());
                    break;
                } catch (Exception e) {
                    System.out.println("숫자를 입력해주세요.");
                }
            }

            total += price;
            sb.append(name + ": " + price + "원\n");

            System.out.print("더 추가 하시겠습니까?(y/n): ");
            String choice;
            while (true) {
                choice = sc.nextLine();
                if (choice.equals("n") || choice.equals("y")) break;
            }
            if (choice.equals("n")) break;
            else sc.nextLine();
        }

        try (FileWriter fw = new FileWriter(file, true)) { // true = 이어쓰기
            fw.write(sb.toString());
            System.out.println("저장 완료");
        } catch (IOException e) {
            System.out.println("저장 중 오류: " + e.getMessage());
        }
    }
    public void showAccount()   {
        File folder = new File(DIR);
        String[] files = folder.list();           // 파일명 배열

        if (files == null ||  files.length == 0){
            System.out.println("비어있습니다.");
            return;
        }
        for (String name : files) {
            if (name.endsWith(".txt"))
                System.out.println(name.replace(".txt", "")); // 날짜만
        }

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
