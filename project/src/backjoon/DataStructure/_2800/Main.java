
package backjoon.DataStructure._2800;

/*  시작 시간 : 10시 10분
 *   종료 시간 : 11시 50분
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String str = bf.readLine();
        Stack<Integer> leftthesesIdx = new Stack<>(); // 괄호의 인덱스 번호를 짚어넣기.
        ArrayList<int[]> parenthesesIdx = new ArrayList<>();

        for(int i =0; i < str.length(); i++){
            if (str.charAt(i) == '('){
                leftthesesIdx.push(i);
                continue;
            } else if(str.charAt(i) == ')'){
                int leftThsisIdx = leftthesesIdx.pop();
                int rightThsisIdx = i;
                parenthesesIdx.add(new int[]{leftThsisIdx, rightThsisIdx});
                continue;
            }
        }


        //부분집합 구하면서 출력하기!
        int subStrSize =(int)Math.pow(2.0, parenthesesIdx.size());
        ArrayList<String> subStr = new ArrayList<>();
        for(int i = 1; i < subStrSize; i++){
            ArrayList<int[]> newSub = new ArrayList<>();
            for(int j = 0; j < parenthesesIdx.size(); j++){
                if((i & 1 << j) != 0){
                    newSub.add(parenthesesIdx.get(j));
                }
            }
            //문자열 출력하기
            StringBuilder newSubStr = new StringBuilder(str);
            for(int[] removeStr: newSub){
                for(int j=0; j<removeStr.length; j++){
                    newSubStr.setCharAt(removeStr[j], ' '); // '(' 또는 ')' 를 ' ' 로 대체
                }
            }


            if(!subStr.contains(newSubStr.toString().replaceAll(" ", ""))){
                subStr.add(newSubStr.toString().replaceAll(" ", "")); // ' '=>'' 로 공백 제거
            }
        }

        // 사전 순으로 정렬하기
        Collections.sort(subStr);
        for(String sub : subStr){
            System.out.println(sub);
        }

    }
}