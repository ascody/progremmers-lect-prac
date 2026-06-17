public interface MemberRepository {
    void add(Member member) throws DuplicateEmailException;
    Member findByEmail(String email);
    Member findByName(String name);
    boolean update(String email, String name, String newEmail, String phone);
    boolean delete(String email);
}
