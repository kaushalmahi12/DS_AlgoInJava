import java.util.*;
import java.io.*;
public class B {
    static List<List<Integer>> nodes, adj;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        nodes = new ArrayList<>();
        nodes.add(new ArrayList<>());
        nodes.add(new ArrayList<>());
        adj = new ArrayList<>();
        for (int i=0; i<n; i++) adj.add(new ArrayList<>());
        for (int i=1; i<n; i++) {
            String[] x = br.readLine().split(" ");
            int u = Integer.parseInt(x[0]) - 1;
            int v = Integer.parseInt(x[1]) - 1;
            
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        
        dfs(0, -1, 0);
        long ans = 0;
        int other = nodes.get(1).size();
        for (int a: nodes.get(0)) {
            int x = adj.get(a).size();
            ans += other - x;
        }
        System.out.println(ans);
    } 
    
    static void dfs(int at, int par, int col) {
        nodes.get(col).add(at);
        for (int to: adj.get(at)) {
            if (to == par) continue;
            dfs(to, at, 1-col);
        }
    }
}
