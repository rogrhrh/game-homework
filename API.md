# API 명세

> MVP 기준 초안입니다.
> 기본 prefix: `/api/v1`

---

## 인증

| 환경 | 방식 | 비고 |
|---|---|---|
| prod | OAuth 2.1 | 웹 API, MCP 모두 OAuth 통일 |
| dev / test | PAT | MCP 테스트 편의를 위해 PAT 허용 |

웹 API는 OAuth 액세스 토큰을 `Authorization: Bearer <token>` 헤더로 전달합니다.

---

## 공통 에러 응답

```json
{
  "code": "ERROR_CODE",
  "message": "에러 메시지"
}
```

| HTTP 상태 | 설명 |
|---|---|
| 400 | 잘못된 요청 (입력값 오류) |
| 401 | 인증 필요 |
| 403 | 권한 없음 |
| 404 | 리소스 없음 |
| 409 | 충돌 (중복 등) |

---

## 커서 기반 페이지네이션

공개 루틴 검색에 적용합니다.

**요청 파라미터**
```
?cursor=xxx&limit=20
```

**응답 형태**
```json
{
  "data": [...],
  "nextCursor": "xxx",
  "hasNext": true
}
```

---

## Auth

| 메서드 | 경로 | 설명 | 인증 |
|---|---|---|---|
| POST | `/api/v1/auth/register` | 회원가입 | 불필요 |
| POST | `/api/v1/auth/login` | 로그인 | 불필요 |

---

## Routine

| 메서드 | 경로 | 설명 | 인증 |
|---|---|---|---|
| POST | `/api/v1/routines` | 루틴 생성 | 필요 |
| GET | `/api/v1/routines` | 공개 루틴 검색 (커서 기반) | 불필요 |
| GET | `/api/v1/routines/{slug}` | 공개 루틴 상세 조회 | 불필요 |
| PUT | `/api/v1/routines/{id}` | 루틴 수정 | 필요 (작성자) |
| DELETE | `/api/v1/routines/{id}` | 루틴 삭제 | 필요 (작성자) |
| GET | `/api/v1/routines/game-names?q=` | 게임명 자동완성 | 불필요 |

---

## RoutineItem

| 메서드 | 경로 | 설명 | 인증 |
|---|---|---|---|
| POST | `/api/v1/routines/{id}/items` | 아이템 추가 | 필요 (작성자) |
| PUT | `/api/v1/routines/{id}/items/{itemId}` | 아이템 수정 | 필요 (작성자) |
| DELETE | `/api/v1/routines/{id}/items/{itemId}` | 아이템 삭제 | 필요 (작성자) |

---

## RoutineTag

| 메서드 | 경로 | 설명 | 인증 |
|---|---|---|---|
| POST | `/api/v1/routines/{id}/tags` | 태그 추가 | 필요 (작성자) |
| DELETE | `/api/v1/routines/{id}/tags/{tagId}` | 태그 삭제 | 필요 (작성자) |

---

## Subscription

| 메서드 | 경로 | 설명 | 인증 |
|---|---|---|---|
| POST | `/api/v1/routines/{id}/subscribe` | 루틴 구독 | 필요 |
| DELETE | `/api/v1/routines/{id}/subscribe` | 구독 취소 | 필요 |

---

## My Deck

| 메서드 | 경로 | 설명 | 인증 |
|---|---|---|---|
| GET | `/api/v1/me/deck` | 내 루틴덱 전체 조회 | 필요 |
| GET | `/api/v1/me/deck/today` | 오늘 할 일 조회 | 필요 |
| GET | `/api/v1/me/deck/week` | 이번 주 할 일 조회 | 필요 |

---

## RoutineItemCheck

| 메서드 | 경로 | 설명 | 인증 |
|---|---|---|---|
| POST | `/api/v1/routine-items/{id}/check` | 아이템 체크 | 필요 |
| DELETE | `/api/v1/routine-items/{id}/check` | 체크 해제 | 필요 |

---

## PersonalAccessToken (MCP용)

| 메서드 | 경로 | 설명 | 인증 |
|---|---|---|---|
| GET | `/api/v1/tokens` | 토큰 목록 조회 | 필요 |
| POST | `/api/v1/tokens` | 토큰 발급 | 필요 |
| DELETE | `/api/v1/tokens/{id}` | 토큰 폐기 | 필요 |
