import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class hashGraph {


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

    static final int MAXNUM = 400000; // simple.txt has or so 61 lines
    int[] dis = new int[MAXNUM]; // distance from bst

    HashMap<String, Integer> actors = new HashMap<>(MAXNUM);
    HashMap<String, Integer> movies = new HashMap<>(MAXNUM);

    hashGraph(String filename) throws IOException {
        // BufferedReader bf = new BufferedReader(new FileReader(filename));
        Arrays.fill(dis, Integer.MAX_VALUE);
        String tmp;
        String[] split;
        Scanner sc = new Scanner(new FileReader(filename));
        while (sc.hasNextLine()) {
            tmp = sc.nextLine();
            split = tmp.split("/");
            for (int i = 1; i < split.length; i++) {
                if (!actors.containsKey(split[i])) {
                    actors.put(split[i],actors.size());
                    linkEdges.add(new Vector<>());
                }
            }
            movies.put(split[0],movies.size());
            int edgIndex = movies.get(split[0]);
            // int edgIndex = movies.size() - 1;
            for (int i = 1; i < split.length; i++) {
                for (int j = 1; j < split.length; j++) {
                    if (j == i) continue;
                    linkEdges.get(actors.get(split[i])).add(new LinkNode(edgIndex, actors.get(split[j])));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        hashGraph hg = new hashGraph("../Complex.txt");
        hg.bfsNoRecursion(hg.actors.get("Sheahan, Norma"));
        hg.sixDegreeShell("Sheahan, Norma");
    }

    public void bfsNoRecursion(int start) {
        boolean[] visited = new boolean[actors.size()];
        ArrayDeque<depthInt> queue = new ArrayDeque<>();
        int dist;
        queue.add(new depthInt(0,start));
        visited[start] = true;
        dis[start] = 0;
        while (!queue.isEmpty()) {
            depthInt k = queue.poll();
            Vector<LinkNode> vc = this.linkEdges.get(k.actorIndex);
            dist = k.depth + 1;
            for (LinkNode ln : vc) {
                if (!visited[ln.starIndex]) {
                    queue.add(new depthInt(dist, ln.starIndex));
                    visited[ln.starIndex] = true;
                    dis[ln.starIndex] = dist;
                }
            }
        }
    }

    /**
     * based on given dst[] (best after BFS), get the path to start vertex and print as requested
     *
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
                System.out.println("\t" + getkey(index,actors) + " was in " + getkey(linkNode.edge,movies) + " with " + getkey(st,actors));
                break;
            }
        }
        path(next);
    }

    public void sixDegreeShell(String name) {
        System.out.println("THIS IS HASH_GRAPH");
        System.out.println("Welcome to the Six Degrees of " + name + ".");
        System.out.println("If you tell me an actor's name, I'll connect them to + " + name + " through the movies they've appeared in.");
        System.out.println("I bet your actor has a " + name + " Number of less than 6!");
        System.out.println("PS: Actually, I'm not so sure because the data set is much bigger and the actress may not be the famous Kevin Bacon. :)");
        while (true) {
            System.out.print("\nPlease input the actor's name(or ALL for everyone)? ");
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            if (str.equals("ALL")) {
                for (int i = 0; i < this.actors.size(); i++) {
                    String s = getkey(i, actors);
                    System.out.println("\nPath from " + s + " to " + name + " :");
                    this.path(i);
                    System.out.println(s + "'s Bacon number is " + this.dis[i]);
                }
            } else if (str.equals("exit")) return;
            else if(str.equals("test ALL")){
                int num = 0;
                boolean[] isMore = new boolean[actors.size()];
                Arrays.fill(isMore,false);
                for(int i  = 0;i<actors.size();i++) {
                    if(isMore[i]) continue;
                    this.bfsNoRecursion(i);
                    int k; boolean flag = false;
                    for(k  = 0;k<actors.size();k++){
                        if(dis[k] > 6) {
                            isMore[i] = true;
                            isMore[k] = true;
                            flag = true;
                        }
                    }
                    if(!flag) num++;
                    if(i%500 == 0) System.out.println(i);
                }
                System.out.println("result " + num);
            } else if (str.equals("test")){
                int j = 0, k = 0;
                for(int i  = 0;i<actors.size();i++) {
                    if(dis[i] == Integer.MAX_VALUE)  j++;
                    else if(dis[i] > 6) k++;
                }
                System.out.println(j + " "+k);
            } else {
                int index = this.actors.get(str);
                System.out.println("Path from " + str + " to Kevin Bacon:");
                this.path(index);
                System.out.println(str + "'s Bacon number is " + this.dis[index]);
            }
        }
    }

    private static String getkey(int value, HashMap<String,Integer> hash) {
        for (HashMap.Entry<String, Integer> entry : hash.entrySet())
            if (entry.getValue().equals(value))
                return entry.getKey();
        return null;
    }

    private class depthInt {
        int depth;
        int actorIndex;

        depthInt(int d,int i){
            depth = d;
            actorIndex = i;
        }
    }

}
