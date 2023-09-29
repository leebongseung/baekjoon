package _22942;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        LinkedList<LinkedList<int[]>> arr = new LinkedList<>();

        for(int i=0; i< 2_000_000; i++){
            arr.add(new LinkedList<>());
        }



        for(int i = 0; i< n; i++){
            String iStr = bf.readLine();

            int cur_mid = Integer.parseInt(iStr.split(" ")[0]);
            int cur_range = Integer.parseInt(iStr.split(" ")[1]);

            int left = cur_mid - cur_range + 1_000_000;
            int right = cur_mid + cur_range + 1_000_000;

            if(!arr.get(left).isEmpty() || !arr.get(right).isEmpty()){
                System.out.println("NO");
                return;
            }
            for(int j = left+1; j < right; j++){
                if(!arr.get(j).isEmpty()){
                    for(int k =0; k < arr.get(j).size(); k++){
                        int[] preCircle = arr.get(j).get(k); // 0: mid, 1: range
                        int d = Math.abs(preCircle[0] - cur_mid);
                        if(!(cur_range + preCircle[1] < d || Math.abs(cur_range - preCircle[1]) > d)){
                            System.out.println("NO");
                            return;
                        }
                    }
                }
            }
            arr.get(left).addLast(new int[]{cur_mid, cur_range});
            arr.get(right).addLast(new int[]{cur_mid, cur_range});
        }

        System.out.println("YES");
    }
}
