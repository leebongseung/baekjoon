## 비트연산
<details>
<summary>
<a href="_17681.java"> [3차] n진수 게임 (소요시간: 1시간 40분)</a>
</summary> 

✐ **코드 설명**

- n 진수로 변환하고
- str 로 순서를 정한 거 만큼 할당 해주 었다.

🤔 **어려웠던 부분 / 배운 점**

- 숫자를 n진수로 변환했던 부분이 가장 난이도가 높았고 풀이시간은 1시간 40분 정도 풀었다.

🌱 **해설 / 다른 사람 코드 참고**

- Integer.toString 함수를 Integer.toString(int i, int radix) 형태로 사용하게 되면 i를 radix에 해당하는 진법으로 변환할 수 있다

</details>

<details>
<summary>
<a href="_17681.java"> [1차] 비밀지도(소요시간: 30분)</a>
</summary> 

<ul>
<li><p>풀이과정</p>
<ul>
<li>비트 OR 연산을 통해서 비트연산을 수행 후 나머지 연산을 통해 비트가 0인지 1인지 알아냄</li>
<li>최종적으로 높은 비트부터 낮은 비트로 바꾸기 위해 String 역정렬을 수행</li>
</ul>
</li>
<li><p>어려운점</p>
<ul>
<li>reverse()를 썻는데 빈 공백 &quot; &quot; 이 제대로 들어가지 않았음.</li>
</ul>
</li>
<li><p>배운점</p>
<ul>
<li>그래서 char[] 타입으로 일일이 하나 씩 역정렬 수행하였다. </li>
<li>다른 사람 풀이를 보니 String += &quot; &quot; 이런식으로 수행하고 역정렬 메서드 reverse() 를 수행하니 정상 동작 한다.</li>
</ul>
</li>
</ul>


</details>