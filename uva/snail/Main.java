import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String[] a = br.readLine().split(" ");
            double H = Double.parseDouble(a[0]);
            double U = Double.parseDouble(a[1]);
            int D = Integer.parseInt(a[2]);
            Double F = Double.parseDouble(a[3]);

            if (H == 0) break;

            double total = 0;
            int days = 1;
            F = F * U / 100;
            boolean first = true;
            while (total < H && (total > 0 || first == true)) {
                first = false;
                total += U;
                if (total > H) break;
                total -= D;
                U -= F;
                U = Math.max(U, 0.0);
//                System.out.println(total + " " + U);
                if (total < 0) break;
                days += 1;
            }

            String ans = "success ";
            if (total <= 0) {
                ans = "failure ";
            }
            ans += "on day " + days;
            System.out.println(ans);
       }

    }
}
