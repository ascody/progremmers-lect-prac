import java.util.HashMap;
import java.util.Map;

public class MemberManager implements MemberRepository{
    private final Map<String, Member> members = new HashMap<>();
    private final int capacity;

    public MemberManager(int capacity) {
     this.capacity = capacity;
    }
    public boolean isFull() {
     return this.members.size() >= this.capacity;
    }
    public boolean existsEmail(String email) {
        return this.members.containsKey(email);
    }
    public int size() { return this.members.size(); }
    public int capacity() { return this.capacity; }

    @Override
    public void add(Member member) throws DuplicateEmailException{
        members.put(member.getEmail(), member);
    }
    @Override
    public Member findByEmail(String email) {
        return this.members.getOrDefault(email, null);
    }
    @Override
    public Member findByName(String name) {
        for (Member member : this.members.values()) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }
    @Override
    public boolean update(String email, String name, String newEmail, String phone) {
        Member m = findByEmail(email);
        if (m == null) return false;
        m.update(name, newEmail, phone);
        return true;
    }
    @Override
    public boolean delete(String email) {
        Member m = findByEmail(email);
        if (m == null) return false;
        members.remove(m);
        return true;
    }
    public void printAll() {
        if (members.isEmpty()) { System.out.println("등록된 회원이 없습니다."); return; }
        for (Member m : members.values()) m.printInfo();
    }
}
