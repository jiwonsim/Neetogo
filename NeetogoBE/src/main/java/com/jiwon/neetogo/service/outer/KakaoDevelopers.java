package com.jiwon.neetogo.service.outer;

import com.google.gson.Gson;
import com.jiwon.neetogo.dto.LocationDTO;
import com.jiwon.neetogo.dto.kakao.geo.Documents;
import com.jiwon.neetogo.dto.kakao.geo.GEOResult;
import com.jiwon.neetogo.dto.kakao.search.SearchResult;
import com.jiwon.neetogo.util.DefaultRes;
import com.jiwon.neetogo.util.ResponseMessage;
import com.jiwon.neetogo.util.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class KakaoDevelopers {

    @Value("${kakao.restApi.key}")
    private String restApiKey;

    String baseUrl = "dapi.kakao.com";

    RestTemplate restTemplate = new RestTemplate();

    Gson gson = new Gson();

    public DefaultRes searchStationByHereLoc(LocationDTO locationDTO) {
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(baseUrl)
                .path("//v2/local/search/category.json")
                .queryParam("category_group_code", "SW8")
                .queryParam("x", locationDTO.getLatitude())
                .queryParam("y", locationDTO.getLongitude())
                .queryParam("radius", "1000")
                .queryParam("sort", "distance")
                .build()
                .encode()
                .toUri();

        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + restApiKey);

        final HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> resultJson = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        if (resultJson.getStatusCodeValue() != 200) {
            return DefaultRes.res(StatusCode.SERVICE_UNAVAILABLE, ResponseMessage.FAIL_TO_READ_DATA);
        }
        else {
            SearchResult result = gson.fromJson(resultJson.getBody(), SearchResult.class);
            return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_DATA, result.getDocuments());
        }
    }

    //https://dapi.kakao.com/v2/local/geo/transcoord.json?x=160710.37729270622&y=-4388.879299157299&input_coord=WTM&output_coord=WGS84
    public Documents transformCoordinate(String longitude, String latitude) {
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(baseUrl)
                .path("/v2/local/geo/transcoord.json")
                .queryParam("x", longitude)
                .queryParam("y", latitude)
                .queryParam("input_coord", "WGS84")
                .queryParam("output_coord", "WTM")
                .build()
                .encode()
                .toUri();

        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + restApiKey);

        final HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> resultJson = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        System.out.println(resultJson.getBody());
        if (resultJson.getStatusCodeValue() != 200) {
            return null;
        }
        else {
            GEOResult geoResult = gson.fromJson(resultJson.getBody(), GEOResult.class);
            return geoResult.getDocuments().get(0);
        }
    }
}
