package com.peter.board.repository;

import com.peter.board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
	@Query("SELECT p FROM Post p LEFT JOIN FETCH p.comments WHERE p.id = :id")
	Optional<Post> findByIdWithComments(@Param("id") Long id);
}
