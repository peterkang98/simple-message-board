package com.peter.board.init;

import com.peter.board.domain.Comment;
import com.peter.board.domain.Post;
import com.peter.board.repository.CommentRepository;
import com.peter.board.repository.PostRepository;
import com.peter.board.service.CommentService;
import com.peter.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Profile("local")
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
	private final PostService postService;
	private final CommentService commentService;

	@Override
	public void run(String... args) throws Exception {

		Post post1 = new Post("제주도 여행 후기",
				"지난 주말에 친구들과 제주도로 여행을 다녀왔어요. " +
						"우도에서 자전거를 타고 돌았는데, 날씨가 너무 좋아서 정말 즐거웠습니다. " +
						"흑돼지 맛집도 찾았는데 인생 고기집이었어요!",
				"수지");
		post1.addComment(new Comment("제주도 정말 좋죠! 우도 카페거리도 추천드려요.", "User1"));
		post1.addComment(new Comment("사진 있으면 공유해 주세요~", "User2"));
		postService.save(post1);


		Post post2 = new Post("스프링 부트 공부 계획 공유",
				"이번 달은 스프링 부트를 집중적으로 공부하려고 합니다. " +
						"기본 CRUD부터 Security, JPA까지 확실히 익히는 게 목표예요.",
				"혁수");
		post2.addComment(new Comment("좋은 계획이에요! 같이 공부해요 💪", "User3"));
		post2.addComment(new Comment("JPA 부분 정리하시면 공유 부탁드립니다.", "User4"));
		postService.save(post2);


		Post post3 = new Post("듄 2(Dune Part Two) 후기",
				"생각보다 훨씬 스케일이 크고 몰입감이 있었습니다. " +
						"사운드와 영상미가 압도적이었고, 티모시 샬라메 연기도 좋았어요.",
				"재원");
		post3.addComment(new Comment("저도 봤는데 OST가 진짜 미쳤어요.", "User1"));
		post3.addComment(new Comment("1편보다 훨씬 재밌다고 하더라고요.", "User5"));
		postService.save(post3);


		Post post4 = new Post("오늘은 Controller 단을 리팩토링했다",
				"중복된 코드가 많아서 Controller를 공통화했어요. " +
						"BaseController를 만들어서 에러 응답 형식을 통일했습니다.",
				"동현");
		post4.addComment(new Comment("좋은 접근이네요. AOP 적용도 고려해보셨나요?", "User2"));
		post4.addComment(new Comment("리팩토링하면서 테스트 코드도 수정하셨나요?", "User3"));
		postService.save(post4);


		Post post5 = new Post("요즘 읽은 책 추천: ‘클린 아키텍처’",
				"로버트 C. 마틴의 책인데, 개발 철학을 다시 생각하게 되네요. " +
						"의존성 방향을 잘 관리하는 게 얼마나 중요한지 느꼈습니다.",
				"희수");
		post5.addComment(new Comment("이 책 정말 좋아요. ‘클린 코드’보다 실무적이에요.", "User4"));
		post5.addComment(new Comment("읽다가 어려워서 중간에 멈췄는데 다시 도전해봐야겠네요.", "User1"));
		postService.save(post5);


		Post post6 = new Post("성수동 분위기 좋은 카페 추천",
				"‘어글리 베이커’라는 카페인데 빵도 맛있고 인테리어가 예술이에요. " +
						"주말엔 사람이 많아서 일찍 가는 걸 추천드립니다!",
				"재성");
		post6.addComment(new Comment("거기 진짜 유명하죠 ㅋㅋ 대기줄 길던데요.", "User2"));
		post6.addComment(new Comment("사진 보니까 감성 터지네요. 다음에 꼭 가봐야겠어요.", "User5"));
		postService.save(post6);


		Post post7 = new Post("사이드 프로젝트 팀 모집합니다!",
				"간단한 게시판 형태의 웹 서비스를 같이 만들어볼 사람 구해요. " +
						"Spring Boot + JPA + Thymeleaf로 진행 예정입니다.",
				"경환");
		post7.addComment(new Comment("참여하고 싶어요! 프론트엔드 맡을 수 있습니다.", "User3"));
		post7.addComment(new Comment("기간은 어느 정도 예상하시나요?", "User4"));
		postService.save(post7);


		Post post8 = new Post("비 오는 날엔 역시 커피 한 잔",
				"밖에 비가 오길래 집 근처 카페에서 커피 마시며 코딩했습니다. " +
						"빗소리 들으면서 개발하니까 집중이 더 잘되네요 ☕",
				"진혁");
		post8.addComment(new Comment("공감돼요! 저는 음악 들으면서 코딩해요.", "User2"));
		post8.addComment(new Comment("그 감성 알죠... 비 오는 날은 카페죠 ㅎㅎ", "User5"));
		postService.save(post8);


		Post post9 = new Post("요즘 빠진 메뉴: 마라탕",
				"한 번 먹고 완전 중독됐어요 😂 매운 거 못 먹는데도 자꾸 생각나네요. " +
						"토핑으로 푸주랑 청경채 넣는 게 최고!",
				"지혜");
		post9.addComment(new Comment("마라샹궈도 맛있어요. 다음엔 그것도 드셔보세요!", "User1"));
		post9.addComment(new Comment("마라탕은 국물 조합이 진짜 중요하죠 ㅋㅋ", "User3"));
		postService.save(post9);


		Post post10 = new Post("JPA N+1 문제 해결기",
				"처음엔 왜 쿼리가 그렇게 많이 나가는지 이해가 안 됐는데, " +
						"Fetch Join으로 해결했습니다. Lazy Loading이 항상 나쁜 건 아니더라고요.",
				"민호");
		post10.addComment(new Comment("맞아요! 필요할 때만 fetch join 써야 해요.", "User4"));
		post10.addComment(new Comment("BatchSize 설정도 한 번 확인해보세요!", "User2"));
		postService.save(post10);
	}
}
