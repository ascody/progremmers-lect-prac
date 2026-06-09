package org.example;

// * String 클래스의 특징
//  * 불변성(Immutable)
//  String 객체가 생성되면 그 객체의 문자열 값은 변경할 수 없다.
//  문자열을 수정하려면 새로운 String 객체를 생성해야 한다.
//  * 메모리 효율성
//  같은 값을 가진 String 리터럴은 같은 메모리에서 공유된다.

public class L_string {

    // 1. charAt(int index)
    public static void exam1() {
        String str = "hello";

        for (int i = 0; i < str.length(); i++) {
            char c =  str.charAt(i);
            System.out.println(c);
        }
    }

    // 2. substring(int beginIndex, int endIndex)
    public static void exam2() {
        String str = "algorithm";
        String sub = str.substring(0,5);
        System.out.println(sub);
    }

    // 3. split(String regex)
    public static void exam3() {
        String str = "one, two, three";
        String[] parts = str.split(", ");
        for (String part : parts) {
            System.out.println(part);
        }
    }

    // 4. toCharArray()
    public static void exam4() {
        String str = "hello";
        char[] chars = str.toCharArray();
        for ( char c : chars ) {
            System.out.println(c);
        }
    }

    // 5. equals(String anotherString)
    public static void exam5() {
        String str1 = "hello1";
        String str2 = "hello2";
        System.out.println(str1.equals(str2));
    }

    // 6. contains(CharSequence s)
    public static void exam6() {
        String str = "hello";
        System.out.println(str.contains("ell"));
    }

    // 7. replace(char oldChar, char newChar) 및 replace(CharSequence target, CharSequence replacement)
    public static void exam7() {
        String str = "hello";
        String newStr = str.replace('l', 'L');
        System.out.println(newStr);
    }

    // 8. indexOf(String str) 및 lastIndexOf(String str)
    public static void exam8() {
        String str = "hello";
        int index = str.indexOf("l"); // 2
        int lastIndex = str.lastIndexOf("l"); // 3
        System.out.println(index);
        System.out.println(lastIndex);
    }

    // 9. StringBuilder 및 StringBuffer
    // StringBuilder와 StringBuffer는 가변(mutable) 문자열을 다루기 위한 클래스입니다.
    // StringBuilder는 성능이 우수하며, StringBuffer는 스레드 안전(thread-safe)한 버전입니다.
    public static void exam9() {
        StringBuilder sb = new StringBuilder();
        sb.append("hello");
        sb.append(" world");
        String result = sb.toString();
        System.out.println(result);
    }

    // 10. reverse()
    public static void exam10() {
        StringBuilder sb = new StringBuilder("hello");
        String result = sb.reverse().toString();
        System.out.println(result);
    }

    // 11. compareTo(String anotherString)
    public static void exam11() {
        String str1 = "apple";
        String str2 = "banana";

        int resultIdx = str1.compareTo(str2);
        System.out.println(resultIdx);
    }

    // 12. toLowerCase(), toUpperCase()
    public static void exam12() {
        String str = "Hello";
        String lower = str.toLowerCase();
        String upper = str.toUpperCase();
        System.out.println(lower);
        System.out.println(upper);
    }

    public static void main(String[] args) {
        exam3();
    }
}
