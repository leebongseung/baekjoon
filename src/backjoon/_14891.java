package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 시작 시간 : 2시 24분
 * 종료 시간 : 3시 32분
 * <p>
 * 문제 분석
 * - 8개의 톱니를 가진 톱니바퀴가 4개 일렬로 연결
 * - 톱니는 N또는 S를 가짐
 * - 톱니바퀴에 번호가 매겨짐
 * - 왼쪽 톱니 1, 2, 3, 4 순으로
 * <p>
 * - 톱니를 K번 회전 시킴
 * - 톱니 회전은 한칸 기준!
 * - 회전은 시계방향, 반시계 방향 존재
 * <p>
 * 회전 조건
 * 1. 회전 시킬 톱니바퀴 결정!,
 * 2. 회전시킬 방향 결정
 * <p>
 * -
 */
public class _14891 {
    static List<GearWheel> lst;
    public static Map<Integer, Integer> map;
    public static boolean[] visited;

    static class GearWheel {
        List<Integer> lstWheel;

        GearWheel(String str) {
            lstWheel = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                lstWheel.add(str.charAt(i) - '0');
            }
        }

        public int get(int idx) {
            return lstWheel.get(idx);
        }

        public void rotation(int rotationDirection) {
            // 반 시계 방향 회전
            if (rotationDirection == -1) {
                Integer integer = lstWheel.remove(0);
                lstWheel.add(integer);
            }
            // 시계방향 회전
            else {
                Integer integer = lstWheel.remove(7);
                lstWheel.add(0, integer);
            }
        }
    }

    // 시계방향 회전 시킴 왼쪽, 오른쪽 확인하기!
    public static void rotationsWheelCheck(int WheelIdx, int rotationDirection) {
        if (WheelIdx == 0 && !visited[WheelIdx]) {
            visited[WheelIdx] = true;
            GearWheel gearWheelA = lst.get(0);
            GearWheel gearWheelB = lst.get(1);

            // 1번 톱니가 만약 같은 극이라면 반대방향으로 회전
            if (gearWheelA.get(2) != gearWheelB.get(6)) {
                rotationsWheelCheck(1, oppositeDirection(rotationDirection));
            }
        } else if (WheelIdx == 1  && !visited[WheelIdx]) {
            visited[WheelIdx] = true;
            GearWheel gearWheelA = lst.get(0);
            GearWheel gearWheelB = lst.get(1);

            // 0번 톱니가 다른 극이라면 반대 방향으로 회전
            if (gearWheelA.get(2) != gearWheelB.get(6)) {
                rotationsWheelCheck(0, oppositeDirection(rotationDirection));
            }

            // 2번 톱니가 다른 극이라면 반대 방향으로 회전.
            GearWheel gearWheelC = lst.get(2);
            if (gearWheelB.get(2) != gearWheelC.get(6)) {
                rotationsWheelCheck(2, oppositeDirection(rotationDirection));
            }
        } else if (WheelIdx == 2&& !visited[WheelIdx]) {
            visited[WheelIdx] = true;
            GearWheel gearWheelA = lst.get(1);
            GearWheel gearWheelB = lst.get(2);

            // 1번 톱니가 다른 극이라면 반대 방향으로 회전
            if (gearWheelA.get(2) != gearWheelB.get(6)) {
                rotationsWheelCheck(1, oppositeDirection(rotationDirection));
            }

            // 3번 톱니가 다른 극이라면 반대 방향으로 회전
            GearWheel gearWheelC = lst.get(3);
            if (gearWheelB.get(2) != gearWheelC.get(6)) {
                rotationsWheelCheck(3, oppositeDirection(rotationDirection));
            }
        } else if (WheelIdx == 3 && !visited[WheelIdx]) {
            visited[WheelIdx] = true;
            GearWheel gearWheelB = lst.get(2);
            GearWheel gearWheelC = lst.get(3);

            // 2번 톱니가 다른 극이라면 반대 방향으로 회전
            if (gearWheelB.get(2) != gearWheelC.get(6)) {
                rotationsWheelCheck(2, oppositeDirection(rotationDirection));
            }
        }
        map.put(WheelIdx, rotationDirection);
    }

    public static int sameDirection(int rotationDirection) {
        return rotationDirection == 1 ? 1 : -1;
    }

    public static int oppositeDirection(int rotationDirection) {
        return rotationDirection == 1 ? -1 : 1;
    }

    public static void main(String[] args) throws IOException {
        lst = new ArrayList<>();

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        // 입력받기
        for (int i = 0; i < 4; i++) {
            lst.add(new GearWheel(bf.readLine()));
        }


/*        for (GearWheel g : lst) {
            for (int num : g.lstWheel) {
                System.out.print(num);
            }
            System.out.println();
        }*/


        int k = Integer.parseInt(bf.readLine());
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int WheelIdx = Integer.parseInt(st.nextToken()) - 1;
            int rotationDirection = Integer.parseInt(st.nextToken()); // 1: 시계방향, -1 : 반시계 방향

            map = new HashMap<>();
            visited = new boolean[4];
            rotationsWheelCheck(WheelIdx, rotationDirection);

            for (Integer idx : map.keySet()) {
                int r_d = map.get(idx); // 회전방향
                GearWheel gearWheel = lst.get(idx);
                gearWheel.rotation(r_d);
            }

/*            for (GearWheel g : lst) {
                for (int num : g.lstWheel) {
                    System.out.print(num);
                }
                System.out.println();
            }
            System.out.println("===================");*/
        }

        // 점수 계산하기
        int res = 0;
        for (int i = 0; i < 4; i++) {
            if (i == 0) res += lst.get(0).get(0) == 0 ? 0 : 1;
            else if (i == 1) res += lst.get(1).get(0) == 0 ? 0 : 2;
            else if (i == 2) res += lst.get(2).get(0) == 0 ? 0 : 4;
            else res += lst.get(3).get(0) == 0 ? 0 : 8;
        }
        System.out.println(res);
    }
}
