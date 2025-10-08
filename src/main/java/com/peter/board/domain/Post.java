package com.peter.board.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends DateBaseEntity{
	@Id
	@GeneratedValue
	@Column(name = "post_id")
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false, length = 3000)
	private String content;
	private String author;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();

	public Post(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}

	// 연관 관계 편의 메소드 / 댓글 추가 기능
	public void addComment(Comment comment) {
		comments.add(comment);
		comment.setPost(this);
	}

	// 게시글 수정 메소드
	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
