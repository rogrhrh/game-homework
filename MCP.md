# MCP 명세

> MVP 기준 초안입니다.
> 루틴덱 MCP 서버는 같은 Spring Boot 앱에 통합되며, 서비스 레이어를 직접 호출합니다.

---

## 개요

루틴덱은 웹 인터페이스 외에 MCP(Model Context Protocol) 인터페이스를 제공합니다.

사용자가 쓰는 LLM(Claude, ChatGPT, Cursor 등)이 MCP를 통해 루틴덱에 접근하면, LLM이 개인 게임 비서처럼 루틴을 조회·추천·체크할 수 있습니다.

루틴덱은 자체 AI를 운영하지 않습니다. AI 처리는 사용자가 쓰는 LLM 클라이언트가 담당하고, 루틴덱은 그 LLM이 사용할 수 있는 루틴 데이터와 도구를 제공합니다.

---

## 인증

MCP 클라이언트는 PersonalAccessToken(PAT)으로 인증합니다.

1. 루틴덱 웹에서 `POST /api/v1/tokens`로 PAT 발급
2. LLM 클라이언트 설정에 PAT 등록
3. MCP 요청 시 `Authorization: Bearer <pat>` 헤더로 전달

---

## Tool 목록

### Read Tools

인증 없이 사용할 수 있는 툴과 인증이 필요한 툴을 분리합니다.

| Tool | 설명 | 인증 |
|---|---|---|
| `search_public_routines` | 게임명, 태그, 키워드로 공개 루틴 검색 | 불필요 |
| `get_public_routine_detail` | 공개 루틴 상세 및 아이템 목록 조회 | 불필요 |
| `get_my_routine_deck_today` | 내 루틴덱에서 오늘 해야 할 아이템 조회 | 필요 |
| `get_my_routine_deck_week` | 내 루틴덱에서 이번 주 해야 할 아이템 조회 | 필요 |

### Write Tools

쓰기 작업은 사용자 확인 후 수행하는 것을 권장합니다.

| Tool | 설명 | 인증 |
|---|---|---|
| `subscribe_routine` | 공개 루틴을 내 루틴덱에 구독 | 필요 |
| `check_routine_item` | 루틴 아이템 완료 처리 | 필요 |
| `uncheck_routine_item` | 루틴 아이템 완료 취소 | 필요 |

---

## 보안 원칙

### 읽기 / 쓰기 분리
읽기 작업과 쓰기 작업은 명확히 분리합니다. 쓰기 작업은 사용자 확인 후 수행합니다.

### 최소 권한
MCP를 통해 허용하지 않는 작업 목록입니다.

- 루틴 삭제
- 루틴 수정
- 공개 / 비공개 변경
- 계정 삭제 / 비밀번호 변경
- 다른 사용자의 데이터 접근
- 관리자 기능

### 권한 검증
모든 요청은 백엔드에서 권한을 재검증합니다. PAT의 scope 범위 내에서만 동작합니다.

---

## PAT Scope

| Scope | 설명 |
|---|---|
| `routine:read_public` | 공개 루틴 검색 및 상세 조회 |
| `routine:read_my` | 내 루틴덱 조회 |
| `routine:write_subscription` | 루틴 구독 |
| `routine:write_check` | 루틴 아이템 체크 / 해제 |

---

## 클라이언트 연결 예시

### Claude Desktop

`claude_desktop_config.json`에 아래 내용을 추가합니다.

```json
{
  "mcpServers": {
    "routinedeck": {
      "url": "http://localhost:8080/mcp",
      "headers": {
        "Authorization": "Bearer <your-pat>"
      }
    }
  }
}
```

### Cursor

`.cursor/mcp.json`에 아래 내용을 추가합니다.

```json
{
  "mcpServers": {
    "routinedeck": {
      "url": "http://localhost:8080/mcp",
      "headers": {
        "Authorization": "Bearer <your-pat>"
      }
    }
  }
}
```

---

## 사용 예시

```
사용자: 오늘 내가 하는 게임들 숙제 뭐 남았어?

LLM → get_my_routine_deck_today 호출
LLM: 루틴덱을 확인해보니 원신 3개, 메이플 4개가 남아 있습니다.
     시간이 부족하다면 원신 레진 소모, 메이플 이벤트 출석부터 처리하는 것을 추천합니다.

사용자: 원신 일일 의뢰 끝냈어. 체크해줘.

LLM → check_routine_item 호출
LLM: 원신 일일 의뢰를 완료 처리했습니다.
```
