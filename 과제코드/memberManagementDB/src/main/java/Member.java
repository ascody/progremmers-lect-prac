public interface Member {
    String getName();
    String getEmail();
    String getPhone();
    String getGrade();
    String getBenefit();

    void update(String name, String email, String phone);

    default void printInfo() {   // 다른 메서드만 써서 구현 → 인터페이스에 둘 수 있음
        System.out.println("[" + getGrade() + "] " + getName() + " / "
                + getEmail() + " / " + getPhone() + " (혜택: " + getBenefit() + ")");
    }
}
