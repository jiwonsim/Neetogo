# Neetogo.BE

현 위치에서 가장 가까운 전철역에 따른 막차 시간을 알려줍니다. 

 
 

개발기반 : 2019.11.07 ~

## API 명세
| URL | 메서드 | 설명 |
|:---|:---:|:---|
|/search|GET|검색한 역의 정보 출력|

**Request**

| 키 | 설명 | 필수 | 타입 | 
|:---|:---:|:---:|:---:|
|name|역 이름|O|String|

**Response**
<pre><code> {
    "status": 200,
    "message": "데이터 조회 성공",
    "data": [
        {
            "num": 248,
            "stationCd": "1714",
            "stationNm": "독산",
            "lineNum": "01호선",
            "frCode": "P143"
        }
    ]
}
</code></pre>

<br>
---
<br>

| URL | 메서드 | 설명 |
|:---|:---:|:---|
|/search/here|GET|현 위치 반경 1km 내의 역 정보 출력|


**Request**

| 키 | 설명 | 필수 | 타입 | 
|:---|:---:|:---:|:---:|
|x|x 좌표로 경위도인 경우 longitude|O|String|
|y|y 좌표로 경위도인 경우 latitude|O|String|

**Response**
<pre><code> {
    "status": 200,
    "message": "데이터 조회 성공",
    "data": [
        {
            "place_name": "독산역 1호선",
            "distance": "590",
            "place_url": "http://place.map.kakao.com/21160605",
            "category_name": "교통,수송 > 지하철,전철 > 수도권1호선",
            "road_address_name": "서울 금천구 벚꽃로 115",
            "id": "21160605",
            "phone": "02-2639-3229",
            "category_group_code": "SW8",
            "category_group_name": "지하철역",
            "x": "126.889475639948",
            "y": "37.4659755677766"
        }
    ]
}
</code></pre>

<br>
---
<br>


| URL | 메서드 | 설명 |
|:---|:---:|:---|
|/search/route|GET|출발역에서 도착역까지의 경로 출력|


**Request**

| 키 | 설명 | 필수 | 타입 | 
|:---|:---:|:---:|:---:|
|start|출발역|O|String|
|end|도착역|O|String|

**Response**
<pre><code>{
    "status": 200,
    "message": "데이터 조회 성공",
    "data": [
        {
            "order": 1,
            "stationNm": "독산",
            "stationCd": "1714",
            "lineNum": "01호선",
            "frCode": "P143"
        },
        {
            "order": 2,
            "stationNm": "가산디지털단지",
            "stationCd": "1702",
            "lineNum": "01호선",
            "frCode": "P142"
        },
        {
            "order": 3,
            "stationNm": "구로",
            "stationCd": "1701",
            "lineNum": "01호선",
            "frCode": "141"
        },
        {
            "order": 4,
            "stationNm": "신도림",
            "stationCd": "0234",
            "lineNum": "02호선",
            "frCode": "234"
        }
    ]
}
</code></pre>

<br>
---
<br>


| URL | 메서드 | 설명 |
|:---|:---:|:---|
|/search/last|GET|막차 시간에 따른 출발 시간 출력|


**Request**

| 키 | 설명 | 필수 | 타입 | 
|:---|:---:|:---:|:---:|

**Response**
<pre><code>
</code></pre>

<br>
---
<br>


| URL | 메서드 | 설명 |
|:---|:---:|:---|
|/favorite|GET|즐겨찾기 목록 출력|


**Request**

**Response**
<pre><code>
{
    "status": 200,
    "message": "데이터 조회 성공",
    "data": [
        {
            "num": 732,
            "name": null,
            "code": null,
            "latitude": null,
            "longitude": null,
            "createdAt": "2019-11-28T11:51:58.000+0000"
        },
        {
            "num": 733,
            "name": null,
            "code": null,
            "latitude": null,
            "longitude": null,
            "createdAt": "2019-11-28T11:52:09.000+0000"
        }
    ]
}
</code></pre>

<br>
---
<br>



| URL | 메서드 | 설명 |
|:---|:---:|:---|
|/favorite|POST|즐겨찾기 등록|


**Request Body**

<pre><code>{
    "name" : "독산", 
    "code" : "1",
    "latitude" : "37.4659755677766",
    "longitude" : "126.889475639948"
}
</code></pre>

**Response**
<pre><code>{
    "status": 200,
    "message": "데이터 저장 성공",
    "data": null
}
</code></pre>


 
## Server Architecture

## Dependencies

## Start Server

## Test 

In NGrinder 

**2019/12/04 기준** 

* RestTemplate(Block I/O & Synchronous API)
* MySQL
* File System 



### Used Tools
1. Spring Boot - Spring Boot Web Framework
2. Maven
3. Tomcat
4. Intellij IDEA - IDE
5. MySQL - Database

