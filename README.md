# 간단한 CRUD 게시판 프로젝트
- 도메인 모델 패턴으로 설계
- `Post` ↔ `Comment` 양방향 1:N 관계
- 게시물 페이징, 정렬 기능
  - 1 페이지에 게시물 5개씩
  - 게시물의 생성날짜, 작성자 또는 제목을 오름차순/내림차순으로 정렬 가능
- 검증 기능
  - 댓글 내용은 비어 있으면 검증 실패
  - 게시물 내용, 제목이 비어 있으면 검증 실패
  - 댓글, 게시물 모두 작성자가 비어 있으면 익명으로 대체
 
## UML
<img width="1647" height="1177" alt="Image" src="https://github.com/user-attachments/assets/4adb7f1e-945a-4006-975d-14628bdc3906" />


## ERD
<img width="2218" height="498" alt="Image" src="https://github.com/user-attachments/assets/3969e1e2-90df-4968-a72a-a24dc0abd248" />

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

## 스크린샷
<img width="2678" height="1014" alt="Image" src="https://github.com/user-attachments/assets/10c18888-5661-47d6-9b1d-255755c81492" />
<img width="1908" height="876" alt="Image" src="https://github.com/user-attachments/assets/1d04bfe5-c9cc-44fd-adeb-1009b9bfb600" />
<img width="1910" height="850" alt="Image" src="https://github.com/user-attachments/assets/ded482bb-6e7f-4731-a6a1-a3062579bd78" />
