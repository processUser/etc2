package org.iptime.mpage.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends UserEntity { // db 조회에 이용할 값을 담는 곳
    public String genderstr;
    public String birthday;

}
