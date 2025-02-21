# busan_hackathon_2025_triton

<a href="https://club-project-one.vercel.app/" target="_blank">
</a>

<br/>
<br/>

## 1. Project Overview (프로젝트 개요)
- 프로젝트 이름: 자원봉사자&독거노인 매칭 플랫폼
- 프로젝트 설명: AI 기반 키워드/태그 매칭을 통한 자원봉사자 매칭 온라인 플랫폼
- 기존 서비스들과 달리 독거노인분들이 도움이 필요한 내용을 입력하시면 AI 기반으로 적절한 태그를 해당 유저에 바인딩
- 또한 봉사자들로부터 재능기부받고 싶은 부분(ex) 뜨개질 등)을 입력받아서 일방적인 봉사가 아닌 서로 도움이 되는 관계로 이끌어나가고자 함.

<br/>
<br/>

## 2. Team Members (팀원 및 팀 소개)
| 신세환 | 정의진 | 김휘수 |
|:------:|:------:|:------:|
| Back-End | Back-End | Front-End |
| [GitHub](https://github.com/sinsehwan) | [GitHub](https://github.com/Kimgooner) | [GitHub](https://github.com/snorlax914) |

<br/>
<br/>

## 3. Key Features (주요 기능)
- **회원가입**:
  - 회원가입 시 DB에 유저정보가 등록됩니다.

- **로그인**:
  - 사용자 인증 정보를 통해 로그인합니다.

- **메인 페이지**:
  - 자원봉사자와 독거노인 간 인터페이스를 다르게 구축해서 웹 서비스 사용이 익숙치 않은 분들도 쉽게 접근할 수 있도록 구성했습니다. 

- **요구사항 분석**:
  - 독거노인분들이 받고싶은 서비스를 줄 글 형식으로 입력하면 Google Gemini API를 활용한 AI가 자동으로 필요하다고 판단되는 태그를 붙여서 DB에 저장합니다.
  - 자원봉사자들도 받고싶은 재능기부활동을 입력할 수 있고 마찬가지로 AI가 해당 글을 요약해서 저장합니다.
  - 자원봉사자들이 태그가 부여된 사용자 목록을 조회할 수 있고 독거노인분들도 자원봉사자들의 희망사항을 열람할 수 있습니다.

- **채팅 생성**:
  - 대화하고 싶은 사용자를 클릭하면 새로운 1:1 채팅방이 생성되고 채팅 목록에서 조회할 수 있습니다.

- **채팅**:
  - 채팅은 WebSocket을 활용해서 실시간 통신을 보장합니다.

<br/>
<br/>

## 4. Tasks & Responsibilities (작업 및 역할 분담)
|  |  |  |
|-----------------|-----------------|-----------------|
| 신세환    | <ul><li>ERD 설계, 순서도 작성</li><li>봉사자-독거노인 채팅 페이지, 진입 구현</li><li>실시간 채팅 처리 구현</li></ul>     |
| 정의진   | <ul><li>메인 페이지 개발</li><li>AI 태그 매칭 구현</li><li>Spring Security 인증 구현</li></ul> |
| 김휘수   |<ul><li>회원가입 페이지 개발</li><li>로그인 페이지 개발</li><li>메인 페이지 개발</li></ul>  |

<br/>
<br/>

## 5. Technology Stack (기술 스택)
### 5.1 Language
|  |  |
|-----------------|-----------------|
| Java    | <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="Java" width="100"> |
| HTML5    |<img src="https://github.com/user-attachments/assets/2e122e74-a28b-4ce7-aff6-382959216d31" alt="HTML5" width="100">| 
| CSS3    |   <img src="https://github.com/user-attachments/assets/c531b03d-55a3-40bf-9195-9ff8c4688f13" alt="CSS3" width="100">|
| Javascript    |  <img src="https://github.com/user-attachments/assets/4a7d7074-8c71-48b4-8652-7431477669d1" alt="Javascript" width="100"> | 


<br/>

### 5.2 Backend
|  |  |  |
|-----------------|-----------------|-----------------|
| Spring Boot    | <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original.svg" alt="Spring Boot" width="100"> |
| H2 Database | |


<br/>

### 5.3 Cooperation
|  |  |
|-----------------|-----------------|
| Git    |  <img src="https://github.com/user-attachments/assets/483abc38-ed4d-487c-b43a-3963b33430e6" alt="git" width="100">    |
| Notion    |  <img src="https://github.com/user-attachments/assets/34141eb9-deca-416a-a83f-ff9543cc2f9a" alt="Notion" width="100">    |

<br/>

## 6. Development Workflow (개발 워크플로우)
### 브랜치 전략 (Branch Strategy)
우리의 브랜치 전략은 Git Flow를 기반으로 하며, 다음과 같은 브랜치를 사용합니다.

- Main Branch
  - 배포 가능한 상태의 코드를 유지합니다.
  - 모든 배포는 이 브랜치에서 이루어집니다.
  
- {name} Branch
  - 팀원 각자의 개발 브랜치입니다.
  - 모든 기능 개발은 이 브랜치에서 이루어집니다.

<br/>
<br/>