<p align="center">
    <img width="200px;" src="https://raw.githubusercontent.com/woowacourse/atdd-subway-admin-frontend/master/images/main_logo.png"/>
</p>
<p align="center">
  <img alt="npm" src="https://img.shields.io/badge/npm-%3E%3D%205.5.0-blue">
  <img alt="node" src="https://img.shields.io/badge/node-%3E%3D%209.3.0-blue">
  <a href="https://edu.nextstep.camp/c/R89PYi5H" alt="nextstep atdd">
    <img alt="Website" src="https://img.shields.io/website?url=https%3A%2F%2Fedu.nextstep.camp%2Fc%2FR89PYi5H">
  </a>
  <img alt="GitHub" src="https://img.shields.io/github/license/next-step/atdd-subway-service">
</p>

<br>

# 지하철 노선도 미션
[ATDD 강의](https://edu.nextstep.camp/c/R89PYi5H) 실습을 위한 지하철 노선도 애플리케이션

<br>

## 요구사항 분석

### Step4 경로 조회 - 거리별 요금 정책 추가
1. 기본 운임(10km 이내): 1,250 원
2. 이용 거리 초과 시 추가운임 부과
   * 10km초과 ~ 50km까지(5km 마다 100원)
   * 50km초과 시 (8km 마다 100원)
   ```ruby
   Feature: 지하철 요금 조회 기능
     Backgorund: 지하철 경로 탐색
       Given 지하철역 등록되어 있음지(출발지, 목적지)
       And 지하철 노선 등록되어 있음(테스트를 위해 여러 노선)
       And 지하철 노선에 지하철역 등록되어 있음
       And 지하철 노선에 구간 등록되어 있음
       And 지하철 노선에 추가 요금노선 등록되어 있음
       And 연령별 요금 할인 적용되어 있음

     Scenario: 지하철 요금조회
       When 경로탐색 구간조회
       Then 경로탐색 거리에 대한 요금 조회
       Then 경로 노선에 추가요금 노선 조회
       When 사용자 로그인
       Then 연령별 요금 할인 적용
   ```

3. 지하철 경로 검색 시나리오 수정
   ```ruby
    Feature: 지하철 경로 탐색 기능

      Background: 지하철, 노선 등록
        Given 지하철역 등록되어 있음지(출발지, 목적지)
        And 지하철 노선 등록되어 있음(테스트를 위해 여러 노선)
        And 지하철 노선에 지하철역 등록되어 있음
        And 지하철 노선에 구간 등록되어 있음

      Scenario: 지하철 경로 탐색 및 요금조회
        When 출발역와 도착역 경로 요청
        Then 연결된 한 노선의 최단거리 조회
        Then 포함된 여러 노선의 구간 거리를 비교하여 최단거리 조회
        When 최단거리 기준으로 지하철 이용 요금 계산
        Then 지하철 이용 요금 조회
   ```

### Step3 인증을 통한 기능 구현

1. 토큰 발급 기능(로그인) - 인수테스트 만들기
   * 인수 테스트 시나리오
   ```ruby
   Feature: 로그인 기능
     
     Scenario: 로그인을 시도한다.
       Given 회원 등록되어 있음
       When 로그인 요청
       Then 로그인
   ```
2. 인증 - 내정보 조회 기능 완성
3. 인증 - 즐겨찾기 기능 완성
   ```ruby
   Feature: 즐겨찾기를 기능
     
     Background
       Given 지하철역 등록되어 있음
       And 지하철 노선 등록되어 있음
       And 지하철 노선에 지하철에 등록되어 있음
       And 회원 등록되어 있음
       And 로그인 되어 있음
       
     Scenario: 즐겨찾기 관리 기능
       When 즐겨찾기 생성을 요청
       Then 즐겨찾기 생성됨
       When 즐겨찾기 목록 조회 요청
       Then 즐겨찾기 목록 조회 됨
       When 즐겨찾기 삭제 요청
       Then 즐겨찾기 삭제됨
   ```

### Step2 경로 조회 기능 구현

1. 최단 경로 조회 인수 테스트
   *  최단 경로 라이브러리 학습
   *  외부 라이브러리 테스트 -> 구현하는 로직 검증(지하철 경로 탐색)
   *  인수 테스트 시나리오 작성
    ```ruby
    Feature: 지하철 경로 탐색 기능
    
      Background: 지하철, 노선 등록
        Given 지하철역 등록되어 있음지(출발지, 목적지)
        And 지하철 노선 등록되어 있음(테스트를 위해 여러 노선)
        And 지하철 노선에 지하철역 등록되어 있음
        And 지하철 노선에 구간 등록되어 있음
    
      Scenario: 지하철 경로 탐색
        When 출발역와 도착역 경로 요청
        Then 연결된 한 노선의 최단거리 조회
        Then 포함된 여러 노선의 구간 거리를 비교하여 최단거리 조회
     ```
2. 최단 경로 조회 기능 구현
   * [x] 출발역과 도착역이 같은 경우 (RuntimeException)
   * [x] 출발역과 도착역이 연결이 되어 있지 않은 경우 (NothingException)
   * [x] 존재하지 않은 출발역이나 도착역을 조회 할 경우 (NothingException)

## 🚀 Getting Started

### Install
#### npm 설치
```
cd frontend
npm install
```
> `frontend` 디렉토리에서 수행해야 합니다.

### Usage
#### webpack server 구동
```
npm run dev
```
#### application 구동
```
./gradlew bootRun
```
<br>

## ✏️ Code Review Process
[텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

<br>

## 🐞 Bug Report

버그를 발견한다면, [Issues](https://github.com/next-step/atdd-subway-service/issues) 에 등록해주세요 :)

<br>

## 📝 License

This project is [MIT](https://github.com/next-step/atdd-subway-service/blob/master/LICENSE.md) licensed.
