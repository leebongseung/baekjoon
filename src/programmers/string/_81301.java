package programmers.string;

import java.io.*;
import java.util.*;



public class _81301
{

    class Solution {
        public int solution(String s) {
            StringBuffer total = new StringBuffer();
            // 문자와 숫자의 구분 방향을 알아야함.
            for(int i = 0; i< s.length() ; i ++){
                char val = s.charAt(i);
                if(val >= '0' && val <= '9'){
                    // Character to String 못함
                    total.append(Character.toString(val));
                }
                else if (val == 'z'){
                    i += 3;
                    total.append(0);
                } else if (val == 'o'){
                    i += 2;
                    total.append(1);
                } else if (val == 't' && s.charAt(i+1) == 'w'){
                    i += 2;
                    total.append(2);
                } else if (val == 't' && s.charAt(i+1) == 'h'){
                    i += 4;
                    total.append(3);
                } else if (val == 'f' && s.charAt(i+1) == 'o'){
                    i += 3;
                    total.append(4);
                } else if (val == 'f' && s.charAt(i+1) == 'i'){
                    i += 3;
                    total.append(5);
                } else if (val == 's' && s.charAt(i+1) == 'i'){
                    i += 2;
                    total.append(6);
                } else if (val == 's' && s.charAt(i+1) == 'e'){
                    i += 4;
                    total.append(7);
                } else if (val == 'e'){
                    i += 4;
                    total.append(8);
                } else if (val == 'n'){
                    i += 3;
                    total.append(9);
                }
            }


            // String to Int 못함
            return Integer.parseInt(total.toString());
        }
    }
}
