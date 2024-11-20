# The Memoridal Day
해당 문서는 주문 제작 케이크 플랫폼 'The Memorial Day'의 백엔드 서비스를 설명하고 있습니다.

## 목차
1. [프로젝트 소개](#프로젝트-소개)
2. [개발 스택](#개발-스택)
3. [폴더 구조](#폴더-구조)
4. [설치 및 실행](#설치-및-실행)
5. [기타 설정](#기타-설정)
6. [주요 기능](#주요-기능)
7. [시스템 아키텍처](#시스템-아키텍처)
8. [ERD](#ERD)
9. [시퀀스 다이어그램](#시퀀스-다이어그램)
10. [제작 기간](#제작-기간)
11. [라이선스](#라이선스)

---

## 프로젝트 소개
**'The Memorial Day'** 케이크 상점과 사용자를 신뢰성 있게 연결하여, 다양한 상점의 케이크를 인기 키워드를 바탕으로 실시간 추천합니다. 사용자들은 원하는 케이크를 쉽게 구매하고 리뷰와 평점을 남길 수 있으며, 상점 주인들은 상점과 상품을 다양하게 표현할 수 있습니다. 또한, 매출 관리 기능을 통해 시각적으로 매출 현황을 확인하고 효율적으로 관리할 수 있는 온라인 케이크 상점 서비스입니다.  

---

## 개발 스택
백엔드에서 사용한 주요 기술들을 나열합니다.
- **프로그래밍 언어**: Java  
- **프레임워크**: Spring Boot 
- **데이터베이스**: MySQL 
- **API 통신**: REST API 
- **서버**: Tomcat, Node.js  
- **버전 관리**: Git

---

## 폴더 구조
프로젝트의 주요 폴더 구조를 설명합니다.

```plaintext
THEMEMORIALDAY/
 ┣ .gradle/                             # Gradle 관련 캐시 및 설정 파일
 ┣ .vscode/                             # Visual Studio Code 관련 설정 파일
 ┣ 시스템 아키텍처/                     # 시스템 아키텍처 문서 또는 다이어그램
 ┣ build/                               # Gradle 빌드 결과물이 저장되는 폴더
 ┣ gradle/                              # Gradle 관련 설정 파일 및 스크립트
 ┣ src/                                 # 소스 코드 디렉토리
 ┃ ┣ main/                              # 메인 소스 코드
 ┃ ┃ ┣ java/com/서비스 상위 폴더명/서비스명/ # 자바 소스 코드 경로
 ┃ ┃ ┃ ┣ common/                        # 공통 모듈 또는 유틸리티 클래스
 ┃ ┃ ┃ ┣ config/                        # 애플리케이션 설정 관련 클래스
 ┃ ┃ ┃ ┣ controller/                    # API 요청을 처리하는 컨트롤러
 ┃ ┃ ┃ ┣ dto/                           # 데이터 전송 객체(Data Transfer Object)
 ┃ ┃ ┃ ┣ entity/                        # 데이터베이스 엔티티 클래스
 ┃ ┃ ┃ ┣ filter/                        # 필터 클래스 (예: 인증, 로깅)
 ┃ ┃ ┃ ┣ handler/                       # 예외 처리기 또는 요청 핸들러
 ┃ ┃ ┃ ┣ provider/                      # 외부 서비스 제공자와의 인터페이스
 ┃ ┃ ┃ ┣ repository/                    # 데이터베이스 접근 레이어
 ┃ ┃ ┃ ┣ service/                       # 비즈니스 로직 처리 클래스
 ┃ ┃ ┃ ┣ ThememorialdayApplication.java # 메인 애플리케이션 클래스
 ┃ ┃ ┣ resources/                       # 리소스 파일 (예: 설정 파일, 정적 파일)
 ┃ ┃ ┃ ┣ application.properties         # 애플리케이션 설정 파일
 ┣ .gitattributes                        # Git 속성 설정 파일
 ┣ .gitignore                            # Git에서 무시할 파일 목록
 ┣ build.gradle                          # Gradle 빌드 설정 파일
 ┣ gradlew                               # Gradle Wrapper 스크립트 (Unix 계열)
 ┣ gradlew.bat                           # Gradle Wrapper 스크립트 (Windows)
 ┣ .HELP.md                              # 프로젝트 관련 도움말 문서
 ┣ hs_err_pid1256.log                    # JVM 오류 로그 파일
 ┗ README.md                             # 프로젝트 소개 파일
 ┗ replay_pid1256.log                    # JVM 재생 로그 파일
 ┗ settings.gradle                       # Gradle 설정 파일

```

---

## 설치 및 실행 

### 필수 조건
- **Java**: 3.3.3 이상 (jar 17 이상)
- **Gradle**: 3.8.1 이상
- **MySQL**: 8.0.31 이상 (데이터베이스 설정 필요)

### 설치 단계
1. 저장소 클론
   ```bash
   git clone https://github.com/TheMemorialDay/backend.git
2. 디렉토리로 이동
   ```bash
   cd 저장소이름
3. 데이터베이스 설정
- MySQL 서버를 실행하고 데이터베이스를 생성합니다.
- src/main/resources/application.properties 파일에서 다음을 설정합니다.
    ```bash
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://127.0.0.1:3306/데이터베이스이름?serverTimezone=UTC&characterEncoding=UTF-8
    spring.datasource.username=사용자이름
    spring.datasource.password=비밀번호
3. 의존성 추가
    ```bash
    runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.2'
    implementation 'io.jsonwebtoken:jjwt-api:0.11.2'
    runtimeOnly 'io.jsonwebtoken:jjwt-jackson::0.11.2'
4. 개발 서버 실행
   ```bash
   gradle bootRun
- 서버가 성공적으로 시작되면 http://localhost:4000에서 확인할 수 있습니다.

---
## 기타 설정
src/main/resources/application.properties 파일에서 다음을 설정합니다.

1. JWT 비밀키
   ```bash
   jwt.secret=JWT 토큰의 서명을 위한 비밀키
2. Request 사이즈 제한 조정 
   ```bash
   spring.servlet.multipart.max-file-size=업로드 가능한 최대 파일 크기 # 예시: 100MB
   spring.servlet.multipart.max-request-size=요청의 최대 크기 # 150MB 
3. file 작업 경로 
   ```bash
   file.path= 파일 경로
   file.url=파일 접근 URL
4. COOL SMS API KEY, SECRET KEY
   ```bash
   cool-sms.api-key=COOL SMS API 키
   cool-sms.secret-key=COOL SMS 비밀 키
   cool-sms.domain=COOL SMS 도메인
   cool-sms.from=발신자 전화번호
5. Kakao OAuth 클라이언트 설정 
   ```bash
   spring.security.oauth2.client.registration.kakao.client-id=Kakao 클라이언트 ID
   spring.security.oauth2.client.registration.kakao.client-secret=Kakao 클라이언트 비밀 키
   spring.security.oauth2.client.registration.kakao.redirect-uri={baseUrl}/oauth2/callback/{registrationId} # 리다이렉트 URI
   spring.security.oauth2.client.registration.kakao.authorization-grant-type=authorization_code # 인증 방식
   spring.security.oauth2.client.registration.kakao.client-authentication-method=client_secret_post # 클라이언트 인증 방법
   spring.security.oauth2.client.registration.kakao.scope=profile_nickname # 요청할 사용자 정보 범위
6. Kakao OAuth2 제공자 설정 
   ```bash
   spring.security.oauth2.client.provider.kakao.authorization-uri=Kakao 인증 URI
   spring.security.oauth2.client.provider.kakao.token-uri=Kakao 토큰 URI
   spring.security.oauth2.client.provider.kakao.user-info-uri=Kakao 사용자 정보 URI
   spring.security.oauth2.client.provider.kakao.user-name-attribute=id # 사용자 이름 속성
7. Naver OAuth 클라이언트 설정  
   ```bash
   spring.security.oauth2.client.registration.naver.client-id=Naver 클라이언트 ID
   spring.security.oauth2.client.registration.naver.client-secret=aver 클라이언트 비밀 키
   spring.security.oauth2.client.registration.naver.redirect-uri={baseUrl}/oauth2/callback/{registrationId} # 리다이렉트 URI
   spring.security.oauth2.client.registration.naver.authorization-grant-type=authorization_code # 인증 방식
   spring.security.oauth2.client.registration.naver.scope=email # 요청할 사용자 정보 범위
8. Naver OAuth2 제공자 설정 
   ```bash
   spring.security.oauth2.client.provider.naver.authorization-uri=Naver 인증 URI
   spring.security.oauth2.client.provider.naver.token-uri=Naver 토큰 URI
   spring.security.oauth2.client.provider.naver.user-info-uri=Naver 사용자 정보 URI
   spring.security.oauth2.client.provider.naver.user-name-attribute=response # 사용자 이름 속성
9. Google OAuth 설정 
   ```bash
   spring.security.oauth2.client.registration.google.client-id=Naver 인증 URI
   spring.security.oauth2.client.registration.google.client-secret=Naver 토큰 URI
   spring.security.oauth2.client.registration.google.redirect-uri={baseUrl}/oauth2/callback/{registrationId} # Naver 사용자 정보 URI
   spring.security.oauth2.client.registration.google.authorization-grant-type=authorization_code # 인증 방식
   spring.security.oauth2.client.registration.google.scope=profile,email  # 요청할 사용자 정보 범위
10. Google OAuth2 프로바이더 설정 
    ```bash
    spring.security.oauth2.client.provider.google.authorization-uri=Google 인증 URI 
    spring.security.oauth2.client.provider.google.token-uri=Google 토큰 URI 
11. 서버 포트 설정
    ```bash
    spring.application.name=thememorialday
    server.port=4000 
---

## 주요 기능
The Memorial Day가 제공하는 주요 기능입니다.  

 1. 로그인, 아이디 찾기, 비밀번호 찾기, 회원가입
 2. 일반 회원 및 사업자 등록 번호 인증을 통한 가게 사장님 회원 분리
 3. 주문 제작 가능한 케이크 상점 정보 제공
 4. 상점별 상품 정보 제공 및 주문 기능
 5. 상점 사장님과 일반 회원 사이의 주문 상태 공유 및 결제 기능
 6. 사용자가 많이 검색한 검색어와 사장님이 많이 적용한 테마를 word cloud 활용하여 시각적 정보 제공
  
### 마이페이지
#### 일반 회원
 1. 비밀번호 확인 후 개인 정보 수정 및 탈퇴
 2. 사용자 주문 내역 확인 가능
 3. 사용자가 작성한 리뷰 확인 가능
 4. 사용자가 찜한 가게 확인 가능

#### 상점 사장님 회원
 1. 일반 회원과 동일한 기능
 2. 상점 및 상품 정보 관리 가능
 3. 상점으로 들어온 주문 관리 기능
 4. 상점 전체 매출 확인 기능

The Memorial Day 서비스 시연 영상입니다. 

[![The Memorial Day 서비스 시연 영상](/README_img/fullshot.JPG)](https://www.youtube.com/watch?v=z0FtjE9dVl0)

---

## 시스템 아키텍처
본 프로젝트의 총 시스템 아키텍처입니다. 

![시스템 아키택처](/README_img/system_architecture.png)

---

## ERD
본 프로젝트의 Entity Relationship Diagram입니다. 

![Entity Relationship Diagram](/README_img/erd.png)

### 테이블 설명

1. `tel_auth`
- **설명**: 유저의 전화번호 인증 정보를 관리하는 테이블입니다.
- **주요 필드**:
  - `tel_number`: VARCHAR(11) NOT NULL, 유저 전화번호 (Primary Key)
  - `tel_auth_number`: VARCHAR(4) NOT NULL, 유저 전화번호 인증번호

2. `user`
- **설명**: 유저 정보를 저장하는 테이블로, 인증 및 권한 관리를 포함합니다.
- **주요 필드**:
  - `user_id`: VARCHAR(20) NOT NULL, 유저 아이디 (Primary Key)
  - `password`: VARCHAR(255) NOT NULL, 유저 비밀번호
  - `name`: VARCHAR(20) NOT NULL, 유저 이름
  - `tel_number`: VARCHAR(11) NOT NULL, 유저 전화번호 (Foreign Key, `tel_auth` 참조)
  - `birth`: VARCHAR(10) NOT NULL, 유저 생일
  - `gender`: VARCHAR(1) NOT NULL, 성별 (남 | 여)
  - `join_path`: VARCHAR(6) NOT NULL DEFAULT 'home', 가입 경로 (home | naver | kakao | google)
  - `sns_id`: VARCHAR(255) NULL DEFAULT NULL, SNS 아이디
  - `business_number`: VARCHAR(10) NULL DEFAULT 'null', 사업자 등록 번호
  - `business_url`: TEXT NULL DEFAULT NULL, 사업자 등록증 이미지
  - `permission`: VARCHAR(2) NOT NULL DEFAULT '일반', 권한 (일반 | 사장)
  - `business_opendate`: VARCHAR(10) NULL DEFAULT NULL, 사업자 개업일

3. `store`
- **설명**: 가게 정보를 저장하는 테이블입니다.
- **주요 필드**:
  - `store_number`: INT NOT NULL AUTO_INCREMENT, 가게 번호 (Primary Key)
  - `user_id`: VARCHAR(20) NOT NULL, 유저 아이디 (Foreign Key, `user` 참조)
  - `store_name`: VARCHAR(45) NOT NULL, 가게명
  - `store_introduce`: TEXT NULL DEFAULT NULL, 가게 소개
  - `store_particular`: TEXT NULL DEFAULT NULL, 가게 상세 소개글
  - `store_contact`: TEXT NULL DEFAULT NULL, 문의하기
  - `store_caution`: TEXT NULL DEFAULT NULL, 가게 유의사항
  - `store_address`: VARCHAR(255) NOT NULL, 가게 주소
  - `store_gugun`: VARCHAR(10) NOT NULL, 가게 구/군
  - `store_dong`: VARCHAR(10) NOT NULL, 가게 읍/동
  - `store_latitude`: VARCHAR(30) NOT NULL, 가게 위도
  - `store_longtitude`: VARCHAR(30) NOT NULL, 가게 경도
  - `store_tel`: VARCHAR(20) NULL DEFAULT NULL, 가게 연락처
  - `store_rating`: FLOAT NULL DEFAULT NULL, 가게 평점
  - `review_count`: INT NULL DEFAULT NULL, 리뷰 개수
  - `like_count`: INT NULL DEFAULT NULL, 찜한 횟수
  - `store_image_url`: TEXT NOT NULL, 가게 썸네일 이미지 URL
  - `sunday_open`, `sunday_last`, `monday_open`, `monday_last`, `tuesday_open`, `tuesday_last`, `wednesday_open`, `wednesday_last`, `thursday_open`, `thursday_last`, `friday_open`, `friday_last`, `saturday_open`, `saturday_last`: VARCHAR(5) NULL DEFAULT NULL, 각 요일의 오픈 및 마감 시간
  - `store_detail_address`: VARCHAR(30) NOT NULL, 가게 상세 주소

4. `like`
- **설명**: 유저가 찜한 가게 정보를 저장하는 테이블입니다.
- **주요 필드**:
  - `store_number`: INT NOT NULL, 가게 번호 (Foreign Key, `store` 참조)
  - `user_id`: VARCHAR(20) NOT NULL, 유저 아이디 (Foreign Key, `user` 참조)
  - PRIMARY KEY (`store_number`, `user_id`)

5. `notice`
- **설명**: 공지사항 정보를 저장하는 테이블입니다.
- **주요 필드**:
  - `notice_number`: INT NOT NULL AUTO_INCREMENT, 공지사항 번호 (Primary Key)
  - `notice_title`: VARCHAR(50) NOT NULL, 공지사항 제목
  - `notice_contents`: TEXT NOT NULL, 공지사항 내용
  - `notice_day`: DATE NOT NULL, 작성일

6. `product`
- **설명**: 상품 정보를 저장하는 테이블입니다.
- **주요 필드**:
  - `product_number`: INT NOT NULL AUTO_INCREMENT, 상품 번호 (Primary Key)
  - `store_number`: INT NOT NULL, 가게 번호 (Foreign Key, `store` 참조)
  - `product_name`: VARCHAR(30) NOT NULL, 상품 이름
  - `product_introduce`: VARCHAR(255) NULL DEFAULT NULL, 상품 소개
  - `product_price`: INT NOT NULL, 상품 가격
  - `product_today`: TINYINT NOT NULL DEFAULT '0', 당일 케이크 여부
  - `product_tag`: VARCHAR(10) NOT NULL, 상품 태그

7. `order`
- **설명**: 유저의 주문 정보를 저장하는 테이블입니다.
- **주요 필드**:
  - `order_code`: VARCHAR(13) NOT NULL, 주문 코드 (Primary Key)
  - `product_number`: INT NOT NULL, 상품 번호 (Foreign Key, `product` 참조)
  - `store_number`: INT NOT NULL, 가게 번호 (Foreign Key, `store` 참조)
  - `user_id`: VARCHAR(20) NOT NULL, 유저 아이디 (Foreign Key, `user` 참조)
  - `product_contents`: VARCHAR(255) NULL DEFAULT NULL, 요청 사항
  - `pickup_time`: VARCHAR(20) NOT NULL, 픽업 시간
  - `order_status`: VARCHAR(20) NOT NULL DEFAULT '승인대기중', 주문 상태
  - `product_count`: INT NOT NULL DEFAULT '1', 제품 수량
  - `total_price`: INT NOT NULL, 총 금액
  - `order_time`: VARCHAR(40) NOT NULL, 주문 시간
  - `cancel_code`: VARCHAR(40) NULL DEFAULT '재료가 소진 되었습니다', 주문 취소 사유
  - `cancel_reason`: VARCHAR(255) NULL DEFAULT '기타', 기타 사유
  - `option_select`: VARCHAR(255) NULL DEFAULT NULL, 주문 내역 옵션 리스트
  - `photo_url`: TEXT NULL DEFAULT NULL, 사진 URL

8. `order_select_option`
- **설명**: 주문한 상품에 대한 모든 옵션 매핑 정보를 저장하는 테이블입니다.
- **주요 필드**:
  - `order_select_number`: INT NOT NULL AUTO_INCREMENT, 주문 선택 번호 (Primary Key)
  - `order_code`: VARCHAR(13) NOT NULL, 주문 코드 (Foreign Key, `order` 참조)
  - `option_number`: INT NULL DEFAULT NULL, 옵션 번호
  - `option_category_number`: INT NOT NULL, 옵션 종류 번호

9. `payment`
- **설명**: 결제 정보를 저장하는 테이블입니다.
- **주요 필드**:
  - `order_code`: VARCHAR(13) NOT NULL, 주문 코드 (Primary Key, Foreign Key, `order` 참조)
  - `user_id`: VARCHAR(20) NOT NULL, 유저 아이디 (Foreign Key, `user` 참조)
  - `success`: VARCHAR(5) NOT NULL, 결제 성공 여부
  - `paid_amount`: INT NOT NULL, 결제 금액

10. `popular_keyword`
- **설명**: 인기 검색어 키워드를 저장하는 테이블입니다.
- **주요 필드**:
  - `keyword_id`: INT NOT NULL AUTO_INCREMENT, 인기 키워드 ID (Primary Key)
  - `keyword`: VARCHAR(30) NOT NULL, 검색어
  - `keyword_date`: DATE NOT NULL, 검색 날짜

11. `product_image`
- **설명**: 상품 이미지 정보를 저장하는 테이블입니다.
- **주요 필드**:
  - `product_image_number`: INT NOT NULL AUTO_INCREMENT, 상품 이미지 번호 (Primary Key)
  - `product_number`: INT NOT NULL, 상품 번호 (Foreign Key, `product` 참조)
  - `product_image_url`: TEXT NOT NULL, 상품 이미지 URL

12. `product_mapping`
- **설명**: 상품 옵션을 매핑하는 테이블입니다.
- **주요 필드**:
  - `option_number`: INT NOT NULL AUTO_INCREMENT, 옵션 번호 (Primary Key)
  - `product_number`: INT NOT NULL, 상품 번호 (Foreign Key, `product` 참조)
  - `product_option_name`: VARCHAR(30) NOT NULL, 옵션 이름 (예: 맛, 색상 등)

13. `product_option`
- **설명**: 상품의 옵션 정보를 저장하는 테이블입니다.
- **주요 필드**:
  - `option_category_number`: INT NOT NULL AUTO_INCREMENT, 옵션 종류 번호 (Primary Key)
  - `option_number`: INT NOT NULL, 옵션 번호 (Foreign Key, `product_mapping` 참조)
  - `product_category`: VARCHAR(10) NOT NULL, 옵션 종류 (예: 딸기맛, 초코맛 등)
  - `product_option_price`: INT NOT NULL DEFAULT '0', 옵션 가격

14. `question_answer`
- **설명**: Q&A 정보를 저장하는 테이블입니다.
- **주요 필드**:
  - `question_number`: INT NOT NULL AUTO_INCREMENT, Q&A 번호 (Primary Key)
  - `question_title`: VARCHAR(50) NOT NULL, Q&A 제목
  - `question_contents`: TEXT NOT NULL, Q&A 내용
  - `question_day`: DATE NOT NULL, 작성일
  - `user_id`: VARCHAR(20) NOT NULL, 작성자 아이디 (Foreign Key, `user` 참조)
  - `question_status`: VARCHAR(4) NOT NULL DEFAULT '미응답', Q&A 상태 (미응답 | 응답완료)
  - `answer_contents`: TEXT NULL DEFAULT NULL, Q&A 답변 내용

15. `review`
- **설명**: 상품에 대한 리뷰 정보를 저장하는 테이블입니다.
- **주요 필드**:
  - `review_number`: INT NOT NULL AUTO_INCREMENT, 리뷰 번호 (Primary Key)
  - `order_code`: VARCHAR(13) NOT NULL, 주문 코드 (Foreign Key, `order` 참조)
  - `review_rating`: INT NOT NULL DEFAULT '5', 별점 (1 | 2 | 3 | 4 | 5)
  - `review_day`: DATE NOT NULL, 리뷰 작성일
  - `review_contents`: VARCHAR(100) NULL DEFAULT NULL, 리뷰 내용
  - `store_name`: VARCHAR(45) NOT NULL, 가게 이름
  - `product_name`: VARCHAR(30) NOT NULL, 상품 이름
  - `user_id`: VARCHAR(20) NOT NULL, 유저 아이디 (Foreign Key, `user` 참조)

16. `review_photo`
- **설명**: 리뷰에 첨부된 사진 정보를 저장하는 테이블입니다.
- **주요 필드**:
  - `review_photo_number`: INT NOT NULL AUTO_INCREMENT, 리뷰 사진 번호 (Primary Key)
  - `review_number`: INT NOT NULL, 리뷰 번호 (Foreign Key, `review` 참조)
  - `review_photo_url`: TEXT NULL DEFAULT NULL, 리뷰 사진 URL

17. `thema`
- **설명**: 상품의 테마 정보를 저장하는 테이블입니다.
- **주요 필드**:
  - `thema`: VARCHAR(10) NOT NULL, 테마 내용 (Primary Key)
  - `product_number`: INT NOT NULL, 상품 번호 (Foreign Key, `product` 참조)

주요 특징
- **유저 관리**: 유저 정보를 체계적으로 저장하고, 전화번호 인증을 통해 보안 강화
- **가게 정보**: 각 가게의 세부 정보를 저장하여 사용자가 쉽게 정보를 검색하고 접근
- **주문 및 결제 처리**: 주문과 결제를 명확하게 분리하여 데이터 처리를 간소화
- **리뷰 및 피드백 시스템**: 유저가 상품에 대한 리뷰를 작성할 수 있어, 다른 유저들에게 유용한 정보를 제공
- **상품 옵션 관리**: 다양한 상품 옵션을 유연하게 관리할 수 있도록 설계


---

## 시퀀스 다이어그램
본 프로젝트의 주요 기능들에 대한 시퀀스 다이어그램을 설명하는 다이어그램이 '시퀀스 다이어그램' 폴더 내에 제공되고 있습니다.

- **아이디 찾기**  

![시퀀스 다이어그램](/README_img/Sequence%20diagram/sequence_diagram_id.png)

- **상품 등록**  

![시퀀스 다이어그램](/README_img/Sequence%20diagram/sequence_diagram_product.png)

- **상품 정보 불러오기**  

![시퀀스 다이어그램](/README_img/Sequence%20diagram/sequence_diagram_order.png)

- **주문 상태 관리**  

![시퀀스 다이어그램](/README_img/Sequence%20diagram/sequence_diagram_order_detail.png)

---

## 제작 기간
### Timeline
![진행 일정](/README_img/schedule.jpg)

### Team Roles

<table style="table-layout: fixed; width: 100%;">
  <thead>
    <tr>
      <th style="width: 20%;">이름</th>
      <th style="width: 80%;">작업 내용</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="text-align: center; width: 20%;">안찬숙</td>
      <td>회원가입, 로그인(소셜 포함) 및 토큰 발급, 회원 탈퇴 및 수정, 아이디 및 비밀번호 찾기(재설정 포함), 가게 검색 및 필터링, 인기 키워드</td>
    </tr>
    <tr>
      <td style="text-align: center; width: 20%;">송태휘</td>
      <td>가게 상세 정보, 가게 등록 및 수정, 결제, 찜 등록 및 삭제, 가게 검색 및 필터링, 인기 키워드</td>
    </tr>
    <tr>
      <td style="text-align: center; width: 20%;">정호정</td>
      <td>가게 별 상품 조회, 주문서 조회, 사업자 등록, 리뷰 작성 및 조회, 공지사항 작성 및 삭제, Q&A 작성 및 삭제</td>
    </tr>
    <tr>
      <td style="text-align: center; width: 20%;">김도연</td>
      <td>상품(테마 및 옵션 포함) 등록 / 수정 / 삭제, 마이페이지 상품리스트 조회, 주문 등록, 매출 관리</td>
    </tr>
  </tbody>
</table>

#### Contributors
<table style="table-layout: fixed; width: 100%;">
  <thead>
    <tr>
      <th style="width: 20%;">이름</th>
      <th style="width: 80%;">GitHub Link</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="text-align: center; width: 20%;">안찬숙</td>
      <td>https://github.com/Anchansook</td>
    </tr>
    <tr>
      <td style="text-align: center; width: 20%;">송태휘</td>
      <td>https://github.com/Song-Tae-Hwi</td>
    </tr>
    <tr>
      <td style="text-align: center; width: 20%;">정호정</td>
      <td>https://github.com/ristukaJJang</td>
    </tr>
    <tr>
      <td style="text-align: center; width: 20%;">김도연</td>
      <td>https://github.com/pdu08075</td>
    </tr>
  </tbody>
</table>

## 라이선스
    GPL License
    Copyright (c) 2024 [ThememorialDay]