public class Main {
    public static void main(String[] args) {
        MyTreeMap treeMap = new MyTreeMap();

        treeMap.put("banana", 1);
        treeMap.put("apple", 2);
        treeMap.put("orange", 3);
        treeMap.put("pineapple", 4);
        treeMap.put("pear", 5);
        treeMap.printSorted();

        System.out.println("------------------------");

        treeMap.put("banana", 6);
        treeMap.printSorted();

        System.out.println("------------------------");

        System.out.println(treeMap.get("avocado"));
        System.out.println(treeMap.remove("avocado"));

        System.out.println("------------------------");

        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.lastKey());

        System.out.println("------------------------");

        System.out.println(treeMap.remove("banana"));
        treeMap.printSorted();
        treeMap.remove("pear");
        treeMap.printSorted();
        treeMap.remove("orange");
        treeMap.printSorted();
    }
}
