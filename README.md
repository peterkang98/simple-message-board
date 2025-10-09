# 간단한 CRUD 게시판 프로젝트
- 도메인 모델 패턴으로 설계
- `Post` ↔ `Comment` 양방향 1:N 관계
- 게시물 페이징, 정렬 기능
  - 1 페이지에 게시물 5개씩
  - 게시물의 생성날짜, 작성자 또는 제목을 오름차순/내림차순으로 정렬 가능

## 기술 스택
- **Spring Boot 3.5.6**
  - Spring Data JPA
  - Spring Validation
- **H2 Database**
  - 인 메모리로 실행
  - jdbc url = jdbc:h2:mem:boarddb
  - username = sa
- **Thymeleaf**
- **Lombok**
- **P6Spy** (SQL 로그 출력)