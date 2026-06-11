import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("요금제를 선택하세요.");
        System.out.println("[1]Lite:10 [2]Basic:20 [3]Premium:30");
        int plan = Integer.parseInt(sc.nextLine());
        MemberManager manager = new MemberManager(plan * 10);

        while (true) {
            System.out.println("[수행할 업무 - 현재 회원 수 : " + manager.getCount() + "/" + manager.getCapacity() + "]");
            System.out.println("[1]회원추가 [2]회원조회(메일) [3]회원조회(이름)");
            System.out.println("[4]전체조회 [5]수정 [6]삭제 [7]종료");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    if (manager.isFull()) {
                        System.out.println("회원이 꽉 찼습니다.");
                    } else {
                        System.out.println("등급 선택 - [1]일반 [2]VIP");
                        int grade = Integer.parseInt(sc.nextLine());

                        System.out.print("이름 > ");   String name  = sc.nextLine();
                        System.out.print("이메일 > "); String email = sc.nextLine();
                        System.out.print("연락처 > "); String phone = sc.nextLine();

                        if (manager.existsEmail(email)) {
                            System.out.println("이미 존재하는 회원입니다.");
                        } else {
                            Member m = (grade == 2) ? new VipMember(name, email, phone)
                                    : new NormalMember(name, email, phone);
                            manager.add(m);
                        }
                    }
                    break;
                case 2:
                    System.out.print("회원 이메일 입력 > ");
                    String email = sc.nextLine();
                    Member m = manager.findByEmail(email);
                    if (m == null) System.out.println("찾으시는 회원이 없습니다.");
                    else m.printInfo();
                    break;
                case 3:
                    System.out.print("회원 이름 입력 > ");
                    String name = sc.nextLine();
                    m = manager.findByName(name);
                    if (m == null) System.out.println("찾으시는 회원이 없습니다.");
                    else m.printInfo();
                    break;
                case 4:
                    if (manager.getCount() == 0) System.out.println("회원이 없습니다.");
                    else manager.printAll();
                    break;
                case 5:
                    System.out.print("회원 이메일 입력 > ");
                    email = sc.nextLine();
                    System.out.print("바꿀 이메일 입력 > ");
                    String newEmail = sc.nextLine();
                    System.out.print("바꿀 이름 입력 > ");
                    String newName = sc.nextLine();
                    System.out.print("바꿀 번호 입력 > ");
                    String newPhone = sc.nextLine();
                    if (manager.update(email, newName, newEmail,  newPhone)) System.out.println("수정 완료");
                    else System.out.println("찾으시는 회원이 없습니다.");
                    break;
                case 6:
                    System.out.print("회원 이메일 입력 > ");
                    email = sc.nextLine();
                    if (manager.delete(email)) System.out.println("삭제 완료");
                    else System.out.println("찾으시는 회원이 없습니다.");
                    break;
                case 7:
                    System.out.println("종료합니다.");
                    return;
            }
        }
    }
}
