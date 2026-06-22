interface Printer { void print(); }
interface Scanner { void scan(); }
interface Faxer { void fax(); }

class SimplePrinter implements Printer {
    public void print() {
        System.out.println("구형 프린터: 인쇄");
    }
}

class SmartMachine implements Printer, Scanner {
    public void print() {
        System.out.println("복합기: 출력");
    }
    public void scan() {
        System.out.println("복합기: 스캔");
    }
}