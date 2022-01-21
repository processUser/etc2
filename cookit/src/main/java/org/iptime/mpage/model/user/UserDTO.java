package org.iptime.mpage.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends UserEntity { // db 조회에 이용할 값을 담는 곳
    public String genderstr; // 성별
    public String birthday; // 생일(월일)
    public String url;  // 현재 url
    public String preUrl; // 이전 url
    public AgreeEntity agree;

}
