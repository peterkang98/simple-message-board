package com.peter.board.controller;

import com.peter.board.domain.Comment;
import com.peter.board.domain.Post;
import com.peter.board.dto.CommentForm;
import com.peter.board.service.CommentService;
import com.peter.board.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/posts/{postId}/comments")
@Controller
@RequiredArgsConstructor
public class CommentController {
	private final PostService postService;
	private final CommentService commentService;

	@PostMapping("/new")
	public String create(@PathVariable Long postId,
						 @Valid @ModelAttribute("commentForm") CommentForm commentForm,
						 BindingResult bindingResult, Model model)
	{
		if (bindingResult.hasErrors()) {
			Post post = postService.findByIdWithComments(postId);
			model.addAttribute("post", post);
			model.addAttribute("commentError", true);
			return "post/post";
		}
		commentService.addComment(postId, commentForm.getAuthorOrAnonymous(), commentForm.getContent());
		return "redirect:/posts/" + postId;
	}

	@PostMapping("/{id}/edit")
	public String edit(@PathVariable Long postId,
					   @PathVariable Long id,
					   @Valid @ModelAttribute("commentForm") CommentForm commentForm,
					   BindingResult bindingResult,
					   Model model)
	{
		if (bindingResult.hasErrors()) {
			Post post = postService.findByIdWithComments(postId);
			Comment comment = post.getComments().stream()
					.filter(c -> c.getId().equals(id))
					.findFirst()
					.orElse(null);

			if (comment != null && !StringUtils.hasText(commentForm.getContent())) {
				commentForm.setContent(comment.getContent());
			}

			model.addAttribute("post", post);
			model.addAttribute("errorCommentId", id);
			return "post/post";
		}
		commentService.updateComment(id, commentForm.getContent());
		return "redirect:/posts/" + postId;
	}

	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long postId, @PathVariable Long id) {
		commentService.deleteComment(postId, id);
		return "redirect:/posts/" + postId;
	}
}
