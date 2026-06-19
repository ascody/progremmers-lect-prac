public enum PricePlan {
    LITE(10), BASIC(20), PREMIUM(30);
    private final int capacity;
    PricePlan(int capacity) {
        this.capacity = capacity;
    }
    public int getCapacity() {
        return capacity;
    }
    public static PricePlan from(int choice) {
        return switch (choice) {
            case 1 -> LITE;
            case 2 -> BASIC;
            case 3 -> PREMIUM;
            default -> null;
        };
    }
}