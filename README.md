# 루틴덱

**게임 루틴을 만들고, 공유하고, 구독하세요.**
사용자가 쓰는 AI가 MCP를 통해 개인 게임 비서처럼 루틴을 조회하고 체크해줍니다.

![Java](https://img.shields.io/badge/Java_21-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_3.5-6DB33F?style=flat&logo=springboot&logoColor=white)
![Spring AI](https://img.shields.io/badge/Spring_AI_MCP-6DB33F?style=flat&logo=spring&logoColor=white)
![H2](https://img.shields.io/badge/H2-004088?style=flat&logo=h2&logoColor=white)

---

## 어떤 서비스인가요?

게임을 하다 보면 이런 상황이 반복됩니다.

- 복귀했는데 지금 뭐부터 해야 하지?
- 여러 게임을 같이 하는데 오늘 숙제 뭐 남았지?
- 길드원한테 매번 같은 설명을 반복해야 한다.

루틴덱은 게임 공략을 **체크 가능한 루틴**으로 만들고, 링크 하나로 공유할 수 있는 서비스입니다.

다른 사람이 만든 루틴을 구독하면 내 루틴덱에 모이고, 오늘 해야 할 일과 이번 주 해야 할 일을 한 곳에서 확인할 수 있습니다.

그리고 루틴덱의 핵심은 여기서 한 단계 더 나아갑니다.

> **내 서비스가 AI를 쓰는 것이 아니라, 사용자가 쓰는 AI가 내 서비스를 씁니다.**

Claude, ChatGPT, Cursor 같은 LLM 클라이언트가 MCP를 통해 루틴덱에 접근해, 자연어로 루틴을 조회하고 체크할 수 있습니다.

---

## 주요 기능

- **루틴 제작** — 게임별 할 일을 루틴 아이템으로 구성하고 태그로 분류
- **링크 공유** — 루틴 링크 하나로 커뮤니티, 디스코드, 길드 채팅에 공유
- **루틴 구독** — 다른 사람이 만든 루틴을 내 루틴덱에 추가
- **오늘 / 이번 주 할 일** — 구독한 루틴들을 한 곳에서 확인하고 체크
- **MCP 연동** — 사용자가 쓰는 LLM이 루틴덱 데이터를 읽고 체크

---

## MCP 연결하기

루틴덱을 MCP 서버로 연결하면 사용 중인 LLM에게 자연어로 물어볼 수 있습니다.

```
"오늘 내가 하는 게임들 숙제 뭐 남았어?"
"시간 30분밖에 없는데 우선순위로 정리해줘."
"원신 일일 의뢰 끝냈어. 체크해줘."
```

### ChatGPT (연동 예정)

ChatGPT 연동은 OAuth 2.1 구현 및 ChatGPT Apps SDK 배포 흐름 확인 후 지원 예정입니다.
아래 단계는 목표 연동 흐름이며, 실제 메뉴명은 ChatGPT 정책에 따라 변경될 수 있습니다.

1. ChatGPT 설정 → **앱** 이동
2. 고급 설정에서 **개발자 모드** 활성화
3. **앱 만들기**에서 루틴덱 MCP 서버 URL 입력
4. OAuth 인증 후 사용 가능

### Claude Desktop

`claude_desktop_config.json`에 추가하세요.

```json
{
  "mcpServers": {
    "routinedeck": {
      "url": "https://your-routinedeck-url/mcp",
      "headers": {
        "Authorization": "Bearer <your-pat>"
      }
    }
  }
}
```

### Cursor

`.cursor/mcp.json`에 추가하세요.

```json
{
  "mcpServers": {
    "routinedeck": {
      "url": "https://your-routinedeck-url/mcp",
      "headers": {
        "Authorization": "Bearer <your-pat>"
      }
    }
  }
}
```

PAT는 루틴덱 웹에서 발급할 수 있습니다.

---

## 시작하기

```bash
git clone https://github.com/rogrhrh/game-homework.git
cd game-homework
./gradlew bootRun
```

서버가 실행되면 `http://localhost:8080`으로 접근할 수 있습니다.
H2 콘솔은 `http://localhost:8080/h2-console`에서 확인할 수 있습니다.

---

## 문서

- [제품 정의](./PRODUCT.md)
- [ERD](./ERD.md)
- [API 명세](./API.md)
- [MCP 명세](./MCP.md)
