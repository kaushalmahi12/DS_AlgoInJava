import java.util.*;
import java.io.*;
public class Heidi {
    static List<List<Pair>> adj;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        adj = new ArrayList<>();
        for (int i=0; i<n; i++) adj.add(new ArrayList<>());
        for (int i=1; i<n; i++) {
            String[] x = br.readLine().split(" ");
            int u = Integer.parseInt(x[0]);
            int v = Integer.parseInt(x[1]);
            int w = Integer.parseInt(x[2]);

            adj.get(u).add(new Pair(v, w));
            adj.get(v).add(new Pair(u, w));
        }
        
        long ans = dfs(0, -1);
        System.out.println(ans);
    } 
    
    static int dfs(int at, int par) {
        int ans = 0;
        for (Pair p: adj.get(at)) {
            int to = p.v;
            int weight = p.w;
            if (to == par) continue;
            ans = Math.max(ans, weight + dfs(to, at));
        }
        return ans;
    }
}

class Pair {
    int v, w;
    Pair(int x, int y) {
        this.v = x;
        this.w = y;
    }

}
