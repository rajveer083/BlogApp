package com.blogapp12.repository;

import com.blogapp12.entity.Comment;
import com.blogapp12.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByPostId(long postId);
}
