package backjoon.DataStructrue2;

import java.io.*;
import java.util.*;

/*  시작 시간 : 9시 00 분
*   종료 시간 : 11시 00 분
*
*   입력 최대 10000 종, 1,000,000 그루
*   출력 : 사전 순 출력, 그 종이 차지하는 비율을 백분율로 소수점 4자리 까지 반환
* */

// 입력 최대 10000 종
// 최대 1,000,000 그루

// 출력
// 이름을 사전 순 출력 - 그 종이 차지하는 비율을 백분율로 소수점 4자리까지 반올림

public class _4358 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        Map<String, Double> trees = new TreeMap<>((s1, s2) -> s1.compareTo(s2));

        String tree;
        Double size = 0.0; // Variable used in lambda expression should be final or effectively final
        while((tree = bf.readLine()) != null){
//                System.out.println(tree + " " + tree.length());
                trees.merge(tree, 1.0, Double::sum);
                size++;
        }
        bf.close();

        Double finalSize = size; // 람다식에서 사용될 변수의 경우 값이 변경되면 안되서 이러한 식으로 사용함
        trees.entrySet().forEach(stringDoubleEntry -> {
                sb.append(stringDoubleEntry.getKey() + " " + String.format("%.4f" ,stringDoubleEntry.getValue() / finalSize * 100)+"\n");
        });

        System.out.print(sb);
    }
}
