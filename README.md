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


## 흐름
1. Controller  
![1 controller](https://user-images.githubusercontent.com/32935365/78762301-a6f45900-79be-11ea-925b-b7719fd0ad6e.PNG)  

2. Crawling  
![2 crawling](https://user-images.githubusercontent.com/32935365/78655773-6be02000-7901-11ea-9a07-c1ff86938730.PNG)  

3. Insert  
![3 insert](https://user-images.githubusercontent.com/32935365/78655799-74d0f180-7901-11ea-8850-d6e8b4189275.PNG)  

4. NewsMapper  

![4 newsmapper](https://user-images.githubusercontent.com/32935365/78655881-8c0fdf00-7901-11ea-9e43-7550174fa293.PNG)  

5. NewsInfoMapper  
![4 newsinfomapper](https://user-images.githubusercontent.com/32935365/78655860-84503a80-7901-11ea-88af-fc09c8d941aa.PNG)  


## AOP
1. Controller  
![7 aop controller](https://user-images.githubusercontent.com/32935365/78762756-3dc11580-79bf-11ea-9b02-d76930978440.PNG)  

2. Crawling  
![7 aop crawling](https://user-images.githubusercontent.com/32935365/78762770-4580ba00-79bf-11ea-895a-8d4b5fe04ce6.PNG)  

3. InsertDB  
![7aop db](https://user-images.githubusercontent.com/32935365/78762787-4dd8f500-79bf-11ea-8136-41d6ca17a915.PNG)  


## 실행 결과

1. MWS_COLT_NEWS  
![5 table](https://user-images.githubusercontent.com/32935365/78752193-9805aa80-79ae-11ea-8d26-af4843cfe6fb.PNG)  

2. MWS_COLT_NEWS_INFO  
![5 info](https://user-images.githubusercontent.com/32935365/78752216-a227a900-79ae-11ea-9895-6716ea5e6db0.PNG)  

3. Log  
![console log](https://user-images.githubusercontent.com/32935365/78766251-fe48f800-79c3-11ea-96d0-9c8a9b6142f8.PNG)  