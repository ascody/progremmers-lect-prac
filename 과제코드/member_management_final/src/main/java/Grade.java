public enum Grade {
    NORMAL("기본 서비스"), VIP("10% 할인 + 무료배송");
    private final String benefit;
    Grade(String benefit) {
        this.benefit = benefit;
    }
    public String getBenefit() {
        return benefit;
    }
    public static Grade from(int choice) {
        return switch (choice) {
            case 1 -> NORMAL;
            case 2 -> VIP;
            default -> null;
        };
    }
}
