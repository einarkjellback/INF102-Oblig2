package classes;

import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;

public class UBST<Key extends Comparable<Key>, Value> {

  private Node root;
  private long compares = 0;

  private class Node{
    private Key key;
    private Value value;
    private Node left, right;

    public Node(Key k, Value v, Node l, Node r){
      this.key = k;
      this.value = v;
      left = l;
      right = r;
      assert key != null && value != null;
    }
  }

  public long getCompares() { return compares; }

  public boolean isEmpty() { return root == null; }

  private void show(Node r){  // depth-first left-to-right tree transversal
    if (r != null) {
      show(r.left);
      StdOut.println(r.value + "\t" + r.key);
      show(r.right);
    }
  }

  public Value get(Key k) {
    if (k == null) throw new IllegalArgumentException("Key should not be null");
    return get(k, root);
  }

  private Value get(Key k, Node r) {
    if (r == null) return null; compares++;
    int cmp = k.compareTo(r.key); compares++;
    if (cmp == 0) return r.value; compares ++;
    if (cmp < 0) return get(k, r.left); compares++;
    return get(k, r.right);
  }

  public void put(Key k, Value v) {
    if (v == null) throw new IllegalArgumentException("Value of a key cannot be null");
    if (k == null) throw new IllegalArgumentException("Key should not be null.");
    root = put(k, v, root);
  }

  private Node put(Key k, Value v, Node r) {
    if (r == null) return new Node(k, v, null, null); compares++;
    int cmp = k.compareTo(r.key); compares++;
    if (cmp == 0) { compares++;
      r.value = v;
      return r;
    }
    if (cmp < 0) { r.left = put(k, v, r.left); }  compares++;
    return r.right = put(k, v, r.right);
  }
  /*
  public static void main(String[] args)  {
    classes.UBST<String,Integer> st = new classes.UBST<>();
    In infile = new In(args[0]);
    while (!infile.isEmpty()) {
      String key = infile.readString();
      Integer i = st.get(key);
      if (i != null) st.put(key,i+1);
      else st.put(key, 1);
    }
    // st.show(); assert st.keylevels();
  }//End of main
*/
  private boolean keylevels(){// used with assert for simple traces
    Queue<Node> q = new Queue<>();
    q.enqueue(root); // precondition: root != null
    q.enqueue(null); // marks end of level
    while (q.size() > 1){ // level-wise tree transversal
      while(q.peek() != null) {
        Node n = q.dequeue();
        if (n.left != null) {
          q.enqueue(n.left);
          StdOut.print("<");
        }
        StdOut.print("-"+n.key+"-");
        if (n.right != null) {
          q.enqueue(n.right);
          StdOut.print(">");
        }
      }
      StdOut.println();
      q.dequeue();
      q.enqueue(null);
    }
    return true;
  }
}//End of classes.UBST, based on Algorithms, 4th Edition, Alg. 3.2
