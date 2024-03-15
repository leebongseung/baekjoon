    package backjoon.sort;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.*;

    /*  시작 시간 : 3시 25분
    *   종료 시간 : 3시 32분
    * */
    public class _2750 {
        public static void main(String[] args) throws IOException {
            // 풀이는 버블 정렬 x, 삽입 정렬 혹은 그 병합 정렬을 하거나 해야한다.
            // 문제에서는 삽입 정렬을 했으니 삽입으로 풀어보자.
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

            int n = Integer.parseInt(bf.readLine());
            List<Integer> list = new ArrayList<>();
            for(int i =0; i<n; i++){
                list.add(Integer.parseInt(bf.readLine()));
            }
            Collections.sort(list);

            StringJoiner sj = new StringJoiner(" ");
            for (Integer integer : list) {
                sj.add(Integer.toString(integer));
            }

            System.out.println(sj);
        }
    }
