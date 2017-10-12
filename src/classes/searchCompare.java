package classes;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

public class searchCompare {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new File("train.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
