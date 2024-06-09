package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 11시 52분
 * 종료 시간 : ??
 * <p>
 * 문제분석
 * - 어른상어가 마법사가 되어 파이어볼 배움
 * - n*n 격자에 파이어볼 m개를 발사
 * - 위치(r, c), 질량 m, 방향 d, 속력 s
 * - 1~n번까지 번호가 매겨져있꼬, 1번 행은 n번과 연결되어있음. 1번열은 n번 열과 연결되어 있음
 * <p>
 * - 모든 파이어볼이 자신의 방향 d로 속력 s 만큼 이동한다
 * - 이동 중 에는 같은 칸에 여러 개의 파이어볼이 존재함
 * - 이동 끝난 후 2개 이상의 파이어볼이 있는 칸에서는 다음일이 발생
 * 1. 같은 칸에 파이어볼을 모두 하나로 합치기
 * 2. 파이어볼은 4개의 파이어볼로 나누기
 * 3. 나누어진 파이어볼의 질량, 속력, 방향은 다음과 같음
 * - 각각 질량 =  합쳐진 파이어볼 질량/5
 * - 각각 속력 = 합쳐진 파이어볼 속력의 합/ 합쳐진 파이어볼 개수
 * - 파이어볼의 방향이 모두 홀수거나, 짝수일 경우 방향은 0, 2, 4, 6 이 되고, 그렇지 않다면 1, 3, 5, 7 이 됨
 * 4. 질량이 0인 파이어볼은 소멸되어 없어짐
 * <p>
 * 마법사 상어가 k번 명령한 후, 남아있는 파이어볼 질량의 합은?
 */
public class _20056 {
    public void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        List<Fireball>[][] lst = new ArrayList[n][n];


        for (int i = 0; i < m; i++) {
            //위치(x, y), 질량 m, 방향 d, 속력 s
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            Fireball fireball = new Fireball(weight, direction, speed);

            lst[x][y].add(fireball);
        }

        // 마법사 상어가 k번 명령후 이동하기
        for (int i = 0; i <= k; i++) {
            // 1. 모든 파이어볼이 방향 d로 속력 s 만큼 이동
            List<Fireball>[][] tmp = new ArrayList[n][n]; // 이동 후 저장하는 배열
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    for (Fireball f : lst[x][y]) {
                        //방향 d로 속력 s 만큼 이동
                        Direction d = Direction.values()[f.direction];
                        int dx = x + (d.x * f.speed);
                        int dy = y + (d.y * f.speed);

                        dx = Math.abs(dx) % n;
                        dy = Math.abs(dy) % n;
                        tmp[dx][dy].add(f);
                    }
                }
            }
            lst = tmp.clone();

            // 2. 2개 이상의 파이어볼이 있는 칸인지 확인하기
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    if (lst[x][y].size() > 1) {

                        int fireballCnt = 0;
                        int weightSum = 0;
                        int directionSum = 0;
                        int speedSum = 0;
                        // 2-1. 같은 칸에 파이어볼 하나로 합치기
                        for (Fireball f : lst[x][y]) {
                            weightSum += f.weight;
                            directionSum += f.direction;
                            speedSum += f.speed;
                            fireballCnt++;
                        }

                        lst[x][y] = new ArrayList<>();

                        // 만약 질량이 5의 배수라면 파이어볼은 소멸
                        if (weightSum / 5 == 0) {
                            continue;
                        }

                        // 2-2. 나누어진 파이어볼의 질량, 속력, 방향 구해서 4개로 나눔
                        for (int z = 0; z < 4; z++) {
                            lst[x][y].add(new Fireball(
                                    weightSum / 5,
                                    speedSum / fireballCnt,
                                    // 짝수일경우 방향은 0, 2, 4, 6이 되고,
                                    // 그렇지 않으면 1, 3, 5, 7이 된다.
                                    directionSum / 2 == 0 ? z * 2 : z * 2 + 1
                            ));
                        }
                    }
                }
            }
        }

        // 남아 있는 파이어볼 질량의 합을 구하기
        int weightSum = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                for (Fireball f : lst[x][y]) {
                    weightSum += f.weight;
                }
            }
        }

        System.out.println(weightSum);
    }

    enum Direction {
        ZERO(-1, 0), ONE(-1, 1), TWO(0, 1), THREE(1, 1),
        FOUR(1, 0), FIVE(1, -1), SIX(0, -1), SEVEN(-1, -1);

        private final int x, y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Fireball {
        int weight, direction, speed;

        public Fireball(int weight, int direction, int speed) {
            this.weight = weight;
            this.direction = direction;
            this.speed = speed;
        }
    }
}
