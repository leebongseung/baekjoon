package backjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Iterator;

/**
 * 시작 시간 : 4시 30분
 * 종료 시간 : 5시 30분
 * */

public class _13305 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine()); // 도시 개수
        BigInteger[] distance = new BigInteger[n - 1]; // 거리
        BigInteger[] gas = new BigInteger[n]; // 리터당 가격
        String[] split = bf.readLine().split(" ");
        for (int i = 0; i < split.length; i++) {
            distance[i] = new BigInteger(split[i]); // 거리 짚어넣기
        }

        split = bf.readLine().split(" ");
        for (int i = 0; i < gas.length; i++) {
            gas[i] = new BigInteger(split[i]); // 거리 짚어넣기
        }

        // 1km 1리터 기름 사용
        // 각도시당 단 하나의 주유소 존재
        // 주유소마다 가격이 다름.
        BigInteger res = BigInteger.valueOf(0);
        BigInteger minGasPrice = gas[0];
        for (int i = 0; i < distance.length; i++) {
            minGasPrice = minGasPrice.min(gas[i]);
            res = res.add(distance[i].multiply(minGasPrice));
        }
        System.out.println(res);
    }
}
