package com.blogapp12.service.impl;

import com.blogapp12.entity.Comment;
import com.blogapp12.entity.Post;
import com.blogapp12.payload.CommentDto;
import com.blogapp12.payload.PostDto;
import com.blogapp12.payload.PostWithCommentDto;
import com.blogapp12.repository.CommentRepository;
import com.blogapp12.repository.PostRepository;
import com.blogapp12.service.CommentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private ModelMapper modelMapper;
    private PostRepository postRepository;


    @Override
    public CommentDto createPostComment(CommentDto commentDto, long postId) {
        Optional<Post> byId = postRepository.findById(postId);
        Post post = byId.get();

        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);

        Comment save = commentRepository.save(comment);
        CommentDto dto = mapToDto(save);
        return dto;
    }

    @Override
    public PostWithCommentDto findByPostId(long postId) {
        List<Comment> byPostId = commentRepository.findByPostId(postId);
        Optional<Post> byId = postRepository.findById(postId);
        Post post = byId.get();

        PostDto postDto = new PostDto();
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());

        List<CommentDto> commentDto= byPostId.stream().map(c->mapToDto(c)).collect(Collectors.toList());

        PostWithCommentDto postWithCommentDto=new PostWithCommentDto();
        postWithCommentDto.setCommentDto(commentDto);
        postWithCommentDto.setPostDto(postDto);

        return postWithCommentDto;
    }

    Comment mapToEntity(CommentDto commentDto){
        return  modelMapper.map(commentDto,Comment.class);
    }
    CommentDto mapToDto(Comment comment){

        return modelMapper.map(comment,CommentDto.class);
    }
}
