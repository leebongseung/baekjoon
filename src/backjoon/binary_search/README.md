## 이진 탐색
<details>
<summary>
<a href="_10815.java">10815 - 숫자 카드(소요시간: 20분)</a>
</summary> 
<ul>
<li><p>풀이 방법</p>
<ul>
<li>특정 문자에 해당 문자가 있는지 비교하는 문제였기 때문에 Hash 자료형을 써서 문제를 해결 하려 했음,</li>
</ul>
</li>
<li><p>어려웠던 점</p>
<ul>
<li>Hash 자료형은 Stream을 적용하면 오히려 Hash처럼 O(1) 탐색이 되지않음. 그래서 Hash자료형은 .contains(Object object) 로 탐색하는게 O(1) 효과가 나온다</li>
</ul>
</li>
<li><p>배운점 </p>
<ul>
<li>contains(Object object)</li>
</ul>
</li>
</ul>
</details>

<details>
<summary>
<a href="_2512.java">2512 - 예산(소요시간: 120분)</a>
</summary> 
<ul>
<li><p>풀이 방법</p>
<ul>
<li>예산 배정 중 가장 많이 예산을 받는 지역의 예산을 출력해야 한다.</li>
<li>그래서 고안한 방법이 현재 예산을 가장 많이 받는 값을 기준으로 배정된 예산을 초과하는지 비교하도록 이진 탐색을 수행</li>
<li>left = 0, right = max값 으로 이진 탐색을 수행한다</li>
</ul>
</li>
<li><p>어려웠던 부분</p>
<ul>
<li>예산의 최댓값을 구해나가면서 요청한 예산에만 초첨을 두고 배정된 예산에 초점을 두지 않았었는데 그 과정에서 생각하기 까지가 어려웠다.</li>
</ul>
</li>
<li><p>배운점</p>
<ul>
<li>이진 탐색은 트리구조!이고 반복을 최대 0(logn)의 시간 복잡도를 가짐</li>
</ul>
</li>
</ul>

</details>