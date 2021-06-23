import java.util.*;
import java.io.*;

public class PointLocation {
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        while (t > 0) {
            t -= 1;
            Point p1=new Point();
            Point p2 = new Point();
            Point p3 = new Point();
            p1.read(sc);
            p2.read(sc);
            p3.read(sc);

            p2.minus(p1);
            p3.minus(p1);

            long crossProd = p2.crossProduct(p3);
            if (crossProd < 0) {
                pw.println("RIGHT");
            }
            else if (crossProd > 0) {
                pw.println("LEFT");
            }
            else {
                pw.println("TOUCH");
            }
            // pw.flush();
        }
        pw.close();
    }
}

class Point {
    long x, y;
    void read(FastScanner sc) {
        x = sc.nextLong();
        y = sc.nextLong();
    }

    void minus(Point other) {
        x -= other.x;
        y -= other.y;
    }

    long crossProduct(Point p) {
        return x * p.y - y * p.x;
    }
}
        

class FastScanner {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st=new StringTokenizer("");

    String next() {
        while (!st.hasMoreTokens())
            try {
                st=new StringTokenizer(br.readLine());
            } catch (IOException e) {}
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }
    long nextLong() {
        return Long.parseLong(next());
    }
}
