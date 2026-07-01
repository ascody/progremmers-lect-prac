package org.example.aop;

public class MemberServiceImpl implements MemberService {
    @Override
    public String register(String user) {
        sleep(80);                       // 실제 작업 흉내
        return "가입완료: " + user;
    }
    private void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}
