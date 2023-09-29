
package _2606;

/* 시작시간 : 10시 30분
 *  종료시간 : 11시 6분
 * */
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static int DFS(int[][] graph, boolean[] visited){
        int res = -1; // 시작노드는 카운터안함 -1
        int edge = graph[0].length;
        visited[0] = true; // 2차원 배열에서 초기화된 값이 0임으로 예외 처리
        visited[1] = true; // 시작 노드 방문 처리

        Stack<Integer> stack = new Stack<>();
        stack.push(1);

        while(!stack.isEmpty()){ // 연결된 노드가 없을때 까지 반복
            int num = stack.pop();
            res++;
            for(int i =1; i < edge; i++){
                if(graph[num][i] != 0 && !visited[graph[num][i]]){ // 예를들어 num = 1 이라면 graph[1][i~edge-1]까지 반복하면서 연결된 노드 있다면, 방문안했다면 방문.
                    stack.push(graph[num][i]);
                    visited[graph[num][i]] = true;
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine()) +1; // 노드 인덱스 :  1 ~ n-1
        int edge = Integer.parseInt(sc.nextLine()) + 1 ; //간선 인덱스 : 1 ~ edge-1
        int[][] graph = new int[n][edge]; //그래프
        boolean[] visited = new boolean[n]; //방문 처리
        Arrays.fill(visited, false);

        for(int i =1; i < edge; i++){ // 예를들어 1와 2가 입력된다면 1의 연결된 간선에는 2 , 2와 연결된 간선에는 1 이 존재함.
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph[x][i] = y; // 위의 예로 graph[1][i] = 2;
            graph[y][i] = x; //         graph[2][i] = 1;
            sc.nextLine();
        }
        int res =DFS(graph, visited);
        System.out.println(res);

    }
}