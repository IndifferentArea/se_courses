package tree.Trie;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

public class DictionaryTrie {
    DictionaryTrie dad = null;
    Vector<DictionaryTrie> sons = new Vector<DictionaryTrie>();
    char ch;
    int dup_times = 0;

    public DictionaryTrie(char c) {
        ch = c;
    }

    public DictionaryTrie(DictionaryTrie father, char c) {
        dad = father;
        father.sons.add(this);
        ch = c;
    }

    // insert a "keyword" to this trie.
    public void insert(String keyword) {
        // search for same prefix string.
        for (DictionaryTrie son : this.sons) {
            if (son.ch == keyword.charAt(0)) {
                if (keyword.length() == 1) son.dup_times++;
                else son.insert(keyword.substring(1));
                return;
            }
        }
        // if this has no son which have same prefix as keyword.
        DictionaryTrie tmp = new DictionaryTrie(this, keyword.charAt(0));
        if (keyword.length() != 1) tmp.insert(keyword.substring(1));
        else tmp.dup_times++;
    }

    /**
     * delete a "keyword" to this Trie.
     * if found no matter it is keyword
     * if not found, printSons ERROR message on the terminal.
     */
    public void delete(String keyword) {
        DictionaryTrie res = this.match(keyword);
        if (res == null) System.out.println("ERROR--[Trie.delete]: no such keyword as \"" + keyword + "\"");
        else res.dup_times = 0;
    }

    // return String
    public Vector<String> PrefixAs(String prefix) {
        DictionaryTrie res = this.match(prefix);
        if (res == null) {
            System.out.println("ERROR--[Trie.PrefixAs]: no such keyword to match as \"" + prefix + "\"");
            return null;
        }
        Vector<String> sen = res.printSons();
        sen.replaceAll(s -> prefix + s);
        if (res.dup_times > 0) sen.add(prefix);
        sen.sort(String::compareTo);
        return sen;
    }

    /**
     * begin with "this", iterate to get the bottom Trie whose prefix match the given target.
     * if found, return Trie; else return null
     */
    public DictionaryTrie match(String target) {
        if (this.sons == null) return null;
        for (DictionaryTrie son : this.sons) {
            if (son.ch == target.charAt(0)) {
                if (target.length() == 1) return son;
                else return son.match(target.substring(1));
            }
        }
        return null;
    }

    /*
     return all valid keyword begin with this Trie use DFS.
     */
    public Vector<String> printSons() {
        Vector<String> res = new Vector<>();
        if (this.sons == null) return null;
        for (DictionaryTrie son : this.sons) {
            if (son.dup_times > 0) res.add(String.valueOf(son.ch));
            Vector<String> tmp = son.printSons();
            if (tmp != null) tmp.replaceAll(s -> son.ch + s);
            res.addAll(tmp);
        }
        return res;
    }

    /**
     * read in by lines
     *
     * @param Filename
     */
    public void ReadIn(String Filename) {
        try {
            Scanner sc = new Scanner(new FileReader(Filename));
            while (sc.hasNextLine()) {  //按行读取字符串
                String line = sc.nextLine();
                this.insert(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("文件未找到~！");
        }
    }

    public String printDad() {
        if (this.dad == null) return "";
        else return this.dad.printDad() + this.ch;
    }

    public Vector<String> printDad_Sons() {
        Vector<String> res = this.printSons();
        res.replaceAll(s -> this.printDad() + s);
        return res;
    }
}
