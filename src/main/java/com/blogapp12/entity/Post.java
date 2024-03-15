package com.blogapp12.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="posts")
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    @Size(min=3,message = "title should be 3 char")
    private String title;
    @NotEmpty
    @Size(min=3,message = "should be 3 char")
    private String description;
    private String content;

    @OneToMany(mappedBy = "post",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<Comment> comments=new ArrayList<>();
}
