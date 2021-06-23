import java.util.*;
import java.io.*;

public class TreeDia {
    List<List<Integer>> adj;
    int[] dep;
    TreeDia(int n) {
        dep = new int[n];
        adj = new ArrayList<>();
        for (int i=0; i<n; i++) adj.add(new ArrayList<>());
    }

    int[] dfs(int src, int par) {
        dep[src] = 0;
        int mx1 = -1;
        int mx2 = -1;
        int[] ans = new int[2];
        for (int ch: adj.get(src)) {
            if (ch == par) continue;
            int[] chAns = dfs(ch, src);
            if (mx1 <= dep[ch]) {
                mx2 = mx1;
                mx1 = dep[ch];
            }
            else if (mx2 < dep[ch]) {
                mx2 = dep[ch];
            }
            ans[0] = Math.max(ans[0], chAns[0]);
            dep[src] = Math.max(dep[src], dep[ch] + 1);
            ans[1] = Math.max(mx1 + mx2 + 2, ans[1]);
         }
         ans[0] = Math.max(ans[1], ans[0]);
         return ans;
      }

      public static void main(String[] args) throws Exception {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          int n = Integer.parseInt(br.readLine());

          TreeDia solver = new TreeDia(n);

          for (int i=1; i<n; i++) {
              String[] l = br.readLine().split(" ");
              int u = Integer.parseInt(l[0]) - 1;
              int v = Integer.parseInt(l[1]) - 1;
              solver.add(u, v);
          }

          System.out.println(3 * solver.dfs(0, -1)[0]);
      }

      void add(int u, int v) {
          adj.get(u).add(v);
          adj.get(v).add(u);
      }
}
