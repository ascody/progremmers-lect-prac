import java.util.Scanner;

public class Main {
    static int readInt(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("[1]Lite:10 [2]Basic:20 [3]Premium:30");
        PricePlan plan = null;
        while (plan == null) {
            plan = PricePlan.from(readInt(sc));
            if (plan == null) System.out.println("1~3 중에서 선택하세요.");
        }
        MemberManager manager = new MemberManager(plan.getCapacity());

        while (true) {
            System.out.println("\n[현재 " + manager.size() + "/" + manager.capacity() + "]");
            System.out.println("[1]추가 [2]메일조회 [3]이름조회 [4]전체 [5]수정 [6]삭제 [7]종료");
            int menu = readInt(sc);

            switch (menu) {
                case 1: {
                    if (manager.isFull()) { System.out.println("정원이 찼습니다."); break; }
                    System.out.println("등급 [1]일반 [2]VIP");
                    int grade = readInt(sc);
                    System.out.print("이름 > ");   String name  = sc.nextLine();
                    System.out.print("이메일 > "); String email = sc.nextLine();
                    System.out.print("연락처 > "); String phone = sc.nextLine();
                    if (manager.existsEmail(email)) { System.out.println("이미 있는 회원입니다."); break; }
                    Member m = (grade == 2)
                            ? new VipMember(name, email, phone)
                            : new NormalMember(name, email, phone);
                    try {
                        manager.add(m);
                    } catch (DuplicateEmailException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("추가되었습니다.");
                    break;
                }
                case 2: {
                    System.out.print("이메일 > "); String email = sc.nextLine();
                    Member m = manager.findByEmail(email);
                    if (m == null) {
                        System.out.println("존재하지 않는 회원입니다.");
                    } else {
                        m.printInfo();
                    }
                    break;
                }
                case 3: {
                    System.out.print("이름 > ");   String name  = sc.nextLine();
                    Member m = manager.findByName(name);
                    if (m == null) {
                        System.out.println("존재하지 않는 회원입니다.");
                    } else {
                        m.printInfo();
                    }
                    break;
                }
                case 4: {
                    manager.printAll();
                    break;
                }
                case 5: {
                    System.out.print("이메일 > "); String email = sc.nextLine();
                    System.out.print("새로운 이메일 > "); String newEmail = sc.nextLine();
                    System.out.print("새로운 이름 > ");   String name  = sc.nextLine();
                    System.out.print("새로운 연락처 > "); String phone = sc.nextLine();
                    if (manager.update(email, name, newEmail, phone)) {
                        System.out.println("수정되었습니다.");
                    } else {
                        System.out.println("존재하지 않는 회원입니다.");
                    }
                }
                case 6: {
                    System.out.print("이메일 > "); String email = sc.nextLine();
                    if (manager.delete(email)) {
                        System.out.println("삭제되었습니다.");
                    } else {
                        System.out.println("존재하지 않는 회원입니다.");
                    }
                }
                case 7: System.out.println("이용해주셔서 감사합니다."); return;
                default: System.out.println("1~7 중에서 선택하세요.");
            }
        }
    }
}
