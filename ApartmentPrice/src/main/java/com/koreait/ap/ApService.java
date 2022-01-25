package com.koreait.ap;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.koreait.ap.model.ApartmentInfoEntity;
import com.koreait.ap.model.LocationCodeEntity;
import com.koreait.ap.model.SearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ApService {

    @Autowired
    private ApMapper mapper;

    public List<LocationCodeEntity> selLocation() {
        List<LocationCodeEntity> list = mapper.selLocation();
        //System.out.println(list);
        return list;
    }

    public Object selApartmentInfo(SearchDto dto) {
        ApartmentInfoEntity entity2 = mapper.selApartmentInfo(dto);
        if(entity2 == null){
            System.out.println("------------------------getdata------------------------");
           return getData(dto);
        } else {
            System.out.println("------------------------seldata------------------------");
            return mapper.selApartmentList(dto);
        }
    }

    public ApartmentInfoEntity[] getData(SearchDto dto) {
        String lawdCd = dto.getOut_code();
        String dealYm = String.format("%d%02d", dto.getYear(), dto.getMonth());
        //System.out.println("dealYm : " + dealYm);

        String serviceKey = "Y2UOCkD8Ilv2gViPGV33ddNTTQfRi92i8mRzUeQX+NgSiNTO3gp9hJZX4J6u8uXucMM6RdRBoGxMn6XHfsEzNA==";
        String url = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("LAWD_CD", lawdCd).queryParam("DEAL_YMD", dealYm).queryParam("serviceKey", serviceKey).queryParam("numOfRows", 5000).build(false);

        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = rest.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        //System.out.println("result : " + result);

        // JsonMapper ,XmlMapper
        ObjectMapper om = new JsonMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 값을 전부 다 안 받도록 가능하게 해주는 역할
        JsonNode jsonNode = null;
        ApartmentInfoEntity[] list = null;
        try{
            jsonNode = om.readTree(result);
            list = om.treeToValue(jsonNode.path("response").path("body").path("items").path("item"), ApartmentInfoEntity[].class);
            LocationCodeEntity incode = mapper.selLocationCode(dto);

            for (ApartmentInfoEntity item: list) {
                item.setLocationcode(incode.getIn_code());
                System.out.println("item" + item);
                item.setDealamount(item.getDealamount().replaceAll(",",""));
                //TODO instert
                mapper.insApartData(item);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
