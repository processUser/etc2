package com.koreait.ap;

import com.koreait.ap.model.ApartmentInfoEntity;
import com.koreait.ap.model.LocationCodeEntity;
import com.koreait.ap.model.SearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApMapper {
    List<LocationCodeEntity> selLocation();
    List<LocationCodeEntity> selApartmentList(SearchDto dto);
    LocationCodeEntity selLocationCode(SearchDto dto);
    ApartmentInfoEntity selApartmentInfo(SearchDto dto);
    int insApartData(ApartmentInfoEntity entity);
}
