import java.util.*;
import java.io.*;

public class Lattice {
    static long gcd(long x, long y) {
        if (x == 0) return y;
        if (y == 0) return x;
        return gcd(y, x%y);
    }

    static long abs(long x) {
        if (x < 0) return -x;
        return x;
    }

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        PrintWriter pw = new PrintWriter(System.out);
        int n = sc.nextInt();
        List<Point> polygon = new ArrayList<>();
        for (int i=0; i<n; i++) {
            Point vertex = new Point();
            vertex.read(sc);
            polygon.add(vertex);
        }
        long boundary  = 0;
        long area = 0;


        for (int i=0; i<n; i++) {
            int next = (i+1)%n;
            Point p = polygon.get(i).minus(polygon.get(next));
            boundary += gcd(abs(p.x), abs(p.y));

            area += polygon.get(i).crossProduct(polygon.get(next));
        }
        area = abs(area);
        long inside = (area + 2 - boundary) / 2;
        pw.println(inside + " " + boundary);
        pw.close();
    }
}

class Point {
    long x, y;
    Point() {
    }

    Point(long a, long b) {
        x = a;
        y = b;
    }

    void read(FastScanner sc) {
        x = sc.nextLong();
        y = sc.nextLong();
    }

    Point minus(Point other) {
        return  new Point(x - other.x, y - other.y);
    }

    long crossProduct(Point p) {
        return x * p.y - y * p.x;
    }

    boolean notCollinearWith(Point p1, Point p2) {
        // p1p2 is either to left of This(point) or right
        return p1.minus(this).crossProduct(p2.minus(this)) != 0;
    }

    boolean util(long x, long x1, long x2) {
        return Math.min(x1, x2) <= x && x <= Math.max(x1, x2);
    }

    boolean isOnBoundary(Point p1, Point p2) {
        if (this.notCollinearWith(p1, p2)) {
            return false;
        }
        return util(this.x, p1.x, p2.x) && util(this.y, p1.y, p2.y);
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
