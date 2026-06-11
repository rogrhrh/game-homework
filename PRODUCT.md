# 겜숙제

## 서비스 개요

게임 유저가 반복 할 일 묶음(Routine)을 만들고, 공유 링크로 다른 사람에게 쉽게 공유할 수 있는 서비스다.
다른 사용자는 공유된 Routine을 복사해서 자기 Routine으로 사용할 수 있다.

---

## 핵심 도메인: Routine

Routine은 유저가 만든 하나의 게임 반복 할 일 묶음이다.

- 메이플스토리 일일/주간 숙제표
- 로스트아크 레이드 체크리스트
- 던전앤파이터 이벤트 루틴
- XCOM2 캠페인 진행 체크리스트

이런 것들이 각각 하나의 Routine이다.
Routine은 저장할 수 있고, 공유 링크를 가질 수 있으며, 다른 사용자가 복사해서 자기 Routine으로 사용할 수 있다.
복사된 Routine도 별도의 독립적인 Routine으로 저장된다.

---

## 해결하려는 문제

게임마다 일일/주간 숙제, 이벤트, 보스, 재화 수급 할 일이 흩어져 있고,
유저들은 구글시트, 메모장, 커뮤니티 글로 직접 숙제표를 만들어 쓰는 경우가 많다.
하지만 공유, 복사, 체크 상태 관리가 불편하다.

---

## MVP 핵심 흐름

1. Routine 생성
2. RoutineItem 추가
3. 공유 링크 생성
4. 공유 링크로 Routine 조회
5. 공유된 Routine 복사
6. 복사한 Routine에서 항목 완료/미완료 토글

---

## MVP 범위

### 포함

- Routine 생성
- RoutineItem 생성
- 반복 주기 설정 (DAILY / WEEKLY / MONTHLY / ONCE)
- 공유용 slug 생성
- 공유 Routine 조회
- Routine 복사
- Routine 조회
- RoutineItem 완료/미완료 토글

### 제외

- 로그인 / 회원가입
- 결제
- 알림
- 자동 초기화 스케줄러
- 게임별 공식 데이터 연동
- AI 기능
- 복잡한 권한 관리

---

## 기술 스택

- Java 21
- Spring Boot 3.5.x
- Gradle Kotlin DSL
- H2 Database
- Spring Data JPA
- Validation
- Lombok
