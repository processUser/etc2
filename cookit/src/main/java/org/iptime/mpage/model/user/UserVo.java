package org.iptime.mpage.model.user;

import lombok.Getter;
import lombok.Setter;
import org.iptime.mpage.model.user.UserEntity;

@Getter
@Setter
public class UserVo extends UserEntity { // select 이후 결과값을 담는 공간
    public String genderstr;
}
