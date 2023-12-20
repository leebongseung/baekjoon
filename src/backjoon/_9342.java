package backjoon;

import javax.imageio.plugins.tiff.FaxTIFFTagSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

/*  시작 시간: 9시 11분
 *   종료 시간:
 * */
public class _9342 {
    public static boolean HasContainsAtoF(String str, int index) {
        String first = str.split("")[index];
        if (first.equals("A")) {
            return true;
        } else if (first.contains("B")) {
            return true;
        } else if (first.contains("C")) {
            return true;
        } else if (first.contains("D")) {
            return true;
        } else if (first.contains("E")) {
            return true;
        } else if (first.contains("F")) {
            return true;
        } else if (first.equals("")) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        final String GOOD = "Good";
        final String INFECTED = "Infected!";
        StringJoiner sj = new StringJoiner("\n");
        for (int i = 0; i < n; i++) {
            String str = bf.readLine();
            //문자열은 {A, B, C, D, E, F} 중 0개 또는 1개로 시작해야 한다.
            int j = 0;
            String[] split = str.split("");
//            while () {
//                if (split[j] == "A") {
//                }
//            }

            if (HasContainsAtoF(str, 0)) {

                //그 다음에는 A가 하나 또는 그 이상 있어야 한다.
                //그 다음에는 F가 하나 또는 그 이상 있어야 한다.
                //그 다음에는 C가 하나 또는 그 이상 있어야 한다.
                //그 다음에는 {A, B, C, D, E, F} 중 0개 또는 1개가 있으며, 더 이상의 문자는 없어야 한다.

            }
        }
        System.out.println(sj);
    }
}
