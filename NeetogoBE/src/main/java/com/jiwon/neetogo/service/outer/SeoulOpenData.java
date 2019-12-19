package com.jiwon.neetogo.service.outer;

import com.google.gson.Gson;
import com.jiwon.neetogo.dto.sodp.LastTrainTime;
import com.jiwon.neetogo.dto.sodp.StationSearchByCode;
import com.jiwon.neetogo.util.DefaultRes;
import com.jiwon.neetogo.util.ResponseMessage;
import com.jiwon.neetogo.util.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class SeoulOpenData {

    @Value("${sodp.searchLocOfStaionNmById.key}")
    private String locOfSodpKey;

    @Value("${sodp.searchLastTrainTimeById.key}")
    private String timeOfSodpKey;

    String baseURL = "openapi.seoul.go.kr";

    RestTemplate restTemplate = new RestTemplate();

    Gson gson = new Gson();

    public DefaultRes searchLocationOfSTNByID(String num){

        URI uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(baseURL)
                .port(8088)
                .path(locOfSodpKey)
                .path("/json/SearchLocationOfSTNByIDService/1/5/")
                .path(num)
                .build()
                .encode()
                .toUri();


        String jsonResult = restTemplate.getForObject(uri, String.class);
        System.out.println(jsonResult);
        StationSearchByCode result = gson.fromJson(jsonResult, StationSearchByCode.class);


        if (!result.getInfo().getResult().getCode().equals("INFO-000")) { // 처리 실패
            return DefaultRes.res(StatusCode.SERVICE_UNAVAILABLE, ResponseMessage.FAIL_TO_READ_DATA);
        }
        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_DATA, result.getInfo().getRow());
    }

    public LastTrainTime searchLastTrainTimeByID(String code, String day, String dir) {
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(baseURL)
                .port(8088)
                .path(timeOfSodpKey)
                .path("/json/SearchLastTrainTimeByIDService/1/5/")
                .path(code + "/" + day + "/" + dir)
                .build()
                .encode()
                .toUri();


        String jsonResult = restTemplate.getForObject(uri, String.class);
        LastTrainTime result = gson.fromJson(jsonResult, LastTrainTime.class);

        return result;
    }

}
