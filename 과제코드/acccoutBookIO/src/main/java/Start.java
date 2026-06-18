import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AccountBook book = new AccountBookImpl();
        while (true) {
            System.out.println("===== 가계부 (File) =====");
            System.out.println("메뉴를 선택해주세요.");
            System.out.println("[1]내역 추가 [2]내역 조회 [3]삭제 [4]종료");
            int menu = 0;
            while (true) {
                try {
                    menu = Integer.parseInt(sc.nextLine());
                    break;
                } catch (Exception e) {
                    System.out.println("숫자를 입력해주세요.");
                }
            }
            switch (menu) {
                case 1: book.addAccount(); break;
                case 2: book.showAccount(); break;
                case 3: book.deleteAccount(); break;
                case 4: System.out.println("종료합니다"); return;
                default: System.out.println("잘못된 번호입니다");
            }
        }
    }
}
