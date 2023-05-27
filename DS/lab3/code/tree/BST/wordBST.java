package tree.BST;

import java.io.*;
import java.util.*;

public class wordBST {
    BSTNode<String, Vector<Integer>> root = new BSTNode<>();

    public static void main(String[] args) throws FileNotFoundException {
        System.setOut(new PrintStream("./output/index_result.txt"));
        wordBST w = new wordBST();
        w.ReadIn("./input/article.txt");
        w.printInorder();
    }


    /**
     * read words from filename
     * @param filename
     */
    private void ReadIn(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader(filename));
        int linenums = 0;
        String tmp;
        Vector<Integer> v;
        Vector<String> res = new Vector<>();
        //可能会出现异常，直接throws就行了
        while (sc.hasNextLine()) {
            ++linenums;
            tmp = sc.nextLine();
            StringTokenizer st = new StringTokenizer(tmp, "[],.!? \n\"-'):;&*(");
            while (st.hasMoreTokens()) {
                tmp = st.nextToken().toLowerCase();
                if (tmp.length()<=2 || tmp.matches("[0-9]+[a-z]*")) continue;
                if ((v = root.search(tmp)) != null) v.add(linenums);
                else {
                    v = new Vector<>();
                    root.insert(tmp, v);
                    v.add(linenums);
                }
            }
        }
    }

    private void printHelper(BSTNode<String,Vector<Integer>> b) {
        if (b == null) return;
        printHelper(b.left);
        System.out.println("[" + b.key + " --- < " +
                b.value.toString().substring(1,b.value.toString().length()-1).replace(",","") + " >]");
        printHelper(b.right);
    }

    public void printInorder(){
        printHelper(this.root);
    }
}
