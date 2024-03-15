package com.blogapp12.payload;

import com.blogapp12.entity.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostWithCommentDto {

    private PostDto postDto;
    private List<CommentDto> commentDto;


}
