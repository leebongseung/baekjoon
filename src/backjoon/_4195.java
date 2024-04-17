package backjoon;

import java.io.*;
import java.util.*;

/**
 * 시작 시간 : 8시 35분
 * 종료 시간 : ??
 *
 * 문제분석
 * - 친구관계가 순서대로 주어질때,
 * - 두사람의 친구 네트워크에 몇명이 있는지 구하는 프로그램
 * - 친구 네트워크란 친구 관계만으로 이동하는 사이
 *
 * 유니온파인드로 풀어보쟝
 *
 * */
public class _4195 {
    public static Map<String, Integer> map; // 유니온 파인드 인덱스 확인하기!
    public static List<Integer> arr;
    public static List<Integer> idxCnt;
    public static int f;
    public static int idx;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringJoiner sj = new StringJoiner("\n");
        int t = Integer.parseInt(bf.readLine()); // 테케 개수
        for(int tCase = 0; tCase < t; tCase++) {
            f = Integer.parseInt(bf.readLine()); // 친구 관계 수, 10만 미만
            map = new HashMap<>();
            arr = new ArrayList<>();
            idxCnt = new ArrayList<>();
            idx = 0;

            for(int i=0; i<f; i++){
                StringTokenizer st =  new StringTokenizer(bf.readLine());
                String str1 = st.nextToken();
                String str2 = st.nextToken();

                if(!map.containsKey(str1)) {
                    map.put(str1, idx);
                    idxCnt.add(1);
                    arr.add(idx++);
                }
                if(!map.containsKey(str2)) {
                    map.put(str2, idx);
                    idxCnt.add(1);
                    arr.add(idx++);
                }

                int num1 = map.get(str1);
                int num2 = map.get(str2);

                union(num1, num2);
                int topNode = find(num1);
                sj.add(String.valueOf(idxCnt.get(topNode)));
            }
        }
        bw.write(sj.toString());
        bw.flush();
        bw.close();
    }

    public static void union(int a, int b){
        int topA = find(a);
        int topB = find(b);
        if(topA != topB) {
            arr.set(topB, topA);
            idxCnt.set(topA, idxCnt.get(topA) + idxCnt.get(topB));
            idxCnt.set(topB, 0);
        }
    }

    public static int find(int num){
        if(num == arr.get(num)) return num;
        else{
            int nextNode = arr.get(num);
            int topNode = find(nextNode);
            arr.set(num, topNode);
            return topNode;
        }
    }
}
