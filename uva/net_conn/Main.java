import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        br.readLine();
        while (test-- > 0) {
            int n = Integer.parseInt(br.readLine());
            UnionFind uf = new UnionFind(n);
            int succ = 0;
            int fail = 0;
            while (true) {
                String[] line = br.readLine().split(" ");
                if (line.length == 1) break;
                String op = line[0];
                int u = Integer.parseInt(line[1]) - 1;
                int v = Integer.parseInt(line[2]) - 1;

                if (op.equals("c")) {
                    uf.unite(u, v);
                } else {
                    if (uf.connected(u, v)) {
                        succ += 1;
                    } else {
                        fail += 1;
                    }
                }
            }
            System.out.println(succ + ", " + fail);
        }
    }
}

class UnionFind {
    int n;
    int[] p;

    UnionFind(int n) {
        this.n = n;
        this.p = new int[n];
        for (int i=0; i<n; i++) p[i] = i;
    }

    int find(int x) {
        if (x == p[x]) return x;
        return (p[x] = find(p[x]));
    }

    void unite(int i, int j) {
        p[find(i)] = find(j);
    }

    boolean connected(int i, int j) {
        i = find(i);
        j = find(j);
        return (i==j);
    }

}
