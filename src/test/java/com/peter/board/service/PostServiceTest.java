package com.peter.board.service;

import com.peter.board.domain.Comment;
import com.peter.board.domain.Post;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

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
		Post post = new Post("제목1", "내용1", "동현");
		Post post2 = new Post("제목2", "내용2", "경환");
		postService.save(post);
		postService.save(post2);

		em.flush();
		em.clear();

		List<Post> allPosts = postService.findAll();

		assertThat(allPosts.size()).isEqualTo(2);

		assertThat(allPosts.get(0).getId()).isEqualTo(post.getId());
		assertThat(allPosts.get(1).getId()).isEqualTo(post2.getId());
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
