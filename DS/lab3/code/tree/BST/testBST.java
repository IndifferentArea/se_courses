package tree.BST;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class testBST {
    BSTNode<String, String> root = new BSTNode<>();


    public static void main(String[] args) throws IOException {
        testBST testBST = new testBST();
        testBST.BSTShell("./input/BST_testcases.txt", "./output/BST_my_res.txt");

    }

    public void BSTShell(String input, String output) throws IOException {
        Scanner sc = new Scanner(new FileReader(input));
        PrintStream out = System.out, fout = new PrintStream(output);
        System.setOut(fout);
        String s;
        String[] tmp;
        while (sc.hasNextLine()) {
            tmp = sc.nextLine().split(" ");
            switch (tmp[0].charAt(0)) {
                case '+':
                    root.insert(tmp[1], tmp[3].substring(1, tmp[3].length() - 1));
                    break;
                case '-':
                    s = root.remove(tmp[1]);
                    if (s == null) System.out.println("remove unsuccess ---" + tmp[1]);
                    else System.out.println("remove success ---" + tmp[1] + " " + s);
                    break;
                case '?':
                    s = root.search(tmp[1]);
                    if (s == null) System.out.println("search unsuccess ---" + tmp[1]);
                    else System.out.println("search success ---" + tmp[1] + " " + s);
                    break;
                case '=':
                    if (root.update(tmp[1], tmp[3].substring(1, tmp[3].length() - 1)))
                        System.out.println("update success ---" + tmp[1] +
                                " " + tmp[3].substring(1, tmp[3].length() - 1));
                    else System.out.println("update unsucess ---" + tmp[1] +
                            " " + tmp[3].substring(1, tmp[3].length() - 1));
                    break;
                case '#':
                    PrintSplitLine();
                    System.out.println("There are " + root.getNodeCons() + " nodes in this BST.");
                    System.out.println("The height of this BST is " + root.getHeight() + ".");
                    PrintSplitLine();
            }
        }
        System.setOut(out);
        compareTwoFile("./output/BST_my_res.txt","./output/BST_result.txt");
    }


    private void PrintSplitLine() {
        for (int i = 0; i < 29; i++) System.out.print('-');
        System.out.println();
    }

    public static void compareTwoFile(String oldFile, String newFile) throws IOException {
        List<String> list1 =  Files.readAllLines(Paths.get(oldFile));
        List<String> list2 =  Files.readAllLines(Paths.get(newFile));
        System.out.printf("compare two files: %s and %s \n", oldFile, newFile);
        List<String> finalList = list2.stream().filter(line ->
                list1.stream().noneMatch(line2 -> line2.equals(line))
        ).toList();
        if (finalList.size() == 0) {
            System.out.println("\tNO difference");
        }else{
            System.out.println("diff:");
            finalList.forEach(System.out::println);
        }
    }
}
