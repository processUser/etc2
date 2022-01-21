package org.iptime.mpage.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntity { //DB와 1:1 매핑
    public int userpk; //인덱스값
    public String email; //이메일
    public String pw; //비밀번호
    public String nm; //이름
    public int gender; // 성별(1-남,2-여,3-선택x)
    public String birthdaymm; // 월
    public String birthdaydd; // 일
    public String rdt; // 가입일
    public String ldt; // 마지막 로그인
    public int joinpath; //로그인 경로 (0,1-홈페이지,2-naver,3-kakao,,,etc)
    public String ukey;
    public int deluser; //삭제여부
}
