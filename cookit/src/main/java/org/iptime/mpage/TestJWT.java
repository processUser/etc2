package org.iptime.mpage;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestJWT {
    //암호화 키
    private final String KEY = "cookit_Test_Secret_Key";

    public static void main(String[] args) throws Exception {
        TestJWT testJWT = new TestJWT();

        String jwt = testJWT.createToken();
        System.out.println(jwt);

        Map<String, Object> claimMap = testJWT.verifyJWT(jwt);
        System.out.println(claimMap); // 토큰이 만료되었거나 문제가있으면 null
    }

    //토큰 생성
    public String createToken() throws Exception {

        //Header 부분 설정정
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        //payload 부분 설정
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("data", "My JWT");
        payloads.put("nm", "고길동");

        Long expiredTime = 1000 * 60L; // 1000 * 60L * 60L * 2L 토큰 유효 시간 (2시간)

        Date ext = new Date(); // 토큰 만료 시간
        ext.setTime(ext.getTime() + expiredTime);

        // 토큰 Builder
        String jwt = Jwts.builder()
                .setHeader(headers) // Headers 설정
                .setClaims(payloads) // Claims 설정
                .setSubject("user") // 토큰 용도
                .setExpiration(ext) // 토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256, KEY.getBytes("UTF-8")) // HS256과 Key로 Sign
                .compact(); // 토큰 생성

        return jwt;
    }

    //토큰 검증
    public Map<String, Object> verifyJWT(String jwt) throws Exception {
        Map<String, Object> claimMap = null;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(KEY.getBytes("UTF-8")) // Set Key
                    .parseClaimsJws(jwt) // 파싱 및 검증, 실패 시 에러
                    .getBody();

            claimMap = claims;

            //Date expiration = claims.get("exp", Date.class);
            //String data = claims.get("data", String.class);

        } catch (ExpiredJwtException e) {
            // 만료 예외 처리
            System.out.println("토큰 만료");
//            return false;
        } catch (JwtException e) {
            // 유효하지 않은 토큰 예외 처리
            System.out.println("유효하지 않은 토큰");
//            return false;
        } catch (Exception e){
            // 기타 예외 처리
            System.out.println(e);
//            return false;
        }
        return claimMap;
    }
}
/*
jwt access token refresh token
 https://github.com/jwtk/jjwt
 https://archijude.tistory.com/432 //js 로 헤더 값 가져오는 방법
 https://developer.mozilla.org/en-US/docs/Web/API/atob // base64 인코딩 디코딩
 https://okky.kr/article/703255 // 헤더에 정보값을 못가져올때
 https://escapefromcoding.tistory.com/255
 https://zlcjfalsvk.github.io/java/jwt/
 https://kunkunwoo.tistory.com/211
 https://tansfil.tistory.com/59?category=475681
 참고 - https://velog.io/@carrykim/%EC%8B%A4%EC%8A%B5-JWT-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0-with-Java
 참고 - https://bamdule.tistory.com/123
 참고 - https://mangkyu.tistory.com/56
 참고 - https://idlecomputer.tistory.com/240?category=766105
https://ksshlee.github.io/spring/java/jwt/
 */