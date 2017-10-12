package tests;

import RandomGenerators.IntGenerator;
import RandomGenerators.StringGenerator;
import classes.UBST;
import classes.UTST;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UBSTTest {
    private UBST<String, Integer> tree = new UBST<>();
    private ArrayList<String> keys = new ArrayList<>();
    private ArrayList<Integer> values = new ArrayList<>();
    private StringGenerator strGen = new StringGenerator();
    private IntGenerator intGen = new IntGenerator();

    @After
    public void reset() {
        keys.clear();
        values.clear();
        tree = new UBST<>();
    }

    @Test
    public void testNewValueForExistingKey() {
        final int ARRAYSIZE = 1000;
        keys = strGen.randomList(ARRAYSIZE, 6);
        values = intGen.randomList(ARRAYSIZE, -500, 500);
        for (int i = 0; i < ARRAYSIZE; i++) {
            String key = keys.get(i);
            int val = values.get(i);
            tree.put(key, val);
            assertTrue(tree.get(key) == val);
        }
    }

    @Test
    public void testValueNullForNonExistingKey() {
        final int ARRAYSIZE = 100;
        keys = strGen.randomList(ARRAYSIZE, 5);
        values = intGen.randomList(ARRAYSIZE, -20, 20);
        for (int i = 0; i < ARRAYSIZE; i++) tree.put(keys.get(i), values.get(i));
        keys = strGen.randomList(ARRAYSIZE, 6);
        for (int i = 0; i < ARRAYSIZE; i++) assertNull(tree.get(keys.get(i)));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNoNullKeys() {
        final int ARRAYSIZE = 1000;
        keys = strGen.randomList(ARRAYSIZE, 6); //Since 6! = 720 and 1000 keys some keys are bound to be equal
        values = intGen.randomList(ARRAYSIZE, -500, 500);
        for (int i = 0; i < ARRAYSIZE; i++) {
            tree.put(keys.get(i), values.get(i));
            assertNotNull(tree.get(keys.get(i)));
        }
        tree.get(null);
    }
}