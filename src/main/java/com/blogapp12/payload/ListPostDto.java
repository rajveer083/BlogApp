package com.blogapp12.payload;

import lombok.Data;

import java.util.List;

@Data
public class ListPostDto {

    private List<PostDto> postDto;

    private int totalElement;

    private boolean lastPage;

    private boolean firstPage;
}
