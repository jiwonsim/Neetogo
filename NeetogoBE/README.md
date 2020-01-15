# Neetogo.BE

### Description

* 현 위치에서 가장 가까운 전철역에 따른 막차 시간을 알려주는 미리 API 서버입니다.  


 
### Used Tools
1. Spring Boot - Spring Boot Web Framework
2. Java 8
2. Maven
3. Tomcat
4. Intellij IDEA - IDE
5. MySQL - Database
6. JPA

 
### Start Server

- 8101 port 로 연결

- `JDK8`과 `Maven` 설치가 필요
 
- `JAVA_HOME`환경변수 설정이 필요
 
- `Path`에 `Maven` 환경변수를 설정

- 내장 톰캣을 이용해 서버를 배포

- `application.properties`파일이 필요

- Spring boot 앱 실행 
```
mvn spring-boot:run
```

- 종료 시, `command + c` 
 

개발기반 : 2019.11 ~ 2019.12

### API Specification

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
    "data": {
        "spendingTime": "6",
        "stationCnt": "4",
        "routeOfStation": [
            {
                "order": 1,
                "stationCd": "1714",
                "stationNm": "독산",
                "lineNum": "01호선",
                "frCode": "P143",
                "direction": "1"
            },
            {
                "order": 2,
                "stationCd": "1702",
                "stationNm": "가산디지털단지",
                "lineNum": "01호선",
                "frCode": "P142",
                "direction": "1"
            },
            {
                "order": 3,
                "stationCd": "1701",
                "stationNm": "구로",
                "lineNum": "01호선",
                "frCode": "141",
                "direction": "1"
            },
            {
                "order": 4,
                "stationCd": "0234",
                "stationNm": "신도림",
                "lineNum": "02호선",
                "frCode": "234",
                "direction": null
            }
        ]
    }
}
</code></pre>

<br>

---
<br>


| URL | 메서드 | 설명 |
|:---|:---:|:---|
|/search/lastTimes|GET|막차 종류 출력|


**Request**

| 키 | 설명 | 필수 | 타입 | 
|:---|:---:|:---:|:---:|
|stn|역이름|O|String|
|dir|상행선/하행선|O|String|

**Response**
<pre><code>{
    "status": 200,
    "message": "데이터 조회 성공",
    "data": [
        {
            "frCode": "P143",
            "stationCd": "1714",
            "stationNm": "독산",
            "subwayEname": "구로",
            "leftTime": "24:23:00",
            "weekTag": "1",
            "inoutTag": "1"
        },
        ...
    ]
}
</code></pre>

<br>

---
<br>

| URL | 메서드 | 설명 |
|:---|:---:|:---|
|/search/startingTime|GET|막차 시간에 따른 출발 시간 출력|


**Request**

| 키 | 설명 | 필수 | 타입 | 
|:---|:---:|:---:|:---:|
|startX|출발 Longitude|O|String|
|startY|출발 Latitude|O|String|
|endX|도착 Longitude|O|String|
|endY|도착 Latitude|O|String|

**Response**
<pre><code>{
    "status": 200,
    "message": "데이터 조회 성공",
    "data": {
       "spendingTime": 10,
       "startingTime": "24:13"
    }
}
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



### Dependencies

코드 환경은 **Intellij IDEA + MAC OS + JAVA 8** 입니다.
MAVEN 의존성 프로젝트는 아래와 같습니다.  

```
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
        <version>2.1.6.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.16</version>
    </dependency>
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>1.7.25</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.8</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
        <version>2.1.4.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.8.5</version>
    </dependency>

</dependencies>
    
```

