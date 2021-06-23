import java.util.*;
import java.io.*;

public class PolygonArea {
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        List<Point> points = new ArrayList<>();
        while (t > 0) {
          t -= 1;
          Point p=new Point();
          p.read(sc);
          points.add(p);
         // pw.flush();
        }
        long area = 0;
        //Point origin = new Point(0, 0);
        for (int i=0; i<points.size(); i++) {
            Point p1 = points.get(i);
            Point p2 = points.get((i+1) % points.size());
            area += p1.crossProduct(p2);
        }
        area = Math.abs(area);
        pw.println(area);
        pw.close();
    }
}

class Point {
    long x, y;
    Point() {}
    Point(long a, long b) {
      x = a;
      y = b;
    }
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
