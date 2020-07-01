# MyProject

## 목표
- 1. 네이버 뉴스 -> 랭킹뉴스 -> 많이 본 뉴스 -> IT/과학 카테고리 내용 Crawling
- 2. Crawling한 정보 DataBase에 삽입  
![1 problem](https://user-images.githubusercontent.com/32935365/78766531-567ffa00-79c4-11ea-923a-e73b77ee0a94.PNG)


## 환경
- Spring 4.3.7
- Spring Boot 1.5.2
- Java 1.8
- Maven 3.1.1
- JSoup 1,10,2
- Mysql
- Mybatis

## DB table
### MWS_COLT_NEWS
|필드명           |데이터 타입   |의미              |비고                |
|----------------|--------------|------------------|-------------------|
|`ID`			 |int           |ID                |PK, AutoIncrement  |
|`AID`           |varchar       |기사번호(aid+oid)  |                   |
|`TITLE`         |varchar       |제목               |                   |
|`CONTENT`       |varchar       |내용               |                   |
|`IMAGE`         |varchar       |이미지 URL         |                   |
|`REG_DT`        |datetime      |최초 수집 시간     |                   |
|`UPT_DT`        |datetime      |최신화 시간        |                   |  
- 랭킹뉴스를 크롤링하여 database insert
- 기존에 DB 에 있던 DATA가 다시 들어올 경우 UPT_DT를 갱신  


### MWS_COLT_NEWS_INFO  
|필드명           |데이터 타입   |의미              |비고                |
|----------------|--------------|------------------|-------------------|
|`ID`			 |int           |ID                |PK, AutoIncrement  |
|`NEWS_ID`       |int           |MWS_COLT_NEWS의 ID|FK                 |
|`RANK`          |int           |기사 랭킹 순위     |                   |
|`VIEW`          |int           |View 수           |                   |
|`REG_DT`        |datetime      |수집 시간          |                   |
- history성 table


## 실행 결과

1. MWS_COLT_NEWS  


2. MWS_COLT_NEWS_INFO  


3. Log  
