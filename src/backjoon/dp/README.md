## DP
<details>
<summary>
<a href="_12865.java">12865 - 평범한 배낭 (120분)</a>
</summary> 
<ul>
<li>풀이 방법<ul>
<li>그림을 그려가면서 dp를 적용하는 방법에 대하여 구상하기</li>

![image](https://github.com/leebongseung/coding-test/assets/101985441/cbfce34a-de76-4602-82f4-e79397394bda)

</ul>
</li>
</ul>
<ul>
<li><p>어려웠던 점</p>
<ul>
<li>구현하는 과정을 1차원 배열로 하는 과정에서 tmp라는 1차원배열도 함께 사용하였는데 얕은 복사로 참조되었다는걸 디버깅을 통해서 알았다. 그래서 앞으로 더 주의 해야할 것 같다. </li>
</ul>
</li>
<li><p>배운 점</p>
<ul>
<li>clone의 중요성</li>
</ul>
</li>
</ul>




</details>

<details>
<summary>
<a href="_24416.java">24416 - 피보나치 수1(18분)</a>
</summary> 
<ul>
<li>풀이 방법<ul>
<li>n≥ 5 크고 n≤ 40보다 작다 ⇒ int 자료형으로 해결가능하다</li>
<li>피보나치 <strong>F(47) = 2,971,215,073 으로 long으로 해결해야함.</strong></li>
</ul>
</li>
<li><p>어려웠던 점</p>
<ul>
<li>재귀적 ⇒ 메모제이션으로 변경하기 이것이 어려움.</li>
</ul>
</li>
<li><p>배운점</p>
<ul>
<li>메모제이션을 이용하여 arr에 담아 가면서 이전 값과 , 그 다음해를 활용하여 현재 피보나치의 값을 구하였다.</li>
</ul>
</li>
</ul>

</details>

<details>
<summary>
<a href="_11726.java">11726 - 2×n 타일링(60분)</a>
</summary> 
<ul>
<li><p>풀이 방법</p>
<ul>
<li><p>n-1번째 결과와 n-2번으로 점화식을 구성하였습니다.</p>
</li>

![image](https://github.com/leebongseung/coding-test/assets/101985441/804af436-2ddb-43ff-aad5-6704ef8ce8e9)


<li><p>위의 사진을 보면 이전의 결과에서 세로도형과 가로도형을 이용하여 다양한 예상경로를 추출해나가면서 점화식을 구성해보았습니다.</p>
</li>
</ul>
</li>
<li>어려웠던 점<ul>
<li>점화식을 도출하기 까지가 가장 어려웠습니다.</li>
</ul>
</li>
<li>배운점<ul>
<li>가능한 모든 예시를 생각하고 적용해나가면서 결과를 내야한다!</li>
</ul>
</li>
</ul>
</details>

<details>
<summary>
<a href="_11727.java">11727 - 2*n 타일링2(20분)</a>
</summary> 

<ul>
<li>풀이 방법<ul>
<li>그림을 그려가며 모든 경우의 수를 예측했다. </li>

![image](https://github.com/leebongseung/coding-test/assets/101985441/de9b5fe8-6cd6-4cc7-8e40-739c4be4b73a)
<li> 점화식 : a[i] = a[i-1] + 2a[i-2]</li>
</ul>
</li>
<li>어려웠던 점<ul>
<li>가로일 경우 두번이 반복되는 과정에서 너무 어려웠다.</li>
</ul>
</li>
<li>배운점 <ul>
<li>그림그리는 과정에서 생각하기가 정말힘든데 이렇게 하면 쉽다,</li>
<li>1. 1번과 2번의 예측가능한 경우의 수를 그린다.</li>
<li>2. 1번에서 + 조건을 더해서 3번이 만들어지는지 확인한다.</li>
<li>3. 2번에서 + 조건을 더해서 3번이 만들어 지는지 확인하다.</li>
<li>4. 1번과 2번에서 중복되는 부분이 있는지 확인한다.</li>
<li>5. 1번과 2번을 통하여 3번이 만들어지는지 확인한다.</li>
</ul>
</li>
</ul>



</details>

