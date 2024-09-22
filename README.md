# BackEnd

# <팔공산> - <경대마블>

### FrontEnd Repository
https://github.com/commercial-app/Frontend

## 서비스 요약
<!-- 서비스 명과 함께 1 ~ 2 줄의 짧은 서비스 요약
ex) 탁구왕 - 실력별 탁구 매칭 서비스 -->
경대마블 - 대구 소재지 음식점이나 관광지을 방문하여 주어진 미션을 수행하여 지역화폐로 교환 가능한 마일리지를
획득하는 서비스

## 주제 구분
-	C타입 대구 지역 상권을 살리는데 도움을 주는 서비스 
<!-- -	S타입 대구 시내의 환경 문제를 해결하고 지속가능한 발전을 지원하는 서비스 
-	E타입 경북대에 다니는 다양한 배경의 학우들을 위한 서비스 -->

* 해당되는 타입 하나를 선택해주시고, 나머지 타입은 지워주세요. 

## 팀원 소개
팀명과 팀원 소개
팔공산
컴퓨터학부 2022428298  정해찬
컴퓨터학부 2022428757 성은우
컴퓨터학부 2022428535 안재민
컴퓨터학부 2022428572 한승연

## 시연 영상
(필수) Youtube 링크
(선택) Github Repository 페이지에서 바로 볼 수 있도록 넣어주셔도 좋습니다.

## 서비스 소개
### 서비스 개요
서비스에 대한 개요/요약을 개략적으로 작성

<!-- ex) 
탁구를 치기 위해 각자의 실력에 맞는 사용자를 찾아 매칭해주는 서비스이다.
사용자는 서비스를 통해 실력이 비슷한 상대를 찾을 수 있다.
또한, 서비스를 통해 탁구와 관련된 정보를 쉽게 찾아보거나 탁구 용품을 쉽게 구매할 수 있다. -->

'경대마블'은 대구 지역의 맛집, 관광명소, 문화시설을 탐방하며 인증샷을 남기고 미션을 수행하면 마일리지를 지급받는 웹 서비스입니다. 사용자는 이 서비스를 통해 대구의 다양한 상권을 경험하고, 적립한 마일리지를 대구 지역화폐로 교환하여 지역 내 소비를 촉진할 수 있습니다. 

### 타서비스와의 차별점
맛집, 관광지를 추천만 해주는 타 서비스와는 달리 사용자에게 각 지역별 상권을 추천해주고 미션을 완수하여 얻을 수 있는 마일리지를 통해 대구 지역상품권으로 교환하여, 지속가능한 경제를 지향한다는 점에서 차이가 있음.

### 구현 내용 및 결과물
서비스의 실제 구현 내용과 결과물을 기재한다.

<!-- ex) -->
<!-- 1. 실력별 매칭 시스템
  - 본인이 미리 선택한 탁구 실력에 맞추어 다른 사용자를 매칭해준다.
  - 매칭된 사용자와의 매칭이 종료된 이후, 상대의 실력을 평가할 수 있다. -->
<!-- 2. 탁구 용품 구매 페이지 -->
1. '경대마블'은 사용자가 주사위를 굴려 게임판 위의 타일을 이동하면서 다양한 미션을 수행하고 마일리지를 적립하는 방식으로 구현된 게임입니다. 게임의 전체적인 흐름은 간단하면서도 직관적인 주사위 굴리기 메커니즘을 기반으로 하여 사용자에게 즐거움을 제공하며, 동시에 마일리지 적립과 지역 경제 활성화를 목표로 하고 있습니다.

2. 대구FC 경기일정 api가 존재하지 않아 Jsoup 라이브러리로 대구FC 경기일정, 인터파크 공연일정을 크롤링하여 미션을 추가하여 사용자들이 대구의 다양한 문화생활을 즐기고 해당 지역을 방문하게 유도하여 해당지역 상권 활성화를 목표로 하였습니다.  

### ERD 다이어그램
![다운로드](https://github.com/user-attachments/assets/6b29f927-7569-4eeb-8b5e-de3110e7687c)

### API 명세서

| 기능               | HTTP매서드 | URL                           | Header                        | Request                           | Status     & Response                |
|--------------------|------------|-------------------------------|-------------------------------|------------------------------------|-------------------------------------|
| 회원가입 요청     | POST       | /api/register                 | Content-Type: application/json | {                                  |
|                    |            |                               |                               |   "name": "사용자 이름",          |
|                    |            |                               |                               |   "email": "이메일 주소",         |
|                    |            |                               |                               |   "password": "비밀번호"           |
|                    |            |                               |                               | }                                  | 201 Created                          |
| 로그인 요청       | POST       | /api/login                    | Content-Type: application/json | {                                  |
|                    |            |                               |                               |   "email": "이메일 주소",         |
|                    |            |                               |                               |   "password": "비밀번호"           |
|                    |            |                               |                               | }                                  | 200 OK                              |
|                    |            |                               |                               |                                    | { "token": "액세스 토큰" }         |
| 보드 정보 요청     | GET        | /api/board                    | Authorization: Bearer {token} |                                    | 200 OK                              |
|                    |            |                               |                               | {                                   |                                  |
|                    |            |                               |                               |   "board_id": "보드 ID",          |
|                    |            |                               |                               |   "member_position": "멤버 위치", |
|                    |            |                               |                               |   "tiles": [                      |
|                    |            |                               |                               |     {                            |
|                    |            |                               |                               |       "tile_id": "타일 ID",       |
|                    |            |                               |                               |       "order": "타일 순서",       |
|                    |            |                               |                               |       "state": "타일 상태",       |
|                    |            |                               |                               |       "mission": {                |
|                    |            |                               |                               |         "mission_id": "미션 ID",   |
|                    |            |                               |                               |         "title": "미션 제목",      |
|                    |            |                               |                               |         "content": "미션 내용",    |
|                    |            |                               |                               |         "image_url": "이미지 URL", |
|                    |            |                               |                               |         "category_name": "카테고리 이름", |
|                    |            |                               |                               |         "mission_summit_state": "미션 제출 상태", |
|                    |            |                               |                               |         "rejection": "거절 사유"   |
|                    |            |                               |                               |       }                            |
|                    |            |                               |                               |     }                              |
|                    |            |                               |                               |   ]                                |
|                    |            |                               |                               | }                                  |
| 유저 이동 요청    | POST       | /api/board/{board_id}/move    | Authorization: Bearer {token} |                                    |
|                    |            |                               |                               |   Param: dice=                    |
|                    |            |                               |                               |   Param: is_cycle=                | 200 OK                              |
|                    |            |                               |                               |                                    | {                                  |
|                    |            |                               |                               |   "board_id": "보드 ID",          |
|                    |            |                               |                               |   "member_position": "멤버 위치", |
|                    |            |                               |                               |   "tiles": [                      |
|                    |            |                               |                               |     {                            |
|                    |            |                               |                               |       "tile_id": "타일 ID",       |
|                    |            |                               |                               |       "order": "타일 순서",       |
|                    |            |                               |                               |       "state": "타일 상태",       |
|                    |            |                               |                               |       "mission": {                |
|                    |            |                               |                               |         "mission_id": "미션 ID",   |
|                    |            |                               |                               |         "title": "미션 제목",      |
|                    |            |                               |                               |         "content": "미션 내용",    |
|                    |            |                               |                               |         "image_url": "이미지 URL", |
|                    |            |                               |                               |         "category_name": "카테고리 이름", |
|                    |            |                               |                               |         "mission_summit_state": "미션 제출 상태", |
|                    |            |                               |                               |         "rejection": "거절 사유"   |
|                    |            |                               |                               |       }                            |
|                    |            |                               |                               |     }                              |
|                    |            |                               |                               |   ]                                |
|                    |            |                               |                               | }                                  |
| 미션 제출 요청    | POST       | /api/board/{mission_id}      | Authorization: Bearer {token} | Content-Type: application/json     |
|                    |            |                               |                               | {                                  |
|                    |            |                               |                               |   "image_url": "이미지 URL",      |
|                    |            |                               |                               |   "content": "미션 내용"           |
|                    |            |                               |                               | }                                  | 200 OK                              |
| 대구FC 경기 일정 요청 | GET     | /api/daegu-fc/schedule       | Content-Type: application/json | {                                  |
|                    |            |                               |                               |   "request": "Get match data for DGB Stadium" | 200 OK                              |
|                    |            |                               |                               | }                                  | [                                  |
|                    |            |                               |                               | {                                  |
|                    |            |                               |                               |   "number": "경기 번호",          |
|                    |            |                               |                               |   "date": "경기 날짜 및 시간",    |
|                    |            |                               |                               |   "stadium": "경기장 이름",       |
|                    |            |                               |                               |   "away": "상대 팀 이름"          |
|                    |            |                               |                               | },                                 |
|                    |            |                               |                               | // ... 나머지 경기 데이터        |
|                    |            |                               |                               | ]                                  |
| 유저 정보 요청    | GET        | /api/member                  | Authorization: Bearer {token} |                                    | 200 OK                              |
|                    |            |                               |                               | {                                  |
|                    |            |                               |                               |   "email": "이메일",              |
|                    |            |                               |                               |   "name": "이름",                 |
|                    |            |                               |                               |   "point": "포인트"               |
|                    |            |                               |                               | }                                  |







### 구현 방식

어떤 언어와 프레임워크를 사용했는지, 만약 배포를 진행했다면 환경은 어떤지 등을 기재
ex) 프론트엔드로는 TypeScript와 React를 사용, 백엔드로는 Java와 Spring을 사용. AWS를 통해 배포를 진행

백앤드로는 Java와 Spring을 사용하였고, AWS를 통하여 배포를 진행하였습니다.



## 향후 개선 혹은 발전 방안

현재 개발된 서비스에서 향후 개선되거나 발전될 수 있는 부분에 대한 설명

1. 개인화 추천 시스템: 이용자의 탐방 기록과 선호도를 분석하여 맞춤형 맛집, 명소, 이벤트를 추천하는 기능을 추가합니다. 이를 통해 개인화된 경험을 제공합니다.

2. VR/AR 체험: 대구의 명소나 맛집을 가상현실(VR)이나 증강현실(AR)로 미리 체험할 수 있는 기능을 도입하여 이용자들이 더욱 흥미롭게 지역을 탐방할 수 있게 합니다.

3. 모바일 앱 개발: 웹 서비스 외에도 모바일 앱을 개발하여 더 편리하게 서비스를 이용할 수 있도록 하고, GPS 기반으로 주변 상가를 추천하는 기능을 추가합니다.

4. 지속 가능한 소비 촉진: 친환경 가게와의 협업을 통해, 지속 가능한 소비를 장려하는 프로그램을 도입합니다. 예를 들어, 친환경 가게를 방문하면 추가 마일리지를 제공하는 방식입니다.
