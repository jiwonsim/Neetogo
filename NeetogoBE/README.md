# Neetogo.BE

현 위치에서 가장 가까운 전철역에 따른 막차 시간을 알려줍니다. 

 

개발기반 : 2019.11.07 ~

## API 명세
| URL | Method | Title | Desc|
|:---|:---:|:---:|:---|
|/search|GET|역 검색|검색한 역의 정보 출력|
|/search/here|GET|현위치 역 검색|현 위치 반경 1km 내의 역 정보 출력|
|/search/route|GET|경로 출력|출발역에서 도착역까지의 경로 출력|
|/search/last|GET|막차 시간|막차 시간에 따른 출발 시간 출력|
|/favorite|GET|즐겨찾기 목록|즐겨찾기 목록 출력|
|/favorite|POST|즐겨찾기 등록|즐겨찾기 등록|


 
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

