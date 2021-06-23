import java.util.*;
import java.io.*;

public class SegmentIntersection {
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        while (t > 0) {
          t -= 1;
          Point p1=new Point();
          Point p2 = new Point();
          Point p3 = new Point();
          Point p4 = new Point();
          p1.read(sc);
          p2.read(sc);
          p3.read(sc);
          p4.read(sc);

         // pw.flush();
          if ( run(p1, p2, p3, p4) ) {
              pw.println("YES");
          } else {
              pw.println("NO");
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

    static boolean run(Point p1, Point p2, Point p3, Point p4) {
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
