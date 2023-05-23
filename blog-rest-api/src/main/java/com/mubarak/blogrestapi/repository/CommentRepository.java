package com.mubarak.blogrestapi.repository;

import com.mubarak.blogrestapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

List<Comment> findBypostId(long postId);

}
