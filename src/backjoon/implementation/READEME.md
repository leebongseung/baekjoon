## 구현
<details>
<summary>
<a href="_1138.java">1138 - 한 줄로 서기(20분)</a>
</summary>

<ul>
<li>풀이과정<ul>
<li>자신의 위치에서 왼쪽부분에 자신보다 큰 값이 몇개 있는지 파악이 되기 때문에 젤 마지막 index 인 n-1 번 부터 읽어가면 된다.</li>
<li>예를들어 4 \n 2 1 1 0 이라면 0(4) -&gt; 1(3) -&gt; 1(2) -&gt; 2(1) 순서대로 arr에 인덱스 위치, value 이런식으로 add 메서드를 활용하면 쉽게 풀이가 가능하다.</li>
</ul>
</li>
</ul>
<ul>
<li><p>어려운점</p>
<ul>
<li>없었던것 같습니다.</li>
</ul>
</li>
<li><p>배운점</p>
<ul>
<li>처음에 int 로 할지 ArrayList로 할지 고민했는데 다음과 같이 그림을 그려보니까 쉽게 풀렸습니다.</li>

![image](https://github.com/leebongseung/coding-test/assets/101985441/c98c46ff-a72b-4e08-9dad-e7a40f72a19d)
</ul>
</li>
</ul>
</details>