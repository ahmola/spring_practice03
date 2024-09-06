package com.example.prac03.blog;

import com.example.prac03.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public List<Blog> all() {
        return blogRepository.findAll();
    }

    public Blog findBlogById(Long id) {
        if(blogRepository.findById(id).isEmpty())
            throw new RuntimeException("There is no such blog : " + id);
        return blogRepository.findById(id).get();
    }

    public BlogDTO createBlog(BlogDTO blogDTO) {
        Blog blog = new Blog();
        blog.setSubject(blogDTO.getSubject());
        blog.setBody(blogDTO.getBody());
        if(!userRepository.existsById(blogDTO.getUser_id()))
            throw new RuntimeException("There is no such user : " + blogDTO.getUser_id());
        blog.setUser(userRepository.findById(blogDTO.getUser_id()).get());
        blogRepository.save(blog);
        return blogDTO;
    }

    public Boolean fixBlog(BlogDTO blogDTO) {
        if(!blogRepository.existsById(blogDTO.getId()))
            throw new RuntimeException("There is no such blog : " + blogDTO.getId());

        Blog blog = blogRepository.findById(blogDTO.getId()).get();
        blog.setSubject(blogDTO.getSubject());
        blog.setBody(blogDTO.getBody());
        if(!userRepository.existsById(blogDTO.getUser_id()))
            throw new RuntimeException("There is no such user : " + blogDTO.getUser_id());
        blog.setUser(userRepository.findById(blogDTO.getUser_id()).get());

        blogRepository.save(blog);

        return true;
    }

    public Boolean deleteBlog(Long id) {
        if(!blogRepository.existsById(id))
            throw new RuntimeException("There is no such blog : " + id);

        blogRepository.deleteById(id);
        return true;
    }
}
