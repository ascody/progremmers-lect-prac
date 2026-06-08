package org.bingo;


import java.util.*;

public class BingoGame {
    static final int TARGET = 3;
    static final int SIZE = 5;
    static final int MAX = 25;
    boolean[] called = new boolean[MAX + 1];   // 1~25
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    void makeBoard(int[][] board) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= MAX; i++) nums.add(i);
        Collections.shuffle(nums);

        int idx = 0;
        for (int r = 0; r < SIZE; r++)
            for (int c = 0; c < SIZE; c++)
                board[r][c] = nums.get(idx++);
    }

    void printBoard(int[][] board, boolean[][] marked) {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (marked[r][c]) System.out.print("[ ★] ");
                else              System.out.printf("[%2d] ", board[r][c]);
            }
            System.out.println();   // 한 줄 끝나면 줄바꿈
        }
    }

    void mark(int[][] board, boolean[][] marked, int num) {
        for (int r = 0; r < SIZE; r++)
            for (int c = 0; c < SIZE; c++)
                if (board[r][c] == num) {
                    marked[r][c] = true;
                    return;
                }
    }

    int countBingo(boolean[][] marked) {
        int count = 0;

        // 가로
        for (int r = 0; r < SIZE; r++) {
            boolean all = true;
            for (int c = 0; c < SIZE; c++) if (!marked[r][c]) all = false;
            if (all) count++;
        }
        // 세로
        for (int c = 0; c < SIZE; c++) {
            boolean all = true;
            for (int r = 0; r < SIZE; r++) if (!marked[r][c]) all = false;
            if (all) count++;
        }
        // 대각선 ＼
        boolean d1 = true;
        for (int i = 0; i < SIZE; i++) if (!marked[i][i]) d1 = false;
        if (d1) count++;
        // 대각선 ／
        boolean d2 = true;
        for (int i = 0; i < SIZE; i++) if (!marked[i][SIZE - 1 - i]) d2 = false;
        if (d2) count++;

        return count;
    }

    int playerPick() {
        while (true) {
            System.out.print("부를 숫자 입력 (1~25) > ");
            int num;
            try {
                num = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력하세요."); continue;
            }
            if (num < 1 || num > MAX)      System.out.println("1~25 사이로 입력하세요.");
            else if (called[num])          System.out.println("이미 부른 숫자입니다.");
            else {
                called[num] = true;
                return num;
            }
        }
    }

    int computerPick() {
        int num;
        do { num = rand.nextInt(MAX) + 1; } while (called[num]);
        called[num] = true;
        return num;
    }

    void callNumber(int num, String who) {
        System.out.println(who + " " + num + "번을 골랐습니다.");
    }

    boolean checkWin(boolean[][] board) {
        return countBingo(board) >= TARGET;
    }

    public void play() {
        System.out.println("===== 빙고 게임 =====");
        System.out.println("컴퓨터와 번갈아 숫자를 불러 빙고를 완성하세요!");

        int[][] playerBoard = new int [SIZE][SIZE];
        int[][] computerBoard = new int [SIZE][SIZE];
        boolean[][] playerMarked = new boolean[SIZE][SIZE];
        boolean[][] computerMarked = new boolean[SIZE][SIZE];

        makeBoard(playerBoard);
        makeBoard(computerBoard);


        boolean playerWin = false;
        boolean computerWin = false;
        while (true) {
            System.out.println("\n===== 내 빙고판 =====");
            printBoard(playerBoard, playerMarked);

            // 내 차례
            int num = playerPick();
            mark(playerBoard, playerMarked, num);
            mark(computerBoard, computerMarked, num);
            callNumber(num, "내가");
            if (checkWin(playerMarked)) {
                playerWin = true;
            }

            // 컴퓨터 차례
            int cNum = computerPick();
            mark(playerBoard, playerMarked, cNum);
            mark(computerBoard, computerMarked, cNum);
            callNumber(cNum, "컴퓨터가");
            if (checkWin(computerMarked)) {
                computerWin = true;
            }

            if  (playerWin || computerWin) break;

            System.out.println("\n현재 빙고 줄  → 나: " + countBingo(playerMarked)
                    + "줄,  컴퓨터: " + countBingo(computerMarked) + "줄");
        }

        System.out.println("\n====================");
        if (playerWin &&  computerWin) System.out.println("무승부!");
        else if (playerWin) System.out.println("당신의 승리!");
        else System.out.println("컴퓨터의 승리!");
        System.out.println("\n=====당신의 빙고판=====");
        printBoard(playerBoard, playerMarked);
        System.out.println("줄 수: " + countBingo(playerMarked) + "줄");
        System.out.println("\n=====컴퓨터의 빙고판=====");
        printBoard(computerBoard, computerMarked);
        System.out.println("줄 수: " + countBingo(computerMarked) + "줄");
    }
}
