package com.peter.board.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	public Comment(String content, String author) {
		this.content = content;
		this.author = author;
	}

	// 연관 관계 설정
	// 서비스나 컨트롤러 등 외부 계층에서는 호출할 수 없게 protected로 막음
	protected void assignTo(Post post) {
		this.post = post;
	}

	public void update(String content) {
		this.content = content;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Comment comment = (Comment) o;
		return Objects.equals(getId(), comment.getId()) && Objects.equals(getContent(), comment.getContent()) && Objects.equals(getAuthor(), comment.getAuthor());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getContent(), getAuthor());
	}
}
