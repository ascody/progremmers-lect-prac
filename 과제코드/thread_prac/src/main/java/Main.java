import java.util.Scanner;

class PrintDash extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++) System.out.print("-");
    }
}
class PrintBar extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++) System.out.print("|");
    }
}
class Sleep extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++) System.out.print("-");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
class Count extends Thread {
    public void run() {
        int i = 10;
        while (i != 0 && !isInterrupted()) {
            System.out.println(i--);
            for (long j = 0; j < 2_500_000_000L; j++) ;
        }
        System.out.println("카운트가 종료되었습니다.");
    }
}
class Interrupt extends Thread {
    public void run() {
        int i = 10;
        while (i != 0 && !isInterrupted()) {
            System.out.println(i--);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("자다가 깨어남! (InterruptedException)");
                break;
            }
        }
        System.out.println("카운트가 종료되었습니다.");
    }
}
class Yield extends Thread {
    private String name;
    public Yield(String name) { this.name = name; }
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + " 실행 중. 반복: " + i);
            Thread.yield();   // 남은 실행시간 양보(힌트)
            try { Thread.sleep(500); } catch (InterruptedException e) { break; }
        }
    }
}
class ManyPrint extends Thread {
    String toPrint;
    public ManyPrint(String toPrint) { this.toPrint = toPrint; }
    public void run() {
        for (int i = 0; i < 300000; i++) System.out.print(toPrint);
    }
}
public class Main {
    static void exam1() {
        PrintDash dash = new PrintDash();
        PrintBar bar = new PrintBar();
        dash.start();
        bar.start();
    }
    static void exam2(){
        Sleep t1 = new Sleep();
        t1.start();
        try {
            Thread.sleep(2000);     // 이 스레드를 2초 멈춤
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("<<종료>>");
    }
    static void exam3(){
        Count t1 = new Count();
        t1.start();
        new Scanner(System.in).nextLine();
        t1.interrupt();
    }
    static void exam4(){
        Interrupt t1 = new Interrupt();
        t1.start();
        new Scanner(System.in).nextLine();
        t1.interrupt();
    }
    static void exam5(){
        new Yield("스레드1").start();
        new Yield("스레드2").start();
    }
    static void exam6(){
        ManyPrint t1 = new ManyPrint("-");
        ManyPrint t2 = new ManyPrint("|");
        t1.start();
        t2.start();

        long start = System.currentTimeMillis();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("소요시간: " + (System.currentTimeMillis() - start) + "ms");
    }
    static void main() {
        exam6();
    }
}
