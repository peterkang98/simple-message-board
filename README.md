# 간단한 CRUD 게시판 프로젝트
- 도메인 모델 패턴으로 설계
- `Post` ↔ `Comment` 양방향 1:N 관계

## 기술 스택
- Spring Boot 3.5.6
  - Spring Data JPA
  - Spring Validation
- H2 Database
  - 인 메모리로 실행
  - jdbc url = jdbc:h2:mem:boarddb
  - username = sa
- Thymeleaf
- Lombok
- P6Spy (SQL 로그 출력)