package com.peter.board.controller;

import com.peter.board.domain.Post;
import com.peter.board.dto.CommentForm;
import com.peter.board.dto.PostForm;
import com.peter.board.service.CommentService;
import com.peter.board.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/posts")
@Controller
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;
	private final CommentService commentService;

	@GetMapping
	public String findAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "createdDate") String sortBy,
			@RequestParam(defaultValue = "desc") String direction,
			Model model
	) {
		Sort sort = Sort.by(direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
		model.addAttribute("posts", postService.findAll(PageRequest.of(page, 5, sort)));
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("direction", direction);
		return "post/list";
	}

	// 단건 게시물 조회 + 댓글 CRUD
	@GetMapping("/{id}")
	public String createForm(@PathVariable Long id, Model model) {
		model.addAttribute("post", postService.findByIdWithComments(id));
		model.addAttribute("commentForm", new CommentForm());
		return "post/post";
	}

	// 새로운 게시물을 등록할 form 조회
	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("postForm", new PostForm());
		model.addAttribute("isEdit", false);
		return "post/form";
	}

	// 새로운 게시물을 실제 저장
	@PostMapping("/new")
	public String save(@Valid @ModelAttribute("postForm") PostForm postForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "post/form";
		}

		Post post = new Post(postForm.getTitle(), postForm.getContent(), postForm.getAuthorOrAnonymous());
		postService.save(post);
		return "redirect:/posts";
	}

	// 수정 form 조회
	@GetMapping("/{id}/edit")
	public String editForm(@PathVariable Long id, Model model) {
		Post post = postService.findById(id);
		PostForm postForm = new PostForm(post.getId(), post.getTitle(), post.getContent(), post.getAuthor());
		model.addAttribute("postForm", postForm);
		model.addAttribute("isEdit", true);
		return "post/form";
	}

	// 실제 수정
	@PostMapping("/{id}/edit")
	public String edit(@Valid @ModelAttribute("postForm") PostForm postForm, BindingResult bindingResult,
					   @PathVariable Long id, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("isEdit", true);
			return "post/form";
		}

		postService.update(id, postForm.getTitle(), postForm.getContent());
		return "redirect:/posts/" + id;
	}

	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		postService.delete(id);
		return "redirect:/posts";
	}
}
