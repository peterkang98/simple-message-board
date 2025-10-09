package com.peter.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class CommentForm {

	@NotBlank(message = "댓글 내용은 필수입니다")
	private String content;
	private String author;

	public String getAuthorOrAnonymous() {
		return StringUtils.hasText(author) ? author : "익명";
	}
}
