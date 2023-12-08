# JWT

JWT (Json Web Token) 학습 전 

세션 생명주기

1. 브라우저 종료 시
2. 시간 초과 시
3. 서버 측에서 세션제거

서버에서 세션을 db에 저장하고 세션을 불러올 떄 마다 속도문제가 생긴다. 
이떄 캐싱을 통하여 한번 조회 된 캐시를 서버 접속없이 처리하여 빠르게 불러올 수 있다.
캐싱을 하는 대표적인 오픈소스는 Redis

============================================================================

UsernamePasswordAuthenticationFilter 설명

https://programmer93.tistory.com/78

authenticationManager 설명

https://velog.io/@on5949/SpringSecurity-Authentication-%EA%B3%BC%EC%A0%95-%EC%A0%95%EB%A6%AC
