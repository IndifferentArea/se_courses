package hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner ins = new Scanner(new File("./input/input3.txt"));
        String s = ins.next();
        while (true) {
            System.out.print("Please input a length n, input 0 means quit\n");
            Scanner inn = new Scanner(System.in);
            int n = inn.nextInt();
            if(n == 0) break;
            else  System.out.println(SuperMaxMulti(s,n));
            System.out.println();
        }
    }

    /*
    * use bigInteger settle String s's n length max multi result.
     */
     static String SuperMaxMulti(String s, int n){
        BigInteger max = new BigInteger("0");
        for(int i = 0;i < s.length() - n + 1;i++){
            BigInteger tmp = new BigInteger("1");
            for(int j = 0; j< n;j++) {
                tmp = tmp.multiply(BigInteger.valueOf((Long.parseLong(s.substring(i + j, i + j + 1)))));
            }
            if(tmp.compareTo(max) > 0) max = tmp;
        }
        return String.valueOf(max);
    }
}
