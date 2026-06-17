public class Race {
    private volatile boolean over = false;          // 모두가 함께 보는 값

    public boolean isOver() { return over; }

    public synchronized void finish(String name) {  // 한 번만 선언되도록 보호
        if (!over) {
            over = true;
            System.out.println("\n*** 우승: " + name + " ***");
        }
    }
}
