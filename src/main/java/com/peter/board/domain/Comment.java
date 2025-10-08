package com.peter.board.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends DateBaseEntity{
	@Id
	@GeneratedValue
	@Column(name = "comment_id")
	private Long id;

	@Column(nullable = false, length = 3000)
	private String content;
	private String author;

	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	public Comment(String content, String author, Post post) {
		this.content = content;
		this.author = author;
		this.post = post;
	}
}
