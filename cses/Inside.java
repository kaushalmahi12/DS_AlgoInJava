import java.util.*;
import java.io.*;

public class Inside {
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        PrintWriter pw = new PrintWriter(System.out);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Point> polygon = new ArrayList<>();
        for (int i=0; i<n; i++) {
            Point vertex = new Point();
            vertex.read(sc);
            polygon.add(vertex);
        }

        for (int j=0; j<m; j++) {
            Point p = new Point();
            p.read(sc);

            Point out = new Point(p.x+1, 2_000_000_001l);
            boolean on_boundary = false;
            int count = 0;
            for (int i=0; i<n; i++) {
                int next = (i+1)%n;
                if (p.isOnBoundary(polygon.get(i), polygon.get(next))) {
                    on_boundary = true;
                    break;
                }
                if (intersects(p, out, polygon.get(i), polygon.get(next))) {
                    count++;
                }
            }
            if (on_boundary) {
                pw.println("BOUNDARY");
            } else if (count%2 == 1) {
                pw.println("INSIDE");
            } else {
                pw.println("OUTSIDE");
            }
         }

        pw.close();
    }

    static boolean test(Point p1, Point p2, Point p3, Point p4) {
        Point ref = p2.minus(p1);
        Point p3Ref = p3.minus(p1);
        Point p4Ref = p4.minus(p1);
        if (ref.crossProduct(p3Ref) < 0 && ref.crossProduct(p4Ref) < 0 || 
            ref.crossProduct(p3Ref) > 0 && ref.crossProduct(p4Ref) > 0) {
          return true;
        }
        return false;
    }

    static boolean rectangleIntersects(Point p1, Point p2, Point p3, Point p4) {
        // check whether horizontally / vertically separable
        boolean horizontal = Math.max(p1.x, p2.x) < Math.min(p3.x, p4.x);
        boolean vertical = Math.max(p1.y, p2.y) < Math.min(p3.y, p4.y);
        return horizontal || vertical;
    }

    static boolean intersects(Point p1, Point p2, Point p3, Point p4) {
        // check if p1p2 and p3p4 are parallel
        if (p2.minus(p1).crossProduct(p4.minus(p3)) == 0) {
            // check the collinearity
            if (p1.notCollinearWith(p3, p4)) {
                return false;
            }

            // check using bounding rectangles whether they intersect or not
            if (rectangleIntersects(p1, p2, p3, p4) || rectangleIntersects(p3, p4, p1, p2)) {
                return false;
            }

            return true;
         }
        // check if segement (p3p4) is completely to left or right and vice versa
        if (test(p1, p2, p3, p4) || test(p3, p4, p1, p2)) {
            return false;
        }
        return true;
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
