package com.TS.TwiterSimplicado.controller;

import org.springframework.web.bind.annotation.RestController;

import com.TS.TwiterSimplicado.controller.dto.CreatePostDto;
import com.TS.TwiterSimplicado.entities.Post;
import com.TS.TwiterSimplicado.repository.PostRepository;
import com.TS.TwiterSimplicado.repository.UserRepository;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class PostController {
    
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    public PostController(PostRepository postRepository, UserRepository userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/posts")
    public ResponseEntity<Void> createPost(@RequestBody CreatePostDto dto, JwtAuthenticationToken token) {
        var user = userRepository.findById(UUID.fromString(token.getName()));
        var post = new Post();
        post.setUser(user.get());
        post.setContent(dto.content());
        postRepository.save(post);
        return ResponseEntity.ok().build();
    }
    
}
