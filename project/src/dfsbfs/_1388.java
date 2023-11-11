package dfsbfs;

/*  시작 시간 : 11시 9분
 *   종료 시간 :
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1388 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken()); // 행 개수
        int m = Integer.parseInt(st.nextToken()); // 열 개수

        List<List<String>> arr = new ArrayList<>();
        for(int i=0; i<n; i++){
            arr.add(new ArrayList<String>());
        }

        // 방문 처리
        boolean[][] isVisit = new boolean[n][m];
        for(int i =0; i<n; i++){
            Arrays.fill(isVisit[i], false);
        }


        // 나무판자 입력받기
        for(int i =0; i<n; i++){
            String sb = bf.readLine();
            for(int j=0; j< sb.length(); j++){
                arr.get(i).add(j, sb.substring(j, j + 1));
            }
        }

        int res = 0; // 나무 판자 개수
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!isVisit[i][j]) {
                    isVisit[i][j] = true;

                    Stack<String> stack = new Stack<>();
                    stack.push(arr.get(i).get(j));

                    // "-" 일 경우 인접한 열에 "-" 가 주어졌는지 확인
                    if (arr.get(i).get(j).equals("-")) {
                        int dy = j+1;
                        while(!stack.isEmpty()){
                            stack.pop();
                            if(dy >= m) break;
                            if(arr.get(i).get(dy).equals("-")){
                                isVisit[i][dy] = true;
                            } else{
                                break;
                            }
                            stack.push(arr.get(i).get(dy));
                            dy++;
                        }
                    }
                    // "|" 일 경우 인접한 행에 "|" 가 주어졌는지 확인
                    else if (arr.get(i).get(j).equals("|")) {
                        int dx = i+1;
                        while(!stack.isEmpty()){
                            stack.pop();
                            if(dx >= n) break;
                            if(arr.get(dx).get(j).equals("|")){
                                isVisit[dx][j] = true;
                            } else{
                                break;
                            }
                            stack.push(arr.get(dx).get(j));
                            dx++;
                        }
                    }
                    res++;
                }
            }
        }
        System.out.println(res);
    }
}
