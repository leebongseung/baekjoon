
package _1158;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 원을 이루며 앉아있는 사람들
        int k = sc.nextInt();
        Queue<Integer> q = new LinkedList<>();

        int value = k - 1;
        for(int i = 1; i<= n; i++){
            q.add(i);
        }

        System.out.print("<");
        while(q.size() > 1) {
            for(int i =0; i< k-1; i++){ // 제거해야할 k 가 아닐경우 다시 add 제거하고 다시 입력시킴 queue 선입선출 자료구조를 이용.
                q.add(q.poll());
            }
            System.out.print(q.poll() + ", ");
        }
        System.out.println(q.poll() + ">");
    }
}