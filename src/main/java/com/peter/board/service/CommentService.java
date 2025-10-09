package com.peter.board.service;

import com.peter.board.domain.Comment;
import com.peter.board.domain.Post;
import com.peter.board.repository.CommentRepository;
import com.peter.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;

	public void addComment(Long postId, String author, String content) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
		Comment comment = new Comment(content, author, post);
		commentRepository.save(comment);
	}

	public void updateComment(Long commentId, String content) {
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
		comment.update(content);
	}

	public void deleteComment(Long postId, Long commentId) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
		post.getComments().remove(comment);
	}
}
