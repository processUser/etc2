package org.iptime.mpage.model.goods;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsVo extends GoodsEntity {
    private String category; //카테고리 이름
    private ReviewVo reviewvo;
}
