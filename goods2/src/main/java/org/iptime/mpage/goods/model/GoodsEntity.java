package org.iptime.mpage.goods.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsEntity {
    private int goodspk;
    private String gnum;
    private int categorypk;
    private String gnm;
    private int price;
    private int quantity;
    private String rdt;
    private int isdel;
}
