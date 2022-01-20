package org.iptime.mpage.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgreeEntity {
    private int agreepk;
    private int userpk;
    private int termsOfUse;
    private int userInformation;
    private int marketing;
    private int age;
    private int notRealpage;
    private String rdt;
    private String mdt;
}
