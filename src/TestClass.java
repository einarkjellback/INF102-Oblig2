import edu.princeton.cs.algs4.Bag;

public class TestClass {
    public static void main(String[] args) {
        Bag<Integer> bag = new Bag<Integer>();
        bag.add(123);
        for (Integer n : bag) {
            System.out.println(n);
        }
    }
}
