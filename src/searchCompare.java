public class searchCompare {
    public static void main(String[] args) {
        UBST<String, Integer> bTree = new UBST<String, Integer>();
        for (int i = 0; i < 10; i++) {
            bTree.put(Character.toString((char) ('a' + i)), i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(bTree.get(Character.toString((char) ('a' + i))));
            System.out.print(" | ");
        }
        System.out.println("");

        UTST<String, Integer> tTree = new UTST<String, Integer>();
        for (int i = 0; i < 10; i++) {
            tTree.put(Character.toString((char) ('a' + i)), i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(tTree.get(Character.toString((char) ('a' + i))));
            System.out.print(" | ");
        }
    }
}
