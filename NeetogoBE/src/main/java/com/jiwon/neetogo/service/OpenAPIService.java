package com.jiwon.neetogo.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jiwon.neetogo.dto.SearchLocationOfSTNByIDService;
import com.jiwon.neetogo.dto.StationSearchByCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class OpenAPIService {

    @Value("${sodp.searchLocOfStaionNmById.key}")
    private String sodpKey;

    public void httpGetRequest(String num) throws Exception {
        String baseURL = "openapi.seoul.go.kr";

        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(baseURL)
                .port(8088)
                .path(sodpKey)
                .path("/json/SearchLocationOfSTNByIDService/1/5/")
                .path(num)
                .build()
                .encode()
                .toUri();

        Gson gson = new Gson();

        String jsonResult = restTemplate.getForObject(uri, String.class);
        StationSearchByCode stationSearchByCode = gson.fromJson(jsonResult, StationSearchByCode.class);


        log.info(jsonResult);
        log.info(stationSearchByCode.toString());
    }


}


