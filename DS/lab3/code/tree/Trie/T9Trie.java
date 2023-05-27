package tree.Trie;

import java.util.Scanner;
import java.util.Vector;

public class T9Trie {
    private static DictionaryTrie root = new DictionaryTrie('\0');
    final static String[] KeyValues = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static void main(String[] args) {
        root.ReadIn("./input/dictionary.txt");
        T9Shell();
    }

    /**
     * for any-length given digital numbers, return matched prefixes;
     *
     * @param digital given digital numbers
     * @param trie    given Trie tree
     * @return matched T9 words
     */
    static Vector<DictionaryTrie> T9Find(DictionaryTrie trie, String digital) {
        Vector<DictionaryTrie> res = new Vector<>();
        int indexOfT9 = Integer.parseInt(String.valueOf(digital.charAt(0)));
        if (trie == null) return null;
        for (int i = 0; i < KeyValues[indexOfT9].length(); i++)
            for (DictionaryTrie son : trie.sons)
                if (son.ch == KeyValues[indexOfT9].charAt(i)) res.add(son);
        Vector<DictionaryTrie> tmp = new Vector<>();
        if (digital.length() != 1) {
            for (DictionaryTrie son : res) {
                Vector<DictionaryTrie> x = T9Find(son, digital.substring(1));
                if (x != null) tmp.addAll(x);
            }
            res = tmp;
        } else for (int i=0;i< res.size();i++) if (res.get(i).dup_times <= 0) res.removeElement(res.remove(i));
        return res;
    }

    public static void T9Shell() {
        System.out.println("Enter \"exit\" to quit.");
        Scanner sc = new Scanner(System.in);
        String command = "", options = "#";
        Vector<String> res = new Vector<>();
        int resIndex = 0;
        boolean isLookUP = false;
        while (true) {
            System.out.println("Enter Key Sequence (or “#” for next word):");
            System.out.print("> ");
            if (sc.hasNextLine()) command = sc.next();
            if (command.equals("exit")) return;
            else if (command.contains("#")) {
                options = command.substring(command.indexOf("#"));
                command = command.substring(0, command.indexOf("#"));
            } else options = "";
            // judge if options are all #
            if (!options.equals("")) {
                if (options.replace("#", "").length() != 0) {
                    System.out.println("Please input as tips: some numbers followed by some '#'s.");
                    continue;
                }
            }
            int optionCount = options.length();
            if (command.equals("")) {
                if (isLookUP) {
                    resIndex += optionCount;
                    if (resIndex < res.size()) System.out.println("'"+res.get(resIndex)+"'");
                    else System.out.println("There are no more T9 words.");
                } else System.out.println("You're not in searching!");
            } else {
                boolean flag = true;  // is command valid
                for (int i = 0; i < command.length(); i++) {
                    if (command.charAt(i) < '0' || command.charAt(i) > '9') {
                        System.out.println("Please input as tips: some numbers followed by some '#'s.");
                        flag = false;
                    }
                }
                if (flag) {
                    res.clear();
                    Vector<DictionaryTrie> tmp = T9Find(root, command);
                    if(tmp == null || tmp.size() == 0){
                        System.out.println("Not found in current dictionary.");
                        continue;
                    }
                    for (DictionaryTrie t : tmp) res.add(t.printDad());
                    res.sort(String::compareTo);
                    isLookUP = true;
                    System.out.println("'"+res.get(optionCount)+"'");
                    resIndex = optionCount;
                }
            }
        }
    }
}


