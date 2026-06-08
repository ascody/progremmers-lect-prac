package org.example;

import java.util.Scanner;

public class K_member_management {

    static int totalCnt = 0;   // 정원 (요금제로 결정)
    static int memberCnt = 0;  // 현재 회원 수 (= 다음에 채울 칸의 인덱스)

    public static int printPricePlan() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[요금제를 선택하세요]");
        System.out.println("[1]Lite : 10명 | [2]Basic : 20명 | [3]Premium : 30명");
        return sc.nextInt();
    }

    private static int printMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[수행할 업무를 선택하세요 - 현재 회원수 : " + memberCnt + "/" + totalCnt + "]");
        System.out.println("[1]회원추가 [2]회원조회(메일) [3]회원조회(이름)");
        System.out.println("[4]회원전체조회 [5]회원정보 수정 [6]회원삭제");
        System.out.println("[7]프로그램 종료");

        int menu;
        while (true) {
            try {
                menu = sc.nextInt();
                if (menu < 1 || menu > 7) {
                    System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                }  else break;
            } catch (Exception e) {
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                sc.nextLine();
            }
        }

        return menu;
    }

    // 1. 회원 추가
    public static void addMember(String[][] members) {
        if (memberCnt == totalCnt) {
            System.out.println("회원이 꽉 찼습니다.");
            return;
        }
        Scanner sc = new Scanner(System.in);

        System.out.println("이름 입력: ");
        String name = sc.nextLine();
        System.out.println("이메일 입력: ");
        String email = sc.nextLine();
        System.out.println("전화번호 입력: ");
        String phone = sc.nextLine();

        if (checkEmail(members, email)) {
            System.out.println("이미 존재하는 회원입니다.");
            return;
        }

        members[memberCnt][0] = name;
        members[memberCnt][1] = email;
        members[memberCnt][2] = phone;
        memberCnt++;
    }

    // 2. 회원 조회 (메일)
    public static boolean checkEmail(String[][] members, String email) {
        for (String[] member : members) {
            if (email.equals(member[1])) return true; // 이미 있음
        }
        return false;
    }

    public static void selectEmail(String[][] members) {
        Scanner sc = new Scanner(System.in);
        System.out.println("[조회] 이메일 입력: ");
        String email = sc.nextLine();

        for (String[] member : members) {
            if (email.equals(member[1])) {
                System.out.println("[이름] " + member[0]);
                System.out.println("[이메일] " + member[1]);
                System.out.println("[전화번호] " + member[2]);
                return;
            }
        }
        System.out.println("찾으시는 정보가 없습니다.");
    }

    // 3. 회원 조회 (이름)
    public static void selectName(String[][] members) {
        Scanner sc = new Scanner(System.in);
        System.out.println("[조회] 이름 입력: ");
        String name = sc.nextLine();

        for (String[] member : members) {
            if (name.equals(member[0])) {
                System.out.println("[이름] " + member[0]);
                System.out.println("[이메일] " + member[1]);
                System.out.println("[전화번호] " + member[2]);
                return;
            }
        }
        System.out.println("찾으시는 정보가 없습니다.");
    }

    // 4. 회원 전체 조회
    public static void selectAll(String[][] members) {
        if (memberCnt == 0) {
            System.out.println("회원이 없습니다.");
            return;
        }

        for (int i = 0; i < memberCnt; i++) {
            System.out.println("[이름] " + members[i][0] + " | [이메일] " + members[i][1] +  " | [전화번호] " + members[i][2]);
        }
    }

    // 5. 회원 정보 수정
    // -> 이메일을 입력 -> 수정 이름, 이메일, 연락처
    public static void updateMember(String[][] members) {
        Scanner sc = new Scanner(System.in);
        System.out.println("[수정] 이메일 입력: ");
        String email = sc.nextLine();

        int idx = -1;
        for (int i = 0; i < memberCnt; i++) {
            if (members[i][1].equals(email)) {
                idx = i;
            }
        }

        if (idx == -1) {
            System.out.println("찾으시는 회원이 없습니다.");
            return;
        }

        System.out.println("변경할 이름 입력: ");
        String name = sc.nextLine();
        System.out.println("변경할 이메일 입력: ");
        email = sc.nextLine();
        System.out.println("변경할 전화번호 입력: ");
        String phone = sc.nextLine();

        members[idx][0] = name;
        members[idx][1] = email;
        members[idx][2] = phone;
        System.out.println("수정이 완료되었습니다.");
    }

    // 6. 회원 삭제
    private static void deleteMember(String[][] members) {
        Scanner sc = new Scanner(System.in);
        System.out.println("[삭제] 이메일 입력: ");
        String email = sc.nextLine();

        int idx = -1;
        for (int i = 0; i < memberCnt; i++) {
            if (members[i][1].equals(email)) {
                idx = i;
            }
        }

        if (idx == -1) {
            System.out.println("찾으시는 회원이 없습니다.");
            return;
        }

        for (int i = idx; i < members.length - 1; i++) {
            members[i][0] = members[i + 1][0];
            members[i][1] = members[i + 1][1];
            members[i][2] = members[i + 1][2];
        }
        members[memberCnt][0] = null;
        members[memberCnt][1] = null;
        members[memberCnt][2] = null;
        memberCnt--;

        System.out.println("삭제가 완료되었습니다.");
    }

    public static void main(String[] args) {
        int num = printPricePlan();
        String[][] members = new String[num * 10][3];
        totalCnt = num * 10;
        while (true) {
            int choice = printMenu();
            switch (choice) {
                case 1: addMember(members); break;
                case 2: selectEmail(members); break;
                case 3: selectName(members); break;
                case 4: selectAll(members);  break;
                case 5: updateMember(members); break;
                case 6: deleteMember(members); break;
                case 7: System.out.println("이용해주셔서 감사합니다."); return;
                default: System.out.println("올바른 번호를 입력하세요.");
            }
        }
    }
}
