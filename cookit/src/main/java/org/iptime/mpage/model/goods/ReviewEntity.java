package org.iptime.mpage.model.goods;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewEntity {
    private int reviewpk;
    private int goodspk;
    private int userpk;
    private int revscore;
    private String revctnt;
    private String revimg;
    private String rdt;
    private String mdt;
    private int isdel;
}
