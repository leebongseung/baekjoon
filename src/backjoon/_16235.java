package backjoon;

import java.lang.reflect.Array;
import java.util.PriorityQueue;

/**
 * 시작 시간 : 6시 42분
 * 종료 시간 : 7시 33분
 *
 * 문제 분석
 * - 0.3초.. 뜨헉
 * - 메모리 512
 *
 * - n*n 크기의 땅 존재
 * - 각각의 칸은 (r, c)로 나타냄
 * - S2D2는 땅의 양분을 조사해 상도에게 전송함.
 * - 모든 칸에 대해 조사한다.
 * - 가장 처음에 양분은 모든칸에 5만큼 들어 있음.
 *
 * - 나무 재태크
 * - M개의 나무를 구매해 땅에 심음
 * - 4계절을 보내며 아래와 같은 걸 반복함
 *      - 봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이 1증가
 *      - 나이 어린 나무부터 양분 먹음
 *      - 양분이 부족해 자신의 나이만큼 양분을 못먹으면 죽음
 *
 *      - 여름에는 봄에 죽은 나무가 양분으로 변함
 *      - 죽은 나무 나이 / 2 만큼 양분이 추가됨
 *      - 소수점 아래는 버림
 *
 *      - 가을 에는 나무 번식
 *      - 나무의 나이가 5의 배수여야함.
 *      - 인접한 8 칸에 나이가 1인 나무가 생김
 *      - 상도에 벗어난 땅은 나무 안생김
 *
 *      - 겨울에는 S2D2가 땅에 양분 추가함.
 *      - 각 칸에 추가되는 양분의 양은 A[r][c]이고
 *      - 입력으로 주어짐
 *
 * - 결과 : K년이 지난 후 상도의 땅에 살아있는 나무의 개수는 ?
 *
 *
 * - 중간 정리
 *  - PQ는 인덱스를 지정을 몬한다.. 이거 너무 슬픔 특정 INDEX 값을 지워야하는데
 *  - 봄, 여름만 수정하면 될 것 같다. 이상
 * */
import java.util.*;
import java.io.*;


public class _16235 {
    public static int n;
    public static int m; // 초기의 나무 개수
    public static int k;
    public static int[][] s2d2;
    public static int[][] nutrientsArr; // 양분 배열
    public static List<List<PriorityQueue<Integer>>> lst = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // 양분 배열 초기화 하기
        nutrientsArr = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(nutrientsArr[i], 5);
        }

        // s2d2 로봇 양분 세팅하기
        s2d2 = new int[n][n];
        for (int x = 0; x < n; x++) {
            st = new StringTokenizer(bf.readLine());
            for (int y = 0; y < n; y++) {
                s2d2[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        // 나무 초기화
        for (int i = 0; i < n; i++) {
            lst.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                lst.get(i).add(new PriorityQueue<>());
            }
        }

        // 나무 심기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int treeAge = Integer.parseInt(st.nextToken());
            lst.get(x).get(y).add(treeAge);
        }

//        for (List<List<Integer>> priorityQueues : lst) {
//            for (List<Integer> priorityQueue : priorityQueues) {
//                System.out.print("(");
//                for (Integer integer : priorityQueue) {
//                    System.out.print(integer + ", ");
//                }
//                System.out.print(")");
//            }
//            System.out.println();
//        }
//        System.out.println("===========");

        for (int i = 0; i < k; i++) {

            Queue<int[]> springQ = new LinkedList<>(); // 0 : x좌표, 1:y 좌표, 2: 번식 개수.
            // 봄
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    int tmpNutrientsCnt = 0; // 죽은 나무 영양분 저장하기
                    int breedingDogs = 0; // 가을 : 번식 횟수 저장하기
                    PriorityQueue<Integer> tmp = new PriorityQueue<>();
                    for (int treeAge : lst.get(x).get(y)) {
                        int nutrient = nutrientsArr[x][y];
                        // 자신의 나이만큼 양분이 있는지 확인하기
                        if (treeAge > nutrient) {
                            // 여름
                            // 죽은 나무 나이 / 2 만큼 양분이 추가됨, 소수점 아래는 버림
                            tmpNutrientsCnt += (int) Math.floor(treeAge / 2.0);
                            continue;
                        }
                        nutrientsArr[x][y] -= treeAge;
                        tmp.add(treeAge + 1);

                        if ((treeAge + 1) % 5 == 0) breedingDogs++;
                    }
                    lst.get(x).remove(y);
                    lst.get(x).add(y, tmp);

                    // 여름
                    nutrientsArr[x][y] += tmpNutrientsCnt;

                    // 가을 큐 짚어넣기
                    if (breedingDogs > 0) springQ.add(new int[]{x, y, breedingDogs});

                    //겨울에는 S2D2가 땅에 양분 추가함.
                    nutrientsArr[x][y] += s2d2[x][y];
                }
            }

            // 가을 + 겨울에는 S2D2가 땅에 양분 추가함.
            // 나무의 나이가 5의 배수여야함
            // 인접한 8 칸에 나이가 1인 나무가 생김
            // 상도에 벗어난 땅은 나무 안생김
            while (!springQ.isEmpty()) {
                int[] poll = springQ.poll();
                int x = poll[0];
                int y = poll[1];
                int breedingDogs = poll[2];
                for (Direction d : Direction.values()) {
                    int dx = x + d.getX();
                    int dy = y + d.getY();

                    if (dx < 0 || dx >= n || dy < 0 || dy >= n) continue;
                    for (int z = 0; z < breedingDogs; z++) {
                        lst.get(dx).get(dy).add(1);
                    }
                }
            }

//            for (List<List<Integer>> priorityQueues : lst) {
//                for (List<Integer> priorityQueue : priorityQueues) {
//                    System.out.print("(");
//                    for (Integer integer : priorityQueue) {
//                        System.out.print(integer + ", ");
//                    }
//                    System.out.print(")");
//                }
//                System.out.println();
//            }
//            System.out.println("-------------------------------------");
//
//            for (int[] ints : nutrientsArr) {
//                for (int anInt : ints) {
//                    System.out.print(anInt + " ");
//                }
//                System.out.println();
//            }
//            System.out.println("============================================");
        }

        // 살아있는 나무 개수 세기
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res += lst.get(i).get(j).size();
            }
        }

        System.out.println(res);


    }

    public static enum Direction {
        topLeft(-1, -1),
        top(-1, 0),
        topRight(-1, 1),
        left(0, -1),
        right(0, 1),
        bottomLeft(1, -1),
        bottom(1, 0),
        bottomRight(1, 1);

        private final int x;
        private final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
