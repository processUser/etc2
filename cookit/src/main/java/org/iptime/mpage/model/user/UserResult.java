package org.iptime.mpage.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResult { // json 응답용
    private int result;
    private String msg;
    private String rjwt;
}
