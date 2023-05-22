package com.mubarak.blogrestapi.repository;

import com.mubarak.blogrestapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
