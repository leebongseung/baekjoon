
package backjoon.DataStructure._2164;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*  시작 시간 : 19시 6분
 *   종료 시간 : 19시 15분
 * */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Queue<Integer> cards = new LinkedList<>();
        for(int i = 1; i<= n; i++){
            cards.offer(i);
        }

        while(cards.size() >= 2){
            cards.poll();
            cards.offer(cards.poll());
        }
        System.out.println(cards.poll());
    }
}