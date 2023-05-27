package tree.Trie;

import java.util.Vector;

public class testTrie {
    private static DictionaryTrie root = new DictionaryTrie('\0');

    public static void main(String[] args) {
        root.ReadIn("./input/dictionary.txt");
        InsertNumTest();
        System.out.println();
        aarBasicTest();
        System.out.println();
        aarDeleteTest();
        System.out.println();
        aarMultiInsertTest();
        System.out.println();
        zyzMultiTest();
    }

    public static void InsertNumTest() {
        System.out.println("---- InsertNumTest begins ----");
        System.out.println("expect:\n\t80368\nrun:");
        System.out.println("\t" + root.printSons().size());
    }

    public static void aarBasicTest() {
        System.out.println("---- aarBasicTest begins ----");
        System.out.println("expect:\n\t5\taardvark\taardwolf\taargh\taarrgh\taarrghh\nrun:");
        Vector<String> res = root.PrefixAs("aar");
        if (res != null) {
            System.out.print("\t" + res.size());
            for (String s : res) System.out.print("\t" + s);
        }
        System.out.println();
    }

    public static void aarDeleteTest() {
        System.out.println("---- aarDeleteTest begins ----");
        System.out.println("expect:\n\t4\taardvark\taardwolf\taarrgh\taarrghh\nrun:");
        root.delete("aargh");
        Vector<String> res = root.PrefixAs("aar");
        if (res != null) {
            System.out.print("\t" + res.size());
            for (String s : res) System.out.print("\t" + s);
        }
        System.out.println();
    }

    public static void aarMultiInsertTest() {
        System.out.println("---- aarMultiInsertTest begins ----");
        System.out.println("expect:\n\t4\taardvark\taardwolf\taarrgh\taarrghh\nrun:");
        root.insert("aardwolf");
        Vector<String> res = root.PrefixAs("aar");
        if (res != null) {
            System.out.print("\t" + res.size());
            for (String s : res) System.out.print("\t" + s);
        }
        System.out.println();
    }

    public static void zyzMultiTest() {
        System.out.println("---- aarMultiInsertTest begins ----");
        System.out.println("expect:\n\t1\tzyzzyvas\nrun:");
        root.delete("zyzzyva");
        root.insert("zyzzyvas");
        Vector<String> res = root.PrefixAs("zyz");
        if (res != null) {
            System.out.print("\t" + res.size());
            for (String s : res) System.out.print("\t" + s);
        }
        System.out.println();
    }
}

