package org.iptime.mpage;

import io.jsonwebtoken.*;
import org.iptime.mpage.model.user.UserVo;

import java.nio.charset.StandardCharsets;
import java.util.*;

// TODO 어떻게 처리할지 고민필요
// JWT 생성
public class TestJWT {
    //암호화 키
    //private String KEY = SecretKey.makeUuid();

    //토큰 생성
    public String createAccessToken(UserVo vo, String key) throws Exception {

        //Header 부분 설정정
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        //payload 부분 설정
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("data", "My JWT");
        payloads.put("userpk", vo.getUserpk());
        payloads.put("nm", vo.getNm());
        payloads.put("email", vo.getEmail());
        payloads.put("joinpath", vo.getJoinpath());

        Long expiredTime = 1000 * 60L * 60L * 2L; // 1000 * 60L * 60L * 2L 토큰 유효 시간 (2시간)

        Date ext = new Date(); // 토큰 만료 시간
        ext.setTime(ext.getTime() + expiredTime);

        // 토큰 Builder
        String jwt = Jwts.builder()
                .setHeader(headers) // Headers 설정
                .setClaims(payloads) // Claims 설정, Token에 담을 정보
                .setSubject("user") // 토큰 용도, Token 제목
                .setExpiration(ext) // 토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256, key) // HS256과 Key로 Sign
                .compact(); // 토큰 생성

        return jwt;
    }

    public String createRefreshToken(int val, String ip2, String path1, int path2, String key) throws Exception {

        //Header 부분 설정정
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        //payload 부분 설정
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("data", "My JWT");
        payloads.put("Succeeded!", val);
        payloads.put("ip", ip2);
        payloads.put("url1", path1);
        payloads.put("url2", path2);


        Long expiredTime = 1000 * 60L * 60L * 24L * 14L; // 1000 * 60L * 60L * 2L 토큰 유효 시간 (2시간)

        Date ext = new Date(); // 토큰 만료 시간
        ext.setTime(ext.getTime() + expiredTime);

        // 토큰 Builder
        String jwt = Jwts.builder()
                .setHeader(headers) // Headers 설정
                .setClaims(payloads) // Claims 설정, Token에 담을 정보
                .setSubject("check") // 토큰 용도, Token 제목
                .setExpiration(ext) // 토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256, key) // HS256과 Key로 Sign
                .compact(); // 토큰 생성

        return jwt;
    }

    //토큰 검증
    public Map<String, Object> verifyJWT(String jwt, String key) throws Exception {
        Map<String, Object> claimMap = null;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key) // Set Key
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
 https://galid1.tistory.com/588 - 참고하면 괜찮을 듯
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