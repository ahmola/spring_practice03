package com.example.prac03.user;

import com.example.prac03.blog.Blog;
import com.example.prac03.blog.BlogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUser(Long id) {
        return userRepository.findById(id).get();
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        
        userRepository.save(user);

        return userDTO;
    }

    public Boolean fixUser(UserDTO userDTO) {
        if(!userRepository.existsById(userDTO.getId()))
            throw new RuntimeException("There is no such User : " + userDTO.getId());

        User user = userRepository.findById(userDTO.getId()).get();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        List<Blog> blogs = new ArrayList<>();
        for (Long id: userDTO.getBlog_ids()) {
            if(!blogRepository.existsById(id))
                throw new RuntimeException("There is no such blog : " + id);
            blogs.add(blogRepository.findById(id).get());
        }
        user.getBlogs().addAll(blogs);

        userRepository.save(user);

        return true;
    }

    public Boolean deleteUser(Long id) {
        if(!userRepository.existsById(id))
            throw new RuntimeException("There is no such User : " + id);
        userRepository.deleteById(id);
        return true;
    }
}
