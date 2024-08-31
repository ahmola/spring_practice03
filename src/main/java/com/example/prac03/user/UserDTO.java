package com.example.prac03.user;

import com.example.prac03.blog.Blog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDTO {

    private Long id;

    private String username;

    private String email;

    private List<Long> blog_ids = new ArrayList<>();
}
