package com.blogapp12.controller;

import com.blogapp12.payload.CommentDto;
import com.blogapp12.payload.PostWithCommentDto;
import com.blogapp12.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;

    //http://localhost:8080/api/comments/4
    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable long postId){

        CommentDto dto = commentService.createPostComment(commentDto, postId);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/comments/4
    @GetMapping("/{postId}")
    public ResponseEntity<PostWithCommentDto> findAllComment(@PathVariable long postId){
        PostWithCommentDto postWithCommentDto = commentService.findByPostId(postId);
        return new ResponseEntity<>(postWithCommentDto,HttpStatus.OK);
    }
}
