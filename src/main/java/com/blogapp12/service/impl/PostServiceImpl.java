package com.blogapp12.service.impl;
import com.blogapp12.entity.Post;
import com.blogapp12.exception.NotfoundException;
import com.blogapp12.payload.ListPostDto;
import com.blogapp12.payload.PostDto;
import com.blogapp12.repository.PostRepository;
import com.blogapp12.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements   PostService {

    private PostRepository postRepository;

    private ModelMapper modelMapper;
    public PostServiceImpl(PostRepository postRepository,ModelMapper modelMapper) {

        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);

        Post saved = postRepository.save(post);

        PostDto dto = mapToPostDto(saved);
        return dto;
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);

    }

    @Override
    public ListPostDto fetchAllPost(int pageNo, int pageSize, String sortBy, String sortdir) {
        Sort sort = sortdir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(pageNo,pageSize, sort);
        Page<Post> all=postRepository.findAll(pageable);
        List<Post>data= all.getContent();
        List<PostDto> postDto = data.stream().map(p -> mapToPostDto(p)).collect(Collectors.toList());

        ListPostDto listPostDto=new ListPostDto();
        listPostDto.setPostDto(postDto);
        listPostDto.setTotalElement(all.getNumber());
        listPostDto.setFirstPage(all.isFirst());
        listPostDto.setLastPage(all.isLast());
        listPostDto.setTotalElement(all.getNumberOfElements());

        return listPostDto;
    }

    @Override
    public PostDto getById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new NotfoundException("No Record Found With Post Id "));
        return mapToPostDto(post);
    }

    Post mapToEntity (PostDto postDto){
       Post post = modelMapper.map(postDto , Post.class);
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());

        return post;
    }
    PostDto mapToPostDto (Post post){
        PostDto dto = modelMapper.map(post,PostDto.class);
//        PostDto dto = new PostDto();
//        dto.setContent(post.getContent());
//        dto.setDescription(post.getDescription());
//        dto.setTitle(post.getTitle());

        return dto;
    }
}

