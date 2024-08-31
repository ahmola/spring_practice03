package com.example.prac03.blog;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
@RestController
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/all")
    public ResponseEntity<List<Blog>> allBlogs(){
        return new ResponseEntity<>(blogService.all(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable Long id){
        return new ResponseEntity<>(blogService.findBlogById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BlogDTO> creatBlog(@RequestBody BlogDTO blogDTO){
        return new ResponseEntity<>(blogService.createBlog(blogDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Boolean> fixBlog(@RequestBody BlogDTO blogDTO){
        return new ResponseEntity<>(blogService.fixBlog(blogDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBlog(@PathVariable Long id){
        return new ResponseEntity<>(blogService.deleteBlog(id), HttpStatus.OK);
    }
}