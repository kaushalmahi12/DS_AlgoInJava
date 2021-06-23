import java.util.*;
import java.io.*;

public class Interactive {
  static PrintWriter pw;
  static FastScanner sc;
  static int N,Q;
  public static void main(String[] args) {
      sc = new FastScanner();
      N = sc.nextInt();
      Q = sc.nextInt();
      pw = new PrintWriter(System.out, true);
      char[] a = new char[N];
    for (int i=0; i<N; i++) {
      a[i] = (char)(i+'A');
    }
//    pw.println(new String(a));
    sort(a, 0, N-1);
    pw.println("!" + new String(a));
    pw.close();
    	
    }
  static void sort(char[] a, int l, int r) {
    if (l < r) {
      int mid = (l+r)/2;
//      System.out.println(new String(a, l, r+1) + " " + l + " " + mid + " " + r);
      sort(a, l, mid);
      sort(a, mid+1, r);
      merge(a, l, mid, r);
    }
  }
  
  static boolean ask(char c1, char c2) {
    pw.println("? " + c1 + " " + c2);
    pw.flush();
    String s = sc.next();
    if (s.equals("<")) {
      return true;
    }
    return false;
  }
  
  static void merge(char[] a, int l, int mid, int r) {
    char[] na = new char[r-l+1];
    int i=l;
    int j = mid+1;
    int k = 0;
    while(i <= mid || j<=r) {
      if (i > mid) {
        na[k++] = a[j++];
      }else if (j > r) {
        na[k++] = a[i++];
      } else if (ask(a[i], a[j]) ) {
        na[k++] = a[i++];
      } else {
        na[k++] = a[j++];
      }
    }
    k = 0;
    for (i=l; i<=r; i++) {
      a[i] = na[k++];
    }
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
