
package DataStructure._2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*  시작 시간 : 10시 10분
 *   제출 시간 : 12시 23분
 * */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());


        LinkedList<int[]> towersHeightAndIdx = new LinkedList<>(); // 0: Height, 1: idx;
        LinkedList<Integer> towerReceptionIdx = new LinkedList<>(); // 결과를 저장하는 변수

        for(int i =0; i<n; i++) {
            int curTowerValue = Integer.parseInt(st.nextToken()); // 현재 타워 높이
            while(!towersHeightAndIdx.isEmpty()){
                int[] preTower = towersHeightAndIdx.removeLast();
                if(preTower[0] > curTowerValue){
                    towersHeightAndIdx.addLast(preTower);
                    towerReceptionIdx.addLast(preTower[1]+1);
                    break;
                }
            }

            if(towersHeightAndIdx.isEmpty()){
                towerReceptionIdx.addLast(0);
            }
            towersHeightAndIdx.addLast(new int[]{curTowerValue, i});
        }

        StringBuffer sb = new StringBuffer();
        while(!towerReceptionIdx.isEmpty()){
            sb.append(towerReceptionIdx.removeFirst() + " ");
        }
        System.out.println(sb);
    }
}