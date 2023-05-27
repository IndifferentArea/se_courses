package hw1;

import java.io.*;
import java.util.*;


public class Problem5 {
    public static void main(String[] args) throws IOException {
        GenerateInput();
        Scanner in = new Scanner(new File("./input/input5.txt"));
        int[] stat = new int[10];
        while (in.hasNext()) {
            int tmp = in.nextInt() / 10;
            if (tmp == 10) tmp -= 1;
            stat[tmp]++;
        }
        Print_Hori(stat);
        System.out.println();
        Print_Vert(stat);
    }

    /*
     * Generate needed data into input5.txt
     */
    public static void GenerateInput() throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("./input/input5.txt"));
        Random r = new Random();
        for (int i = 0; i < r.nextInt(50, 101); i++)
            out.write(r.nextInt(101) + "\n");
        out.close();
    }

    /*
     * print src (length is 10) which represents 10 segments' times in Horizontal form.
     */
    public static void Print_Hori(int[] src) {
        for (int i = 0; i < 10; i++) {
            if (i == 0) System.out.print(" 0 -  9: ");
            else if (i == 9) System.out.print("90 -100: ");
            else System.out.print(i * 10 + " - " + (i * 10 + 9) + ": ");
            for (int j = 0; j < src[i]; j++) System.out.print("*");
            System.out.println();
        }
    }

    /*
     * print src (length is 10) which represents 10 segments' times in Vertical form.
     */
    public static void Print_Vert(int[] src) {
        int max = 0;
        for (int i = 0; i < 10; i++) if (src[i] > max) max = src[i];
        for (int i = 0; i < max; i++) {
            System.out.print(" ");
            for (int j = 0; j < 10; j++) {
                if (max - i <= src[j]) System.out.print("*");
                else System.out.print(" ");
                if (j != 9) System.out.print("     ");
                else System.out.println();
            }
        }
        for (int k = 0; k < 10; k++) {
            if (k == 0) System.out.print("0-9 ");
            else if (k == 9) System.out.print(" 90-100");
            else System.out.print(" " + k * 10 + "-" + (k * 10 + 9));
        }
    }
}
