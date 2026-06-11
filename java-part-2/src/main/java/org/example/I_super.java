package org.example;

// * super
// super는 자식 클래스에서 조상 클래스로부터 상속받은 멤버를 참조하는데 사용되는 참조 변수이다.
// 멤버변수와 지역변수 이름이 같을 때 this를 붙여 구별하였듯이 상속받은 멤버와 자신의 멤버가 이름이 같을 때는
// super를 붙여서 구별할 수 있다.

class SuperParent {
    int x = 10;
    int y = 20;

    public String getLocation() {
        return "x = " + x + ", y = " + y;
    }
}

class SuperChild extends SuperParent {
    int y = 30;
    int z = 40;

    void method() {
        System.out.println( "x = " + x );
        System.out.println("this.x = " + this.x );
        System.out.println("super.x = " + super.x );

        System.out.println( "y = " + y );
        System.out.println("this.y = " + this.y );
        System.out.println("super.y = " + super.y );
    }

    @Override
    public String getLocation() {
        return super.getLocation() + ", z = " + z;
    }
}

public class I_super {
    static void main(String[] args) {
        SuperChild superChild = new SuperChild();
        superChild.method();
        System.out.println(superChild.getLocation());
    }
}