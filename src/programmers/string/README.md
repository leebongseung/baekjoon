## String

<details>
<summary>
<a href="_17687.java">k진수에서 소수 개수 구하기(소요시간: 50분)</a>
</summary> 

✐ **코드 설명**

1. StringBuffer 를 이용해서 우선 k 진수화 시켜줍니다.
2. reverse를 통하여 k진수로 변환된 값을 만들구용
3. 그 다음에 split(”0”)으로 0이 포함되지 않은 소수 인지 확인 합니다.
4. 최종적으로 구한 cnt값을 출력합니다.

🤔 **어려웠던 부분 / 배운 점**

- split(”0”) 이것으로 문자열나누고요
- `is_prime_number` 로 소수인지 확인하는 로직에서 `Math.sqrt(x)` 를 이용해서 시간 복잡도를 줄였습니다.
- NumberFormatException(””) 빈문자열을 넣을경우 에러가 터져서 exception 처리를 해두었습니당!

🌱 **해설 / 다른 사람 코드 참고**

- split(”0”)


</details>

<details>
<summary>
<a href="_81301.java">숫자 문자열과 영단어 - 게임맵(소요시간: 30분)</a>
</summary> 
<ul>
<li><p>풀이과정</p>
<ul>
<li>문자열 에서 숫자와 문자를 chatAt으로 하나하나 비교해가면서 추출하였음</li>
</ul>
</li>
<li><p>어려운점</p>
<ul>
<li>Character to String 을 할줄 몰라서.. 솔직히 검색했다.</li>
<li>String to Int 이건 왜 못했지 처음엔 valueOf 를 사용해서 그랬던거 같습니다.</li>
</ul>
</li>
<li><p>배운점</p>
<ul>
<li>다른이의 풀이에서 replace 로 대체해서 사용하는 거 보고 엄청나게 감탄했습니다.. replace 문자열 대체!! 기억</li>
</ul>
</li>
</ul>


</details>