public class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> hashMap = new MyHashMap<>();

        hashMap.put("apple", 100);
        System.out.println(hashMap.get("apple"));
        hashMap.put("apple", 200);
        System.out.println(hashMap.get("apple"));

        for (int i = 0; i < 100; i++) {
            hashMap.put(i+"", i);
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(hashMap.get(i+""));
        }

        System.out.println(hashMap.get("banana"));
        System.out.println(hashMap.size());
        System.out.println(hashMap.remove("apple"));
        System.out.println(hashMap.size());
    }
}
