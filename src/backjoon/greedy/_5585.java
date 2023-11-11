package backjoon.greedy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*  시작 시간 : 11시 51분
*   종료 시간 : 11시 59분
*
* */
public class _5585 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int myMoney = 1000;
        int num = Integer.parseInt(bf.readLine()); //계산해야할 값
        int[] money = {500, 100, 50, 10, 5, 1};

        myMoney -= num;

        int res =0 ;
        for(int i =0; i<money.length; i++){
            if(myMoney <= 0) break;

            if(myMoney / money[i] != 0){
                res += myMoney / money[i];
                myMoney = myMoney % money[i];
//                System.out.println("money = " + money[i]);
//                System.out.println("res = " + res);
            }
        }

        System.out.println(res);
    }
}
