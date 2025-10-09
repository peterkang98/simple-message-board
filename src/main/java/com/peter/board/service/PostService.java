package com.peter.board.service;

import com.peter.board.domain.Post;
import com.peter.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;

	@Transactional(readOnly = true)
	public Post findById(Long id) {
		return postRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
	}

	@Transactional(readOnly = true)
	public Post findByIdWithComments(Long id) {
		return postRepository.findByIdWithComments(id)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
	}

	@Transactional(readOnly = true)
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	public void save(Post post) {
		postRepository.save(post);
	}

	public void update(Long id, String title, String content) {
		Post post = findById(id);
		post.update(title, content);
	}

	public void delete(Long id) {
		postRepository.deleteById(id);
	}
}
