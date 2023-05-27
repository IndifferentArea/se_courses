import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class movieStarGraph {

    private class LinkNode {
        int edge;
        int starIndex;

        // if edge = -1, it means this is the first one of a link;
        LinkNode(int e, int s) {
            edge = e;
            starIndex = s;
        }
    }

    Vector<Vector<LinkNode>> linkEdges = new Vector<>();

    static final int MAXNUM = 50000; // simple.txt has or so 61 lines
    int[] dis = new int[MAXNUM]; // distance from bst
    boolean[] visited = new boolean[MAXNUM];
    Vector<String> starNames = new Vector<>(MAXNUM);
    Vector<String> movieNames = new Vector<>(MAXNUM);
    HashMap<Integer, String> has = new HashMap<>(MAXNUM);

    movieStarGraph(String filename) throws IOException {
        // BufferedReader bf = new BufferedReader(new FileReader(filename));
        Arrays.fill(dis, -1);

        String tmp;
        String[] split;
        int lines = 0;
        Scanner sc = new Scanner(new FileReader(filename));
        while (sc.hasNextLine()) {
            tmp = sc.nextLine();
            lines++;
            split = tmp.split("/");
            for (int i = 1; i < split.length; i++) {
                if (!starNames.contains(split[i])) {
                    starNames.add(split[i]);
                    linkEdges.add(new Vector<>());
                }
            }
            movieNames.add(split[0]);
            int edgIndex = movieNames.size() - 1;
            for (int i = 1; i < split.length; i++) {
                int startStarIndex = starNames.indexOf(split[i]);
                for (int j = 1; j < split.length; j++) {
                    if (j == i) continue;
                    linkEdges.get(startStarIndex).add(new LinkNode(edgIndex, starNames.indexOf(split[j])));
                }
            }
            if (lines % 500 == 0) {
                System.out.println("has read " + lines);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        movieStarGraph msg = new movieStarGraph("../Simple.txt");
        msg.BFS(0, msg.starNames.indexOf("Kevin Bacon"));
        msg.sixDegreeShell();
    }

    /**
     * BFS started with given index, after BFS, dis[] values should be set.
     *
     * @param depth the depth of BFS, this is used for initialize
     * @param start start index
     */
    public void BFS(int depth, int start) {
        dis[start] = depth;
        visited[start] = true;
        Vector<Integer> tmp = new Vector<>();
        Vector<LinkNode> stLink = linkEdges.get(start);
        for (LinkNode linkNode : stLink)
            if (!visited[linkNode.starIndex])
                tmp.add(linkNode.starIndex);
        for (int i : tmp) BFS(depth + 1, i);
    }

    /**
     * based on given dst[] (best after BFS), get the path to start vertex and print as requested
     * @param index - index of the star name.
     */
    public void path(int index) {
        if (index == -1) return;
        if (dis[index] == 0) return;
        int next = -1;
        Vector<LinkNode> stLink = linkEdges.get(index);
        for (LinkNode linkNode : stLink) {
            int st = linkNode.starIndex;
            if (dis[st] == dis[index] - 1) {
                next = st;
                System.out.println("\t" + starNames.get(index) + " was in " + movieNames.get(linkNode.edge) + " with " + starNames.get(st));
                break;
            }
        }
        path(next);
    }

    public void sixDegreeShell(){
        System.out.print("""  
                Welcome to the Six Degrees of Kevin Bacon.
                If you tell me an actor's name, I'll connect them to Kevin Bacon through the movies they've appeared in.
                I bet your actor has a Kevin Bacon Number of less than 6!
                """);
        while(true){
            System.out.print("\nPlease input the actor's name(or ALL for everyone)? ");
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            if (str.equals("ALL")) {
                for (int i = 0; i < this.starNames.size(); i++) {
                    System.out.println("\nPath from " + this.starNames.get(i) + " to Kevin Bacon:");
                    this.path(i);
                    System.out.println(this.starNames.get(i) + "'s Bacon number is " + this.dis[i]);
                }
            }else if (str.equals("exit")) return;
            else {
                int index = this.starNames.indexOf(str);
                System.out.println("Path from " + this.starNames.get(index) + " to Kevin Bacon:");
                this.path(index);
                System.out.println(this.starNames.get(index) + "'s Bacon number is " + this.dis[index]);
            }
        }

    }

}
