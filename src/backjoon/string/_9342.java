package backjoon.string;

import javax.imageio.plugins.tiff.FaxTIFFTagSet;
import javax.swing.text.StyledEditorKit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringJoiner;

/*  시작 시간: 6시 00분
 *   종료 시간:
 * */
public class _9342 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        char[] passWord = {'A', 'B', 'C', 'D', 'E', 'F'};
        StringJoiner sj = new StringJoiner("\n");
        for (int i = 0; i < n; i++) {
            // A~F 로 시작/ A 1개이상 / F 1개 이상/ C 1개 이상/ A~F 0개또는 1개, 더이상 문자 X
            String str = bf.readLine();
            StringBuilder tmp = new StringBuilder();
            tmp.append(str.charAt(0));
            for(int j=1; j <str.length(); j++){
                if(str.charAt(j) == str.charAt(j-1)){
                    continue;
                }
                tmp.append(str.charAt(j));
            }
            str = String.valueOf(tmp);

            boolean isInfected = false;
            for (char c : passWord) {
                if (str.charAt(0) == c) {
                    // 첫번째 문자열에 A한개 이상
                    if (str.charAt(0) == 'A' && str.charAt(1) == 'F' || str.charAt(1) == 'A' && str.charAt(2) == 'F') {
                        // 첫번째 경우에는 3번째 인덱스 값존재, 4번째부터 null
                        if (str.charAt(2) == 'C' && str.length() <= 4) {
                            if (str.length() == 3) {
                                sj.add("Infected!");
                                isInfected = true;
                                break;
                            }
                            for (char c1 : passWord) {
                                if (str.charAt(3) == c1) {
                                    sj.add("Infected!");
                                    isInfected = true;
                                    break;
                                }
                            }
                        } else if (str.charAt(3) == 'C' && str.length() <= 5) {
                            if (str.length() == 4) {
                                sj.add("Infected!");
                                isInfected = true;
                                break;
                            }
                            for (char c1 : passWord) {
                                if (str.charAt(4) == c1) {
                                    sj.add("Infected!");
                                    isInfected = true;
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
            }
            if(!isInfected) sj.add("Good");
        }
        System.out.println(sj);
    }
}
