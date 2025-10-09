package com.peter.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class PostForm {
	private Long id;

	@NotBlank(message = "제목은 필수입니다.")
	private String title;

	@NotBlank(message = "내용은 필수입니다.")
	private String content;

	private String author;

	public PostForm() {
	}

	public PostForm(Long id, String title, String content, String author) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
	}

	public String getAuthorOrAnonymous() {
		return StringUtils.hasText(author) ? author : "익명";
	}
}
