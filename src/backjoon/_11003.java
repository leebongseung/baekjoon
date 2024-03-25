package backjoon;

import java.io.*;
import java.util.*;

/**
 * 시작 시간 : 15시 40분
 * 종료 시간 : 17시 22분
 *
 * 문제분석
 * - n이 5백만이하,
 * - L이 n 이하
 *
 * 트러블 슈팅
 * 1. 시간 초과
 *  - 원인 :
 *  - 해결방안 : ??
 * */
public class _11003 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken()); // i-l+1번째 전 index ~ i번째 중 최솟값을 구하면됨

        //dp 로 풀면될듯
        st = new StringTokenizer(bf.readLine());
        Deque<Node> lst = new LinkedList<>();
        for(int i=0; i<n; i++){
            int num = Integer.parseInt(st.nextToken());

            while(!lst.isEmpty() && num < lst.getLast().getValue()) {
                lst.removeLast();
            }

            lst.addLast(new Node(i, num));

            if(lst.getFirst().getIndex() <= i-l){
                lst.removeFirst();
            }

            // StringJoiner 를 쓰면 timeout이 난다.
            bw.write(String.valueOf(lst.getFirst().getValue()) + " ");
        }
        bw.flush();
        bw.close();
    }

    static class Node {
        private int index;
        private int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public int getValue() {
            return value;
        }
    }
}
