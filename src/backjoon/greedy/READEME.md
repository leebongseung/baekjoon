## Greedy
<details>
<summary>
<a href="_13305.java">13305 - 주유소  (60분)</a>
</summary> 
<ul>
<li><p>풀이과정</p>
<ul>
<li>long으로 푸니까 곱셈과정에서 제대로 계산이 안됨. 왜냐하면 10억이 최대 100,000개가 나올 수 있어서 이렇게 풀경우에 58점이 나온다</li>
<li>그래서 생각한 방법은 BigDecimal로 풀면 100점 나옴</li>
</ul>
</li>
<li><p>어려운점</p>
<ul>
<li>2중 for문으로 minGas를 계산했는데 타임에러가 나서 풀 수 없었음.</li>
</ul>
</li>
<li><p>배운점</p>
<ul>
<li>long도 한계가 존재한다는점 알았다.</li>
<li><strong><code>long</code></strong>의 최댓값: 9,223,372,036,854,775,807 (즉, $2^{63}−1$)</li>
<li><strong><code>BigDecimal</code></strong>의 최댓값: 제한 없음 (시스템 메모리와 JVM 한계에 의존)</li>
</ul>
</li>
</ul>



</details>

<details>
<summary>
<a href="_19941.java">19941 - 햄버거 분배  (40분)</a>
</summary> 
<ul>
<li><p>풀이과정</p>
<ul>
<li>문자열이 햄버거일 때 현재위치-k번째 ~ 현재위치 +k번째에 안먹은 사람이 존재하는지 확인하며 완전탐색</li>
</ul>
</li>
<li><p>어려운점</p>
<ul>
<li>처음에는 반복문을 이상하게 적어서 풀지못했다</li>
</ul>
</li>
<li><p>배운점</p>
</li>
</ul>


</details>



