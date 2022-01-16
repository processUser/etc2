package org.iptime.mpage.goods.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsVo extends GoodsEntity {
    private String category; //카테고리 이름
    private double avgscore; //상품 별점 총 평균
    private int countscore; //상품 별점 총 평균
}
