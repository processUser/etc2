package org.iptime.mpage.model.goods;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewVo extends ReviewEntity {
    private double avgscore; //상품 별점 총 평균
    private int countscore; //상품 별점 총 평균
}
