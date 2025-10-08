package com.peter.board.service;

import com.peter.board.domain.Post;
import com.peter.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;

	public Post findById(Long id) {
		return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
	}

	public List<Post> findAll() {
		return postRepository.findAll();
	}

	@Transactional
	public void save(Post post) {
		postRepository.save(post);
	}

	@Transactional
	public void update(Long id, String title, String content) {
		Post post = findById(id);
		post.update(title, content);
	}

	public void delete(Long id) {
		postRepository.deleteById(id);
	}
}
