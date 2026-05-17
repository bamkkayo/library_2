# 도서 관리 시스템 (Library Management System)

Next.js 14(App Router) 프론트엔드와 Spring Boot 3 백엔드를 연동한 풀스택 도서 관리 프로젝트입니다.  
내장형 H2 데이터베이스(MySQL 모드)를 사용하여 별도의 외부 DB 설치 없이 즉시 구동 및 테스트가 가능합니다.

---

## 기술 스택 
### Frontend
- **Framework:** Next.js 14 (App Router)
- **Styling:** Tailwind CSS
- **State/Fetch:** React Hooks & Native Fetch API

### Backend
- **Framework:** Spring Boot 3.3.0
- **Language:** Java 21
- **Build Tool:** Gradle
- **Database:** H2 Database (In-Memory, MySQL Mode)
- **Data Access:** JDBC (DriverManager)

---

## 기능 (Features)
1. **도서 목록 조회 (GET)**
   - 메인 화면에서 전체 도서 리스트를 카드로 확인
2. **도서 등록 (POST)**
   - 제목, 저자, 가격을 입력하여 새로운 도서 데이터 추가
3. **도서 상세 보기 (GET - Dynamic Routing)**
   - 도서 카드를 클릭하면 각 도서의 고유 ID(`/[id]`) 기반 동적 상세 페이지로 이동
4. **도서 삭제 (DELETE)**
   - 상세 페이지 내에서 해당 도서를 데이터베이스에서 영구 삭제

---

## 시작하기 
프로젝트를 로컬 환경에서 구동하기 위해 아래 순서대로 명령어를 실행합니다.

### 1. Repository 클론
```bash
git clone https://github.com/bamkkayo/library2
cd library2
