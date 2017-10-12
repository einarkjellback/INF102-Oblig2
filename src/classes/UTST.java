package classes;

import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut;

// an incomplete implementation of unbalanced ternary trees,
// to be used (and modified) in a compulsory exercise 
// no values, no delete, all internal nodes are 3-nodes

public class UTST<Key extends Comparable<Key>, Value> {

  private Node root;

  private class Node {
    private Key hiKey, loKey;
    private Value hiVal, loVal;
    private Node left, mid, right;

    //loKey cannot be null, but hiKey may be null.
    public Node(Key k1, Value v1, Key k2, Value v2, Node l, Node m, Node r) {
      loKey = k1;
      loVal = v1;
      hiKey = k2;
      hiVal = v2;
      if (loKey == null || (hiKey != null && loKey.compareTo(hiKey) > 0)) {
        exchange(hiKey, loKey);
        exchange(hiVal, loVal);
      }
      assert loKey != null && (hiKey == null || loKey.compareTo(hiKey) < 0);
      left = l;
      mid = m;
      right = r;
      if (hiKey == null && mid != null) { throw new IllegalArgumentException("If exactly one key is null the mid node must also be null"); }
      assert hiKey != null || (mid == null && right == null);
    }
  }

  private void exchange(Object v1, Object v2) {
    Object temp = v1;
    v1 = v2;
    v2 = temp;
  }

  public void show(){ show(root); }

  private void show(Node r){// depth-first left-to-right tree transversal
    if (r != null) {
      show(r.left);
      StdOut.println(r.hiKey);
      show(r.mid);
      StdOut.println(r.loKey);
      show(r.right);
    }
  }

  public Value get(Key k) {
    if (k == null) throw new IllegalArgumentException("Key should not be null");
    return get(k, root);
  }

  private Value get(Key k, Node r) {
    if (r == null) return null;
    int cmp = k.compareTo(r.loKey);
    if (cmp == 0) return r.loVal;
    if (cmp < 0) return get(k, r.left);
    if (r.hiKey == null) return null;
    cmp = k.compareTo(r.hiKey);
    if (cmp == 0) return r.hiVal;
    if (cmp > 0) return get(k, r.right);
    return get(k, r.mid);
  }

  public void put(Key k, Value v) {
    if (v == null) throw new IllegalArgumentException("Value of a key cannot be null");
    if (k == null) throw new IllegalArgumentException("Key should not be null");
    root = put(k, v, root);
  }

  private Node put(Key k, Value v, Node r) {
    if (r == null) return new Node(k, v, null, null, null, null, null);
    int cmp = k.compareTo(r.loKey);
    if (cmp == 0) {
      r.loVal = v;
      return r;
    } else if (cmp < 0) r.left = put(k, v, r.left);
    if (r.hiKey == null) {
      r.hiKey = k;
      r.hiVal = v;
      return r;
    }
    cmp = k.compareTo(r.hiKey);
    if (cmp == 0) {
      r.loVal = v;
      return r;
    } else if (cmp > 0) r.right = put(k, v, r.right);
    return r.mid = put(k, v, r.mid);
  }
/*
  public static void main(String[] args)  {
    classes.UTST<String, Integer> st = new classes.UTST<String, Integer>();
    In infile = new In(args[0]);
    while (!infile.isEmpty()) {
      String key = infile.readString();
      st.put(key);
    }
    st.show();
  }//End of main
  */
}//End of classes.UTST
