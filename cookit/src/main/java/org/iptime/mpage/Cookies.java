package org.iptime.mpage;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class Cookies { // cookie 생성 메서드

    public static void setCookie(HttpServletResponse res, String value){
        Cookie cookie = new Cookie("tokens", value); // 쿠키 이름 지정하여 생성( key, value 개념)
        cookie.setMaxAge(60*60*24*14); //쿠키 유효 기간: 하루로 설정(60초 * 60분 * 24시간)
        cookie.setPath("/"); //모든 경로에서 접근 가능하도록 설정
        cookie.setHttpOnly(true);
        //cookie.setDomain("127.0.0.1");
        System.out.println("cookie : "+cookie);
        res.addCookie(cookie); //response에 Cookie 추가
        //res.setHeader("setcookie", value);  // ajax 통신에서 쿠키 불가능함. 그래서 해결 방법 1.
        // 해결방법 2 json  형태로 만들어버리기.
    }

}
/*
*
* https://blog.naver.com/PostView.nhn?blogId=adamdoha&logNo=222081530284
* https://lookingfor.tistory.com/entry/%EC%9E%90%EB%B0%94-%EC%BF%A0%ED%82%A4-%EB%8B%A4%EB%A3%A8%EA%B8%B0-%EC%BF%A0%EA%B8%B0-%EC%83%9D%EC%84%B1-%EC%A1%B0%ED%9A%8C-%EC%82%AD%EC%A0%9C
* */