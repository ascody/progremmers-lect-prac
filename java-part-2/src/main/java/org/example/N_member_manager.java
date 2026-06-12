package org.example;

public class N_member_manager {
    private N_member[] members;
    private int memberCnt;

    N_member_manager(int capacity) {
        members = new N_member[capacity];
        memberCnt = 0;
    }

    public boolean isFull() {
        return memberCnt == members.length;
    }

    public boolean existsEmail(String email) {
        for (int i = 0; i < memberCnt; i++) {
            if (members[i].getEmail().equals(email)) return true;
        }
        return false;
    }

    public void add(N_member m) {
        members[memberCnt] = m;
        memberCnt++;
    }

    public int getCount()    { return memberCnt; }
    public int getCapacity() { return members.length; }

    public N_member findByEmail(String email) {
        for (int i = 0; i < memberCnt; i++) {
            if (members[i].getEmail().equals(email)) return members[i];
        }
        return null;
    }

    public N_member findByName(String name) {
        for (int i = 0; i < memberCnt; i++) {
            if (members[i].getName().equals(name)) return members[i];
        }
        return null;
    }

    public void printAll() {
        for (int i = 0; i < memberCnt; i++) {
            members[i].printInfo();
        }
    }

    public boolean update(String email, String name, String newEmail, String phone) {
        N_member m = this.findByEmail(email);
        if (m == null) return false;
        m.update(name, newEmail, phone);
        return true;
    }

    public boolean delete(String email) {
        int idx = -1;
        for (int i = 0; i < memberCnt; i++) {
            if (members[i].getEmail().equals(email)) {
                idx = i;
                break;
            }
        }
        if (idx == -1) return false;

        for (int i = idx; i < memberCnt - 1; i++) {
            members[i] = members[i + 1];
        }
        memberCnt--;
        return true;
    }
}
