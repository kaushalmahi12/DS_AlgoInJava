import java.util.*;
import java.util.stream.Collectors;
import java.io.*;
public class Balloon {
    static List<List<Integer>>  adj;
    static int[] f, val; 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        adj = new ArrayList<>();
        f = new int[n];
        val = new int[n];
        for (int i=0; i<n; i++) adj.add(new ArrayList<>());
        for (int i=1; i<n; i++) {
            String[] x = br.readLine().split(" ");
            int u = Integer.parseInt(x[0]) - 1;
            int v = Integer.parseInt(x[1]) - 1;
            
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        val[0] = 1;
        
        dfs(0, -1);
        long ans = 0;
        System.out.println(Arrays.stream(f).max().getAsInt() + "\n" + Arrays.stream(val)
  .mapToObj(String::valueOf)
  .collect(Collectors.joining(" ")));
    } 
    
    static void dfs(int at, int par) {
        f[at] = adj.get(at).size() + 1;
        int cur = 1;
        int skip = par != -1 ? val[par] : -1;
        for (int to: adj.get(at)) {
            if (to == par) continue;
            while (skip == cur || val[at] == cur) cur++;
            val[to] = cur++;
            dfs(to, at);
        }
    }
}
