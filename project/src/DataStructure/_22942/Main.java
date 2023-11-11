package DataStructure._22942;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        LinkedList<int[]> arr = new LinkedList<>();


        for(int i = 0; i< n; i++){
            String iStr = bf.readLine();
            StringTokenizer st = new StringTokenizer(iStr);

            int cur_mid = Integer.parseInt(st.nextToken());
            int cur_range = Integer.parseInt(st.nextToken());

            int left = cur_mid - cur_range;
            int right = cur_mid + cur_range;

            arr.addLast(new int[]{left, i});
            arr.addLast(new int[]{right, i});

        }

        // left 오름차순 정렬
        Collections.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]){
                    if(o1[1] > o2[1]){
                        return o2[1] - o1[1];
                    } else{
                        return o1[1] - o2[1];
                    }
                }
                return o2[0]- o1[0];
            }
        });


        LinkedList<Integer> stack = new LinkedList<>();
        while (!arr.isEmpty()){
            int[] curCircle = arr.removeLast();
            if(stack.isEmpty()) {
                stack.push(curCircle[1]);
                continue;
            }

            int preCircleIdx = stack.peek();
            if(curCircle[1] == preCircleIdx){
                stack.pop();
            } else{
                stack.push(curCircle[1]);
            }
        }

        if(!stack.isEmpty()){
            System.out.println("NO");
        }else{
            System.out.println("YES");
        }
    }
}
