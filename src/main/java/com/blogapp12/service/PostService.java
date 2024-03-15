package com.blogapp12.service;

import com.blogapp12.payload.ListPostDto;
import com.blogapp12.payload.PostDto;

import java.util.List;

public interface PostService {

    public PostDto createPost(PostDto postDto);

    public void deletePost(long id);

    public ListPostDto fetchAllPost(int pageNo, int pageSize, String sortBy, String sortdir);

    PostDto getById(long id);
}
