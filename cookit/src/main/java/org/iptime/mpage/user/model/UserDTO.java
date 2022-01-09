package org.iptime.mpage.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends UserVo { // db 조회에 이용할 값을 담는 곳
    public String birthday;
}
