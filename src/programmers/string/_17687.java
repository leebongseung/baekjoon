package programmers.string;
/*
    시작 시간 : 8시 50분
    종료 시간 : 9시 50분
*/
public class _17687 {
    class Solution {
        public boolean is_prime_number(Long x){
            if(x == 0 || x == 1){
                return false;
            }

            for(int i = 2; i<= Math.sqrt(x); i++){
                if(x % i == 0) return false;
            }
            return true;
        }


        public int solution(int n, int k) {

            StringBuffer sb = new StringBuffer();
            while(n > k){
                sb.append(Integer.toString(n % k));
                if(n / k <= k){
                    sb.append(Integer.toString(n / k));
                }
                n /= k;
            }

            String str = sb.reverse().toString();
            sb = new StringBuffer();
            int cnt = 0;
            String[] arr = str.split("0");
            for(String i : arr){
                // 0P0 처럼 양쪽에 0이 있는 경우
                if(!i.equals("") && !sb.toString().contains("0") && is_prime_number(Long.parseLong(i))){
                    cnt ++;
                }
            }



            return cnt;
        }
    }
}
