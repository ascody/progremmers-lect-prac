public enum PricePlan {
    LITE(10), BASIC(20), PREMIUM(30);
    private int capacity;
    PricePlan(int capacity) {
        this.capacity = capacity;
    }
    public int getCapacity() {
        return capacity;
    }
    public static PricePlan from(int choice) {
        return switch (choice) {
            case 10 -> LITE;
            case 20 -> BASIC;
            case 30 -> PREMIUM;
            default -> null;
        };
    }
}
