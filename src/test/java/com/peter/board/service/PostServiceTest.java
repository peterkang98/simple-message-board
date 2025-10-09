package com.peter.board.service;

import com.peter.board.domain.Comment;
import com.peter.board.domain.Post;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class PostServiceTest {
	@Autowired
	PostService postService;

	@Autowired
	EntityManager em;

	@Autowired
	CommentService commentService;

	@Test
	void findById() {
		Post post = getPost();
		postService.save(post);

		em.flush();
		em.clear();

		Post foundPost = postService.findById(post.getId());
		assertThat(foundPost.getId()).isEqualTo(post.getId());
		assertThat(foundPost.getTitle()).isEqualTo(post.getTitle());
		assertThat(foundPost.getContent()).isEqualTo(post.getContent());
	}

	@Test
	void findByIdWithComments() {
		Post post = getPost();
		postService.save(post);

		em.flush();
		em.clear();

		// JOIN FETCH로 특정 게시물에 있는 모든 댓글을 한 번에 가져오기
		Post foundPost = postService.findByIdWithComments(post.getId());

		// 댓글 리스트의 크기 + 내용 비교
		List<Comment> foundComments = foundPost.getComments();
		assertThat(foundComments.size()).isEqualTo(post.getComments().size());

		for (int i = 0; i < foundComments.size(); i++) {
			Comment foundComment = foundComments.get(i);
			Comment originalComment = post.getComments().get(i);

			assertThat(foundComment.getContent()).isEqualTo(originalComment.getContent());
			assertThat(foundComment.getAuthor()).isEqualTo(originalComment.getAuthor());
		}

	}

	@Test
	void findAll() {
		for (int i = 1; i <= 15; i++) {
			Post post = new Post("제목" + i, "내용", "동현");
			postService.save(post);
		}

		em.flush();
		em.clear();

		// 생성된 날짜를 내림차순으로 정렬한 요소들 중에서 처음 5개 가져오기
		Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "createdDate"));
		Page<Post> page = postService.findAll(pageable);
		List<Post> posts = page.getContent();

		assertThat(page.getTotalElements()).isEqualTo(15);
		assertThat(page.getTotalPages()).isEqualTo(3);
		assertThat(posts.size()).isEqualTo(5);

		for (int i = 0; i < posts.size() - 1; i++) {
			System.out.println("posts.get(i).getCreatedDate() = " + posts.get(i).getCreatedDate());
			assertThat(posts.get(i).getCreatedDate()).isAfterOrEqualTo(posts.get(i + 1).getCreatedDate());
		}
	}

	@Test
	void update() {
		Post post = getPost();
		postService.save(post);

		em.flush();
		em.clear();

		String newTitle = "새로운 제목";
		String newContent = "새로운 내용";
		Post foundPost = postService.findById(post.getId());
		postService.update(foundPost.getId(), newTitle, newContent);

		em.flush();
		em.clear();

		Post updatedPost = postService.findById(post.getId());
		assertThat(updatedPost.getTitle()).isEqualTo(newTitle);
		assertThat(updatedPost.getContent()).isEqualTo(newContent);
	}

	@Test
	void delete() {
		Post post = getPost();
		postService.save(post);

		em.flush();
		em.clear();

		Post foundPost = postService.findById(post.getId());
		List<Comment> foundComments = foundPost.getComments();
		postService.delete(foundPost.getId());

		em.flush();
		em.clear();

		assertThatThrownBy(() -> postService.findById(post.getId())).isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> commentService.updateComment(foundComments.get(0).getId(), "새로운 내용"))
				.isInstanceOf(IllegalArgumentException.class);
	}

	private Post getPost() {
		Post post = new Post("제목", "내용", "동현");
		post.addComment(new Comment("댓글 내용1", "희수"));
		post.addComment(new Comment("댓글 내용2", "경환"));
		return post;
	}
}
