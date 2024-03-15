package com.blogapp12.controller;

import com.blogapp12.entity.Post;
import com.blogapp12.payload.ListPostDto;
import com.blogapp12.payload.PostDto;
import com.blogapp12.service.PostService;
import javax.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;
    public PostController(PostService postService) {

        this.postService = postService;
    }



    //http://localhost:8080/api/posts
    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
          return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/posts/2
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post is deleted",HttpStatus.OK);
    }

    //http://localhost:8080/api/posts?pageNo=0&pageSize=3&sortBy=title&sortdir=asc
    @GetMapping
    public ResponseEntity<ListPostDto> getAllPost(
            @RequestParam(name="pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(name="pageSize",defaultValue = "3",required = false)int pageSize,
            @RequestParam(name="sortBy",defaultValue = "id",required = false)String sortBy,
            @RequestParam (name="sortdir",defaultValue = "id",required = false)String sortdir){
            ListPostDto listPostDto = postService.fetchAllPost(pageNo,pageSize,sortBy,sortdir);
        return new ResponseEntity<>(listPostDto,HttpStatus.OK);
    }

    //http://localhost:8080/api/4
    @GetMapping("{id}")
    public ResponseEntity<PostDto> getById(@PathVariable long id){
        PostDto dto = postService.getById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

}
