import java.util.*;
import java.io.*;
public class DynamicTreeDia {
    static List<Node> adj;
    static int diaEnd;
    static int[] dist;
    static int n;
    static class Node {
        List<Node> child;
        int dist;
        boolean end=false;
        Node() {
            this.child = new ArrayList<>();
            dist = -1;
            end = false;
        }

        void add(Node other) {
            this.child.add(other);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        adj = new ArrayList<>();
        for (int i=0; i<n; i++) adj.add(new Node());
        for (int i=1; i<n; i++) {
            String[] x = br.readLine().split(" ");
            int u = Integer.parseInt(x[0]) - 1;
            int v = Integer.parseInt(x[1]) - 1;
            
            adj.get(u).add(adj.get(v));
            adj.get(v).add(adj.get(u));
        }    
        bfs(adj.get(0));
        Node distant = otherEnd();
        bfs(distant);
        distant = otherEnd();
        int dia = distant.dist;
//        System.out.println(dia);
        for (Node nn: adj) {
            nn.end = (nn.dist==dia);
        }
        bfs(distant);
        for (Node nn: adj) {
            nn.end |= (nn.dist==dia);
        }
//        System.out.println(first + "->" + other + "\n" + Arrays.toString(dist));
        for (int i=0; i<n; i++) {
            int v = (adj.get(i).end) ? (dia+1) : dia;
            System.out.println(v);
        }
    }

    static Node otherEnd() {
        Node ans = adj.get(0);
        for (Node nn: adj) {
//          System.out.printf(nn.dist + " ");
            if (nn.dist > ans.dist) {
                ans = nn;
            }
        }
  //      System.out.println();
        return ans;
    }
    
    static void bfs(Node at) {
        for (Node nn: adj) nn.dist = -1;
        Deque<Node> q = new ArrayDeque<>();
        q.offer(at);
        at.dist = 0;
        while (q.size() > 0) {
            Node from = q.poll();
            for (Node to: from.child) {
                if (to.dist == -1) {
                    to.dist = from.dist + 1;
                    q.offer(to);
                }
            }
       }
    }
}
