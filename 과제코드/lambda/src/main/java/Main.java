import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    static void main() {
        System.out.println("===== 1. 익명 클래스 vs 람다 (같은 동작) =====");
        Operation add = new Operation() {
            @Override
            public int apply(int a, int b) {
                return a + b;
            }
        };
        System.out.println("익명 클래스 add: " + add.apply(3,4));

        add = (a, b) -> a + b;
        System.out.println("람다 add: " + add.apply(3,4));

        System.out.println();

        Operation add1 = new Operation() {
            @Override
            public int apply(int a, int b) { return a + b; }
        };
        Operation add2 = (a, b) -> { return a + b; };
        Operation add3 = (a, b) -> a + b;
        Operation sub = (a, b) -> a - b;
        Operation mul = (a, b) -> a * b;

        System.out.println("===== 2. 람다로 만든 연산들 =====");
        System.out.println("3 + 4 = " + add3.apply(3,4));
        System.out.println("9 - 2 = " + sub.apply(9,2));
        System.out.println("3 * 5 = " + mul.apply(3,5));

        System.out.println();

        System.out.println("===== 3. 매개변수 개수별 람다 =====");
        Runnable hello = () -> System.out.println("(0개) 안녕하세요, 람다!");
        hello.run();

        Printer log = msg -> System.out.println("(1개) [로그] " + msg);  // 괄호 생략
        log.print("시작합니다");

        System.out.println("(2개) 10 + 20 = " + add.apply(10, 20));

        System.out.println();

        System.out.println("===== 4. 실전: Comparator로 길이순 정렬 =====");
        ArrayList<String> names = new ArrayList<>(Arrays.asList("가나다", "가", "라마"));
        System.out.println("정렬 전: " + names);
        names.sort((a, b) -> a.length() - b.length());
        System.out.println("정렬 후: " + names);

        System.out.println();

        Operation div = (a, b) -> a / b;
//      Exception in thread "main" java.lang.ArithmeticException: / by zero
//      System.out.println(div.apply(3,0));

        names.sort((a, b) -> b.length() - a.length());
        System.out.println("내림차순: " + names);

//        names.sort((a, b) -> a.compareTo(b));
        names.sort(String::compareTo);
        System.out.println("가나다순: "  + names);

        Predicate<String> test1 = s -> s.isEmpty();
        System.out.println("문자열이 비었나요?: " + test1.test(""));
        Predicate<String> test2 = String::isEmpty;
        System.out.println("문자열이 비었나요?: " + test2.test("dd"));
    }
}
