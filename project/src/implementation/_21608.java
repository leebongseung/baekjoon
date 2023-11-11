package implementation;

import org.w3c.dom.ls.LSException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*  시작 시간 : 9시 36분
*   종료 시간 : 결국 또 못풀었따..
* */
public class _21608 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

//        List<List<Integer>> studentsServey = new ArrayList<>();
        List<LinkedList<Integer>> studentsServey = new LinkedList<>();

        // 1. 선호도 조사하기
        for(int i =0; i<n*n; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int idx = Integer.parseInt(st.nextToken());
            studentsServey.add(new LinkedList<>());
            studentsServey.get(i).add(idx);

            for(int j =0; j< 4; j++){ // 좋아하는 학생 총 4명 조사하기
                int value = Integer.valueOf(st.nextToken());
                studentsServey.get(i).add(value);
            }
        }

        int[][] arrs = new int[n][n];
        int[][] topBottomLeftRight = {{-1,0},{1,0},{0, -1}, {0,1}};
        int max = 0;
        int[] currentIdx = new int[2];
        
        // 2. 빈공간 조회하고 첫번째 학생 주입하기
        for (int i=0; i<n; i++){
            for(int j =0; j<n; j++){
                int cnt = 0;

                for(int k = 0; k < topBottomLeftRight.length ; k++){
                    int dx = i + topBottomLeftRight[k][0];
                    int dy = j + topBottomLeftRight[k][1];

                    if(dx >= n || dx < 0 || dy >= n || dy < 0 ) continue;
                    if(arrs[dx][dy] == 0) cnt++;
                }
                if(cnt > max){
                    max = cnt;
                    currentIdx = new int[]{i,j};
                }
            }
        }
        // 3. 첫번째 학생 주입
        arrs[currentIdx[0]][currentIdx[1]] = studentsServey.get(0).get(0);


        // 4. 좌석 배정 진행하기
        for (int p=1; p < n*n; p++) {
            LinkedList<Integer> student = studentsServey.get(p);
//            System.out.println(student.get(0));
            int idx = student.get(0);

            // 4-1. 비어있는 칸중에 좋아하는 학생이 인접한 칸을 선택

            List<List<Integer>> arrsSide = new ArrayList<>();
            //top, bottom, left, right // 좋아하는 학생인접개수, 비어있는 칸개수, x, y


            // 완전 탐색
            for (int i=0; i<n; i++) {
                for (int j = 0; j < n; j++) {
                    int dx = i;
                    int dy = j;

                    // 빈자리가 아니라면 나가기!
                    if (arrs[dx][dy] != 0) continue;
                    arrsSide.add(new ArrayList<Integer>());
                    

                    // 빈자리일경우
                    // i. arrSide 값 초기화 진행
                    for(int k =0; k<4; k++){
                        arrsSide.get(arrsSide.size()-1).add(0);
                    }

                    // ii. x축, y축 값 넣기
                    arrsSide.get(arrsSide.size()-1).set(2, dx);
                    arrsSide.get(arrsSide.size()-1).set(3, dy);

                    // iii. cnt 구하기(상하좌우 탐색하기)
                    int cnt = 0;
                    for (int z = 0; z < topBottomLeftRight.length; z++) {
                        // 상하좌우 인덱스를 나타내는 변수 dx, dy
                        dx = arrsSide.get(arrsSide.size()-1).get(2) + topBottomLeftRight[z][0];
                        dy = arrsSide.get(arrsSide.size()-1).get(3) + topBottomLeftRight[z][1];

                        // 상하좌우가 인덱스를 벗어날 경우
                        if (dx >= n || dx < 0 || dy >= n || dy < 0) continue;
                        
                        // 상하좌우가 비어있다면
                        if (arrs[dx][dy] == 0) cnt++;
                        
                        // 상하좌우에 인접한 학생이 있을경우 [][0] +1 수행하기
                        if (student.contains(arrs[dx][dy])){
                            arrsSide.get(arrsSide.size()-1).set(0, arrsSide.get(arrsSide.size()-1).get(0)+1);
                        }
                    }
                    // [][1] 비어있는 좌석개수 넣기
                    arrsSide.get(arrsSide.size()-1).set(1, cnt);
                }
            }


            // 네개 방면 중 가장 큰 값을 구해야하는데
            // 2. 1을 만족하는 칸이 여러개인 경우, 비어있는 칸이 가장 많은 칸으로 선택
            // 3. 2를 만족하는 칸도 여러개인 경우, 행의 번호가 작은 칸으로 선택, 그러한 칸도 여러개이면 열의 번호가 가장 작은칸을 선택.

            // 스트림을 사용하여 정렬하고 결과를 새로운 리스트로 반환
            List<List<Integer>> sortedList = arrsSide.stream().sorted((o1, o2) -> {
                return !o1.get(0).equals(o2.get(0))? o2.get(0) - o1.get(0) :
                        !o1.get(1).equals(o2.get(1)) ? o2.get(1) - o1.get(1) :
                                !o1.get(2).equals(o2.get(2)) ? o1.get(2) - o2.get(2) : o1.get(3)-o2.get(3);
            }).collect(Collectors.toList());

//            Arrays.sort(arrsSide2, ((o1, o2) -> {
//                return o1[0] != o2[0]? o2[0] - o1[0] :
//                    o1[1] != o2[1] ? o2[1] - o1[1] :
//                    o1[2] != o2[2] ? o1[2] - o2[2] : o1[3]-o2[3];}));


//            System.out.println("fourSide[0] = " + sortedList.get(0).get(0));
//            System.out.println("fourSide[1] = " +  sortedList.get(0).get(1));
//            System.out.println("fourSide[2] = " +  sortedList.get(0).get(2));
//            System.out.println("fourSide[3] = " + sortedList.get(0).get(3));
            arrs[sortedList.get(0).get(2)][sortedList.get(0).get(3)] = idx;

        }

        int res = 0;
        // 6. 만족도 총합 구하기
        for (LinkedList<Integer> student : studentsServey) {
            for (int i=0; i<n; i++) {
                for (int j = 0; j < n; j++) {
                    if(arrs[i][j] != student.get(0)) continue;

                    int cnt = 0; //
                    for(int k=0; k<topBottomLeftRight.length; k++){
                        int dx = i + topBottomLeftRight[k][0];
                        int dy = j + topBottomLeftRight[k][1];

                        if (dx >= n || dx < 0 || dy >= n || dy < 0) continue;
                        if (student.contains(arrs[dx][dy])) cnt++;
                    }

                    if(cnt == 1) res += 1;
                    else if (cnt == 2) res += 10;
                    else if (cnt == 3) res += 100;
                    else if (cnt == 4) res += 1000;
                }
            }
        }

        System.out.println(res);

//        for (int[] dx : arrs) {
//            for (int dy : dx) {
//                System.out.print(dy + " ");
//            }
//            System.out.println();
//        }
    }


}
